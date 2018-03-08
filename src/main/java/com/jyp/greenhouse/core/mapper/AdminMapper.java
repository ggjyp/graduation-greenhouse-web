package com.jyp.greenhouse.core.mapper;

import com.jyp.greenhouse.core.entity.Admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMapper {
    int insert(Admin record);

    int deleteByPrimaryKey(String adminId);

    int update(Admin record);
    int updateLogininfo(Admin admin);

    Admin selectByPrimaryKey(String adminId);
    Admin selectByName(String adminName);
    List<Admin> list();

    Admin login(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);


}