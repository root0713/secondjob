package net.bestmember.isjung.rentalfree.dto;

import java.util.Date;
import java.util.List;

public class RequestDto {
	private Long req_seq;
	private String req_date;
	private String req_name;
	private String req_tel;
	private String req_content;
	private String proc_yn;
	private List<RequestHistoryDto> requestHistoryDtoList; 
	public Long getReq_seq() {
		return req_seq;
	}
	public void setReq_seq(Long req_seq) {
		this.req_seq = req_seq;
	}
	public String getReq_date() {
		return req_date;
	}
	public void setReq_date(String req_date) {
		this.req_date = req_date;
	}
	public String getReq_name() {
		return req_name;
	}
	public void setReq_name(String req_name) {
		this.req_name = req_name;
	}
	public String getReq_tel() {
		return req_tel;
	}
	public void setReq_tel(String req_tel) {
		this.req_tel = req_tel;
	}
	public String getReq_content() {
		return req_content;
	}
	public void setReq_content(String req_content) {
		this.req_content = req_content;
	}
	public String getProc_yn() {
		return proc_yn;
	}
	public void setProc_yn(String proc_yn) {
		this.proc_yn = proc_yn;
	}
	public List<RequestHistoryDto> getRequestHistoryDtoList() {
		return requestHistoryDtoList;
	}
	public void setRequestHistoryDtoList(List<RequestHistoryDto> requestHistoryDtoList) {
		this.requestHistoryDtoList = requestHistoryDtoList;
	}
}
