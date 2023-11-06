<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="运输代码" prop="transportCode">
      <el-input v-model="dataForm.transportCode" placeholder="运输代码"></el-input>
    </el-form-item>
    <el-form-item label="配送ID" prop="deliveryId">
      <el-input v-model="dataForm.deliveryId" placeholder="配送ID"></el-input>
    </el-form-item>
    <el-form-item label="配送员" prop="deliverymanId">
      <el-input v-model="dataForm.deliverymanId" placeholder="配送员"></el-input>
    </el-form-item>
    <el-form-item label="运输方式" prop="vehicleId">
      <el-input v-model="dataForm.vehicleId" placeholder="运输方式"></el-input>
    </el-form-item>
    <el-form-item label="名称" prop="transportName">
      <el-input v-model="dataForm.transportName" placeholder="名称"></el-input>
    </el-form-item>
    <el-form-item label="简述" prop="transportDesc">
      <el-input v-model="dataForm.transportDesc" placeholder="简述"></el-input>
    </el-form-item>
    <el-form-item label="地址" prop="transportAddress">
      <el-input v-model="dataForm.transportAddress" placeholder="地址"></el-input>
    </el-form-item>
    <el-form-item label="手机" prop="transportMobile">
      <el-input v-model="dataForm.transportMobile" placeholder="手机"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="transportEmail">
      <el-input v-model="dataForm.transportEmail" placeholder="邮箱"></el-input>
    </el-form-item>
    <el-form-item label="当前运输状态（待配送、配送中、已完成、已停运）" prop="transportStatus">
      <el-input v-model="dataForm.transportStatus" placeholder="当前运输状态（待配送、配送中、已完成、已停运）"></el-input>
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
          transportCode: '',
          deliveryId: '',
          deliverymanId: '',
          vehicleId: '',
          transportName: '',
          transportDesc: '',
          transportAddress: '',
          transportMobile: '',
          transportEmail: '',
          transportStatus: '',
          status: '',
          createUserId: '',
          createTime: '',
          tenantId: ''
        },
        dataRule: {
          transportCode: [
            { required: true, message: '运输代码不能为空', trigger: 'blur' }
          ],
          deliveryId: [
            { required: true, message: '配送ID不能为空', trigger: 'blur' }
          ],
          deliverymanId: [
            { required: true, message: '配送员不能为空', trigger: 'blur' }
          ],
          vehicleId: [
            { required: true, message: '运输方式不能为空', trigger: 'blur' }
          ],
          transportName: [
            { required: true, message: '名称不能为空', trigger: 'blur' }
          ],
          transportDesc: [
            { required: true, message: '简述不能为空', trigger: 'blur' }
          ],
          transportAddress: [
            { required: true, message: '地址不能为空', trigger: 'blur' }
          ],
          transportMobile: [
            { required: true, message: '手机不能为空', trigger: 'blur' }
          ],
          transportEmail: [
            { required: true, message: '邮箱不能为空', trigger: 'blur' }
          ],
          transportStatus: [
            { required: true, message: '当前运输状态（待配送、配送中、已完成、已停运）不能为空', trigger: 'blur' }
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
              url: this.$http.adornUrl(`/logisticstransport/logisticstransport/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.transportCode = data.logisticsTransport.transportCode
                this.dataForm.deliveryId = data.logisticsTransport.deliveryId
                this.dataForm.deliverymanId = data.logisticsTransport.deliverymanId
                this.dataForm.vehicleId = data.logisticsTransport.vehicleId
                this.dataForm.transportName = data.logisticsTransport.transportName
                this.dataForm.transportDesc = data.logisticsTransport.transportDesc
                this.dataForm.transportAddress = data.logisticsTransport.transportAddress
                this.dataForm.transportMobile = data.logisticsTransport.transportMobile
                this.dataForm.transportEmail = data.logisticsTransport.transportEmail
                this.dataForm.transportStatus = data.logisticsTransport.transportStatus
                this.dataForm.status = data.logisticsTransport.status
                this.dataForm.createUserId = data.logisticsTransport.createUserId
                this.dataForm.createTime = data.logisticsTransport.createTime
                this.dataForm.tenantId = data.logisticsTransport.tenantId
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
              url: this.$http.adornUrl(`/logisticstransport/logisticstransport/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'transportCode': this.dataForm.transportCode,
                'deliveryId': this.dataForm.deliveryId,
                'deliverymanId': this.dataForm.deliverymanId,
                'vehicleId': this.dataForm.vehicleId,
                'transportName': this.dataForm.transportName,
                'transportDesc': this.dataForm.transportDesc,
                'transportAddress': this.dataForm.transportAddress,
                'transportMobile': this.dataForm.transportMobile,
                'transportEmail': this.dataForm.transportEmail,
                'transportStatus': this.dataForm.transportStatus,
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
