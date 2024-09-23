package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> listByArticleId(Integer articleId);
}
