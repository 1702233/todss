let openKaarten;
var timeStarted;
var timeDone;

document.querySelector("#nextMinigame").addEventListener("click", nextMinigame);

var minigame = JSON.parse(sessionStorage.getItem('minigame'));
var student = JSON.parse(sessionStorage.getItem('studentID'));

(function init(){
	var minigame = JSON.parse(sessionStorage.getItem('minigame'));
	
	fetch("gamechane/minigames/" + minigame)
    .then(response => response.json())
    .then(function(myJson) {
    	for(var cr=0;cr<myJson.cardRules.length; cr++){
    		for(var ca=0;ca<myJson.cardRules[cr].cardAssignments.length; ca++){
    			createCard(myJson.cardRules[cr].cardAssignments[ca],myJson.cardRules[cr].group, myJson.cardRules[cr].cardAssignments.length);
    		}
    	}
    	
    	timeStarted = new Date;
    	
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
		card.setAttribute("data-type","img");
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
		card.setAttribute("data-type","text");
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
	
	if(ev.target.className == "end-square"){
		if(document.getElementById(ev.target.id).childNodes[0].dataset.type == "img"){
			document.getElementById(data).style.width = "90%";	
		}
		else{
			document.getElementById(data).style.width = "100%";
		}
		document.getElementById(data).style.height = "90%";
	}
	else if(ev.target.className == "cards-field"){
		document.getElementById(data).style.width = "calc(14.5% - 10px)";
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
					if(cardInSquare2.dataset.rank == 2 && cardInSquare3.dataset.rank == 3)
						removeCards(cardInSquare1,cardInSquare2,cardInSquare3);
				}
				break;
			case 4:
				if(cardInSquare1.dataset.set === cardInSquare2.dataset.set &&cardInSquare1.dataset.set === cardInSquare3.dataset.set && cardInSquare4.dataset.set == cardInSquare1.dataset.set && cardInSquare1 !== null){
					if(cardInSquare2.dataset.rank == 2 && cardInSquare3.dataset.rank == 3 && cardInSquare4.dataset.rank == 4)
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
	}, 1000);
	
	
	
}

function checkWon(card1){
	openKaarten = openKaarten -card1.dataset.cardsingroup;
	
	if(openKaarten == 0){
		document.getElementById('winDIV').style.display='block';
		
		timeDone = new Date;
	}
}

function nextMinigame(){
	var obj = { timeStarted: timeStarted, timeDone: timeDone, minigame: minigame, student: student };
    jsonString = JSON.stringify(obj);
    
    var fetchoptionsPost = {
            method: 'POST',
            body: jsonString
        };
    
	fetch("gamechane/result", fetchoptionsPost)
	.then(function(response){
			window.location.href="startminigame.html";
		})
	.catch(error => console.log(error));
}
