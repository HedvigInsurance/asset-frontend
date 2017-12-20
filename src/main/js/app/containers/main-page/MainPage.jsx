/* eslint-disable react/prop-types */
import React from 'react';
import { connect } from 'react-redux';
import { assetUpdate, assetRequest } from '../../store/actions/assetsActions';

class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.fetchAssets = this.fetchAssets.bind(this);
    }

    fetchAssets() {
        const { assetRequest } = this.props;
        return assetRequest();
    }

    componentDidMount() {
        // this.fetchAssets();
    }

    render() {
        const list =
            this.props.assets.list &&
            this.props.assets.list.map(item => (
                <li key={item.id}>
                    <span>{item.title} - {item.state}</span>
                </li>
            ));
        return (
            <div>
                <h1>Main Page</h1>
                <button onClick={this.fetchAssets}>get assets</button>
                <ul> {list} </ul>
            </div>
        );
    }
}

const mapStateToProps = ({ assets }) => {
    return {
        assets: assets
    };
};

export default connect(mapStateToProps, { assetUpdate, assetRequest })(
    MainPage
);
