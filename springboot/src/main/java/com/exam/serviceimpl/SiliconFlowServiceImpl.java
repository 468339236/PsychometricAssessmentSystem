package com.exam.serviceimpl;

import com.exam.service.SiliconFlowService;
import kong.unirest.HttpResponse; // 确保使用的是 Unirest 的 HttpResponse
import kong.unirest.Unirest;

public class SiliconFlowServiceImpl implements SiliconFlowService {
    private  SiliconFlowService siliconFlowService;
    private static final String API_URL = "https://api.siliconflow.cn/v1/chat/completions";
    private static final String API_KEY = "<your_api_token>";

    /**
     * 调用硅基流动 API 进行分析
     *
     * @param prompt 用户输入的 Prompt
     * @return 分析结果
     */
    public String analyze(String prompt) {
        try {
            // 调用 Unirest 发送 POST 请求
            HttpResponse<String> response = Unirest.post(API_URL)
                    .header("Authorization", "Bearer " + API_KEY)
                    .header("Content-Type", "application/json")
                    .body("{\n" +
                            "  \"model\": \"Qwen/Qwen2.5-VL-72B-Instruct\",\n" +
                            "  \"stream\": false,\n" +
                            "  \"max_tokens\": 512,\n" +
                            "  \"temperature\": 0.7,\n" +
                            "  \"top_p\": 0.7,\n" +
                            "  \"top_k\": 50,\n" +
                            "  \"frequency_penalty\": 0.5,\n" +
                            "  \"n\": 1,\n" +
                            "  \"stop\": [],\n" +
                            "  \"messages\": [\n" +
                            "    {\"role\": \"user\", \"content\": \"" + prompt + "\"}\n" +
                            "  ]\n" +
                            "}")
                    .asString();

            // 检查响应状态码
            if (response.getStatus() == 200) {
                return response.getBody(); // 返回分析结果
            } else {
                throw new RuntimeException("API 调用失败: " + response.getStatusText());
            }
        } catch (Exception e) {
            throw new RuntimeException("API 调用异常", e);
        }
    }
}