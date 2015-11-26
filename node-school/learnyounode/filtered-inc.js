var fs = require('fs');
var path = require('path');

module.exports = function (dirPath, ext, callback) {
	var filteredFiles = [];

	fs.readdir(dirPath, function(err, list) {
		if (err) {
			return callback(err);
		}

		list.forEach(function (file) {
			if (path.extname(file) === '.' + ext) {
				filteredFiles.push(file);
			}
		});

		callback(null, filteredFiles)
	});
};