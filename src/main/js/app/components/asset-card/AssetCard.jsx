import React from 'react';
import { Card, Image, Dropdown } from 'semantic-ui-react';
import moment from 'moment';

const assetStates = [
    { key: 0, text: 'CREATED', value: 'CREATED' },
    { key: 1, text: 'PENDING', value: 'PENDING' },
    { key: 2, text: 'WAITING_FOR_PAYMENT', value: 'WAITING_FOR_PAYMENT' },
    { key: 3, text: 'NOT_COVERED', value: 'NOT_COVERED' },
    { key: 4, text: 'COVERED', value: 'COVERED' },
    { key: 5, text: 'DELETED', value: 'DELETED' }
];

/* eslint-disable react/prop-types */
export default class AssetCard extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            disabled: false,
        }
        this.dropdownHandler = this.dropdownHandler.bind(this);
    }

    dropdownHandler(e, { value }) {
        this.setState(() => ({ disabled: true }));
        this.props.assetUpdate(this.props.asset.id, value);
    }

    componentWillReceiveProps(nextProps) {
        if (nextProps.updateStatus !== this.props.updateStatus) {
            this.setState({
                disabled: false,
            })
        }
    }

    render() {
        const { asset } = this.props;
        const date = asset.registrationDate;
        const assetDate = moment()
            .year(date.year)
            .month(date.monthValue)
            .dayOfYear(date.dayOfYear)
            .format('MMMM Do YYYY');
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
                        onChange={this.dropdownHandler}
                        options={assetStates}
                        placeholder="Choose asset state"
                        selection
                        style={{width: '100%'}}
                        value={asset.state}
                        disabled={this.state.disabled}
                    />
                </Card.Content>
            </Card>
        );
    }
}