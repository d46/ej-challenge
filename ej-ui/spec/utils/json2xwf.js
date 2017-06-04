export default (json) => {
	let formBody = [];
	for (let property in json) {
		let encodedKey = encodeURIComponent(property);
		let encodedValue = encodeURIComponent(json[property]);
		formBody.push(encodedKey + "=" + encodedValue);
	}
	return formBody.join("&");
}
