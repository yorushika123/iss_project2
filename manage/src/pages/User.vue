<template>
  <div  style="margin: 0 10px 0 20px;padding-top: 30px">
    <el-form style="float: left;margin-left: 100px" :inline="true"  class="demo-form-inline"  ref="UsersFormRef" :model="UsersForm">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="UsersForm.username"  placeholder="用户名"   clearable></el-input>
      </el-form-item>


      <el-form-item label="专业" prop="professional" style="margin-left: 30px">
        <el-input v-model="UsersForm.professional" placeholder="专业名" clearable></el-input>
      </el-form-item>

      <el-form-item label="学号" prop="studentnumber" style="margin-left: 30px">
        <el-input v-model="UsersForm.studentnumber" placeholder="学号" clearable></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" style="margin-left: 120px" @click="select">查询</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>

    </el-form>

      <el-table  :key="1"
        :data="users.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                 stripe
        style="width: 100%">
        <el-table-column
          label="序号"
          type="index"
          :index="Nindex"
          width="150">
        </el-table-column>
        <el-table-column
          prop="avatar"
          label="头像"
          type="index"
          index="Nindex"
          key="index"
          width="150">
          <template slot-scope="scope">
            <img :src="scope.row.avatar" class="img1" >
          </template>
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          width="150">
        </el-table-column>
        <el-table-column
          prop="professional"
          label="专业"
          width="150">
        </el-table-column>
        <el-table-column
          prop="studentnumber"
          label="学号"
          width="150">
        </el-table-column>
        <el-table-column
          prop="status"
          label="身份"
          width="150">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button v-if="scope.row.state===0"
                       size="mini"
                       type="warning"
                       @click="changeState(scope.row.username)">禁言</el-button>
            <el-button v-if="scope.row.state===1"
                       size="mini"
                       type="success"
                       @click="changeState2(scope.row.username)">取消禁言</el-button>

            <el-button
                       size="mini"
                       type="danger"
                       @click="deleteUser(scope.row.id)">删除</el-button>

            <el-switch
              v-model='scope.row.status'
              active-text="设为专家"
              active-color="#13ce66"
              active-value='专家'
              inactive-value='学生'
              @change="changestatus1(scope.row.status,scope.row.id)"
            >
            </el-switch>

            <el-switch
              v-model="scope.row.status"
              active-text="设为老师"
              active-color="#13ce66"
              active-value='老师'
              inactive-value='学生'
              @change="changestatus1(scope.row.status,scope.row.id)"
            >
            </el-switch>
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
                   :total="users.length">
    </el-pagination>


  </div>
</template>

<script>

export default {
  name: "User",
  data(){
    return{
      status:'',
      id:'',
      userid:'',
      value:1,
      currentPage:1,
      totalCount:0,
      pageSize:10,
      UsersForm: {
        username: "",
        professional: "",
        studentnumber: "",
      },
      users:[],
      input: '',
      selectUser: "",
    }
  },
  methods: {
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
    resetForm() {
      this.$refs.UsersFormRef.resetFields()
      this.findAllUser()
      this.findAllUser()
    },
    changestatus1(st,st2){
      this.status=st;
      this.id=st2;
      this.$http.post('http://localhost:8888/User/changestatus1',
        {"id":this.id,"status":this.status},
        {emulateJSON: true}
      ).then(function (res) {
        if (res.data.code == 200) {
          alert(res.data.msg);
        }
      })

    },
    selectUsersbySchool() {
      this.$http.post('http://localhost:8888/User/findUserBySchool',
        {"professional": this.UsersForm.professional},
        {emulateJSON: true}
      ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
            this.users = res.data.data;
          } else {
            alert(res.data.msg);
          }
        }
      )
    },

    findAllUser() {
      this.$http.post('http://localhost:8888/User/findAllUser',
      ).then(function (res) {
        this.users = res.data.data;
      })
    },
    deleteUser(iid){
      this.userid=iid;
      this.$confirm('是否确认删除该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post('http://localhost:8888/User/deleteUserByID',
          {"id": this.userid},
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
    changeState(username){
      this.$confirm('是否禁言该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.UsersForm.username = username;
        this.$http.post('http://localhost:8888/User/changestate',
          {"username": this.UsersForm.username},
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
          message: '已取消禁言'
        });
      });
    },
    changeState2(username){
      this.$confirm('是否取消禁言该用户?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.UsersForm.username = username;
        this.$http.post('http://localhost:8888/User/changestate2',
          {"username": this.UsersForm.username},
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
          message: '已取消取消禁言'
        });
      });
    },
    select() {
      if(this.UsersForm.username.length===0&&this.UsersForm.professional.length===0&&this.UsersForm.studentnumber.length===0){
        this.$message("请输入");
      }else {
        this.$http.post('http://localhost:8888/User/select1',
          {
            "username": this.UsersForm.username,
            "professional": this.UsersForm.professional,
            "studentnumber": this.UsersForm.studentnumber
          },
          {emulateJSON: true}
        ).then(function (res) {
          if (res.data.code == 200) {
            alert(res.data.msg);
            this.users = res.data.data;
          } else {
            alert(res.data.msg);
          }
        })
      }
    }
  },
  created(){
    this.findAllUser();
    this.findAllUser(); //两次加载
  }
}
</script>

<style scoped>

.img1{
  width:50px;
  height:80%;
  border-radius: 50%;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}


</style>
