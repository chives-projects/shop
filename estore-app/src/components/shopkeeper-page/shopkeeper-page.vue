<template>
  <div>
    <mu-drawer class="app-drawer"
      :z-depth="2"
      :docked="drawerDocked"
      :open.sync="drawerOpen"
      width="320px">
      <mu-list>
        <!--mu-list-item>
          <span>店长管理界面</span>
        </mu-list-item-->
        <mu-list-item button @click="routeTo('goods')">
          <mu-icon value="widgets" :size="24"></mu-icon>
          <span>商品</span>
        </mu-list-item>
        <mu-list-item button @click="routeTo('supplier')">
          <mu-icon value="airport_shuttle" :size="24"></mu-icon>
          <span>供应商</span>
        </mu-list-item>
        <mu-list-item button @click="routeTo('stock')">
          <mu-icon value="store" :size="24"></mu-icon>
          <span>库存</span>
        </mu-list-item>
        <mu-list-item button @click="routeTo('person')">
          <mu-icon value="person" :size="24"></mu-icon>
          <span>人员</span>
        </mu-list-item>
        <mu-list-item button @click="routeTo('order')">
          <mu-icon value="reorder" :size="24"></mu-icon>
          <span>订单</span>
        </mu-list-item>
        <mu-list-item button @click="routeTo('revenue')">
          <mu-icon value="attach_money" :size="24"></mu-icon>
          <span>营收</span>
        </mu-list-item>
      </mu-list>
    </mu-drawer>

    <mu-appbar style="width: 100%;" color="primary">
      <mu-button @click="drawerOpen = true" icon slot="left">
        <mu-icon value="menu"></mu-icon>
      </mu-button>
      <span>{{ title }}</span>
      <mu-menu slot="right">
        <mu-button flat>工号：{{ userId }}</mu-button>
        <mu-list slot="content">
          <mu-list-item button @click="modifiyPassword">
            <mu-list-item-title>修改密码</mu-list-item-title>
          </mu-list-item>
          <mu-list-item button @click="logout">
            <mu-list-item-title>注销</mu-list-item-title>
          </mu-list-item>
        </mu-list>
      </mu-menu>
    </mu-appbar>

    <mu-container>
      <router-view name="content"></router-view>
    </mu-container>
  </div>
</template>

<script>
export default {
  props: {
    userId: String
  },
  data() {
    return {
      title: '',
      drawerOpen: false,
      drawerDocked: false,
      titles: {
        goods: '商品',
        stock: '库存',
        person: '人员',
        order: '订单',
        revenue: '营收',
        supplier: '供应商'
      }
    }
  },
  methods: {
    routeTo(routeName) {
      if (this.$route.name != routeName) {
        this.$router.push({
          name: routeName
        })
      }
      this.drawerOpen = false

    },
    refresh() {
      this.title = this.titles[this.$route.name]
    },
    logout() {
      this.$store.clearSessionToken()
      this.$router.push({ name: 'login' })
    },
    modifiyPassword() {
      let _this = this
      this.$router.push({ 
        name: 'password',
        params: { role: 1, userId: _this.userId }
      })
    }
  },
  watch: {
    '$route': 'refresh'
  },
  mounted() {
    this.refresh()
  }
}
</script>

<style>
.mu-list {
  padding: 0 !important;
}
/* .app-drawer .mu-list li:first-child {
  background-color: #2196f3;
  height: 120px;
  line-height: 60px;
} */
/* .app-drawer .mu-list li:first-child span {
  color: white;
  font-size: 36px;
} */
.app-drawer .mu-item {
  padding: 35px 10px;
}
.app-drawer .mu-item .mu-icon {
  color: #757575;
}
.app-drawer .mu-item span {
  color: black;
  font-size: 16px;
  padding-left: 32px;
  font-weight: 700;
}
</style>
