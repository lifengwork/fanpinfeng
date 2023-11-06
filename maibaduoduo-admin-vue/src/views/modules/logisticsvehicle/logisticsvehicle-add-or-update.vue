<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="名称" prop="vehicleName">
      <el-input v-model="dataForm.vehicleName" placeholder="名称"></el-input>
    </el-form-item>
    <el-form-item label="简述" prop="vehicleDesc">
      <el-input v-model="dataForm.vehicleDesc" placeholder="简述"></el-input>
    </el-form-item>
    <el-form-item label="陆运、空运、水运" prop="vehicleType">
      <el-input v-model="dataForm.vehicleType" placeholder="陆运、空运、水运"></el-input>
    </el-form-item>
    <el-form-item label="地址" prop="vehicleAddress">
      <el-input v-model="dataForm.vehicleAddress" placeholder="地址"></el-input>
    </el-form-item>
    <el-form-item label="手机" prop="vehicleMobile">
      <el-input v-model="dataForm.vehicleMobile" placeholder="手机"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="vehicleEmail">
      <el-input v-model="dataForm.vehicleEmail" placeholder="邮箱"></el-input>
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
          vehicleName: '',
          vehicleDesc: '',
          vehicleType: '',
          vehicleAddress: '',
          vehicleMobile: '',
          vehicleEmail: '',
          status: '',
          createUserId: '',
          createTime: '',
          tenantId: ''
        },
        dataRule: {
          vehicleName: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          vehicleDesc: [
            { required: true, message: '简述不能为空', trigger: 'blur' }
          ],
          vehicleType: [
            { required: true, message: '陆运、空运、水运不能为空', trigger: 'blur' }
          ],
          vehicleAddress: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          vehicleMobile: [
            { required: true, message: '手机不能为空', trigger: 'blur' }
          ],
          vehicleEmail: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/logisticsvehicle/logisticsvehicle/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.vehicleName = data.logisticsVehicle.vehicleName
                this.dataForm.vehicleDesc = data.logisticsVehicle.vehicleDesc
                this.dataForm.vehicleType = data.logisticsVehicle.vehicleType
                this.dataForm.vehicleAddress = data.logisticsVehicle.vehicleAddress
                this.dataForm.vehicleMobile = data.logisticsVehicle.vehicleMobile
                this.dataForm.vehicleEmail = data.logisticsVehicle.vehicleEmail
                this.dataForm.status = data.logisticsVehicle.status
                this.dataForm.createUserId = data.logisticsVehicle.createUserId
                this.dataForm.createTime = data.logisticsVehicle.createTime
                this.dataForm.tenantId = data.logisticsVehicle.tenantId
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
              url: this.$http.adornUrl(`/logisticsvehicle/logisticsvehicle/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'vehicleName': this.dataForm.vehicleName,
                'vehicleDesc': this.dataForm.vehicleDesc,
                'vehicleType': this.dataForm.vehicleType,
                'vehicleAddress': this.dataForm.vehicleAddress,
                'vehicleMobile': this.dataForm.vehicleMobile,
                'vehicleEmail': this.dataForm.vehicleEmail,
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
