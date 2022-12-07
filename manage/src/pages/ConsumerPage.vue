<template>
  <div>
    <el-form style="float: left;margin-left: 100px" :inline="true"  class="demo-form-inline"  ref="QuestionsFormFormRef" :model="QuestionsForm">
      <el-form-item label="提问人名称" prop="creater_name">
        <el-input v-model="QuestionsForm.creater_name"  placeholder="提问人名称"   clearable></el-input>
      </el-form-item>
      <el-form-item label="专家名称" prop="experts_name" style="margin-left: 30px">
        <el-input v-model="QuestionsForm.experts_name" placeholder="专家名称"  clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="margin-left: 120px" @click="select">查询</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>

    <el-table
      :data="Questions.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%">
      <el-table-column
        label="序号"
        type="index"
        :index="Nindex"
        width="150">
      </el-table-column>
      <el-table-column
        prop="creater_name"
        label="提问人名称"
        width="150">
      </el-table-column>
      <el-table-column
        prop="experts_name"
        label="专家名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="create_time"
        label="创建时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="question_content"
        label="问题内容"
        width="180">
      </el-table-column>
      <el-table-column
        prop="answer_content"
        label="回答内容"
        width="180">
      </el-table-column>
      <el-table-column
        prop="qid"
        label="问题id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="answer_time"
        label="回答时间"
        width="180">
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="deleteQuestion(scope.row.qid)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>



    <el-pagination align='center'
                   @size-change="handleSizeChange"
                   @current-change="handleCurrentChange"
                   :current-page="currentPage"
                   :page-sizes="[1,5,10,20]"
                   :page-size="pageSize"
                   layout="total, sizes, prev, pager, next, jumper"
                   :total="Questions.length">
    </el-pagination>


  </div>
</template>

<script>
export default {
  name: 'ConsumerPage',
  data(){
    return{
      currentPage:1,
      totalCount:0,
      pageSize:10,
      QuestionsForm:{
        creater_name:"",
        experts_name:"",
      },
      selectPost:"",
      Questions:[],
    }
  },
  methods:{
    Nindex(index) {
      const page = this.currentPage // 当前页码
      const pagesize = this.pageSize // 每页条数
      return index  +1+ (page - 1) * pagesize
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.currentPage = 1;
      this.pageSize = val;
    },
    //当前页改变时触发 跳转其他页
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.currentPage = val;
    },
    resetForm() {
      this.$refs.QuestionsFormFormRef.resetFields()
    },
    findInterlocution2(){
      this.$http.post('http://localhost:8888/Interlocution/findInterlocution2',
        {"creater_name": this. QuestionsForm.creater_name},
        {emulateJSON: true}
      ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
            this.Posts = res.data.data;
          } else {
            alert(res.data.msg);
          }
        }
      )
    },
    findInterlocution(){
      if (!isNaN(this.QuestionsForm.creater_id)) {
        this.$http.post('http://localhost:8888/Interlocution/findInterlocution',
          {"experts_name": this. QuestionsForm.experts_name},
          {emulateJSON: true}
        ).then(function (res) {
            if (res.data.code == 200) {
              alert(res.data.msg);
              this.Posts = res.data.data;
            } else {
              alert(res.data.msg);
            }
          }
        )
      } else {
        this.$message("请输入数字");
      }
    },
    findAllInter() {
      this.$http.post('http://localhost:8888/Interlocution/findAllInter',
      ).then(function (res) {
        this.Questions = res.data.data;
      })
    },
    deleteByQid(qid){
      this.$confirm('是否确认删除该提问?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.QuestionsForm.qid= qid;
        this.$http.post('http://localhost:8888/Question/deleteByQid',
          {"qid": this.QuestionsForm.qid},
          {emulateJSON: true}
        ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
          }
        })
        this.$router.go(0);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    select(){
      if((this.QuestionsForm.experts_name==null||this.QuestionsForm.experts_name=='')&&(this.QuestionsForm.creater_name!=null&&this.QuestionsForm.creater_name!='')){
        this.findInterlocution2();
      }
      else if((this.QuestionsForm.experts_name!=null&&this.QuestionsForm.experts_name!='')&&(this.QuestionsForm.creater_name==null||this.QuestionsForm.creater_name=='')){
        this.findInterlocution();
      }
      else if((this.QuestionsForm.experts_name==null||this.QuestionsForm.experts_name=='')&&(this.QuestionsForm.creater_name==null||this.QuestionsForm.creater_name=='')){
        this.$message("请输入");
      }
      else
        this.findInterlocution2();
    }
  },
  created() {
    this.findAllInter();
  }
}
</script>





















<style scoped>

</style>
