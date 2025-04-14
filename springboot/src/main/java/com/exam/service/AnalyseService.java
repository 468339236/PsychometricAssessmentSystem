package com.exam.service;

import com.exam.entity.AnalyseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface AnalyseService {

//    public List<AnalyseResult> findScoresWithExamName(String studentId);
    List<AnalyseResult> findScoresWithExamName(String studentId);

}
