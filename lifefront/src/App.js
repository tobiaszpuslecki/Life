import React, { Component } from 'react'
import Login from "./components/Login/Login"
import {BrowserRouter as Router, Route,Link, Redirect} from 'react-router-dom'
import ProtectedRoute from './service/ProtectedRoute'
import Home from './components/home/Home'
import AuthenticationService from './service/AuthenticationService'
import Header from './components/Header/Header'
export default class App extends Component {
  render() {
    return (
      <Router>
      <div>
        <Header/>
        <Route path="/"/>
        <Route
          exact path="/login"
          render={(props) => <Login {...props}/>}
        />
        <ProtectedRoute path="/home" component={Home}/>
      </div>
      </Router>

    )
  }
}
