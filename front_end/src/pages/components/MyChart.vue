<template>
    <div ref="echart">
    </div>
</template>

<script>
import * as echarts from 'echarts'
export default {
    name: "MyChart",
    props:{
        isAxisChart:{
            type:Boolean,
            default:true
        },
        chartData:{
            type:Object,
            default(){ //����Ĭ��ֵ
                return {
                    xData:[],
                    series:[]
                }
            }
        }
    },
    watch:{
        chartData:{
            handler:function() {
                this.initChart()
            },
            deep:true
        }
    },
    methods:{
        initChart() {
            console.log(this.$refs.echart)
            this.initChartData()
            if(this.echart) { //��Ⱦ
                this.echart.setOption(this.options)
            }
            else {
                this.echart=echarts.init(this.$refs.echart)
                this.echart.setOption(this.options)
            }
        },
        initChartData() {
            if(this.isAxisChart) {
                this.axisOption.xAxis.data=this.chartData.xData
                this.axisOption.series=this.chartData.series
            }
            else {
                this.normalOption.series=this.chartData.series
            }
        }
    },
    created() {
        this.$nextTick(() => {
            this.initChart()
        })
    },
    data() {
        return {
            axisOption: {
                legend:{
                        //ͼ��������ɫ
                        textStyle:{
                            color:"#333",
                        },
                    },
                    grid:{
                        left:"20%",
                    },
                    //��ʾ��
                    tooltip:{
                        trigger:"axis",
                    },
                    xAxis:{
                        type:"category", //��Ŀ��
                        data:[],
                        axisLine:{
                            lineStyle:{
                                color:"#17b3a3",
                            },
                        },
                        axisLabel:{
                            interval:0,
                            color:"#333",
                        },
                    },
                    yAxis:[
                        {
                            type:"value",
                            axisLine:{
                                lineStyle:{
                                    color:"#17b3a3",
                                },
                            },
                        },
                    ],
                    color:["#2ec7c9","#b6a2de"],
                    series:[]
            },
            normalOption:{
                tooltip:{
                        trigger:"item",
                    },
                    color:[
                        "#0f78f4",
                        "#dd536b",
                        "#9462e5",
                        "#a6a6a6",
                        "#e1bb22",
                        "#39c362",
                        "#3ed1cf",
                    ],
                    series:[],
            },
            echart:null
        }
    },
    computed:{
        options() {
            return this.isAxisChart ? this.axisOption : this.normalOption
        }
    }
}
</script>