<template>
  <div>
    <div id="main">
      <el-row>
        <el-input
          v-model="article.articleTitle"
          style="margin: 10px 0px;font-size: 18px;"
          placeholder="请输入标题"></el-input>
      </el-row>
<el-row style="margin-bottom: 10px">
  <el-select
    v-model="value"
    style="width: 30%;"
    multiple
    filterable
    allow-create
    default-first-option
    placeholder="请选择帖子标签">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</el-row>
      <el-row style="height: calc(100vh - 140px);">
        <mavon-editor
          v-model="article.articleContentMd"
          style="height: 100%;"
          ref=md
          @save="saveArticles"
          fontSize="16px">
          <button type="button" class="op-icon el-icon-document" :title="'摘要/封面'" slot="left-toolbar-after"
                  @click="dialogVisible = true"></button>
        </mavon-editor>
        <el-dialog
          :visible.sync="dialogVisible"
          width="30%">
          <el-divider content-position="left">摘要</el-divider>
          <el-input
            type="textarea"
            v-model="article.articleAbstract"
            rows="6"
            maxlength="255"
            show-word-limit></el-input>
          <el-divider content-position="left">封面</el-divider>
          <div style="margin-top: 20px">
            <el-input v-model="article.articleCover" placeholder="图片 URL"></el-input>
            <img-upload @onUpload="uploadImg" ref="imgUpload" style="margin-top: 5px"></img-upload>
          </div>
          <span slot="footer" class="dialog-footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
        </span>
        </el-dialog>
      </el-row>
      <el-row style="margin-top: 25px;float: right"><el-button type="primary" :disabled="users.state" >上传<i class="el-icon-upload el-icon--right"></i></el-button></el-row>
    </div>


  </div>
</template>

<script>
import imgUpload from "./imgUpload";
//年
let year = new Date().getFullYear();
//月份是从0月开始获取的，所以要+1;
let month = new Date().getMonth() +1;
//日
let day = new Date().getDate();
//时

let time = year + '-' + month + '-' + day
export default {
  name: "write",
  components:{imgUpload},
  data() {
    return{
      options: [{
        value: 'HTML',
        label: 'HTML'
      }, {
        value: 'SpringBoot',
        label: 'SpringBoot'
      }, {
        value: 'JavaScript',
        label: 'JavaScript'
      },{
        value: '大数据',
        label: '大数据'
      },{
        value: 'Java',
        label: 'Java'
      },{
        value: 'C++',
        label: 'C++'
      }],
      value: [],
      flag:true,
      contents_id:'37',
      123:'',
      article:{},
      dialogVisible:false,
      users:{}
    }
  },
  methods: {

    saveArticles (value, render) {
      this.article.articleDate =time;
      // value 是 md，render 是 html
      this.$confirm('是否保存并发布文章?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
          this.$http.post('http://localhost:8888//user/write/article', {
              id: this.article.id,
              articleTitle: this.article.articleTitle,
              articleContentMd: value,
              articleContentHtml: render,
              articleAbstract: this.article.articleAbstract,
              articleCover: this.article.articleCover,
              /*articleDate: this.article.articleDate*/
            }).then(res => {
            console.log(res, 333)
            if (res.data.code == 200) {
              this.contents_id=res.data.data.id
              this.addPost()
              this.$message({
                type: 'success',
                message: '发布成功'
              })
            }else{
              this.$message({
                type: 'info',
                message: '内容重复，已取消发布'
              })
            }
          })
        }
      )

    },
    uploadImg () {
      this.article.articleCover = this.$refs.imgUpload.url
    },
    getUser(){
      this.$http.post('http://localhost:8888/User/findUserByName2', {
        'username':localStorage.getItem('userName')
      }).then(res=>{
        this.users=res.data.data
      })

    },
    addPost(){
      this.$http.post('http://localhost:8888//user/write/addPost', {
        contents_id:this.contents_id,
        author_id:this.users.id,
        img:this.article.articleCover,
        post_title:this.article.articleTitle
      }).then(res=>{
        if (res.data.code == 200) {
          this.$message({
            type: 'success',
            message: 'post'
          })
        }else{
          this.$message({
            type: 'info',
            message: '错误'
          })
        }
      })
    },
  },
mounted() {

},
created() {
  this.getUser()

}
}
</script>

<style scoped>

</style>
