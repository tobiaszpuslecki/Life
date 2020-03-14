import React, { Component } from 'react'
import axios from 'axios'
import AuthenticationService from '../../service/AuthenticationService'
import {withGlobalState} from 'react-globally'
import { Redirect } from 'react-router'
class Home extends Component {
    state={
        quest: {}
    }
    componentDidMount(){
    AuthenticationService.setupAxiosInterceptors();
      axios.get("http://localhost:8080/quests")
      .then( response => (
          this.setState({quest:response.data})
      ) 
      )
    }
    render() {
        if(!this.props.globalState.logged){
            return <Redirect to='/login'/>
        }
        return (
            <div>
            </div>
        )
    }
}
export default withGlobalState(Home)