var fieldhtml, gamejson, keyDirection, heroInfohtml;

(function() {
	'use strict';
	document.addEventListener("DOMContentLoaded", function() {
		fieldhtml = document.querySelector(".field");
		heroInfohtml = document.querySelector(".heroInfo");
		initialize();
		document.addEventListener("keydown", function myKey(){
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
			case 32:
				keyDirection = "ACT";
			}
			updateField(keyDirection);
		})
	});
})();

function initialize(){
	var initGameRequest = new XMLHttpRequest();
	initGameRequest.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			gamejson = JSON.parse(this.responseText);
		}
	}
	initGameRequest.open("GET", "rest/controller/initSimple", false);
	initGameRequest.send();
	
	representField(gamejson);
}

function updateField(keyDirection){
	var updateReq = new XMLHttpRequest();
	updateReq.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200) {
			gamejson = JSON.parse(this.responseText);
		}
	}
	updateReq.open("POST", "rest/controller/moveSimple", false);
	updateReq.send(keyDirection);
	
	representField(gamejson);
}

function representField(gamejson){
	fieldhtml.innerHTML = "";
	for(var i=0; i<gamejson["field"].length; i++){
		fieldhtml.innerHTML += gamejson["field"][i] + "<br/>";
	}
	heroInfohtml.innerHTML = "Your HP: " + gamejson["heroHP"] + ". Mood: " + (gamejson["heroHP"] > 0 ? "Happy!" : "Dead.");
}