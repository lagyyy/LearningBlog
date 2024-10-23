package com.imeiman.ssm.blog.controller.admin;

import com.imeiman.ssm.blog.domain.entity.Category;
import com.imeiman.ssm.blog.domain.vo.CategoryAndArticleCount;
import com.imeiman.ssm.blog.mapper.CategoryMapper;
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
 * Date: 2024/10/22
 * Time: 11:22
 * Description:
 */

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    @RequestMapping("")
    public String index(Model model){
        try {
            List<CategoryAndArticleCount> allAndArticleCounts = categoryMapper.getAllAndArticleCounts();
            model.addAttribute("categoryList",allAndArticleCounts);
            return "/Admin/Category/index";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
    @RequestMapping("/delete/{categoryId}")
    public String deleteById(@PathVariable Integer categoryId,Model model){
        try {
            int i = categoryMapper.deleteById(categoryId);
            if (i == 1){
                List<CategoryAndArticleCount> allAndArticleCounts = categoryMapper.getAllAndArticleCounts();
                model.addAttribute("categoryList",allAndArticleCounts);
                return "/Admin/Category/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "insertSubmit",method = RequestMethod.POST)
    public String insertOne(Category category,Model model){
        try {
            int i = categoryMapper.insertOne(category);
            if (i==1){
                List<CategoryAndArticleCount> allAndArticleCounts = categoryMapper.getAllAndArticleCounts();
                model.addAttribute("categoryList",allAndArticleCounts);
                return "/Admin/Category/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping("/edit/{categoryId}")
    public String toEditPage(@PathVariable Integer categoryId,Model model){
        try {
            Category byCategoryId = categoryMapper.getById(categoryId);
            if (byCategoryId !=null){
                model.addAttribute("category",byCategoryId);
                return "/Admin/Category/edit";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "editSubmit",method = RequestMethod.POST)
    public String editSubmit(Category category,Model model){
        try {
            int i = categoryMapper.update(category);
            if (i==1){
                List<CategoryAndArticleCount> allAndArticleCounts = categoryMapper.getAllAndArticleCounts();
                model.addAttribute("categoryList",allAndArticleCounts);
                return "/Admin/Category/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }


}
