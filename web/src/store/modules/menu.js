import sessionStorageUtil from "@/utils/sessionStorageUtil"
const menu = {
  state: {
    menuList:sessionStorageUtil.getMenuList() || []
  },

  mutations: {
    SET_MENU_LIST: (state, menuList) => {
      state.menuList = menuList
    }/*,
    SET_AUTH_MENU_LIST: (state, authMenuList) => {
      state.authMenuList = authMenuList
    }*/
  },

  actions: {
    // 登录
    changeIns({ commit }, data) {
      sessionStorageUtil.setMenuList(data.menu)
     /* util.sStore.setItem(util.sStore.AUTH_MENU_LIST,data.funCodeList)*/
      commit('DEL_ALL_VIEWS')
      if(data.route){
            data.route.replace('/default')
      }
      commit('SET_MENU_LIST', data.menu)
      // commit('SET_AUTH_MENU_LIST', data.funCodeList)
    },
  }
}

export default menu
