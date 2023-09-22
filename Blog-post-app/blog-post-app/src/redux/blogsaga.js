import * as types from "./actionType";

import{ take, 
        takeEvery, 
        takeLatest, 
        put, 
        all, 
        delay, 
        fork,
        call} from "redux-saga/effects";

    import { loadPostError, 
            loadPostSuccess, 
            createPostSuccess, 
            createPostError,
            deletePostSuccess,
            deletePostError,
            updatePostSuccess,
            updatePostError
            } from "./action";
    import { loadPostsApi, 
            createPostApi, 
            deletePostApi,
            updatePostApi
            } from "./api";

    function* onLoadPostStartAsync(){
        try{
            const response = yield call(loadPostsApi);
            if(response.status === 200){
                yield delay(500);
                yield put(loadPostSuccess(response.data))
            }
            }catch(error){
                yield put(loadPostError(error.response.data))
        }
    }
    function* onCreatePostStartAsync({payload}){
        try{
            const response = yield call(createPostApi, payload);
            if(response.status === 201){
                yield put(createPostSuccess(response.data))
            }
            }catch(error){
                yield put(createPostError(error.response.data))
        }
    }

    function* onUpdatePostStartAsync({payload:{id, formValue}}){
        try{
            const response = yield call(updatePostApi, id, formValue);
            if(response.status === 200){
                yield put(updatePostSuccess())
            }
            }catch(error){
                yield put(updatePostError(error.response.data))
        }
    }

    function* onDeletePostStartAsync(postId){
        try{
            const response = yield call(deletePostApi, postId);
            if(response.status === 200){
                yield delay(200);
                yield put(deletePostSuccess(postId))
            }
            }catch(error){
                yield put(deletePostError(error.response.data))
        }
    }

    export function* onDeletePost(){
        while(true){
            const {payload: postId} = yield take(types.DELETE_POST_START);
            yield call(onDeletePostStartAsync, postId);
        }
    }

    export function* onLoadPost(){
        yield takeEvery(types.LOAD_POST_START, onLoadPostStartAsync);
    }

    export function* onCreatePost(){
        yield takeLatest(types.CREATE_POST_START, onCreatePostStartAsync);
    }
    export function* onUpdatePost(){
        yield takeLatest(types.UPDATE_POST_START, onUpdatePostStartAsync);
    }

    const postSagas = [fork(onLoadPost), fork(onCreatePost), fork(onDeletePost), fork(onUpdatePost)]

    export default function* rootSaga(){
        yield all([...postSagas]);
    }



