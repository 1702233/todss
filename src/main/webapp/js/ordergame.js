let openKaarten;
document.querySelector("#nextMinigame").addEventListener("click", nextMinigame);


(function init(){
	var minigame = JSON.parse(sessionStorage.getItem('minigame'));
	
	fetch("gamechane/minigames/" + minigame)
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    	for(var cr=0;cr<myJson.cardRules.length; cr++){
    		for(var ca=0;ca<myJson.cardRules[cr].cardAssignments.length; ca++){
    			createCard(myJson.cardRules[cr].cardAssignments[ca],myJson.cardRules[cr].group, myJson.cardRules[cr].cardAssignments.length);
    		}
    		
    	}
    	console.log(myJson.name)
    	document.getElementById('minigameName').innerHTML = myJson.name;
    	shuffle(document.querySelectorAll('.game-card'))
    	openKaarten = document.querySelectorAll('.game-card').length;
    })
})();

let lockBoard = false;

function createCard(cardAssignment,group, cardsInGroup){
	
	if(cardAssignment.card.frontside.tekst == null){
		var card = document.createElement("img");
		card.setAttribute("class","game-card");
		card.setAttribute("id",cardAssignment.card.id);
		card.setAttribute("data-rank",cardAssignment.rank);
		card.setAttribute("data-set",cardAssignment.id);
		card.setAttribute("data-cardsingroup",cardsInGroup);
		card.setAttribute("draggable","true");
		card.setAttribute("ondragstart","drag(event)");
		card.setAttribute("src",cardAssignment.card.frontside.picture.url);
	}
	else{
		var card = document.createElement("div");
		card.setAttribute("class","game-card");
		card.setAttribute("id",cardAssignment.card.id);
		card.setAttribute("data-rank",cardAssignment.rank);
		card.setAttribute("data-set",cardAssignment.id);
		card.setAttribute("data-cardsingroup",cardsInGroup);
		card.setAttribute("draggable","true");
		card.setAttribute("ondragstart","drag(event)");
		
		var wrapper = document.createElement("div");
		wrapper.setAttribute("class", "card-wrapper");
		wrapper.innerHTML = cardAssignment.card.frontside.tekst;
		
		card.appendChild(wrapper);
	}
	
	document.getElementById("cards-field").appendChild(card);
}

function allowDrop(ev)
{
	if(ev.target.className == "game-card" || ev.target.className == "card-wrapper")return;
	ev.preventDefault();
}

function drag(ev)
{
	ev.dataTransfer.setData("content",ev.target.id);
}

function drop(ev)
{
	ev.preventDefault();
	var data =ev.dataTransfer.getData("content");
	ev.target.appendChild(document.getElementById(data));	
	
	if (ev.dataTransfer.getData('content') == 'drag1'){ 
		alert("ok");
	}
	
	if(ev.target.className == "end-square"){
		document.getElementById(data).style.width = "100%";
		document.getElementById(data).style.height = "90%";
	}
	else if(ev.target.className == "cards-field"){
		document.getElementById(data).style.width = "calc(16.667% - 10px)";
		document.getElementById(data).style.height = "calc(20% - 10px)";
	}
	
	checkMatch();
}

function shuffle(cards) {
	  cards.forEach(card => {
	    let randomPos = Math.floor(Math.random() * cards.length);
	    card.style.order = randomPos;
	  });
	}

function checkMatch(){
	cardInSquare1 = document.getElementById("end-square1").childNodes[0];
	cardInSquare2 = document.getElementById("end-square2").childNodes[0];
	cardInSquare3 = document.getElementById("end-square3").childNodes[0];
	cardInSquare4 = document.getElementById("end-square4").childNodes[0];
	
	console.log(cardInSquare1);
	
	try{
		if(cardInSquare1.dataset.rank == 1){
			var cardsInSet = cardInSquare1.dataset.cardsingroup;
			
			switch(parseInt(cardsInSet)){
			case 2:
				if(cardInSquare1.dataset.set === cardInSquare2.dataset.set && cardInSquare1 !== null && cardInSquare3 == null && cardInSquare4 == null){
					removeCards(cardInSquare1,cardInSquare2);
				}
				break;
			case 3:
				if(cardInSquare1.dataset.set === cardInSquare2.dataset.set &&cardInSquare1.dataset.set === cardInSquare3.dataset.set && cardInSquare1 !== null && cardInSquare4 == null){
					removeCards(cardInSquare1,cardInSquare2,cardInSquare3);
				}
				break;
			case 4:
				if(cardInSquare1.dataset.set === cardInSquare2.dataset.set &&cardInSquare1.dataset.set === cardInSquare3.dataset.set && cardInSquare4.dataset.set == cardInSquare1.dataset.set && cardInSquare1 !== null){
					removeCards(cardInSquare1,cardInSquare2,cardInSquare3,cardInSquare4);
				}
				break;
			}
		}
	}
	catch(e){console.log(e)}
}

function removeCards(card1, card2, card3, card4){
	
	document.querySelectorAll('.game-card').forEach(card => {
		card.removeAttribute("draggable");
	});
	
	console.log('Match');
	
	setTimeout(() => {
		document.getElementById("end-square1").removeChild(card1);
		document.getElementById("end-square2").removeChild(card2);
		try{document.getElementById("end-square3").removeChild(card3)}
		catch(e){console.log(e)}
		try{document.getElementById("end-square4").removeChild(card4)}
		catch(e){console.log(e)}
		document.querySelectorAll('.game-card').forEach(card => {
			card.setAttribute("draggable","true");
		});
		checkWon(card1);
	}, 3000);
	
	
	
}

function checkWon(card1){
	openKaarten = openKaarten -card1.dataset.cardsingroup;
	console.log(openKaarten)
	
	if(openKaarten == 0){
		console.log("WIN");
		document.getElementById('winDIV').style.display='block';
	}
}

function nextMinigame(){
	console.log("AAAAAAAAAAAAAAA");
	window.location.href="startminigame.html";
}
