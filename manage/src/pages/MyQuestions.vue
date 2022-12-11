<template>
<div style="height: 5000px">
  <el-dialog
    title="请输入你的问题"
    :visible.sync="dialogVisible"
    width="50%"
    :before-close="handleClose">
    <el-input
      type="textarea"
      :rows="20"
      placeholder="请输入内容"
      v-model="textarea">
    </el-input>
    <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="dialogVisible = false" v-on:click="question()">提 问</el-button>
  </span>
  </el-dialog>

  <el-dialog
    title="查看回答"
    :visible.sync="dialogVisible1"
    width="50%"
    center
    top="30vh"
    :before-close="handleClose">
    <span>{{this.content}}</span>
    <span slot="footer" class="dialog-footer">
  </span>
  </el-dialog>


  <el-dialog
    title="我的问题"
    :visible.sync="dialogVisible2"
    width="50%"
    center
    top="30vh"
    :before-close="handleClose">
    <span>{{this.content1}}</span>
    <span slot="footer" class="dialog-footer">
  </span>
  </el-dialog>


  <div v-for="item in replay.slice((currentPage-1)*pageSize,currentPage*pageSize)">
    <div class="message" >
      <div v-if="item.answer_content"><img class="img1" :src=item.avatar></div>
      <div>
        <div v-if="item.answer_content" style="margin-left: 20px"><strong style="color: #6f42c1">专家{{item.username}}</strong>&nbsp;回答了你的问题</div>
        <div v-if="item.answer_content" style="margin-left: 20px;margin-top: 10px;overflow: hidden;white-space: nowrap;text-overflow:ellipsis;width: 75vw">
          <a v-on:click="watch(item.answer_content)" @click="dialogVisible1=true" style="color: #6f42c1;font-size: 18px;cursor: pointer">{{item.answer_content}}</a>
        </div>
        <div v-if="item.answer_content" style="margin-left: 20px;margin-top: 10px"><span>{{item.answer_time}}</span></div>
        <div style="margin-left: 20px;margin-top: 20px;display: flex;align-items: center;">
           <div style="margin-right: 10px"><img class="img1" :src=avatars.avatar></div>
           <div>
             <div style='color: #6f42c1'><a style="cursor: pointer" v-on:click="watch1(item.question_content)" @click="dialogVisible2=true">{{item.question_content}}</a></div>
             <div style="margin-top: 10px">
               <a>{{item.create_time}}</a>
               <a style="margin-left: 20px;" class="a1" v-on:click="deletequestion(item.id)"><i class="el-icon-delete"></i>删除提问</a>
             </div>
           </div>
        </div>
      </div>
    </div>
  </div>
  <el-pagination align='center'
                 @size-change="handleSizeChange"
                 @current-change="handleCurrentChange"
                 :current-page="currentPage"
                 :page-sizes="[1,3,5,10,20]"
                 :page-size="pageSize"
                 layout="total, sizes, prev, pager, next, jumper"
                 :total="replay.length">
  </el-pagination>

  <el-table
    @row-click="openDetails"
    :data="users"
    style="width:30%;float: right">
    <el-table-column
      width="180">
      <template slot-scope="scope">
        <img :src="scope.row.avatar" class="img1">
      </template>
    </el-table-column>
    <el-table-column
      label="专家表"
      prop="username"
      width="180">
    </el-table-column>
  </el-table>



</div>
</template>

<script>
import axios from "axios";
import Header from "../components/Header";

let year = new Date().getFullYear();
//月份是从0月开始获取的，所以要+1;
let month = new Date().getMonth() +1;
//日
let day = new Date().getDate();
//时
let h=new Date().getHours();
let m=new Date().getMinutes();
let mi=new Date().getMilliseconds();
let time = new Date();
export default {
  name: 'MyQuestions',
  components: {Header},
  data() {
    return {
      currentPage:1,
      totalCount:0,
      pageSize:10,
      create_time:'',
      users:[],
      username:"",
      name: '',
      message:"",
      experts_id: '',
      textarea: '',
      dialogVisible: false,
      dialogVisible1: false,
      dialogVisible2: false,
      replay:[],
      content:'',
      content1:'',
      avatars:'',
      id:'',
    }
  },
  methods: {
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
    watch(contents) {
      this.content = contents;
    },
    watch1(contents) {
      this.content1 = contents;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    openDetails(row) {
      // this.$message(row.name);
      this.experts_id = row.id;
      this.$confirm('是否对该专家提问?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.dialogVisible = true
      }).catch(() => {

      });
    },
    question() {
      axios.post('http://localhost:8888/Interlocution/addInterlocution',
        {"experts_id": this.experts_id, "question_content": this.textarea,"creater_id":JSON.parse(localStorage.getItem("users")).id},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200){
          alert(res.data.msg);
        }else {
          alert(res.data.msg);
        }
      })
      this.$router.go(0);
    },

    findAllUser() {
      this.$http.post('http://localhost:8888/User/findAllUser',
      ).then(function (res) {
        this.users = res.data.data;
      })
    },
    watchreplay(){
      this.$http.post('http://localhost:8888/Interlocution/findInterlocution2',
        {"creater_id": JSON.parse(localStorage.getItem("users")).id},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200) {
          this.replay=res.data.data;
        }else {
        }
      })
    },
    findmyavatar(){
      this.$http.post('http://localhost:8888/Interlocution/findInterlocutioncreater',
        {"creater_id":JSON.parse(localStorage.getItem("users")).id},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200) {
          this.avatars= res.data.data;
        } else {
        }
      })
    },
    deletequestion(iid) {
      this.$confirm('是否确认删除该提问?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.id = iid;
        this.$http.post('http://localhost:8888/Interlocution/deleteInterlocution',
          {"id": this.id},
          {emulateJSON: true},
        ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
            this.$router.go(0);
          } else {
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    }
  },
  created() {
    this.findAllUser();
    this.watchreplay();
    this.findmyavatar();
  }
}
</script>

<style scoped>
.img1{
  width:50px;
  height:50px;
  border-radius: 50%;
}

.message{
  width: 100%;
  height: auto;
  border-bottom: 1px solid rgba(65, 105, 225, 0.2);
  display: flex;
  margin-top: 20px;
  padding: 10px 0 10px 0;
}


.a1:hover{
  color: #e30f44;
  cursor:pointer
}
</style>
