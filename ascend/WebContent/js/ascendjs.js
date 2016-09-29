(function() {
	'use strict';
	document.addEventListener("DOMContentLoaded", function() {
		var field = document.querySelector(".field");
<<<<<<< HEAD
		var textarea = document.querySelector(".textarea");
		var fieldjson = JSON
				.parse(document.querySelector(".jsonfield").innerText);
		var actorjson = JSON
				.parse(document.querySelector(".jsonactors").innerText);
=======
		var fieldjson = JSON
				.parse(document.querySelector(".fieldjson").innerText);
>>>>>>> refs/remotes/origin/master

		for (var i = 0; i < fieldjson.length; i++) {
			var tile = fieldjson[i];
<<<<<<< HEAD
			if(tile.occupied == false){
				field.innerHTML += tile.attribute;
			} else {
				for(var j=0; j<actorjson.length; j++){
					var actor = actorjson[j];
					if(actor.x == tile.x && actor.y == tile.y){
						field.innerHTML += actor.attribute;
					}
				}
			}
			//needs to be set to (max) tile.x
=======
			field.innerHTML += tile.attribute;
>>>>>>> refs/remotes/origin/master
			if (tile.x == 63) {
				field.innerHTML += "<br/>";
			}
<<<<<<< HEAD
=======

>>>>>>> refs/remotes/origin/master
		}
		
	});
})();