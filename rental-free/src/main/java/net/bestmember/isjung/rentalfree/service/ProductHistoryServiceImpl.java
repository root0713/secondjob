package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.bestmember.isjung.rentalfree.dao.ProductHistoryMapper;
import net.bestmember.isjung.rentalfree.dto.ProductHistoryDto;

@Service
public class ProductHistoryServiceImpl implements ProductHistoryService {
	
	@Autowired
	private ProductHistoryMapper mapper; 

	@Override
	public void create(ProductHistoryDto dto) {
		// TODO Auto-generated method stub
		if(!StringUtils.isEmpty(dto.getHist_name())) {
			mapper.insertProductHistory(dto);
		}
	}

	@Override
	public void update(ProductHistoryDto dto) {
		// TODO Auto-generated method stub
		mapper.updateProductHistory(dto);
	}

	@Override
	public void delete(long prodSeq) {
		// TODO Auto-generated method stub
		mapper.deleteProductHistory(prodSeq);
	}

	@Override
	public ProductHistoryDto select(long prodSeq) {
		// TODO Auto-generated method stub
		return mapper.selectProductHistory(prodSeq);
	}

	@Override
	public List<ProductHistoryDto> selectList(long prodSeq) {
		// TODO Auto-generated method stub
		return mapper.selectProductHistoryList(prodSeq);
	}

}
