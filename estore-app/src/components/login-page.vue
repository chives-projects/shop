<template>
  <mu-paper id="login-page" :z-depth="1">
    <mu-form :model="form">
      <mu-form-item label="登录身份" prop="identity">
        <mu-select v-model="form.role">
          <mu-option v-for="(role, index) in roles"
            :key="role" :label="role" :value="index">
          </mu-option>
        </mu-select>
      </mu-form-item>

      <mu-form-item label="用户账号" prop="userId">
        <mu-text-field v-model="form.userId">
        </mu-text-field>
      </mu-form-item>

      <mu-form-item label="用户密码" prop="password">
        <mu-text-field v-model="form.password"
          :action-icon="passwordVisibility ? 'visibility' : 'visibility_off'"
          :action-click="() => (passwordVisibility = !passwordVisibility)"
          :type="passwordVisibility ? 'text' : 'password'">
        </mu-text-field>
      </mu-form-item>

      <mu-form-item>
        <mu-button @click="submit" color="primary" full-width
          style="margin: auto; margin-top: 20px;">
          登录
        </mu-button>
      </mu-form-item>
    </mu-form>

    <mu-snackbar color="error" :open.sync="snackbarOpen">
      <mu-icon left value="warning"></mu-icon>
      用户名或密码错误!
    </mu-snackbar>
  </mu-paper>
</template>

<script>
export default {
  data() {
    return {
      roles: [ '收银员', '店长' ],
      form: {
        role: 0,
        userId: '',
        password: ''
      },
      passwordVisibility: false,
      snackbarOpen: false
    }
  },
  methods: {
    submit() {
      let _this = this
      this.axios.post('/api/login', this.form)
      .then(response => {
        let routeName = (_this.form.role == 0) ? 'cashier': 'shopkeeper'
        _this.$store.addSessionToken(response.data)
        _this.$router.push({
          name: routeName,
          params: { userId: _this.form.userId }
        })
      })
      .catch(error => {
        if (error.response.status == 418) {
          this.snackbarOpen = true
          setTimeout(() => {
            this.snackbarOpen = false
          }, 3000)
        }
      })
    }
  }
}
</script>

<style>
#login-page {
  position: absolute;
  margin: auto;
  padding: 30px 30px;
  display: block;
  max-width: 320px;
  max-height: 400px;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>
