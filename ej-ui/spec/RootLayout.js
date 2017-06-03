/* global VERSION */
import 'normalize.css';
import React, {Component} from 'react';

import { Layout, Panel } from '../components/layout';
import AppBar from '../components/app_bar';
import Button from '../components/button';
import style from './style.css';
import Link from "react-router-dom/es/Link";

class RootLayout extends Component {
	constructor(props){
		super(props)
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
					<Link to="/register">
						<Button label="Register" neutral inverse/>
					</Link>
				</AppBar>
				<Panel className={style.app}>
					{this.props.container()}
				</Panel>
			</Layout>
		);
	}
}

export default RootLayout;
