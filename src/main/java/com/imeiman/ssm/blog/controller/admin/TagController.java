package com.imeiman.ssm.blog.controller.admin;

import com.imeiman.ssm.blog.domain.entity.Tag;
import com.imeiman.ssm.blog.domain.vo.TagVo;
import com.imeiman.ssm.blog.mapper.TagMapper;
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
 * Time: 11:23
 * Description:
 */

@Controller
@RequestMapping("/admin/tag")
public class TagController {
    @Autowired
    private TagMapper tagMapper;

    @RequestMapping("")
    public String toTagIndex(Model model){
        List<TagVo> allTags = tagMapper.getAllTagsAndArticleCount();
        model.addAttribute("tagList",allTags);
        return "Admin/Tag/index";
    }

    @RequestMapping("delete/{tagId}")
    public String deleteById(@PathVariable Integer tagId,Model model){
        try {
            int i = tagMapper.deleteById(tagId);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<TagVo> allTags = tagMapper.getAllTagsAndArticleCount();
        model.addAttribute("tagList",allTags);
        return "Admin/Tag/index";
    }

    @RequestMapping(value = "insertSubmit",method = RequestMethod.POST)
    public String insert (Model model,Tag tag){
        int insert = tagMapper.insert(tag);
        if (insert==1){
            List<TagVo> allTags = tagMapper.getAllTagsAndArticleCount();
            model.addAttribute("tagList",allTags);
            return "Admin/Tag/index";
        }
        return "";
    }
    @RequestMapping("edit/{tagId}")
    public String toTagEditPage(@PathVariable Integer tagId,Model model){
        Tag byId = tagMapper.getById(tagId);
        model.addAttribute("tag",byId);
        return "Admin/Tag/edit";
    }


    @RequestMapping(value = "editSubmit",method = RequestMethod.POST)
    public String editSubmit (Model model,Tag tag){
        try {
            int insert = tagMapper.update(tag);
            if (insert==1){
                List<TagVo> allTags = tagMapper.getAllTagsAndArticleCount();
                model.addAttribute("tagList",allTags);
                return "Admin/Tag/index";
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
