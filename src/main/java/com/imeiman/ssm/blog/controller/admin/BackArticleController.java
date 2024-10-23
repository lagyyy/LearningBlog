package com.imeiman.ssm.blog.controller.admin;

import com.github.pagehelper.PageInfo;
import com.imeiman.ssm.blog.domain.dto.ArticleEditDTO;
import com.imeiman.ssm.blog.domain.entity.Article;
import com.imeiman.ssm.blog.domain.entity.Category;
import com.imeiman.ssm.blog.domain.entity.Tag;
import com.imeiman.ssm.blog.domain.entity.User;
import com.imeiman.ssm.blog.service.ArticleService;
import com.imeiman.ssm.blog.service.CategoryService;
import com.imeiman.ssm.blog.service.TagService;
import com.imeiman.ssm.blog.util.BeanCopyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/9/24
 * Time: 16:05
 * Description:
 */

@Controller
@RequestMapping("/admin/article")
public class BackArticleController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = { ""})
    public String index(@RequestParam(required = false, defaultValue = "0") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model){

        PageInfo<Article> articlePageInfo = articleService.pageArticle(null, pageIndex, pageSize);
        articlePageInfo.setPages(pageIndex);
        articlePageInfo.setPageNum(pageSize);
        model.addAttribute("pageInfo",articlePageInfo);
        return "Admin/Article/index";
    }

    @RequestMapping(value = { "/edit/{articleId}"})
    public String edit(Model model, @PathVariable Integer articleId){
        System.out.println(articleId);
        Article articleById = articleService.getById(articleId,null);

        List<Category> categoryByArticleIdList = categoryService.getCategoryByArticleIdList(articleId);
        articleById.setCategoryList(categoryByArticleIdList);

                List<Tag> articleTagById = tagService.getArticleTagById(articleId);
        articleById.setTagList(articleTagById);

        List<Tag> allTags = tagService.getAllTags();
        model.addAttribute("tagList",allTags);

        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);

        System.out.println(articleById);
        model.addAttribute("article",articleById);

        return "Admin/Article/edit";
        }

    @RequestMapping(value = { "/editSubmit"})
//    @ResponseBody
    public String editSave(Model model, @ModelAttribute("article")ArticleEditDTO article){
        System.out.println("————————————————————————————————————Edit_______________________________________");

        System.out.println("articleParentCategoryId="+article.getArticleParentCategoryId()+":"+" articleChildCategoryId="+article.getArticleChildCategoryId());

        List<Integer> articleTagIds = article.getArticleTagIds();

        for (Integer i:articleTagIds){
            System.out.println(i);
        }
        System.out.println();


        Integer integer = articleService.editSave(article);
        System.out.println(integer+"dddddddddddddddddd");
        if (integer ==1){
            PageInfo<Article> articlePageInfo = articleService.pageArticle(null, 0, 10);

            model.addAttribute("pageInfo",articlePageInfo);
            return "redirect:/admin/article";
        }
        return "";

    }


    @RequestMapping(value = { "/insert"})
    public String insert(Model model){
        List<Tag> allTags = tagService.getAllTags();
        model.addAttribute("tagList",allTags);

        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList",categoryList);
        return "Admin/Article/insert";

    }


    @RequestMapping(value = { "/insertSubmit"})
    public String insertSave(HttpSession session, Model model, @ModelAttribute("article")ArticleEditDTO article){
        System.out.println("————————————————————————————————————Insert_______________________________________");

        article.setArticleContent("");



        int i = articleService.insertOne(article,session);

        System.out.println(article);
//        if (i==1){
//            PageInfo<Article> articlePageInfo = articleService.pageArticle(null, 0, 10);
//
//            model.addAttribute("pageInfo",articlePageInfo);
//            return "redirect:/admin/article";
//        }



//        Integer integer = articleService.in-Save(article);
//        System.out.println(integer+"dddddddddddddddddd");
//        if (integer ==1){
//            PageInfo<Article> articlePageInfo = articleService.pageArticle(null, 0, 10);
//
//            model.addAttribute("pageInfo",articlePageInfo);
//            return "redirect:/admin/article";
//        }
        return "";

    }



}
