angular.module('routeEditor', ['ui.grid']).controller ('routeEditCtrl',routeEditCtrl);

function routeEditCtrl() {
    var vm = this;

    vm.myData = [
        {
            'first-name': 'Cox',
            friends: ['friend0'],
            address: {street: '301 Dove Ave', city: 'Laurel', zip: '39565'},
            getZip: function() {return this.address.zip;}
        }
    ];

    vm.gridOptions = {
        enableSorting: true,
        columnDefs: [
            { name:'firstName', field: 'first-name' , enableCellEdit: true},
            { name:'1stFriend', field: 'friends[0]' },
            { name:'city', field: 'address.city'},
            { name:'getZip', field: 'getZip()', enableCellEdit: false}
        ],
        data: '$ctrl.myData'
    };
}
