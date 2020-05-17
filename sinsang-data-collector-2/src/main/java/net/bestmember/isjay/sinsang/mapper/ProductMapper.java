package net.bestmember.isjay.sinsang.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import net.bestmember.isjay.sinsang.dto.ProductDTO;

@Repository
@Mapper
public interface ProductMapper {
    public List<ProductDTO> findAll() throws Exception;

    public ProductDTO findById(int gid) throws Exception;
    
    public List<HashMap<String,Object>> contentsList(HashMap<String,Object> param) throws Exception;

    public int deleteById(int gid) throws Exception;

    public int insert(ProductDTO productDTO) throws Exception;

}