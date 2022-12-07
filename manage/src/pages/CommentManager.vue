<template>
<div>
  <el-table
    :data="Comments.slice((currentPage-1)*pageSize,currentPage*pageSize)"
    style="width: 100%">
    <el-table-column
      label="序号"
      type="index"
      :index="Nindex"
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
    <el-table-column
      prop="b_comments_id"
      label="被评论者id"
      width="180">
    </el-table-column>
    <el-table-column
      prop="user_id"
      label="评论者id"
      width="180">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="success"
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
  name: 'CommentManager',
  data(){
    return{
      Comments:[],
      currentPage:1,
      totalCount:0,
      pageSize:10,
      user_id:33,
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
    selectCommentsbyuserid(){
        this.$http.post('http://localhost:8888/Comments/findCommentsByUserIDL',
          {"user_id": this.user_id},
          {emulateJSON: true}
        ).then(function (res) {
            if (res.data.code == 200) {
              this.Comments=res.data.data;
            } else {
            }
          }
        )},
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
    },
  created() {
    this.selectCommentsbyuserid();
  }

}
</script>

<style scoped>

</style>
