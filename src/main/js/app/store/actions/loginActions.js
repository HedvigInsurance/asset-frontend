import { LOGIN_REQUESTING } from '../constants/actionTypes';

export const loginRequest = ({ email, password }) => ({
    type: LOGIN_REQUESTING,
    email,
    password
});
