package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import net.bestmember.isjung.rentalfree.dto.RequestHistoryDto;

public interface RequestHistoryService {
    public void create (RequestHistoryDto dto);
    public void update (RequestHistoryDto dto);
    public void delete (long prodSeq);
    public RequestHistoryDto select (long reqSeq);
    public List<RequestHistoryDto> selectList(long reqSeq);
}
