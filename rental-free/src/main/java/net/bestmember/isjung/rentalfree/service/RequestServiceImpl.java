package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bestmember.isjung.rentalfree.dao.RequestHistoryMapper;
import net.bestmember.isjung.rentalfree.dao.RequestMapper;
import net.bestmember.isjung.rentalfree.dto.ContractDto;
import net.bestmember.isjung.rentalfree.dto.ProductDto;
import net.bestmember.isjung.rentalfree.dto.ProductHistoryDto;
import net.bestmember.isjung.rentalfree.dto.RequestDto;
import net.bestmember.isjung.rentalfree.dto.RequestHistoryDto;

@Service
public class RequestServiceImpl implements RequestService {
	
	@Autowired
	private RequestMapper mapper; 
	
	@Autowired
	private RequestHistoryMapper rhMapper; 

	@Override
	public void create(RequestDto dto) {
		// TODO Auto-generated method stub
		mapper.insertRequest(dto);
	}

	@Override
	public void update(RequestDto dto) {
		// TODO Auto-generated method stub
		mapper.updateRequest(dto);
	}

	@Override
	public void delete(long prodSeq) {
		// TODO Auto-generated method stub
		mapper.deleteRequest(prodSeq);
	}

	@Override
	public RequestDto select(long reqSeq) {
		
		// TODO Auto-generated method stub
		List<RequestHistoryDto> requestHistoryList = rhMapper.selectRequestHistoryList(reqSeq);
		RequestDto requestDto = mapper.selectRequest(reqSeq);
		requestDto.setRequestHistoryDtoList(requestHistoryList);
		
		return requestDto;
	}

	@Override
	public List<RequestDto> selectList(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return mapper.selectRequestList(params);
	}

}
