<template>
  <div>

<!--    <el-dialog-->
<!--      title="请输入你的回答"-->
<!--      :visible.sync="dialogVisible"-->
<!--      width="50%"-->
<!--      center-->
<!--      :before-close="handleClose">-->
<!--      <el-input-->
<!--        type="textarea"-->
<!--        :rows="20"-->
<!--        placeholder="请输入内容"-->
<!--        v-model="textarea" >-->
<!--      </el-input>-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--    <el-button @click="dialogVisible = false">取 消</el-button>-->
<!--    <el-button type="primary" @click="dialogVisible = false" v-on:click="answer()">确认回复</el-button>-->
<!--  </span>-->
<!--    </el-dialog>-->

    <el-dialog
      title="查看问题"
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
      title="查看我的回答"
      :visible.sync="dialogVisible2"
      width="50%"
      center
      top="30vh"
      :before-close="handleClose">
      <span>{{this.answers.answer_content}}</span>
      <span slot="footer" class="dialog-footer">
  </span>
    </el-dialog>

    <div v-for="(item,index) in message" :key="index">
      <div class="message">
        <div><img class="img1" :src=item.avatar></div>
         <div>
          <div style="margin-left: 20px"><strong style="color: #343333">{{item.username}}</strong>&nbsp;&nbsp;&nbsp;向你发起提问</div>

          <div style="margin-left: 20px;margin-top: 10px;overflow: hidden;white-space: nowrap;text-overflow:ellipsis;width: 75vw"><a  v-on:click="watch(item.question_content)" style="color: #6f42c1;cursor: pointer;font-size: 18px" @click="dialogVisible1=true">{{item.question_content}}</a></div>
          <div style="margin-left: 20px;margin-top: 10px"><span>{{item.create_time}}</span>
            <a  class="a1" @click="isShow(index);getID(item.id)" v-on:click="findmyavatar()"><i class="el-icon-edit-outline"></i>回答</a>
            <a style="margin-left: 20px" class="a1" @click="dialogVisible2=true" v-on:click="findBYID(item.id)"><i class="el-icon-chat-round"></i>我的回答</a>
          </div>
          <div  ref="show" class="active" style="margin-top: 20px">
            <div style="" ><img class="img1" :src=avatars.avatar style="margin-right: 20px"></div>
            <el-input  type="textarea"
                       :rows="2"
                       placeholder="请输入内容"
                       v-model="textarea[index]"
                       style="width: 1000px;"
            >
            </el-input>
            <el-button  type="success" v-on:click="answer(index)">发表回答</el-button>
          </div>
         </div>
      </div>
    </div>
    </div>
</template>

<script>
import Header from "../components/Header";
import axios from "axios";


export default {
  name: "MyMessage",
  components: {Header},
  data(){
    return{
        message:[],
        users:[],
        // dialogVisible: false,
        dialogVisible1: false,
        dialogVisible2: false,
        textarea:[],
        id:'',
        username:'',
        index:'',
        content:'',
        content2:'',
        answers:'',
        display:'display:none',
        avatars:''
    }
  },
  methods: {
    isShow(index) {
      let a = this.$refs.show[index]		//	通过下标找到对应同级节点
      if (a.getAttribute('class') === 'active') {
        a.setAttribute('class', 'active1')
      } else {
        a.setAttribute('class', 'active')
      }
    },
    getID(iid) {
      this.id = iid;
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {
        });
    },
    watchmessage() {
      this.$http.post('http://localhost:8888/Interlocution/findInterlocution',
        {"experts_id":JSON.parse(localStorage.getItem("users")).id},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200) {
          this.message = res.data.data;
        } else {
        }
      })
    },
    answer(indexx) {
      this.index=indexx;
      axios.post('http://localhost:8888/Interlocution/updateInterlocution',
        {
          "answer_content": this.textarea[this.index],
          "id": this.id
        },
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200) {
          alert(res.data.msg);
        } else {
          alert(res.data.msg);
        }
      })
      this.$router.go(0);
    },
    watch(contents) {
      this.content = contents;
    },
    findBYID(iid) {
      this.id = iid;
      this.$http.post('http://localhost:8888/Interlocution/findBYID',
        {"id": this.id},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200) {
          this.answers = res.data.data;
        } else {
        }
      })
    },
    findmyavatar(){
      this.$http.post('http://localhost:8888/Interlocution/findInterlocutionexperts',
        {"experts_id":JSON.parse(localStorage.getItem("users")).id},
        {emulateJSON: true},
      ).then(function (res) {
        if (res.data.code == 200) {
          this.avatars= res.data.data;
        } else {
        }
      })
    },
    show: function () {
      if (this.display === "display:none") {
        this.display = "";
      } else {
        this.display = "display:none";
      }
    }
  },
  created() {
    this.watchmessage();
  }
}
</script>

<style scoped>
.message{
  width: 100%;
  height: auto;
  border-bottom: 1px solid rgba(65, 105, 225, 0.2);
  display: flex;
  margin-top: 20px;
  padding: 10px 0 10px 0;
}

.img1{
  width:50px;
  height:50px;
  border-radius: 50%;
}

.a1:hover{
  color: #920ed3;
  cursor:pointer
}

.active{
  display:none;
}

.active1{
  display:flex;
}
</style>
