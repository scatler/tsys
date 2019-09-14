(function () {
    'use strict';

    angular.module('BlurAdmin.pages.infoPass', ['infoPassApp'])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('infoPass', {
                url: '/infoPass',
                templateUrl: 'app/pages/infoPass/info-pass.html',
                title: 'List passengers',
                sidebarMeta: {
                    icon: 'ion-clipboard',
                    order: 800,
                    authRole: ['ADMIN']
                },
            });
    }

})();