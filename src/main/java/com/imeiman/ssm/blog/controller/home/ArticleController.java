package com.imeiman.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.imeiman.ssm.blog.domain.entity.*;
import com.imeiman.ssm.blog.domain.vo.TagVo;
import com.imeiman.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private LinkService linkService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/article/{articleId}")
    public String getArticleDetailPage(@PathVariable Integer articleId, Model model){
        Article article = articleService.getById(articleId,1);
        if (article == null) {
            return "Home/Error/404";
        }
        User userById = userService.getById(article.getArticleUserId());
        article.setUser(userById);

        List<Tag> articleTagById = tagService.getArticleTagById(articleId);
        article.setTagList(articleTagById);

        List<Article> articles = articleService.listArticleByViewCount(5);
        model.addAttribute("mostCommentArticleList",articles);
        model.addAttribute("similarArticleList",articles);

        List<Tag> allTags = tagService.getAllTags();
        model.addAttribute("allTagList",allTags);


        List<Category> categoryByArticleIdList = categoryService.getCategoryByArticleIdList(articleId);
        article.setCategoryList(categoryByArticleIdList);

        model.addAttribute("article",article);

        List<Article> articlesRandom = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList",articlesRandom);

        List<Comment> comments = commentService.listByArticleId(article.getArticleId());
        model.addAttribute("commentList",comments);

        List<Article> articlesByLike = articleService.listByArticleLikeCount(5);
        model.addAttribute("mostViewArticleList",articlesByLike);

        return "Home/Page/articleDetail";


    }

    @RequestMapping(value = "/tag/{tagId}")
    public String getArticleTagPage(Model model, @PathVariable Integer tagId){
        System.out.println(tagId);

        List<Article> articleByTagIdList = articleService.getArticleByTagIdList(tagId);
        PageInfo<Article> articlePageInfo = new PageInfo<>(articleByTagIdList);
        model.addAttribute("pageInfo",articlePageInfo);
        System.out.println(articlePageInfo.getList().size()+"______________________________Tag");

        return "Home/Page/articleListByTag";
    }

    @RequestMapping(value = "/category/{categoryId}")
    public String getArticleCategoryPage(Model model, @PathVariable Integer categoryId){
        List<Article> articleByCategoryIdList = articleService.getArticleByCategoryIdList(categoryId);
        PageInfo<Article> articlePageInfo = new PageInfo<>();
        articlePageInfo.setList(articleByCategoryIdList);
        model.addAttribute("pageInfo",articlePageInfo);
        return "/Home/Page/articleListByCategory";
    }
}
