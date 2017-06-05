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

class RootLayout extends Component {
	constructor(props) {
		super(props)
	}

	logout() {
		console.log(new Cookies().get("JSESSIONID"));
		new Cookies().set('JSESSIONID',"",{
			expires:new Date(-1)
		})
		user.set({
			isAuthenticated: false
		})
	}

	render() {
		return (
			<Layout>
				<AppBar
					title={`Emlakjet`}
					className={style.appbar}
					fixed
					flat
				>
					<Link to="/">
						<Button label="Home Page" neutral inverse/>
					</Link>
					<Link to="/aboutus">
						<Button label="About Us" neutral inverse/>
					</Link>
					<Link to="/blog">
						<Button label="Blog" neutral inverse/>
					</Link>

					{user.get('isAuthenticated') ?
						<Button label="Logout" onRippleEnded={this.logout} neutral inverse/>
						:
						<Link to="/member">
							<Button label="Member" neutral inverse/>
						</Link>
					}

				</AppBar>
				<Panel className={style.app}>
					{this.props.container()}
				</Panel>
			</Layout>
		);
	}
}

export default RootLayout;
