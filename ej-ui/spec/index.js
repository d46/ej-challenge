import React from 'react';
import ReactDOM from 'react-dom';
import Homepage from './Homepage'
import {
	BrowserRouter as Router,
	Route
} from 'react-router-dom'

const HomePage = () => (
	<div>
		<Homepage />
	</div>
)

const AboutUs = () => (
	<div>
		<Homepage />
		<h1>AboutUs</h1>
	</div>
)

const Blog = () => (
	<div>
		<Homepage />
		<h1>Blog</h1>
	</div>
)

const Register = () => (
	<div>
		<Homepage />
		<h1>Register</h1>
	</div>
)

// Root Level
const Root = () => (
	<Router>
		<div>
			<Route exact path="/" component={HomePage}/>
			<Route path="/aboutus" component={AboutUs}/>
			<Route path="/blog" component={Blog}/>
			<Route path="/register" component={Register}/>
		</div>
	</Router>
)

ReactDOM.render(<Root />, document.getElementById('spec'));


