package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Kunyueyeuser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface KunyueyeuserMapper extends BaseMapper<Kunyueyeuser> {

    List<Kunyueyeuser> selectUserByNameAndPassword(String name, String password);
}
