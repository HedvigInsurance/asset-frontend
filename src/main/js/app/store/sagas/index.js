import LoginSaga from './loginSaga';
import AssetsSaga from './assetsSaga';

export default function* IndexSaga() {
    yield [LoginSaga(), AssetsSaga()];
}
