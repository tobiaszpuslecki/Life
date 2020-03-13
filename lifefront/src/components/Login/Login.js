import React, { Component } from 'react'
import "./login.css"
import AuthenticationService from "../../service/AuthenticationService"
import { Redirect } from 'react-router'
export default class Login extends Component {
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
            this.setState({username: username})
            this.setState({ showSuccessMessage: true })
            this.setState({ hasLoginFailed: false })
        }).catch(() => {
            this.setState({ showSuccessMessage: false })
            this.setState({ hasLoginFailed: true })
        })
      }
    render() {
        const { from } = this.props.location.state || { from: { pathname: '/' } }
        console.log(this.state.showSuccessMessage)
        if(this.state.showSuccessMessage === true){
            return <Redirect to={from}/>
        }
        return (
            <div>
                <form  onSubmit={this.submit}>
                    <input type="text" name = "username" placeholder="Login" onChange={this.onChangeUser} />
                    <input type="password" name = "password" placeholder="password" onChange={this.onChangePassword} />
                    <input type="submit" value="Log in" />
                </form>
            </div>
        )
    }
}
