package com.imeiman.ssm.blog.controller.admin;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.imeiman.ssm.blog.domain.entity.Page;
import com.imeiman.ssm.blog.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: NGZ
 * Date: 2024/10/23
 * Time: 11:49
 * Description:
 */

@Controller
@RequestMapping("/admin/page")
public class PageController {

    @Autowired
    private PageMapper pageMapper;

    @RequestMapping("")
    public String indexPage(Model model){
        try {
            List<Page> allPages = pageMapper.getAllPages();
            model.addAttribute("pageList",allPages);
            return "Admin/Page/index";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("insert")
    public String toInsertPage(){
        return "/Admin/Page/insert";
    }

    @RequestMapping("/edit/{pageId}")
    public String editPage(Model model, @PathVariable Integer pageId){
        try {
            Page page = pageMapper.getById(pageId);
            model.addAttribute("page",page);
            return "Admin/Page/edit";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/editSubmit")
    public String editPage(Model model, Page page){
        try {
            page.setPageUpdateTime(DateTime.now());
            boolean update = pageMapper.update(page);
            if (update){
                List<Page> allPages = pageMapper.getAllPages();
                model.addAttribute("pageList",allPages);
                return "Admin/Page/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping(value = "insertSubmit",method = RequestMethod.POST)
    public String insertSubmit(Model model,Page page){
        try {
            page.setPageCreateTime(DateTime.now());
            page.setPageUpdateTime(DateTime.now());
            int i = pageMapper.insertOne(page);
            if (i==1){
                List<Page> allPages = pageMapper.getAllPages();
                model.addAttribute("pageList",allPages);
                return "Admin/Page/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("delete/{pageId}")
    public String deleteById(Model model,@PathVariable Integer pageId){
        try {
            Integer integer = pageMapper.deleteById(pageId);
            if (integer==1){
                List<Page> allPages = pageMapper.getAllPages();
                model.addAttribute("pageList",allPages);
                return "Admin/Page/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
