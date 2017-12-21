import { call, put, takeLatest } from 'redux-saga/effects';
import api from '../../api/AssetApi';

import { ASSET_UPDATING, ASSET_REQUESTING } from '../constants/actionTypes';

import {
    assetUpdateSuccess,
    assetUpdateError,
    assetRequestSuccess,
    assetRequestError
} from '../actions/assetsActions';

function* assetUpdateFlow(action) {
    try {
        const { assetId, assetState } = action;
        const { data } = yield call(api.update, assetId, assetState);
        const { assets: { list } } = yield select();
        const updatedList = list.map(asset => {
            if (asset.id === data.id) {
                return {...asset, state: data.state};
            }
            return asset;
        });
        yield put(assetUpdateSuccess(updatedList));
    } catch (error) {
        yield put(assetUpdateError(error));
    }
}

function* assetRequestFlow(action) {
    try {
        const { client } = action;
        const assets = yield call(api.get, client);
        yield put(assetRequestSuccess(assets));
    } catch (error) {
        yield put(assetRequestError(error));
    }
}

function* assetsWatcher() {
    yield [
        takeLatest(ASSET_UPDATING, assetUpdateFlow),
        takeLatest(ASSET_REQUESTING, assetRequestFlow)
    ];
}

export default assetsWatcher;
