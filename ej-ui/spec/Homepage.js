/* global VERSION */
import 'normalize.css';
import React, {Component} from 'react';

import {Layout} from '../components/layout';
import AppBar from '../components/app_bar';
import {Button} from '../components/button';
import style from './style.css';

class Root extends Component {
	render() {
		return (
			<Layout>
				<AppBar
					title={`Homepage`}
					className={style.appbar}
					leftIcon="menu"
					fixed
					flat
				>
					<Button href="/" label="Homepage" neutral inverse/>
					<Button href="/aboutus" label="AboutUs" neutral inverse/>
					<Button href="/blog" label="Blog" neutral inverse/>
					<Button href="/register" label="Blog" neutral inverse/>
				</AppBar>

			</Layout>
		);
	}
}

export default Root;
