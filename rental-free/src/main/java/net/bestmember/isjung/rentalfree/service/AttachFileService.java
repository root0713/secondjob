package net.bestmember.isjung.rentalfree.service;

import java.util.List;
import java.util.Map;

import net.bestmember.isjung.rentalfree.dto.AttachFileDto;

public interface AttachFileService {
    public void create (Map<String, Object> dto);
    public void update (Map<String, Object> dto);
    public void delete (long prodSeq);
    public Map<String, Object> select (long prodSeq);
    public List<AttachFileDto> selectList(long prodSeq);
}
