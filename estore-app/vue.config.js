module.exports = {
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8886',
        changeOrigin: true,
        pathRewrite: {
          "^/api": "/"
        }
      }
    }
  },
  transpileDependencies: [
    'vue-echarts',
    'resize-detector'
  ]
}
