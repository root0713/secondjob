package net.bestmember.isjung.rentalfree.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.bestmember.isjung.rentalfree.dto.RequestDto;
import net.bestmember.isjung.rentalfree.dto.RequestHistoryDto;
import net.bestmember.isjung.rentalfree.service.RequestHistoryService;
import net.bestmember.isjung.rentalfree.service.RequestService;

@Controller
@Transactional
public class RequestController {

	@Autowired
	private RequestService requestService;

	@Autowired
	private RequestHistoryService requestHistoryService;
	
    @RequestMapping(value= {"/admin/request/list", "/admin/request"}, method= {RequestMethod.GET, RequestMethod.POST})
    public String list(Model model, @RequestParam HashMap<String, Object> params) {
    	model.addAttribute("result", requestService.selectList(params));
    	model.addAttribute("params", params);
        return "request/list";
    }
    
    @RequestMapping(value="/admin/request/createForm", method= {RequestMethod.GET, RequestMethod.POST})
    public String createForm(Model model, @RequestParam HashMap<String, Object> params) {
    	return "request/createForm";
    }
    
    @RequestMapping(value="/admin/request/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
    public String updateForm(Model model, @RequestParam HashMap<String, Object> params) {
		long reqSeq = Long.parseLong(params.get("req_seq").toString());
		model.addAttribute("result", requestService.select(reqSeq));
		model.addAttribute("params", params);
		return "request/updateForm";
    }
    
	@RequestMapping(value="/admin/request/update", method= {RequestMethod.GET, RequestMethod.POST})
	public String update(Model model, RequestDto params) {
		requestService.update(params);
		for(RequestHistoryDto requestHistoryDto : params.getRequestHistoryDtoList()) {
			requestHistoryDto.setReq_seq(params.getReq_seq());
			if(requestHistoryDto.getHist_seq() == null && (!StringUtils.isEmpty(requestHistoryDto.getHist_name())||!StringUtils.isEmpty(requestHistoryDto.getHist_content()))) {
				requestHistoryService.create(requestHistoryDto);
			}
		}
	    return this.list(model, null);
	}
    
    @RequestMapping(value="/service/request", method= {RequestMethod.GET, RequestMethod.POST})
    public String create(Model model, RequestDto params) {
    	requestService.create(params);
    	return "forward:list";
    }
//    	
//    	for(ProductHistoryDto history : params.getProductHistoryDto()) {
//    		history.setProd_seq(params.getProd_seq());
//    		productHistoryService.create(history);
//    	}
//    	
//        return this.list(model, null);
//    }
//    
//    @RequestMapping(value="/admin/product/update", method= {RequestMethod.GET, RequestMethod.POST})
//    public String update(Model model, ProductDto params) {
//    	productService.update(params);
//    	contractService.delete(params.getProd_seq());
//    	productHistoryService.delete(params.getProd_seq());
//    	for(ContractDto contract : params.getContractDtoList()) {
//    		contract.setProd_seq(params.getProd_seq());
//    		contractService.create(contract);
//    	}
//    	
//    	for(ProductHistoryDto history : params.getProductHistoryDto()) {
//    		history.setProd_seq(params.getProd_seq());
//    		productHistoryService.create(history);
//    	}
//        return this.list(model, null);
//    }
//    
//    @RequestMapping(value="/admin/product/updateService", method= {RequestMethod.GET, RequestMethod.POST})
//    public String updateService(Model model, ProductDto params) {
//    	productService.update(params);
//        return this.list(model, null);
//    }
//    
//    @RequestMapping(value="/user/product/select", method= {RequestMethod.GET, RequestMethod.POST})
//    public String select(Model model, HashMap<String, Object> params) {
//    	long prodSeq = Long.parseLong(params.get("prod_seq").toString());
//    	productService.select(prodSeq);
//        return this.list(model, null);
//    }
}
