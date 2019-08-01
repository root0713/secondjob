package net.bestmember.isjung.rentalfree.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import net.bestmember.isjung.rentalfree.dao.AttachFileMapper;
import net.bestmember.isjung.rentalfree.dao.RequestHistoryMapper;
import net.bestmember.isjung.rentalfree.dto.AttachFileDto;
import net.bestmember.isjung.rentalfree.dto.RequestHistoryDto;

@Service
public class AttachFileServiceImpl implements AttachFileService {
	
	@Autowired
	private AttachFileMapper mapper; 

	@Override
	public void create(Map<String, Object> dto) {
		// TODO Auto-generated method stub
		mapper.insertAttachFile(dto);
	}

	@Override
	public void update(Map<String, Object> dto) {
		// TODO Auto-generated method stub
		mapper.updateAttachFile(dto);
	}

	@Override
	public void delete(long prodSeq) {
		// TODO Auto-generated method stub
		mapper.deleteAttachFile(prodSeq);
	}

	@Override
	public Map<String, Object> select(long prodSeq) {
		// TODO Auto-generated method stub
		return mapper.selectAttachFile(prodSeq);
	}

	@Override
	public List<AttachFileDto> selectList(long prodSeq) {
		List<Map<String, Object>> selectAttachFileList = mapper.selectAttachFileList(prodSeq);
		List<AttachFileDto> attachFileDtoList = new ArrayList<AttachFileDto>(); 
		for(Map<String, Object> selectAttachFile : selectAttachFileList) {
			AttachFileDto attachFileDto = new AttachFileDto();
			byte[] imageContent = (byte[]) selectAttachFile.get("att_file");
			String base64Encoded = DatatypeConverter.printBase64Binary(imageContent);
			attachFileDto.setAtt_file_display(base64Encoded);
			attachFileDto.setAtt_seq(Long.parseLong(selectAttachFile.get("att_seq").toString()));
			attachFileDto.setProd_seq(Long.parseLong(selectAttachFile.get("prod_seq").toString()));
			attachFileDtoList.add(attachFileDto);
		}
		return attachFileDtoList;
	}

}
