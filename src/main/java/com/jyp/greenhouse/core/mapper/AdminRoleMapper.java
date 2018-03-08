package com.jyp.greenhouse.core.mapper;

import com.jyp.greenhouse.core.entity.Adminroleadmin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by oplsu on 2017/1/18.
 */
@Repository
public interface AdminRoleMapper {

    @ResultType(Adminroleadmin.class)
    @Select({"select * from admin_role where admin_id=#{adminid}"})
    List<Adminroleadmin> list(@Param("adminid") String adminid);

    @Delete({"delete from admin_role where admin_id=#{adminid} and role=#{role}"})
    int delete(@Param("adminid") String adminid, @Param("role") String role);

    @Delete({"delete from admin_role where admin_id=#{adminid}"})
    int deleteByAdminid(@Param("adminid") String adminid);

    @Insert({"insert into admin_role(admin_id,role) values(#{adminid},#{role})"})
    int add(@Param("adminid") String adminid, @Param("role") String role);
}
