package net.bestmember.isjung.rentalfree.dto;

public class RequestHistoryDto {
	private Long hist_seq;
	private String hist_name;
	private String hist_content;
	private String hist_date;
	private Long req_seq;
	public Long getHist_seq() {
		return hist_seq;
	}
	public void setHist_seq(Long hist_seq) {
		this.hist_seq = hist_seq;
	}
	public String getHist_name() {
		return hist_name;
	}
	public void setHist_name(String hist_name) {
		this.hist_name = hist_name;
	}
	public String getHist_content() {
		return hist_content;
	}
	public void setHist_content(String hist_content) {
		this.hist_content = hist_content;
	}
	public String getHist_date() {
		return hist_date;
	}
	public void setHist_date(String hist_date) {
		this.hist_date = hist_date;
	}
	public Long getReq_seq() {
		return req_seq;
	}
	public void setReq_seq(Long req_seq) {
		this.req_seq = req_seq;
	}
	
}
