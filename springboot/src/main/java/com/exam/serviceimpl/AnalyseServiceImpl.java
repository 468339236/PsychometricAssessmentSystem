package com.exam.serviceimpl;

import com.exam.entity.AnalyseResult;
import com.exam.entity.Score;
import com.exam.mapper.AnalyseMapper;
import com.exam.service.AdminService;
import com.exam.service.AnalyseService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AnalyseServiceImpl implements AnalyseService {
    @Autowired
    private  AnalyseMapper analyseMapper;


//    public AnalyseServiceImpl(AnalyseMapper analyseMapper) {
//        this.analyseMapper = analyseMapper;
//    }

    @Override
    public List<AnalyseResult> findScoresWithExamName(String studentId) {
        // 调用 Mapper 方法，而非自身定义
        return analyseMapper.findScoresWithExamName(studentId);
    }

}
