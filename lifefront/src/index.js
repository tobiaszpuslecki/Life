import React from 'react';
import {Provider} from 'react-globally'
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import AuthenticationService from './service/AuthenticationService';
const initialState = {
    logged: AuthenticationService.isUserLoggedIn()
  }
  ReactDOM.render(
    <Provider globalState={initialState}>
      <App />
    </Provider>,
    document.getElementById('root')
  )

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
