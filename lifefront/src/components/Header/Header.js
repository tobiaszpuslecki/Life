import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import AuthenticationService from "../../service/AuthenticationService"
export default class Header extends Component {
    state={
        logged:false
    }
    logout = () =>{
        AuthenticationService.logout();
        this.setState({logged: false});
    }
    render() {
        if(this.state.logged){
            return(
                <div>
                    <Link to="/home">Home</Link>
                    <Link to="/" onClick={this.logout}>Logout</Link>
                </div>
            )
        }
        return (
            <div>
                <Link to="/login">Login</Link>
                <Link to="/home">Home</Link>
            </div>
        )
    }
}
