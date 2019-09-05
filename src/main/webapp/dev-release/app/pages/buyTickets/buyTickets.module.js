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
                    order: 800,
                },
            });
    }

})();