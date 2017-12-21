import {
    ASSET_UPDATING,
    ASSET_UPDATE_SUCCESS,
    ASSET_UPDATE_ERROR,
    ASSET_REQUESTING,
    ASSET_REQUEST_SUCCESS,
    ASSET_REQUEST_ERROR
} from '../constants/actionTypes';

export const assetUpdate = function assetUpdate(assetId, assetState) {
    return {
        type: ASSET_UPDATING,
        assetId,
        assetState,
    };
};

export const assetUpdateSuccess = function assetUpdateSuccess(asset) {
    return {
        type: ASSET_UPDATE_SUCCESS,
        asset
    };
};

export const assetUpdateError = function assetUpdateError(error) {
    return {
        type: ASSET_UPDATE_ERROR,
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
