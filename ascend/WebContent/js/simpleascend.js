var fieldhtml, gamejson, keyDirection, heroInfohtml, heroName;
var loaded = false;

(function() {
	'use strict';
	document.addEventListener("DOMContentLoaded", function() {
		document.querySelector(".nameField").focus();
		document.querySelector(".nameField").select();
	    $(".splash-arrow").click(submitName);
		
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
			if(loaded){
				console.log(keyDirection);
				updateField(keyDirection);
			}
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
	heroInfohtml.innerHTML = "Your HP: " + gamejson["heroHP"] + ". Mood: " + (gamejson["heroHP"] > 0 ? "Happy!" : "Dead.") + " Kill count: " + gamejson["enemiesKilled"] + ".";
}

function newGame(){
	var newGameReq = new XMLHttpRequest();
	newGameReq.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200) {
			gamejson = JSON.parse(this.responseText);
		}
	}
	newGameReq.open("GET", "rest/controller/newGame", false);
	newGameReq.send();
	
	representField(gamejson);
}

function submitName(){
	event.preventDefault();
	var inputtedName = document.querySelector(".nameField").value
	if(inputtedName == "Name..."){
		var defaultNames = ["Paulus de Plopkabouter", "Borbor", "Proculus de Pastelkleurige", "Jason and also the Argonauts", "Donwald Baardhelm", 
		                    "YoungCapital Professional", "Cornolas Raekh'var", "Jörgen", "Arno", "Hans", "Ben", "Theuderic Achtervoet"];
		heroName = defaultNames[Math.floor((Math.random() * defaultNames.length))];
	} else {
		heroName = inputtedName;
	}
	$(".splash").slideUp("800", function() {
        $(".wrapper").delay(100).animate({"opacity":"1.0"},800);
        loaded = true;
    });
	document.querySelector(".message").innerHTML = "Go forth, brave " + heroName + "!";
}