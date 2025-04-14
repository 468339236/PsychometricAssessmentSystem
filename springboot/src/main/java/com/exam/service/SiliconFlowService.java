package com.exam.service;

public interface SiliconFlowService {
    /**
     * 调用硅基流动 API 进行分析
     *
     * @param prompt 用户输入的 Prompt
     * @return 分析结果（JSON 格式字符串）
     */
    String analyze(String prompt);

}
