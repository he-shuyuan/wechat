import util from "../../utils/"
const menu = {
  state: {
   /*  menuList:util.sStore.getItem(util.sStore.MENU_LIST) || [],
     authMenuList:util.sStore.getItem(util.sStore.AUTH_MENU_LIST) || []*/
  },

  mutations: {
    SET_MENU_LIST: (state, menuList) => {
      state.menuList = menuList
    },
    SET_AUTH_MENU_LIST: (state, authMenuList) => {
      state.authMenuList = authMenuList
    }
  },

  actions: {
    // 登录
    changeIns({ commit }, data) {
      /*util.sStore.setItem(util.sStore.MENU_LIST,data.functionList)
      util.sStore.setItem(util.sStore.AUTH_MENU_LIST,data.funCodeList)*/
      commit('DEL_ALL_VIEWS')
      if(data.router){
            data.router.push({name:'Dashboard'})
      }
      commit('SET_MENU_LIST', data.functionList)
      commit('SET_AUTH_MENU_LIST', data.funCodeList)
    },
  }
}

export default menu
