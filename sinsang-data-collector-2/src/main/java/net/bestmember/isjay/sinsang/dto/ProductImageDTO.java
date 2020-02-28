package net.bestmember.isjay.sinsang.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductImageDTO {
	public ProductImageDTO() {
	}
	private int fid;
	private int gid;
	private String imageUrl;
	private String filename;
}
