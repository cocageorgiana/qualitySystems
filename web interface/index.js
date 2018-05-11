#!/usr/bin/env node

var express = require("express"),
	app = express();

//app.use(express.static('js'));
app.use('/vendor', express.static('vendor'));
app.use('/js', express.static('js'));

app.get("/", function (req, res) {
	res.sendFile(__dirname + "/index.html");
});


app.listen(3000);
