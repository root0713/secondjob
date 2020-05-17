package net.bestmember.isjay.sinsang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import net.bestmember.isjay.sinsang.dto.ProductDTO;
import net.bestmember.isjay.sinsang.dto.ProductImageDTO;

@Repository
@Mapper
public interface ProductImageMapper {
    public List < ProductImageDTO > findImagesByGid(int gid) throws Exception;

    public int deleteImagesByGid(int gid) throws Exception;

    public int insert(ProductImageDTO productImageDTO) throws Exception;

}

