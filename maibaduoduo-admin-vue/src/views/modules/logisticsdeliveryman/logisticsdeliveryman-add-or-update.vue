<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="配送人员代码" prop="deliverymanCode">
      <el-input v-model="dataForm.deliverymanCode" placeholder="配送人员代码"></el-input>
    </el-form-item>
    <el-form-item label="配送人员名称" prop="deliverymanName">
      <el-input v-model="dataForm.deliverymanName" placeholder="配送人员名称"></el-input>
    </el-form-item>
    <el-form-item label="性别" prop="deliverymanSex">
      <el-input v-model="dataForm.deliverymanSex" placeholder="性别"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="deliverymanEmail">
      <el-input v-model="dataForm.deliverymanEmail" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="手机号" prop="deliverymanPhone">
      <el-input v-model="dataForm.deliverymanPhone" placeholder="手机号"></el-input>
    </el-form-item>
    <el-form-item label="状态" prop="status">
      <el-input v-model="dataForm.status" placeholder="状态"></el-input>
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
          deliverymanCode: '',
          deliverymanName: '',
          deliverymanSex: '',
          deliverymanEmail: '',
          deliverymanPhone: '',
          status: '',
          createUserId: '',
          createTime: '',
          tenantId: ''
        },
        dataRule: {
          deliverymanCode: [
            { required: true, message: '配送人员代码不能为空', trigger: 'blur' }
          ],
          deliverymanName: [
            { required: true, message: '配送人员名称不能为空', trigger: 'blur' }
          ],
          deliverymanSex: [
            { required: true, message: '性别不能为空', trigger: 'blur' }
          ],
          deliverymanEmail: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          deliverymanPhone: [
            { required: true, message: '手机号不能为空', trigger: 'blur' }
          ],
          status: [
            { required: true, message: '状态不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/logisticsdeliveryman/logisticsdeliveryman/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.deliverymanCode = data.logisticsDeliveryman.deliverymanCode
                this.dataForm.deliverymanName = data.logisticsDeliveryman.deliverymanName
                this.dataForm.deliverymanSex = data.logisticsDeliveryman.deliverymanSex
                this.dataForm.deliverymanEmail = data.logisticsDeliveryman.deliverymanEmail
                this.dataForm.deliverymanPhone = data.logisticsDeliveryman.deliverymanPhone
                this.dataForm.status = data.logisticsDeliveryman.status
                this.dataForm.createUserId = data.logisticsDeliveryman.createUserId
                this.dataForm.createTime = data.logisticsDeliveryman.createTime
                this.dataForm.tenantId = data.logisticsDeliveryman.tenantId
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
              url: this.$http.adornUrl(`/logisticsdeliveryman/logisticsdeliveryman/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'deliverymanCode': this.dataForm.deliverymanCode,
                'deliverymanName': this.dataForm.deliverymanName,
                'deliverymanSex': this.dataForm.deliverymanSex,
                'deliverymanEmail': this.dataForm.deliverymanEmail,
                'deliverymanPhone': this.dataForm.deliverymanPhone,
                'status': this.dataForm.status,
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
