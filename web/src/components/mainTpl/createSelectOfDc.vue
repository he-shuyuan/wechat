<template>
    <el-select v-bind="$attrs"
               v-bind:value="value" 
               v-on="$listeners" 
               :dcCodeMain="dcCodeMain" 
               :clearable="clearable || true"
               :placeholder="placeholder" 
               :size="size"
               :loading="loading"
               :collapse-tags="collapseTags"
               :popper-class="popperClass"
               :filterable="filterable || true" 
               :disabled="disabled"
               :multiple="multiple"
               :value-key="valueKey"
               :auto-complete="autoComplete"
               :multiple-limit="multipleLimit"
               :no-data-text="noDataText"
               :allow-create="allowCreate"
               @visible-change="visible => emitEventHandler('visible-change',visible)"
               @change="value=>emitEventHandler('change',value)"
               :name="name"
               @remove-tag="()=>emitEventHandler('remove-tag')"
               @clear="()=>emitEventHandler('clear')"
               @blur="()=>emitEventHandler('blur')"
               @focus="()=>emitEventHandler('focus')">
        <el-option v-for="(item,index) in initData" :disabled="optionDisabledHandler(index)" :key="item.value" :label="item.label" :value="item.value">
        </el-option>
    </el-select>
</template>

<script>
    import {
        customAsync
    } from '@/api/async'
    export default {
        props: {
            value: String,
            dcCodeMain: String,
            placeholder: String,
            size: String,
            name: String,
            clearable: Boolean,
            filterable: Boolean,
            disabled: Boolean,
            popperClass:String,
            noDataText:String,
            multiple:Boolean,
            valueKey:String,
            collapseTags:Boolean,
            multipleLimit:Number,
            autoComplete:{
                type:String,
                default:'off'
            },
            optionDisabled:[Number,Boolean,Array],
            allowCreate:Boolean,
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
                initData: [],
                selectValue: '',
                loading:false
            }
        },
        created() {
            this.setInitData()
        },
        methods: {
            setInitData() {
                    this.loading = true
                    let params = {}
                    params.dcCodeMain = this.dcCodeMain
                    params.notShowLoading = true
                    customAsync({
                        that: this,
                        method: 'getDicList',
                        paramObj: params,
                        callback: res => {
                            //sessionStorage储存区域列表
                            this.loading = false
                            this.initData = res.body.map(item => {
                                return {
                                    value: item.dcCode,
                                    label: item.dcname
                                }
                            })
                        },
                        errorCallback: () => {},
                    })
            },
            emitEventHandler(event) {
                this.$emit(event, ...Array.from(arguments).slice(1))
            },
            optionDisabledHandler(index) {
                if(typeof(this.optionDisabled) == 'boolean') {
                    return this.optionDisabled
                } else if(typeof(this.optionDisabled) == 'object') {
                   for(let key in this.optionDisabled) {
                       if(this.optionDisabled[key] == index) {
                        return true
                       }
                   }
                } else {
                    if(this.optionDisabled == index) {
                        return true
                    }
                }
            }
        }
    }
</script>

<style>
</style>