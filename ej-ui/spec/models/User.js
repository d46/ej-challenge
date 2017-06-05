import {Model} from "backbone-model";
import xwf from "../utils/json2xwf";
import rest from "rest";

class User extends Model {

	defaults() {
		return {
			username: '',
			password: '',
			totalScore: 0,
			isAuthenticated: false
		}
	}

	toXwf() {
		let json = this.toJSON()
		return xwf(json)
	}


	checkStatus() {
		return new Promise(function (resolve, reject) {
			rest({
				method: "GET",
				path: "http://localhost:8080/",
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				mixin: {
					withCredentials: true
				}
			}).then((response) => {
				if (response.status.code == 200) {
					try {
						let entity = JSON.parse(response.entity);
						this.set({
							username: entity.username,
							totalScore: entity.totalScore,
							isAuthenticated: true
						})
					} catch (err) {
						this.set({
							isAuthenticated: false
						})
					}
					resolve(response)
				}
			});
		}.bind(this))
	}

	login() {
		return new Promise(function (resolve, reject) {
			rest({
				method: "POST",
				path: "http://localhost:8080/login",
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				mixin: {
					withCredentials: true
				},
				entity: this.toXwf()
			}).then((response) => resolve(response));
		}.bind(this))
	}

	register() {
		return new Promise(function (resolve, reject) {
			rest({
				method: "POST",
				path: "http://localhost:8080/register",
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				mixin: {
					withCredentials: true
				},
				entity: this.toXwf()
			}).then((response) => resolve(response));
		}.bind(this))
	}



}

export let user = new User();
