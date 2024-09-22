package com.imeiman.ssm.blog.service.impl;

import com.imeiman.ssm.blog.domain.entity.Options;
import com.imeiman.ssm.blog.mapper.OptionsMapper;
import com.imeiman.ssm.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsMapper optionsMapper;


    @Override
    public Options getOptions() {
        Options options = optionsMapper.getOptions();
        return options;
    }
}
