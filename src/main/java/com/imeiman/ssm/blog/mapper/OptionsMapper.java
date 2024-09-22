package com.imeiman.ssm.blog.mapper;

import com.imeiman.ssm.blog.domain.entity.Options;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OptionsMapper {
    Options getOptions();
}
