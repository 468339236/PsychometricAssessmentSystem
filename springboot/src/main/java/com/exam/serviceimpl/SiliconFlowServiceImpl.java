package com.exam.serviceimpl;

import com.exam.entity.AnalyseResult;
import com.exam.service.SiliconFlowService;
import kong.unirest.HttpResponse; // 确保使用的是 Unirest 的 HttpResponse
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.StringEscapeUtils.escapeJson;
@Service
public class SiliconFlowServiceImpl implements SiliconFlowService {
    private  SiliconFlowService siliconFlowService;
    private static final String API_URL = "https://api.siliconflow.cn/v1/chat/completions";
    private static final String API_KEY = "sk-kpaeonghxzmjhehmghkanodvwlpgvuikmcedmmkvihxpuvla";

    /**
     * 调用硅基流动 API 进行分析
     *
     * @param prompt 用户输入的 Prompt
     * @return 分析结果
     */
    public String analyseResultsWithSiliconFlow(List<AnalyseResult> results) {
        try {
            // 构造请求体
            String requestBody = constructRequestBody(results);

            // 发送 POST 请求
            HttpResponse<String> response = Unirest.post(API_URL)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .body(requestBody)
                    .asString();

            // 检查响应状态码
            if (response.getStatus() == 200) {
                // 返回响应内容
                return response.getBody();
            } else {
                throw new RuntimeException("API request failed with status code: " + response.getStatus());
            }
        } catch (UnirestException e) {
            throw new RuntimeException("Failed to send request to SiliconFlow API", e);
        }
    }
    private String constructRequestBody(List<AnalyseResult> results) {
        // 将 AnalyseResult 列表转换为适合 API 的输入格式
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("请根据以下要求和输入数据，直接分点列出结论，不要包含任何分析过程或解释。\n" +
                "要求：\n" +
                "1. 数据未去重：每个学生每日每类测试仅保留最新记录。\n" +
                "2. 自主过滤无效数据：得分小于0或早于2025年的记录应被过滤。\n" +
                "3. 关键字段说明：\n" +
                "   - `etScore` 是抑郁测试得分，范围为 0-100，分数越高风险越低。\n" +
                "   - `source` 固定为“抑郁测试”。\n" +
                " 需要回答的问题：\n" +
                "1. 抑郁得分随时间的变化趋势。\n" +
                "2. 是否存在单日多次测试的异常模式（已去重，可忽略）。\n" +
                "3. 根据国际标准（如 PHQ-9），评估学生的抑郁风险等级。");
        for (AnalyseResult result : results) {
            contentBuilder.append(String.format(
                    "Score ID: %s, Exam Code: %s, Student ID: %s, Score: %.2f, Date: %s, Source: %s\n",
                    result.getScoreId(),
                    result.getExamCode(),
                    result.getStudentId(),
                    result.getEtScore(),
                    result.getAnswerDate(),
                    result.getSource()
            ));
        }

        // 构造最终的请求体 JSON
        return "{\n" +
                "  \"model\": \"Qwen/QwQ-32B\",\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": \"" + escapeJson(contentBuilder.toString()) + "\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"stream\": false,\n" +
                "  \"max_tokens\": 512,\n" +
                "  \"temperature\": 0.7,\n" +
                "  \"top_p\": 0.7,\n" +
                "  \"frequency_penalty\": 0.5,\n" +
                "  \"n\": 1\n" +
                "}";
    }
    private String escapeJson(String input) {
        return input.replace("\"", "\\\"").replace("\n", "\\n");
    }



}