package net.bestmember.isjay.sinsang.controller;

import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.bestmember.isjay.common.util.StringUtils;
import net.bestmember.isjay.sinsang.dto.ProductDTO;
import net.bestmember.isjay.sinsang.dto.ProductImageDTO;
import net.bestmember.isjay.sinsang.mapper.ProductImageMapper;
import net.bestmember.isjay.sinsang.mapper.ProductMapper;

@RestController
public class SinsangController {
	
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private ProductImageMapper productImageMapper;
	
    @RequestMapping("/collect")
    public List<ProductDTO> collect() throws Exception {
    	
    	ProductDTO productDTO = new ProductDTO();
    	ProductImageDTO productImageDTO = new ProductImageDTO();

    	SinsangDataReader.setDetailApiCookie();
    	// 0. 카테고리 목록 가져오기
    	List<String> getCategoryURLList = SinsangDataReader.getCategoryURLList();
    	// 1. 카테고리 Loop
    	for(String getCategoryURL : getCategoryURLList) {
        	// 2. 카테고리 별 리스트 API 호출
    		String getSinsangList = SinsangDataReader.getSinsangList(getCategoryURL, SinsangDataReader.getListApiHeader());
    		
    		JsonElement sinsangList = JsonParser.parseString(getSinsangList);
    		JsonElement sinsangListData = sinsangList.getAsJsonObject().get("data");
    		
    		// 3. 카테고리 별 리스트 Loop
    		for( JsonElement sinsang : sinsangListData.getAsJsonObject().get("list").getAsJsonArray()) {
    			Thread.sleep(500);
    			JsonObject row = sinsang.getAsJsonObject();
    			
    			ProductDTO checkproduct = productMapper.findById(row.get("id").getAsInt());
    			
    			if(checkproduct != null) {
    				continue;
    			}
    			
    			productDTO.setGid(row.get("id").getAsInt());
    			productDTO.setTitle(row.get("title").getAsString());
    			productDTO.setStoreId(row.get("storeId").getAsInt());
    			productDTO.setStyleId(row.get("styleId").getAsInt());
    			productDTO.setLikes(row.get("likes").getAsInt());
    			productDTO.setPrice(row.get("price").getAsInt());
    			productDTO.setCatId(row.get("catId").getAsInt());
    			productDTO.setCatGenderId(row.get("catGenderId").getAsInt());
    			productDTO.setCatItemId(row.get("catItemId").getAsInt());
    			
    			// 4. 콘텐츠 상세 API 호출 ( 리스트의 콘텐츠 기본정보 + GID 포함)
    			String getSinsangDetail = null;
    			if( getCategoryURL.indexOf("storeId") > 0 ) {
        			getSinsangDetail = SinsangDataReader.getSinsagChoiceDetail(productDTO.getGid(), SinsangDataReader.getDetailApiHeader());
    			}else {
        			getSinsangDetail = SinsangDataReader.getSinsangDetail(productDTO.getGid(), SinsangDataReader.getDetailApiHeader());
    			}
    			
    			if(StringUtils.isEmpty(getSinsangDetail)) {
    				continue;
    			}
    			
    			// 4.1. JSON Parse 
        		JsonElement sinsangDetail = JsonParser.parseString(getSinsangDetail);
        		JsonElement sinsangDetailData = sinsangDetail.getAsJsonObject().get("gdata");
        		
        		JsonObject detailRow = sinsangDetailData.getAsJsonObject();
        		productDTO.setBigCategoryName(detailRow.get("bigCategoryName") == null ? detailRow.get("categoryName").getAsString() : detailRow.get("bigCategoryName").getAsString());
        		productDTO.setDetailCategoryName(detailRow.get("detailCategoryName") == null ? detailRow.get("subCategoryName").getAsString() : detailRow.get("detailCategoryName").getAsString());
        		productDTO.setTel(detailRow.get("tel") == null ? detailRow.get("originStoreTel").getAsString() : detailRow.get("tel").getAsString());
        		productDTO.setColor(detailRow.get("color") == null ? "" : detailRow.get("color").getAsString());
        		productDTO.setSize(detailRow.get("size") == null ? "" : detailRow.get("size").getAsString());
        		productDTO.setMixtureRate(detailRow.get("mixtureRate") == null ? "" : detailRow.get("mixtureRate").getAsString());
        		productDTO.setIntro(detailRow.get("intro") == null ? "" : detailRow.get("intro").getAsString());
        		productDTO.setMadeInCountry(detailRow.get("madeInCountry") == null ? "" : detailRow.get("madeInCountry").getAsString());
//        		productDTO.setCannotBuySingle(detailRow.get("cannotBuySingle") == null ? 0 : detailRow.get("cannotBuySingle").getAsInt());
        		
        		// 5. 리턴된 상세 데이터 + 리스트의 콘텐츠 기본정보 + GID 로 상품정보 DB insert
        		productMapper.insert(productDTO);
        		
        		// 5.1. 이미지정보 loop
        		for( JsonElement image : detailRow.get("goodsImages").getAsJsonArray()) {
        			JsonObject imageRow = image.getAsJsonObject();
        			productImageDTO.setGid(productDTO.getGid());
        			productImageDTO.setImageUrl(imageRow.get("imageUrl").getAsString());
        			productImageDTO.setFilename(imageRow.get("filename").getAsString());
        			
        			// 6. 상품 이미지 DB insert
        			productImageMapper.insert(productImageDTO);
        		}
    		}
    			
    		
    	}
    	
    	return null;
    }
}
