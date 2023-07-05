package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Sk;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface SkMapper extends BaseMapper<Sk> {
    List<Sk> selectByMap(@Param("sex") String sex, @Param("userName") String userName,@Param("num") String num,@Param("zb") String zb);
    List<Sk> selectListByUserid();
    List<Sk> selectByUserId(String id);
}
