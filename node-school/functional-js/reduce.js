function countWords(inputWords) {
	return inputWords.reduce(function (inputWordsTimes, word) {
		(++inputWordsTimes[word]) || (inputWordsTimes[word] = 1)
		return inputWordsTimes;
	}, {})
}

module.exports = countWords