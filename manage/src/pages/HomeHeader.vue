<template>
  <el-header style="height: 45px">
    <el-row type="flex" align="center" style="height: 45px">
      <el-col span="7">
        <div style="display: flex;align-items: center; height:100%;color: #E6A23C;font-size: 20px"><h3>🧀&nbsp芝士校园</h3></div>
      </el-col>
      <el-col span="8">
        <div style="display: flex;align-items: center;height: 100%;">
          <el-input placeholder="请输入搜索内容"></el-input>
          <el-button icon="el-icon-search" style="height: 40px;width: 70px">搜索</el-button>
        </div>
      </el-col>
      <el-col span="4">
        <div style="height: 100%;"></div>
      </el-col>
      <el-col span="2">
        <div style="display: flex;align-items:center;height: 100%;">
          <el-link :underline="false" href="http://127.0.0.1:8088/#/UserCenter"><p>个人中心</p></el-link>
        </div>
      </el-col>
      <el-col span="1">
        <div style="display:flex;align-items:center;height: 100%;width: 45px">
          <el-avatar :size="small" :src="users.avatar"></el-avatar>
        </div>
      </el-col>
      <el-col span="1">
        <div style="display: flex;height: 100%;align-items: center">
          <!--            {{username}}-->
          {{ users.username }}
        </div>
      </el-col>
      <el-col span="1">
        <el-dropdown style="display: flex;height: 100%;align-items: center"  @command="handleCommand">
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="logout" @click="localStorage.removeItem('user')">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-col>
    </el-row>
  </el-header>
</template>

<script>
export default {
  name: "HomeHeader",
  data(){
    return{
      users:{},
    }
  },
  methods:{
    getuser(){
      this.users = JSON.parse(localStorage.getItem('users'))
    },
    handleCommand (command) {
      if (command === 'logout') {
        localStorage.removeItem('users')
        this.$router.push('/login')
      }
    }
  },
  created() {
    this.getuser()
  }
}
</script>

<style scoped>
*{
  margin: 0;
  padding: 0;
}
.item{
  background-color: #eeeeee;
  width: 100%;
  height: 120px;
  border: 1px solid #ffebda;
}
</style>
