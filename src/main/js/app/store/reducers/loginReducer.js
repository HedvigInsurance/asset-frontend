import initialState from '../initialState';
import {
    LOGIN_REQUESTING,
    LOGIN_SUCCESS,
    LOGIN_ERROR
} from '../constants/actionTypes';

export default function(state = initialState.login, action) {
    switch (action.type) {
        case LOGIN_REQUESTING:
            return {
                requesting: true,
                successful: false,
                messages: [{ body: 'Logging in...', time: new Date() }],
                errors: []
            };

        case LOGIN_SUCCESS:
            return {
                errors: [],
                messages: [],
                requesting: false,
                successful: true
            };

        case LOGIN_ERROR:
            return {
                errors: state.errors.concat([
                    {
                        body: action.error.toString(),
                        time: new Date()
                    }
                ]),
                messages: [],
                requesting: false,
                successful: false
            };

        default:
            return state;
    }
}
