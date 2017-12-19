import axios from 'axios';
import config from './config';

export default class AssetApi {
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

    async get() {
        return this.api.request({
            method: config.asset.get.method,
            url: config.asset.get.url,
            params: {

            }
        })
    }

    async update() {
        return this.api.request({
            method: config.asset.update.method,
            url: config.asset.update.url,
            params: {

            }
        })
    }
}