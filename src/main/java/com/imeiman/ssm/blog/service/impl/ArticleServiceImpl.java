package com.imeiman.ssm.blog.service.impl;

import cn.hutool.core.date.DateTime;
import com.github.pagehelper.PageInfo;
import com.imeiman.ssm.blog.domain.dto.ArticleEditDTO;
import com.imeiman.ssm.blog.domain.entity.*;
import com.imeiman.ssm.blog.mapper.*;
import com.imeiman.ssm.blog.service.ArticleService;
import com.imeiman.ssm.blog.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
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

    @Autowired
    ArticleTagRefMapper articleTagRefMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

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
    public Article getById(Integer articleId,Integer status) {
        Article byId = null;
        try {
            byId = articleMapper.getById(articleId, status);
        }catch(Exception e){
            e.printStackTrace();
        }
        return byId;
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

    @Override
    public List<Article> listByArticleLikeCount(Integer limit) {
        List<Article> articles = articleMapper.listByArticleLikeCount(limit);
        return articles;
    }

    @Override
    public List<Article> getArticleByTagIdList(Integer tagId) {
        List<Article> articleByTagIdList = articleMapper.getArticleByTagIdList(tagId);
        return articleByTagIdList;
    }


    @Override
    public List<Article> getArticleByCategoryIdList(Integer categoryId) {
        List<Article> articleByCategoryIdList = articleMapper.getArticleByCategoryIdList(categoryId);
        return articleByCategoryIdList;
    }


    @Override
    public Integer editSave(ArticleEditDTO article) {
        Integer articleChildCategoryId = article.getArticleChildCategoryId();
        Integer articleId = article.getArticleId();
        List<Integer> articleTagIds = article.getArticleTagIds();
        List<ArticleCategoryRef> articleCategoryRefs = new ArrayList<>();
        articleCategoryRefs.add(new ArticleCategoryRef().setArticleId(articleId).setCategoryId(article.getArticleParentCategoryId()));
        articleCategoryRefs.add(new ArticleCategoryRef().setArticleId(articleId).setCategoryId(article.getArticleChildCategoryId()));

        try {
            int r = articleCategoryRefMapper.deleteByArticleId(articleId);
            Integer integer = articleTagRefMapper.deleteByArticleId(articleId);

                List<ArticleTagRef> articleTagRefs = new ArrayList<>();

                for (Integer id:articleTagIds){
                    articleTagRefs.add(new ArticleTagRef().setArticleId(articleId).setTagId(id));
                }
                int i = articleTagRefMapper.insertBatch(articleTagRefs);

                int i1 = articleCategoryRefMapper.insertBatch(articleCategoryRefs);
                System.out.println("成功添加数据"+i1);

            Article article1 = BeanCopyUtils.copyBean(article, Article.class);
            boolean b = articleMapper.updateById(article1);
            if (b){
                return 1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int insertOne(ArticleEditDTO article, HttpSession session) {




        try {
            Article article1 = BeanCopyUtils.copyBean(article, Article.class);

            User user = (User) session.getAttribute("user");
            System.out.println(user+"添加——————————————————————————");
            if (user != null) {
                article1.setArticleUserId(user.getUserId());
            }
            article1.setArticleCreateTime(DateTime.now());
            article1.setArticleUpdateTime(DateTime.now());
            int b = articleMapper.insertOne(article1);


            Integer articleId = article1.getArticleId();
            List<Integer> articleTagIds = article.getArticleTagIds();
            List<ArticleCategoryRef> articleCategoryRefs = new ArrayList<>();
            articleCategoryRefs.add(new ArticleCategoryRef().setArticleId(articleId).setCategoryId(article.getArticleParentCategoryId()));
            articleCategoryRefs.add(new ArticleCategoryRef().setArticleId(articleId).setCategoryId(article.getArticleChildCategoryId()));
            List<ArticleTagRef> articleTagRefs = new ArrayList<>();
            for (Integer id:articleTagIds){
                articleTagRefs.add(new ArticleTagRef().setArticleId(articleId).setTagId(id));
            }
            int i = articleTagRefMapper.insertBatch(articleTagRefs);
            int i1 = articleCategoryRefMapper.insertBatch(articleCategoryRefs);

            System.out.println("成功添加数据"+i1);
            return b;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


}
