'use strict';

const webpack = require('webpack');
const BrowserSyncPlugin = require('browser-sync-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const WebpackNotifierPlugin = require('webpack-notifier');

const path = require('path');
const buildPath = path.resolve(__dirname, 'src/main/resources/static');

module.exports = {
    devtool: 'eval-source-map',
    devServer: {
        contentBase: buildPath,
        historyApiFallback: true,
        proxy: [{
            context: [
                '/api',
                '/auth'
            ],
            target: 'http://127.0.0.1:8080',
            secure: false
        }, {
            context: [
                '/websocket'
            ],
            target: 'ws://127.0.0.1:8080',
            ws: true
        }],
        watchOptions: {
            ignored: /node_modules/
        }
    },
    entry: {
        main: './src/main/js/app/app'
    },
    output: {
        path: buildPath,
        filename: '[name].js'
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /node_modules/,
                loaders: [
                    {
                        loader: 'babel-loader',
                        query: {
                            cacheDirectory: true,
                            presets: ['es2015', 'react']
                        }
                    },
                    {
                        loader: 'eslint-loader',
                        options: {
                            emitWarning: true,
                            failOnWarning: false,
                            failOnError: false,
                        }
                    },
                ]
            },
            {
                test: /\.css$/,
                exclude: /node_modules/,
                loaders: [
                    {
                        loader: 'style-loader',
                    },
                    {
                        loader: 'css-loader',
                        options: {
                            importLoaders: 1,
                        }
                    },
                    {
                        loader: 'postcss-loader'
                    }
                ]
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './src/main/js/index.html',
            chunksSortMode: 'dependency',
            inject: 'body'
        }),
        new BrowserSyncPlugin({
            host: 'localhost',
            port: 9000,
            proxy: {
                target: 'http://localhost:9060',
                ws: true
            }
        }, {
            reload: false
        }),
        new webpack.NoEmitOnErrorsPlugin(),
        new webpack.NamedModulesPlugin(),
        new WebpackNotifierPlugin()
    ]
};
