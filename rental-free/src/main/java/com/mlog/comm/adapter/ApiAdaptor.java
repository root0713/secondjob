package com.mlog.comm.adapter;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.springframework.http.MediaType;

import com.mlog.comm.type.CodeResult;
import com.mlog.comm.util.HmsHttpClient;

/**
 * 비디오포털 API 연동 모듈 공통화 Class
 */
public class ApiAdaptor {

    /**
     * MIMS 통신 및 결과 코드를 JSON 형태 String으로 반환
     *
     * @param url     : URL
     * @param param   : API parameter
     * @param tailURL : 통합통계 관련 parameter
     * @return MIMS 통신 결과
     * @Method mimsStringResult
     */
    public static CodeResult<String> mimsStringResult(String url, String param, String tailURL) {
        CodeResult<String> apiResult = HmsHttpClient.httpGet(url, param, tailURL == null ? "" : tailURL , HmsHttpClient.DEFAULT_TIME_OUT);
        apiResult.setCallUrl(url + param);
        return apiResult;
    }
    
    public static CodeResult<String> mimsStringResult(String url, List<NameValuePair> params, String tailURL) {
        CodeResult<String> apiResult = new CodeResult<String>();
        tailURL = StringUtils.defaultString(tailURL, "");
        apiResult = HmsHttpClient.httpPost(url, params, MediaType.APPLICATION_JSON_VALUE);
        return apiResult;
    }

}
