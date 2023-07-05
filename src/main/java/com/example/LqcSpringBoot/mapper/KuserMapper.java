package com.example.LqcSpringBoot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.LqcSpringBoot.model.Kuser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KuserMapper extends BaseMapper<Kuser> {
    //小程序根据编号
    List<Kuser> selectByNum(String num);
    //小程序根据真实名字查询
    List<Kuser> selectByName(String name);
    //根据name 或 num 查询
    List<Kuser> selectKuserByNameAndNum(@Param("name") String name,@Param("num") String num,@Param("phone") String phone);
    //删除
    Integer deleteKuserById(String id);
    //清空表
    Integer cleanTable();
    //编辑查询回显数据功能
    List<Kuser> selectKuserById(String id);
    //验证重复数据（导入的时候）
    List<Kuser> selectKuserByNum(String num);
    //查询待审核的数据
    List<Kuser>  selectListByStatus();
    //查询已经审核的数据
    List<Kuser> selectListisStatus();
    //电话号码分组
    List<Kuser>  selectListGroupByPhone();
    //男女比例
    List<Kuser> selectListGroupBySex();
    //注册量
    List<Kuser> selectListGroupByDate();
    //一键审核
    Integer updateAll();
    int updateKuser(@Param("cm") String cm,@Param("dyname") String dyname,@Param("name") String name,@Param("num") String num,@Param("id") String id,@Param("sex") String sex,@Param("phone") String phone);

}
