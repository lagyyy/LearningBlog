package com.imeiman.ssm.blog.service.impl;

import com.imeiman.ssm.blog.domain.entity.Link;
import com.imeiman.ssm.blog.mapper.LinkMapper;
import com.imeiman.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public List<Link> getAllLink() {
        List<Link> allLink = linkMapper.getAllLink();
        return allLink;
    }
}
