package com.jyp.greenhouse.service;

import com.jyp.greenhouse.core.entity.AutoCtrlParam;
import com.jyp.greenhouse.core.mapper.AutoCtrlParamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   : jyp
 * Date     : 2017-05-03 15:30
 * Describe :
 */
@Service
public class AutoCtrlParamService {
    @Autowired
    AutoCtrlParamMapper autoCtrlParamMapper;

    public int delete(String id) {
        return autoCtrlParamMapper.deleteByPrimaryKey(id);
    }

    public int update(AutoCtrlParam autoCtrlParam) {
        return  autoCtrlParamMapper.updateByPrimaryKey(autoCtrlParam);
    }

    public AutoCtrlParam findById(String id) {
        return autoCtrlParamMapper.selectByPrimaryKey(id);
    }

    public int insert(AutoCtrlParam autoCtrlParam) {
        return autoCtrlParamMapper.insert(autoCtrlParam);
    }

    public int toUsing(String modifyDate, String id) {
        if (autoCtrlParamMapper.toUnUsing() > 0) {
            return autoCtrlParamMapper.toUsing(modifyDate, id);
        }
        return 0;
    }

    public List<AutoCtrlParam> list(String paramName, int page, int pageshow) {
        List<AutoCtrlParam> records = new ArrayList<>(pageshow);
        records = autoCtrlParamMapper.list(paramName, (page - 1) * pageshow, pageshow);
        return records;
    }

    public int count(String paramName) {
        return autoCtrlParamMapper.count(paramName);
    }
}
