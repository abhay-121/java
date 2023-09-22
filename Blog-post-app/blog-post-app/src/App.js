import React from 'react';
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import './App.css';
import Header from './component/Header';
import AddEditPost from './pages/AddEditPost';
import Home from './pages/Home';
import PostInfo from './pages/PostInfo';
import {ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
function App() {
  return (
    <BrowserRouter>
      <div>
      <ToastContainer/>
      <Header/>
        <Routes>
          <Route exact path ="/" element={<Home/>}/>
          <Route  path ="/addPost" element={<AddEditPost/>}/>
          <Route  path ="/editPost/:id" element={<AddEditPost/>}/>
          <Route  path ="/postInfo/:id" element={<PostInfo/>}/>
        </Routes>
      </div>
    </BrowserRouter>
    
  );
}

export default App;
