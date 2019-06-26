// globale variablen die worden gebruikt in meerdere functies.
var cardsetfetch;
var selectedcardset;
var aangemaakteminigameid;
var jsonSets;
var aantalSets;
var setLengte;
var alertBoxGreen = document.getElementById("greenalert");
var alertBox = document.getElementById("redalert");

(function init(){
	//inpage wizard zodat alleen minigamebasics wordt weergegeven
	document.getElementById("minigamebasics").style.display = "block";
	document.getElementById("minigameselection").style.display = "none";
	document.getElementById("minigamespecifics").style.display = "none";
	document.getElementById("summaryform").style.display = "none";
	// haalt alle cardsets op voor de ingelogde docent en zet het in cardsetfetch
	fetch("gamechane/cardset/"+sessionStorage.getItem('docent'), {headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")}})
    .then(response => response.json())
    .then(function(myJson) {
    	cardsetfetch = myJson;
    	fillcardsetdropdown(myJson);
    })
	
})();

function minigamebasisinformatie() {
	//checkt of de titel en omschrijving langer is dan 1 character als validatie.
	var titel = document.getElementById("titelinput");
	var omschrijving = document.getElementById("omschrijvinginput");
	if (titel.value.length > 1 && omschrijving.value.length > 1) {
		// volgende stap in de wizard, minigameselection.
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "block";
		document.getElementById("minigamespecifics").style.display = "none";
		document.getElementById("summaryform").style.display = "none";
		// zorg ervoor dat de voorgangbolletjes worden gevult.
		document.getElementsByClassName("step")[0].className += " finish";
		document.getElementsByClassName("step")[1].className += " active";
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
	if (speltype.value == "memory") {
		setLengte = 2;
	} else if (speltype.value == "ordergame") {
		var setlengte = document.getElementById("setlengte").value;
		setLengte = setlengte;
	}
	var aantalsets = document.getElementById("setaantal");

	//maak een jsonobject aan met alle sets met kaartIds erin om op te slaan
	JsonObj = []
	for(var i = 0;  i < aantalSets;  i++) {
		var slot = [];
		for(var i2 = 0; i2 < setLengte; i2++) {
			slot.push(document.getElementById("kaartsetslotdiv"+i+(i2+1)));
		}
		
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
				}
			}
			
		}
		JsonObj.push(item);
		
	}
	
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
	// kijkt van elke kaart van de geselecteerde set of het tekst heeft dan maakt het een dragable div aan met de tekst erin, anders een dragable img met het plaatje.
	for(var i = 0;  i < cardsetfetch[cardset].allCards.length;  i++) {
		if (cardsetfetch[cardset].allCards[i].frontside.tekst != null) {
			cardsetimages.innerHTML += '<div id="kaartsetloadinslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"><div class="card" id="drag_' + cardsetfetch[cardset].allCards[i].id + '" draggable="true" ondragstart="drag(event)" width="150" height="150">' + cardsetfetch[cardset].allCards[i].frontside.tekst + '</div></div>'
		} else {
			cardsetimages.innerHTML += '<div id="kaartsetloadinslotdiv" ondrop="drop(event)" ondragover="allowDrop(event)"><img class="card" id="drag_' + cardsetfetch[cardset].allCards[i].id + '" src="' + cardsetfetch[cardset].allCards[i].frontside.picture.url + '" draggable="true" ondragstart="drag(event)" width="150" height="150"></div>'
		}
	}
}


function soortselectie(soort) {
	// functie die een specifieke minigame selected aanroept op basis van de gebruiker's keuze
	if (soort == "memory") {
		memoryselected();
	} else if (soort == "ordergame"){
		ordergameselected();
	}
	
}

function memoryselected() {
	// functie die wordt uitgevoerd als er een memory type game word geselecteerd.
	document.getElementById('minigamedefine').innerHTML = '' +
	'<div style="display:none">' +
		'<select id="kaartsidestart">' +
			'<option value="open">kaarten open</option>' +
			'<option value="dicht" selected value>kaarten dicht</option>' +
		'</select>' +
	'</div>' +
	'<div style="padding-top:20px;" class="form-group row">' +
	    '<label for="setaantal" class="col-sm-2 col-form-label">Aantal sets</label>' +
	    '<div class="col-sm-10">' +
	      '<input type="number" class="form-control" id="setaantal" name="setaantal" value="" min="1" max="16" step="1" placeholder="Minimaal 1 - Maximaal 16"/>' +
	    '</div>' +
    '</div>' +
	'<input type="submit" class="btn btn-outline-secondary" value="Submit" onclick="memorydefined()">';
}

function memorydefined() {
	var speltype = document.getElementById("soortselection");
	var kaartsidestart = document.getElementById("kaartsidestart");
	var aantalsets = document.getElementById("setaantal");
	aantalSets = document.getElementById("setaantal").value;
	//validatie op speltype, kaartsidestart, setaantal.
	if (speltype.value.length > 1 && kaartsidestart.value.length > 1 && aantalsets.value > 0 && aantalsets.value < 17) {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "block";
		document.getElementById("summaryform").style.display = "none";
		
		var sethtml = "Vul alle sets met de kaarten uit een kaartset.";
		
		for(var i = 0;  i < aantalsets.value;  i++) {
			sethtml = sethtml + '<div class ="row">Set : ' + (i + 1) + '<div class="col kaartsetslot" id="kaartsetslotdiv' + i +'1" ondrop="drop(event)" ondragover="allowDrop(event)"></div>' + '<div class="col kaartsetslot" id="kaartsetslotdiv' + i +'2" ondrop="drop(event)" ondragover="allowDrop(event)"></div></div>';
		};
		
		var alertbox =  '<div class="alert alert-danger row" role="alert" id="redalert2"></div>';
		var submitbutton = '<div class="row" style="text-align:center;margin-top:40px;"><input type="submit" class="btn btn-outline-secondary" value="Submit" onclick="finalformminigame()"></div>'
		// de aangemaakte html in de loop wordt nu samen met een alertbox en submit button aan de user getoont.
		document.getElementById('minigamesets').innerHTML = sethtml + alertbox + submitbutton;
		//hide redalert2.
		document.getElementById("redalert2").style.display = "none";
		// zorg ervoor dat de voorgangbolletjes worden gevult.
		document.getElementsByClassName("step")[1].className += " finish";
		document.getElementsByClassName("step")[2].className += " active";
		alertBox.style.display = "none";
	} else {
		alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "Ongeldig 'Aantal sets' voor minigametype";
	}
}

function ordergameselected() {
	// maak de HTML aan voor de extra informatie nodig bij het aanmaken van een ordergame.
	document.getElementById('minigamedefine').innerHTML = '' +
	'<div style="padding-top:20px;" class="form-group row">' +
    '<label for="setaantal" class="col-sm-2 col-form-label">Aantal sets</label>' +
	    '<div class="col-sm-10">' +
	      '<input type="number" class="form-control" id="setaantal" name="setaantal" value="" step="1" min="1" max="12" placeholder="Minimaal 1 - Maximaal 12"/>' +
	    '</div>' +
    '</div>' +
    '<div class="form-group row">' +
    '<label for="setlengte" class="col-sm-2 col-form-label">Set lengte</label>' +
	    '<div class="col-sm-10">' +
	      '<input type="number" class="form-control" id="setlengte" name="setlengte" value="" max="4" min ="2" step="1" placeholder="Minimaal 2 - Maximaal 4"/>' +
	    '</div>' +
    '</div>' +
	'<input type="submit" class="btn btn-outline-secondary" value="Submit" onclick="ordergamedefined()">';;


	
}

function ordergamedefined() {
	var speltype = document.getElementById("soortselection");
	var aantalsets = document.getElementById("setaantal");
	var setlengte = document.getElementById("setlengte");
	//globale variale wordt geset zodat die in een andere functie kan worden gebruikt.
	aantalSets = document.getElementById("setaantal").value;
	// validatie van speltype(langer dan 1 character), setlengte tussen 2 en 4, setaantal meer dan 1.
	if (speltype.value.length > 1 && setlengte.value > 1 && setlengte.value< 5 && aantalsets.value > 0 && aantalsets.value < 13) {
		// volgende stap in de wizard.
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "block";
		document.getElementById("summaryform").style.display = "none";
		
		// alle dynamische html die op basis van de users keuze wordt aangemaakt komt hierin.
		var sethtml = "De eerste twee slots moeten minimaal gevult worden per set.";
		
		// voor elke set gaan we een loop in om de html hiervoor aan te maken.
		for(var i = 0;  i < aantalsets.value;  i++) {
			sethtml = sethtml + '<div class ="row"> Set : ' + (i + 1);
			// voor het aantal kaarten in een set gaan we een loop in om de html hiervoor aan te maken.
			for(var i2 = 0; i2 <setlengte.value; i2++) {
				sethtml = sethtml + '<div class="col kaartsetslot" id="kaartsetslotdiv' + i + (i2+1) +'" ondrop="drop(event)" ondragover="allowDrop(event)"></div>';
			};
			sethtml = sethtml + '</div>';
		};
		
		var alertbox =  '<div class="alert alert-danger row" role="alert" id="redalert2"></div>';
		var submitbutton = '<div class="row" style="text-align:center;margin-top:40px;"><input type="submit" class="btn btn-outline-secondary" value="Submit" onclick="finalformminigame()"></div>'
		// de aangemaakte html in de loops wordt nu samen met een alertbox en submit button aan de user getoont.
		document.getElementById('minigamesets').innerHTML = sethtml + alertbox + submitbutton;
		//hide redalert2.
		document.getElementById("redalert2").style.display = "none";
		// zorg ervoor dat de voorgangbolletjes worden gevult.
		document.getElementsByClassName("step")[1].className += " finish";
		document.getElementsByClassName("step")[2].className += " active";
		alertBox.style.display = "none";
	} else {
		alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "Ongeldige waarde ingevuld.";
	}
	
		
}

function finalformminigame() {
	var alertBox2 = document.getElementById("redalert2");
//	als de eerste twee aartsetslotdiv's in een sets gevult zijn dan dit uitvoeren. anders error.
	var validation = true;
	for (var i = 0;  i < aantalSets;  i++) {
		var slot1 = document.getElementById("kaartsetslotdiv"+i+"1").innerHTML;
		var slot2 = document.getElementById("kaartsetslotdiv"+i+"2").innerHTML;
		if (slot1.length < 2 || slot2.length < 2) {
			validation = false;
		} else {
			if (validation == true) {
				validation = true;
			} else {
				validation = false;
			}
		}
	}
	if (validation == true) {
		// volgende stap in wizard.
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "none";
		document.getElementById("summaryform").style.display = "block";
		alertBox2.style.display = "none";
		// zorg ervoor dat de voorgangbolletjes worden gevult.
		document.getElementsByClassName("step")[2].className += " finish";
		document.getElementsByClassName("step")[3].className += " active";
		minigameinformatie();
	} else {
		alertBoxGreen.style.display = "none";
        alertBox2.style.display = "block";
        alertBox2.innerHTML = "Vul elke set in.";
	}
	
}

function maakminigameaan() {
	var formtitel = document.getElementById("titel").value;
	var formspeltype = document.getElementById("speltype").value;
	var formcardsopened = document.getElementById("cardopened").value;
	var formomschrijving = document.getElementById("omschrijving").value;
	var formteachernaam = document.getElementById("teachernaam").value;
	var formcardsetid = document.getElementById("cardsetid").value;

	//validatie op alle userinputs.
	if (formtitel.length > 1 && formspeltype.length > 1 && formcardsopened.length > 1 && formomschrijving.length > 1 && formteachernaam.length > 1 && formcardsetid > 0) {
		var formData = new FormData(document.querySelector("#minigamedata"));
		var encData = new URLSearchParams(formData.entries());
		  // aanmaken van de minigame.
		  fetch("gamechane/minigames", { method: 'POST', body: encData, headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}} )
		    .then(response => response.json())
		    .then(function(myJson) { 
		    	aangemaakteminigameid = myJson.id;
		    	//voeg aan de json met alle sets nog een item toe met de minigameID
		    	item = {}
		    	item["minigameid"] = aangemaakteminigameid;
		    	var jsonSetsMinigameId = JSON.parse(JSON.stringify(jsonSets));
		    	jsonSetsMinigameId.push(item)
		    	// aanmaken van alle cardrules en assignments.
		    	fetch("gamechane/cardrule", { method: 'POST', body: JSON.stringify(jsonSetsMinigameId), headers: {'Authorization': 'Bearer ' + window.sessionStorage.getItem("myJWT")}} )
			    .then(response => response.json())
			    .then(function(myJson) { 
			    	})
		    	});
		  //terug naar eerste stap in wizard.	
		  document.getElementById("minigamebasics").style.display = "block";
		  document.getElementById("minigameselection").style.display = "none";
		  document.getElementById("minigamespecifics").style.display = "none";
		  document.getElementById("summaryform").style.display = "none";
		  
		  //sucess message.
		  alertBox.style.display = "none";
		  alertBoxGreen.style.display = "block";
	      alertBoxGreen.innerHTML = "Succes, de minigame '" + formtitel  + "' is aangemaakt "
	      //reset voortgang bolletjes.
	      document.getElementsByClassName("step")[0].className = "step active";
	  	  document.getElementsByClassName("step")[1].className = "step";
	  	  document.getElementsByClassName("step")[2].className = "step";
	  	  document.getElementsByClassName("step")[3].className = "step";
	  	  
	      resetAllInputs();
	} else {
		alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "Er mist informatie om een minigame aan te maken";
        document.getElementById("minigamebasics").style.display = "block";
		document.getElementById("minigameselection").style.display = "block";
		document.getElementById("minigamespecifics").style.display = "block";
		document.getElementById("summaryform").style.display = "block";
		window.scrollTo(0,0);
	}
	
}

function checkIfMinigameNameExistsForTeacher() {
	// haalt alle minigames op voor de ingelogde docent.
	fetch("gamechane/minigames/teacher/"+sessionStorage.getItem('docent'), {headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")}})
    .then(response => response.json())
    .then(function(myJson) {
    	var formtitel = document.getElementById("titel").value
    	var check = 0;
    	// voor elke minigame van de ingelogde docent kijken of de naam gelijk is aan nieuwe titel input.
    	for (var i = 0;  i < myJson.length;  i++) {
    		if (formtitel == myJson[i].name) {
    			check = 1;
    			// zorgt ervoor dat de user de titel kan aanpassen
    			document.getElementById('titel').removeAttribute('readonly');
    			document.getElementById('titel').value = "";
    			document.getElementById('titel').focus();
    			// rode alert zodat de userweet dat de minigame titel al bestaat.
    			alertBoxGreen.style.display = "none";
    	        alertBox.style.display = "block";
    	        alertBox.innerHTML = "Minigametitel '"+ formtitel +"' bestaat al voor '" + sessionStorage.getItem('docent') + "'\n voer een nieuwe titel in.";
    		}
    	}
    	// nadat alle aangemaakte minigames van deze docent zijn vergeleken met de nieuwe minigametitel maakminigameaan() als er geen dezelfde naam is.
    	if (check != 1) {
    		maakminigameaan();
    		alertBox.style.display = "none";
    	}
    })
}

//functie die alle userinputs leeg maakt.
function resetAllInputs() {
	 $("input[type=text], input[type=number], textarea").val("");
	 $('#soortselection').get(0).selectedIndex = 0;
	 $('#cardsetdropdown').get(0).selectedIndex = 0;
	 document.getElementById('cardsetimages').innerHTML = "";
	 
}

// functie voor het klikken van de voortgangbolletjes
function step(element) {
	// laat alleen het deel zien dat bij het voortgangbolletjes hoort.
	if (element.id == "step1") {
		document.getElementById("minigamebasics").style.display = "block";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "none";
		document.getElementById("summaryform").style.display = "none";
	} else if (element.id == "step2") {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "block";
		document.getElementById("minigamespecifics").style.display = "none";
		document.getElementById("summaryform").style.display = "none";
	} else if (element.id == "step3") {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "block";
		document.getElementById("summaryform").style.display = "none";
	} else if (element.id == "step4") {
		document.getElementById("minigamebasics").style.display = "none";
		document.getElementById("minigameselection").style.display = "none";
		document.getElementById("minigamespecifics").style.display = "none";
		document.getElementById("summaryform").style.display = "block";
	}
	
}

//deze functie is voor het drag en drop van plaatjes.
function allowDrop(ev) {
	if (ev.target.className == "card") {
		return
	}
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