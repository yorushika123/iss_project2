<template>
  <div>
    <el-form style="float: left;margin-left: 100px" :inline="true"  class="demo-form-inline"  ref="CommentsFormFormRef" :model="CommentsForm">
      <el-form-item label="帖子id" prop="post_id">
        <el-input v-model="CommentsForm.post_id"  placeholder="帖子id"   clearable></el-input>
      </el-form-item>


      <el-form-item label="评论者id" prop="user_id" style="margin-left: 30px">
        <el-input v-model="CommentsForm.user_id" placeholder="评论者id"  clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" style="margin-left: 120px" @click="select">查询</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>


    <el-table
              :data="Comments.slice((currentPage-1)*pageSize,currentPage*pageSize)"
              style="width: 100%">
      <el-table-column
        label="序号"
        type="index"
        :index="Gindex"
        width="180">
      </el-table-column>
      <el-table-column
        prop="post_id"
        label="帖子id"
        width="180">
      </el-table-column>
      <el-table-column
        prop="comments_content"
        label="评论内容"
        width="180">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deleteComments(scope.row.post_id)">删除</el-button>
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
                   :total="Comments.length">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'SongList',
  data(){
    return{
      currentPage:1,
      totalCount:0,
      pageSize:10,
      selectComment:"",
      Comments:[],
      CommentsForm: {
        post_id: '',
        user_id:'',
      }
    }
  },
  methods:{
    Gindex(index) {
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
      this.$refs.CommentsFormFormRef.resetFields()
    },
    selectCommentsbypostid(){
      if (!isNaN(this.CommentsForm.post_id)) {
          this.$http.post('http://localhost:8888/Comments/findCommentsByPostIDL',
            {"post_id": this.CommentsForm.post_id},
            {emulateJSON: true}
          ).then(function (res) {
              if (res.data.code == 200) {
                alert(res.data.msg);
                this.Comments = res.data.data;
              } else {
                alert(res.data.msg);
              }
            }
          )
      }
      else
        this.$message('请输入数字');
     },
        selectCommentsbyuserid(){
          if (!isNaN(this.CommentsForm.user_id)) {
          this.$http.post('http://localhost:8888/Comments/findCommentsByUserIDL',
            {"user_id": this.CommentsForm.user_id},
            {emulateJSON: true}
          ).then(function (res) {
              if (res.data.code == 200) {
                alert(res.data.msg);
                this.Comments = res.data.data;
              } else {
                alert(res.data.msg);
              }
            }
          )}
          else
            this.$message('请输入数字');
        },
    deleteComments(post_id){
      this.$confirm('是否确认删除该评论?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.CommentsForm.post_id = post_id;
        this.$http.post('http://localhost:8888/Comments/deleteComments',
          {"post_id": this.CommentsForm.post_id},
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

    select() {
      if ((this.CommentsForm.user_id != null && this.CommentsForm.user_id != "") && (this.CommentsForm.post_id == null || this.CommentsForm.post_id == "")) {
        this.selectCommentsbyuserid();
      }
    else  if ((this.CommentsForm.user_id == null || this.CommentsForm.user_id == "") && (this.CommentsForm.post_id != null && this.CommentsForm.post_id != "")) {
        this.selectCommentsbypostid();
      }
     else if ((this.CommentsForm.user_id == null || this.CommentsForm.user_id == "") && (this.CommentsForm.post_id == null || this.CommentsForm.post_id == "")) {
        this.$message("请输入");
      }
     else
       this.selectCommentsbyuserid();
    },

    findAllComments() {
      this.$http.post('http://localhost:8888/Comments/findAllComments',
      ).then(function (res) {
        this.Comments = res.data.data;
      })
    },
  },
  created() {
    this.findAllComments();
  }
}

</script>

<style scoped>

</style>
