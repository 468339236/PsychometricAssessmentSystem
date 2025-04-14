package com.exam;

import com.exam.entity.AnalyseResult;
import com.exam.mapper.AnalyseMapper;
import com.exam.service.AnalyseService;
import com.exam.serviceimpl.AnalyseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalyseMapperTest {

    @Autowired
    private AnalyseService analyseMapper;

    @Test
    public void testFindScoresWithExamName() { // 修改为 public
        // 调用 Mapper 方法
        List<AnalyseResult> results = analyseMapper.findScoresWithExamName("20240001");

        // 断言结果不为空
        System.out.println(results);
        assertFalse(results.isEmpty());
    }
}