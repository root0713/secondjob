package net.bestmember.isjay.common.vo;

import java.io.Serializable;

import org.json.JSONObject;

public class CodeResult<T> implements Serializable {
	private static final long serialVersionUID = 7750706257988260919L;
	public static final String HTTP_SUCCESS = "200";
	public static final String MIMS_SUCCESS = "0000";
	private String code;
	private String message;
	private int totalCnt;
	private long sDate; // 시작시간 ( 로그처리 )
	private long eDate; // 종료시간 ( 로그처리 )
	private String callUrl; // 호출 URL ( 로그처리 )
	private T data;

	public CodeResult() {
	}

	public CodeResult(T data) {
		this.data = data;
	}

	public CodeResult(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public CodeResult(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		if (data == null)
			return false;
		JSONObject jsonData = new JSONObject(data.toString());
		return !jsonData.isNull("data") && HTTP_SUCCESS.equals(String.valueOf(jsonData.getInt("status"))) && jsonData.getJSONArray("data").length() > 0;
	}

	/*public boolean isMimsSuccess() {
		if (data == null)
			return false;
		JSONObject jsonData = new JSONObject(data.toString());
		JSONObject result = jsonData.getJSONObject("result");
		return !jsonData.isNull("result") && MIMS_SUCCESS.equals(result.getString("flag")) && Integer.parseInt(result.getString("total_cnt")) > 0;
	}*/

	/*
	 *  data 제외한 항목 복사 처리
	 */
	public void copyExceptData(CodeResult<?> from) {
		this.setCode(from.getCode());
		this.setMessage(from.getMessage());
		this.setTotalCnt(from.getTotalCnt());
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCodeMsg(String message) {
		if (!message.isEmpty())
			this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public long getsDate() {
		return sDate;
	}

	public long geteDate() {
		return eDate;
	}

	public void setsDate(long sDate) {
		this.sDate = sDate;
	}

	public void seteDate(long eDate) {
		this.eDate = eDate;
	}

	public String getCallUrl() {
		return callUrl;
	}

	public void setCallUrl(String callUrl) {
		this.callUrl = callUrl;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CodeResult [code=");
		builder.append(code);
		builder.append(", message=");
		builder.append(message);
		builder.append(", totalCnt=");
		builder.append(totalCnt);
		if (data != null) {
			builder.append(", data=");
			builder.append(data.toString());
		}
		builder.append("]");
		return builder.toString();
	}
}
