import axios from "axios";

export const loadPostsApi = async() => 
    await axios.get("http://localhost:3010/posts");

export const createPostApi = async(post) => 
    await axios.post("http://localhost:3010/posts", post);

export const deletePostApi = async(postId) => 
    await axios.delete(`http://localhost:3010/posts/${postId}`);

export const updatePostApi = async(postId, postInfo) => 
    await axios.put(`http://localhost:3010/posts/${postId}`, postInfo);