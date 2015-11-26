function checkUsersValid(goodUsers) {
  return function allUsersValid(submittedUsers) {
    return submittedUsers.every(function (submittedUsers) {
    	return goodUsers.some(function (goodUsers) {
    		return submittedUsers.id === goodUsers.id;
    	})
    })
  };
}

module.exports = checkUsersValid