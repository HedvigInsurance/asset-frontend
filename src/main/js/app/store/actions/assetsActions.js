import {
    ASSET_CREATING,
    ASSET_CREATE_SUCCESS,
    ASSET_CREATE_ERROR,
    ASSET_REQUESTING,
    ASSET_REQUEST_SUCCESS,
    ASSET_REQUEST_ERROR
} from '../constants/actionTypes';

export const assetCreate = function assetCreate(client, asset) {
    return {
        type: ASSET_CREATING,
        client,
        asset
    };
};

export const assetCreateSuccess = function assetCreateSuccess(asset) {
    return {
        type: ASSET_CREATE_SUCCESS,
        asset
    };
};

export const assetCreateError = function assetCreateError(error) {
    return {
        type: ASSET_CREATE_ERROR,
        error
    };
};

export const assetRequest = function assetRequest(client) {
    return {
        type: ASSET_REQUESTING,
        client
    };
};

export const assetRequestSuccess = function assetRequestSuccess(assets) {
    return {
        type: ASSET_REQUEST_SUCCESS,
        assets
    };
};

export const assetRequestError = function assetRequestError(error) {
    return {
        type: ASSET_REQUEST_ERROR,
        error
    };
};
