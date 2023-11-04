package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Kucunuser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KucunuserMapper extends BaseMapper<Kucunuser> {

    List<Kucunuser> selectUserByNameAndPassword(String name, String password);
}
