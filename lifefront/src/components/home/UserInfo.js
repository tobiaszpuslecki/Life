import React, { Component } from 'react'
import './UserInfo.css'
export default class UserInfo extends Component {
    render() {
        return (
            <div className="userInfoContainer">
                <p>Username: {this.props.username}</p>
                <p>Experience: {this.props.xp}</p>
            </div>
        )
    }
}
