<template>
    <div id="app">
      <!-- <a-space direction="vertical"> -->
        <a-card title="合同文件" style="margin-top: 24px">
            <WebViewer v-if="contract.contract_id == 1" :path="`${publicPath}lib`" :url="URL1"/>
            <WebViewer v-if="contract.contract_id == 2" :path="`${publicPath}lib`" :url="URL2"/>
        </a-card>
      <!-- </a-space> -->
    </div>
</template>

<script>
import WebViewer from './WebViewer.vue'
import axios from "axios";
axios.defaults.withCredentials = false
export default {
    name: 'SignPage',
    components: {
        WebViewer,
    },
    data () {
        return {
            publicPath: process.env.BASE_URL,
            URL1: "https://cdn.jsdelivr.net/gh/xinwuyun/facedec-SHOGOKI@main/src/assets/contracts/%E5%90%88%E5%90%8C1.pdf",
            URL2: "https://cdn.jsdelivr.net/gh/xinwuyun/facedec-SHOGOKI@main/src/assets/contracts/%E5%90%88%E5%90%8C2.pdf",
            contract: [{
            }]
        }
    },
  methods:{
    getDetailContrast(string) {
      let str = 'http://43.138.171.232:8088/getDetailContract?contract_id=' + string
      axios.get(str)
          .then( (response) => {
            console.log(response.data.detail_info)
            this.contract=response.data.detail_info
            this.URL=response.data.detail_info.URL
            console.log(this.contract.URL)
            console.log("Contract--",this.contract)
            console.log("url",this.URL)
            console.log(this.contract.id)
          })
          .catch(function (error) {
            console.log(error);
          });
    },
    stateChange(id){
      let str = 'http://43.138.171.232:8088/sign?contract_id=' + id
      axios.post(str)
      axios.post('http://43.138.171.232:8088/sign', {
        contract_id:id
      })
          .then(function (response) {
            console.log(response);
          })
          .catch(function (error) {
            console.log(error);
          });
    }
  },
    created() {

      const contractId = this.$route.params.id;
      this.getDetailContrast(contractId);
      console.log("***********************************",contractId);
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
  