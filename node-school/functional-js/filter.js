function getShortMessages(messages) {
	return messages.filter(function(f) {
		return f.message.length < 50;
	}).map(function(s) {
		return s.message;
	})
}

module.exports = getShortMessages