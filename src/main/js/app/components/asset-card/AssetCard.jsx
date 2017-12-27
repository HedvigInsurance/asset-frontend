import React from 'react';
import { Card, Image, Dropdown } from 'semantic-ui-react';
import moment from 'moment';
import { assetStates } from 'app/lib/selectOptions';

/* eslint-disable react/prop-types */
export default class AssetCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            disabled: false
        };
        this.dropdownHandler = this.dropdownHandler.bind(this);
    }

    dropdownHandler(e, { value }) {
        this.setState(() => ({ disabled: true }));
        this.props.assetUpdate(this.props.asset.id, value);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.updateStatus !== this.props.updateStatus) {
            this.setState({
                disabled: false
            });
        }
    }

    render() {
        const { asset } = this.props;
        const assetDate = moment(
            asset.registrationDate,
            'YYYY-MM-DD HH:mm'
        ).format('MMMM Do YYYY');
        return (
            <Card>
                <Image src={asset.photoUrl} />
                <Card.Content>
                    <Card.Header>{asset.title}</Card.Header>
                    <Card.Meta>{assetDate}</Card.Meta>
                    <Card.Description>{asset.userId}</Card.Description>
                </Card.Content>
                <Card.Content extra>
                    <Dropdown
                        className="dropdown--short-text"
                        onChange={this.dropdownHandler}
                        options={assetStates}
                        placeholder="Choose asset state"
                        selection
                        style={{ width: '100%' }}
                        value={asset.state}
                        disabled={this.state.disabled}
                    />
                </Card.Content>
            </Card>
        );
    }
}
