'use strict';

import React from 'react';
import { Provider } from 'react-redux';
import { Router, Route, Switch } from 'react-router';
import createBrowserHistory from 'history/createBrowserHistory'

import Routes from './routes';
import Store from './store';

const history = createBrowserHistory();

const { configureStore } = Store;
const store = configureStore();

export default class App extends React.Component {
    render() {
        return (
            <Provider store={store}>
                <Router history={history}>
                    <Switch>
                        <Route path="/login" component={Routes.LoginPageRoute} />
                        <Route path="/assets" component={Routes.MainPageRoute} />
                        <Route component={Routes.LoginPageRoute}/>
                    </Switch>
                </Router>
            </Provider>
        );
    }
}
