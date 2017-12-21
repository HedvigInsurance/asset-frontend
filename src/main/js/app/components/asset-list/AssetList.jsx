import React from 'react';
// import PropTypes from 'prop-types';
import { Card, Dimmer, Loader, Segment } from 'semantic-ui-react';
import AssetCard from '../asset-card/AssetCard.jsx';

// eslint-disable-next-line react/prop-types
const AssetList = ({ assetsList, assetUpdate, updateStatus }) => {
    return (
        <Segment style={{minHeight: '300px'}}>
            <Dimmer active={!assetsList.length} inverted>
                <Loader size='large'>Loading</Loader>
            </Dimmer>
            <Card.Group itemsPerRow={4}>
                {assetsList.length ? (
                    assetsList.map(asset => (
                        <AssetCard
                            key={asset.id}
                            asset={asset}
                            assetUpdate={assetUpdate}
                            updateStatus={updateStatus}
                        />
                    ))
                ) : (
                    <h1>Assets list is empty</h1>
                )}
            </Card.Group>
        </Segment>
    );
};

export default AssetList;
