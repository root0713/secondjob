package net.bestmember.isjung.rentalfree.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.bestmember.isjung.rentalfree.dto.ContractDto;

@Mapper
public interface ContractMapper {
    public void insertContract (ContractDto dto);
    public void updateContract (ContractDto dto);
    public void deleteContract (long key);
    public ContractDto selectContract (long key);
    public List<ContractDto> selectContractList(long key);
}
