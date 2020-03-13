import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import './MainPage.css'
export default class MainPage extends Component {
    render() {
        return (
            <div id='MainPage'>
                <h2 id='MainTitle'>Life</h2>
                <Link id='AdBtn' className="link btn" to='/login'><p>Create new account!</p></Link>
            </div>
        )
    }
}
