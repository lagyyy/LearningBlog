package com.imeiman.ssm.blog.interceptor;

import com.imeiman.ssm.blog.domain.entity.Options;
import com.imeiman.ssm.blog.service.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class HomeResourceInterceptor implements HandlerInterceptor {
    @Autowired
    private OptionsService optionsService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Options options = optionsService.getOptions();
        System.out.println(options);
        httpServletRequest.setAttribute("options", options);

        List<Integer> siteBasicStatistics = new ArrayList<>();
        Integer articleCount = articleService.getArticleCount();
        siteBasicStatistics.add(articleCount);

        Integer allByArticleCommentCountInteger = articleService.getAllByArticleCommentCountInteger();
        siteBasicStatistics.add(allByArticleCommentCountInteger);

        Integer categoryCount = categoryService.getCount();
        siteBasicStatistics.add(categoryCount);

        Integer tagCount = tagService.getTagCount();
        siteBasicStatistics.add(tagCount);

        Integer linkCount = linkService.getLinkCount();
        siteBasicStatistics.add(linkCount);


        Integer articleViewCountInteger = articleService.getArticleViewCountInteger();
        siteBasicStatistics.add(articleViewCountInteger);

        httpServletRequest.setAttribute("siteBasicStatistics", siteBasicStatistics);
        Date articleLastUpdateTimeDate = articleService.getArticleLastUpdateTimeDate();
        httpServletRequest.setAttribute("articleUpdateTime",articleLastUpdateTimeDate);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
