package net.bestmember.isjung.rentalfree.dto;

import java.util.List;

public class ProductDto {
	private Long prod_seq;
	private String address1;
	private String address2;
	private String address3;
	private String exclusive_area;
	private String common_area;
	private String m_exclusive_area;
	private String m_common_area;
	private String unusual;
	private String move_date;
	private String status;
	private String prod_type;
	private int sale_price;
	private int deposit;
	private int month_price;
	private String mntnc_includ_yn;
	private int mntnc_fee;
	private String mntnc_content;
	private String options;
	private String prod_content;
	private String service_yn;
	private List<ContractDto> contractDtoList; 
	private List<ProductHistoryDto> productHistoryDto;
	private int total_floor_cnt;
	private int floor_cnt;
	private int parking_cnt;
	private List<AttachFileDto> attachFileDtoList; 
	private String prod_title;
	
	public Long getProd_seq() {
		return prod_seq;
	}
	public void setProd_seq(Long prod_seq) {
		this.prod_seq = prod_seq;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getAddress3() {
		return address3;
	}
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	public String getExclusive_area() {
		return exclusive_area;
	}
	public void setExclusive_area(String exclusive_area) {
		this.exclusive_area = exclusive_area;
	}
	public String getCommon_area() {
		return common_area;
	}
	public void setCommon_area(String common_area) {
		this.common_area = common_area;
	}
	public String getUnusual() {
		return unusual;
	}
	public void setUnusual(String unusual) {
		this.unusual = unusual;
	}
	public String getMove_date() {
		return move_date;
	}
	public void setMove_date(String move_date) {
		this.move_date = move_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProd_type() {
		return prod_type;
	}
	public void setProd_type(String prod_type) {
		this.prod_type = prod_type;
	}
	public int getSale_price() {
		return sale_price;
	}
	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getMonth_price() {
		return month_price;
	}
	public void setMonth_price(int month_price) {
		this.month_price = month_price;
	}
	public String getMntnc_includ_yn() {
		return mntnc_includ_yn;
	}
	public void setMntnc_includ_yn(String mntnc_includ_yn) {
		this.mntnc_includ_yn = mntnc_includ_yn;
	}
	public int getMntnc_fee() {
		return mntnc_fee;
	}
	public void setMntnc_fee(int mntnc_fee) {
		this.mntnc_fee = mntnc_fee;
	}
	public String getMntnc_content() {
		return mntnc_content;
	}
	public void setMntnc_content(String mntnc_content) {
		this.mntnc_content = mntnc_content;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getProd_content() {
		return prod_content;
	}
	public void setProd_content(String prod_content) {
		this.prod_content = prod_content;
	}
	public String getService_yn() {
		return service_yn;
	}
	public void setService_yn(String service_yn) {
		this.service_yn = service_yn;
	}
	public List<ContractDto> getContractDtoList() {
		return contractDtoList;
	}
	public void setContractDtoList(List<ContractDto> contractDtoList) {
		this.contractDtoList = contractDtoList;
	}
	public List<ProductHistoryDto> getProductHistoryDto() {
		return productHistoryDto;
	}
	public void setProductHistoryDto(List<ProductHistoryDto> productHistoryDto) {
		this.productHistoryDto = productHistoryDto;
	}
	public int getTotal_floor_cnt() {
		return total_floor_cnt;
	}
	public void setTotal_floor_cnt(int total_floor_cnt) {
		this.total_floor_cnt = total_floor_cnt;
	}
	public int getFloor_cnt() {
		return floor_cnt;
	}
	public void setFloor_cnt(int floor_cnt) {
		this.floor_cnt = floor_cnt;
	}
	public int getParking_cnt() {
		return parking_cnt;
	}
	public void setParking_cnt(int parking_cnt) {
		this.parking_cnt = parking_cnt;
	}
	public String getProd_title() {
		return prod_title;
	}
	public void setProd_title(String prod_title) {
		this.prod_title = prod_title;
	}
	public List<AttachFileDto> getAttachFileDtoList() {
		return attachFileDtoList;
	}
	public void setAttachFileDtoList(List<AttachFileDto> attachFileDtoList) {
		this.attachFileDtoList = attachFileDtoList;
	}
	public String getM_exclusive_area() {
		return m_exclusive_area;
	}
	public void setM_exclusive_area(String m_exclusive_area) {
		this.m_exclusive_area = m_exclusive_area;
	}
	public String getM_common_area() {
		return m_common_area;
	}
	public void setM_common_area(String m_common_area) {
		this.m_common_area = m_common_area;
	}
	
}
