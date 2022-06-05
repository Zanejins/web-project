import Vue from 'vue'
import App from './App.vue'
import {initRouter} from './router'
import './theme/index.less'
import Antd from 'ant-design-vue'
import Viser from 'viser-vue'
// import '@/mock'
import store from './store'
import 'animate.css/source/animate.css'
import Plugins from '@/plugins'
import {initI18n} from '@/utils/i18n'
import bootstrap from '@/bootstrap'
import 'moment/locale/zh-cn'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

const router = initRouter(store.state.setting.asyncRoutes)
const i18n = initI18n('CN', 'US')
import axios from 'axios'
Vue.prototype.$http=axios
Vue.use(Antd)
Vue.config.productionTip = false
Vue.use(Viser)
Vue.use(Plugins)
Vue.use(axios)
Vue.use(ElementUI)

bootstrap({router, store, i18n, message: Vue.prototype.$message})

new Vue({
  router,
  store,
  i18n,
  render: h => h(App),
}).$mount('#app')
