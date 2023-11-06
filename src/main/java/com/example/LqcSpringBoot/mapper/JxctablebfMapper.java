package com.example.LqcSpringBoot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Jxctable;
import com.example.LqcSpringBoot.model.Jxctablebf;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 出仓库
 *
 */
@Mapper
public interface JxctablebfMapper extends BaseMapper<Jxctablebf> {

    List<Jxctablebf> selectKuserBysameting(@Param("start") String start, @Param("end") String end);

    List<Jxctablebf> selectAll();
}
