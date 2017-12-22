import React from 'react';
import { Link } from 'react-router-dom';
import { Card, Dimmer, Loader, Segment, Message } from 'semantic-ui-react';
import AssetCard from '../asset-card/AssetCard.jsx';

// eslint-disable-next-line react/prop-types
const AssetList = ({ assetsList, errors, assetUpdate, updateStatus }) => {
    return (
        <Segment className="assets-list">
            <Dimmer active={assetsList && !assetsList.length} inverted>
                <Loader size="large">Loading</Loader>
            </Dimmer>
            {errors && errors.length ? (
                <AssetListErrors errors={errors} />
            ) : (
                <Card.Group itemsPerRow={4}>
                    {assetsList &&
                        !!assetsList.length &&
                        assetsList.map(asset => (
                            <AssetCard
                                key={asset.id}
                                asset={asset}
                                assetUpdate={assetUpdate}
                                updateStatus={updateStatus}
                            />
                        ))}
                </Card.Group>
            )}
        </Segment>
    );
};

const AssetListErrors = errors => {
    return errors.errors.map((err, id) => (
        <Message negative key={id}>
            <p>
                {err.message}.
                {(err.status === 403 || err.status === 401) && (
                    <span>
                        Go to <Link to="/login">login</Link>
                    </span>
                )}
            </p>
        </Message>
    ));
};

export default AssetList;
