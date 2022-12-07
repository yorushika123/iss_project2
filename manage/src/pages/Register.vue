<template>
  <div class="container">
    <div class="box" >
      <div style="display:flex;">
        <el-form>
        <el-form-item prop="avatar"  label="上传头像:" label-width="100px">
        </el-form-item>
        </el-form>
        <div class="avatar_box">
<!--        <img :src="this.img1" alt="" srcset=""  v-show="a==1">-->
<!--        <input type="file" name="" id="" @change="getImg"  ref="ipt" style="margin-top: 45px" v-show="a==2">-->
        <el-upload style="width: 100%;height: 100%"  v-show="a==2"
          class="avatar-uploader"
          action="#"
          :show-file-list="false"
          :http-request="httpRequest"
          :before-upload="beforeAvatarUpload">
          <i  class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <img v-if="imageUrl" :src="imageUrl" class="avatar" v-show="a==1">
      </div>
      </div>

      <el-form style="margin-top: 20px" ref="RegisterFormRef" :model="registerForm" :rules="registerFormRules" class="form" label-position="left">

        <el-form-item  prop="account" label="用户名：" label-width="100px">
          <el-input v-model=" registerForm.account"  placeholder="请输入用户名" prefix-icon="el-icon-user" ></el-input>
        </el-form-item>

        <el-form-item prop="password"  label="密码:" label-width="100px">
          <el-input placeholder="请输入密码" v-model="registerForm.password" prefix-icon="el-icon-lock" type="password" ></el-input>
        </el-form-item>

        <el-form-item prop="password2"  label="确认密码:" label-width="100px">
          <el-input placeholder="请确认密码" v-model="registerForm.password2" prefix-icon="el-icon-lock" type="password" ></el-input>
        </el-form-item>

        <el-form-item prop="professional" label="专业名" label-width="100px">
          <el-select v-model="registerForm.professional" placeholder="请选择专业名">
            <el-option label="互联网软件开发" value="互联网软件开发"></el-option>
            <el-option label="大数据" value="大数据"></el-option>
            <el-option label="区块链" value="区块链"></el-option>
            <el-option label="音乐" value="音乐"></el-option>
            <el-option label="体育" value="体育"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item prop="studentnumber"  label="学号:" label-width="100px">
          <el-input placeholder="请确认学号" v-model="registerForm.studentnumber" prefix-icon="el-icon-lock"></el-input>
        </el-form-item>

        <el-form-item prop="phoneNumber"  label="手机号:" label-width="100px">
          <el-input placeholder="请确认手机号" v-model="registerForm.phoneNumber" prefix-icon="el-icon-lock" ></el-input>
        </el-form-item>
        <div style="width: 100%;height: 100%;text-align: center">
          <el-button type="primary" @click="register" style="margin-right:30px ">注册</el-button>
          <el-button @click="resetForm('registerForm')">重置</el-button>
        </div>
      </el-form>
    </div>

  </div>
</template>

<script>
import login from "./Login";

export default {
  name: 'Register',
  data() {
    return {
      a:2,
      imageUrl: '',
      imageFile:'',
      img1:'',
      registerForm: {professional: '', account: '', password: '', password2: '', phoneNumber: '',studentnumber:''},
      // 正则表达式的相关校验
      registerFormRules: {
        professional: [
          {required: true, message: '请输入专业名称', trigger: 'blur'},
          {min: 1, max: 10, message: '长度在 1 到 10 个字符', trigger: ['blur', 'change']}
        ],
        account: [
          {required: true, message: '请输入用户名或邮箱', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 10 个字符', trigger: ['blur', 'change']}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, max: 20, message: '长度在 1 到 10 个字符', trigger: ['blur', 'change']}
        ],
        password2: [
          {required: true, message: '请确认密码', trigger: 'blur'},
          {min: 1, max: 20, message: '与上输入的密码相同', trigger: ['blur', 'change']}
        ],
        phoneNumber: [
          {required: true, message: '请输入手机号码', trigger: 'blur'},
          {len: 11, message: '长度为11位', trigger: ['blur', 'change']}
        ],
        studentnumber: [
          {required: true, message: '请输入学号', trigger: 'blur'},
        ]
      }
    }
  },
  methods: {
    httpRequest(data){
      let that = this
      let rd = new FileReader()
      let file = data.file
      this.imageFile = data.file
      rd.readAsDataURL(file)
      rd.onloadend = function (e) {
        that.imageUrl = this.result
      }
      this.a=1;
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    },
    getImg() {
      let that = this; // vue实例
      let reads = new FileReader();//读取文件的实例
      //this.$refs.ipt获取某个元素
      let url = this.$refs.ipt.files[0];
      reads.readAsDataURL(url); //转化为base64形式
      reads.onload = function (e) {
        that.img1 = this.result;//this.result转化为base64形式的地址,this指向当前方法onloadend的作用域
      }
      this.a = 1;
    },
    // 清空表单的校验
    resetForm() {
      this.$refs.RegisterFormRef.resetFields()
    },
    register: function () {
      if (this.registerForm.account == "" || this.registerForm.password == "" || this.registerForm.password2 == "" || this.registerForm.professional == "" || this.registerForm.phoneNumber == "") {
        this.$message("请输入完全");
      } else if (this.registerForm.password != this.registerForm.password2) {
        this.$message("两次输入密码不同！");
      } else if (this.registerForm.phoneNumber.length != 11 || isNaN(this.registerForm.phoneNumber)) {
        this.$message("手机号码输入错误！");
      } else if (this.registerForm.account.length > 20) {
        this.$message("用户名长度超出！");
      } else if (this.registerForm.professional.length > 20) {
        this.$message("专业名长度超出！");
      } else {
        this.$http.post('http://localhost:8888/User/registerStatus', {
            "username": this.registerForm.account,
            "password": this.registerForm.password,
            "professional": this.registerForm.professional,
            "phoneNumber": this.registerForm.phoneNumber,
            "studentnumber": this.registerForm.studentnumber,
            "img1": this.imageUrl,
          },
          {emulateJSON: true}
        ).then(function (res) {
          if (res.data.code == 200) {
            this.$message.success(res.data.msg);
            this.$router.push({path: "/Login"})
          } else {
            this.$message.error("已有账号,注册失败");
          }
        });
      }
    }
  }
}
</script>

<style scoped>
.container {
  background-size: 100%;
  background-image: url("../assets/img/bg07.jpg");
  height: 100vh;
  width: 100%;
}

.box {
  width: 450px;
  height: 500px;
  background-color: #fff;
  border-radius: 3px;
  border-color: transparent;
  padding-top: 20px;
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
  /*background-image: url("../assets/img/bg07.jpg");*/
}
.avatar_box img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
  background-color: #eee;
}
.form {
  bottom: 0;
  width: 100%;
  padding: 0px 20px 40px 20px;
  box-sizing: border-box;
  /*position: absolute;*/
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
