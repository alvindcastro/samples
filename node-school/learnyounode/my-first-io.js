var fs = require('fs');

var txt = fs.readFileSync(process.argv[2]);

console.log(txt.toString().split('\n').length - 1);