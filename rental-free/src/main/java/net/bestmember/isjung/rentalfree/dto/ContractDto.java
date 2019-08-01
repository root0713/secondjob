package net.bestmember.isjung.rentalfree.dto;

public class ContractDto {
	private Long cntrt_seq;
	private String contrt_type;
	private String contrt_name;
	private String contrt_tel;
	private Long prod_seq;
	public Long getCntrt_seq() {
		return cntrt_seq;
	}
	public void setCntrt_seq(Long cntrt_seq) {
		this.cntrt_seq = cntrt_seq;
	}
	public String getContrt_type() {
		return contrt_type;
	}
	public void setContrt_type(String contrt_type) {
		this.contrt_type = contrt_type;
	}
	public String getContrt_name() {
		return contrt_name;
	}
	public void setContrt_name(String contrt_name) {
		this.contrt_name = contrt_name;
	}
	public String getContrt_tel() {
		return contrt_tel;
	}
	public void setContrt_tel(String contrt_tel) {
		this.contrt_tel = contrt_tel;
	}
	public Long getProd_seq() {
		return prod_seq;
	}
	public void setProd_seq(Long prod_seq) {
		this.prod_seq = prod_seq;
	}
	
	
}
