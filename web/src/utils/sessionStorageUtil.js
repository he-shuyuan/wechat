let token_session_key = 'user_token_session_key'
let user_info_key = 'user_info_key'
let user_depList_key = 'user_depList_key'
let user_insId_key = 'user_insId_key'
let role_menu_list_key = 'role_menu_list_key'
export default {
  /**
     * 获取Item
     * @return {[type]} [description]
     */
    getItem:function(key){
      let param = window.sessionStorage.getItem(key);
      if(param){
        return JSON.parse(param)
      }else{
        return {};
      }
    },
    /**
     * 设置
     * @return {[type]} [description]
     */
    setItem:function(key,param){
       window.sessionStorage.setItem(key,JSON.stringify(param));
    },
    /**
     * 获取token
     * @return {[type]} [description]
     */
    getToken:function(){
      return window.sessionStorage.getItem(token_session_key);
    },
    /**
     * 设置
     * @return {[type]} [description]
     */
    setToken:function(token){
       window.sessionStorage.setItem(token_session_key,token);
    },
    /**
     * 设置用户信息
     * @param {[type]} userInfo [description]
     */
    setUserInfo:function(userInfo){
       window.sessionStorage.setItem(user_info_key,JSON.stringify(userInfo));
    },
    /**
     * 获取用户基本信息
     * @return {[type]} [description]
     */
    getUserInfo:function(){
      let userInfo = window.sessionStorage.getItem(user_info_key); 
      if(userInfo){
        return JSON.parse(userInfo);
      }else{
        return {};
      }
    },
    /**
     * 设置部门基本信息
     * @param {[type]} depList [description]
     */
    setDepList:function(depList){
        window.sessionStorage.setItem(user_depList_key,JSON.stringify(depList));
    },
     /**
     * 获取部门基本信息
     * @return {[type]} [description]
     */
    getDepList:function(){
      let depList = window.sessionStorage.getItem(user_depList_key); 
      if(depList){
        return JSON.parse(depList);
      }else{
        return [];
      }
    },

    /**
     * 获取机构id
     * @return {[type]} [description]
     */
    getInsId:function(){
      return window.sessionStorage.getItem(user_insId_key);
    },
    /**
     * 设置机构id
     * @return {[type]} [description]
     */
    setInsId:function(insId){
       window.sessionStorage.setItem(user_insId_key,insId);
       window.localStorage.setItem(user_insId_key, insId);
    },
    /**
     * 设置菜单
     * @param {[type]} menuList [description]
     */
    setMenuList:function(menuList){
       window.sessionStorage.setItem(role_menu_list_key,JSON.stringify(menuList));
    },
    /**
     * 获取菜单
     * @return {[type]} [description]
     */
    getMenuList:function(){
       let list = window.sessionStorage.getItem(role_menu_list_key);
       if(list){
        return JSON.parse(list);
      }else{
        return [];
      }
    },
    /**
     * 清空session
     * @return {[type]} [description]
     */
    removeAll:function(){
       window.sessionStorage.clear();
    }
}
