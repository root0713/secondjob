package net.bestmember.isjay.sinsang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import net.bestmember.isjay.sinsang.dto.ProductDTO;

@Mapper
public interface ProductMapper {
    @Select("select * from product")
    public List < ProductDTO > findAll();

    @Select("SELECT * FROM product WHERE gid = #{id}")
    public ProductDTO findById(int gid);

    @Delete("DELETE FROM product WHERE gid = #{id}")
    public int deleteById(int gid);

    @Insert("INSERT INTO product (gid,title,storeId,styleId,likes ,price,catId,catGenderId,catItemId,bigCategoryName,detailCategoryName,tel,color,size,mixtureRate ,intro,madeInCountry,cannotBuySingle)" +
        " VALUES (#{gid},#{title},#{storeId},#{styleId},#{likes},#{price},#{catId},#{catGenderId},#{catItemId},#{bigCategoryName},#{detailCategoryName},#{tel},#{color},#{size},#{mixtureRate},#{intro},#{madeInCountry},#{cannotBuySingle})")
    public int insert(ProductDTO productDTO);

    @Update("Update employees set first_name=#{firstName}, " +
        " last_name=#{lastName}, email_address=#{emailId} where id=#{id}")
    public int update(ProductDTO productDTO);
}



 