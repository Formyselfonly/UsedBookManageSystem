<template>
  <el-container style="height: 500px; border: 1px solid #eee">

    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">

      <el-menu router :default-openeds="['0', '1']">
        <el-submenu v-for="(item,index) in $router.options.routes" :index="index+''" v-if="item.show">
          <template slot="title">{{item.name}}</template>
          <el-menu-item v-for="(item2,index2) in item.children" :index="item2.path"
                        :class="$route.path==item2.path?'is-active':''">{{item2.name}}</el-menu-item>
        </el-submenu>
      </el-menu>

    </el-aside>

    <!--这个功能待实现 如果实现不了,就把下面的el-container块改为
                       <el-main><router-view></router-view></el-main>
    -->
    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>王小虎</span>
      </el-header>

      <el-main>
        <el-form
                style="width: 60%"
                :model="ruleForm"
                :rules="rules"
                ref="ruleForm"
                label-width="100px"
                class="demo-ruleForm"
        >
          <el-form-item label="图书名称" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>

          <el-form-item label="作者" prop="owner">
            <el-input v-model="ruleForm.owner"></el-input>
          </el-form-item>

          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>


</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        name: '',
        owner: ''
      },
      rules: {
        name: [
          { required: true, message: '图书名称不能为空', trigger: 'blur' }
        ],
        owner: [
          { required: true, message: '作者不能为空', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      const _this = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios.post('http://localhost:8181/book/save', this.ruleForm).then(function (resp) {
            if (resp.data == 'success') {
              _this.$alert('《' + _this.ruleForm.name + '》添加成功！', '消息', {
                confirmButtonText: '确定',
                callback: action => {
                  _this.$router.push('/BookManage')
                }
              })
            }
          })
        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

