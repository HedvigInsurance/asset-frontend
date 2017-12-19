import axios from 'axios';
import config from './config';

export default class LoginApi {
    constructor() {
        this.api = axios.create({
            baseURL: config.baseUrl,
            timeout: 10000,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }

    async login() {
        return await this.api.request({
            method: config.login.login.method,
            url: config.login.login.url,
            params: {

            }
        })
    }

    async logout() {
        return await this.api.request({
            method: config.login.logout.method,
            url: config.asset.logout.url,
            params: {

            }
        })
    }
}