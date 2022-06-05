
      <!-- <a-space direction="vertical"> -->
<template>
  <a-card>
    <a-descriptions
        title="具体信息"
        bordered
        :column="{ xxl: 3, xl: 4, lg: 4, md: 4, sm: 4, xs: 4}"
    >
      <a-descriptions-item label="收款单位"  :span="2">
        {{this.contract[0].get_company}}
      </a-descriptions-item>
      <a-descriptions-item label="付款说明" :span="2">
        {{this.contract[0].give_illus}}
      </a-descriptions-item>
      <a-descriptions-item label="付款金额"  :span="2">
        {{this.contract[0].money}}
      </a-descriptions-item>
      <a-descriptions-item label="币种"  :span="2">
        {{this.contract[0].the_specific}}
      </a-descriptions-item>
      <a-descriptions-item label="收款方银行账号" :span="2">
        {{this.contract[0].get_number}}
      </a-descriptions-item>
      <a-descriptions-item label="付款单位"  :span="2">
        {{this.contract[0].give_company}}
      </a-descriptions-item>
      <a-descriptions-item label="历史付款总金额"  :span="2">
        {{this.contract[0].total_money}}
      </a-descriptions-item>
      <a-descriptions-item label="支付状态" :span="2">
        {{this.contract[0].give_state}}
      </a-descriptions-item>
      <a-descriptions-item label="支付日期"  :span="2">
        {{this.contract[0].date}}
      </a-descriptions-item>
      <a-descriptions-item label="发票齐全"  :span="2">
        {{this.contract[0].check_all}}
      </a-descriptions-item>
      <a-descriptions-item label="总期数" :span="2">
        {{this.contract[0].total_phases}}
      </a-descriptions-item>
      <a-descriptions-item label="当前支付期数"  :span="2">
        {{this.contract[0].the_phase}}
      </a-descriptions-item>
      <a-descriptions-item label="NC单据号"  :span="2">
        {{this.contract[0].NC_number}}
      </a-descriptions-item>
      <a-descriptions-item label="单据状态"  :span="2">
        {{this.contract[0].the_check_state}}
      </a-descriptions-item>
      <a-descriptions-item label="审批状态" :span="2">
        {{this.contract[0].state}}
      </a-descriptions-item>
      <a-descriptions-item label="审批人"  :span="2">
        {{this.contract[0].the_check_people}}
      </a-descriptions-item>
    </a-descriptions>
  </a-card>

</template>
      <!-- </a-space> -->


<script>
import WebViewer from './WebViewer.vue'
import axios from "axios";
axios.defaults.withCredentials = false
export default {
    name: 'SignPage',
    data () {
        return {
            publicPath: process.env.BASE_URL,
            contract: [
                {
                  id:1,
                  get_company: "靳东明",
                  money: 20,
                  get_number: "23894610840168-98",
                  total_money: 13413,
                  date: "2015/5",
                  check_all: "是",
                  the_phase: 1,
                  NC_number: "D80081245690",
                  state: "通过",
                  give_illus: "业财维保",
                  the_specific:"人民币",
                  give_company:"盛泰舒",
                  give_state:"结算成功",
                  total_phases:1,
                  the_check_state:"保存",
                  the_check_people:"胡军",
                },
              {
                id:1,
                get_company: "西安电子科技大学",
                money: 20,
                get_number: "23894610840168-98",
                total_money: 13413,
                date: "2015/5",
                check_all: "是",
                the_phase: 1,
                NC_number: "D80081245690",
                state: "通过",
                give_illus: "业财维保",
                the_specific:"人民币",
                give_company:"盛泰舒",
                give_state:"结算成功",
                total_phases:1,
                the_check_state:"保存",
                the_check_people:"胡军",
              }
            ]
        }
    },
  methods:{
  },
    created() {

      const contractId = this.$route.params.id;
    }
}
</script>

<style>
#app {
font-family: 'Avenir', Helvetica, Arial, sans-serif;
-webkit-font-smoothing: antialiased;
-moz-osx-font-smoothing: grayscale;
/* text-align: center; */
color: #2c3e50;
}
</style>
  