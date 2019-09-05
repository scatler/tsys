function AbstractEntity($http) {
    var serv = "";
    this.setData = function (data) {
        angular.extend(this, data);
    },
        this.load = function (id) {
            var scope = this;
            $http.get(serv + id).success(function (data) {
                scope.setData(data);
            });
        },
        this.delete = function () {
            $http.delete(serv + id);
        },
        this.update = function () {
            $http.put(serv + id, this);
        }
    return this;
}

function AbstractManager(Entity, $http, $q) {
    var manager = {
        serv: "test",
        _pool: {},
        _retrieveInstance: function (id, data) {
            var instance = this._pool[id];
            if (instance) {
                instance.setData(data);
            } else {
                instance = new Entity(data); // problem 1
                this._pool[id] = instance;
            }
            return instance;
        },
        _search: function (id) {
            return this._pool[id];
        },
        _load: function (id, deferred) {
            var scope = this;
            var serv;
            $http.get(serv + id)
                .success(function (data) {
                    var station = scope._retrieveInstance(data.id, data);
                    deferred.resolve(station);
                })
                .error(function () {
                    deferred.reject();
                });
        },
        /*Публичные методы*/
        /* Получение  по идентификатору*/
        get: function (id) {
            var deferred = $q.defer();
            var instance = this._search(id);
            if (instance) {
                deferred.resolve(instance);
            } else {
                this._load(id, deferred);
            }
            return deferred.promise;
        },
        /* Получение списка  */
        loadAll: function () {
            var deferred = $q.defer();
            var scope = this;
            $http.get(this.serv)
                .success(function (entArray) {
                    var array = [];
                    entArray.forEach(function (data) {
                        var instance = scope._retrieveInstance(data.id, data);
                        array.push(instance);
                    });
                    deferred.resolve(array);
                })
                .error(function () {
                    deferred.reject();
                });
            return deferred.promise;
        },
        /* Редактирование */
        set: function (data) {
            var scope = this;
            var ent = this._search(data.id);
            if (ent) {
                ent.setData(data);
            } else {
                ent = scope._retrieveInstance(data);
            }
            return ent;
        }
    };
    return manager;
}

function RouteManager(AbstractManager) {
    angular.extend(RouteManager.prototype, AbstractManager);
    this.serv = "http://localhost:8080/routes/";
}

angular.module('routeEditor')
    .service('AbstractEntity', ['$http', AbstractEntity])
    .factory('Route', ['AbstractEntity', function (AbstractEntity) {
        angular.extend(Route.prototype, AbstractEntity);
        this.serv = "http://localhost:8080/routes/";
        function Route(data) {
            if (data) {
                this.setData(data)
            }
        };
        return Route
    }]);
angular.module('routeEditor')
    .service('AbstractManager', ['Route', '$http', '$q', AbstractManager])
    .service('RouteManager', ['AbstractManager', RouteManager]);
