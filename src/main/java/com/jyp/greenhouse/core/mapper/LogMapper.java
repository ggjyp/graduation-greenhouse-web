package com.jyp.greenhouse.core.mapper;

import com.jyp.greenhouse.core.entity.Log;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogMapper {
    int insert(Log record);
    List<Log> list(
            @Param("operateFrom") String operateFrom,
            @Param("operateTo") String operateTo,
            @Param("starttime") int starttime,
            @Param("endtime") int endtime,
            @Param("offset") int offset,
            @Param("limit") int limit
    );
    int count(
            @Param("operateFrom") String operateFrom,
            @Param("operateTo") String operateTo,
            @Param("starttime") int starttime,
            @Param("endtime") int endtime
    );

    int deleteByPrimaryKey(Integer id);
    Log selectByPrimaryKey(Integer id);
}
