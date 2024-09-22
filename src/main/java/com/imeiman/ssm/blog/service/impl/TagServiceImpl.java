package com.imeiman.ssm.blog.service.impl;

import com.imeiman.ssm.blog.domain.entity.Tag;
import com.imeiman.ssm.blog.mapper.TagMapper;
import com.imeiman.ssm.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 当你将@Service和@Slf4j一起用于一个类中时，你既告诉了Spring这是一个服务层组件，又方便地获得了一个日志记录器，从而可以方便地记录业务逻辑执行过程中的各种信息。
 * */


@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> getAllTags() {
        List<Tag> allTags = null;
        try {
             allTags = tagMapper.getAllTags();
        }catch (Exception e){
            e.printStackTrace();
            log.error("获取标签失败, cause:{}", e);
        }

        return allTags;
    }
}
