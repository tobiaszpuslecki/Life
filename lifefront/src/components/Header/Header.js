import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import AuthenticationService from "../../service/AuthenticationService"
import {withGlobalState} from 'react-globally'
import './Header.css'
class Header extends Component {
    logout = () =>{
        AuthenticationService.logout();
        this.props.setGlobalState(prevGlobalState => ({
            logged: false
        }))
        
    }
    render() {
        console.log(this.props.globalState.logged)
        if(this.props.globalState.logged){
            
            return(
                <div className='header'>
                    <Link className='link logo' to="/">Life</Link>
                    <Link className='link' to="/home">Home</Link>
                    <Link className='link' to="/" onClick={this.logout}>Logout</Link>
                    

                </div>
            )
        }
        return (
            <div className='header'>
                <Link className='link logo' to="/">Life</Link>
                <Link className='link' to="/login">Login</Link>
                <Link className='link' to="/sign-up">Sign up</Link>
            </div>
        )
    }
}
export default withGlobalState(Header)