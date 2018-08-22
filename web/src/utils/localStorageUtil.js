export default {

    user_insId_key:'user_insId_key',

    setItem(name, content) {
        if (!name) return
        if (typeof content !== 'string') {
            content = JSON.stringify(content)
        }
        window.localStorage.setItem(name, content)
    },
    getItem(name) {
        if (!name) return
        return window.localStorage.getItem(name)
    },
     /**
     * 获取机构id
     * @return {[type]} [description]
     */
    getInsId:function(){
      return window.localStorage.getItem(this.user_insId_key);
    },
    /**
     * 设置机构id
     * @return {[type]} [description]
     */
    setInsId:function(insId){
       window.localStorage.setItem(this.user_insId_key, insId);
    },
}
