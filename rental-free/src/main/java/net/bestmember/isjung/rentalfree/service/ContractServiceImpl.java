package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import net.bestmember.isjung.rentalfree.dao.ContractMapper;
import net.bestmember.isjung.rentalfree.dto.ContractDto;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
	private ContractMapper mapper; 

	@Override
	public void create(ContractDto dto) {
		// TODO Auto-generated method stub
		if(!StringUtils.isEmpty(dto.getContrt_type())) {
			mapper.insertContract(dto);
		}
	}

	@Override
	public void update(ContractDto dto) {
		// TODO Auto-generated method stub
		mapper.updateContract(dto);
	}

	@Override
	public void delete(long prodSeq) {
		// TODO Auto-generated method stub
		mapper.deleteContract(prodSeq);
	}

	@Override
	public ContractDto select(long prodSeq) {
		// TODO Auto-generated method stub
		return mapper.selectContract(prodSeq);
	}

	@Override
	public List<ContractDto> selectList(long prodSeq) {
		// TODO Auto-generated method stub
		return mapper.selectContractList(prodSeq);
	}

}
