import { fork } from 'redux-saga/effects';
import { getAssetsFlow, updateAssetFlow } from './assetsSaga';
import { logoutFlow, loginFlow } from './loginSaga';

export default function* startForman() {
    yield fork(loginFlow);
    yield fork(logoutFlow);
    yield fork(getAssetsFlow);
    yield fork(updateAssetFlow);
}
