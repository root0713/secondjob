package net.bestmember.isjung.rentalfree.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.bestmember.isjung.rentalfree.dao.AttachFileMapper;
import net.bestmember.isjung.rentalfree.dao.ContractMapper;
import net.bestmember.isjung.rentalfree.dao.ProductHistoryMapper;
import net.bestmember.isjung.rentalfree.dao.ProductMapper;
import net.bestmember.isjung.rentalfree.dto.AttachFileDto;
import net.bestmember.isjung.rentalfree.dto.ContractDto;
import net.bestmember.isjung.rentalfree.dto.ProductDto;
import net.bestmember.isjung.rentalfree.dto.ProductHistoryDto;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapper mapper; 
	
	@Autowired
	private ContractMapper cMapper; 
	
	@Autowired
	private ProductHistoryMapper phMapper;
	
	@Autowired
	private AttachFileMapper aMapper; 

	@Override
	public long create(ProductDto dto) {
		// TODO Auto-generated method stub
		return mapper.insertProduct(dto);
	}

	@Override
	public void update(ProductDto dto) {
		// TODO Auto-generated method stub
		mapper.updateProduct(dto);
	}

	@Override
	public void delete(long prodSeq) {
		// TODO Auto-generated method stub
		mapper.deleteProduct(prodSeq);
	}

	@Override
	public ProductDto select(long prodSeq) {
		// TODO Auto-generated method stub
		List<ContractDto> contractList = cMapper.selectContractList(prodSeq);
		List<ProductHistoryDto> productHistoryList = phMapper.selectProductHistoryList(prodSeq);
		
		List<Map<String, Object>> selectAttachFileList = aMapper.selectAttachFileList(prodSeq);
		List<AttachFileDto> attachFileDtoList = new ArrayList<AttachFileDto>(); 
		for(Map<String, Object> selectAttachFile : selectAttachFileList) {
			AttachFileDto attachFileDto = new AttachFileDto();
			byte[] imageContent =  (byte[]) selectAttachFile.get("att_file");
			String base64Encoded = DatatypeConverter.printBase64Binary(imageContent);
			
			attachFileDto.setAtt_file_display(base64Encoded);
			attachFileDto.setAtt_seq(Long.parseLong(selectAttachFile.get("att_seq").toString()));
			attachFileDto.setProd_seq(Long.parseLong(selectAttachFile.get("prod_seq").toString()));
			attachFileDtoList.add(attachFileDto);
		}
		
		ProductDto productDto = mapper.selectProduct(prodSeq);
		productDto.setContractDtoList(contractList);
		productDto.setProductHistoryDto(productHistoryList);
		productDto.setAttachFileDtoList(attachFileDtoList);
		return productDto;
	}

	@Override
	public List<ProductDto> selectList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		List<ProductDto> selectList = mapper.selectProductList(params);
		for(ProductDto product : selectList) {
			List<Map<String, Object>> selectAttachFileList = aMapper.selectAttachFileList(product.getProd_seq());
			List<AttachFileDto> attachFileDtoList = new ArrayList<AttachFileDto>(); 
			for(Map<String, Object> selectAttachFile : selectAttachFileList) {
				// 첫번째 대표이미지만 가져옴 
				AttachFileDto attachFileDto = new AttachFileDto();
				byte[] imageContent = (byte[]) selectAttachFile.get("att_file");
				String base64Encoded = DatatypeConverter.printBase64Binary(imageContent);
				
				attachFileDto.setAtt_file_display(base64Encoded);
				attachFileDto.setAtt_seq(Long.parseLong(selectAttachFile.get("att_seq").toString()));
				attachFileDto.setProd_seq(Long.parseLong(selectAttachFile.get("prod_seq").toString()));
				attachFileDtoList.add(attachFileDto);
				break;
			}
			product.setAttachFileDtoList(attachFileDtoList);
		}
		
		return mapper.selectProductList(params);
	}

}
