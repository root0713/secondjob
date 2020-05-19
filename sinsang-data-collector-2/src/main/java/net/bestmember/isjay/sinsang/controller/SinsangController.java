package net.bestmember.isjay.sinsang.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.bestmember.isjay.common.util.CSVUtils;
import net.bestmember.isjay.sinsang.dto.ProductDTO;
import net.bestmember.isjay.sinsang.service.SinsangService;

@RestController
public class SinsangController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    private SinsangService sinsangService;
    
	
    @GetMapping({"/collect", "/collect/{gender}/{size}/{priceLow}/{priceHigh}"})
    public String collect(@PathVariable(required = false) String gender, @PathVariable(required = false) Integer size, @PathVariable(required = false) Integer priceLow, @PathVariable(required = false) Integer priceHigh) throws Exception {
    	sinsangService.crawlling(gender, size, priceLow, priceHigh);
    	return null;
    }
    
    @GetMapping({"/findAll"})
    @ResponseBody
    public List<ProductDTO>  findAll() throws Exception {
    	List<ProductDTO> result = sinsangService.findAll();
    	return result ;
    }
    
    @GetMapping({"/contentsList"})
    @ResponseBody
    public List<HashMap<String,Object>> contentsList(@RequestParam HashMap<String, Object> params) throws Exception {
    	List<HashMap<String,Object>> result = sinsangService.contentsList(params);
    	return result ;
    }
    
    @GetMapping({"/downloadCSV"})
    public void downloadCSV(@RequestParam HashMap<String, Object> params, HttpServletResponse response) throws Exception {
    	if(params.get("minDate") == null) {
    		Date date = new Date(); 
    		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		params.put("minDate", formatter.format(date));
    	}
        //set file name and content type
        String filename = String.format("%s_%s.csv", "sinsang", params.get("minDate") == null ? "" : params.get("minDate"));

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + filename + "\"");

        response.setCharacterEncoding("EUC-KR");
        CSVUtils.csvWriter(sinsangService.contentsList(params), response.getWriter());
    }
    
    // csv 파일 업로드
    
    // 
}
