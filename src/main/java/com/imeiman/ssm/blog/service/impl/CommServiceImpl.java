package com.imeiman.ssm.blog.service.impl;

import com.imeiman.ssm.blog.domain.entity.Comment;
import com.imeiman.ssm.blog.mapper.CommentMapper;
import com.imeiman.ssm.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/23
 * Time: 14:22
 * Description:
 */

@Service
public class CommServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listByArticleId(Integer articleId) {
        List<Comment> comments = commentMapper.listByArticleId(articleId);
        return comments;
    }
}
