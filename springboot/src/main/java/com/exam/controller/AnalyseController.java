package com.exam.controller;

import com.exam.entity.ApiResult;
import com.exam.service.SiliconFlowService;
import com.exam.util.ApiResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AnalyseController {

    @PostMapping("/analyze")
    public ResponseEntity<String> analyze(@RequestBody Map<String, Object> requestData) {
        System.out.println("runrunrun");
        String studentId = (String) requestData.get("studentId");

        // 非空校验
        if (studentId == null || studentId.isEmpty()) {
            return ResponseEntity.badRequest().body("学生 ID 不能为空");
        }

        // 模拟处理逻辑
        System.out.println("接收到的学生 ID: " + studentId);

        // 返回结果
        return ResponseEntity.ok("获取到学生 ID: " + studentId);
    }
}