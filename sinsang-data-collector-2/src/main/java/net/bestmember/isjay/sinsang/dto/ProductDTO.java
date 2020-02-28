package net.bestmember.isjay.sinsang.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
	public ProductDTO() {
	}
	private int gid;
	private String title;
	private int storeId;
	private int styleId;
	private int likes;
	private int price;
	private int catId;
	private int catGenderId;
	private int catItemId;
	private String bigCategoryName;
	private String detailCategoryName;
	private String tel;
	private String color;
	private String size;
	private String mixtureRate;
	private String intro;
	private String madeInCountry;
	private int cannotBuySingle;
	private Date createDate;
	private Date updateDate;
}