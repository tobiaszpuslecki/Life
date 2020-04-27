import React, { Component } from 'react'
import axios from 'axios'
import AuthenticationService from '../../service/AuthenticationService'
import {withGlobalState} from 'react-globally'
import { Redirect } from 'react-router'
import DailyQuest from '../Quest/Daily/DailyQuest'
import WeeklyQuest from '../Quest/WeeklyQuest/WeeklyQuest'
import UserInfo from './UserInfo'
class Home extends Component {
    state={
        quest: {},
        daily: {},
        weekly: {},
        userFinishedDaily: false,
        userFinishedWeekly: false,
        username:'',
        xp: 0
    }
    toggleFinishedDaily = () =>{
        this.setState({
            userFinishedDaily: !this.state.userFinishedDaily
        })
    }
    toggleFinishedWeekly = () =>{
        this.setState({
            userFinishedWeekly: !this.state.userFinishedWeekly
        })
    }
    componentDidMount(){
    AuthenticationService.setupAxiosInterceptors();
    // set interval to check sync user info with server
        this.sync()
      this.interval = setInterval(this.sync,1000)
    }
    componentWillUnmount(){
        clearInterval(this.interval)
    }
    sync = () =>{
        console.log("Syncing")
        axios({
            method:'GET',
            url:`http://localhost:8080/users/${AuthenticationService.getLoggedInUserName()}`
        })
        .then((response)=>{
            this.setState({
                userFinishedDaily: response.data.doneDaily,
                userFinishedWeekly: response.data.doneWeekly,
                username: response.data.username,
                xp:response.data.xp
            })
        })
            //get daily quest
        axios({
            method:'get',
            url:`http://localhost:8080/daily/${AuthenticationService.getLoggedInUserName()}`
        })
        .then((response) =>{
            console.log(response)
            this.setState({
                daily:response.data
            })
        })
        .catch(()=>{
            this.setState({
                userFinishedDaily: true
            })
        })
        //get weekly quest
        axios({
            method:'get',
            url:`http://localhost:8080/weekly/${AuthenticationService.getLoggedInUserName()}`
        })
        .then((response) =>{
            console.log(response)
            this.setState({
                weekly:response.data
            })
        })
        .catch(()=>{
            this.setState({
                userFinishedWeekly: true
            })
        })
    }
    render() {
        if(!this.props.globalState.logged){
            return <Redirect to='/login'/>
        }
        return (
            <div>
                <UserInfo username={this.state.username} xp={this.state.xp}/>
                <WeeklyQuest quest={this.state.weekly} userFinishedWeekly={this.state.userFinishedWeekly} toggleFinishedWeekly={this.toggleFinishedWeekly}/>
                 <DailyQuest quest={this.state.daily} userFinishedDaily={this.state.userFinishedDaily} toggleFinishedDaily={this.toggleFinishedDaily}/>
            </div>
        )
        
    }
}
export default withGlobalState(Home)
