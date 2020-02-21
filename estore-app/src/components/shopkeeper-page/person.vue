<template>
	<mu-container>
		<mu-row style="margin:24px 0;">
			<mu-col span="12">
				<mu-paper :z-depth="1">
					<mu-data-table height="560px" :data="users" :columns="userColumns">
						<template slot-scope="scope">
							<td>
								{{ scope.row.userId }}
							</td>
							<td>
								{{ roles[scope.row.role] }}
							</td>
							<td>
								{{ scope.row.name }}
							</td>
							<td>
								{{ sexs[scope.row.sex] }}
							</td>
							<td>
								{{ scope.row.address }}
							</td>
							<td>
								<mu-button icon color="primary" small
									@click="editUser(scope.row.userId)">
									<mu-icon value="edit"></mu-icon>
								</mu-button>
								<mu-button icon color="error" small
									@click="removeUser(scope.row.userId)">
									<mu-icon value="delete"></mu-icon>
								</mu-button>
							</td>
						</template>
					</mu-data-table>
					<mu-flex justify-content="end">
					</mu-flex>
				</mu-paper>
			</mu-col>
		</mu-row>

		<mu-row gutter>
			<mu-col>
				<mu-flex justify-content="end">
					<mu-button @click="addUser"
						fab color="primary" style="margin-top:-48px;margin-right: 16px;">
						<mu-icon value="add"></mu-icon>
					</mu-button>
				</mu-flex>
			</mu-col>
		</mu-row>

		<data-dialog ref="dataDialog">
			<template #default="slot">
				<mu-list>
					<mu-list-item>
						<mu-text-field prefix="员工工号" v-model="slot.data.userId"/>
					</mu-list-item>
					<mu-list-item>
						<mu-select v-model="slot.data.role">
							<mu-option v-for="(role, index) in roles"
							:key="role" :label="role" :value="index">
							</mu-option>
						</mu-select>
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="姓名" v-model="slot.data.name"/>
					</mu-list-item>
					<mu-list-item>
						<mu-select v-model="slot.data.sex">
							<mu-option v-for="(sex, index) in sexs"
							:key="sex" :label="sex" :value="index">
							</mu-option>
						</mu-select>
					</mu-list-item>
					<mu-list-item>
						<mu-text-field prefix="地址" v-model="slot.data.address"/>
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
			userColumns: [
				{
					title: '员工工号',
					name: 'userId'
				},
				{
					title: '职位',
					name: 'role'
				},
				{
					title: '姓名',
					name: 'name'
				},
				{
					title: '性别',
					name: 'sex'
				},
				{
					title: '地址',
					name: 'address'
				},
				{
					title: '操作',
					width: 140,
					name: 'delete'
				},
			],
			users: [],
      roles: ['收银员', '店长'],
      sexs: ['男', '女']
		}),
		methods: {
			removeUser(userId) {
				let _this = this
				this.$confirm('确定要删除？', '提示', {
					type: 'warning'
				}).then(({ result }) => {
					if (result) {
						let i = 0
						_this.users.forEach((eachUser, index) => {
							if(eachUser.userId == userId) i = index
						})
						_this.axios.delete('/api/user/' + userId)
						.then(() => {
							_this.users.splice(i, 1)
						})
					}
				})
			},
			editUser(userId) {
				let i = 0, _this = this, user
				this.users.forEach((eachUser, index) => {
					if(eachUser.userId == userId) i = index
				})
				user = this.users[i]
				this.$refs.dataDialog.show({
					userId: user.userId,
					password: user.password,
					role: user.role,
					name: user.name,
          sex: user.sex,
          address: user.address
				}, newUser => {
					this.axios.put('/api/user/', newUser)
					.then(() => {
						_this.users.splice(i, 1)
						_this.users.push(newUser)
					})
				})
			},
			addUser() {
				let _this = this
				this.$refs.dataDialog.show({
          userId: '',
					password: '4321', // defalut
					role: 0,
					name: '',
          sex: 0,
          address: ''
				}, newUser => {
					this.axios.post('/api/user/', newUser)
					.then(() => {
						_this.users.push(newUser)
					})
				})
			},
		},
		mounted() {
			let _this = this
			this.axios.get('/api/user/')
				.then(response => {
					_this.users = response.data
          console.log(_this.users)
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
