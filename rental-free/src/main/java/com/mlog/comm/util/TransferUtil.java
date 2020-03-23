package com.mlog.comm.util;

import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.springframework.util.StringUtils;

public class TransferUtil {
	
	/**
	 * HttpClient를 이용하여 웹주소를 호출한다.
	 * @param url	예)http://123.123.123.2:80
	 * @param timeout	예)2	
	 * @param acceptHeader	예)application/xml
	 * @param Method	예)POST
	 * @param encoding	예)UTF-8
	 * @param body	POST,PUT일 경우 BODY영역을 이용해서 데이터를 전달 할 수 있다. 예)<aaaa><bbb>BODY</bbb></aaa>
	 * @param sslYn	HTTPS통신을 할것인지?
	 * @return
	 * @throws Exception
	 */
	public static String callHttpClient(String url, int timeout, String acceptHeader, String Method, String encoding, String body, String sslYn) throws Exception{
		String result = "";
		HttpRequestBase method_type = null;
		
		HttpClient httpClient = null;
		HttpResponse response = null;
		HttpEntity entity = null;
		InputStream is = null;
		StringBuffer sb = new StringBuffer();
		byte[] b = new byte[4096];
		
		try {
			
			if("POST".equals(Method.toUpperCase())){
				method_type = new HttpPost(url);
				((HttpPost)method_type).setEntity(new StringEntity(body,encoding));
			}else if("PUT".equals(Method.toUpperCase())){
				method_type = new HttpPut(url);
				((HttpPut)method_type).setEntity(new StringEntity(body,encoding));
			}else if("DELETE".equals(Method.toUpperCase())){
				method_type = new HttpDelete(url);
			}else{
				method_type = new HttpGet(url);
			}
			
			if(!StringUtils.isEmpty(acceptHeader)){
				method_type.setHeader("Accept", acceptHeader);
			}
			
			httpClient = new DefaultHttpClient();
			
			if("Y".equals(sslYn)){
				TrustManager easyTrustManager = new X509TrustManager() {

					public X509Certificate[] getAcceptedIssuers() {
						// no-op
						return null;
					}

					public void checkServerTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
					}

					public void checkClientTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
					}
				};
				
				SSLContext sslcontext = SSLContext.getInstance("TLS");
				sslcontext
						.init(null, new TrustManager[] { easyTrustManager }, null);

				SSLSocketFactory socketFactory = new SSLSocketFactory(sslcontext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
				
				Scheme sch = new Scheme("https", 443, socketFactory);
				httpClient.getConnectionManager().getSchemeRegistry().register(sch);

			}
			
			HttpParams params = httpClient.getParams();
            params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            HttpConnectionParams.setConnectionTimeout(params, timeout);
            HttpConnectionParams.setSoTimeout(params, timeout);
            
			response = httpClient.execute(method_type);
			entity = response.getEntity();
			is = entity.getContent();
			sb = new StringBuffer();
			
			for(int n; (n =is.read(b)) != -1;){
				sb.append(new String(b, 0, n));
			}
			result = sb.toString();
			
		} catch (ClientProtocolException e) {
			// e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// e.printStackTrace();
			throw e;
		}finally{
			httpClient.getConnectionManager().shutdown();
		}
		
		return result;
	}

}
