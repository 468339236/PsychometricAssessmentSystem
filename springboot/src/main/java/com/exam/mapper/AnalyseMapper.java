package com.exam.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exam.entity.AnalyseResult;
import com.exam.entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@Mapper
//@MapperScan("com.exam.mapper")
public interface AnalyseMapper {
    @Select("SELECT " +
            "s.scoreId, " +
            "s.examCode, " +
            "s.studentId, " +
            "s.etScore, " +
            "s.answerDate, " +
            "e.source " +
            "FROM score s " +
            "LEFT JOIN exam_manage e ON s.examCode = e.examCode " +
            "WHERE s.studentId = #{studentId} "+
            "ORDER BY s.scoreId DESC")
    List<AnalyseResult> findScoresWithExamName( String studentId);
}
