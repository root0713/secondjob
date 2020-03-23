package com.mlog.comm.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mlog.comm.type.CertType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceInfoVO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 691389615933899565L;

    private String ctn = "";
    private String ipAddr = "";
    private String macAddr = "";
    private String iccId = "";
    private String model = "";
    private String serialNo = "";
    private String confVer = "";
    private String swVer = "";
    //G : 3G 접속, L : LTE 접속, W : WIFI 접속
    private String netType = "";
    private boolean uplusCtn;

    private String width = "";
    private String height = "";
    private String reqFrom = "";
    private String autoLogin = "N";
    private String userId = "";
    private String userPwd = "";
    private String ctnNo = "";
    private String deviceModel = "";
    private String maVer = "";
    private String tutorial = "";
    private String carrierType = "";    //이통사 구분
    private String appVer = "";
    private String osType = ""; // OS타입 (A: 안드로이드, I: iOS)
    private String devInfo = ""; // 통합 통계용 접속 단말 타입 (PHONE, PAD, PC, TV, STB)
    private String appName = "";
    private String apiSid = "";
    @JsonIgnore
    private CertType certType;        // 인증타입(1: ID/PWD, 2: CTN, 4: ONEID, 6:SNSID)
    private String oneIdKey = "";
    private String oneIdSsoKey = "";
    private String snsCd = "";
    private String snsId = "";
    private String snsIdKey = "";
    private String prePage = "";
    private String curPage = "";
    private String roaming = "";
    private String udid = "";

    private String hdtvGB = "P";
    private String fiveYN = "N";
    private String uplusCtnYn = "Y";

    
    
    public String getCtn() {
		return ctn;
	}

	public void setCtn(String ctn) {
		this.ctn = ctn;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getIccId() {
		return iccId;
	}

	public void setIccId(String iccId) {
		this.iccId = iccId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getConfVer() {
		return confVer;
	}

	public void setConfVer(String confVer) {
		this.confVer = confVer;
	}

	public String getSwVer() {
		return swVer;
	}

	public void setSwVer(String swVer) {
		this.swVer = swVer;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public boolean isUplusCtn() {
		return uplusCtn;
	}

	public void setUplusCtn(boolean uplusCtn) {
		this.uplusCtn = uplusCtn;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getReqFrom() {
		return reqFrom;
	}

	public void setReqFrom(String reqFrom) {
		this.reqFrom = reqFrom;
	}

	public String getAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(String autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getCtnNo() {
		return ctnNo;
	}

	public void setCtnNo(String ctnNo) {
		this.ctnNo = ctnNo;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getMaVer() {
		return maVer;
	}

	public void setMaVer(String maVer) {
		this.maVer = maVer;
	}

	public String getTutorial() {
		return tutorial;
	}

	public void setTutorial(String tutorial) {
		this.tutorial = tutorial;
	}

	public String getCarrierType() {
		return carrierType;
	}

	public void setCarrierType(String carrierType) {
		this.carrierType = carrierType;
	}

	public String getAppVer() {
		return appVer;
	}

	public void setAppVer(String appVer) {
		this.appVer = appVer;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getDevInfo() {
		return devInfo;
	}

	public void setDevInfo(String devInfo) {
		this.devInfo = devInfo;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getApiSid() {
		return apiSid;
	}

	public void setApiSid(String apiSid) {
		this.apiSid = apiSid;
	}

	public CertType getCertType() {
		return certType;
	}

	public void setCertType(CertType certType) {
		this.certType = certType;
	}

	public String getOneIdKey() {
		return oneIdKey;
	}

	public void setOneIdKey(String oneIdKey) {
		this.oneIdKey = oneIdKey;
	}

	public String getOneIdSsoKey() {
		return oneIdSsoKey;
	}

	public void setOneIdSsoKey(String oneIdSsoKey) {
		this.oneIdSsoKey = oneIdSsoKey;
	}

	public String getSnsCd() {
		return snsCd;
	}

	public void setSnsCd(String snsCd) {
		this.snsCd = snsCd;
	}

	public String getSnsId() {
		return snsId;
	}

	public void setSnsId(String snsId) {
		this.snsId = snsId;
	}

	public String getSnsIdKey() {
		return snsIdKey;
	}

	public void setSnsIdKey(String snsIdKey) {
		this.snsIdKey = snsIdKey;
	}

	public String getPrePage() {
		return prePage;
	}

	public void setPrePage(String prePage) {
		this.prePage = prePage;
	}

	public String getCurPage() {
		return curPage;
	}

	public void setCurPage(String curPage) {
		this.curPage = curPage;
	}

	public String getRoaming() {
		return roaming;
	}

	public void setRoaming(String roaming) {
		this.roaming = roaming;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	public String getHdtvGB() {
		return hdtvGB;
	}

	public void setHdtvGB(String hdtvGB) {
		this.hdtvGB = hdtvGB;
	}

	public String getFiveYN() {
		return fiveYN;
	}

	public void setFiveYN(String fiveYN) {
		this.fiveYN = fiveYN;
	}

	public String getUplusCtnYn() {
		return uplusCtnYn;
	}

	public void setUplusCtnYn(String uplusCtnYn) {
		this.uplusCtnYn = uplusCtnYn;
	}

	/**
     * @return the serialversionuid
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{\"ipAddr\":\"");
        builder.append(ipAddr);
        builder.append("\", \"macAddr\":\"");
        builder.append(macAddr);
        builder.append("\", \"swVer\":\"");
        builder.append(swVer);
        builder.append("\", \"netType\":\"");
        builder.append(netType);
        builder.append("\", \"ctnNo\":\"");
        builder.append(ctnNo);
        builder.append("\", \"deviceModel\":\"");
        builder.append(deviceModel);
        builder.append("\", \"carrierType\":\"");
        builder.append(carrierType);
        builder.append("\", \"osType\":\"");
        builder.append(osType);
        builder.append("\", \"hdtvGB\":\"");
        builder.append(hdtvGB);
        builder.append("\", \"fiveYN\":\"");
        builder.append(fiveYN);
        builder.append("\", \"roaming\":\"");
        builder.append(roaming);
        builder.append("\", \"udid\":\"");
        builder.append(udid);
        builder.append("\"}");
        return builder.toString();
    }


}
