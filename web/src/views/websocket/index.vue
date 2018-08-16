<template>
<div>
        <h3>WebSocket 聊天室：</h3>
        <textarea id="responseText" v-model="input.content" style="width: 500px; height: 300px;"></textarea>
        <br>
         <input type="text" v-model="input.sendMsg"> <input type="button" value="发送消息" @click="sendMessage">
        <input type="button" @click="()=>this.input.content=''" value="清空聊天记录">
</div>
</template>

<script>
    export default {
        data() {
            return {
              input:{
                content:'wqwqw',
                sendMsg:'',
               },
               socket:null,
            }
        },
        created(){
          var ipaddress="127.0.0.1";
          //新建socket对象
         this.socket = new WebSocket("ws://"+ipaddress+":7397?id=12345");
        //监听netty服务器消息并打印到页面上
         this.socket.onmessage = this.onmessage;
         this.socket.onopen=this.onopen;
        },
        mounted() {
           
        },
        methods: {
           sendMessage:function(){
            if(!this.input.sendMsg){
                alert('请先输入发送您内容')
                return;
            }
            this.input.content+=(this.input.content?'\n':'')+'#客户端：\n'+this.input.sendMsg;
            this.socket.send(this.input.sendMsg);
            this.input.sendMsg = '';
           },
           onmessage:function(event){
            console.log('收到消息',event.data)
            this.input.content+=(this.input.content?'\n':'')+'#服务器：\n'+event.data;
           },
           onopen:function(event){
              console.log('打开连接',event.data)
            }
            }
        }
   

</script>


