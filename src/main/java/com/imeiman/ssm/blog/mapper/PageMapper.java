package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author ngz
* @description 针对表【page】的数据库操作Mapper
* @createDate 2024-10-23 14:34:29
* @Entity generator.domain.Page
*/
@Mapper
public interface PageMapper {
    List<Page> getAllPages();

    Page getById(Integer pageId);

    boolean update(Page page);

    Integer deleteById(Integer pageId);

    int insertOne(Page page);
}




