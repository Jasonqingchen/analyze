package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Rctable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 入仓
 */
@Mapper
public interface RcMapper extends BaseMapper<Rctable> {
    List<Rctable> selectList();
    Rctable  selectBYPnumber(String rccount);//商品入仓时 验证是否已经存在该商品编号

    List<Rctable> selectKuserBysameting(@Param("pnumber") String pnumber, @Param("sshg") String sshg, @Param("start") String start, @Param("end") String end);

    //删除
    Integer deleteKuserById(String id);
    List<Rctable> selectListByGnumber(String Gnumber);//关联查询

    Map selectcountBYtable();

}
