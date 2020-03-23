package com.mlog.comm.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;

import com.mlog.comm.type.CodeResult;

public class HmsHttpClient {

    private static final Logger log = LoggerFactory.getLogger(HmsHttpClient.class);
    
    @Value("${comm.api.default.timeout}")
    public static int DEFAULT_TIME_OUT;    // 디폴트 타임아웃 시간 (개발: 30초/ 상용: 15초)
    
    @Value("${comm.api.s2i.timeout}")
    public static int S2i_TIME_OUT;    // 디폴트 타임아웃 시간 (개발: 10초/ 상용: 5초)

    /**
     * HTTP GET 통신
     *
     * @param url
     * @return
     */
    public static CodeResult<String> httpGet(String url) {
        return httpGet(url, new HashMap<String, String>(), DEFAULT_TIME_OUT);
    }

    public static CodeResult<String> httpGet(String url, int timeout) {
        return httpGet(url, new HashMap<String, String>(), timeout);
    }

    public static CodeResult<String> httpGet(String url, Map<String, String> header) {
        return httpGet(url, header, DEFAULT_TIME_OUT);
    }

    public static CodeResult<String> httpGet(String url, String param) {
        return httpGet(url, param, DEFAULT_TIME_OUT);
    }

    public static CodeResult<String> httpGet(String url, String param, String ecode) {
        try {
            param = URLEncoder.encode(param, ecode);
        } catch (UnsupportedEncodingException e) {
        }
        return httpGet(url + param, DEFAULT_TIME_OUT);
    }

    public static CodeResult<String> httpGet(String url, String param, int time) {
        try {
            param = URLEncoder.encode(param, "EUC-KR");
        } catch (UnsupportedEncodingException e) {
        }
        return httpGet(url + param, time);
    }

    public static CodeResult<String> httpGet(String preUrl, String param, String tailUrl, int time) {
        return httpGet(preUrl + param + tailUrl, time);
    }

    public static CodeResult<String> httpGet(String url, Map<String, String> header, int timeout) {

        RequestConfig requestConfig = RequestConfig.custom().setExpectContinueEnabled(false).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

        return httpGet(httpclient, url, header);
    }

    public static CodeResult<String> httpGet(HttpClient httpclient, String url, Map<String, String> header) {
        url = url.replaceAll(" ", "");
        log.debug("********** 요청 URL : " + url);
        CodeResult<String> result = new CodeResult<String>();
        HttpGet request = new HttpGet(url);
        request.addHeader("return-type", MediaType.APPLICATION_JSON_VALUE);
        request.addHeader("Accept", MediaType.APPLICATION_JSON_VALUE);

        for (String key : header.keySet()) {
            request.addHeader(key, header.get(key));
        }

        try {
            // 통신 시작시간
            result.setsDate(System.currentTimeMillis());
            result.setCallUrl(url);

            // 통신 처리
            HttpResponse httpResponse = httpclient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            // 200일경우 통신 성공
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (entity != null) {
                String resultRes = EntityUtils.toString(entity, "utf-8").trim();
                result.setCode(String.valueOf(statusCode));
                result.setData(resultRes);
                log.debug("###########  응답 Size(byte) : " + FileIoUtils.objectSize(resultRes));

            }

        } catch (ClientProtocolException e) {
            result.setCode("503");
        } catch (SocketTimeoutException e) {
            result.setCode("408");
        } catch (IOException e) {
            result.setCode("503");
        } finally {
            // 통신 종료시간
            result.seteDate(System.currentTimeMillis());
        }

        return result;
    }


    /**
     * HTTP POST 통신
     *
     * @param url
     * @param params
     * @return
     */
    public static CodeResult<String> httpPost(String url, List<NameValuePair> params) {
        return httpPost(url, params, DEFAULT_TIME_OUT);
    }

    public static CodeResult<String> httpPost(String url, List<NameValuePair> params, int timeout) {
        url = url.replaceAll(" ", "");
        CodeResult<String> result = new CodeResult<String>();

        RequestConfig requestConfig = RequestConfig.custom().setExpectContinueEnabled(false).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

        HttpPost httpPost = new HttpPost(url);
        //httpPost.addHeader("Cookie", "testcode=401");
        log.debug("********** 요청 URL : " + url + params.toString());
        try {
            // 통신 시작시간
            result.setsDate(System.currentTimeMillis());
            result.setCallUrl(url + params.toString());

            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();

            // 200일경우 통신 성공
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (entity != null) {
                String resultRes = EntityUtils.toString(entity).trim();
                result.setCode(String.valueOf(statusCode));
                result.setData(resultRes);

                log.debug("###########  응답 Size(byte) : " + FileIoUtils.objectSize(resultRes));
            }

        } catch (ClientProtocolException e) {
            result.setCode("503");
        } catch (SocketTimeoutException e) {
            result.setCode("408");
        } catch (IOException e) {
            result.setCode("503");
        } finally {
            // 통신 종료시간
            result.seteDate(System.currentTimeMillis());
        }

        return result;
    }

    public static CodeResult<String> httpPost(String url, List<NameValuePair> params, String mediaType) {
        return httpPost(url, params, DEFAULT_TIME_OUT, mediaType);
    }

    public static CodeResult<String> httpPost(String url, List<NameValuePair> params, int timeout, String mediaType) {
        url = url.replaceAll(" ", "");
        CodeResult<String> result = new CodeResult<String>();

        RequestConfig requestConfig = RequestConfig.custom().setExpectContinueEnabled(false).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

        HttpPost httpPost = new HttpPost(url);
        if (!mediaType.isEmpty()) {
            httpPost.addHeader("return-type", mediaType);
            httpPost.addHeader("Accept", mediaType);
        }
        log.debug("********** 요청 URL : " + url + params.toString());
        try {
            // 통신 시작시간
            result.setsDate(System.currentTimeMillis());
            result.setCallUrl(url + params.toString());

            httpPost.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpclient.execute(httpPost);
            HttpEntity entity = httpResponse.getEntity();

            // 200일경우 통신 성공
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (entity != null) {
                String resultRes = EntityUtils.toString(entity).trim();
                result.setCode(String.valueOf(statusCode));
                result.setData(resultRes);

                log.debug("###########  응답 Size(byte) : " + FileIoUtils.objectSize(resultRes));
            }

        } catch (ClientProtocolException e) {
            result.setCode("503");
        } catch (SocketTimeoutException e) {
            result.setCode("408");
        } catch (IOException e) {
            result.setCode("503");
        } finally {
            // 통신 종료시간
            result.seteDate(System.currentTimeMillis());
        }

        return result;
    }

    public static CodeResult<String> httpPut(String url, List<NameValuePair> params, String mediaType) {
        return httpPost(url, params, DEFAULT_TIME_OUT, mediaType);
    }

    public static CodeResult<String> httpPut(String url, List<NameValuePair> params, int timeout, String mediaType) {
        url = url.replaceAll(" ", "");
        CodeResult<String> result = new CodeResult<String>();

        RequestConfig requestConfig = RequestConfig.custom().setExpectContinueEnabled(false).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
        HttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();

        HttpPut httpPut = new HttpPut(url);
        if (!mediaType.isEmpty()) {
            httpPut.addHeader("return-type", mediaType);
            httpPut.addHeader("Accept", mediaType);
        }
        log.debug("********** 요청 URL : " + url + params.toString());
        try {
            // 통신 시작시간
            result.setsDate(System.currentTimeMillis());
            result.setCallUrl(url + params.toString());

            httpPut.setEntity(new UrlEncodedFormEntity(params));
            HttpResponse httpResponse = httpclient.execute(httpPut);
            HttpEntity entity = httpResponse.getEntity();

            // 200일경우 통신 성공
            int statusCode = httpResponse.getStatusLine().getStatusCode();

            if (entity != null) {
                String resultRes = EntityUtils.toString(entity).trim();
                result.setCode(String.valueOf(statusCode));
                result.setData(resultRes);

                log.debug("###########  응답 Size(byte) : " + FileIoUtils.objectSize(resultRes));
            }

        } catch (ClientProtocolException e) {
            result.setCode("503");
        } catch (SocketTimeoutException e) {
            result.setCode("408");
        } catch (IOException e) {
            result.setCode("503");
        } finally {
            // 통신 종료시간
            result.seteDate(System.currentTimeMillis());
        }

        return result;
    }
}
