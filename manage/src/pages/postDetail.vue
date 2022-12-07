<template>
  <div>
  <HomeHeader></HomeHeader>
  <div style="width: 100%;background-color: #A0B4DC;padding: 8px 12px 0 12px;">

    <div style="width:20%;">
      <el-card class="aside_box" style="text-align: left;width: 268px;float: left;margin:0 10px 0 auto">
        <div class="intro">
          <div class="image">
            <img :src="author.avatar" style="width: 100%;height: 100%;border-radius:50%"/>
            <!--  <div slot="placeholder" class="image-slot">
                <i class="el-icon-loading"/>
              </div>-->
          </div>
          <div class="user_top">
            <span class="user_top_name">{{ author.username }}</span>
            <span class="years">{{ author.professional }}</span>
            <span v-if="author.status=='老师'">
      <img src="../assets/img/laoshi.png" class="user_icon">
      <i style="font-size: 10px;">老师认证</i>
    </span>
            <span v-if="author.status=='专家'">

      <img src="../assets/img/zhuanjia.png" class="user_icon">
      <i style="font-size: 10px;">专家认证</i>
    </span>
          </div>
        </div>

        <div style="margin: 10px auto 10px auto;text-align: center">
          <div style="border-bottom: 1px solid #999aaa">
     <span class="tongJi_text">
       {{ sumPost }}
     </span>
            <span style="margin: 0px 50px 0px 50px" class="tongJi_text">
       {{ sumGood }}
     </span>
            <span class="tongJi_text">
      {{ sumView }}
     </span>
          </div>
          <div>
          <span class="tongJi_text">
       原创
     </span>
            <span style="margin: 0px 30px 0px 30px" class="tongJi_text">
       点赞
     </span>
            <span class="tongJi_text">
     点击量
     </span>
          </div>
        </div>
        <div>
          <el-button round style="width: 100%;margin-top: 20px" @click="dialogVisible = true">提问</el-button>
        </div>
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
      </el-card>
      <div>
        <el-card class="aside_box2">
          <div class="assideNextTop">
            <span class="assideNext">用户的其他文章</span></div>

        </el-card>
      </div>
    </div>


    <div class="articles-area">
      <el-card style="text-align: left;width: 990px;margin: 0 auto 0 10px;min-height: 400px">
        <div>
          <span style="font-size: 20px"><strong>{{ article.articleTitle }}</strong></span>
          <div style="margin: 10px 0px 10px 35px;">
              <div  style="color:#999aaa;font-size: 14px; line-height: 24px;display: flex">文章标签：
              <div v-for="item in label" :key="label.id" >
                <div>
                  <a class="label">{{item.label_name}}</a>
                </div>
              </div>
              </div>
          </div>
          <div style="font-size: 8px;margin: 10px 0 0 40px">
            <el-divider content-position="left"><span style="color: #409EFF">发布于：</span>
              {{article.create_time}}
            </el-divider>
          </div>

          <div class="markdown-body">
            <div v-html="article.articleContentHtml"></div>
            <div >
              <div style="float: right" @click="handleClickUp">
                <div style="margin: 0 auto">
                  <img style="margin: 0 auto" v-if="isUp" src="../assets/img/1.png" alt="" />
                  <img style="margin: 0 auto" v-else src="../assets/img/2.png" alt="" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-card>
      <el-card style="text-align: left;width: 990px;margin: 10px auto 0 279px">
        <div>
          <div class="image" style="display: flex">
            <img :src=users.avatar style="width: 100%;height: 100%;border-radius:50%"/>
          </div>
          <div class="user_top">
          </div>
          <!--        <el-input
                    type="textarea"
                    :autosize="{ minRows: 2, maxRows: 5}"
                    placeholder="说说你的高见"
                    v-model="text1"
                  style="margin-top: 10px">
                  </el-input>-->
          <el-form :model="messageForm">
            <el-form-item prop="content">
              <el-input :rows="5" v-model="messageForm.content" type="textarea" placeholder="说说你的高见"></el-input>
            </el-form-item>
            <el-form-item style="text-align: right">
              <el-button type="primary" @click="publish">点击发表</el-button>
            </el-form-item>
          </el-form>
          <div style="margin: 20px 0">
            <div style="margin: 10px 0;font-size: 24px;padding: 10px 0;border-bottom: 1px solid #cccccc">评论列表</div>
            <div style="margin: 20px 0 ;text-align: left;">
              <div style="padding: 10px 0;" v-for="item in comments" :key="item.id">
                <div style="display: flex">
                  <div style="width: 50px;margin-right: 20px">
                    <el-avatar :size="50"
                               :src="item.avatar" class="touxiang"></el-avatar>
                  </div>
                  <div style="flex: 1">
                    <div style="">{{ item.username }}<span style="margin-left:10px;font-size: 8px ">{{
                        item.create_time
                      }}</span></div>
                    <div style="margin-top: 10px">{{ item.comments_content }}</div>
                  </div>
                </div>
                <div style="text-align: right;padding: 5px">
                  <el-button type="text" @click="item.replyFlag=!item.replyFlag">回复</el-button>
                  <el-button type="text" @click="openReport(item.id,item.user_id,item.comments_content)">举报</el-button>
                  <div>
                    <el-form :model="replyComment" v-if="item.replyFlag">
                      <el-form-item prop="content">
                        <el-input :rows="2" v-model="replyComment" type="textarea"
                                  placeholder="说说你的高见"></el-input>
                      </el-form-item>
                      <el-form-item style="text-align: right">
                        <el-button type="primary" @click="replyComments(item.id)">回复</el-button>
                      </el-form-item>
                    </el-form>
                  </div>
                </div>
                <div v-if="item.comments_next.length!=0"
                     style="margin-left: 100px;background-color:aliceblue;padding: 10px;border-radius: 10px">
                  <div v-for="sub in item.comments_next" :key="sub.id">
                    <div style="padding: 5px 0; ">
                      <el-avatar :size="35" :src="sub.avatar" class="touxiang">

                      </el-avatar>
                      <span style="margin-left:10px ">{{ sub.username }}</span>
                      <span style="margin: 0 10px">回复</span>
                      <span style="color: cornflowerblue">@{{ item.username }} </span>
                      <span><span style="color: #666;margin-left: 10px">{{ sub.contents }}</span></span>
                    </div>
                    <div style="text-align: right;padding: 5px">
                      <el-button type="text" @click=""><span style="font-size: 3px">举报</span></el-button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-card>
    </div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible1"
      width="30%"
      :before-close="handleClose">
      <el-select v-model="value" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
      <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible1 = false">取 消</el-button>
    <el-button type="primary" @click="report">确 定</el-button>
  </span>
    </el-dialog>
  </div>
  </div>
</template>

<script>
import "../assets/css/markdown.css"
import axios from "axios";
import CommentList from "./CommentList";
import HomeHeader from "./HomeHeader";

export default {
  components: {CommentList,HomeHeader},
  name: "postDetail",
  data() {
    return {
      reportComments_id:'',
      reportCommentsUser_id:'',
      reportComments_content:'',
      dialogVisible1: false,
      isUp: false,
      support:'',
      dialogVisible: false,
      post_id: '27',
      users: {},
      text1: '测试下回复功能',
      sumPost: '0',
      sumView: '0',
      sumGood: '0',
      author_id: '',
      messageForm: {
        content: '',
      },
      messageList: [
        {
          id: '1',
          createTime: '2022',
          nickname: '测试',
        }
      ],
      author: {},
      article: {
/*        articleTitle: '测试一下',
        articleDate: '2022-11-25',
        articleContentHtml: '<p>测试一下</p>',*/
      },


      replyComment: '',
      comments: [],
      options: [{
        value: '发表不良言论',
        label: '发表不良言论'
      }, {
        value: '辱骂他人',
        label: '辱骂他人'
      }, {
        value: '发布反动信息',
        label: '发布反动信息'
      }, {
        value: '传播淫秽色情',
        label: '传播淫秽色情'
      }, {
        value: '其他',
        label: '其他'
      }],
      value: '',
      label:[
        {
          label_id:'1',
      label_name:'HTML',
      },
        {
          label_id:'2',
          label_name:'CSS',
        }
      ],
    }
  },
  methods: {
    question() {
      if (this.users.state === 0) {
        axios.post('http://localhost:8888/Interlocution/addInterlocution',
          {
            "experts_name": this.author.name,
            "question_content": this.textarea,
            "creater_name": localStorage.getItem('userName')
          },
          {emulateJSON: true},
        ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
          } else {
            alert(res.data.msg);
          }
        })
      } else {
        this.$message({
          type: 'info',
          message: '你已被禁言'
        })
      }

    },
    publish() {
      if (this.users.state === 0) {
        if (this.users.username != null) {

          this.$http.post('http://localhost:8888//postDetail/addComment', {
            comments_content: this.messageForm.content,
            user_id: this.users.id,
            post_id: this.post_id,
          }).then(res => {
            if (res.data.code == 200) {
              this.messageForm.content = null
              this.selectAllComments()
              this.$message({
                type: 'success',
                message: '评论成功'
              })
            } else {
              this.messageForm.content = null
              this.$message({
                type: 'info',
                message: '评论失败'
              })
            }
          })
        }
      } else {
        this.messageForm.content = null
        this.$message({
          type: 'info',
          message: '你已被禁言'
        })
      }


    },
    selectByid() {
      this.$http.post('http://localhost:8888//postDetail/selectByid', {
        id: this.post_id,
      }).then(res => {
        if (res.data.code == 200) {
          this.$message({
            type: 'success',
            message: '用户接受成功'
          })
        }
        this.article.articleTitle = res.data.data.articleTitle,
          this.article.articleContentHtml = res.data.data.articleContentHtml,
          this.article.create_time = res.data.data.create_time
      })
    },
    getUser() {
      /* this.$http.post('http://localhost:8888/User/findUserByName2', {
           'username':localStorage.getItem('userName')
       }).then(res=>{
         this.users=res.data.data
       })*/
      this.users = JSON.parse(localStorage.getItem('users'))
    },

    getAuthor() {
      this.$http.post('http://localhost:8888//postDetail/selectAuthorByid', {
        id: this.post_id,
      }).then(res => {
        if (res.data.code == 200) {
          this.$message({
            type: 'success',
            message: '作者接受成功'
          })
        }
        this.author = res.data.data
        this.$http.post('http://localhost:8888//postDetail/selectAll', {
          id: res.data.data.id
        }).then(res2 => {
          this.sumPost = res2.data.data.SumPost,
            this.sumView = res2.data.data.SumView,
            this.sumGood = res2.data.data.SumGood
        })
      })
    },

    selectAllComments() {
      this.$http.post('http://localhost:8888//postDetail/selectAllComments', {
        id: this.post_id
      }).then(res => {
        this.comments = res.data.data
      })
    },

    replyComments(comments_id) {
      if (this.users.state === 0) {
        this.$http.post('http://localhost:8888//postDetail/replyComments', {
          comments_id: comments_id,
          contents: this.replyComment,
          user_id: this.users.id,
        }).then(res => {
          if (res.data.code == 200) {
            this.replyComment = null
            this.selectAllComments()
          }
        })
      } else {
        this.replyComment = null
        this.$message({
          type: 'info',
          message: '你已被禁言'
        })

      }

    },
    handleClickUp () {
      if(this.isUp===false)
      {
        this.$http.post('http://localhost:8888//postDetail/Good', {
          up_counts:1,
          id:this.post_id
        })
      }

      else
      {
        this.$http.post('http://localhost:8888//postDetail/Good', {
          up_counts:-1,
          id:this.post_id
        })
        this.support=this.support-1
      }

      this.isUp = !this.isUp
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    openReport(id,user_id,comments_content){
     this.dialogVisible1 = true
    this.reportComments_id= id,
      this.reportCommentsUser_id=user_id,
      this.reportComments_content=comments_content
    },
    report(){
        this.$http.post('http://localhost:8888//Report//addReport', {
          report_reason:this.value,
          report_id:this.users.id,
          reported_id:this.reportCommentsUser_id,
          comments_id:this.reportComments_id,
          reported_content:this.reportComments_content
        }).then(res=>{
           this.dialogVisible1=false
          this.$message({
            type: 'success',
            message: '举报成功'
          })

        })
    },
  },
  created() {
    this.selectByid()
    this.getUser()
    this.getAuthor()
    this.selectAllComments()
  },
  computed: {
    userName() {
      return localStorage.getItem('userName')
    },

  }
}
</script>

<style scoped>
.aside_box {
  /* background-color: #f5f6f7;*/
  margin-bottom: 8px;
  border-radius: 2px;
  /*  display: block;*/
}

.intro {
  padding: 16px 16px 6px 1px;
  display: flex;
}

.image {
  position: relative;
  width: 48px;
  height: 48px;
  flex-shrink: 0;
}

.user_top {
  margin-left: 8px;
  width: 156px;
  position: relative;
  padding-top: 4px;
  line-height: 20px;
  min-width: 62px;
}

.user_top_name {
  width: 100%;
  font-size: 14px;
  font-weight: 500;
  height: 20px;
  line-height: 20px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  min-width: 62px;
}

.years {
  width: 60px !important;
  color: #999aaa;
  font-size: 13px;
  line-height: 20px;
  height: 20px;
  overflow: hidden;
  margin-right: 2px;
}

.user_icon {
  width: 16px;
  height: 16px;
  vertical-align: -2px;
}

.tongJi {
  padding: 9px 10px;
}

.tongJi_text {
  color: #4a4d52;
  font-size: 14px;
  font-weight: 500;
  line-height: 22px;
}

.assideNextTop {
  color: #3d3d3d;
  background: #f5f5f5;
  padding: 0 16px;
  height: 38px;
  line-height: 38px;
}

.assideNext {
  position: relative;
  padding: 0px 16px 0;
  font-size: 14px;
  color: #333;
  font-weight: bold;
}
</style>
<style>

.aside_box2 {
  border-radius: 2px;
  text-align: center;
  width: 268px;
  float: left;
  margin: 10px 10px 8px auto;
  min-height: 100px;
}

.aside_box2 .el-card__body {
  padding: 0 0 20px 0 !important;
}

.el-avatar > img {
  width: 100%;
}
.label{
  box-sizing: border-box;
  margin-right: 8px;
  padding: 3px 6px;
  font-size: 12px;
  background-color: #fff;
  color: #5094d5;
  border: 1px solid #eaeaef;
  height: 24px;
  line-height: 17px;
  border-radius: 2px;
  display: inline-block;
}
</style>
