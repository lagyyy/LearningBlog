package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 接口实现：@Mapper注解告诉Mybatis这是一个Mapper接口，
 Mybatis会在运行时动态生成该接口的实现类，并将其纳入Spring容器中进行统一管理。
 * */
@Mapper
public interface TagMapper {
    /**
     * 查询所有的tag
     * */

    List<Tag> getAllTags();


    List<Tag> getArticleTagById(Integer id);
}
