var fs = require('fs');
var path = require('path');

fs.readdir( process.argv[2], function (err, files) { 
	if (!err) {
		files.forEach(function (fileName) {
			if (path.extname(fileName) === '.' + process.argv[3]) {
				console.log(fileName);
			}
		}) 
	} else {
		throw err;
	}
});