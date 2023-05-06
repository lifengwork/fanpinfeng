<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="仓库名称" prop="storeName">
      <el-input v-model="dataForm.storeName" placeholder="仓库名称"></el-input>
    </el-form-item>
    <el-form-item label="仓库简述" prop="storeDescription">
      <el-input v-model="dataForm.storeDescription" placeholder="仓库简述"></el-input>
    </el-form-item>
    <el-form-item label="创建者ID" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder="创建者ID"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="租户编码" prop="tenantId">
      <el-input v-model="dataForm.tenantId" placeholder="租户编码"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          storeName: '',
          storeDescription: '',
          createUserId: '',
          createTime: '',
          tenantId: ''
        },
        dataRule: {
          storeName: [
            { required: true, message: '仓库名称不能为空', trigger: 'blur' }
          ],
          storeDescription: [
            { required: true, message: '仓库简述不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '创建者ID不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          tenantId: [
            { required: true, message: '租户编码不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/store/store/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.storeName = data.store.storeName
                this.dataForm.storeDescription = data.store.storeDescription
                this.dataForm.createUserId = data.store.createUserId
                this.dataForm.createTime = data.store.createTime
                this.dataForm.tenantId = data.store.tenantId
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/store/store/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'storeName': this.dataForm.storeName,
                'storeDescription': this.dataForm.storeDescription,
                'createUserId': this.dataForm.createUserId,
                'createTime': this.dataForm.createTime,
                'tenantId': this.dataForm.tenantId
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
