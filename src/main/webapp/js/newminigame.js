var cardsetfetch;
var selectedcardset;
var aangemaakteminigameid;
var jsonSets;

(function init(){
	console.log("fetching all cardsets");
//	document.getElementById("minigamebasics").style.display = "block";
//	document.getElementById("minigameselection").style.display = "none";
//	document.getElementById("minigamespecifics").style.display = "none";
//	document.getElementById("summaryform").style.display = "none";
	fetch("gamechane/cardset/"+sessionStorage.getItem('docent'))
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    	cardsetfetch = myJson;
    	fillcardsetdropdown(myJson);
    })
	
})();

function minigamebasisinformatie() {
//	document.getElementById("minigamebasics").style.display = "none";
//	document.getElementById("minigameselection").style.display = "block";
//	document.getElementById("minigamespecifics").style.display = "none";
//	document.getElementById("summaryform").style.display = "none";
}

function minigameinformatie() {
	var slot1;
	var slot2;
	var aantalsets = document.getElementById("setaantal").value;
	console.log("aantalsets : " + aantalsets)
	
	//maak een jsonobject aan met alle sets met kaartIds erin om op te slaan
	JsonObj = []
	for(var i = 0;  i < aantalsets;  i++) {
		var slot1 = document.getElementById("kaartsetslotdiv"+i+"1");	
		var slot2 = document.getElementById("kaartsetslotdiv"+i+"2");
		
		item = {};
		item["set"+i] = slot1.childNodes[0].id.split("_").pop() + " " + slot2.childNodes[0].id.split("_").pop();
		JsonObj.push(item);
		
	}
	
	console.log(JsonObj);
	jsonSets = JsonObj;
	
	var titel = document.getElementById("titelinput");
	var omschrijving = document.getElementById("omschrijvinginput");
	var speltype = document.getElementById("soortselection");
	var kaartsidestart = document.getElementById("kaartsidestart");
	var opendicht = kaartsidestart.options[kaartsidestart.selectedIndex].value;
	var leraar = sessionStorage.getItem('docent');
	var cardset = document.getElementById("cardsetdropdown")
	
	document.getElementById("titel").value = titel.value;
	document.getElementById("speltype").value = speltype.value;
	document.getElementById("cardopened").value = $("#kaartsidestart :selected").val();;
	document.getElementById("omschrijving").value = omschrijving.value;
	document.getElementById("teachernaam").value = leraar;
	document.getElementById("cardsetid").value = selectedcardset;
		
}

function fillcardsetdropdown(myJson) {
	var select = document.getElementById("cardsetdropdown"); 
	for(var i = 0;  i < myJson.length;  i++) {
		var opt = myJson[i];
	    var el = document.createElement("option");
	    el.textContent = opt.name + " by " + opt.teacher.username;
	    el.value = i;
	    select.appendChild(el);
	}
}	

function cardsetselectie(cardset) {
	var cardsetimages = document.getElementById("cardsetimages");
	selectedcardset = cardsetfetch[cardset].id;
	console.log("kaartsetgeselecteerd id = " + selectedcardset);
	for(var i = 0;  i < cardsetfetch[cardset].allCards.length;  i++) {
		//cardsetimages.innerHTML += '<div class="col-sm">' + cardsetfetch[cardset].allCards[i].frontside.picture.url + "</div>";
		//cardsetimages.innerHTML += '<img id="drag_' + 1 + '" src="' + cardsetfetch[cardset].allCards[i].frontside.picture.url + '" draggable="true" ondragstart="drag(event)" width="100" height="150">';
		cardsetimages.innerHTML += '<div class="col" id="kaartsetloadinslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"><img id="drag_' + i + '" src="' + cardsetfetch[cardset].allCards[i].frontside.picture.url + '" draggable="true" ondragstart="drag(event)" width="150" height="150"></div>'
	}
	console.log(cardsetfetch[cardset].allCards)
}


function soortselectie(soort) {
	console.log("soortselectie() gaat af met soort = " + soort);
	// functie die een specifieke minigame selected aanroept op basis van de gebruiker's keuze
	if (soort == "memory") {
		memoryselected();
		console.log("memoryselected")
	} else if (soort == "3rij") {
		console.log("3rijselected")
	} else if (soort =="4rij") {
		console.log("4rijselected")
	}
	
}

function memoryselected() {
	console.log("memoryselected() functie");
	// functie die wordt uitgevoerd als er een memory type game word geselecteerd.
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
	console.log("memorydefined() functie")
//	document.getElementById("minigamebasics").style.display = "none";
//	document.getElementById("minigameselection").style.display = "block";
//	document.getElementById("minigamespecifics").style.display = "block";
//	document.getElementById("summaryform").style.display = "none";
	
	var setamountobject = document.getElementById("setaantal");
	var kaartsidestart = document.getElementById("kaartsidestart").value;
	var sethtml;
	console.log(sethtml);
	
	for(var i = 0;  i < setamountobject.value;  i++) {
		if (sethtml == null) {
			sethtml = 'Set 1 :<div class ="row"><div class="col kaartsetslot" id="kaartsetslotdiv01" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col kaartsetslot" id="kaartsetslotdiv02" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
		} else {
		sethtml = sethtml + 'Set ' + (i + 1) +' :<div class ="row"><div class="col kaartsetslot" id="kaartsetslotdiv' + i +'1" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col kaartsetslot" id="kaartsetslotdiv' + i +'2" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
		};
	};
	
	var form = '<form>';
	var submitbutton = '<div><input type="submit" value="Submit" onclick="finalformminigame()"></div>'
	document.getElementById('minigamesets').innerHTML = sethtml + submitbutton;
}

function finalformminigame() {
	console.log("finalformminigame()");
//	document.getElementById("minigamebasics").style.display = "none";
//	document.getElementById("minigameselection").style.display = "none";
//	document.getElementById("minigamespecifics").style.display = "none";
//	document.getElementById("summaryform").style.display = "block";
	minigameinformatie();
}

function maakminigameaan() {
	console.log("maakminigameaan() functie ");
	var formData = new FormData(document.querySelector("#minigamedata"));
	var encData = new URLSearchParams(formData.entries());

	  fetch("gamechane/minigames", { method: 'POST', body: encData, headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}} )
	    .then(response => response.json())
	    .then(function(myJson) { 
	    	console.log(myJson);
	    	aangemaakteminigameid = myJson.id;
	    	fetch("gamechane/cardrule", { method: 'POST', body: JSON.stringify(jsonSets), headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}} )
		    .then(response => response.json())
		    .then(function(myJson) { 
		    	console.log(myJson);
		    	})
	    	})
}

//deze functies zijn voor het drag en drop van plaatjes.
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