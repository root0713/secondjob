package com.mlog.comm.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

public class FileIoUtils {
    private static final Logger log = LoggerFactory.getLogger(FileIoUtils.class);

    /**
     * @return d:/Downloads/aaa.txt --> aaa.txt
     */
    public static String getName(String filePath) {
        return FilenameUtils.getName(filePath);
    }

    /**
     * @return d:/Downloads/aaa.txt --> txt
     */
    public static String getExtension(String filePath) {
        return FilenameUtils.getExtension(filePath);
    }

    /**
     * @return d:/Downloads/aaa.txt --> d:/Downloads
     */
    public static String getDirName(String filePath) {
        int idx = filePath.indexOf(getName(filePath));
        return filePath.substring(0, idx > 0 ? idx - 1 : 0);
    }

    /**
     * @return d:/Downloads/aaa.txt --> aaa
     */
    public static String getBaseName(String filename) {
        return FilenameUtils.getBaseName(filename);
    }

    public static String streamToString(InputStream input, String encoding) {
        try {
            return IOUtils.toString(input, encoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    public static String streamToString(InputStream input) {
        return streamToString(input, null);
    }

    public static String fileToString(File file) {
        return fileToString(file, null);
    }

    public static String fileToString(File file, String encoding) {
        try {
            return org.apache.commons.io.FileUtils.readFileToString(file, encoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stringToFile(String text, File file) {
        stringToFile(text, file, null);
    }

    public static void stringToFile(String text, File file, String encoding) {
        try {
            org.apache.commons.io.FileUtils.write(file, text, encoding);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * stream을 복사하고 두 스트림 모두 닫는다.
     */
    public static void copy(InputStream src, OutputStream dst) {
        try {
            IOUtils.copy(src, dst);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(src);
            IOUtils.closeQuietly(dst);
        }
    }

    public static void copy(String src, String dst) {
        try {
            org.apache.commons.io.FileUtils.copyFile(new File(src), new File(dst));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File mkDirIfNotExist(String dir) {
        File file = new File(dir);
        if (!file.isDirectory())
            file.mkdirs();
        return file;
    }

//	public static String getFileEncoding(File file) {
//		try {
//			return detectCharset(new FileInputStream(file));
//		}
//		catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//	}
//	
//	public static String detectCharset(InputStream in) {
//		byte[] buf = new byte[4096];
//		String encoding = "EUC-KR";
//
//		try {
//			UniversalDetector detector = new UniversalDetector(null);
//
//			int nread = 0;
//			while ((nread = in.read(buf)) > 0 && !detector.isDone()) {
//				detector.handleData(buf, 0, nread);
//			}
//			detector.dataEnd();
//
//			String enc = detector.getDetectedCharset();
//			if (enc != null && !enc.equals("WINDOWS-1252")) { // EUC-KR을 WINDOWS-1252로 찾는 버그 있음.
//				encoding = enc;
//			}
//
//			detector.reset();
//			return encoding;
//		}
//		catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		finally {
//			IOUtils.closeQuietly(in);
//		}
//	}

    public static void fileDownload(File file
            , HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = file.getName();
        long fileSize = file.length();
        InputStream in = new FileInputStream(file);
        fileDownload(request, response, fileName, fileSize, in);
    }

    public static void fileDownload(Resource res
            , HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileName = res.getFilename();
        long fileSize = res.contentLength();
        InputStream in = res.getInputStream();
        fileDownload(request, response, fileName, fileSize, in);
    }

    public static void fileDownload(HttpServletRequest request, HttpServletResponse response
            , String fileName, long fileSize, InputStream in) throws IOException {
        // response header
        String userAgent = request.getHeader("User-Agent");
        String fname, dispos;
        if (userAgent.indexOf("MSIE") >= 0) {
            fname = URLEncoder.encode(fileName, "UTF-8");
        } else {
            String fenc = System.getProperty("file.encoding");
            if (fenc != null && fenc.equals("IBM-eucKR"))
                fname = new String(fileName.getBytes("UTF-8"), "UTF-8"); // for dev server (AIX 5, jeus6)
            else
                fname = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); // for local, stp, rel server (winXP, tomcat6, AIX 5, jeus6)
        }
        dispos = String.format("attachment; filename=\"%s\"", fname);

        response.setHeader("Content-Disposition", dispos);
        response.setHeader("Content-Transfer-Encoding", "binary;");
        response.setHeader("Content-Length", String.valueOf(fileSize));
//		response.setHeader("Pragma", "no-cache;");
//		response.setHeader("Expires", "-1;");

        // write to response
        OutputStream out = response.getOutputStream();
        IOUtils.copy(in, out);
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
    }

    public static String fileUpload(MultipartFile file, String saveDir, String name) {
        if (file == null || file.isEmpty())
            return null;

        File dir = mkDirIfNotExist(saveDir);
//		String type = file.getContentType();
//		String name = file.getOriginalFilename();
//		long size = file.getSize();
//		log.debug("name = [" + name + "], size = [" + size + "], type = [" + type + "]");
        File path = new File(dir, name);
        try {
            file.transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path.getPath();
    }

    /**
     * @param ext "."를 포함해야 정확한 결과를 얻는다.
     */
    public static boolean isExtension(String fname, String... exts) {
        if (fname == null) return false;
        for (String ext : exts) {
            if (fname.toLowerCase().endsWith(ext.toLowerCase()))
                return true;
        }
        return false;
    }

    /**
     * 파일이 없으면 생성, 있으면 추가.
     */
    public static void writeLine(File file, String line) {
        try {
            FileUtils.writeLines(file, Arrays.asList(line), true);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public static void writeLines(File file, List<String> lines) {
        try {
            FileUtils.writeLines(file, lines, true);
        } catch (IOException e) {
            log.error("", e);
        }
    }

    public static int objectSize(Object object) {
        ByteArrayOutputStream out = null;
        int size = 0;
        try {
            out = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(out);
            stream.writeObject(object);
            stream.flush();
            size = out.size();
        } catch (IOException e) {
            log.error("Exception while retrieving size " + e);
        }
        return size;
    }

}
