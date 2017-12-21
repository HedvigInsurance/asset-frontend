/* eslint-disable */
import React from 'react';
import { connect } from 'react-redux';
import { Container } from 'semantic-ui-react';
import { assetUpdate, assetRequest } from '../../store/actions/assetsActions';
import { pollStart, pollStop } from '../../store/actions/pollActions';
import AssetList from '../../components/asset-list/AssetList.jsx';

class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.fetchAssets = this.fetchAssets.bind(this);
        this.pollStart = this.pollStart.bind(this);
        this.pollStop = this.pollStop.bind(this);
    }

    pollStart() {
        this.props.pollStart(null, 2000);
    }

    pollStop() {
        this.props.pollStop();
    }

    fetchAssets() {
        const { assetRequest } = this.props;
        return assetRequest();
    }

    componentDidMount() {
        this.fetchAssets();
    }

    componentWillUnmount() {
        this.pollStop()
    }

    render() {
        return (
            <Container>
                <h1>Assets List</h1>
                {/*<button onClick={this.fetchAssets}>get assets</button>
                <button onClick={this.pollStart}>poll</button>
                <button onClick={this.pollStop}>poll stop</button>*/}
                <AssetList
                    assetsList={this.props.assets.list}
                    assetUpdate={this.props.assetUpdate}
                    updateStatus={this.props.assets.requesting}
                />
            </Container>
        );
    }
}

const mapStateToProps = ({ assets }) => {
    return {
        assets: assets
    };
};

export default connect(mapStateToProps, {
    assetUpdate,
    assetRequest,
    pollStart,
    pollStop
})(MainPage);
