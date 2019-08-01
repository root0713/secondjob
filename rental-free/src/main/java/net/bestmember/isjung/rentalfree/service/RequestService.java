package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import net.bestmember.isjung.rentalfree.dto.RequestDto;

public interface RequestService {
    public void create (RequestDto dto);
    public void update (RequestDto dto);
    public void delete (long prodSeq);
    public RequestDto select (long prodSeq);
    public List<RequestDto> selectList(HashMap<String, Object> params);
}
