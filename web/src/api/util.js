
export default{

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
}
}
var _guidGenerator = function() {
   return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
}

