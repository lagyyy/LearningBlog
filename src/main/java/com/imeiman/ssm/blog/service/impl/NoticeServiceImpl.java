package com.imeiman.ssm.blog.service.impl;

import com.imeiman.ssm.blog.domain.entity.Notice;
import com.imeiman.ssm.blog.mapper.NoticeMapper;
import com.imeiman.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeMapper noticeMapper;


    @Override
    public List<Notice> getAllNotice() {
        List<Notice> allNotice = noticeMapper.getAllNotice();
        return allNotice;
    }
}
