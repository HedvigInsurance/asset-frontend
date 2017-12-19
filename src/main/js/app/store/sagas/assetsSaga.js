import { call, put, takeLatest } from 'redux-saga/effects';
import { AssetsApi } from '../../api';

import { ASSETS_UPDATING, ASSETS_REQUESTING } from '../constants/actionTypes';

import {
    assetCreateSuccess,
    assetCreateError,
    assetRequestSuccess,
    assetRequestError
} from '../actions/assetsActions';

const api = new AssetsApi();

function* assetUpdateFlow(action) {
    try {
        const { client, asset } = action;
        const createdWidget = yield call(api.update, client, asset);
        yield put(assetCreateSuccess(createdWidget));
    } catch (error) {
        yield put(assetCreateError(error));
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
        takeLatest(ASSETS_UPDATING, assetUpdateFlow),
        takeLatest(ASSETS_REQUESTING, assetRequestFlow)
    ];
}

export default assetsWatcher;
