package net.bestmember.isjung.rentalfree.dto;

import java.util.Date;

public class ProductHistoryDto {
	private Long prod_hist_seq;
	private String hist_name;
	private String hist_date;
	private String hist_content;
	private Long prod_seq;
	public Long getProd_hist_seq() {
		return prod_hist_seq;
	}
	public void setProd_hist_seq(Long prod_hist_seq) {
		this.prod_hist_seq = prod_hist_seq;
	}
	public String getHist_name() {
		return hist_name;
	}
	public void setHist_name(String hist_name) {
		this.hist_name = hist_name;
	}
	public String getHist_date() {
		return hist_date;
	}
	public void setHist_date(String hist_date) {
		this.hist_date = hist_date;
	}
	public String getHist_content() {
		return hist_content;
	}
	public void setHist_content(String hist_content) {
		this.hist_content = hist_content;
	}
	public Long getProd_seq() {
		return prod_seq;
	}
	public void setProd_seq(Long prod_seq) {
		this.prod_seq = prod_seq;
	}
	
}
