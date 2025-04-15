<!--用于分析学生的测试情况-->
<!--sequenceDiagram-->
<!--participant 用户界面-->
<!--participant SpringBoot控制器-->
<!--participant 数据库-->
<!--participant LLM服务-->

<!--用户界面->>SpringBoot控制器: POST /analyze/{studentId}-->
<!--SpringBoot控制器->>数据库: 查询学生成绩+心理问卷-->
<!--数据库&ndash;&gt;>SpringBoot控制器: 返回学生数据-->
<!--SpringBoot控制器->>SpringBoot控制器: 生成对比统计数据-->
<!--SpringBoot控制器->>LLM服务: 发送结构化Prompt-->
<!--LLM服务&ndash;&gt;>SpringBoot控制器: 返回分析报告-->
<!--SpringBoot控制器&ndash;&gt;>用户界面: 渲染分析结果-->
<template>
  <div id="message">
    <div class="title">分析细节</div>
    <div class="wrapper">
      <div class="title1">
        <el-input
          placeholder="输入id进行查询"
          v-model="studentId"
          clearable>
        </el-input>
      </div>
      <div class="content">
      <div class="btn">
        <el-button type="primary" @click="submitAnalysis()">提交分析</el-button>
      </div>
      </div>
      <div v-if="result" class="result-content" >
        <p>分析结果：{{ result }}</p>
      </div>

    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      studentId: '',
      result: null,

    };

  },
  // computed: {
  //   resContent() {
  //     if (!this.result || !this.result.content) return '';
  //     // 将换行符替换为 <br>
  //     return this.result.content.split('').map(item => item === '\n' ? '<br>' : item).join('');
  //   }
  // },
  computed: {
    resContent() {
      if (!this.result || !this.result.content) return '';
      // 将换行符替换为 <br>
      return this.result.content.split('').map(item => item === '\n' ? '<br>' : item).join('');
    }
  },


  methods: {
    async submitAnalysis() {
      if (!this.studentId) {
        this.$message({
          type: 'error',
          message: '学生 ID 不能为空',
        });
        return;
      }

      try {
        const response = await axios.post('/api/analyze', { studentId: this.studentId });
        this.result = response.data;
        console.log(typeof response.data); // 输出 "object" 或 "string"
        const jsonStartIndex = this.result.indexOf('{');
        const plainText = this.result.substring(0, jsonStartIndex).trim(); // 普通文本部分
        const jsonDataString = this.result.substring(jsonStartIndex); // JSON 数据部分
        console.log(jsonDataString);
        const jsonData = JSON.parse(jsonDataString);
        const choices = jsonData.choices;

        if (choices && choices.length > 0) {
          this.result = choices[0].message.content; // 获取 content
        } else {
          this.result = '未找到分析结果';
        }
      } catch (error) {
        console.error('分析失败:', error);
        this.$message({
          type: 'error',
          message: '分析失败，请稍后再试',
        });
      }
    }
  },
};
</script>
<style lang="less" scoped>
.pagination {
  display: flex;
  justify-content: center;
}
.result-content {
  white-space: pre-line;
  line-height: 1.6;
  /* 如果需要保留缩进 */
  /* white-space: pre-wrap; */
}
#message {
  width: 980px;
  margin: 0 auto;
}
.title {
  margin: 20px;
}
.content {
  padding: 20px 0px;
}
#message  {
  .btn {
    padding-bottom: 20px;
  }
  .all {
    .date {
      color: rgb(80, 157, 202);
      line-height: 45px;
      font-size: 13px;
    }
    .list {
      background-color: #eee;
      padding:10px;
      border-radius: 4px;
      margin: 10px 0px;
      position: relative;
      transition: all .3s ease;
      .title {
        color: #5f5f5f;
        margin: 0px;
        font-size: 13px;
        line-height: 30px;
      }
      .content {
        padding: 0px;
      }
      .icon-untitled33 {
        font-size: 13px;
        margin-right: 4px;
      }
      .icon-date {
        font-size: 13px;
        margin-right: 4px;
        color: rgb(80, 157, 202);
      }
      .replay {
        position: absolute;
        right: 30px;
        bottom: 15px;
        color: #53baea;
        cursor: pointer;
        transition: all .3s ease;
      }
      .comment {
        margin:-7px 0px;
        padding-bottom: 12px;
        font-size: 13px;
        color: #28b2b4;
        i {
          margin-right: 4px;
        }
      }
    }
  }
}
#message .wrapper {
  background-color: #fff;
  padding: 20px;

}
</style>
