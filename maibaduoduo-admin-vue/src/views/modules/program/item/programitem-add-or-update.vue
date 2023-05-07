<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="执行计划编码" prop="programId">
      <el-input v-model="dataForm.programId" placeholder="执行计划编码"></el-input>
    </el-form-item>
    <el-form-item label="操作人" prop="programItemOperator">
      <el-input v-model="dataForm.programItemOperator" placeholder="操作人"></el-input>
    </el-form-item>
    <el-form-item label="执行状态" prop="programItemStatus">
      <el-input v-model="dataForm.programItemStatus" placeholder="执行状态"></el-input>
    </el-form-item>
    <el-form-item label="执行时间" prop="programItemDate">
      <el-input v-model="dataForm.programItemDate" placeholder="执行时间"></el-input>
    </el-form-item>
    <el-form-item label="租户编码" prop="tenantId">
      <el-input v-model="dataForm.tenantId" placeholder="租户编码"></el-input>
    </el-form-item>
    <el-form-item label="创建者" prop="createUserId">
      <el-input v-model="dataForm.createUserId" placeholder="创建者"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
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
          programId: '',
          programItemOperator: '',
          programItemStatus: '',
          programItemDate: '',
          tenantId: '',
          createUserId: '',
          createTime: ''
        },
        dataRule: {
          programId: [
            { required: true, message: '执行计划编码不能为空', trigger: 'blur' }
          ],
          programItemOperator: [
            { required: true, message: '操作人不能为空', trigger: 'blur' }
          ],
          programItemStatus: [
            { required: true, message: '执行状态不能为空', trigger: 'blur' }
          ],
          programItemDate: [
            { required: true, message: '执行时间不能为空', trigger: 'blur' }
          ],
          tenantId: [
            { required: true, message: '租户编码不能为空', trigger: 'blur' }
          ],
          createUserId: [
            { required: true, message: '创建者不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/programitem/programitem/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.programId = data.programItem.programId
                this.dataForm.programItemOperator = data.programItem.programItemOperator
                this.dataForm.programItemStatus = data.programItem.programItemStatus
                this.dataForm.programItemDate = data.programItem.programItemDate
                this.dataForm.tenantId = data.programItem.tenantId
                this.dataForm.createUserId = data.programItem.createUserId
                this.dataForm.createTime = data.programItem.createTime
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
              url: this.$http.adornUrl(`/programitem/programitem/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'programId': this.dataForm.programId,
                'programItemOperator': this.dataForm.programItemOperator,
                'programItemStatus': this.dataForm.programItemStatus,
                'programItemDate': this.dataForm.programItemDate,
                'tenantId': this.dataForm.tenantId,
                'createUserId': this.dataForm.createUserId,
                'createTime': this.dataForm.createTime
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
