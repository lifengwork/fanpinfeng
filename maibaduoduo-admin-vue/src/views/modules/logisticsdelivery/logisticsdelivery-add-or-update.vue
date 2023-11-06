<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="配送代码" prop="delliveryCode">
      <el-input v-model="dataForm.delliveryCode" placeholder="配送代码"></el-input>
    </el-form-item>
    <el-form-item label="订单编码" prop="orderId">
      <el-input v-model="dataForm.orderId" placeholder="订单编码"></el-input>
    </el-form-item>
    <el-form-item label="仓库编码" prop="storeId">
      <el-input v-model="dataForm.storeId" placeholder="仓库编码"></el-input>
    </el-form-item>
    <el-form-item label="配送名称" prop="deliveryName">
      <el-input v-model="dataForm.deliveryName" placeholder="配送名称"></el-input>
    </el-form-item>
    <el-form-item label="简述" prop="deliveryDesc">
      <el-input v-model="dataForm.deliveryDesc" placeholder="简述"></el-input>
    </el-form-item>
    <el-form-item label="地址" prop="deliveryAddress">
      <el-input v-model="dataForm.deliveryAddress" placeholder="地址"></el-input>
    </el-form-item>
    <el-form-item label="手机" prop="deliveryMobile">
      <el-input v-model="dataForm.deliveryMobile" placeholder="手机"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="deliveryEmail">
      <el-input v-model="dataForm.deliveryEmail" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="2部分在途、1全部配送完成、0失效停配" prop="deliveryStatus">
      <el-input v-model="dataForm.deliveryStatus" placeholder="2部分在途、1全部配送完成、0失效停配"></el-input>
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
          delliveryCode: '',
          orderId: '',
          storeId: '',
          deliveryName: '',
          deliveryDesc: '',
          deliveryAddress: '',
          deliveryMobile: '',
          deliveryEmail: '',
          deliveryStatus: '',
          status: '',
          createUserId: '',
          createTime: '',
          tenantId: ''
        },
        dataRule: {
          delliveryCode: [
            { required: true, message: '配送代码不能为空', trigger: 'blur' }
          ],
          orderId: [
            { required: true, message: '订单编码不能为空', trigger: 'blur' }
          ],
          storeId: [
            { required: true, message: '仓库编码不能为空', trigger: 'blur' }
          ],
          deliveryName: [
            { required: true, message: '配送名称不能为空', trigger: 'blur' }
          ],
          deliveryDesc: [
            { required: true, message: '简述不能为空', trigger: 'blur' }
          ],
          deliveryAddress: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          deliveryMobile: [
            { required: true, message: '手机不能为空', trigger: 'blur' }
          ],
          deliveryEmail: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          deliveryStatus: [
            { required: true, message: '2部分在途、1全部配送完成、0失效停配不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/logisticsdelivery/logisticsdelivery/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.delliveryCode = data.logisticsDelivery.delliveryCode
                this.dataForm.orderId = data.logisticsDelivery.orderId
                this.dataForm.storeId = data.logisticsDelivery.storeId
                this.dataForm.deliveryName = data.logisticsDelivery.deliveryName
                this.dataForm.deliveryDesc = data.logisticsDelivery.deliveryDesc
                this.dataForm.deliveryAddress = data.logisticsDelivery.deliveryAddress
                this.dataForm.deliveryMobile = data.logisticsDelivery.deliveryMobile
                this.dataForm.deliveryEmail = data.logisticsDelivery.deliveryEmail
                this.dataForm.deliveryStatus = data.logisticsDelivery.deliveryStatus
                this.dataForm.status = data.logisticsDelivery.status
                this.dataForm.createUserId = data.logisticsDelivery.createUserId
                this.dataForm.createTime = data.logisticsDelivery.createTime
                this.dataForm.tenantId = data.logisticsDelivery.tenantId
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
              url: this.$http.adornUrl(`/logisticsdelivery/logisticsdelivery/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'delliveryCode': this.dataForm.delliveryCode,
                'orderId': this.dataForm.orderId,
                'storeId': this.dataForm.storeId,
                'deliveryName': this.dataForm.deliveryName,
                'deliveryDesc': this.dataForm.deliveryDesc,
                'deliveryAddress': this.dataForm.deliveryAddress,
                'deliveryMobile': this.dataForm.deliveryMobile,
                'deliveryEmail': this.dataForm.deliveryEmail,
                'deliveryStatus': this.dataForm.deliveryStatus,
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
