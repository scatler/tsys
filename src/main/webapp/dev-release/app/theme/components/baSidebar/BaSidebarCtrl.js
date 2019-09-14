/**
 * @author v.lugovksy
 * created on 16.12.2015
 */
(function () {
    'use strict';
    angular.module('BlurAdmin.theme.components')
        .controller('BaSidebarCtrl', BaSidebarCtrl);

    /** @ngInject */
    function BaSidebarCtrl($scope, baSidebarService, $rootScope) {
        /*    Auth.getCurrentUser(function(user){
              $scope.menuItems = baSidebarService.getAuthorizedMenuItems(user);
              $scope.defaultSidebarState = $scope.menuItems[0].stateRef;
            });*/
        $scope.menuItems = baSidebarService.getAuthorizedMenuItems($rootScope.user);
        $scope.defaultSidebarState = $scope.menuItems[0].stateRef;

        $scope.hoverItem = function ($event) {
            $scope.showHoverElem = true;
            $scope.hoverElemHeight = $event.currentTarget.clientHeight;
            var menuTopValue = 66;
            $scope.hoverElemTop = $event.currentTarget.getBoundingClientRect().top - menuTopValue;
        };

        $scope.$on('$stateChangeSuccess', function () {
            $scope.menuItems = baSidebarService.getAuthorizedMenuItems($rootScope.user);
            if (baSidebarService.canSidebarBeHidden()) {
                baSidebarService.setMenuCollapsed(true);
            }
        });
    }
})();