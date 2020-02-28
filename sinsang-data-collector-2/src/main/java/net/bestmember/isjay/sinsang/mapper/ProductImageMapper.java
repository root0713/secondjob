package net.bestmember.isjay.sinsang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.bestmember.isjay.sinsang.dto.ProductDTO;
import net.bestmember.isjay.sinsang.dto.ProductImageDTO;

@Mapper
public interface ProductImageMapper {
    @Select("select * from product_image WHERE gid = #{gid}")
    public List < ProductImageDTO > findImagesByGid(int gid);

    @Delete("DELETE FROM product_image WHERE gid = #{gid}")
    public int deleteImagesByGid(int gid);

    @Insert("INSERT INTO product_image (gid,imageUrl,filename) VALUES (#{gid},#{imageUrl},#{filename})")
    public int insert(ProductImageDTO productImageDTO);

    @Update("Update employees set first_name=#{firstName}, " +
        " last_name=#{lastName}, email_address=#{emailId} where id=#{id}")
    public int update(ProductDTO productDTO);
}

