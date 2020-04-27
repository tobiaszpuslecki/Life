import axios from 'axios'
import {withGlobalState} from 'react-globally'
const API_URL = 'http://localhost:8080'

export const USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
export const USER_JWT_TOKEN = 'JWToken'
export const USER_LOGGED_IN = 'logged_in'
class AuthenticationService {
    executeJwtAuthenticationService(username, password) {
        return axios({
            method:'post',
            url: `${API_URL}/login`,
            data:{
                username: username,
                password: password
            }
        })
    }
    registerSuccessfulLoginForJwt(username, token) {
        sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username)
        sessionStorage.setItem(USER_JWT_TOKEN, token)
        sessionStorage.setItem(USER_LOGGED_IN,true)
        this.setupAxiosInterceptors()
    }

    createJWTToken(token) {
        return token
    }


    logout() {
        sessionStorage.removeItem(USER_NAME_SESSION_ATTRIBUTE_NAME);
        sessionStorage.removeItem(USER_JWT_TOKEN);
        sessionStorage.removeItem(USER_LOGGED_IN)
    }

    isUserLoggedIn() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return false
        return true
    }

    getLoggedInUserName() {
        let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
        if (user === null) return ''
        return user
    }

    setupAxiosInterceptors() {
        axios.interceptors.request.use(
            (config) => {
                if (this.isUserLoggedIn()) {
                    config.headers.authorization = sessionStorage.getItem(USER_JWT_TOKEN)
                }
                return config
            }
        )
    }
}

export default new AuthenticationService()
