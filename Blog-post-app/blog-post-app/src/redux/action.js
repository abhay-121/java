import * as types from "./actionType";

export const loadPostStart = () => ({
    type:types.LOAD_POST_START,
});
export const loadPostSuccess = (posts) => ({
    type:types.LOAD_POST_SUCCESS,
    payload: posts
});
export const loadPostError = (error) => ({
    type:types.LOAD_POST_ERROR,
    payload: error
});


export const createPostStart = (post) => ({
    type:types.CREATE_POST_START,
    payload:post
});
export const createPostSuccess = (post) => ({
    type:types.CREATE_POST_SUCCESS,
});
export const createPostError = (error) => ({
    type:types.CREATE_POST_ERROR,
    payload: error
});


export const deletePostStart = (postId) => ({
    type:types.DELETE_POST_START,
    payload:postId,
    
})
export const deletePostSuccess = (postId) => ({
    type:types.DELETE_POST_SUCCESS,
    payload:postId
})
export const deletePostError = (error) => ({
    type:types.DELETE_POST_ERROR,
    payload: error
})

export const updatePostStart = (postInfo) => ({
    type:types.UPDATE_POST_START,
    payload:postInfo,
    
})
export const updatePostSuccess = () => ({
    type:types.UPDATE_POST_SUCCESS,
})
export const updatePostError = (error) => ({
    type:types.UPDATE_POST_ERROR,
    payload: error
})