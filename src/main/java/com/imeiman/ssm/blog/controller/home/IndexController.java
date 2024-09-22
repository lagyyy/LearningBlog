package com.imeiman.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.imeiman.ssm.blog.domain.entity.Article;
import com.imeiman.ssm.blog.domain.entity.Link;
import com.imeiman.ssm.blog.domain.entity.Notice;
import com.imeiman.ssm.blog.domain.entity.Tag;
import com.imeiman.ssm.blog.enums.ArticleStatus;
import com.imeiman.ssm.blog.mapper.LinkMapper;
import com.imeiman.ssm.blog.service.ArticleService;
import com.imeiman.ssm.blog.service.LinkService;
import com.imeiman.ssm.blog.service.NoticeService;
import com.imeiman.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private LinkService linkService;

    @RequestMapping(value = {"/", "/article"})
    public String index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,Model model){
        HashMap<String, Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        System.out.println(pageIndex+":::::::::"+pageSize);
        PageInfo<Article> articles = articleService.pageArticle(1, pageIndex, pageSize);

        model.addAttribute("pageInfo", articles);

        List<Notice> allNotice = noticeService.getAllNotice();
        model.addAttribute("noticeList",allNotice);


        List<Link> allLink = linkService.getAllLink();
        model.addAttribute("linkList",allLink);


        List<Tag> allTags = tagService.getAllTags();
        model.addAttribute("allTagList", allTags);
        return "Home/index";
    }
}
