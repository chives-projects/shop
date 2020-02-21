<template>
	<mu-container>
		<mu-row gutter style="margin-top: 16px">
			<mu-col span="7" sm="10">
      <mu-auto-complete :data="searchKey" placeholder="输入商品编号" :max-search-results="5"
        v-model="goodsId" full-width open-on-focus>
      </mu-auto-complete>
			</mu-col>
			<mu-col span="3" sm="2">
				<mu-button color="primary" full-width :disabled="(goodsId == null || goodsId == '')" @click="findGoods">
					查找
				</mu-button>
			</mu-col>
			<mu-col span="3" sm="2" justify='end'>
				<mu-button color="primary" full-width @click="findAllGoods">
					显示全部
				</mu-button>
			</mu-col>
		</mu-row>
		
		<mu-row style="margin:24px 0;">
		
			<mu-col span='12'>
				<mu-flex justify-content="end">
					<mu-button @click="addGoods" fab color="primary" style="margin-top:-48px;margin-right: 16px;">
						<mu-icon value="add"></mu-icon>
					</mu-button>
				</mu-flex>
			</mu-col>
			<mu-col span="12">
				<mu-paper :z-depth="1">
					<mu-data-table height="400px" :data="goods" :columns="goodsColumns">
						<template slot-scope="scope">
							<td>
								{{ scope.row.goodsId }}
							</td>
							<td>
								{{ scope.row.name }}
							</td>
							<td>
								{{ scope.row.goodsPrice }}
							</td>
							<td>
								{{ scope.row.sellPrice }}
							</td>
							<td>
								{{ scope.row.unit }}
							</td>
							<td>
								{{ scope.row.stockNum }}
							</td>
							<td>
								<mu-button icon color="primary" small @click="editGoods(scope.row.goodsId)">
									<mu-icon value="edit"></mu-icon>
								</mu-button>
								<mu-button icon color="error" small @click="removeGoods(scope.row.goodsId)">
									<mu-icon value="delete"></mu-icon>
								</mu-button>
							</td>
						</template>
					</mu-data-table>
				</mu-paper>
			</mu-col>
		</mu-row>

		

		<data-dialog ref="dataDialog">
			<template #default="slot">
				<mu-list>
					<mu-list-item>
						<mu-text-field prefix="商品编号" v-model="slot.data.goodsId" />
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="商品名称" v-model="slot.data.name" />
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="进价" v-model="slot.data.goodsPrice" />
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="售价" v-model="slot.data.sellPrice" />
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="规格" v-model="slot.data.unit" />
					</mu-list-item>
				</mu-list>
			</template>
		</data-dialog>
	</mu-container>
</template>
<script>
	import dataDialog from './data-dialog.vue'

	export default {
		data: () => ({
			goodsColumns: [{
					title: '商品编号',
					name: 'goodsId'
				},
				{
					title: '商品名称',
					width: 120,
					name: 'name'
				},
				{
					title: '进价',
					name: 'goodsPrice'
				},
				{
					title: '售价',
					name: 'sellPrice'
				},
				{
					title: '规格',
					name: 'unit'
				},
				{
					title: '库存',
					name: 'stockNum'
				},
				{
					title: '操作',
					width: 140,
					name: 'delete'
				},
			],
			goods: [],
			goodsId: '',
			searchKey: [],
		}),
		methods: {
			removeGoods(goodsId) {
				let _this = this
				this.$confirm('确定要删除？', '提示', {
					type: 'warning'
				}).then(({
					result
				}) => {
					if(result) {
						let i = 0
						_this.goods.forEach((eachGoods, index) => {
							if(eachGoods.goodsId == goodsId) i = index
						})
						_this.axios.delete('/api/goods/' + goodsId)
							.then(() => {
								_this.goods.splice(i, 1)
								_this.searchKey.splice(i,1)
							})
              .catch(() => {
                _this.$toast.error('发生错误，无法删除！');
              })
							
					}
				})
			},
			editGoods(goodsId) {
				let i = 0,
					_this = this,
					goods
				this.goods.forEach((eachGoods, index) => {
					if(eachGoods.goodsId == goodsId) i = index
				})
				goods = this.goods[i]
				this.$refs.dataDialog.show({
					goodsId: goods.goodsId,
					name: goods.name,
					goodsPrice: goods.goodsPrice,
					sellPrice: goods.sellPrice,
					unit: goods.unit,
					stockNum: goods.stockNum
				}, newGoods => {
					this.axios.put('/api/goods/', newGoods)
						.then(() => {
							_this.goods.splice(i, 1)
							_this.goods.push(newGoods)
						})
				})
			},
			addGoods() {
				let _this = this
				this.$refs.dataDialog.show({
					goodsId: '',
					name: '',
					goodsPrice: 0,
					sellPrice: 0,
					unit: '',
					stockNum: 0
				}, newGoods => {
					this.axios.post('/api/goods/', newGoods)
						.then(() => {
							_this.goods.push(newGoods)
							_this.searchKey.push(newGoods.goodsId)
						})
				})
			},
			findGoods() {
				let _this = this
				this.goods = [];
				this.axios.get('/api/goods/' + this.goodsId)
					.then(response => {
						_this.goods.push(response.data)
					})
					.catch(error => {
						console.log(error)
					})
			},
			findAllGoods() {
				this.goodsId=''
				this.axios.get('/api/goods/')
				.then(response => {
					this.goods = response.data
				})
				.catch(error => {
					console.log(error)
				})
			},
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
		},
		components: {
			'dataDialog': dataDialog
		}
	}
</script>

<style>
	tbody tr td {
		padding: 24px 0;
	}
</style>