<template>
  <mu-container>
    <mu-row style="margin:24px 0;">
      <mu-col span="12">
        <mu-paper :z-depth="1">
          <mu-data-table height="540px" :data="purchases" :columns="purchaseColumns">
            <template slot-scope="scope">
              <td>
                {{ scope.row.goodsId }}
              </td>
              <td>
                {{ scope.row.supplierId }}
              </td>
              <td>
                {{ scope.row.userId }}
              </td>
              <td>
                {{ scope.row.goodsPrice }}
              </td>
              <td>
                {{ scope.row.num }}
              </td>
              <td>
                <mu-button icon color="primary" small
                  @click="editPurchase(scope.row.id)">
                  <mu-icon value="edit"></mu-icon>
                </mu-button>
              </td>
            </template>
          </mu-data-table>
          <mu-flex justify-content="end">
          </mu-flex>
        </mu-paper>
      </mu-col>
    </mu-row>

    <mu-row gutter>
      <mu-col>
        <mu-flex justify-content="end">
          <mu-button @click="addPurchase"
            fab color="primary" style="margin-top:-48px;margin-right: 16px;">
            <mu-icon value="add"></mu-icon>
          </mu-button>
        </mu-flex>
      </mu-col>
    </mu-row>

    <data-dialog ref="dataDialog">
      <template #default="slot">
        <mu-list>
          <mu-list-item>
            <mu-select placeholder="商品编号" v-model="slot.data.goodsId">
              <mu-option v-for="goods in goods"
              :key="goods.goodsId" :value="goods.goodsId"
              :label="goods.name + '['+ goods.goodsId + ']' ">
              
              <!-- customize option -->
              <span style="font-weight: 600;margin-right: 6px;">
                {{ goods.name }}
              </span>
              <span style="margin-right: 12px;">
                {{ goods.unit }}
              </span>
              <span style="color: #bdbdbd;">
                {{ goods.goodsId }}
              </span>
                
              </mu-option>
            </mu-select>
          </mu-list-item>
          
          <mu-list-item>
            <mu-select placeholder="供应商编号" v-model="slot.data.supplierId">
              <mu-option v-for="supplier in suppliers"
              :key="supplier.supplierId" :value="supplier.supplierId"
              :label="supplier.supplierName + '['+ supplier.supplierId + ']' ">
              
              <!-- customize option -->
              <span style="font-weight: 600;margin-right: 6px;">
                {{ supplier.supplierName }}
              </span>
              <span style="color: #bdbdbd;">
                {{ supplier.supplierId }}
              </span>
                
              </mu-option>
            </mu-select>
          </mu-list-item>
          
          <mu-list-item>
            <mu-text-field prefix="进价" v-model="slot.data.goodsPrice"/>
          </mu-list-item>
          
          <mu-list-item>
            <mu-text-field prefix="数量" v-model="slot.data.num"/>
          </mu-list-item>
        </mu-list>
      </template>
    </data-dialog>
  </mu-container>
</template>
<script>
  import dataDialog from './data-dialog.vue'

  export default {
    data: () => ({
      purchaseColumns: [
        {
          title: '商品编号',
          name: 'goodsId'
        },
        {
          title: '供应商编号',
          name: 'supplierId'
        },
        {
          title: '操作员工号',
          name: 'userId'
        },
        {
          title: '进价',
          name: 'goodsPrice'
        },
        {
          title: '数量',
          name: 'num'
        },
        {
          title: '操作',
          width: 140,
          name: 'operation'
        },
      ],
      purchases: [],
      goods: [],
      suppliers: []
    }),
    methods: {
      editPurchase(id) {
        let i = 0, _this = this, purchase
        this.purchases.forEach((eachPurchase, index) => {
          if(eachPurchase.id == id) i = index
        })
        purchase = this.purchases[i]
        this.$refs.dataDialog.show({
          id: id,
          goodsId: purchase.goods.goodsId,
          supplierId: purchase.supplier.supplierId,       
          goodsPrice: purchase.goodsPrice,
          num: purchase.num
        }, newPurchase => {
          newPurchase.userId = _this.$parent.userId
          this.axios.put('/api/purchase/', newPurchase)
          .then(() => {
            console.log(i)
            console.log(newPurchase)
            _this.purchases.splice(i, 1)
            _this.purchases.push(newPurchase)
          })
        })
      },
      addPurchase() {
        let _this = this
        this.$refs.dataDialog.show({
          goodsId: '',
          supplierId: '', 
          goodsPrice: 0,
          sellPrice: 0,
          num: 0
        }, newPurchase => {
          newPurchase.userId = _this.$parent.userId
          this.axios.post('/api/purchase/', newPurchase)
          .then(() => {
            _this.purchases.push(newPurchase)
          })
        })
      },
    },
    mounted() {
      let _this = this
      this.axios.get('/api/goods/')
        .then(response => {
          _this.goods = response.data
        })
        .catch(error => {
          console.log(error)
        })
      
      this.axios.get('/api/supplier/')
        .then(response => {
          _this.suppliers = response.data
        })
        .catch(error => {
          console.log(error)
        })
        
      this.axios.get('/api/purchase/')
        .then(response => {
          response.data.forEach(item => {
            _this.purchases.push(item)
          })
        })
        .catch(error => {
          console.log(error)
        })
    },
    components: {
      'dataDialog': dataDialog
    }
  }
</script>

<style>
tbody tr td {
  padding: 24px 0;
}
</style>

