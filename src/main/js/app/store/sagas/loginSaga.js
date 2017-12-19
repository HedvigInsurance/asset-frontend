import { take, fork, cancel, call, put, cancelled } from 'redux-saga/effects';
import { browserHistory } from 'react-router';
import { LoginApi } from '../../api';

import {
    LOGIN_REQUESTING,
    LOGIN_SUCCESS,
    LOGIN_ERROR,
    CLIENT_UNSET
} from '../constants/actionTypes';

import { setClient, unsetClient } from '../actions/loginActions';

const api = new LoginApi();

function* logout() {
    yield put(unsetClient());
    // localStorage.removeItem('token')
    browserHistory.push('/login');
}

function* loginFlow(email, password) {
    let token;
    try {
        token = yield call(api.loginApi, email, password);

        yield put(setClient(token));

        yield put({ type: LOGIN_SUCCESS });

        // localStorage.setItem('token', JSON.stringify(token))

        browserHistory.push('/');
    } catch (error) {
        yield put({ type: LOGIN_ERROR, error });
    } finally {
        if (yield cancelled()) {
            browserHistory.push('/login');
        }
    }

    return token;
}

function* loginWatcher() {
    while (true) {
        const { email, password } = yield take(LOGIN_REQUESTING);
        const task = yield fork(loginFlow, email, password);

        const action = yield take([CLIENT_UNSET, LOGIN_ERROR]);

        if (action.type === CLIENT_UNSET) yield cancel(task);

        yield call(logout);
    }
}

export default loginWatcher;
