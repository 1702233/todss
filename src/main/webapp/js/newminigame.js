var cardsetfetch;

(function init(){
	console.log("fetching all cardsets");
	document.getElementById("minigamebasics").style.display = "block";
	document.getElementById("minigameselection").style.display = "none";
	document.getElementById("minigamespecifics").style.display = "none";
	document.getElementById("summaryform").style.display = "none";
	fetch("gamechane/cardset/leraar1")
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    	cardsetfetch = myJson;
    	fillcardsetdropdown(myJson);
    })
	
})();

function minigamebasisinformatie() {
	document.getElementById("minigamebasics").style.display = "none";
	document.getElementById("minigameselection").style.display = "block";
	document.getElementById("minigamespecifics").style.display = "none";
	document.getElementById("summaryform").style.display = "none";
}

function minigameinformatie() {
	
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
	document.getElementById("cardsetid").value = cardset.value;
		
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
	for(var i = 0;  i < cardsetfetch[cardset].allCards.length;  i++) {
		//cardsetimages.innerHTML += '<div class="col-sm">' + cardsetfetch[cardset].allCards[i].frontside.picture.url + "</div>";
		//cardsetimages.innerHTML += '<img id="drag' + 1 + '" src="' + cardsetfetch[cardset].allCards[i].frontside.picture.url + '" draggable="true" ondragstart="drag(event)" width="100" height="150">';
		cardsetimages.innerHTML += '<div class="col" id="kaartsetloadinslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"><img id="drag' + i + '" src="' + cardsetfetch[cardset].allCards[i].frontside.picture.url + '" draggable="true" ondragstart="drag(event)" width="150" height="150"></div>'
	}
	console.log(cardsetfetch[cardset].allCards)
}


function soortselectie(soort) {
	console.log("geselecteerde minigame soort = " + soort);
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
	console.log("memoryselected functie");
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
	document.getElementById("minigamebasics").style.display = "none";
	document.getElementById("minigameselection").style.display = "block";
	document.getElementById("minigamespecifics").style.display = "block";
	document.getElementById("summaryform").style.display = "none";
	
	var setamountobject = document.getElementById("setaantal");
	var kaartsidestart = document.getElementById("kaartsidestart").value;
	var sethtml;
	console.log(sethtml);
	
	for(var i = 0;  i < setamountobject.value;  i++) {
		if (sethtml == null) {
			sethtml = 'Set 1 :<div class ="row"><div class="col kaartsetslot" id="kaartset01" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col kaartsetslot" id="kaartsetslotdiv02" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
		} else {
		sethtml = sethtml + 'Set ' + (i + 1) +' :<div class ="row"><div class="col kaartsetslot" id="kaartset' + i +'1" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col kaartsetslot" id="kaartset' + i +'2" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
		};
	};
	
	var form = '<form>';
	var submitbutton = '<div><input type="submit" value="Submit" onclick="finalformminigame()"></div>'
	document.getElementById('minigamesets').innerHTML = sethtml + submitbutton;
}

function finalformminigame() {
	document.getElementById("minigamebasics").style.display = "none";
	document.getElementById("minigameselection").style.display = "none";
	document.getElementById("minigamespecifics").style.display = "none";
	document.getElementById("summaryform").style.display = "block";
	minigameinformatie();
	console.log("hier de functie voor de form aanmaken.")
}

function maakminigameaan(sets, kaartsidestart) {
	console.log("minigame aangemaakt met : ");
	var formData = new FormData(document.querySelector("#minigamedata"));
	var encData = new URLSearchParams(formData.entries());

	  fetch("gamechane/minigames", { method: 'POST', body: encData, headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}} )
	    .then(response => response.json())
	    .then(function(myJson) { 
	    	console.log(myJson); 
	    	})
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