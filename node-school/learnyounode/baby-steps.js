var numbers = process.argv.slice(2), 
	sum = numbers.reduce(function (curr, prev) {
	return +curr + +prev;
});

 console.log(sum);