angular.lowercase = function (string) {
    if (typeof string === 'string' || string instanceof String)
        return string.toLowerCase();
    else
        return string;
};