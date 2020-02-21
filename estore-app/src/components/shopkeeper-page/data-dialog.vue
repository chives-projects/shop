<template>
  <mu-dialog width="380" scrollable :open.sync="open">
    <slot :data="contentData">
    </slot>
    <mu-flex style="margin-top: 18px;" justify-content="end">
      <mu-button @click="cancel" style="margin-right: 18px;">
        取消
      </mu-button>
      <mu-button @click="confirm" color="primary">
        确认
      </mu-button>
    </mu-flex>
  </mu-dialog>
</template>

<script>
export default {
  data() {
    return {
      open: false,
      contentData: {},
      confirmCallback: null
    }
  },
  methods: {
    show(contentData, confirmCallback) {
      this.open = true
      this.contentData = contentData || {}
      this.confirmCallback = confirmCallback || {}
    },
    confirm() {
      this.open = false
      this.confirmCallback(this.contentData)
    },
    cancel() {
      this.open = false
    }
  }
}
</script>
