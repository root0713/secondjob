package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import net.bestmember.isjung.rentalfree.dto.ContractDto;

public interface ContractService {
    public void create (ContractDto dto);
    public void update (ContractDto dto);
    public void delete (long prodSeq);
    public ContractDto select (long prodSeq);
    public List<ContractDto> selectList(long prodSeq);
}
