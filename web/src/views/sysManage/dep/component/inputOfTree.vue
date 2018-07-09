<template>
    <div class="tree-input-wrapper" @mouseover="showTree = true" @mouseout="showTree = false">
        <el-input :placeholder="placeholder" clearable v-bind="$attrs" v-bind:value="value" v-on="$listeners"></el-input>
        <div class="tree" v-show="showTree">
            <div class="header">请选择</div>
            <div class="tree-content">
                <el-tree :load="load" :lazy="lazy" :node-key="nodeKey" :props="props" highlight-current @node-click="handleNodeClick"></el-tree>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            placeholder: String,
            value: String,
            props: {
                type: Object,
                default: () => {
                    return {
                        children: 'children',
                        label: 'label'
                    }
                }
            },
            lazy: Boolean,
            load: Function,
            nodeKey: String
        },
        computed: {
            inputListeners: function () {
                var vm = this
                // `Object.assign` 将所有的对象合并为一个新对象
                return Object.assign({},
                    // 我们从父级添加所有的监听器
                    this.$listeners,
                    // 然后我们添加自定义监听器，
                    // 或覆写一些监听器的行为
                    {
                        // 这里确保组件配合 `v-model` 的工作
                        input: function (event) {
                            vm.$emit('input', event.target.value)
                        }
                    }
                )
            }
        },
        data() {
            return {
                showTree: false,
                dep: ''
            };
        },
        methods: {
            handleNodeClick(data) {
                console.log(data)
                this.$emit('nodeSelect', data)
                this.showTree = false
            }
        }
    }
</script>

<style scoped>
    .header {
        padding: 0 10px;
        background-color: #f5f5f5;
        border-color: #ddd;
    }

    .tree-input-wrapper {
        position: relative;
    }

    .tree {
        position: absolute;
        width: 100%;
        z-index: 1000;
        border: 1px solid #ddd;
    }

    .tree-content {
        max-height: 200px;
        overflow: auto;
    }
</style>