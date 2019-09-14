(function () {
    'use strict';

    angular.module('BlurAdmin.pages.infoStation', ['infoStationApp'])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('infoStation', {
                url: '/infoStation',
                templateUrl: 'app/pages/infoStation/info-station.html',
                title: 'Station timetable',
                sidebarMeta: {
                    icon: 'ion-flag',
                    order: 800,
                    authRole: ['USER','ADMIN']
                },
            });
    }

})();