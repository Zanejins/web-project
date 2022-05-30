<template>
    <div>
        <a-row :gutter="20">
            <a-col :span="4">
                <a-card style="height:350px"> 
                  <a-statistic title="合同总数" :value="572" style="margin-right: 50px" />
                  <a-statistic title="当月合同"  :value="23" />
                  <a-statistic title="待签署"  :value="5" />
                  <a-statistic title="已过期"  :value="3" />
                </a-card>
            </a-col>
            <a-col :span="8">
                <a-card>
                    <h4>最近签署合同列表</h4>
                  <a-table :columns="columns" :data-source="lists">
                    <a slot="name" slot-scope="text">{{ text }}</a>
                  </a-table>   
                </a-card>
            </a-col>
            <a-col :span="12">
                <a-card> 
                  <h4>月度合同分析表</h4>
                  <my-chart :chartData="echartData.user" :isAxisChart="true" style="height:275px"/>
                </a-card>
            </a-col>
            
            
        </a-row>

        <a-row :gutter="20">
            <a-col :span="8">
                <a-card>
                    <h4>合同类型统计图</h4>
                    <my-chart :chartData="echartData.video" :isAxisChart="false" style="height:240px"/>
                </a-card>
            </a-col>
            <a-col :span="16">
                <a-card>
                    <h4>各类合同变化折线图</h4>
                    <my-chart :chartData="echartData.order" style="height:240px"/>
                </a-card>
            </a-col>
        </a-row>
    </div>
</template>
<script>
// import {getData,getMenu} from '../../api/data.js'
import MyChart from '../components/MyChart.vue'
const columns = [
  {
    title: '合同id',
    dataIndex: 'key',
    key: 'name',
    scopedSlots: { customRender: 'name' },
  },
  {
    title: '甲方',
    dataIndex: 'name',
    key: 'age',
    width: 80,
  },
  {
    title: '乙方',
    dataIndex: 'age',
    key: 'address 1',
    ellipsis: true,
  },
];
const lists=[
    {
        key:'1',
        name: '靳东明',
        age: 'zbw',
        //address:'2022年5月13日'
    },
    {
        key:'2',
        name: '孙蕴琦',
        age: 'zbw',
        //address:'2022年5月13日'
    },
    {
        key:'3',
        name: '孙蕴琦',
        age: 'zbw',
    },
  
]
export default {
     name:'home',
     components:{
        MyChart
     },
     data() {
         return {
             lists,
             columns,
              echartData:{
                 order:{
                     xData:[
                         "Jau",
                         "Feb",
                         "Mar",
                         "April",
                         "May",
                         "June",
                         "July",
                         "Aug",
                         "Sep",
                         "Oct",
                         "Nov",
                         "Dec"
                     ],
                     series:[
                         {
                             data:[
                                 15,
                                 23,
                                 31,
                                 17,
                                 28,
                                 36,
                                 47,
                                 35,
                                 63,
                                 28,
                                 42,
                                 31,
                             ],
                             name:"买卖合同",
                             type:"line"
                         },
                         {
                             data:[
                                37,
                                41,
                                28,
                                32,
                                29,
                                46,
                                28,
                                31,
                                28,
                                52,
                                52,
                                27,
                             ],
                             name:"租赁合同",
                             type:"line"
                         },
                         {
                             data:[
                                 19,
                                 32,
                                 19,
                                 36,
                                 48,
                                 36,
                                 47,
                                 35,
                                 63,
                                 28,
                                 42,
                                 31,
                             ],
                             name:"借款合同",
                             type:"line"
                         },
                         {
                             data:[
                                 15,
                                 23,
                                 31,
                                 17,
                                 28,
                                 36,
                                 53,
                                 39,
                                 48,
                                 64,
                                 27,
                                 41,
                             ],
                             name:"仓储合同",
                             type:"line"
                         },

                     ]
                 },
                 user:{
                     xData:[
                         "1月",
                         "2月",
                         "3月",
                         "4月",
                         "5月",
                     ],
                     series:[
                         {
                             data:[
                                 55,
                                 43,
                                 67,
                                 59,
                                 25
                             ],
                             name:'完成签署',
                             type:"bar"
                         },
                         {
                             data:[
                                 9,
                                 8,
                                 10,
                                 15,
                                 1
                             ],
                             name:'未签署',
                             type:"bar"
                         }
                     ]
                 },
                 video:{
                     series:[
                         {
                             data:[
                                 {
                                    name:'买卖合同',
                                    value:390
                                },
                                {
                                    name:'租赁合同',
                                    value:300
                                },
                                {
                                    name:'借款合同',
                                    value:150
                                },
                                {
                                    name:'仓储合同',
                                    value:'50'
                                }
                             ],
                             type:'pie'
                         }
                     ],
                 }
             }
         }
     },
}         
//         return {
//             userImg:require('../../assets/img/xidian-logo-red.png'),
//             tabledata:[
//                 {
//                     name:'oppo',
//                     todayBuy:100,
//                     monthBuy:300,
//                     totalBuy:800
//                 },
//                 {
//                     name:'vivo',
//                     todayBuy:100,
//                     monthBuy:300,
//                     totalBuy:800
//                 },
//                 {
//                     name:'apple',
//                     todayBuy:100,
//                     monthBuy:300,
//                     totalBuy:800
//                 },
//                 {
//                     name:'huawei',
//                     todayBuy:100,
//                     monthBuy:300,
//                     totalBuy:800
//                 },
//                 {
//                     name:'sanxing',
//                     todayBuy:100,
//                     monthBuy:300,
//                     totalBuy:800
//                 }
//             ],
//             tablaaba:{
//                 name:'无人机ID',
//                 todayBuy:'起始位置',
//                 monthBuy:'目标位置',
//                 totalBuy:'实时位置'
//             },
//             countData:[
//                 {
//                     name:"今日支付订单",
//                     value:1234,
//                     icon:"success",
//                     color:"#2ec7c9"
//                 },
//                 {
//                     name:"今日收藏订单",
//                     value:210,
//                     icon:"star-on",
//                     color:"#ffb980"
//                 },
//                 {
//                     name:"今日未支付订单",
//                     value:1234,
//                     icon:"s-goods",
//                     color:"#5ab1ef"
//                 },
//                 {
//                     name:"本月支付订单",
//                     value:1234,
//                     icon:"success",
//                     color:"#2ec7c9"
//                 },
//                 {
//                     name:"本月收藏订单",
//                     value:210,
//                     icon:"star-on",
//                     color:"#ffb980"
//                 },
//                 {
//                     name:"本月未支付订单",
//                     value:1234,
//                     icon:"s-goods",
//                     color:"#5ab1ef"
//                 },
//             ],
//             echartData:{
//                 order:{
//                     xData:[],
//                     series:[]
//                 },
//                 user:{
//                     xData:[],
//                     series:[]
//                 },
//                 video:{
//                     series:[],
//                 }
//             }
//         }
//     },
//     mounted() {
        
//         getData().then(res =>{
//             const {code,data}=res.data;
//             if(code==20000) {
                
//                 this.tabledata=data.tableData
//                 const order=data.orderData
//                 //console.log("orderData:")
//                 //console.log(order)
//                 const xData=order.date
//                 //console.log("xData:")
//                 //console.log(xData)
//                 const keyArray=Object.keys(order.data[0])
//                 //console.log("keyArray:")
//                 //console.log(keyArray)
//                 const series=[]
//                 keyArray.forEach(key =>{
//                     series.push({
//                         name:key,
//                         data:order.data.map(item => item[key]),
//                         type:'line'
//                     })
//                 })
//                 this.echartData.order.xData=xData
//                 this.echartData.order.series=series
//                 this.echartData.user.xData=data.userData.map( item => item.date),
//                 this.echartData.user.series=
//                 [
//                         {
//                             name:'新增用户',
//                             data:data.userData.map(item => item.new),
//                             type:'bar'
//                         },
//                         {
//                             name:'活跃用户',
//                             data:data.userData.map(item => item.active),
//                             type:'bar'
//                         }
//                 ]
//                 this.echartData.video.series=
//                 [
//                         {
//                             data:data.videoData,
//                             type:'pie'
//                         }
//                 ]
//             }
//             console.log(res);
//         })
//     }
// }
</script>