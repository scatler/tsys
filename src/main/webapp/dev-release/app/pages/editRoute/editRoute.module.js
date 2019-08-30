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
                title: 'Edit route',
                sidebarMeta: {
                    order: 800,
                },
            });
    }

})();