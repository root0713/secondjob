package net.bestmember.isjay.sinsang.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.bestmember.isjay.common.util.StringUtils;
import net.bestmember.isjay.sinsang.dto.ProductDTO;
import net.bestmember.isjay.sinsang.dto.ProductImageDTO;
import net.bestmember.isjay.sinsang.mapper.ProductImageMapper;
import net.bestmember.isjay.sinsang.mapper.ProductMapper;

@Service
public class SinsangService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private ProductMapper productMapper;
    
    @Autowired
    private ProductImageMapper productImageMapper;
    
	public String crawlling(String gender, int size, int priceLow, int priceHigh) throws Exception {
    	int productCnt = 0;
    	int productImageCnt = 0;
    	int maxImageContentCnt = 10;
    	ProductDTO productDTO = new ProductDTO();
    	ProductImageDTO productImageDTO = new ProductImageDTO();

    	SinsangDataReader.setDetailApiCookie();
    	// 0. 카테고리 목록 가져오기
    	List<String> getCategoryURLList = SinsangDataReader.getCategoryURLList(gender, size, priceLow, priceHigh);
    	// 1. 카테고리 Loop
    	for(String getCategoryURL : getCategoryURLList) {
        	// 2. 카테고리 별 리스트 API 호출
    		String getSinsangList = SinsangDataReader.getSinsangList(getCategoryURL, SinsangDataReader.getListApiHeader());
    		
    		JsonElement sinsangList = JsonParser.parseString(getSinsangList);
    		JsonElement sinsangListData = sinsangList.getAsJsonObject().get("data");
    		
    		int checkCnt = 0;
    		
    		// 3. 카테고리 별 리스트 Loop
    		for( JsonElement sinsang : sinsangListData.getAsJsonObject().get("list").getAsJsonArray()) {
    			Thread.sleep(1 * 100);
    			JsonObject row = sinsang.getAsJsonObject();
    			
    			ProductDTO checkproduct = productMapper.findById(row.get("id").getAsInt());
    			
        		logger.debug("productCnt : " + productCnt + " : " + productDTO.getGid() + " : started...");
    			
    			// 5번 이상 컨텐츠가 존재하는 경우는 out loop
    			if(checkCnt >= 5) {
    				break;
    			}
    			
    			// 같은 컨텐츠가 있는 경우는 next loop
    			if(checkproduct != null) {
    				checkCnt++;
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
        		productCnt++;
        		
        		int imageContentCnt = 0;
        		// 5.1. 이미지정보 loop
        		for( JsonElement image : detailRow.get("goodsImages").getAsJsonArray()) {
        			if(imageContentCnt >= maxImageContentCnt) {
        				break;
        			}
        			imageContentCnt++;
        			
        			JsonObject imageRow = image.getAsJsonObject();
        			productImageDTO.setGid(productDTO.getGid());
        			productImageDTO.setImageUrl(imageRow.get("imageUrl").getAsString());
        			productImageDTO.setFilename(imageRow.get("filename").getAsString());
        			
        			// 6. 상품 이미지 DB insert
        			productImageMapper.insert(productImageDTO);
        			productImageCnt++;
        		}
    		}
    	}
    	return String.format("%s%s%s%s%s","상품 컨텐츠 : ", productCnt, " 건 / 상품 연관 이미지 컨텐츠 : ", productImageCnt, "건 정상 스크래핑 되었습니다.!!");
	}

    public List<ProductDTO> findAll() throws Exception {
    	return productMapper.findAll();
    }
    
    public List<HashMap<String,Object>> contentsList(HashMap<String, Object> params) throws Exception {
    	return productMapper.contentsList(params);
    }
}
