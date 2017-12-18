import { createStore, combineReducers, applyMiddleware } from 'redux';
import { routerReducer, routerMiddleware } from 'react-router-redux';
import createHistory from 'history/createBrowserHistory';
import createSagaMiddleware from 'redux-saga';
import rootSaga from './sagas/index';
import reducers from './reducers';

const history = createHistory();

const rootReducer = combineReducers({
    ...reducers,
    routing: routerReducer
});

const configureStore = () => {
    const sagaMiddleware = createSagaMiddleware();
    const routerMid = routerMiddleware(history); 

    return {
        ...createStore(rootReducer, applyMiddleware(routerMid, sagaMiddleware)),
        runSaga: sagaMiddleware.run(rootSaga)
    };
}

export default configureStore;