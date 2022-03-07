import Vue from 'vue'
import VueRouter from 'vue-router'
import BookManage from '../views/BookManage'
import AddBook from '../views/AddBook'
import Index from '../views/Index'
import BookUpdate from '../views/BookUpdate'
import Login from "../views/Login"
import Error from "../views/Error";
import User from "../views/user.vue"
import BookShow from "../views/BookShow"
import Start from "../views/Start.vue"

Vue.use(VueRouter)

const routes = [
    {
        path: "/",
        name: "图书管理",
        component: Index,
        show: true,
        redirect: "/BookShow",
        children: [
            {
                path: "/BookManage",
                name: "查询图书",
                component: BookManage
            },
            {
                path: "/AddBook",
                name: "添加图书",
                component: AddBook
            },
            {
                path: '/login',
                name: '注销/登录',
                component: Login
            }

        ]
    },
    {
        path: '/update',
        component: BookUpdate,
        show: false
    },
    {
        path: '/start',
        component: Start,
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/error',
        name: 'Error',
        component: Error
    },
    {
        path: '/user',
        name: 'User',
        component: User
    },
    {
        path: '/BookShow',
        name: 'BookShow',
        component: BookShow,
    }

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

/*router.beforeEach((to, from, next) => {

        if (to.path.startsWith('/login')) {
            window.localStorage.removeItem('access-admin')
            next()
        } else {
            let admin = JSON.parse(window.localStorage.getItem('access-admin'))
            if (!admin) {
                next({path: '/login'})
            } else {
                //校验token合法性
                axios({
                    url: 'http://localhost:8181/checkToken',
                    method: 'get',
                    headers: {
                        token: admin.token
                    }
                }).then((response) => {
                    console.log(response.data)
                    if (!response.data) {
                        console.log('校验失败')
                        next({path: '/error'})
                    }
                })
                next()
            }
        }
})*/

export default router
