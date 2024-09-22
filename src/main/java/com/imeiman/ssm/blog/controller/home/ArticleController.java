package com.imeiman.ssm.blog.controller.home;

import com.imeiman.ssm.blog.domain.entity.Article;
import com.imeiman.ssm.blog.domain.entity.Category;
import com.imeiman.ssm.blog.domain.entity.Tag;
import com.imeiman.ssm.blog.domain.entity.User;
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
    private UserService userService;

    @RequestMapping(value = "/article/{articleId}")
    public String getArticleDetailPage(@PathVariable Integer articleId, Model model){
        Article article = articleService.getById(articleId);

        if (article == null) {
            return "Home/Error/404";
        }
        User userById = userService.getById(article.getArticleUserId());
        article.setUser(userById);

        List<Tag> articleTagById = tagService.getArticleTagById(articleId);
        article.setTagList(articleTagById);



        List<Category> categoryByArticleIdList = categoryService.getCategoryByArticleIdList(articleId);
        article.setCategoryList(categoryByArticleIdList);

        model.addAttribute("article",article);

        return "Home/Page/articleDetail";


    }
}
