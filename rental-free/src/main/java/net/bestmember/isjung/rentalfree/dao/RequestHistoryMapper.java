package net.bestmember.isjung.rentalfree.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.bestmember.isjung.rentalfree.dto.RequestHistoryDto;

@Mapper
public interface RequestHistoryMapper {
    public void insertRequestHistory (RequestHistoryDto dto);
    public void updateRequestHistory (RequestHistoryDto dto);
    public void deleteRequestHistory (long key);
    public RequestHistoryDto selectRequestHistory (long key);
    public List<RequestHistoryDto> selectRequestHistoryList(long key);
}
