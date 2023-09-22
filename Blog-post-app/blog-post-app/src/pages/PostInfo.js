import React, { useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { useSelector } from 'react-redux';
import {MDBCard, MDBCardBody, MDBCardHeader, MDBBtn, MDBIcon} from 'mdb-react-ui-kit'

 const PostInfo = () => {

    const[liked, setLike] = useState(false);
    const {posts} = useSelector(state => state.data);
    const {id} = useParams();
    const navigate = useNavigate();
    const singlePost = posts.find((post) => post.id === Number(id));

    const toggle = () =>{
        if(!liked){
            setLike(true);
        }else{
            setLike(false);
        }
    }

    return ( 
        <div className='col d-flex justify-content-center'>
                <div className='mt-5'>
                    <MDBCard style={{width:"50rem"}}>
                        <MDBCardHeader className="text-white mid bg-secondary">
                            <h3>{singlePost.categories}</h3>
                        </MDBCardHeader>
                        <MDBCardBody>
                            <p className='fw-bold text-gray'>{singlePost.title}</p>
                            <p>{singlePost.content}</p>
                            <MDBBtn color='white' className="btn-outline-secondary" 
                                    onClick={()=>navigate("/")}>Go Back</MDBBtn>
                            <MDBBtn onClick={toggle} className='btn btn-outline-secondary mx-2 text-secondary' color='none'>Like{!liked ?
                                    <MDBIcon  title ="Like" fas icon="heart" 
                                                style={{ marginLeft:"4px", fontSize:"15px", color:"gray"}}/> 
                                    : <MDBIcon  title ="Like" fas icon="heart" 
                                                style={{ marginLeft:"4px", fontSize:"15px", color:"red"}}/>  }

                            </MDBBtn>
                        </MDBCardBody>
                    </MDBCard>  
                </div>
            </div>
     );
 }
  
 export default PostInfo;