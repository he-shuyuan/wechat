let token_session_key = 'user_token_session_key'
export default {
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
    }
}
