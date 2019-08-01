package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import net.bestmember.isjung.rentalfree.dto.ProductHistoryDto;

public interface ProductHistoryService {
    public void create (ProductHistoryDto dto);
    public void update (ProductHistoryDto dto);
    public void delete (long prodSeq);
    public ProductHistoryDto select (long prodSeq);
    public List<ProductHistoryDto> selectList(long prodSeq);
}
