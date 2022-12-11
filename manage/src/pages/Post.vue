<template>
  <div style="margin: 20px 20px 0px 20px">
    <el-form style="float: left;margin-left: 100px" :inline="true"  class="demo-form-inline"  ref="PostsFormFormRef" :model="PostsForm">
      <el-form-item label="标题" prop="post_title">
        <el-input v-model="PostsForm.post_title"  placeholder="标题"   clearable></el-input>
      </el-form-item>

      <el-form-item label="作者名" prop="author_name" style="margin-left: 30px">
        <el-input v-model="PostsForm.author_name" placeholder="作者名"  clearable></el-input>
      </el-form-item>

      <el-form-item label="时间排序" prop="sort" style="margin-left: 30px">
        <el-select  v-model="PostsForm.sort" placeholder="时间排序" style="width: 150px;">
          <el-option label="正序" value="正序"></el-option>
          <el-option label="倒序" value="倒序"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" style="margin-left: 120px" @click="select">查询</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>

    </el-form>

    <el-table
      :data="Posts.slice((currentPage-1)*pageSize,currentPage*pageSize)"
      style="width: 100%">
      <el-table-column
        label="序号"
        type="index"
        :index="Nindex"
        width="180">
      </el-table-column>
      <el-table-column
        prop="post_title"
        label="标题"
        width="180">
      </el-table-column>
      <el-table-column
        prop="username"
        label="作者名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="up_counts"
        label="点赞数"
        width="180">
      </el-table-column>
      <el-table-column
        prop="create_time"
        label="创建时间"
        width="180">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            @click="handleEdit(scope.row.post_title)">编辑</el-button>-->
          <el-button
            size="mini"
            type="danger"
            @click="deletePost(scope.row.id)">删除</el-button>
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
                   :total="Posts.length">
    </el-pagination>


  </div>
</template>

<script>
export default {
  name: 'Post',
  data(){
    return{
      currentPage:1,
      totalCount:0,
      pageSize:10,
      PostsForm:{
        post_title:"",
        author_name:"",
        sort:'倒序'
      },
      selectPost:"",
      id:'',
      Posts:[],
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
      this.$refs.PostsFormFormRef.resetFields();
      this.findAllPosts();
    },
    selectPostsByTitle(){
      this.$http.post('http://localhost:8888/Post/findPostByTitleL',
        {"post_title": this. PostsForm.post_title},
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
    selectPostsByAuthor(){
      this.$http.post('http://localhost:8888/Post/findPostByAuthorL',
        {"author_name": this. PostsForm.author_name},
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
    findAllPosts() {
      this.$http.post('http://localhost:8888/Post/findAllPost',
      ).then(function (res) {
        this.Posts = res.data.data;
      })
    },
    deletePost(iid){
      this.$confirm('是否确认删除该帖子?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.id = iid;
        this.$http.post('http://localhost:8888/Post/deletePostByID',
          {"id": this.id},
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
      // if (this.PostsForm.author_name === '' && this.PostsForm.post_title === ''){
      //   this.$message("请输入");
      // } else {
        this.$http.post('http://localhost:8888/Post/select',
          {"author_name": this.PostsForm.author_name, "post_title": this.PostsForm.post_title,"sort":this.PostsForm.sort},
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
      }
  },
  created() {
    this.findAllPosts();
  }
}
</script>

<style scoped>

</style>
