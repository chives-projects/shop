<template>
  <div>
    <mu-row style="margin-bottom: 16px;">
      <mu-col span="12">
        <mu-appbar color="primary" :title="title">
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
      </mu-col>
    </mu-row>

    <mu-row style="margin-bottom: 16px;">
      <mu-container>
        <mu-row gutter>
          <mu-col span="7" sm="10">
            <mu-auto-complete full-width :data="searchKey"
              placeholder="输入商品编号" :max-search-results="5"
              v-model="goodsId" open-on-focus>
						</mu-auto-complete>
          </mu-col>
          <mu-col span="5" sm="2">
            <mu-button color="primary" full-width
              :disabled="(goodsId == null || goodsId == '')"
              @click="addOrderItem">
              添加
            </mu-button>
          </mu-col>
        </mu-row>


        <mu-paper :z-depth="1" style="margin-bottom: 16px;padding: 6px 8px;">
          <mu-row gutter>
            <mu-col span="3" sm="3" md="3" lg="3" xl="2"
              style="border-right: 1px solid #bdbdbd;border-left: 1px solid #bdbdbd;">
              <mu-row>
                <mu-col
                  style="text-align: center;font-weight: 600;">
                  实收金额
                </mu-col>
              </mu-row>
              <mu-row>
                <mu-col>
                  <div style="margin: auto;width: 60px;text-align: center;">
                    <span >￥</span>
                    <input style="border: none; width: 45px;outline: none;"
                      v-model="real"/>
                  </div>
                </mu-col>
              </mu-row>
            </mu-col>

            <mu-col span="3" sm="3" md="3" lg="3" xl="2"
              style="border-right: 1px solid #bdbdbd;">
              <mu-row>
                <mu-col
                  style="text-align: center;font-weight: 600;">
                  应收金额
                </mu-col>
              </mu-row>
              <mu-row>
                <mu-col style="text-align: center;">￥{{ total }}</mu-col>
              </mu-row>
            </mu-col>

            <mu-col span="3" sm="3" md="3" lg="3" xl="2"
              style="border-right: 1px solid #bdbdbd;">
              <mu-row>
                <mu-col
                  style="text-align: center;font-weight: 600;">
                  找零金额
                </mu-col>
              </mu-row>
              <mu-row>
                <mu-col style="text-align: center;">￥{{ change }}</mu-col>
              </mu-row>
            </mu-col>

            <mu-col span="3" sm="3" md="3" lg="3" xl="2">
              <mu-button
                full-width
                style="margin: 4px 0;" color="success"
                :disabled="(orderItems.length == 0)"
                @click="submitOrder">
                结账
              </mu-button>
            </mu-col>
          </mu-row>
        </mu-paper>

        <mu-row>
          <mu-col span="12">
            <mu-paper :z-depth="1">
              <mu-data-table
                height="436px"
                :data="orderItems"
                :columns="orderColumns">
                <template slot-scope="scope">
                  <td style="padding: 10px 20px;">
                    <mu-row
                      style="margin-bottom: 4px; font-size: 16px;font-weight: 700;">
                      <mu-col>{{ scope.row.name }}</mu-col>
                    </mu-row>
                    <mu-row style="margin-bottom: 8px;">
                      <mu-col>规格：{{ scope.row.unit }}</mu-col>
                    </mu-row>
                    <mu-row>
                      <mu-col style="color: #bdbdbd;">编号：{{ scope.row.goodsId }}</mu-col>
                    </mu-row>
                  </td>
                  <td>
                    <mu-text-field style="margin: auto;"
                      v-model="scope.row.quantity" prop="quantity"
                      type="number">
                    </mu-text-field>
                  </td>
                  <td>
                    ￥{{ scope.row.sellPrice }}
                  </td>
                  <td style="font-weight: 600;color: #2196f3;">
                    ￥{{ calcSubtotal(scope.row.sellPrice, scope.row.quantity) }}
                  </td>
                  <td>
                    <mu-button @click="removeOrderItem(scope.row.goodsId)"
                      style="margin: auto" icon color="error">
                      <mu-icon value="delete"></mu-icon>
                    </mu-button>
                  </td>
                </template>
              </mu-data-table>
            </mu-paper>
          </mu-col>
        </mu-row>
      </mu-container>
    </mu-row>

  </div>
</template>

<script>
	import {isInteger} from './../../utils/validator.js'
export default {
  props: {
    userId: String
  },
  data: () => ({
    title: '收银界面',
    goodsId: '',
    total: 0,
    real: 0,
    change: 0,
    searchKey: [],
    orderItems: [],
    orderColumns: [
      { title: '商品', name: 'goods' },
      { title: '数量', width: 120, name: 'quantity' },
      { title: '单价', name: 'unitPrice' },
      { title: '小计', name: 'subtotal' },
      { title: '', width: 120, name: 'delete' },
    ],
    goods: [],
    rules: {
      quantity:[
        {validator:isInteger,trigger: 'blur'}
      ]
    }
  }),
  methods: {
    logout() {
      this.$store.clearSessionToken()
      this.$router.push({ path: '/login' })
    },
    modifiyPassword() {
      let _this = this
      this.$router.push({ 
        name: 'password',
        params: { role: 0, userId: _this.userId }
      })
    },
    removeOrderItem(goodsId) {
      let i = 0
      this.orderItems.forEach((orderItem, index) => {
        if (orderItem.goodsId == goodsId) i = index
      })
      this.orderItems.splice(i, 1);
    },
    addOrderItem() {
      let _this = this
      this.orderItems.forEach(item => {
					if(_this.goodsId == item.goodsId) {
						item.quantity++;
						_this.goodsId = ''
						return;
					}
				})
      this.goods.forEach(goods => {
        if (_this.goodsId == goods.goodsId){
          _this.orderItems.push({
            goodsId: goods.goodsId,
            name: goods.name,
            sellPrice: goods.sellPrice,
            unit: goods.unit,
            quantity: 1, // Default quantity
          })
          _this.goodsId = ''
        }
      })
    },
    submitOrder() {
      let items = []
      this.orderItems.forEach(item => {
        items.push({
          goodsNumber: item.goodsId,
          quantity: item.quantity,
          sellPrice: parseFloat(item.sellPrice)
        })
      })
      this.axios.post('/api/cashier/', {
        userId: this.userId,
        actualPayment: parseFloat(this.real),
        items: items
      })
      .then(() => {
        this.orderItems.splice(0, this.orderItems.length)
        this.$toast.success('交易成功')
      })
    }
  },
  watch: {
    orderItems: {
      handler(orderItems) {
        let summary = 0
        orderItems.forEach(orderItem => {
          summary += orderItem.sellPrice * orderItem.quantity
        })
        this.total = summary.toFixed(2)
        this.real = this.total
      },
      deep: true
    },
    real: function(newReal) {
      this.change = newReal - this.total
      this.change = this.change.toFixed(2)
    },
    total: function(newTotal) {
      this.change = this.real - newTotal
      this.change = this.change.toFixed(2)
    }
  },
  computed: {
    calcSubtotal() {
      return function(sellPrice, quantity) {
        let subtotal = sellPrice*quantity
        return subtotal.toFixed(2)
      }
    }
  },
  mounted() {
    let _this = this
    this.axios.get('/api/goods/')
    .then(response => {
      _this.goods = response.data
      _this.goods.forEach(function(goods) {
						_this.searchKey.push(goods.goodsId)
			})
    })
    .catch(error => {
      console.log(error)
    })
  }
}
</script>
