package com.jyp.greenhouse.core.mapper;

import com.jyp.greenhouse.core.entity.AutoCtrlParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoCtrlParamMapper {
    int deleteByPrimaryKey(String id);

    int insert(AutoCtrlParam record);

    AutoCtrlParam selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AutoCtrlParam record);

    int updateByPrimaryKey(AutoCtrlParam record);

    /**
     * 更改自动控制参数的启用状态
     * @param modifyDate 启用时间
     * @param id 自动控制参数ID
     * @return
     */
    int toUsing(@Param("modifyDate") String modifyDate, @Param("id") String id);
    int toUnUsing();

    List<AutoCtrlParam> list(
            @Param("paramName") String paramName,
            @Param("offset") int offset,
            @Param("limit") int limit
    );
    int count(
            @Param("paramName") String paramName
    );
}