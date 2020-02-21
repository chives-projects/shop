import Vue from 'vue'

// route
import VueRouter from 'vue-router'
import { routes } from './route.js'
const router = new VueRouter({ routes })
Vue.use(VueRouter)

// muse ui
import MuseUI from 'muse-ui'
import Helpers from 'muse-ui/lib/Helpers'
import Message from 'muse-ui-message'
import Toast from 'muse-ui-toast'
import 'muse-ui/dist/muse-ui.css'
import 'material-icons/iconfont/material-icons.scss'
Vue.use(MuseUI)
Vue.use(Helpers)
Vue.use(Message)
Vue.use(Toast)

// axios
import VueAxios from 'vue-axios'
import axios from 'axios'
axios.interceptors.response.use(response => {
  return response
}, error => {
  if (error.status == 401) router.push({ path: '/login-page' })
  return Promise.reject(error)
})

Vue.use(VueAxios, axios)

// store
import store from './store.js'
Vue.prototype.$store = store

// echarts
import ECharts from 'vue-echarts'
Vue.component('v-chart', ECharts)

// Add format method to Date.
Date.prototype.format = function(formatStr)
{
    var str = formatStr;
    var Week = ['日','一','二','三','四','五','六'];
    var month = this.getMonth() + 1

    str=str.replace(/yyyy|YYYY/,this.getFullYear());
    str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

    str=str.replace(/MM/,month>9?month :'0' + month);
    str=str.replace(/M/g,month);

    str=str.replace(/w|W/g,Week[this.getDay()]);

    str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
    str=str.replace(/d|D/g,this.getDate());

    str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
    str=str.replace(/h|H/g,this.getHours());
    str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
    str=str.replace(/m/g,this.getMinutes());

    str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
    str=str.replace(/s|S/g,this.getSeconds());

    return str;
}

import App from './app.vue'
Vue.config.productionTip = false

router.beforeEach((to, from, next) => {
  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

new Vue({
  render: h => h(App),
  router,
  store
}).$mount('#app')
