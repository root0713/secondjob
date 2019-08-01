package net.bestmember.isjung.rentalfree.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.bestmember.isjung.rentalfree.dto.RequestDto;

@Mapper
public interface RequestMapper {
    public void insertRequest (RequestDto dto);
    public void updateRequest (RequestDto dto);
    public void deleteRequest (long key);
    public RequestDto selectRequest (long key);
    public List<RequestDto> selectRequestList(HashMap<String, Object> params);
}
