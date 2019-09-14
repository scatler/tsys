(function () {
    'use strict';

    angular.module('BlurAdmin.pages.buyTickets', ['buyTicketsApp'])
        .config(routeConfig);

    /** @ngInject */
    function routeConfig($stateProvider) {
        $stateProvider
            .state('buyTickets', {
                url: '/buyTickets',
                templateUrl: 'app/pages/buyTickets/but-tickets.html',
                title: 'Tickets & Trains',

                sidebarMeta: {
                    icon: 'ion-star',
                    order: 800,
                    authRole: ['USER']
                },
            });
    }

})();