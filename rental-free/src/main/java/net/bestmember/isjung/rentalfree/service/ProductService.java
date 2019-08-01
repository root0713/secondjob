package net.bestmember.isjung.rentalfree.service;

import java.util.HashMap;
import java.util.List;

import net.bestmember.isjung.rentalfree.dto.ProductDto;

public interface ProductService {
    public long create (ProductDto dto);
    public void update (ProductDto dto);
    public void delete (long prodSeq);
    public ProductDto select (long prodSeq);
    public List<ProductDto> selectList(HashMap<String, Object> params);
}
