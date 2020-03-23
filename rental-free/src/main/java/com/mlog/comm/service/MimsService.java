package com.mlog.comm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mlog.comm.adapter.ApiAdaptor;
import com.mlog.comm.type.CodeResult;
import com.mlog.comm.vo.ConfigurationInfoVO;
import com.mlog.comm.vo.ConfigurationVO;

/**
 * MIMS 서비스 연동 Class
 */
@Service
public class MimsService {

	@Value("${mims.server}")
    private String MIMS_SERVER;
	@Value("${comm.mobile.guest.saId}")
	private  String GUEST_ID;
	@Value("${comm.mobile.guest.saMac}")
	private  String GUEST_MAC;
	@Value("${hms.config.key}")
	private String configKey;
	@Value("${hms.config.svc.type}")
	private String svcType;
	
    private String CP_ID;
    private String ACCESS_KEY;
    private Gson gson = new GsonBuilder().create();

    public MimsService() {
        CP_ID = "cpid";
        ACCESS_KEY = "akey";
    }

    private String guestId() {
        return "sa_id=" + GUEST_ID + "&stb_mac=" + GUEST_MAC;
    }

    /**
     * 미리보기 설정정보 조회
     * - MIMS-OA 연동규격서  4.36. Configuration 설정정보 조회
     *
     * @param deviceInfoVO : 단말 정보
     * @param codeId       : 설정정보 ID
     * @return 미리보기 설정정보
     * @Method getConfigSetInfo
     */
    public ConfigurationVO getConfigSetInfo() {
        String url = MIMS_SERVER + "/hdtv/v1/setting";

        StringBuilder params = new StringBuilder();
        params.append("?access_key=").append(ACCESS_KEY);
        params.append("&cp_id=").append(CP_ID);
        params.append("&").append(this.guestId());
        params.append("&code_id=").append(configKey);
        params.append("&svc_type=").append(svcType);

        CodeResult<String> apiResult = ApiAdaptor.mimsStringResult(url, params.toString(), null);
        JsonObject object = gson.fromJson(apiResult.getData(), JsonObject.class);

        return gson.fromJson(object.get("result"), ConfigurationVO.class);
    }
    
    public String getConfigSetInfoStringValue() {
        String url = MIMS_SERVER + "/hdtv/v1/setting";

        StringBuilder params = new StringBuilder();
        params.append("?access_key=").append(ACCESS_KEY);
        params.append("&cp_id=").append(CP_ID);
        params.append("&").append(this.guestId());
        params.append("&code_id=").append(configKey);
        params.append("&svc_type=").append(svcType);

        CodeResult<String> apiResult = ApiAdaptor.mimsStringResult(url, params.toString(), null);

        return apiResult.getData();
    }
    
    public String[] getVipsList() {
    	String[] vipsList = null;
    	ConfigurationVO vo = this.getConfigSetInfo();
    	if (vo != null) {
    		List<ConfigurationInfoVO> infoVoList = vo.getRecordset();
    		if (infoVoList != null && infoVoList.size() > 0) {
    			for (ConfigurationInfoVO infoVo : infoVoList) {
    				if (infoVo != null) {
    					String codeName = infoVo.getCode_name();
    					vipsList = codeName.split("\\Q|\\E");
    				}
    			}
    		}
    	}
    	return vipsList;
    }
}
