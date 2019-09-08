(function () {
    'use strict';

    angular.module('BlurAdmin.pages.editRoute', ['routeEditor'])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('editRoute', {
                url: '/editRoute',
                templateUrl: 'app/pages/editRoute/edit-route.html',
                resolve: {
                    security: ['$q', '$http','$timeout',function($q, $http, $timeout){
                        var deferred = $q.defer();
                        var isAdmin;
                        $http({
                            url: 'http://localhost:8080/user',
                            method: "GET"
                        }).then(function (response) {
                            isAdmin = response.data.authorities[0].authority === "ROLE_ADMIN";
                            if (isAdmin) {
                                $timeout (function () {
                                    deferred.resolve();
                                }, 500);
                                //deferred.resolve();
                            }
                            else {
                                deferred.reject("Not Authorized");
                            }

                        }, function (error) {
                            console.log(error);
                        });
                        return deferred;
                    }]
                },
                title: 'Edit route',
                sidebarMeta: {
                    order: 800,
                },
            });
    }

})();