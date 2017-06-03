/* global VERSION */
import 'normalize.css';
import React, {Component} from 'react';

import {Layout} from '../components/layout';
import AppBar from '../components/app_bar';
import {Button} from '../components/button';
import style from './style.css';
import Link from "react-router-dom/es/Link";

class Root extends Component {
	render() {
		return (
			<Layout>
				<AppBar
					title={`Homepage`}
					className={style.appbar}
					fixed
					flat
				>
					<Link to="/">
						<Button label="Homepage" neutral inverse/>
					</Link>

					<Link to="/aboutus">
						<Button label="AboutUs" neutral inverse/>
					</Link>
					<Link to="/blog">
						<Button label="Blog" neutral inverse/>
					</Link>
					<Link to="/register">
						<Button label="Register" neutral inverse/>
					</Link>
				</AppBar>

			</Layout>
		);
	}
}

export default Root;
