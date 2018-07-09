<template>
    <el-cascader :options="options" 
                 v-bind="$attrs" 
                 v-bind:value="value" 
                 v-on="$listeners" 
                 :type="type" 
                 :dcGroup="dcGroup" 
                 :method="method"
                 :separator="separator" 
                 :popper-class="popperClass" 
                 :placeholder="placeholder" 
                 :disabled="disabled" 
                 :clearable="clearable"
                 :expand-trigger="expandTrigger" 
                 :show-all-levels="showAllLevels" 
                 :filterable="filterable" 
                 :debounce="debounce" 
                 :change-on-select="changeOnSelect"
                 :size="size" 
                 :before-filter="beforeFilter" 
                 @change="value=>emitEventHandler('change',value)" 
                 @active-item-change="active=>emitEventHandler('activeItemChange',active)"
                 @blur="()=>emitEventHandler('blur')" @focus="()=>emitEventHandler('focus')">
    </el-cascader>
</template>

<script>
    import {
        customAsync
    } from '@/api/async'
  import utils from 'utils'
    const dictMapper = (list, labelName, valueName) => list.map(item => ({
        label: item[labelName],
        value: item[valueName],
    }))
    export default {
        props: {
            dcGroup: {
                type: String,
            },
            type: {
                type: String,
                default: 'dcGroup'
            },
            method:String,
            notKeepAive: Boolean,
            value: Array,
            separator: {
                type: String,
                default: '/'
            },
            popperClass: String,
            placeholder: String,
            disabled: Boolean,
            clearable: {
                type: Boolean,
                default: true
            },
            expandTrigger: {
                type: String,
                default: 'click'
            },
            showAllLevels: {
                type: Boolean,
                default: true
            },
            filterable: {
                type: Boolean,
                default: true
            },
            debounce: {
                type: Number,
                default: 300
            },
            changeOnSelect: Boolean,
            size: String,
            beforeFilter: Function
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
                            vm.$emit('select', event.target.value)
                        }
                    }
                )
            }
        },
        data() {
            return {
                options: []
            }
        },
        created() {
            this.setOptions()
        },
        methods: {
            //字典组数据处理
            dcGroupDatahandle(res) {
                this.options = res.map(item => ({
                    value: item.dcCodeMain,
                    label: item.dcNameMain,
                    children: dictMapper(item.dcList, 'dcName', 'dcCode')
                }))
            },
            //设置级联器数据
            setOptions() {
                if (this.type == 'dcGroup') {
                    customAsync({
                            that: this,
                            method: 'getDicGroupList',
                            paramObj: {
                                notShowLoading: true,
                                dcGroup: this.dcGroup
                            },
                            callback: res => {
                                this.dcGroupDatahandle(res.body)
                            },
                            errorCallback: () => {},
                    })
                } else if (this.type == 'area') {
                    //初始化地区选择栏数据
                    let _method = this.method ? this.method : 'getAreaList'
                        customAsync({
                            that: this,
                            method: _method,
                            paramObj: {
                                notShowLoading: true
                            },
                            callback: res => {
                                this.options = utils.areaDataHandler(res.body)
                            },
                            errorCallback: () => {},
                        })
                } else {
                    console.log(`[没有type=${this.type}这种类型,目前仅支持'dcGroup'、'area'两种类型，默认type='dcGroup']`)
                }
            },
            //中转el$emit事件
            emitEventHandler(event) {
                this.$emit(event, ...Array.from(arguments).slice(1))
            }
        }
    }
</script>

<style>
</style>