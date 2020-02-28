package net.bestmember.isjay.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.bestmember.isjay.common.vo.CodeResult;


/**
 * API 연계를 위한 공통 Http Client
 * 
 * @since 2018. 4. 27.
 * @author isjung
 */
public class HttpClient {

	public static int DEFAULT_TIME_OUT = 30 * 1000;
	private static final Logger logger = LoggerFactory.getLogger(HttpClient.class);

	/**
	 * HTTP GET 통신
	 * 
	 * @since 2018. 4. 27.
	 * @author isjung
	 * @param url 연동 시스템 URL
	 * @return CodeResult call result
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

	public static CodeResult<String> httpGet(String url, String param, boolean isParamEncoding) {
		return isParamEncoding ? httpGet(url, param, DEFAULT_TIME_OUT) : httpGet(String.format("%s?%s", url, param), DEFAULT_TIME_OUT);
	}

	public static CodeResult<String> httpGet(String url, Map<String, String> paramMap, boolean isParamEncoding) {
		return httpGet(url, ParamUtils.mapToQuerystring(paramMap), isParamEncoding);
	}

	public static CodeResult<String> httpGet(String url, String param, String ecode) {
		try {
			param = URLEncoder.encode(param, ecode);
		} catch (UnsupportedEncodingException e) {
		}
		return httpGet(String.format("%s?%s", url, param), DEFAULT_TIME_OUT);
	}

	public static CodeResult<String> httpGet(String url, String param, int timeout) {
		try {
			param = URLEncoder.encode(param, "EUC-KR");
		} catch (UnsupportedEncodingException e) {
		}
		return httpGet(String.format("%s?%s", url, param), timeout);
	}

	public static CodeResult<String> httpGet(String url, Map<String, String> header, int timeout) {
		CloseableHttpClient httpclient = getDefaultHttpClient(timeout);
		return httpGet(httpclient, url, header);
	}
	
	public static CodeResult<String> httpGet(String url, Map<String, String> header, int timeout, BasicCookieStore cookieStore) {
		CloseableHttpClient httpclient = getCookieHttpClient(timeout, cookieStore);
		return httpGet(httpclient, url, header);
	}

	public static CodeResult<String> httpGet(CloseableHttpClient httpclient, String url, Map<String, String> header) {
		// logger.debug("********** 요청 URL : " + url);
		// try { decodeUrl = URLDecoder.decode(url, "EUC-KR"); } catch (Exception e) {}

		CodeResult<String> result = new CodeResult<String>();
		HttpGet request = new HttpGet(url);
		request.addHeader("return-type", MediaType.APPLICATION_JSON_VALUE);
		request.addHeader("Connection", "close");

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
				// logger.debug("########### 응답 Size(byte) : " + FileIoUtils.objectSize(resultRes));
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
	 * @since 2018. 4. 27.
	 * @author isjung
	 * @param url 연동 시스템 URL
	 * @return CodeResult call result
	 */

	public static CodeResult<String> httpPost(String url, Map<String, Object> params) {
		return httpPost(url, params, null, DEFAULT_TIME_OUT);
	}

	public static CodeResult<String> httpPost(String url, Map<String, Object> params, String mediaType) {
		return httpPost(url, params, mediaType, DEFAULT_TIME_OUT);
	}

	private static CodeResult<String> httpPost(String url, Map<String, Object> params, String mediaType, int timeout) {
		HttpPost httpPost = new HttpPost(url);

		CodeResult<String> result = new CodeResult<String>();
		CloseableHttpClient httpclient = getDefaultHttpClient(timeout);
		if (mediaType != null && !mediaType.isEmpty()) {
			httpPost.addHeader("return-type", mediaType);
			httpPost.addHeader("Accept", mediaType);
		}
		try {
			// 통신 시작시간
			result.setsDate(System.currentTimeMillis());
			result.setCallUrl(url);

			if (params != null && !params.isEmpty()) {
				String json = new ObjectMapper().writeValueAsString(params);
				// logger.debug("********** 요청 URL : {}\n▶{}", url, json);

				StringEntity postingString = new StringEntity(json);
				httpPost.setEntity(postingString);
				httpPost.addHeader("Content-type", MediaType.APPLICATION_JSON_VALUE);
			}

			result.setsDate(System.currentTimeMillis());
			HttpResponse httpResponse = httpclient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();

			// 200일경우 통신 성공
			int statusCode = httpResponse.getStatusLine().getStatusCode();

			if (entity != null) {
				String resultRes = EntityUtils.toString(entity).trim();
				result.setCode(String.valueOf(statusCode));
				result.setData(resultRes);

				// logger.debug("########### 응답 Size(byte) : {}", FileIoUtils.objectSize(resultRes));
			}

		} catch (ClientProtocolException e) {
			result.setCode("503");
			result.setMessage(e.getCause().getMessage());
		} catch (SocketTimeoutException e) {
			result.setCode("408");
			result.setMessage(e.getCause().getMessage());
		} catch (IOException e) {
			result.setCode("503");
			result.setMessage(e.getCause().getMessage());
		} finally {
			// 통신 종료시간
			result.seteDate(System.currentTimeMillis());
		}
		return result;
	}

	/**
	 * default http client
	 * 
	 * @since 2018. 4. 27.
	 * @author isjung
	 * @param timeout connect timeout
	 * @return CloseableHttpClient default http client
	 */
	private static CloseableHttpClient getDefaultHttpClient(int timeout) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();

		CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
		return httpclient;
	}
	
	private static CloseableHttpClient getCookieHttpClient(int timeout, BasicCookieStore cookieStore) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();

		CloseableHttpClient httpclient = HttpClientBuilder.create().setDefaultRequestConfig(config).setDefaultCookieStore(cookieStore).build();
		return httpclient;
	}

	private static CloseableHttpClient getManagedHttpClient(int timeout) {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(300);
		cm.setDefaultMaxPerRoute(50);
		return HttpClients.custom().setDefaultRequestConfig(config).setConnectionManager(cm).build();
	}
	
	
	public static BasicCookieStore parseRawCookie(String rawCookie, String path, String domain, BasicCookieStore cookieStore) throws Exception {
	    String[] rawCookieParams = rawCookie.split(";");
	    String[] rawCookieNameAndValue = null;
	    String cookieName = null;
	    String cookieValue = null;
	    for(String rawCookieParam : rawCookieParams) {
	    	rawCookieNameAndValue = rawCookieParam.split("=");
		    cookieName = rawCookieNameAndValue[0].trim();
		    cookieValue = rawCookieNameAndValue[1].trim();
		    
		    BasicClientCookie cookie = new BasicClientCookie(cookieName, cookieValue);
		    cookie.setPath(path);
		    cookie.setDomain(domain);
		    cookie.setSecure(false);
		    cookie.setExpiryDate(null);
		    
		    cookieStore.addCookie(cookie);
	    }
	    return cookieStore;
	}
}