package com.work.test.data.repository;

import com.work.test.data.model.Region;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface RegionRepository {

    @Insert("insert into Regions(id, name, abbreviation) values(#{id}, #{name}, #{abbreviation})")
    void create(Region region);

    @Select("select * from Regions")
    List<Region> findAll();

    @Select("select * from Regions where id = #{id}")
    Region findById(@Param("id") Long id);

    @Update("Update Regions set name=#{name},abbreviation=#{abbreviation} where id=#{id}")
    void updateRegion(Region region);

    @Delete("delete from Regions where id = #{id}")
    void delete(@Param("id") Long id);
}
