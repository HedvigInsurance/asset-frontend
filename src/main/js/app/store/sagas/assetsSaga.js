import { call, put, takeLatest } from 'redux-saga/effects';
import AssetsApi from '../../api/AssetApi';

import { ASSET_UPDATING, ASSET_REQUESTING } from '../constants/actionTypes';

import {
    assetUpdateSuccess,
    assetUpdateError,
    assetRequestSuccess,
    assetRequestError
} from '../actions/assetsActions';

const api = new AssetsApi();

function* assetUpdateFlow(action) {
    try {
        const { client, asset } = action;
        const createdWidget = yield call(api.update, client, asset);
        yield put(assetUpdateSuccess(createdWidget));
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
