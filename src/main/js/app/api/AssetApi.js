import axios from 'axios';
import config from './config';

export default class AssetApi {
    constructor() {
        this.api = axios.create({
            baseURL: config.baseUrl,
            timeout: 10000,
            withCredentials: false,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
    }

    async get() {
        return await this.api.request({
            method: config.asset.get.method,
            url: config.asset.get.url,
        })
    }

    async update() {
        return await this.api.request({
            method: config.asset.update.method,
            url: config.asset.update.url,
        })
    }
}