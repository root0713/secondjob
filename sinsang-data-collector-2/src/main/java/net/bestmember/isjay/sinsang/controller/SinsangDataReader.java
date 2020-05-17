package net.bestmember.isjay.sinsang.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.http.impl.client.BasicCookieStore;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.JSONObject;

import net.bestmember.isjay.common.util.HttpClient;
import net.bestmember.isjay.common.util.StringUtils;
import net.bestmember.isjay.common.vo.CodeResult;

public class SinsangDataReader {
	
	static final int DEFAULT_MEN_CATE_ID = 1;
	static final int DEFAULT_WOMEN_CATE_ID = 2;
	static final int DEFAULT_MEN_CATE_SIZE = 36;
	static final int DEFAULT_WOMEN_CATE_SIZE = 200;
			
	static BasicCookieStore cookieStore = new BasicCookieStore();
			
	public static List<String> getCategoryURLList(){
		return getCategoryURLList("", 0, 5000, 10000);
	}
	
	public static List<String> getCategoryURLList(String gender, int size, int priceLow, int priceHigh){
		/**
		 * 1. 남성 티/탑 : 신상배송 / 댄디 / 대한민국 / 5000~10000
		 * https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size=36&disableAds=1&enableSDelivery=1&catId=12&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow=5000&priceHigh=10000
		 * 2. 남성 셔츠/남 : 신상배송 / 댄디 / 대한민국 / 5000~10000
		 * https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size=36&disableAds=1&enableSDelivery=1&catId=13&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow=5000&priceHigh=10000
		 * 3. 남성 청바 : 신상배송 / 전체 / 대한민국 / 5000~10000
		 * https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size=36&disableAds=1&enableSDelivery=1&catId=16&catGenderId=2&catItemId=1&manufacturingCountryId=1&priceLow=5000&priceHigh=10000
		 * 4. 남성 팬 : 신상배송 / 전체 / 대한민국 / 5000~10000
		 * https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size=36&disableAds=1&enableSDelivery=1&catId=17&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow=5000&priceHigh=10000
		 * 5. 여성의류 전체 : 신상초이스 / 전체 / 대한민국 / 5000~10000
		 * https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size=36&disableAds=1&storeId=9164&catGenderId=1&catItemId=1&manufacturingCountryId=1&priceLow=5000&priceHigh=10000
		**/
		int catGenderId = "men".equals(gender) ? DEFAULT_MEN_CATE_ID : "women".equals(gender) ? DEFAULT_WOMEN_CATE_ID : 0;
		List<String> categoryList = new ArrayList<String>();
		if(DEFAULT_MEN_CATE_ID == catGenderId) {
			if(size == 0) size = DEFAULT_MEN_CATE_SIZE;
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ size +"&disableAds=1&enableSDelivery=1&catId=12&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ size +"&disableAds=1&enableSDelivery=1&catId=13&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ size +"&disableAds=1&enableSDelivery=1&catId=16&catGenderId=2&catItemId=1&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ size +"&disableAds=1&enableSDelivery=1&catId=17&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
		}else if(DEFAULT_WOMEN_CATE_ID == catGenderId) {
			if(size == 0) size = DEFAULT_WOMEN_CATE_SIZE;
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ size +"&disableAds=1&storeId=9164&catGenderId=1&catItemId=1&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
		}else {
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ DEFAULT_MEN_CATE_SIZE +"&disableAds=1&enableSDelivery=1&catId=12&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ DEFAULT_MEN_CATE_SIZE +"&disableAds=1&enableSDelivery=1&catId=13&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ DEFAULT_MEN_CATE_SIZE +"&disableAds=1&enableSDelivery=1&catId=16&catGenderId=2&catItemId=1&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ DEFAULT_MEN_CATE_SIZE +"&disableAds=1&enableSDelivery=1&catId=17&catGenderId=2&catItemId=1&styleId=13&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
			categoryList.add("https://search-api.sinsangmarket.co.kr/product/list/category?page=1&size="+ DEFAULT_WOMEN_CATE_SIZE +"&disableAds=1&storeId=9164&catGenderId=1&catItemId=1&manufacturingCountryId=1&priceLow="+priceLow+"&priceHigh="+priceHigh);
		}
		return categoryList; 
	}
	
	public static String getSinsangList(String url, Map<String, String> header) {
		CodeResult<String> result = HttpClient.httpGet(url, header);
//		System.out.println(result.getData().toString());
		return result.getData();
	}
	
	public static String getSinsangDetail(int gid, Map<String, String> header) throws Exception {
		String url = "https://www.sinsangmarket.kr/api/getSomeGoodsData?userid=bearbell&se=&oauth_token=F0884F0EB339E9A1EBE3D36C8D540B4A25E6CA4D329224485B3CF659D6F3DF39&gid=" + gid;
		CodeResult<String> result = HttpClient.httpGet(url, header, 30 * 1000, cookieStore);
//		System.out.println(result.getData().toString());
		return result.getData();
	}
	
	public static String getSinsagChoiceDetail(int gid, Map<String, String> header) throws Exception {
		String url = "https://www.sinsangmarket.kr/api/getSChoiceGoodsData?userid=bearbell&se=&oauth_token=F0884F0EB339E9A1EBE3D36C8D540B4A25E6CA4D329224485B3CF659D6F3DF39&gid=" + gid;
		CodeResult<String> result = HttpClient.httpGet(url, header, 30 * 1000, cookieStore);
//		System.out.println(result.getData().toString());
		return result.getData();
	}
	
	public static Object getSinsangDetail(String url, Map<String, String> header) throws Exception {
		CodeResult<String> result = HttpClient.httpGet(url, header, 30 * 1000, cookieStore);
//		System.out.println(result.getData().toString());
		return result.getData();
	}
	
	public Object getSinsangDetailImage(JSONObject productJSON) {
		return null;
	}
	
	public static HashMap<String, String> getListApiHeader(){
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("userId", "1c9d73a691a999c41a776730643b9a7e");
		header.put("userIdRaw", "bearbell");
		header.put("deviceId", "ba8a1b8cb8db825c33f99086e172cf86");
		header.put("serviceId", "ssm");
		header.put("terminalId", "10001001");
		header.put("accessToken", "F0884F0EB339E9A1EBE3D36C8D540B4A25E6CA4D329224485B3CF659D6F3DF39");
		return header;
	}
	
	public static HashMap<String, String> getDetailApiHeader(){
		HashMap<String, String> header = new HashMap<String, String>();
		header.put("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
		return header;
	}
	
	public static void setDetailApiCookie() throws Exception{
		HttpClient.parseRawCookie("_ga=GA1.2.753454924.1580877504;_fbp=fb.1.1580877263374.344891722;_gid=GA1.2.720628637.1582681583;", "/", ".sinsangmarket.kr", cookieStore);
		HttpClient.parseRawCookie("PHPSESSID=21698s6q00fvv36r9cnhgrbvb6;PHPSESSID=j8u793874c0m7lu84vnk2t9192;cd8562d226d37128b84a8bc1aad7ea3a=MjEwLjEwOC4xMzguMw%3D%3D;autoLogin=y;userid=bearbell;rsIdx=165465;memberPart=R;certStatus=Confirmed;userName=%EB%B0%B0%EC%A7%84%EC%84%B1;storeName=%EB%B2%A0%EC%96%B4%EB%B2%A8;oauth_token=F0884F0EB339E9A1EBE3D36C8D540B4A25E6CA4D329224485B3CF659D6F3DF39;cec01c36d54bc4dc676baa8b7bf8cfaa=c4408d355cbe21015b113a41975968bf;isAutoLogin=y;imgpopup=no;thisTimeSession=bd7c9c77de9af1563c5f84b818399bc2;wcs_bt=b2b2e49974a34:1582682569;", "/", ".www.sinsangmarket.kr", cookieStore);
	}
	
//	static final String DEFAULT_PATH = "/Users/sujin/Library/Mobile Documents/com~apple~CloudDocs/Documents/sinsang/scraping/collect/20.03.19/5000-9999/";
	static final String DEFAULT_PATH = "/Users/isjung/Library/Mobile Documents/com~apple~CloudDocs/Documents/sinsang/scraping/collect/";
	static final String[] DEFAULT_FILE_NAME = {
			"20.05.17/_sinsang_korea.xlsx"
	};
	
	public static void imageDownloadByReadExcel() throws EncryptedDocumentException, InvalidFormatException, IOException {
		imageDownloadByReadExcel(DEFAULT_PATH, DEFAULT_FILE_NAME);
	}
	
	public static void imageDownloadByReadExcel(String filePath, String[] fileName) throws EncryptedDocumentException, InvalidFormatException, IOException {
		for(String file : fileName) {
			excuteDownload(filePath, file);
		}
	}

	public static void excuteDownload(String filePath, String file) throws IOException, InvalidFormatException {
		String XLSX_FILE_PATH = filePath + file;
		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(new File(XLSX_FILE_PATH));
		// Retrieving the number of sheets in the Workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");
		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);
		System.out.println("\\nfile name : "+XLSX_FILE_PATH+" job starting\n");
		for (Row row: sheet) {
			for(Cell cell: row) {
				imageDownload(cell);
			}
			System.out.println();
		}
		workbook.close();
		System.out.println("\\nfile name : "+XLSX_FILE_PATH+" job end\n");
	}
	
	
	public static void readExcel() throws EncryptedDocumentException, InvalidFormatException, IOException {
    	String SAMPLE_XLSX_FILE_PATH = DEFAULT_PATH + DEFAULT_FILE_NAME;
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        Workbook workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));

        // Retrieving the number of sheets in the Workbook
        System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

        /*
           =============================================================
           Iterating over all the sheets in the workbook (Multiple ways)
           =============================================================
        */

        // 1. You can obtain a sheetIterator and iterate over it
//        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
//        System.out.println("Retrieving Sheets using Iterator");
//        while (sheetIterator.hasNext()) {
//            Sheet sheet = sheetIterator.next();
//            System.out.println("=> " + sheet.getSheetName());
//        }
//
//        // 2. Or you can use a for-each loop
//        System.out.println("Retrieving Sheets using for-each loop");
//        for(Sheet sheet: workbook) {
//            System.out.println("=> " + sheet.getSheetName());
//        }
//
//        // 3. Or you can use a Java 8 forEach wih lambda
//        System.out.println("Retrieving Sheets using Java 8 forEach with lambda");
//        workbook.forEach(sheet -> {
//            System.out.println("=> " + sheet.getSheetName());
//        });

        /*
           ==================================================================
           Iterating over all the rows and columns in a Sheet (Multiple ways)
           ==================================================================
        */

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
//        Iterator<Row> rowIterator = sheet.rowIterator();
//        while (rowIterator.hasNext()) {
//            Row row = rowIterator.next();
//
//            // Now let's iterate over the columns of the current row
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//            while (cellIterator.hasNext()) {
//                Cell cell = cellIterator.next();
//                String cellValue = dataFormatter.formatCellValue(cell);
//                System.out.print(cellValue + "\t");
//            }
//            System.out.println();
//        }
//
        // 2. Or you can use a for-each loop to iterate over the rows and columns
        System.out.println("\n\nIterating over Rows and Columns using for-each loop\n");
        for (Row row: sheet) {
            for(Cell cell: row) {
                imageDownload(cell);
            }
            System.out.println();
        }

        // 3. Or you can use Java 8 forEach loop with lambda
//        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
//        sheet.forEach(row -> {
//            row.forEach(cell -> {
////                printCellValue(cell);
//                
////                cell.getStringCellValue();
//                imageDownload(cell);
//                
//            });
//            System.out.println();
//        });
        

        // Closing the workbook
        workbook.close();
	}
	
	static int gid = 0;
	static int price = 0;
	static String bigCateName = "";
	static String detailCateName = "";
	
    private static void imageDownload(Cell cell) {
//    	System.out.println("cell.getColumnIndex()  : " + cell.getColumnIndex() );
//    	System.out.println("cell.getRowIndex()  : " + cell.getRowIndex() );
    	if(cell.getColumnIndex() == 0 && cell.getRowIndex() > 0)
    		gid = (int) Math.round(cell.getNumericCellValue());
    	if(cell.getColumnIndex() == 4 && cell.getRowIndex() > 0)
    		price = (int) cell.getNumericCellValue();
    	if(cell.getColumnIndex() == 2 && cell.getRowIndex() > 0) 
    		bigCateName = cell.getStringCellValue().replace("/", "&");
    	if(cell.getColumnIndex() == 3) 
    		detailCateName = cell.getStringCellValue().replace("/", "&");
    	if(cell.getColumnIndex() > 13 && cell.getRowIndex() > 0) {
    		String imgUrl = cell.getStringCellValue();
    		System.out.println("imgUrl  : " + imgUrl);
    		if(StringUtils.isNotEmpty(imgUrl)) {
				URL url;
				try {
					url = new URL(imgUrl);
					String fileName = imgUrl.substring( imgUrl.lastIndexOf('/')+1, imgUrl.length() ); // 이미지 파일명 추출
					String ext = imgUrl.substring( imgUrl.lastIndexOf('.')+1, imgUrl.length() ).split("&")[0];  // 이미지 확장자 추출
					fileName = detailCateName.toUpperCase().substring(0, 2) + "-" + gid + "-" + price + "-" + fileName.substring(0, fileName.indexOf("."));
					BufferedImage img = ImageIO.read(url);
					String targetDir = DEFAULT_PATH + bigCateName;
					File checkDir = new File(targetDir);
					if (!checkDir.exists()) {
						try {
							checkDir.mkdirs(); // 폴더 생성합니다.
							System.out.println("폴더가 생성되었습니다.");
						} catch (Exception e) {
							e.getStackTrace();
						}
					}
					File checkFile = new File(targetDir+"/"+fileName+"."+ext);
					if(!checkFile.exists()) {
						ImageIO.write(img, ext, new File(targetDir+"/"+fileName+"."+ext));
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
	}
	
	public static void main(String[] args) throws Exception {
		imageDownloadByReadExcel();
		
	}
	
}
