package net.bestmember.isjung.rentalfree.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class AttachFileDto {
	private Long att_seq;
	private Long prod_seq;
	private MultipartFile att_file;
	private String att_file_display;
	
	public Long getAtt_seq() {
		return att_seq;
	}
	public void setAtt_seq(Long att_seq) {
		this.att_seq = att_seq;
	}
	public Long getProd_seq() {
		return prod_seq;
	}
	public void setProd_seq(Long prod_seq) {
		this.prod_seq = prod_seq;
	}
	public MultipartFile getAtt_file() {
		return att_file;
	}
	public void setAtt_file(MultipartFile att_file) {
		this.att_file = att_file;
	}
	public String getAtt_file_display() {
		return att_file_display;
	}
	public void setAtt_file_display(String att_file_display) {
		this.att_file_display = att_file_display;
	}
	
}
