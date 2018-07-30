<template>
    <section ref="appMain" class="app-main">
        <transition name="fade" mode="out-in">
            <!-- <router-view :key="key"></router-view> -->
            <router-view></router-view>
        </transition>
    </section>
</template>

<script>
    export default {
        name: 'AppMain',
        computed: {
            // key() {
            //   return this.$route.name !== undefined ? this.$route.name + +new Date() : this.$route + +new Date()
            // }
        cachedViews() {
                 return this.$store.state.tagsView.cachedViews
            },
        },
        mounted() {
            let bodyHeight = document.body.offsetHeight
            let that = this
            this.$refs.appMain.style.height = `${document.body.offsetHeight - 170}px`;
            window.onresize = function _resize() {
              if(that.$refs.appMain) {
                that.$refs.appMain.style.height = `${document.body.offsetHeight - 170}px`;
              }
            };
        }
    }
</script>