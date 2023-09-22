import React, {useEffect, useState} from 'react';
import { Link, useNavigate, useParams} from 'react-router-dom';
import {useDispatch, useSelector} from 'react-redux';
import {createPostStart, updatePostStart} from '../redux/action';
import { toast } from 'react-toastify';
import { MDBValidation, 
            MDBInput, 
            MDBTextArea, 
         } from 'mdb-react-ui-kit';

const initialState ={
    title:"",
    categories:'',
    content:''
}

const AddEditPost = () => {
    const [formValue, setFormValue] = useState(initialState);
    const [editMode, setEditMode] = useState(false);
    const {title, categories, content} = formValue;

    const {posts} = useSelector(state => state.data);
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const {id} = useParams();

    useEffect(() => {
        if(id){
            setEditMode(true);
            const post = posts.find(item => item.id === Number(id));
            setFormValue({...post});
        }else{
            setEditMode(false);
            setFormValue({...initialState});
        }
    }, [id])


    const handleSubmit = (e) => {
        e.preventDefault();
        if(title && categories && content){
            if(!editMode){
                dispatch(createPostStart(formValue));
                toast.success("Post Added Successfully")
                setTimeout(() => navigate("/"), 500);
            }else{
                dispatch(updatePostStart({id, formValue}));
                toast.success("Post Updated Successfully")
                setEditMode(false);
                setTimeout(() => navigate("/"), 500);
            }
        }
    }

    const onInputChange = (e)=>{
        let {name, value} = e.target;
        setFormValue({...formValue, [name]: value});

    }
    return (
        
        <MDBValidation className='container' 
                    style={{marginTop:"10px"}} 
                    noValidate 
                    onSubmit={handleSubmit}>
                    <div className='container'>
                        <div className="">
                            <Link to="/">
                                <button className="btn btn-outline-success mt-3 mb-2"> Go Back</button>
                            </Link>
                        </div>
                    </div>
                    <p className='fw-bold fs-2 text-center text-info'>
                        {!editMode ? "Add Post" : "Update Post"}
                    </p>
                    <div className='row'>
                        <div className='col-sm-2'></div>
                        <div className='col-sm-8'>
                            <MDBInput className="mb-3 form-control form-control-lg"
                            value={title || ''}
                            name="title"
                            type="text"
                            onChange={onInputChange}
                            required
                            label="Title"
                            validation="please enter a title"
                            />

                            <MDBInput className="form-control form-control-lg mb-3"
                            value={categories || ''}
                            name="categories"
                            type="text"
                            onChange={onInputChange}
                            required
                            label="categories"
                            validation="please provide categories"
                            />

                            <MDBTextArea className="form-control form-control-lg m-3"
                            value={content || ''}
                            name="content"
                            type="text"
                            onChange={onInputChange}
                            required
                            label="Content"
                            validation="Plese write some content"
                            />
                            <button className="btn btn-primary" type="submit">
                                {!editMode ? "Add Post" : "Update Post"}
                            </button>  
                        </div>
                    </div> 
        </MDBValidation>
    )
}
 
export default AddEditPost;