//This script is a true masterpiece of javascript artisanship but it does way too many things to actually be efficient at them.
//It's basically just here to serve as an example of how not to seamlessly integrate a front- and backend.

//rip ascendjs.js, you were a true pain in the behind.

var fieldhtml, fieldjson;
var fieldWidth = 63;

(function() {
	'use strict';
	document.addEventListener("DOMContentLoaded", function() {
		var actorjson, alljson;
		var keyDirection;
		fieldhtml = document.querySelector(".field");
		var initGameRequest = new XMLHttpRequest();
		initGameRequest.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				alljson = JSON.parse(this.responseText);
			}
		}

		initGameRequest.open("GET", "rest/controller/initialize", false);
		initGameRequest.send();

		fieldjson = alljson["tiles"];
		actorjson = alljson["actors"];

		representField(fieldjson, actorjson);

		document.addEventListener("keydown", function myKey() {
			switch (event.keyCode) {
			case 38:
				keyDirection = "NORTH";
				break;
			case 40:
				keyDirection = "SOUTH";
				break;
			case 37:
				keyDirection = "WEST";
				break;
			case 39:
				keyDirection = "EAST";
				break;
			}

			updateField(keyDirection);
		})

	});

})();

function updateField(keyDirection) {
	// send request here!
	var response;
	var test = document.querySelector(".test");
	var moveReq = new XMLHttpRequest();
	moveReq.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			response = JSON.parse(this.responseText);
		}
	};
	moveReq.open("POST", "rest/controller/move", false);
	moveReq.send(keyDirection);

	fieldhtml.innerHTML = "";

	var newPositions = response["newActorPositions"]
	representField(fieldjson, newPositions);

}


//redraw entire field. Works, but slow.
function representField(fieldjson, actorjson) {
	for (var i = 0; i < fieldjson.length; i++) {
		var tile = fieldjson[i];
		if (tile.attribute == '#') {
			fieldhtml.innerHTML += tile.attribute;
		} else {
			var foundActor = false;
			for (var j = 0; j < actorjson.length; j++) {
				var actor = actorjson[j];
				if (actor.x == tile.x && actor.y == tile.y) {
					fieldhtml.innerHTML += actor.attribute;
					foundActor = true;
				}
			}
			if (!foundActor) {
				fieldhtml.innerHTML += tile.attribute;
			}
		}
		// needs to be set to (max) tile.x which i really should send with
		// the initgame json i guess?
		if (tile.x == fieldWidth) {
			fieldhtml.innerHTML += "<br/>";
		}
	}
}