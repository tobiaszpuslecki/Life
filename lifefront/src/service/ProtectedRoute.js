import React, { Component } from 'react'
import { Route, Redirect } from 'react-router'
import AuthenticationService from "./AuthenticationService"

const PrivateRoute = ({ component: Component, ...rest }) => (
    <Route {...rest} render={(props) => (
      AuthenticationService.isUserLoggedIn()
        ? <Component {...props} />
        : <Redirect to={{
          pathname: '/login',
          state: {from: props.location}
        }} />
    )} />
  )
export default PrivateRoute
