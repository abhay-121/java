import React, {useEffect}from 'react';
import{useDispatch, useSelector} from 'react-redux'
import { loadPostStart, deletePostStart } from '../redux/action';
import {MDBTable, 
    MDBTableHead, 
    MDBTableBody, 
    MDBBtn, 
    MDBIcon, 
    MDBSpinner} from 'mdb-react-ui-kit'
import {Link} from "react-router-dom"
import { toast } from 'react-toastify';

const Home = () => {
    const dispatch = useDispatch();
    const {posts, loading} = useSelector(state => state.data)

    useEffect(() => {
        dispatch(loadPostStart())
    }, []);

    if(loading){
        return (
            <div className='row justify-content-center '>
            <MDBSpinner style={{color:"green"}} role="status">
                <span className='visually-hidden'>Loding...</span>
            </MDBSpinner>
            </div>
            
        )
        
    }

    const handleDelete =(id) =>{
        console.log(id);
        if(window.confirm("Are you sure that you wanted to delete this post?")){
            dispatch(deletePostStart(id));
            toast.success("user deleted Successfully")
        }
    }


    return (
        <div className='container' style={{marginTop:"50px"}}>
        <h1 className='text-info text-center'>Blogs</h1>
        <MDBTable className='table'>
            <MDBTableHead className='table-info'>
                <tr>
                    <th scope ="col">Title</th>
                    <th className="mid">Action</th>
                </tr>
            </MDBTableHead>
            {posts &&  posts.map((post, id) =>(
                <MDBTableBody key = {post.id}>
                    <tr>
                        <td>{post.title}</td>
                        <td className="mid">
                            <MDBBtn className='m-1' tag="a" color='none' onClick={()=>handleDelete(post.id)}>
                                    <MDBIcon  title ="Delete" fas icon="trash" style={{color:"red",margin:"10px"}}/>  
                            </MDBBtn>
                            <Link to={`/editPost/${post.id}`}>
                                    <MDBIcon  title ="Edit" fas icon="pen" style={{color:"blue", margin:"10px"}}/>
                            </Link>
                            <Link to={`/postInfo/${post.id}`}>
                                    <MDBIcon  title ="View" fas icon="eye" style={{color:"gray", margin:"10px"}}/>
                                
                            </Link>

                        </td>
                    </tr>
                </MDBTableBody>

            ))}
            
        </MDBTable>
            
        </div>  
    );
}
 
export default Home;