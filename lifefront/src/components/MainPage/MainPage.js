import React, { Component } from 'react'
import { Link, Redirect } from 'react-router-dom'
import './MainPage.css'
import {withGlobalState} from 'react-globally'
class MainPage extends Component {
    render() {
        if(this.props.globalState.logged){
            return <Redirect to='/home'/>
        }
        return (
            <div id='MainPage'>
                <h2 id='MainTitle'>Life</h2>
                <Link id='AdBtn' className="link btn" to='/sign-up'><p>Create new account!</p></Link>
            </div>
        )
    }
}
export default withGlobalState(MainPage);