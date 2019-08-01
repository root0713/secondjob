package net.bestmember.isjung.rentalfree.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.bestmember.isjung.rentalfree.dto.ProductHistoryDto;

@Mapper
public interface ProductHistoryMapper {
    public void insertProductHistory (ProductHistoryDto dto);
    public void updateProductHistory (ProductHistoryDto dto);
    public void deleteProductHistory (long key);
    public ProductHistoryDto selectProductHistory (long key);
    public List<ProductHistoryDto> selectProductHistoryList(long key);
}
