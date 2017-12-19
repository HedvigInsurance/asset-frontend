'use strict';

/* eslint-env browser */
/* global module */

import 'babel-polyfill';
import React from 'react';
import ReactDOM from 'react-dom';
import './app.css';

import App from './app';

const appElement = document.getElementById('react');

/*eslint-disable*/
console.log(appElement);
console.log(App);

ReactDOM.render(
    <App />,
    appElement
);

if (module.hot) {
    module.hot.accept();
}
