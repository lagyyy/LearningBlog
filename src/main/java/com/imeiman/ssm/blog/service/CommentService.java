package com.imeiman.ssm.blog.service;

import com.imeiman.ssm.blog.domain.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/23
 * Time: 14:21
 * Description:
 */

@Service
public interface CommentService {
    List<Comment> listByArticleId(Integer articleId);
}
