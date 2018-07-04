package com.shandong.human.resource.service.sys.impl;

import com.shandong.human.resource.domain.SurveyTime;
import com.shandong.human.resource.mapper.sys.SurveyTimeMapper;
import com.shandong.human.resource.service.sys.SurveyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 调查期Service实现类
 * <p>
 * Author: syc <522560298@qq.com>
 * Date: 3/20/16 下午1:21
 */
@Service
public class SurveyTimeServiceImpl implements SurveyTimeService {

    @Autowired
    SurveyTimeMapper surveyTimeMapper;

    /**
     * 获取所有调查时间
     *
     * @return
     */
    public ArrayList<SurveyTime> getAllSurveyTime() {
        return surveyTimeMapper.getAllSurveyTime();
    }

    /**
     * 向表中插入一个时间
     *
     * @param time
     * @return
     */
    public Integer insertSurveyTime(SurveyTime time) {
        return surveyTimeMapper.insertSurveyTime(time);
    }

    /**
     * 通过id获得所有调查期
     *
     * @param id
     * @return
     */
    public SurveyTime getAllSurveyTimeById(int id) {
        return surveyTimeMapper.getAllSurveyTimeById(id);
    }

    /**
     * 获得调查期的总数
     *
     * @return
     */
    public Integer getSurveyTimeCount() {
        return surveyTimeMapper.getSurveyTimeCount();
    }
}
