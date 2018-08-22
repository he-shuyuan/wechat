import sessionStorageUtil from '@/utils/sessionStorageUtil'

export default{

/**
 * 随机32位
 * @return {[type]} [description]
 */
 getUUID:function() {
    return (
       _guidGenerator() +
       _guidGenerator() +
       _guidGenerator() +
       _guidGenerator() +
       _guidGenerator() +
       _guidGenerator() +
       _guidGenerator() +
       _guidGenerator()
  )
},
sessionStorage:sessionStorageUtil,
}
var _guidGenerator = function() {
   return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
}

