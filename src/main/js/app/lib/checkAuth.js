import { setClient } from '../store/actions';

function checkAuthorization(dispatch) {
    const storedToken = '';//localStorage.getItem('token');
    if (storedToken) {
        const token = JSON.parse(storedToken);
        const createdDate = new Date(token.created);
        const created = Math.round(createdDate.getTime() / 1000);
        const ttl = 1209600;
        const expiry = created + ttl;

        if (created > expiry) return false;

        dispatch(setClient(token));
        return true;
    }

    return false;
}

export function checkIndexAuthorization({ dispatch }) {
    return (nextState, replace, next) => {
        if (checkAuthorization(dispatch)) {
            replace('assets');

            return next();
        }

        replace('login');
        return next();
    };
}

export function checkAssetAuthorization({ dispatch, getState }) {
    return (nextState, replace, next) => {
        const client = getState().client;
        if (client && client.token) return next();
        if (checkAuthorization(dispatch)) return next();
        replace('login');
        return next();
    };
}
