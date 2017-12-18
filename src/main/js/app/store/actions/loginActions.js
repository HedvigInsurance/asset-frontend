import { LOGIN_USER } from '../constants/actionTypes';

export const loginUserAction = credentials => ({
    type: LOGIN_USER,
    credentials
})