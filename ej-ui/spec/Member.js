/* global VERSION */
import 'normalize.css';
import React, {Component} from 'react';
import {Button} from '../components/button';
import {Input} from "../components/input/index";
import {user} from "./models/User"
import {Snackbar} from "../components/snackbar/index";

class Member extends Component {

	constructor(props) {
		super(props)
		this.loginUsernameChange = this.loginUsernameChange.bind(this)
		this.loginPasswordChange = this.loginPasswordChange.bind(this)
		this.registerUsernameChange = this.registerUsernameChange.bind(this)
		this.registerPasswordChange = this.registerPasswordChange.bind(this)
		this.login = this.login.bind(this)
		this.register = this.register.bind(this)
	}

	componentWillMount() {
		this.state = {
			loginStatus: user.get('isAuthenticated'),
			loginUsername: "",
			loginPassword: "",
			registerUsername: "",
			registerPassword: "",
			snackbarActive: false,
			snackbarMsg: ""

		}
	}

	render() {
		return (
			<section>
				<h1>Login</h1>
				<Input type="text" name="loginUsername" required value={this.state.loginUsername}
					   onChange={this.loginUsernameChange}
					   label="Username"/>
				<Input type="password" name="loginPassword" required value={this.state.loginPassword}
					   onChange={this.loginPasswordChange}
					   label="Password"/>
				<Button style={{float: 'right'}} label='Login' onRippleEnded={this.login} raised primary/>
				<br/>

				<h1>Register</h1>
				<Input type="text" name="registerUsername" value={this.state.registerUsername}
					   onChange={this.registerUsernameChange}
					   label="Username"/>
				<Input type="password" name="registerPassword" value={this.state.registerPassword}
					   onChange={this.registerPasswordChange}
					   label="Password"/>
				<Button style={{float: 'right'}} label='Register' onRippleEnded={this.register} raised primary/>
				<br/>
				<br/>
				<br/>
				<Snackbar
					action='Dismiss'
					active={this.state.snackbarActive}
					label={this.state.snackbarMsg}
					timeout={2000}
					onClick={this.handleSnackbarClick}
					onTimeout={this.handleSnackbarTimeout}
					type='cancel'
				/>
			</section>
		)
	}


	login() {
		let valid;
		valid = this.validation(
			(this.state.loginPassword != "" && this.state.loginPassword.length > 2),
			"Password cannot be empty and smaller than 3 character"
		)
		if (!valid) {
			return valid;
		}
		valid = this.validation(
			(this.state.loginUsername != "" && this.state.loginUsername.length > 1),
			"Username cannot be empty or smaller than 3 character"
		)
		if (!valid) {
			return valid;
		}
		user.set({
			username: this.state.loginUsername,
			password: this.state.loginPassword
		}).login().then(function (response) {
			console.log(response);
			if (response.entity.indexOf("html") < 0) {
				this.setState({
					loginStatus: true
				})
				user.set("isAuthenticated", true)
			}
			if (response.entity.indexOf("html") > -1) {
				this.setState({
					snackbarMsg: "Your login attempt was not successful, try again.",
					snackbarActive: true
				})
				user.set("isAuthenticated", false)
			}
		}.bind(this))

	}

	register() {
		let valid;
		valid = this.validation(
			(this.state.registerPassword != "" && this.state.registerPassword.length > 2),
			"Password cannot be empty and smaller than 3 character"
		)
		if (!valid) {
			return valid;
		}
		valid = this.validation(
			(this.state.registerUsername != "" && this.state.registerUsername.length > 1),
			"Username cannot be empty or smaller than 3 character"
		)
		if (!valid) {
			return valid;
		}

		user.set({
			username: this.state.registerUsername,
			password: this.state.registerPassword
		}).register().then(function (response) {
			if (response.status.code == 200) {
				this.setState({
					snackbarMsg: "Successfully registered. Try login!",
					snackbarActive: true
				})
			}
			if (response.status.code == 500) {
				this.setState({
					loginStatus: false
				})
				this.setState({
					snackbarMsg: "Username already registered",
					snackbarActive: true
				})

			}
		}.bind(this))
	}

	loginUsernameChange = (value, ev) => {
		this.setState({[ev.target.name]: value})
	}

	loginPasswordChange = (value, ev) => {
		this.setState({[ev.target.name]: value})
	}

	registerUsernameChange = (value, ev) => {
		this.setState({[ev.target.name]: value})
	}

	registerPasswordChange = (value, ev) => {
		this.setState({[ev.target.name]: value})
	}

	handleSnackbarClick = (event, instance) => {
		this.setState({snackbarActive: false})
	}

	handleSnackbarTimeout = (event, instance) => {
		this.setState({snackbarActive: false})
	}

	validation = (valid, msg) => {
		if (!valid) {
			this.setState({
				snackbarMsg: msg,
				snackbarActive: true
			})
		}
		return valid;
	}
}

export default Member;
