import {
    FETCH_ASSETS_REQUEST,
    UPDATE_ASSET_REQUEST
} from '../constants/actionTypes';

export const getAssets = payload => ({
    type: FETCH_ASSETS_REQUEST,
    payload
})

export const updateAsset = id => ({
    type: UPDATE_ASSET_REQUEST,
    id
})