/* global VERSION */
import 'normalize.css';
import React, {Component} from 'react';

import {Layout, Panel} from '../components/layout';
import AppBar from '../components/app_bar';
import Button from '../components/button';
import style from './style.css';
import Link from "react-router-dom/es/Link";
import Cookies from 'universal-cookie';
import {user} from './models/User'
import $ from "jquery";
import {badgeProgress} from "./models/BadgeProgress"
import rest from "rest";


class RootLayout extends Component {
	constructor(props) {
		super(props)
		this.handleScroll = this.handleScroll.bind(this)
		this.pageChange = this.pageChange.bind(this)
	}

	state = {
		scrollCheck: true,
		timeOut:true,
		type:null
	}

	componentWillReceiveProps() {
		$('[data-react-toolbox="panel"]').scrollTop(0);
		if(this.state.timeOut != true){
			clearTimeout(this.state.timeOut)
		}
		this.state.timeOut = setTimeout(function () {
			badgeProgress.set({
				actionName: `EXPECTANT_${this.state.type}`
			}).record();
			this.setState({
				timeOut : true
			})
		}.bind(this), 15000)

	}

	logout() {
		new Cookies().set('JSESSIONID', "", {
			expires: new Date(-1)
		})
		rest({
			method: "GET",
			path: "http://localhost:8080/logout",
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			mixin: {
				withCredentials: true
			}
		}).then((response) => {
			user.set({
				//TODO: Spring security do not logout
				// isAuthenticated: false
			})
		});

	}

	handleScroll = (event) => {
		if ($(event.currentTarget).find(">div").offset().top * -1 > $(document).height() * 50 / 100 && this.state.scrollCheck) {
			this.state.scrollCheck = false;
			badgeProgress.set({
				actionName: `SCROLLER_${this.state.type}`
			}).record();
		}
	}

	pageChange(type) {
		this.setState({
			type:type
		})
		this.state.scrollCheck = true
		//Reset Viewport Scroll :bug:
		badgeProgress.set({
			actionName: `EXPLORER_${type}`
		}).record();

	}

	render() {
		return (
			<Layout >
				<AppBar
					title={`Emlakjet`}
					className={style.appbar}
					fixed
					flat
				>
					<Link to="/">
						<Button label="Home Page" onRippleEnded={()=>{this.pageChange("1")}} neutral inverse/>
					</Link>
					<Link to="/aboutus">
						<Button label="About Us" onRippleEnded={()=>{this.pageChange("2")}} neutral inverse/>
					</Link>
					<Link to="/toplist">
						<Button label="Top List" onRippleEnded={()=>{this.pageChange("3")}} neutral inverse/>
					</Link>
					<Link to="/blog">
						<Button label="Blog" onRippleEnded={()=>{this.pageChange("4")}} neutral inverse/>
					</Link>

					{user.get('isAuthenticated') ?
						<div>
							<Link to="/me">
								<Button label="Me" onRippleEnded={()=>{this.pageChange("5")}} neutral inverse/>
							</Link>
							<Button label="Logout" onRippleEnded={()=>{this.pageChange("6")}} neutral inverse/>
						</div>
						:
						<Link to="/member">
							<Button label="Member" onRippleEnded={()=>{this.pageChange("7")}}   neutral inverse/>
						</Link>
					}

				</AppBar>
				<Panel onScroll={this.handleScroll} scrollY className={style.app}>
					{this.props.container()}
				</Panel>
			</Layout>
		);
	}


}

export default RootLayout;
