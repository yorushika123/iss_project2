<template>
  <div class="login_container">
    <div class="login_box">
      <div class="avatar_box">
        <img src="../assets/img/ban.png" alt="头像">
      </div>

      <el-form ref="LoginFormRef" :model="loginForm" :rules="LoginFormRules" class="login_form" label-position="left">
        <el-form-item prop="account" label="学号：" label-width="80px">
          <el-input v-model="loginForm.studentnumber"  placeholder="请输入学号" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <el-form-item prop="password"  label="密码:" label-width="80px">
          <el-input placeholder="请输入密码" v-model="loginForm.password" prefix-icon="el-icon-lock" type="password" ></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="2" style="float: right;margin-bottom: 10px">
            <div>
              <el-link type="info" class="top-title" @click="goRegister" style="">注册</el-link>
            </div>
          </el-col>
        </el-row>
          <div style="width: 100%;height: 100%;text-align: center">
          <el-button type="primary" @click="login" style="margin-right:30px;width: 100%">登录</el-button>
          </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import {getLoginStatus} from '../api/index'
import {mixin} from '../mixins/index'

export default {
  mixins: {mixin},
  name: 'Login',
  data () {
    return {
      loginForm: {password: '123', studentnumber:'202026702027',},
      // 正则表达式的相关校验
      LoginFormRules: {
        username: [{required: true, message: '请输入学号', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}]
      },
      user:{},
    }
  },
  methods: {
    login () {
      this.$http.post('http://localhost:8888/User/login', {
        studentnumber:this.loginForm.studentnumber,
        password:this.loginForm.password,
        }).then((res) => {
          if (res.data.code === 200) {
            localStorage.setItem('userName', res.data.data.username)
            localStorage.setItem('users', JSON.stringify(res.data.data))
            this.$store.commit('login', this.loginForm)
            var path = this.$route.query.redirect
            this.$router.replace('/Home')
            this.$message.success(res.msg)
          } else {
            this.$message.error(res.msg)
          }
        })
    },
    goRegister: function () {
      this.$router.push('/register')
    },
  }
}
</script>

<style scoped>
.top-title:hover {
  cursor: pointer;
  color: cornflowerblue !important;
}
.login_container {
  background-size: 100%;
  background-image: url("../assets/img/bg07.jpg");
  height: 100vh;
  width: 100%;
}

.login_box {
  width: 450px;
  height: 320px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.avatar_box {
  width: 130px;
  height: 130px;
  border: 1px solid #eee;
  border-radius: 50%;
  padding: 10px;
  box-shadow: 0 0 10px #ddd;
  position: absolute;
  left: 50%;
  transform: translate(-50%, -50%);
  background-image: url("../assets/img/bg07.jpg");
}
.avatar_box img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: #eee;
}
.login_form {
  bottom: 0;
  width: 100%;
  padding: 0px 20px 40px 20px;
  box-sizing: border-box;
  position: absolute;
}

</style>
