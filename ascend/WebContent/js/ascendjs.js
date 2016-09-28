(function() {
	'use strict';
	document.addEventListener("DOMContentLoaded", function() {
		var field = document.querySelector(".field");
		var fieldjson = JSON
				.parse(document.querySelector(".fieldjson").innerText);

		for (var i = 0; i < fieldjson.length; i++) {
			var tile = fieldjson[i];
			field.innerHTML += tile.attribute;
			if (tile.x == 63) {
				field.innerHTML += "<br/>";
			}

		}

	});
})();