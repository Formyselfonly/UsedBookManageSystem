<template>
  <div>
    <el-table :data="tableData" border style="width: 70%">
      <el-table-column fixed prop="id" label="编号" width="150">
      </el-table-column>
      <el-table-column prop="name" label="图书名" width="120">
      </el-table-column>
      <el-table-column prop="owner" label="作者" width="120"> </el-table-column>
      <el-table-column fixed="right" label="操作" width="100">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row)" type="text" size="small"
            >修改</el-button
          >
          <el-button @click="deleteBook(scope.row)" type="text" size="small"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="pageSize"
      :total="total"
      @current-change="page"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  methods: {
    deleteBook(row) {
      const _this = this
      axios.delete('http://localhost:8181/book/deleteById/' + row.id).then(function (resp) {
        _this.$alert('《' + row.name + '》删除成功！', '消息', {
          confirmButtonText: '确定',
          callback: action => {
            window.location.reload()
          }
        })
      })
    },
    edit(row) {
      this.$router.push({
        path: '/update',
        query: {
          id: row.id
        }
      })
    },
    page(currentPage) {
      const _this = this
      axios.get('http://localhost:8181/book/findAll/' + (currentPage - 1) + '/6').then(function (resp) {
        console.log(resp)
        _this.tableData = resp.data.content
        _this.pageSize = resp.data.size
        _this.total = resp.data.totalElements
      })
    }
  },

  data() {
    return {
      pageSize: '1',
      total: '11',
      tableData: [{
        id: 1,
        name: '解忧杂货店',
        owner: '东野圭吾'
      }, {
        id: 2,
        name: '追风筝的人',
        owner: '卡勒德·胡赛尼'
      }, {
        id: 3,
        name: '人间失格',
        owner: '太宰治'
      }]
    }
  },

  created() {
    const _this = this
    axios.get('http://localhost:8181/book/findAll/1/5').then(function (resp) {
      console.log(resp)
      _this.tableData = resp.data.content
      _this.pageSize = resp.data.size
      _this.total = resp.data.totalElements
    })
  }
}
</script>