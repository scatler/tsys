var buyTicketsApp = angular.module('buyTicketsApp', ['ui.bootstrap', 'toastr', 'ngAnimate','ngTouch', 'ui.select', 'ngSanitize', 'ui.grid', 'ui.grid.edit', 'ui.grid.rowEdit', 'ui.grid.cellNav', 'ui.grid.resizeColumns', 'ui.grid.selection', 'routeEditor']);
buyTicketsApp.controller('AccordionDemoCtrl', ['$scope', '$http', 'stationsManager','toastr','$uibModal', '$log','$document',function ($scope, $http, stationsManager,toastr, $uibModal, $log, $document) {
    var vm = this;
    vm.oneAtATime = true;
    vm.groups = [
        {
            title: 'Dynamic Group Header - 1',
            content: 'Dynamic Group Body - 1'
        },
        {
            title: 'Dynamic Group Header - 2',
            content: 'Dynamic Group Body - 2'
        }
    ];
    vm.items = ['Item 1', 'Item 2', 'Item 3'];
    vm.addItem = function () {
        var newItemNo = $scope.items.length + 1;
        $scope.items.push('Item ' + newItemNo);
    };
    vm.status = {
        isCustomHeaderOpen: false,
        isFirstOpen: true,
        isFirstDisabled: false
    };
    vm.findTrains = {};
    vm.stationFrom = {};
    vm.stationTo = {};
    var buttonTRD =
        vm.dt = {};
    vm.df = {};
    vm.findTrains = {
        columnDefs: [
/*            {name: 'trainId', width: '10%'},
            {name: 'trainRouteDay', width: '10%'},*/
            {name: 'Info1', width: '10%',
                cellTemplate: '<span> ' +
                    '{{row.entity.trainName}}<br> ' +
                    'TrainId:{{row.entity.trainId}}<br> ' +
                    'TRD:{{row.entity.trainRouteDay}}<br> ' +
                    '#{{row.entity.routeId}} </span>'},
/*            {name: 'trainName', width: '10%'},
            {name: 'routeId', width: '10%'},*/
            {name: 'From', width: '10%',
                cellTemplate: '<p>From</p>'},
            {name: 'Info2', width: '20%',
                cellTemplate: '<span> ' +
                    '{{row.entity.arrivalTimeToStation1}}<br> ' +
                    '{{row.entity.station1Name}} </span>'},
            {name: 'To', width: '10%',
                cellTemplate: '<p>To</p>'},
            {name: 'Info3', width: '20%',
                cellTemplate: '<span> ' +
                    '{{row.entity.arrivalTimeToStation2}}<br> ' +
                    '{{row.entity.station2Name}} </span>'},
            //{name: 'station1Id', width: '10%'},
/*            {name: 'station1Name', width: '10%'},
            //{name: 'dayOfStart', width: '10%'},
            {name: 'arrivalTimeToStation1', width: '20%'},
            {name: 'arrivalTimeToStation2', width: '20%'},*/
            //{name: 'station2id', width: '10%'},
            //{name: 'station2name', width: '10%'},
            {name: 'Info4', width: '10%',
                cellTemplate: '<span> ' +
                    '<b>Free seats:{{row.entity.freeTickets}}</b><br>' +
                    'Total seats: {{row.entity.totalSeats}}</span>'},
/*            {name: 'totalSeats', width: '10%'},
            {name: 'freeTickets', width: '10%'},*/
            {
                name: 'ticket',
                cellTemplate: '<button ' +
                    'class="btn btn-sm btn-info" ' +
                    'ng-click="grid.appScope.$ctrl.enterPesonalInfo(row.entity.trainRouteDay)" ' +
                    'type="button">Buy ticket ' +
                    '</button>'
            }
        ],
        data: []
    };

    vm.findTrains.rowHeight = 80;
    vm.findTrains.showHeader = false;

    function loadData(dataPath, table, dateFrom, dateTo, stFrom, stTo) {
        $http.get('http://localhost:8080/' + dataPath,
            {params: {stationFrom: stFrom, stationTo: stTo, dayFrom: dateFrom, dayTo:dateTo}})
            .then(function (response) {
                table.data = response.data;
                if (response) vm.panels.trainOpen = true;
            });
    }

    vm.findTrains.onRegisterApi = function (gridApi) {
        vm.gridApi = gridApi;
    };
    /*My function*/
    vm.searchForTrains = function (dateFrom, dateTo, stFrom, stTo) {
        vm.panels.ticketOpen = false;
        vm.panels.trainOpen = false;
        vm.panels.infoOpen = false;
        vm.findTrains.data = [];
        loadData('availableTrains', vm.findTrains,
            formatDate(vm.df),
            formatDate(vm.dt),
            vm.stationFrom.id,
            vm.stationTo.id);
        //TODO open accordion
    };

    function formatDate(date) {
        var day = date.getDate();
        var monthIndex = date.getMonth() + 1;
        var year = date.getFullYear();
        return monthIndex + '/' + day + '/' + year;
    }

    function formatDateBirthday(date) {
        var day = date.getDate();
        var monthIndex = date.getMonth() + 1;
        var year = date.getFullYear();
        return day + '/' + monthIndex + '/' + year;
    }

    /*Panels*/
    vm.panels = {
        trainOpen: false,
        ticketOpen: false,
        infoOpen: false,
        findTrains: true
    };
    /*Dropdown menu*/
    vm.stationList = [];
    stationsManager.loadAll().then(function (data) {
        vm.stationList = data;
    });
    /*Date picker zone*/
    vm.today = function () {
        vm.dt = new Date();
        vm.df = new Date();
    };
    vm.today();
    vm.clear = function () {
        vm.dt = null;
    };
    /*    vm.inlineOptions = {
            customClass: getDayClass,
            minDate: new Date(),
            showWeeks: true
        };*/
    //****
    vm.dateOptions = {
        //dateDisabled: disabled,
        formatYear: 'yy',
        maxDate: new Date(2020, 5, 22),
        minDate: new Date(),
        startingDay: 1
    };
    // Disable weekend selection
    /*
        function disabled(data) {
            var date = data.date,
                mode = data.mode;
            return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
        }
    */
    /*    vm.toggleMin = function() {
            vm.inlineOptions.minDate = vm.inlineOptions.minDate ? null : new Date();
            vm.dateOptions.minDate = vm.inlineOptions.minDate;
        };*/
    /*    vm.toggleMin();*/
    //****
    vm.open1 = function () {
        vm.popup1.opened = true;
    };
    vm.open2 = function () {
        vm.popup2.opened = true;
    };
    vm.open3 = function () {
        vm.popup3.opened = true;
    };
    vm.setDate = function (year, month, day) {
        vm.dt = new Date(year, month, day);
    };
    vm.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
    vm.format = vm.formats[1];
    vm.altInputFormats = ['M!/d!/yyyy'];
    vm.popup1 = {
        opened: false
    };
    vm.popup2 = {
        opened: false
    };
    vm.popup3 = {
        opened: false
    }
    var tomorrow = new Date();
    tomorrow.setDate(tomorrow.getDate() + 1);
    var afterTomorrow = new Date();
    afterTomorrow.setDate(tomorrow.getDate() + 1);
    vm.events = [
        {
            date: tomorrow,
            status: 'full'
        },
        {
            date: afterTomorrow,
            status: 'partially'
        }
    ];

    function getDayClass(data) {
        var date = data.date,
            mode = data.mode;
        if (mode === 'day') {
            var dayToCheck = new Date(date).setHours(0, 0, 0, 0);
            for (var i = 0; i < vm.events.length; i++) {
                var currentDay = new Date(vm.events[i].date).setHours(0, 0, 0, 0);
                if (dayToCheck === currentDay) {
                    return vm.events[i].status;
                }
            }
        }
        return '';
    }

    /*Date picker zone*/
    /*Ticket*/
    vm.birthday = new Date();
    vm.userForm = {
        name: '',
        surname: '',
        birthday: '',
        station1Id: vm.stationTo,
        station2Id: vm.stationFrom,
        trd: ''
    };
/*    vm.testForm = {
        //id: 1,
        name: 'JS',
        surname: 'Spring',
        birthday: '2019/09/06',
        station1Id: 1011,
        station2Id: 1013,
        trd: 70
    };*/
/*    vm.ticketId = {};*/

    vm.enterPesonalInfo = function(trd) {
        vm.panels.ticketOpen = true;
        vm.userForm.trd = trd;
    };

    vm.buyTicket = function () {

        vm.userForm.station1Id = vm.stationFrom.id;
        vm.userForm.station2Id = vm.stationTo.id;
        vm.userForm.birthday = formatDateBirthday(vm.userForm.birthday);

        $http.post('http://localhost:8080/saveTicket', vm.userForm).success(function (response) {
            if (response) {
               /* vm.ticketId = response.data;*/
                vm.ticketId = response;
                vm.panels.infoOpen = true;
                vm.panels.trainOpen = false;
                vm.panels.ticketOpen = false;
                vm.showSuccessMsg('You bought a ticket');
                //vm.openModal();
            } else {

            }
        })
            .catch(function (data, status, headers, config) {
                toastr.warning('Something wrong', data.data.error, {})
            })
    }
    //-----------Notification--------------------------
    vm.showSuccessMsg = function (msg) {
        toastr.success(msg);
    };

    function openModal (msg) {
        vm.items = msg;
        vm.open();
    }


    //----------------------Modal
    vm.items = 'Hello!';

    vm.animationsEnabled = true;

    vm.open = function (size, parentSelector) {
        var parentElem = parentSelector ?
            angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
        var modalInstance = $uibModal.open({
            animation: vm.animationsEnabled,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'myModalContent.html',
            controller: 'AccordionDemoCtrl',
            controllerAs: '$ctrl',
            size: size,
            appendTo: parentElem,
            resolve: {
                items: function () {
                    return vm.items;
                }
            }
        });


        modalInstance.result.then(function (selectedItem) {
            vm.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };



    vm.openComponentModal = function () {
        var modalInstance = $uibModal.open({
            animation: vm.animationsEnabled,
            component: 'modalComponent',
            resolve: {
                items: function () {
                    return vm.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            vm.selected = selectedItem;
        }, function () {
            $log.info('modal-component dismissed at: ' + new Date());
        });
    };

    vm.openMultipleModals = function () {
        $uibModal.open({
            animation: vm.animationsEnabled,
            ariaLabelledBy: 'modal-title-bottom',
            ariaDescribedBy: 'modal-body-bottom',
            templateUrl: 'stackedModal.html',
            size: 'sm',
            controller: function($scope) {
                $scope.name = 'bottom';
            }
        });

        $uibModal.open({
            animation: vm.animationsEnabled,
            ariaLabelledBy: 'modal-title-top',
            ariaDescribedBy: 'modal-body-top',
            templateUrl: 'stackedModal.html',
            size: 'sm',
            controller: function($scope) {
                $scope.name = 'top';
            }
        });
    };

    vm.toggleAnimation = function () {
        vm.animationsEnabled = !vm.animationsEnabled;
    };

}]);

