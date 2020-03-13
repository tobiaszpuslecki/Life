import React, { Component } from 'react'
import axios from 'axios'
import AuthenticationService from '../../service/AuthenticationService'

export default class Home extends Component {
    state={
        quest: {}
    }
    componentDidMount(){
    AuthenticationService.setupAxiosInterceptors();
      axios.get("http://localhost:8080/quests/1")
      .then( response => (
          this.setState({quest:response.data})
      ) 
      )
    }
    test = () =>{
        axios.get("http://localhost:8080/quests/1")
        .then( response => (
            this.setState({quest:response.data})
        ) 
        )
    }
    render() {
        return (
            <div>
                <h2>
                    {this.state.quest.name}
                </h2>
                <p>
                    Description: {this.state.quest.description}
                </p>
                <p>
                    Points: {this.state.quest.points}
                </p>
            </div>
        )
    }
}
