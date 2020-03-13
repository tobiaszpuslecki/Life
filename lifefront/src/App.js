import React, { Component } from 'react'
import Login from "./components/Login/Login"
import {BrowserRouter as Router, Route,Link, Redirect} from 'react-router-dom'
import ProtectedRoute from './service/ProtectedRoute'
import Home from './components/home/Home'
import AuthenticationService from './service/AuthenticationService'
import Header from './components/Header/Header'
import SignUp from './components/SignUp/SignUp'
import {withGlobalState} from 'react-globally'
import MainPage from './components/MainPage/MainPage'
class App extends Component {
  render() {
    return (
      <Router>
        <Header></Header>
        <Route exact path="/sign-up" component={SignUp}/>
        <Route
          exact path="/login"
          render={(props) => <Login {...props}/>}
        />
        <Route exact path="/home" component={Home}/>
        <Route exact path="/" component={MainPage}/>
      </Router>

    )
  }
}
export default withGlobalState(App)