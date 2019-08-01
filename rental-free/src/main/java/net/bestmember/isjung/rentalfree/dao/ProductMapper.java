package net.bestmember.isjung.rentalfree.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.bestmember.isjung.rentalfree.dto.ProductDto;

@Mapper
public interface ProductMapper {
    public long insertProduct (ProductDto dto);
    public void updateProduct (ProductDto dto);
    public void deleteProduct (long key);
    public ProductDto selectProduct (long key);
    public List<ProductDto> selectProductList(HashMap<String, Object> params);
}
