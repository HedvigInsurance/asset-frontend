import { browserHistory } from 'react-router';
import { take, call, put, race } from 'redux-saga/effects';
import Api from '../../api';
import {
    SENDING_REQUEST,
    LOGIN_REQUEST,
    SET_AUTH,
    LOGOUT,
    CHANGE_FORM,
    REQUEST_ERROR
} from '../constants/actionsTypes';

const auth = Api.auth;

/**
 * Effect to handle authorization
 */
export function* authorize({ username, password }) {
    yield put({ type: SENDING_REQUEST, sending: true });

    try {
        const response = yield call(auth.login, username, password);

        return response;
    } catch (error) {
        yield put({ type: REQUEST_ERROR, error: error.message });
        return false;
    } finally {
        yield put({ type: SENDING_REQUEST, sending: false });
    }
}

/**
 * Effect to handle logging out
 */
export function* logout() {
    yield put({ type: SENDING_REQUEST, sending: true });
    try {
        const response = yield call(auth.logout);
        yield put({ type: SENDING_REQUEST, sending: false });

        return response;
    } catch (error) {
        yield put({ type: REQUEST_ERROR, error: error.message });
    }
}

/**
 * Log in saga
 */
export function* loginFlow() {
    while (true) {
        const request = yield take(LOGIN_REQUEST);
        const { username, password } = request.data;
        const winner = yield race({
            auth: call(authorize, { username, password, isRegistering: false }),
            logout: take(LOGOUT)
        });

        if (winner.auth) {
            yield put({ type: SET_AUTH, newAuthState: true });
            yield put({
                type: CHANGE_FORM,
                newFormState: { username: '', password: '' }
            });
            browserHistory.push('/');
        }
    }
}

/**
 * Log out saga
 */
export function* logoutFlow() {
    while (true) {
        yield take(LOGOUT);
        yield put({ type: SET_AUTH, newAuthState: false });

        yield call(logout);
        browserHistory.push('/login');
    }
}

