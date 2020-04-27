import React, { Component } from 'react'
import axios from 'axios'
import AuthenticationService from '../../../service/AuthenticationService'
import "./DailyQuest.css"
export default class DailyQuest extends Component {

    onClick = () =>{
        axios({
            method: 'POST',
            url:`http://localhost:8080/daily/${AuthenticationService.getLoggedInUserName()}`
        })
        .then(() =>{
            this.props.toggleFinishedDaily()
        })
    }

    render() {
        if(this.props.userFinishedDaily === true){
            return(
               <div className='dailyContainer'>
                    <p className="finished">You've finished daily task</p>
               </div>
            )
        }
        else{
            return (
                <div className='dailyContainer'>
                    <h1 className="dailyHeader">Daily challenge</h1>
                    <h1>{this.props.quest.name}</h1>
                    <h2>{this.props.quest.description}</h2>
                    <h3>You will earn {this.props.quest.points} points</h3>
                    <button onClick={this.onClick}>DONE!</button>
                </div>
            )
        }
    }
}
