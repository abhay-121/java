import React, { useState } from 'react';
import {
  MDBNavbar,
  MDBContainer,
  MDBIcon,
  MDBNavbarNav,
  MDBNavbarItem,
  MDBNavbarToggler,
  MDBNavbarBrand,
  MDBCollapse
} from 'mdb-react-ui-kit';
import {NavLink} from 'react-router-dom';

const Header = () => {
    const [showState, setShowBasic] = useState(false);
 return(
    <>
        <MDBNavbar expand='lg' dark bgColor='primary'>
        <MDBContainer fluid>
          <MDBNavbarBrand className='text-white fw-bold'>Blog App </MDBNavbarBrand>
          <MDBNavbarToggler
            aria-controls='navbarColor02'
            aria-expanded='false'
            aria-label='Toggle navigation'
            className='text-white'
            onClick={() => setShowBasic(!showState)}
          >
            <MDBIcon fas icon = "bars"/>
          </MDBNavbarToggler>

          <MDBCollapse  navbar show={showState}>
            <MDBNavbarNav className='me-auto mb-2 mb-lg-0'>
              <MDBNavbarItem className='active'>
                <NavLink to ="/" className=' m-2 text-white'>
                        Home
                </NavLink>
              </MDBNavbarItem>
              <MDBNavbarItem>
                    <NavLink to ="/addPost" className='m-2 text-white'>
                        New Post
                    </NavLink>
              </MDBNavbarItem>
            </MDBNavbarNav>
          </MDBCollapse>
        </MDBContainer>
      </MDBNavbar>
    </>
 )
}
export default Header;