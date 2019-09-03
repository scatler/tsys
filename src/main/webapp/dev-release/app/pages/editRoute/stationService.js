angular.module('routeEditor').factory('Station', ['$http', function ($http) {
    function Station(data) {
        if (data) {
            this.setData(data)
        }
        //что-то, что еще нужно для инициализации книги
    };
    var serv = "http://localhost:8080/stations/";
    Station.prototype = {
        setData: function (data) {
            angular.extend(this, data);
        },
        load: function (id) {
            var scope = this;
            $http.get(serv + id).success(function (data) {
                scope.setData(data);
            });
        },
        delete: function () {
            $http.delete(serv + bookId);
        },
        update: function () {
            $http.put(serv + bookId, this);
        }
    };
    return Station;
}]);

angular.module('routeEditor').factory('stationsManager', ['$http', '$q', 'Station', function($http, $q, Station) {
    var stationsManager = {
        _pool: {},
        _retrieveInstance: function(id, data) {
            var instance = this._pool[id];
            if (instance) {
                instance.setData(data);
            } else {
                instance = new Station(data);
                this._pool[id] = instance;
            }
            return instance;
        },
        _search: function(id) {
            return this._pool[id];
        },
        _load: function(id, deferred) {
            var scope = this;
            $http.get('http://localhost:8080/stations' + id)
                .success(function(data) {
                    var station = scope._retrieveInstance(data.id, data);
                    deferred.resolve(station);
                })
                .error(function() {
                    deferred.reject();
                });  },
        /*Публичные методы*/
        /* Получение  по идентификатору*/
        getBook: function(id) {
            var deferred = $q.defer();
            var station = this._search(id);
            if (station) {
                deferred.resolve(station);
            } else {
                this._load(id, deferred);
            }  return deferred.promise;
        },
        /* Получение списка  */
        loadAll: function() {
            var deferred = $q.defer();
            var scope = this;
            $http.get('http://localhost:8080/stations')
                .success(function(stationArray) {
                    var stations = [];
                    stationArray.forEach(function(data) {
                        var station = scope._retrieveInstance(data.id, data);
                        stations.push(station);
                    });
                    deferred.resolve(stations);
                })
                .error(function() {
                    deferred.reject();
                });
            return deferred.promise;
        },
        /* Редактирование */
        set: function(data) {
            var scope = this;
            var station = this._search(data.id);
            if (station) {
                station.setData(data);
            } else {
                station = scope._retrieveInstance(data);
            }  return station;
        },
    };
    return stationsManager;
}]);