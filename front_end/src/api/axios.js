import axios from 'axios'
import config from '../config'

const baseUrl=process.env.NODE_ENV=='development' ? config.baseUrl.dev : config.baseUrl.pro;

class HttpRequest {
    constructor(baseUrl) {
        this.baseUrl=baseUrl
    }
    getInsideConfig() {
        const config ={
            baseUrl:this.baseUrl,
            header:{}
        }
        return config
    }
    interceptors(instance) {
           // �������������
        instance.interceptors.request.use(function (config) {
           // �ڷ�������֮ǰ��Щʲô
           return config;
        }, function (error) {
           // �����������Щʲô
           return Promise.reject(error);
        });

        // �����Ӧ������
        instance.interceptors.response.use(function (response) {
            // ����Ӧ��������ʲô
           return response;
        }, function (error) {
           // ����Ӧ��������ʲô
           return Promise.reject(error);
       });
    }
    request(options) {
        const instance=axios.create()
        options={...this.getInsideConfig(),...options}
        this.interceptors(instance);
        return instance(options);
    }
}

export default new HttpRequest(baseUrl)