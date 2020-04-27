import React, { Component } from 'react'
import Axios from 'axios'
import AuthenticationService from '../../service/AuthenticationService'
import { Redirect } from 'react-router'
import './SignUp.css'
import {withGlobalState} from 'react-globally'
class SignUp extends Component {
    state={
        username:'',
        password:'',
        completed: false
    }
    onChangeUser = (e)=>{
        this.setState({
          [e.target.name] : e.target.value
        })
    }
    onChangePassword = (e)=>{
        this.setState({
          [e.target.name] : e.target.value
        })
    }
    onSubmit = (e) =>{
        e.preventDefault();
        if(this.state.username !== '' && this.state.password !== ''){
            e.preventDefault();
            AuthenticationService.setupAxiosInterceptors();
            Axios({
                method:'post',
                url: 'http://localhost:8080/users/sign-up',
                data:{
                    username: this.state.username,
                    password: this.state.password
                }
            }).then((response) =>{
                this.setState({completed:true})
                
            }).catch((e) =>{
                if(e.response.status === 409){
                    alert("This username is already in use")
                }
            })
        }
        else alert("Please provide valid username/password")
    }
    render() {
        if(this.state.completed){
            return <Redirect to='/login'/>
        }
        else if (this.props.globalState.logged){
            return <Redirect to={'/home'}/>
        }
        return (
            <div className='signUpContainer'> 
                <h1>Create new account</h1>
                <form  onSubmit={this.onSubmit}>
                    <input type="text" name = "username" placeholder="Login" onChange={this.onChangeUser} />
                    <input type="password" name = "password" placeholder="password" onChange={this.onChangePassword} />
                    <input className='btn' type="submit" value="Create account" />
                </form>
            </div>
        )
    }
}
export default withGlobalState(SignUp)