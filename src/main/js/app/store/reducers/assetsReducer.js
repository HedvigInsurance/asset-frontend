import initialState from '../initialState';
import {
    ASSET_UPDATING,
    ASSET_UPDATE_SUCCESS,
    ASSET_UPDATE_ERROR,
    ASSET_REQUESTING,
    ASSET_REQUEST_SUCCESS,
    ASSET_REQUEST_ERROR
} from '../constants/actionTypes';

export default function(state = initialState.assets, action) {
    switch (action.type) {
        case ASSET_UPDATING:
            return state;

        case ASSET_UPDATE_SUCCESS:
            return state;

        case ASSET_UPDATE_ERROR:
            return state;

        case ASSET_REQUESTING:
            return {
                ...state,
                requesting: false,
                successful: true,
                messages: [
                    {
                        body: 'Fetching assets',
                        time: new Date()
                    }
                ],
                errors: []
            };

        case ASSET_REQUEST_SUCCESS:
            return {
                list: action.assets,
                requesting: false,
                successful: true,
                messages: [
                    {
                        body: 'Assets fetched',
                        time: new Date()
                    }
                ],
                errors: []
            };

        case ASSET_REQUEST_ERROR:
            return {
                requesting: false,
                successful: false,
                messages: [],
                errors:
                    state.errors.concat[
                        {
                            body: action.error.toString(),
                            time: new Date()
                        }
                    ]
            };

        default:
            return state;
    }
}
