import {Model} from "backbone-model";
import rest from "rest";
import xwf from "../utils/json2xwf"

class BadgeProgress extends Model {
	defaults() {
		return {
			action: ''
		}
	}
	toXwf() {
		let json = this.toJSON()
		return xwf(json)
	}

	record() {
		return new Promise(function (resolve,reject) {
			rest({
				method: "POST",
				path: "http://localhost:8080/reports/record",
				headers: {'Content-Type': 'application/x-www-form-urlencoded'},
				mixin: {
					withCredentials: true
				},
				entity: this.toXwf()
			}).then((response) => resolve(response));
		}.bind(this))
	}
}

export const badgeProgress = new BadgeProgress();
