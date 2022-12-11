<template>
<div style="margin: 20px 20px 0 20px">

  <el-form style="float: left;margin-left: 50px" :inline="true"  class="demo-form-inline"  ref="ReportFormRef" :model="ReportForm">
    <el-form-item label="举报者" prop="username1">
      <el-input  style="width: 150px;" v-model="ReportForm.username1"  placeholder="举报者"  clearable></el-input>
    </el-form-item>

    <el-form-item label="被举报者" prop="username2"  style="margin-left: 30px">
      <el-input style="width: 150px;" v-model="ReportForm.username2"  placeholder="被举报者"   clearable></el-input>
    </el-form-item>

    <el-form-item label="时间排序" prop="sort" style="margin-left: 30px">
      <el-select  v-model="ReportForm.sort" placeholder="时间排序" style="width: 150px;">
      <el-option label="正序" value="正序"></el-option>
      <el-option label="倒序" value="倒序"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item label="处理进度" prop="progress" style="margin-left: 30px" >
      <el-select v-model="ReportForm.progress" placeholder="进度" style="width: 150px;">
        <el-option label="已处理" value="1"></el-option>
        <el-option label="未处理" value="0"></el-option>
        <el-option label="全部"  value="全部"></el-option>
      </el-select>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" style="margin-left: 50px" @click="select">查询</el-button>
      <el-button @click="resetForm">重置</el-button>
    </el-form-item>

  </el-form>

  <el-dialog
    title="举报内容"
    :visible.sync="dialogVisible"
    width="50%"
    center
    :before-close="handleClose">
    <span>{{this.content}}</span>
    <span slot="footer" class="dialog-footer">
      <el-button  type="primary" @click="dialogVisible = false" v-on:click="">不处理</el-button>
      <el-button  type="warning" @click="dialogVisible = false" v-on:click="dispose();deletecomment()">删除</el-button>
      <el-button  type="danger" @click="ifjingyan=true;dialogVisible = false" v-on:click="dispose();deletecomment()">删除并禁言用户</el-button>
  </span>
  </el-dialog>

<el-table  :key="1"
            :data="report.slice((currentPage-1)*pageSize,currentPage*pageSize)"
            stripe
            style="width: 100%">
  <el-table-column
    label="序号"
    type="index"
    :index="Nindex"
    width="180">
  </el-table-column>
  <el-table-column
    prop="username1"
    label="举报者"
    width="180">
  </el-table-column>
  <el-table-column
    prop="username2"
    label="被举报者"
    width="180">
  </el-table-column>
<!--  <el-table-column-->
<!--    prop="reported_content"-->
<!--    label="举报内容"-->
<!--    width="180">-->
<!--  </el-table-column>-->
  <el-table-column
    prop="report_reason"
    label="举报理由"
    width="180">
  </el-table-column>
  <el-table-column
    prop="report_time"
    label="举报时间"
    width="180">
  </el-table-column>
  <el-table-column label="操作">
    <template slot-scope="scope">
      <el-button
                 size="mini"
                 type="warning"
                 @click="dialogVisible=true;getreported_content(scope.row.reported_content);getID(scope.row.id);getcommentid(scope.row.comments_id);getreportedid(scope.row.reported_id)">查看详情</el-button>
<!--       <i v-if="scope.row.progress===1" class="el-icon-success" style="font-size: 25px;color: #10dc6f"></i>-->
<!--      <el-button-->
<!--        size="mini"-->
<!--        type="danger"-->
<!--        @click="">删除</el-button>-->
    </template>
  </el-table-column>
  <el-table-column label="进度">
    <template slot-scope="scope">
      <i v-if="scope.row.progress===1" class="el-icon-success" style="font-size: 20px;color: #10dc6f"><span style="font-size: 15px">已处理</span></i>
      <i v-if="scope.row.progress===0" class="el-icon-warning-outline" style="font-size: 20px;color: #e12a0a"><span style="font-size: 15px">未处理</span></i>
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
                 :total="report.length">
  </el-pagination>
</div>
</template>

<script>
export default {
  name: "Report",
  data() {
    return {
      ifjingyan:false,
      reported_id:'',
      report: [],
      currentPage:1,
      totalCount:0,
      pageSize:10,
      dialogVisible:false,
      content:'',
      id:'',
      commentid:'',
      circleUrl: "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      qwe:'',
      ReportForm:{
        progress:"",
        username1:'',
        username2:'',
        sort:'倒序',
      }
    }
  },
  methods:{
    resetForm() {
      this.$refs.ReportFormRef.resetFields()
      this.findALLReport()
    },
    getreportedid(iid){
      this.reported_id=iid;
    },
    getID(iid){
      this.id=iid;
    },
    getcommentid(iid){
      this.commentid=iid;
      console.log(this.commentid);
    },
    getreported_content(a){
      this.content=a;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    findALLReport(){
      this.$http.post('http://localhost:8888/Report/findAllReport',
      ).then(function (res) {
        this.report = res.data.data;
      })
    },
    Nindex(index) {
      const page = this.currentPage // 当前页码
      const pagesize = this.pageSize // 每页条数
      return index  +1+ (page - 1) * pagesize
    },

    //每页条数改变时触发 选择一页显示多少行
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
    dispose(){
        this.$http.post('http://localhost:8888/Report/updateReport',
            {"id":this.id},
            {emulateJSON: true}
          ).then(function (res) {
            if (res.data.code == 200) {
              alert(res.data.msg);
              this.$router.go(0);
            }
          })
    },
    deletecomment(){
      this.$http.post('http://localhost:8888/Report/deleteComment',
        {"comment_id": this.commentid},
        {emulateJSON: true}
      ).then(function (res) {
        if (res.data.code == 200) {
          alert(res.data.msg);
        }
      })
      if(this.ifjingyan==true){
        this.$http.post('http://localhost:8888/Report/changestate',
          {"id": this.reported_id},
          {emulateJSON: true}
        ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
            this.ifjingyan=false;
          }
        })
      }
      this.$router.go(0);
    },
    select(){
      // if(this.ReportForm.progress.length===0&&this.ReportForm.username1.length===0&&this.ReportForm.username2.length===0){
      //   this.$message("请输入");
      // }else {
        if(this.ReportForm.progress=="全部") {
          this.ReportForm.progress = ""
        }
        this.$http.post('http://localhost:8888/Report/select',
          {
            "progress": this.ReportForm.progress,
            "report_name": this.ReportForm.username1,
            "reported_name": this.ReportForm.username2,
            "sort": this.ReportForm.sort
          },
          {emulateJSON: true}
        ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
            this.report = res.data.data;
          } else {
            alert(res.data.msg);
          }
        })
      }
    // }
  },
  created() {
    this.findALLReport();
  }
}
</script>

<style scoped>
.box{

}
</style>
