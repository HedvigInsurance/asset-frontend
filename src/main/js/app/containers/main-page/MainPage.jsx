import React from 'react';
import { connect } from 'react-redux';
import { Container, Button } from 'semantic-ui-react';
import actions from '../../store/actions';
import AssetList from '../../components/asset-list/AssetList.jsx';
import { checkAuthorization } from '../../lib/checkAuth';

/* eslint-disable react/prop-types */
class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.fetchAssets = this.fetchAssets.bind(this);
        this.pollStart = this.pollStart.bind(this);
        this.pollStop = this.pollStop.bind(this);
        this.logout = this.logout.bind(this);
    }

    pollStart() {
        const { pollStart, client: { token } } = this.props;
        pollStart(token, 2000);
    }

    pollStop() {
        this.props.pollStop();
    }

    logout() {
        this.props.unsetClient();
    }

    fetchAssets() {
        const { assetRequest, client: { token } } = this.props;
        return assetRequest(token);
    }

    componentDidMount() {
        checkAuthorization(null, this.props.setClient);
        this.fetchAssets();
    }

    componentWillUnmount() {
        this.pollStop();
    }

    render() {
        return (
            <Container>
                <h1>Assets List</h1>
                <Button onClick={this.fetchAssets}>get assets</Button>
                <Button onClick={this.pollStart}>poll start</Button>
                <Button onClick={this.pollStop}>poll stop</Button>
                <Button onClick={this.logout}>logout</Button>
                <AssetList
                    assetsList={this.props.assets.list}
                    errors={this.props.assets.errors}
                    assetUpdate={this.props.assetUpdate}
                    updateStatus={this.props.assets.requesting}
                />
            </Container>
        );
    }
}

const mapStateToProps = ({ assets, client, polling }) => ({
    assets,
    client,
    polling
});

export default connect(mapStateToProps, {
    ...actions.assetsActions,
    ...actions.clientActions,
    ...actions.pollActions
})(MainPage);
