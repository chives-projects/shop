<template>
	<mu-container>
		<mu-row style="margin:24px 0;">
			<mu-col span="12">
				<mu-paper :z-depth="1">
					<mu-data-table height="530px" :data="orders" :columns="orderColumns" @row-click="getOrderDetail">
            <template slot="expand">
              <mu-list>
                <mu-list-item v-for="(orderItem, index) in expandOrderItems" :key="index">
                  <div style="padding: 12px;" >
                    <span style="font-weight: 600;">{{ index+1 }}.</span>
                    <span style="padding: 12px;">商品名称：{{ orderItem.ordersPK.goods.name }}</span>
                    <span style="padding: 12px;">商品售价：{{ orderItem.ordersPK.goods.sellPrice }}</span>
                    <span style="padding: 12px;">数量：{{ orderItem.amount }}</span>
                  </div>
                </mu-list-item>
              </mu-list>
            </template>
            
						<template slot-scope="scope">
							<td>
								{{ scope.row.ordersId }}
							</td>
							<td>
								{{ scope.row.userId }}
							</td>
							<td>
								{{ scope.row.actualPayment }}
							</td>
							<td>
								{{ scope.row.createdtime }}
							</td>
							<td>
                {{ (scope.row.flag == 0) ? '否': '是' }}
              </td>
							<td>
                <mu-button :disabled="(scope.row.flag == 1)" icon color="error" small @click="revoke(scope.row.ordersId)">
                  <mu-icon value="exit_to_app"></mu-icon>
                </mu-button>
							</td>
						</template>
					</mu-data-table>
				</mu-paper>
			</mu-col>
		</mu-row>
	</mu-container>
</template>
<script>
	export default {
		data: () => ({
			orderColumns: [
				{
					title: '订单编号',
					name: 'ordersId'
				},
				{
					title: '员工工号',
					name: 'userId'
				},
				{
					title: '实付',
					name: 'actualPayment'
				},
				{
					title: '创建时间',
					name: 'createdtime'
				},
				{
          title: '是否已退货',
          name: 'flag'
        },
        {
          title: '操作',
          width: 140,
          name: 'operation'
        },
			],
			orders: [],
      expandOrderItems: []
		}),
		methods: {
      revoke(ordersId) {
        window.event.cancelBubble = true
        let _this = this
        this.$confirm('确定要退货？', '提示', {
          type: 'warning'
        }).then(({
          result
        }) => {
          if(result) {
            let i = 0
            _this.orders.forEach((eachOrder, index) => {
              if(eachOrder.ordersId == ordersId) i = index
            })
            _this.axios.delete('/api/orders/' + ordersId)
              .then(() => {
                _this.orders[i].flag = 1
              })
              .catch(() => {
                _this.$toast.error('发生错误，无法退货！')
              })
              
          }
        })
      },
      getOrderDetail(index, orders) {
        let _this = this
        this.axios.get('/api/ordersItem/' + orders.ordersId)
        .then(response => {
          _this.expandOrderItems = response.data
          console.log(_this.expandOrderItems)
        })
      }
		},
		mounted() {
			let _this = this
			this.axios.get('/api/orders/')
				.then(response => {
					_this.orders = response.data.map(item => {
						item.userId = item.user.userId
						return item
					})
				})
				.catch(error => {
					console.log(error)
				})
		}
	}
</script>

<style>
tbody tr td {
	padding: 24px 0;
}
</style>
