package com.imeiman.ssm.blog.service.impl;

import com.github.pagehelper.PageInfo;
import com.imeiman.ssm.blog.domain.entity.*;
import com.imeiman.ssm.blog.mapper.ArticleMapper;
import com.imeiman.ssm.blog.mapper.CategoryMapper;
import com.imeiman.ssm.blog.mapper.TagMapper;
import com.imeiman.ssm.blog.mapper.UserMapper;
import com.imeiman.ssm.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    UserMapper userMapper;


    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    TagMapper tagMapper;

    @Override
    public PageInfo<Article> pageArticle(Integer status, Integer pageIndex, Integer pageSize) {
        List<Article> articles = null;

        try {
            articles = articleMapper.pageArticle(status, pageIndex, pageSize);
        }catch (Exception e){

        }
        int size = articles.size();
        for (int i = 0; i < size; i++) {
            Article article = articles.get(i);
            Integer articleUserId = article.getArticleUserId();
            User byId = userMapper.getById(articleUserId);
            article.setUser(byId);
            List<Category> categoryByArticleIdList = categoryMapper.getCategoryByArticleIdList(article.getArticleId());
            article.setCategoryList(categoryByArticleIdList);

            List<Tag> articleTagById = tagMapper.getArticleTagById(article.getArticleId());
            article.setTagList(articleTagById);

        }

        return new PageInfo<>(articles);
    }

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {


        return null;
    }

    @Override
    public Integer getArticleCount() {
        Integer articleCount = articleMapper.getArticleCount();
        return articleCount;
    }

    @Override
    public Integer getAllByArticleCommentCountInteger() {
        return articleMapper.getAllByArticleCommentCountInteger();
    }

    @Override
    public Integer getArticleViewCountInteger() {
        return articleMapper.getArticleViewCountInteger();
    }

    @Override
    public Date getArticleLastUpdateTimeDate() {
        Date articleLastUpdateTimeDate = articleMapper.getArticleLastUpdateTimeDate();
        return articleLastUpdateTimeDate;
    }

    @Override
    public Article getById(Integer articleId) {
        return articleMapper.getById(articleId);
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return articleMapper.listArticleByViewCount(limit);
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        List<Article> articles = articleMapper.listRandomArticle(limit);
        return articles;
    }


}
