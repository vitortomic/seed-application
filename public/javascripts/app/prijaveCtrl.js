app.controller('prijaveCtrl', function($scope, prijaveService, $mdDialog){
	
	prijaveService.listPrijave().then((prijave) => {
		$scope.prijave = prijave;
	});
	
	var ocene = [5,6,7,8,9,10];
	
	$scope.upisiOcenu = function(ev, prijava) {
	    // Appending dialog to document.body to cover sidenav in docs app
	    var confirm = $mdDialog.prompt()
	      .title('Unesite ocenu')
	      .targetEvent(ev)
	      .ok('Unesi')
	      .cancel('Odustani');

	    $mdDialog.show(confirm).then(function(result) {
	      if(ocene.some(ocena => ocena == result)){
	    	  prijava.ocena = result;
	    	  apdejtujPrijavu(prijava);
	      }
	      else{
	    	  alert("Dozvoljene vrednosti su 5,6,7,8,9,10");
	      }
	    }, function() {
	    	
	    });
	  };
	  
	  $scope.obrisiPrijavu = function(ev, prijava) {
		    // Appending dialog to document.body to cover sidenav in docs app
		    var confirm = $mdDialog.confirm()
		          .title('Da li ste sigurni da zelite da obrisete prijavu?')
		          .ariaLabel('Lucky day')
		          .targetEvent(ev)
		          .ok('Obrisi')
		          .cancel('Odustani');

		    $mdDialog.show(confirm).then(function() {
		    	obrisiPrijavu(prijava);
		    }, function() {
		     
		    });
		  };
	 
	  var apdejtujPrijavu = function(prijava){
		  prijaveService.updatePrijavu(prijava);
	  }
	  
	  var obrisiPrijavu = function(prijava){
		  prijaveService.deletePrijavu(prijava).then(function(response){
			  $scope.prijave = $scope.prijave.filter(function(element){
				  return !angular.equals(prijava, element);
			  })
		  });
	  }
});