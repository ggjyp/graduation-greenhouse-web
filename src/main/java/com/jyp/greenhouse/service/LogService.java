package com.jyp.greenhouse.service;

import com.jyp.greenhouse.core.entity.Log;
import com.jyp.greenhouse.core.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   : jyp
 * Date     : 2017-05-03 14:21
 * Describe :
 */
@Service
public class LogService {
    @Autowired
    private LogMapper logMapper;

    public int insert(Log log) {
        return logMapper.insert(log);
    }

    public List<Log> list(String operateFrom, String operateTo, int starttime, int endtime, int page, int pageshow) {
        List<Log> records = new ArrayList<>(pageshow);
        records = logMapper.list(operateFrom, operateTo, starttime, endtime, (page - 1) * pageshow, pageshow);
        return records;
    }

    public int count(String operateFrom, String operateTo, int starttime,int endtime) {
        return logMapper.count(operateFrom, operateTo, starttime, endtime);
    }
}
