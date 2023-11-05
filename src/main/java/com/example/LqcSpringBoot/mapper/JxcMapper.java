package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Cctable;
import com.example.LqcSpringBoot.model.Jxctable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * jxc
 *
 */
@Mapper
public interface JxcMapper extends BaseMapper<Jxctable> {

    Jxctable selectByPnamber(String Pnumber);
    List<Jxctable> selectAll();

    List<Jxctable> selectKuserBysameting(@Param("pnumber") String pnumber, @Param("start") String start, @Param("end") String end);


}
