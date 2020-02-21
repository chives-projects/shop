var store = {
  debug: true,
  state: {
    sessionToken: null
  },
  addSessionToken(token) {
    this.state.sessionToken = token
    if (this.debug) console.log(token)
  },
  clearSessionToken() {
    this.state.sessionToken = null
  }
}
export default store
