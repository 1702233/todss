(function init(){
	console.log("starting");
	
	fetch("gamechane/cardset")
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    })
	
})();


function soortselectie(soort) {
	console.log("geselecteerde minigame soort = " + soort);
	if (soort == "memory") {
		memoryselected();
	} else if (soort == "3rij") {
		console.log("3rij")
	} else if (soort =="4rij") {
		console.log("4rij")
	}
	
}

function memoryselected() {
	console.log("memoryselected functie");
	document.getElementById('minigamedefine').innerHTML = '' +
	'<div class="input-group mb-3">' +
		'<form> \n' +
		'<div class="input-group-prepend">' +
			'<span class="input-group-text" id="basic-addon">open of dicht</span>' +
			'<select id="kaartsidestart">' +
				'<option disabled selected value> -- selecteer hoe een game begint -- </option>' +
				'<option value="open">kaarten open</option>' +
				'<option value="dicht">kaarten dicht</option>' +
			'</select>' +
		'</form>' +
		'</div>' +
	'</div>' +
	'<div class="input-group mb-3">' +
		'<div class="input-group-prepend">' +
			'<span class="input-group-text" id="basic-addon">hoeveel sets van 2</span>' +
	'<input type="number" name="setaantal" id="setaantal">' +
	'</div>' +
	'</div>' +
	'<input type="submit" value="Submit" onclick="memorydefined()">';
}

function memorydefined() {
	var setamountobject = document.getElementById("setaantal");
	var kaartsidestart = document.getElementById("kaartsidestart").value;
	var sethtml;
	console.log(sethtml);
	
	for(var i = 0;  i < setamountobject.value;  i++) {
		if (sethtml == null) {
			sethtml = 'Set 1 :<div class ="row"><div class="col" id="kaartsetslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col" id="kaartsetslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
		} else {
		sethtml = sethtml + 'Set ' + (i + 1) +' :<div class ="row"><div class="col" id="kaartsetslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col" id="kaartsetslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
		};
	};
	
	var statischeplaatjes = '<img id="drag1" src="img/apple.jpg" draggable="true" ondragstart="drag(event)" width="100" height="150"> <img id="drag2" src="img/bmw.jpg" draggable="true" ondragstart="drag(event)" width="100" height="150">  <img id="drag3" src="img/mcdonalds.jpg" draggable="true" ondragstart="drag(event)" width="100" height="150">'
	var statischeplaatjes2 = '<img id="drag4" src="img/apple.jpg" draggable="true" ondragstart="drag(event)" width="100" height="150">  <img id="drag5" src="img/bmw.jpg" draggable="true" ondragstart="drag(event)" width="100" height="150">  <img id="drag6" src="img/mcdonalds.jpg" draggable="true" ondragstart="drag(event)" width="100" height="150">'
	
	var submitbutton = '<div><input type="submit" value="Submit" onclick="maakminigameaan()"></div>'
	document.getElementById('minigamespecifics').innerHTML = statischeplaatjes + statischeplaatjes2 + sethtml + submitbutton;
}

function maakminigameaan(sets, kaartsidestart) {
	console.log("minigame aangemaakt met : ");
}

function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}