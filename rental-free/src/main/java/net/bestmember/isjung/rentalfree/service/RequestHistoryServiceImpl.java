package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bestmember.isjung.rentalfree.dao.RequestHistoryMapper;
import net.bestmember.isjung.rentalfree.dto.RequestHistoryDto;

@Service
public class RequestHistoryServiceImpl implements RequestHistoryService {
	
	@Autowired
	private RequestHistoryMapper mapper; 

	@Override
	public void create(RequestHistoryDto dto) {
		// TODO Auto-generated method stub
		mapper.insertRequestHistory(dto);
	}

	@Override
	public void update(RequestHistoryDto dto) {
		// TODO Auto-generated method stub
		mapper.updateRequestHistory(dto);
	}

	@Override
	public void delete(long prodSeq) {
		// TODO Auto-generated method stub
		mapper.deleteRequestHistory(prodSeq);
	}

	@Override
	public RequestHistoryDto select(long reqSeq) {
		// TODO Auto-generated method stub
		return mapper.selectRequestHistory(reqSeq);
	}

	@Override
	public List<RequestHistoryDto> selectList(long reqSeq) {
		// TODO Auto-generated method stub
		return mapper.selectRequestHistoryList(reqSeq);
	}

}
