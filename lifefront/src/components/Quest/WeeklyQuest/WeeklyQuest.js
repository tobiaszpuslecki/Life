import React, { Component } from 'react'
import axios from 'axios'
import AuthenticationService from '../../../service/AuthenticationService'
import './WeeklyQuest.css'
export default class WeeklyQuest extends Component {

    onClick = () =>{
        axios({
            method: 'POST',
            url:`http://localhost:8080/weekly/${AuthenticationService.getLoggedInUserName()}`
        })
        .then(() =>{
            this.props.toggleFinishedWeekly()
        })
    }
    render() {
        if(this.props.userFinishedWeekly === true){
            return(
                <div className="weeklyContainer">
                    <p className="finished">You've finished weekly task</p>
                </div>
            )
        }
        else{
            return (
                <div className="weeklyContainer">
                    <h1 className="weeklyHeader">Weekly challenge</h1>
                    <h1>{this.props.quest.name}</h1>
                    <h2>{this.props.quest.description}</h2>
                    <h3>You will earn {this.props.quest.points} points</h3>
                    <button onClick={this.onClick}>DONE!</button>
                </div>
            )
        }
    }
}
