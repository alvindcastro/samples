var filterDir = require('./filtered-inc');

var dirPath = process.argv[2], 
	ext = process.argv[3];

filterDir(dirPath, ext, function(err, list) {
	if (err) {
		console.log('Error!' + dirPath);
		return err;
	}

	list.forEach(function(fileName) {
		console.log(fileName);
	});
});