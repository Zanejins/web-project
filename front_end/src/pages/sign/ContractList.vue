<template>
    <div>
      <a-card :bordered="false">
        <div style="display: flex; flex-wrap: wrap">
          <a-button-group style="text-align: center">
            <a-button type="primary" v-on:click="total_contracts" style="width: 150px">全部合同</a-button>
            <a-button v-on:click="rent_contracts" style="width: 150px">租赁合同</a-button>
            <a-button v-on:click="borrow_contracts" style="width: 150px">借款合同</a-button>
            <a-button v-on:click="give_contracts" style="width: 150px">赠与合同</a-button>
            <a-button v-on:click="action_contracts" style="width: 150px">行纪合同</a-button>
          </a-button-group>

        </div>

      </a-card>
      <a-card
        style="margin-top: 24px"
        :bordered="true"
      >
        <a-table
            rowKey="list_id"
            size="default"
            :columns="columns"
            :dataSource="contracts"
            :pagination="false"
            :hideDefaultSelections="true"
            :expandRowByClick="true"
        >
          ,<template slot="action" slot-scope="text,record" class="icon-select">
            <router-link :to="`/contract/${record.id}/sign`">
              <i class="el-icon-thumb" style="font-size: 25px"></i>
            </router-link>
            <router-link :to="`/contract/${record.id}/details`">
              <i class="el-icon-document" style="font-size: 25px"></i>
            </router-link>
          </template>
        </a-table>

      </a-card>
    </div>
  </template>
  
  <script>
  import HeadInfo from '../../components/tool/HeadInfo'
  import axios from 'axios'
  axios.defaults.withCredentials = false
  export default {
    name: 'StandardList',
    data() {
      return {
        contracts: [
          {
            id: 1,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 1
          },
          {
            id: 2,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 3,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 4,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 5,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 6,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
        ],
        show_content: "所有合同",
        columns:[
          {
            width: '60px',
            title: '合同编号',
            align: 'center',
            dataIndex: 'id',
            key: 'index'
          },
          {
            width: '180px',
            title: '合同名称',
            align: 'center',
            dataIndex: 'name',
            key: 'name'
          },
          {
            width: '180px',
            title: '合同年限',
            align: 'center',
            dataIndex: 'year',
            key: 'year'
          },

          {
            width: '120px',
            title: '合作对象',
            align: 'center',
            dataIndex: 'object',
            key: 'object'
          },
          {
            width: '120px',
            title: '操作',
            align: 'center',
            scopedSlots: { customRender: 'action' },
            key: 'item_meeting_list'
          }
        ]
      }
    },
    methods: {
      total_contracts() {
        this.show_content = "所有合同"
        this.contracts=[
          {
            id: 1,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 2,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 3,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 4,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 5,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 6,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
        ]
      },
      rent_contracts(){
          this.contracts=[
            {
              id: 1,
              name: "《销售合同》",
              year: "2022年5月5日",
              object:"盛泰舒",
              signed: 0
            },
            {
              id: 2,
              name: "《租赁合同》",
              year: "2022年5月5日",
              object:"盛泰舒",
              signed: 0
            },
        ]
      },
      borrow_contracts(){
        this.contracts=[
          {
            id: 3,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 4,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
        ]
      },
      give_contracts(){
        this.contracts=[
          {
            id: 5,
            name: "《销售合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
          {
            id: 6,
            name: "《租赁合同》",
            year: "2022年5月5日",
            object:"盛泰舒",
            signed: 0
          },
        ]
      },
      action_contracts(){

      },
    },
  }
  </script>
  
  <style lang="less" scoped>
    .list-content-item{
      color: @text-color-second;
      display: inline-block;
      vertical-align: middle;
      font-size: 14px;
      margin-left: 40px;
      span{
        color: rgba(0,0,0, 0.65);
        font-size: 14px;
        font-weight: 500;
        line-height: 24px;
      }
      p{
        margin: 4px 0 0;
        line-height: 22px;
      }
    }
  </style>
  