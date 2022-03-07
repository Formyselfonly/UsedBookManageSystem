<template>
  <div class="login-container">
    <el-form
      :model="ruleForm"
      :rules="rules"
      status-icon
      ref="ruleForm"
      label-position="left"
      label-width="0px"
      class="demo-ruleForm login-page"
    >
      <h3 class="title">系统登录</h3>
      <el-form-item prop="username">
        <el-input
          type="text"
          v-model="ruleForm.username"
          auto-complete="off"
          placeholder="用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          type="password"
          v-model="ruleForm.password"
          auto-complete="off"
          placeholder="密码"
        ></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
            <el-button type="primary" style="width: 100%" @click="visitor"
            >我是游客</el-button
            >
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%" @click="handleSubmit"
          >登录</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      ruleForm: {
        username: '请输入用户名和密码',
        password: ''
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    }
  },
  methods: {
      visitor() {
          window.localStorage.removeItem('access-admin')
          this.$router.replace({ path: '/BookShow' })
      },
    handleSubmit() {
      this.$refs.ruleForm.validate((valid) => {
        if (valid) {
          let _this = this
          axios.get('http://localhost:8181/login', { params: _this.ruleForm }).then(function (response) {
            if (response.data != null) {
              localStorage.setItem('access-admin', JSON.stringify(response.data))
              _this.$router.replace({ path: '/start' })
            }
          })
        } else {
          console.log('error submit!');
          return false;
        }
      })
    }
  }
};
</script>

<style scoped>
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 50px auto;
  width: 350px;
  padding: 25px 35px 25px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>