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
                    security: ['$q','$rootScope', function($q,$rootScope){
                        if($rootScope.user === 'USER'){
                            return $q.reject("Not Authorized");
                        }
                    }]
                },
                title: 'Edit route',
                sidebarMeta: {
                    icon: 'ion-settings',
                    order: 800,
                    authRole: ['ADMIN']
                },
            });
    }

})();