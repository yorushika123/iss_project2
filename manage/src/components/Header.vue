<template>
<div class="header">
<!--折叠图片-->
  <div class="collapse-btn" @click="collapseChange()">
    <i class="el-icon-menu"></i>
  </div>

  <div class="logo"><img src="../assets/img/logo.png"/>&nbsp;芝士校园后台管理</div>
  <div class="header-right">
    <div class="btn-fullscreen" @click="handleFullScreen">
      <el-tooltip :content="fullscreen?`取消全屏`:`全屏`" placement="bottom">
      <i class="el-icon-rank"></i>
      </el-tooltip>
    </div>
    <div class="user-avator">
      <el-avatar :src="user.avatar"></el-avatar>
    </div>
    <el-dropdown class="user-name" trigger="click" @command="handleCommand">
   <span class="el-dropdown-link">
      {{user.username}}
     <i class="el-icon-caret-bottom" style="color: #ffffff">
   </i>
   </span>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item command="logout" @click="localStorage.removeItem('user')">退出登录</el-dropdown-item>
    </el-dropdown-menu>
    </el-dropdown>
  </div>
</div>
</template>

<script>

import bus from '../assets/js/bus'
export default {
  name: 'header',
  data () {
    return {
      user:{},
      collapse: false,
      fullscreen: false
    }
  },
  methods: {
    collapseChange () {
      this.collapse = !this.collapse
      bus.$emit('collapse', this.collapse)
    },
    handleFullScreen () {
      if (this.fullscreen) {
        if (document.exitFullscreen) {
          document.exitFullscreen()
        } else if (document.webkitCancelFullScreen) {
          document.webkitCancelFullScreen()
        } else if (document.mozCancelFullScreen) {
          document.mozCancelFullScreen()
        } else if (document.msEsExitFullScreen) {
          document.msEsExitFullScreen()
        }
      } else {
        let element = document.documentElement
        if (element.requestFullscreen) {
          element.requestFullscreen()
        } else if (element.webkitRequestFullScreen) {
          element.webkitRequestFullScreen()
        } else if (element.mozRequestFullScreen) {
          element.mozRequestFullScreen()
        } else if (element.msRequsetFullScreen) { /* ie */
          element.msRequsetFullScreen()
        }
      }
      this.fullscreen = !this.fullscreen
    },
    handleCommand (command) {
      if (command === 'logout') {
        localStorage.removeItem('users')
        this.$router.push('/login')
      }
    }
  },

  computed: {
  },
  created() {
    this.user= JSON.parse(localStorage.getItem('users'))
  }
}
</script>

<style scoped>
.header {
position: relative;
  background-image: url("../assets/img/menu-top.png");
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #ffffff;
         }
.collapse-btn{
  float: left;
  padding:0 21px;
  cursor: pointer;
  line-height: 70px;
}
.header .logo{
  float:left;
  line-height: 70px;
  text-align: center;
}
.logo img{
  height: 22px;
}
.header-right{
  float: right;
  padding-right: 50px;
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-fullscreen{
  transform: rotate(45deg);
  margin-right:5px;
  font-size: 24px;
}
.user-avator{
  margin-left: 20px;
}
.user-avator img{
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
}
.user-name{
  margin-left: 10px;
}
.el-dropdown-link{
  color: #ffffff;
  cursor: pointer;
}
</style>
