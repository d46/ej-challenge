/* global VERSION */
import React, {Component} from 'react';
import {List, ListItem} from "../components/list/index";
import rest from "rest";

const listStyle = {
	border: '1px solid #EEE',
	display: 'inline-block',
	width: '100%',
};


class TopList extends Component {
	constructor(props) {
		super(props)
	}

	componentWillMount() {
		rest({
			method: "GET",
			path: "http://localhost:8080/reports/topList",
			headers: {'Content-Type': 'application/x-www-form-urlencoded'},
			mixin: {
				withCredentials: true
			}
		}).then((response) => {
			this.setState({
				topList: JSON.parse(response.entity)[0].content
			})
		});
	}

	state = {
		topList: []
	};


	render() {
		return (
			<section>
				<h5>User Top List</h5>
				<div style={listStyle}>
					<List selectable ripple>
						<div>
							{ this.state.topList.map((row, index) => (
								<ListItem key={index}
										  avatar="https://avatars2.githubusercontent.com/u/559654?v=3&s=460"
										  caption={row.username}
										  legend={row.totalScore + ""}
								/>
							))}
						</div>
					</List>
				</div>
			</section>
		);
	}
}

export default TopList;

