package net.bestmember.isjung.rentalfree.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bestmember.isjung.rentalfree.dto.AttachFileDto;
import net.bestmember.isjung.rentalfree.dto.ContractDto;
import net.bestmember.isjung.rentalfree.dto.ProductDto;
import net.bestmember.isjung.rentalfree.dto.ProductHistoryDto;
import net.bestmember.isjung.rentalfree.service.AttachFileService;
import net.bestmember.isjung.rentalfree.service.ContractService;
import net.bestmember.isjung.rentalfree.service.ProductHistoryService;
import net.bestmember.isjung.rentalfree.service.ProductService;

@Controller
@Transactional
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ContractService contractService;
	
	@Autowired
	private ProductHistoryService productHistoryService;
	
	@Autowired
	private AttachFileService attachFileService;
	
    @RequestMapping(value= {"/admin/product/list", "/admin/product"}, method= {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model, @RequestParam HashMap<String, Object> params) {
    	model.addAttribute("result", productService.selectList(params));
    	model.addAttribute("params", params);
        return "product/list";
    }
    
    @RequestMapping(value="/admin/product/createForm", method= {RequestMethod.GET, RequestMethod.POST})
    public String createForm(Model model, @RequestParam HashMap<String, Object> params) {
    	return "product/createForm";
    }
    
    @RequestMapping(value="/admin/product/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
    public String updateForm(Model model, @RequestParam HashMap<String, Object> params) {
		long prodSeq = Long.parseLong(params.get("prod_seq").toString());
		model.addAttribute("result", productService.select(prodSeq));
		model.addAttribute("params", params);
		return "product/updateForm";
    }
    
    @RequestMapping(value="/admin/product/create", method= {RequestMethod.POST})
    public String create(Model model, ProductDto params) throws IllegalStateException, IOException {
    	productService.create(params);
    	for(ContractDto contract : params.getContractDtoList()) {
    		contract.setProd_seq(params.getProd_seq());
    		contractService.create(contract);
    	}
    	
    	for(ProductHistoryDto history : params.getProductHistoryDto()) {
    		history.setProd_seq(params.getProd_seq());
    		productHistoryService.create(history);
    	}
    	uploadAttachFilesToDB(params);
        return this.list(model, null);
    }

    @RequestMapping(value="/admin/product/update", method= {RequestMethod.GET, RequestMethod.POST})
    public String update(Model model, ProductDto params) throws IllegalStateException, IOException {
    	productService.update(params);
    	contractService.delete(params.getProd_seq());
    	productHistoryService.delete(params.getProd_seq());
    	for(ContractDto contract : params.getContractDtoList()) {
    		contract.setProd_seq(params.getProd_seq());
    		contractService.create(contract);
    	}
    	
    	for(ProductHistoryDto history : params.getProductHistoryDto()) {
    		history.setProd_seq(params.getProd_seq());
    		productHistoryService.create(history);
    	}
    	uploadAttachFilesToDB(params);
        return this.list(model, null);
    }
    
//	private void uploadAttachFiles(ProductDto params) throws IOException {
//		if( params.getAttachFileDtoList() != null ) {
//	    	for(AttachFileDto attachFile : params.getAttachFileDtoList()) {
//	    		MultipartFile sourceFile = attachFile.getAtt_file();
//	    		if(attachFile.getAtt_file().isEmpty()) {
//	    			continue;
//	    		}
//	    		// create data
//	    		attachFile.setProd_seq(params.getProd_seq());
////	    		attachFileService.create(attachFile);
//	    		
//	    		// file upload
//	            String sourceFileNameExtension = FilenameUtils.getExtension(sourceFile.getOriginalFilename()).toLowerCase();
//	
//	            File destinationFile;
//	            String destinationFileName;
//	            do {
//	                destinationFileName = location + attachFile.getAtt_seq() + "." + sourceFileNameExtension;
//	                destinationFile = new File(destinationFileName);
//	            } while (destinationFile.exists());
//	            
//	            if(!destinationFile.getParentFile().exists()) destinationFile.getParentFile().mkdirs();
//	            sourceFile.transferTo(destinationFile);
////	            attachFileService.update(attachFile);
//	    	}
//    	}
//	}
    
    @RequestMapping(value="/admin/product/updateService", method= {RequestMethod.GET, RequestMethod.POST})
    public String updateService(Model model, ProductDto params) {
    	productService.update(params);
        return this.list(model, null);
    }
    
    @RequestMapping(value= {"", "/", "/service/list", "/service"}, method= {RequestMethod.GET, RequestMethod.POST})
    public String serviceList(Model model, @RequestParam HashMap<String, Object> params) {
    	params.put("srch_service_yn", "Y");
    	model.addAttribute("result", productService.selectList(params));
    	model.addAttribute("params", params);
        return "service/list";
    }
    
    @RequestMapping(value="/serivce/ajaxDetail", method= {RequestMethod.GET, RequestMethod.POST}, produces = "text/html;charset=UTF-8")
	public String ajaxSelect(Model model, @RequestParam Map<String, Object> params ) {
		long prodSeq = Long.parseLong(params.get("prod_seq").toString());
		model.addAttribute("result", productService.select(prodSeq));
		return "service/ajaxDetail";
	}
    
	@RequestMapping(value="/service/getByteImage/{att_seq}")
	public ResponseEntity<byte[]> getByteImage(@PathVariable String att_seq) throws IOException {
		long attSeq = Long.parseLong(att_seq);
		Map<String, Object> attachFileMap = attachFileService.select(attSeq);
		byte[] imageContent = (byte[]) attachFileMap.get("att_file");
		final HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.IMAGE_JPEG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	private void uploadAttachFilesToDB(ProductDto params) throws IOException {
		if( params.getAttachFileDtoList() != null ) {
	    	for(AttachFileDto attachFile : params.getAttachFileDtoList()) {
	    		if(attachFile.getAtt_file() == null || attachFile.getAtt_file().isEmpty()) {
	    			continue;
	    		}
	    		Map<String, Object> hmap = new HashMap<String, Object>();
	    		hmap.put("att_file", attachFile.getAtt_file().getBytes());
	    		hmap.put("prod_seq", params.getProd_seq());
	    		
	    		// create data
	    		attachFileService.create(hmap);
	    	}
    	}
	}
    
}
