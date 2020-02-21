<template>
	<mu-container>
		<mu-row gutter style="margin-top: 16px">
			<mu-col span="7" sm="10">
        <mu-auto-complete :data="searchKey" placeholder="输入供应商编号" :max-search-results="5"
          v-model="supplierId" full-width open-on-focus>
				</mu-auto-complete>
			</mu-col>
			<mu-col span="3" sm="2">
				<mu-button color="primary" full-width :disabled="(supplierId == null || supplierId == '')" @click="findSupplier">
					查找
				</mu-button>
			</mu-col>
			<mu-col span="3" sm="2" justify='end'>
				<mu-button color="primary" full-width @click="findAllSupplier">
					显示全部
				</mu-button>
			</mu-col>
		</mu-row>
		
		<mu-row style="margin:24px 0;">
		
			<mu-col span='12'>
				<mu-flex justify-content="end">
					<mu-button @click="addSupplier" fab color="primary" style="margin-top:-48px;margin-right: 16px;">
						<mu-icon value="add"></mu-icon>
					</mu-button>
				</mu-flex>
			</mu-col>
			<mu-col span="12">
				<mu-paper :z-depth="1">
					<mu-data-table height="400px" :data="supplier" :columns="supplierColumns">
						<template slot-scope="scope">
							<td>
								{{ scope.row.supplierId }}
							</td>
							<td>
								{{ scope.row.supplierName }}
							</td>
							<td>
								{{ scope.row.contactsName }}
							</td>
							<td>
								{{ scope.row.phone }}
							</td>
							<td>
								<mu-button icon color="primary" small @click="editGoods(scope.row.supplierId)">
									<mu-icon value="edit"></mu-icon>
								</mu-button>
								<mu-button icon color="error" small @click="removeGoods(scope.row.supplierId)">
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
						<mu-text-field prefix="供应商编号" v-model="slot.data.supplierId" />
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="供应商名称" v-model="slot.data.supplierName" />
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="联系人" v-model="slot.data.contactsName" />
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="联系电话" v-model="slot.data.phone" />
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
			supplierColumns: [{
					title: '供应商编号',
					name: 'supplierId'
				},
				{
					title: '供应商名称',
					width: 120,
					name: 'supplierName'
				},
				{
					title: '联系人',
					name: 'contactsName'
				},
				{
					title: '联系电话',
					name: 'phone'
				},
				{
					title: '操作',
					width: 140,
					name: 'delete'
				},
			],
			supplier: [],
			supplierId: '',
			searchKey: [],
		}),
		methods: {
			removeGoods(supplierId) {
				let _this = this
				this.$confirm('确定要删除？', '提示', {
					type: 'warning'
				}).then(({
					result
				}) => {
					if(result) {
						let i = 0
						_this.supplier.forEach((eachSupplier, index) => {
							if(eachSupplier.supplierId == supplierId) i = index
						})
						_this.axios.delete('/api/supplier/' + supplierId)
							.then(() => {
								_this.supplier.splice(i, 1)
								_this.searchKey.splice(i,1)
							})
							
					}
				})
			},
			editGoods(supplierId) {
				let i = 0,
					_this = this,
					supplier
				this.supplier.forEach((eachSupplier, index) => {
					if(eachSupplier.supplierId == supplierId) i = index
				})
				supplier = this.supplier[i]
				this.$refs.dataDialog.show({
					supplierId: supplier.supplierId,
					supplierName: supplier.supplierName,
					contactsName: supplier.contactsName,
					phone: supplier.phone
				}, newSupplier => {
					this.axios.put('/api/supplier/', newSupplier)
						.then(() => {
							_this.supplier.splice(i, 1)
							_this.supplier.push(newSupplier)
						})
				})
			},
			addSupplier() {
				let _this = this
				this.$refs.dataDialog.show({
					supplierId: '',
					supplierName: '',
					contactsName: '',
					phone: ''
				}, newSupplier => {
					this.axios.post('/api/supplier/', newSupplier)
						.then(() => {
							_this.supplier.push(newSupplier)
							_this.searchKey.push(newSupplier.supplierId)
						})
				})
			},
			findSupplier() {
				let _this = this
				this.supplier = [];
				this.axios.get('/api/supplier/' + this.supplierId)
					.then(response => {
						_this.supplier.push(response.data)
					})
					.catch(error => {
						console.log(error)
					})
			},
			findAllSupplier() {
				this.supplierId=''
				this.axios.get('/api/supplier/')
				.then(response => {
					this.supplier = response.data
				})
				.catch(error => {
					console.log(error)
				})
			},
		},
		mounted() {
			let _this = this
			this.axios.get('/api/supplier/')
				.then(response => {
					_this.supplier = response.data
					_this.supplier.forEach(function(supplier) {
						_this.searchKey.push(supplier.supplierId)
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