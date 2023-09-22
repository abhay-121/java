import { applyMiddleware, legacy_createStore as createstore } from "redux";
import createSagaMiddleware from "redux-saga";
import logger from "redux-logger";
import rootReducer from "./rootReducer";
import rootSaga from "./blogsaga";

const sagaMiddleware = createSagaMiddleware();
const middlewares = [sagaMiddleware];

if(process.env.NODE_ENV === "development"){
    middlewares.push(logger)
}

const store = createstore(rootReducer, applyMiddleware(...middlewares));
sagaMiddleware.run(rootSaga);

export default store;