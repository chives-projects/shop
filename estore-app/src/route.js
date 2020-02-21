import loginPage from './components/login-page.vue'
import passwordPage from './components/password-page.vue'
import cashierPage from './components/cashier-page/cashier-page.vue'
import shopkeeperPage from './components/shopkeeper-page/shopkeeper-page.vue'

import goods from './components/shopkeeper-page/goods.vue'
import stock from './components/shopkeeper-page/stock.vue'
import supplier from './components/shopkeeper-page/supplier.vue'
import person from './components/shopkeeper-page/person.vue'
import order from './components/shopkeeper-page/order.vue'
import revenue from './components/shopkeeper-page/revenue.vue'

export const routes = [
  {
    name: 'login',
    path: '/login',
    alias: '/',
    component: loginPage,
    meta: { title: '登录',  noCache: true, affix: true ,content: 'disable'}
  },
  {
    name: 'password',
    path: '/password/:role/:userId',
    component: passwordPage,
    meta: { title: '修改密码',  noCache: true, affix: true ,content: 'disable'},
    props: true
  },
  {
    name: 'cashier',
    path: '/cashier/:userId',
    component: cashierPage,
    meta: { title: '收银',  noCache: true, affix: true ,content: 'disable'},
    props: true
  },
  {
    name: 'shopkeeper',
    path: '/shopkeeper/:userId',
    redirect: {
      name: 'goods'
    },
    component: shopkeeperPage,
    meta: { title: '管理',  noCache: true, affix: true ,content: 'disable'},
    props: true,
    children: [
        {
          name: 'goods',
          meta: { title: '商品',  noCache: true, affix: true ,content: 'disable'},
          path: 'goods',
          components: {
            content: goods
          }
        },
        {
          name: 'stock',
          meta: { title: '库存',  noCache: true, affix: true ,content: 'disable'},
          path: 'stock',
          components: {
            content: stock
          }
        },
        {
          name: 'supplier',
          meta: { title: '供应商',  noCache: true, affix: true ,content: 'disable'},
          path: 'supplier',
          components: {
            content: supplier
          }
        },
        {
          name: 'person',
          meta: { title: '人员',  noCache: true, affix: true ,content: 'disable'},
          path: 'person',
          components: {
            content: person
          }
        },
        {
          name: 'order',
          meta: { title: '订单',  noCache: true, affix: true ,content: 'disable'},
          path: 'order',
          components: {
            content: order
          }
        },
        {
          name: 'revenue',
          meta: { title: '营收',  noCache: true, affix: true ,content: 'disable'},
          path: 'revenue',
          components: {
            content: revenue
          }
        }
      ]
     }
   ]
