/**
 * @author v.lugovsky
 * created on 16.12.2015
 */
(function () {
  'use strict';

  angular.module('BlurAdmin.pages', [
    'ui.router',
/*    'BlurAdmin.pages.dashboard',
    'BlurAdmin.pages.ui',
    'BlurAdmin.pages.components',
    'BlurAdmin.pages.form',
    'BlurAdmin.pages.tables',
    'BlurAdmin.pages.charts',
    'BlurAdmin.pages.maps',
    'BlurAdmin.pages.profile',*/
    'BlurAdmin.pages.editRoute',
    'BlurAdmin.pages.buyTickets',
  ])
      .config(routeConfig)
      .run(run);

  /** @ngInject */
  function routeConfig($urlRouterProvider, baSidebarServiceProvider) {
    $urlRouterProvider.otherwise('/dashboard');

    baSidebarServiceProvider.addStaticItem({
      title: 'Pages',
      icon: 'ion-document',
      subMenu: [{
        title: 'Sign In',
        fixedHref: 'auth.html',
        blank: true
      }, {
        title: 'Sign Up',
        fixedHref: 'reg.html',
        blank: true
      }, {
        title: 'User Profile',
        stateRef: 'profile'
      }, {
        title: '404 Page',
        fixedHref: '404.html',
        blank: true
      }]
    });
    baSidebarServiceProvider.addStaticItem({
      title: 'Menu Level 1',
      icon: 'ion-ios-more',
      subMenu: [{
        title: 'Menu Level 1.1',
        disabled: true
      }, {
        title: 'Menu Level 1.2',
        subMenu: [{
          title: 'Menu Level 1.2.1',
          disabled: true
        }]
      }]
    });
  }

    /** @ngInject */
    function run($rootScope, $location, $http, $window) {
        var userData = $window.sessionStorage.getItem('userData');
        if (userData) {
            $http.defaults.headers.common['Authorization']
                = 'Basic ' + JSON.parse(userData).authData;
        }

        $rootScope.isAdmin = false;

        $http({
            url: 'http://localhost:8080/user',
            method: "GET"
        }).then(function (response) {
            $rootScope.isAdmin = response.data.authorities[0].authority === "ROLE_ADMIN";

        })
/*        $rootScope
            .$on('$locationChangeStart', function (event, next, current) {
                var restrictedPage
                    = $.inArray($location.path(), ['/login','/editRoute']) === -1;
                var isAdmin;
                $http({
                  url: 'http://localhost:8080/user',
                  method: "GET"
                }).then(function (response) {
                   isAdmin = response.data.authorities[0].authority === "ROLE_ADMIN";
                }, function (error) {
                  console.log(error);
                });

                if (!isAdmin && restrictedPage) {
                    $location.path('/login');
                }
            });*/
    }


})();
