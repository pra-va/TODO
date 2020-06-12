import { createStore, applyMiddleware, compose } from 'redux'
import thunkMiddleware from 'redux-thunk'
import { reducer } from './reducers'

declare global {
    interface Window {
        __REDUX_DEVTOOLS_EXTENSION_COMPOSE__?: typeof compose;
    }
}

const configureStore = () => {
    const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
    const middlewares = [ thunkMiddleware ]
    const middlewareEnforcer = composeEnhancers(applyMiddleware(...middlewares))
    return createStore(reducer, middlewareEnforcer)
}

export default configureStore