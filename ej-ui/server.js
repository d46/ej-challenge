const path = require('path');
const express = require('express');
const internalIp = require('internal-ip');
const CFonts = require('cfonts');
const config = require('./webpack/webpack.config.dev');
const webpack = require('webpack');

const app = express();
const compiler = webpack(config);
const port = 8081;
const ip = internalIp.v4();

webpackDevMiddlewareInstance = require('webpack-dev-middleware')(compiler, {
	publicPath: config.output.publicPath,
})
app.use(webpackDevMiddlewareInstance);
app.use(require('webpack-hot-middleware')(compiler));

sign = () => {
	setTimeout(function(){
		CFonts.say('Emlakjet', {
			font: 'block',        //define the font face
			align: 'left',        //define text alignment
			colors: ['green','white','green'],    //define all colors
			background: 'black',  //define the background color
			letterSpacing: 1,     //define letter spacing
			lineHeight: 0,        //define the line height
			space: true,          //define if the output text should have empty lines on top and on the bottom
			maxLength: '0'        //define how many character can be on one line
		});
		console.log(' --------------------------------------');
		console.log(' ');
		console.log(`    Local: http://0.0.0.0:${port}`);
	},10000)
}
app.get('*', (req, res) => {
	res.sendFile(path.join(__dirname, './spec/index.html'));
});

webpackDevMiddlewareInstance.waitUntilValid(function(){
	sign()
});

app.listen(port, (err) => {
	console.log(err);
});
