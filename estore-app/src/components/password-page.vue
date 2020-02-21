<template>
  <mu-paper id="password-page" :z-depth="1">
    <mu-form :model="form">
      <mu-form-item label="旧的密码">
        <mu-text-field v-model="form.oldPw"
          :action-icon="oldPasswordFlag ? 'visibility' : 'visibility_off'"
          :action-click="() => (oldPasswordFlag = !oldPasswordFlag)"
          :type="oldPasswordFlag ? 'text' : 'password'">
        </mu-text-field>
      </mu-form-item>
      
      <mu-form-item label="新的密码">
        <mu-text-field v-model="form.newPw"
          :action-icon="newPasswordFlag ? 'visibility' : 'visibility_off'"
          :action-click="() => (newPasswordFlag = !newPasswordFlag)"
          :type="newPasswordFlag ? 'text' : 'password'">
        </mu-text-field>
      </mu-form-item>

      <mu-form-item>
        <mu-button @click="submit" color="primary" full-width
          style="margin: auto; margin-top: 20px;">
          确定
        </mu-button>
      </mu-form-item>
      
      <mu-form-item>
        <mu-button @click="cancel" full-width
          style="margin: auto; margin-top: -10px;">
           取消
        </mu-button>
      </mu-form-item>
    </mu-form>
  </mu-paper>
</template>

<script>
export default {
  props: {
    userId: String,
    role: Number
  },
  data() {
    return {
      oldPasswordFlag: false,
      newPasswordFlag: false,
      form: {
        oldPw: '',
        newPw: ''
      },
      routeName: [ 'cashier', 'shopkeeper' ],
    }
  },
  methods: {
    submit() {
      let _this = this
      this.form.userId = this.userId
      this.axios.post('/api/user/password', this.form)
      .then(() => {
        _this.$router.push({
          name: _this.routeName[_this.role],
          params: { userId: _this.userId }
        })
      })
      .catch(error => {
        if (error.response.status == 419) {
          _this.$toast.error('旧密码错误！');
        } else {
          _this.$toast.error('未知错误！');
        }
      })
    },
    cancel() {
      let _this = this
      this.$router.push({
        name: _this.routeName[_this.role],
        params: { userId: _this.userId }
      })
    }
  }
}
</script>

<style>
#password-page {
  position: absolute;
  margin: auto;
  padding: 30px 30px;
  display: block;
  max-width: 320px;
  max-height: 340px;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
}
</style>
