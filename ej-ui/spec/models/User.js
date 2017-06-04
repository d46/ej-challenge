import {Model} from "backbone-model";
import xwf from "../utils/json2xwf";
import rest from "rest";

class User extends Model {

	defaults() {
		return {
			username: '',
			password: ''
		}
	}

	toXwf() {
		let json = this.toJSON()
		return xwf(json)
	}

	fetch() {
		return new Promise(function (resolve, reject) {
			rest({
				method: "POST",
				path: "http://localhost:8080/login",
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				mixin: {
					withCredentials: true
				},
				entity: this.toXwf()
			}).then((response)=> resolve(response));
		}.bind(this))
	}

}

export default User;
