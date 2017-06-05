import React from 'react';
import ReactDOM from 'react-dom';
import {
	BrowserRouter as Router,
	Route
} from 'react-router-dom'
import 'normalize.css';
import RootLayout from "./RootLayout";
import Homepage from "./Homepage";
import AboutUs from "./AboutUs";
import Blog from "./Blog";
import Member from "./Member";
import {user} from "./models/User"



const Container = () => (
	<div>
		<Route exact path="/" component={Homepage}/>
		<Route path="/aboutus" component={AboutUs}/>
		<Route path="/blog" component={Blog}/>
		<Route path="/member" component={Member}/>
	</div>
)
// Root Level
const Root = () => (
	<Router>
		<div>
			<RootLayout container={Container}></RootLayout>
		</div>
	</Router>
)

user.checkStatus().then(function () {
	ReactDOM.render(<Root />, document.getElementById('spec'));
})



