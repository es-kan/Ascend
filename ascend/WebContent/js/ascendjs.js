(function() {
	'use strict';
	document.addEventListener("DOMContentLoaded", function() {
		var field = document.querySelector(".field");
		var fieldjson, actorjson;

		var initfieldrequest = new XMLHttpRequest();
		var initactorsrequest = new XMLHttpRequest();
		initfieldrequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				fieldjson = JSON.parse(this.responseText);
			}
		};
		initactorsrequest.onreadystatechange = function(){
			if (this.readyState == 4 && this.status == 200) {
				actorjson = JSON.parse(this.responseText);
			}
		}

		initfieldrequest.open("GET", "rest/jsonmaker/game", false);
		initfieldrequest.send();
		initactorsrequest.open("GET", "rest/jsonmaker/actors", false);
		initactorsrequest.send();
		
		//display EVERYTHING
		for(var i=0; i<fieldjson.length; i++){
			var tile = fieldjson[i];
			field.innerHTML += tile.attribute;
			
			if (tile.x == 63) {
				field.innerHTML += "<br/>";
			}
		}
		
		
		/**
		// HERE BE DRAGONS

		for (var i = 0; i < fieldjson.length; i++) {
			var tile = fieldjson[i];
			if (tile.occupied == false) {
				field.innerHTML += tile.attribute;
			} else {
				for (var j = 0; j < actorjson.length; j++) {
					var actor = actorjson[j];
					if (actor.x == tile.x && actor.y == tile.y) {
						field.innerHTML += actor.attribute;
					}
				}
			}
			// needs to be set to (max) tile.x
			if (tile.x == 63) {
				field.innerHTML += "<br/>";
			}
		}
		*/
	});
	
	function initNewGame () {
		
	}
})();