var cardsetfetch;
var selectedcardset;
var aangemaakteminigameid;
var jsonSets;
var aantalSets;
var setLengte;
var alertBoxGreen = document.getElementById("greenalert");
var alertBox = document.getElementById("redalert");

(function init(){
	console.log("fetching all cardsets");
	document.getElementById("minigamebasics").style.display = "block";
	document.getElementById("minigameselection").style.display = "none";
	document.getElementById("minigamespecifics").style.display = "none";
	document.getElementById("summaryform").style.display = "none";
	fetch("gamechane/cardset/"+sessionStorage.getItem('docent'))
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    	cardsetfetch = myJson;
    	fillcardsetdropdown(myJson);
    })
	
})();

function minigamebasisinformatie() {
	var titel = document.getElementById("titelinput");
	var omschrijving = document.getElementById("omschrijvinginput");
	if (titel.value.length > 1 && omschrijving.value.length > 1) {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "block";
		document.getElementById("minigamespecifics").style.display = "none";
		document.getElementById("summaryform").style.display = "none";
		alertBox.style.display = "none";
	} else {
		alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "Vul een titel en omschrijving in.";
	}

}

function minigameinformatie() {
	var titel = document.getElementById("titelinput");
	var omschrijving = document.getElementById("omschrijvinginput");
	var speltype = document.getElementById("soortselection");
	var leraar = sessionStorage.getItem('docent');
	var cardset = document.getElementById("cardsetdropdown")
	try {
		var kaartsidestart = document.getElementById("kaartsidestart");
	} catch(e) {}
	
	// zet de zetlengte op 2 als het type memory is, anders haalt hij het op uit de userinput.
	console.log("speltype :" + speltype.value)
	if (speltype.value == "memory") {
		setLengte = 2;
	} else if (speltype.value == "ordergame") {
		var setlengte = document.getElementById("setlengte").value;
		setLengte = setlengte;
	}
	console.log("setlengte :" + setLengte);
	var aantalsets = document.getElementById("setaantal");

	//maak een jsonobject aan met alle sets met kaartIds erin om op te slaan
	JsonObj = []
	for(var i = 0;  i < aantalSets;  i++) {
		var slot = [];
		for(var i2 = 0; i2 < setLengte; i2++) {
			slot.push(document.getElementById("kaartsetslotdiv"+i+(i2+1)));
		}
		try {
			console.log(slot[0]);
			console.log(slot[1]);
			console.log(slot[2]);
			console.log(slot[3]);
		} catch(e) {		}
		
		item = {};
		for(var i3 = 0; i3 < setLengte; i3++) {
			if (i3 == 0) {
				item["set"+i] = slot[i3].childNodes[0].id.split("_").pop() + " ";
			}else if(i3 < 2) {
				item["set"+i] += slot[i3].childNodes[0].id.split("_").pop() + " ";
			} else {
				try {
					item["set"+i] += slot[i3].childNodes[0].id.split("_").pop() + " ";
				} catch(e) {
					console.log(slot[i3].id + "heeft geen waarde.")
				}
			}
			
		}
		JsonObj.push(item);
		
	}
	
	console.log(JsonObj);
	jsonSets = JsonObj;
	
	document.getElementById("titel").value = titel.value;
	document.getElementById("speltype").value = speltype.value;
	document.getElementById("omschrijving").value = omschrijving.value;
	document.getElementById("teachernaam").value = leraar;
	document.getElementById("cardsetid").value = selectedcardset;
	try {
		document.getElementById("cardopened").value = $("#kaartsidestart :selected").val();
	} catch(e) {}
		
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
	cardsetimages.innerHTML ='';
	selectedcardset = cardsetfetch[cardset].id;
	console.log("cardsetselectie() gaat af, kaartset id = " + selectedcardset);
	// kijkt van elke kaart van de geselecteerde set of het tekst heeft dan maakt het een dragable div aan met de tekst erin, anders een dragable img met het plaatje.
	for(var i = 0;  i < cardsetfetch[cardset].allCards.length;  i++) {
		if (cardsetfetch[cardset].allCards[i].frontside.tekst != null) {
			cardsetimages.innerHTML += '<div class="col" id="kaartsetloadinslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"><div id="drag_' + cardsetfetch[cardset].allCards[i].id + '" draggable="true" ondragstart="drag(event)" width="150" height="150">' + cardsetfetch[cardset].allCards[i].frontside.tekst + '</div></div>'
		} else {
			cardsetimages.innerHTML += '<div class="col" id="kaartsetloadinslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"><img id="drag_' + cardsetfetch[cardset].allCards[i].id + '" src="' + cardsetfetch[cardset].allCards[i].frontside.picture.url + '" draggable="true" ondragstart="drag(event)" width="150" height="150"></div>'
		}
	}
	console.log(cardsetfetch[cardset].allCards)
}


function soortselectie(soort) {
	console.log("soortselectie(soort) gaat af met soort = " + soort);
	// functie die een specifieke minigame selected aanroept op basis van de gebruiker's keuze
	if (soort == "memory") {
		memoryselected();
	} else if (soort == "ordergame"){
		ordergameselected();
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
	var speltype = document.getElementById("soortselection");
	var kaartsidestart = document.getElementById("kaartsidestart");
	var aantalsets = document.getElementById("setaantal");
	aantalSets = document.getElementById("setaantal").value;
	console.log("aantalsets : " + aantalSets)
	if (speltype.value.length > 1 && kaartsidestart.value.length > 1 && setaantal.value > 0) {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "block";
		document.getElementById("summaryform").style.display = "none";
		
		var sethtml;
		
		for(var i = 0;  i < aantalsets.value;  i++) {
			if (sethtml == null) {
				sethtml = 'Set 1 :<div class ="row"><div class="col kaartsetslot" id="kaartsetslotdiv01" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col kaartsetslot" id="kaartsetslotdiv02" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
			} else {
			sethtml = sethtml + 'Set ' + (i + 1) +' :<div class ="row"><div class="col kaartsetslot" id="kaartsetslotdiv' + i +'1" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col kaartsetslot" id="kaartsetslotdiv' + i +'2" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
			};
		};
		
		var alertbox =  '<div class="alert alert-danger" role="alert" id="redalert2"></div>';
		var submitbutton = '<div><input type="submit" value="Submit" onclick="finalformminigame()"></div>'
		document.getElementById('minigamesets').innerHTML = sethtml + alertbox + submitbutton;
		alertBox.style.display = "none";
	} else {
		alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "lege waarde ingevuld.";
	}
}

function ordergameselected() {
	console.log("ordergameselected() functie");
	document.getElementById('minigamedefine').innerHTML = '' +
	'<div class="input-group mb-3">' +
		'<div class="input-group-prepend">' +
			'<span class="input-group-text" id="basic-addon">hoeveel sets</span>' +
			'<input type="number" name="setaantal" id="setaantal">' +
		'</div>' +
	'</div>' +
	'<div class="input-group mb-3">' +
		'<div class="input-group-prepend">' +
			'<span class="input-group-text" id="basic-addon">maximale setlengte 2-4</span>' +
			'<input type="number" name="setlengte" id="setlengte">' +
		'</div>' +
	'</div>' +
	'<input type="submit" value="Submit" onclick="ordergamedefined()">';;


	
}

function ordergamedefined() {
	console.log("ordergamedefined()");
	console.log("memorydefined() functie")
	var speltype = document.getElementById("soortselection");
	var aantalsets = document.getElementById("setaantal");
	var setlengte = document.getElementById("setlengte");
	aantalSets = document.getElementById("setaantal").value;
	console.log("aantalsets : " + aantalsets.value);
	console.log("setlengte : " + setlengte.value);
	if (speltype.value.length > 1 && setlengte.value > 1 && setlengte.value< 5 && setaantal.value > 0) {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "block";
		document.getElementById("summaryform").style.display = "none";
		
		var sethtml = "";
		
		for(var i = 0;  i < aantalsets.value;  i++) {
			sethtml = sethtml + 'Set ' + (i + 1) +' :<div class ="row">';
			for(var i2 = 0; i2 <setlengte.value; i2++) {
				sethtml = sethtml + '<div class="col kaartsetslot" id="kaartsetslotdiv' + i + (i2+1) +'" ondrop="drop(event)" ondragover="allowDrop(event)"></div>';
			};
			sethtml = sethtml + '</div>';
		};
		
		var alertbox =  '<div class="alert alert-danger" role="alert" id="redalert2"></div>';
		var submitbutton = '<div><input type="submit" value="Submit" onclick="finalformminigame()"></div>'
		document.getElementById('minigamesets').innerHTML = sethtml + alertbox + submitbutton;
		alertBox.style.display = "none";
	} else {
		alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "ongeldige waarde ingevuld.";
	}
	
		
}

function finalformminigame() {
	console.log("finalformminigame()");
	var alertBox2 = document.getElementById("redalert2");
//	als de eerste twee aartsetslotdiv's in een sets gevult zijn dan dit uitvoeren. anders error.
	var validation = true;
	console.log(aantalSets);
	for (var i = 0;  i < aantalSets;  i++) {
		var slot1 = document.getElementById("kaartsetslotdiv"+i+"1").innerHTML;
		var slot2 = document.getElementById("kaartsetslotdiv"+i+"2").innerHTML;
		console.log(slot1);
		console.log(slot2)
		if (slot1.length < 2 || slot2.length < 2) {
			validation = false;
			console.log("false2")	
		} else {
			if (validation == true) {
				validation = true;
				console.log("true1")
			} else {
				validation = false;
				console.log("false1")
			}	
		}
	}
	if (validation == true) {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "none";
		document.getElementById("summaryform").style.display = "block";
		alertBox2.style.display = "none";
		minigameinformatie();
	} else {
		alertBoxGreen.style.display = "none";
        alertBox2.style.display = "block";
        alertBox2.innerHTML = "vul elke set in.";
	}
	
}

function maakminigameaan() {
	console.log("maakminigameaan() functie ");
	var formtitel = document.getElementById("titel").value;
	var formspeltype = document.getElementById("speltype").value;
	var formcardsopened = document.getElementById("cardopened").value;
	var formomschrijving = document.getElementById("omschrijving").value;
	var formteachernaam = document.getElementById("teachernaam").value;
	var formcardsetid = document.getElementById("cardsetid").value;
	
	console.log(formtitel + formspeltype + formcardsopened + formomschrijving + formteachernaam + formcardsetid);
	if (formtitel != null && formspeltype != null && formcardsopened != null && formomschrijving != null && formteachernaam != null && formcardsetid != null) {
		var formData = new FormData(document.querySelector("#minigamedata"));
		var encData = new URLSearchParams(formData.entries());

		  fetch("gamechane/minigames", { method: 'POST', body: encData, headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}} )
		    .then(response => response.json())
		    .then(function(myJson) { 
		    	console.log(myJson);
		    	aangemaakteminigameid = myJson.id;
		    	item = {}
		    	item["minigameid"] = aangemaakteminigameid;
		    	var jsonSetsMinigameId = JSON.parse(JSON.stringify(jsonSets));
		    	jsonSetsMinigameId.push(item)
		    	console.log("minigame id = " + aangemaakteminigameid);
		    	fetch("gamechane/cardrule", { method: 'POST', body: JSON.stringify(jsonSetsMinigameId), headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}} )
			    .then(response => response.json())
			    .then(function(myJson) { 
			    	console.log(myJson);
			    	})
		    	})
		    	
		  document.getElementById("minigamebasics").style.display = "block";
		  document.getElementById("minigameselection").style.display = "none";
		  document.getElementById("minigamespecifics").style.display = "none";
		  document.getElementById("summaryform").style.display = "none";
		  
		  alertBox.style.display = "none";
		  alertBoxGreen.style.display = "block";
          alertBoxGreen.innerHTML = "Succes, de minigame '" + formtitel  + "' is aangemaakt "
          resetAllInputs();
	} else {
		alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "er mist informatie om een minigame aan te maken";
        document.getElementById("minigamebasics").style.display = "block";
		document.getElementById("minigameselection").style.display = "block";
		document.getElementById("minigamespecifics").style.display = "block";
		document.getElementById("summaryform").style.display = "block";
		window.scrollTo(0,0);
	}
	
}

function checkIfMinigameNameExistsForTeacher() {
	console.log("checkIfMinigameNameExistsForTeacher() functie ");
	fetch("gamechane/minigames/teacher/"+sessionStorage.getItem('docent'))
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    	var formtitel = document.getElementById("titel").value
    	var check = 0;
    	for (var i = 0;  i < myJson.length;  i++) {
    		if (formtitel == myJson[i].name) {
    			console.log("GELIJKE TITEL" + formtitel + "==" + myJson[i].name);
    			check = 1;
    			document.getElementById('titel').removeAttribute('readonly');
    			document.getElementById('titel').value = "";
    			document.getElementById('titel').focus();
    			alertBoxGreen.style.display = "none";
    	        alertBox.style.display = "block";
    	        alertBox.innerHTML = "Minigametitel '"+ formtitel +"' bestaat al voor leraar : " + sessionStorage.getItem('docent') + "\n voer een nieuwe titel in.";
    		} else {
    			console.log(formtitel + "!=" + myJson[i].name);
    		}
    	}
    	if (check != 1) {
    		maakminigameaan();
    		alertBox.style.display = "none";
    	}
    })
}

function resetAllInputs() {
	 $("input[type=text], input[type=number], textarea").val("");
	 $('#soortselection').get(0).selectedIndex = 0;
	 $('#cardsetdropdown').get(0).selectedIndex = 0;
	 document.getElementById('cardsetimages').innerHTML = "";
	 
}

//deze functie is voor het drag en drop van plaatjes.
function allowDrop(ev) {
  ev.preventDefault();
}
//deze functie is voor het drag en drop van plaatjes.
function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}
//deze functie is voor het drag en drop van plaatjes.
function drop(ev) {
  ev.preventDefault();
  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
}