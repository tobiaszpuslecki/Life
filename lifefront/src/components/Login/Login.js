import React, { Component } from 'react'
import "./login.css"
import AuthenticationService from "../../service/AuthenticationService"
import { Redirect } from 'react-router'
import {withGlobalState} from 'react-globally'
class Login extends Component {
    state={
        username: '',
        password: '',
        showSuccessMessage: false,
        hasLoginFailed: false
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
      submit = (e) =>{
        e.preventDefault();
        this.login(this.state.username,this.state.password);
      }
      login = (username,password) =>{
        
        AuthenticationService
        .executeJwtAuthenticationService(username, password)
        .then((response) => {
          AuthenticationService.registerSuccessfulLoginForJwt(username, response.headers.authorization)
          this.props.setGlobalState(() =>({
            logged:true
        }))
        }).catch(() => {
          alert('The username or password is incorrect')
        })
      }
    render() {
        const { from } = this.props.location.state || { from: { pathname: '/' } }
        if(this.props.globalState.logged){
            return <Redirect to={'/home'}/>
        }
        return (
            <div className='loginContainer'>
                <h1>Life</h1>
                <form  onSubmit={this.submit}>
                    <input type="text" name = "username" placeholder="Login" onChange={this.onChangeUser} />
                    <input type="password" name = "password" placeholder="password" onChange={this.onChangePassword} />
                    <input className='btn' type="submit" value="Log in" />
                </form>

            </div>
        )
    }
}
export default withGlobalState(Login)