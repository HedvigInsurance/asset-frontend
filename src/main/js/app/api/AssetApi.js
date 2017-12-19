import axios from 'axios';
import config from './config';

const axiosInstance = axios.create({
    baseURL: config.baseUrl,
    timeout: 10000,
    withCredentials: false,
    headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
    }
});

const get = async () => {
    return await axiosInstance.request({
        url: config.asset.get.url,
        method: config.asset.get.method
    });
};

const update = async () => {
    return await axiosInstance.request({
        url: config.asset.update.url,
        method: config.asset.update.method
    });
};

export default { get, update };
