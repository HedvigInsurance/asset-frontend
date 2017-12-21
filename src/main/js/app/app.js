'use strict';

import React from 'react';
import { Provider } from 'react-redux';
import { Router, Route, Switch } from 'react-router';
import createBrowserHistory from 'history/createBrowserHistory';

import Routes from './routes';
import Store from './store';

const history = createBrowserHistory();
const store = Store.configureStore();

export default class App extends React.Component {
    render() {
        const PrivateRoute = Routes.PrivateRoute;
        return (
            <Provider store={store}>
                <Router history={history}>
                    <Switch>
                        <Route path="/login" component={Routes.LoginPageRoute} />
                        <PrivateRoute path="/assets" store={store} component={Routes.MainPageRoute} />
                        <Route component={Routes.MainPageRoute}/>
                    </Switch>
                </Router>
            </Provider>
        );
    }
}
