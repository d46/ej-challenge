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

app.use(require('webpack-dev-middleware')(compiler, {
	publicPath: config.output.publicPath,
}));
app.use(require('webpack-hot-middleware')(compiler));

app.get('*', (req, res) => {
	res.sendFile(path.join(__dirname, './spec/index.html'));
});

app.listen(port, (err) => {
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
	console.log(` External: http://${ip}:${port}`);
});
