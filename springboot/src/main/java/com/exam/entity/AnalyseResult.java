package com.exam.entity;

import java.security.Timestamp;
import java.util.Date;

public class AnalyseResult {
    private String scoreId;       // 成绩 ID
    private String examCode;      // 考试代码
    private String studentId;      // 学生 ID
    private Double etScore;        // 考试分数
    private Date answerDate;       // 答题日期
    private String source;         // 数据来源


    public String getScoreId() {
        return scoreId;
    }

    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public String getExamCode() {
        return examCode;
    }

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Double getEtScore() {
        return etScore;
    }

    public void setEtScore(Double etScore) {
        this.etScore = etScore;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }


    @Override
    public String toString() {

        return "AnalyseResult{" +
                "scoreId=" + scoreId +
                ", examCode=" + examCode +
                ", studentId='" + studentId + '\'' +
                ", etScore=" + etScore +
                ", answerDate='" + answerDate + '\'' +
                ", source='" + source + '\'' +
                '}';
    }


}
