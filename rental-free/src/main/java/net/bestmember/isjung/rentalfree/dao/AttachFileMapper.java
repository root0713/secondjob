package net.bestmember.isjung.rentalfree.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import net.bestmember.isjung.rentalfree.dto.AttachFileDto;
import net.bestmember.isjung.rentalfree.dto.RequestHistoryDto;

@Mapper
public interface AttachFileMapper {
    public void insertAttachFile (Map<String, Object> dto);
    public void updateAttachFile(Map<String, Object> dto);
    public void deleteAttachFile (long key);
    public Map<String, Object> selectAttachFile (long key);
    public List<Map<String, Object>> selectAttachFileList(long key);
}
