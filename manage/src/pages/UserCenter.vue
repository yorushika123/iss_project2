<template>
  <div>
<!--    <HomeHeader></HomeHeader>-->
  <el-container style="height: 100%">
    <el-header style="display: flex;justify-content: space-between;align-items: center" >
      <div style="color: #E6A23C;font-size: 20px">🧀&nbsp芝士校园个人中心</div>
      <div class="el-header-right">
        <el-dropdown>
          <span class="el-dropdown-link">
      {{username}}
     <i class="el-icon-caret-bottom" style="color: #ffffff">
   </i>
   </span>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </el-header>
    <el-container>
      <el-aside width="200px" style="">
        <router-link to="/WriteMsg">
          <el-button type="danger" round style="width: 200px">写帖子</el-button>
        </router-link>
        <el-menu

          default-active="$route.path" router>
          <el-submenu>
            <template slot="title"><i class="el-icon-document"></i>管理</template>
            <el-menu-item-group>
              <router-link to="/PostManager">
                <el-menu-item index="1-1"><i class="el-icon-message"></i><span slot="title">帖子管理</span></el-menu-item>
              </router-link>
              <router-link to="/CommentManager">
                <el-menu-item index="1-2"><i class="el-icon-more"></i><span slot="title">评论管理</span></el-menu-item>
              </router-link>
            </el-menu-item-group>
          </el-submenu>
          <router-link to="/Data">
            <el-menu-item index="2"><i class="el-icon-sort-down"></i><span slot="title">数据</span></el-menu-item>
          </router-link>
          <router-link to="/MyQuestions">
            <el-menu-item index="3"><i class="el-icon-bell"></i><span slot="title">我的提问</span></el-menu-item>
          </router-link>
          <router-link to="/MyMessage">
            <el-menu-item index="4"><i class="el-icon-bell"></i><span slot="title">我的消息</span></el-menu-item>
          </router-link>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
  </div>
</template>

<script>
import bus from "../assets/js/bus";
import HomeHeader from "./HomeHeader";

export default {
  name: 'UserCenter',
  components:{HomeHeader},
  methods:{
    collapseChange () {
      this.collapse = !this.collapse
      bus.$emit('collapse', this.collapse)
    },
  }
}
</script>

<style scoped>
body{
  margin: 0;
  border: 0;
  padding: 0;
}
.el-header{
}
.header-right{
  display: flex;
}
.user-img{
  height: 50px;
  width: 50px;
  border-radius: 50%;
}
.el-aside {
  color: #333;
}
/*.el-main {*/
/*  background-color: #E9EEF3;*/
/*  color: #333;*/
/*  text-align: center;*/
/*  line-height: 160px;*/
/*}*/
</style>
