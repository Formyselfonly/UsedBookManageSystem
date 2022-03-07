<template>
    <div style="margin-left: 400px">


        <el-container style="height: 500px; border: 1px solid #eee">



            <!--这个功能待实现 如果实现不了,就把下面的el-container块改为
                               <el-main><router-view></router-view></el-main>
            -->
            <el-container>
                <el-header style="text-align: right; font-size: 12px">
                    <el-dropdown>
                        <i class="el-icon-setting" style="margin-right: 15px"></i>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item> <a href="http://localhost:8080/login">登录/注销</a></el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                    <span>王小虎</span>
                </el-header>

                <el-main>
                    <el-table :data="tableData"   border style="width: 100%" >
                        <el-table-column fixed prop="id" label="编号" width="150">
                        </el-table-column>
                        <el-table-column prop="name" label="图书名" width="120">
                        </el-table-column>
                        <el-table-column prop="owner" label="作者" width="120"> </el-table-column>
                    </el-table>

                    <el-pagination
                            background
                            layout="prev, pager, next"
                            :page-size="pageSize"
                            :total="total"
                            @current-change="page"
                    >
                    </el-pagination>

                </el-main>
            </el-container>
        </el-container>



    </div>
</template>

<script>
    export default {
        methods: {
            page(currentPage) {
                //在这里的this才是指代的这个vue，如果美欧这个_this=this，
                // 那么下面那个代码块就没法取代vue，因为它里面的this这指的这个函数而不是vue
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
                total: '10',
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