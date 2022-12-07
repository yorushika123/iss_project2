<template>
  <div>
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
        prop="create_time"
        label="发布时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="up_counts"
        label="点赞数"
        width="180">
      </el-table-column>
      <el-table-column
        prop="content_id"
        label="内容id"
        width="180">
      </el-table-column>

      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="handleEdit(scope.row.post_title)">修改</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="deletePost(scope.row.post_title)">删除</el-button>
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
  name: 'PostManager',
  data(){
    return{
      Posts:[],
      currentPage:1,
      totalCount:0,
      pageSize:10,
      author_id:123,
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
    selectPostsByAuthor(){
        this.$http.post('http://localhost:8888/Post/findPostByAuthorL',
          {"author_id": this.author_id},
          {emulateJSON: true}
        ).then(function (res) {
            if (res.data.code == 200) {
              this.Posts = res.data.data;
            } else {
            }
          }
        )
    },
    deletePost(post_title){
      this.$confirm('是否确认删除该帖子?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.PostsForm.post_title = post_title;
        this.$http.post('http://localhost:8888/Post/deletePostByTitle',
          {"post_title": this.PostsForm.post_title},
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
},
  created() {
    this.selectPostsByAuthor();
  }
}


</script>

<style scoped>

</style>
