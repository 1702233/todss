let hasFlippedCard = false;
let lockBoard = false;
let firstCard, secondCard;
let openKaarten;
var timeStarted;
var timeDone;

var minigame = JSON.parse(sessionStorage.getItem('minigame'));
var student = JSON.parse(sessionStorage.getItem('studentID'));

document.querySelector("#nextMinigame").addEventListener("click", nextMinigame);
console.log(document.getElementById("body").clientWidth);

$('.memory-card').height(document.getElementById("body").clientWidth/5);

console.log(document.getElementById("body").clientWidth/5);

(function init(){	
	fetch("gamechane/minigames/" + minigame)
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    	var cardrule=0;
		for(var cr=0;cr<myJson.cardRules.length; cr++){
			for(var ca=0;ca<myJson.cardRules[cr].cardAssignments.length; ca++){
	    		createCard(myJson.cardRules[cr].cardAssignments[ca],myJson.cardRules[cr].group);
			}
		}	
    	const cards = document.querySelectorAll('.memory-card');
    	openKaarten = cards.length;
    	shuffle(cards);
    	
    	timeStarted = new Date;


    	
    	cards.forEach(card => card.addEventListener('click', flipCard));
    })	
})();

function createCard(cardAssignment, cardruleID){
	var div = document.createElement("div");
	div.setAttribute("class","memory-card");
	
	div.setAttribute("data-framework", cardruleID);
	
	if(cardAssignment.card.frontside.tekst == null){
		var frontside = document.createElement("img");
		frontside.setAttribute("class","front-face");
		frontside.setAttribute("src", cardAssignment.card.frontside.picture.url);
	}
	else{
		var frontside = document.createElement("div");
		frontside.setAttribute("class","front-face");
		frontside.innerHTML = cardAssignment.card.frontside.tekst;
	}
	
	if(cardAssignment.card.backside.tekst == null){
		var backside = document.createElement("img");
		backside.setAttribute("class","back-face");
		backside.setAttribute("src", cardAssignment.card.backside.picture.url);
	}
	else{
		var backside = document.createElement("div");
		backside.setAttribute("class","back-face");
		backside.innerHTML = cardAssignment.card.backside.tekst;
	}
	
	div.appendChild(frontside);
	div.appendChild(backside);
	document.getElementById("memory-game").appendChild(div);
}

function flipCard() {
    if (lockBoard) return;
    if (this === firstCard) return;

    this.classList.add('flip');

    if (!hasFlippedCard) {
        // first click
        hasFlippedCard = true;
        firstCard = this;

        return;
    }

    // second click
    secondCard = this;

    checkForMatch();
}

function checkForMatch() {
    let isMatch = firstCard.dataset.framework === secondCard.dataset.framework;

    if(isMatch){
    	disableCards();
    	checkWon()
    }else{unflipCards()}
}

function disableCards() {
    firstCard.removeEventListener('click', flipCard);
    secondCard.removeEventListener('click', flipCard);

    resetBoard();
}

function unflipCards() {
    lockBoard = true;

    setTimeout(() => {
        firstCard.classList.remove('flip');
        secondCard.classList.remove('flip');

        resetBoard();
    }, 1500);
}

function resetBoard() {
    [hasFlippedCard, lockBoard] = [false, false];
    [firstCard, secondCard] = [null, null];
}

function shuffle(cards) {
  cards.forEach(card => {
    let randomPos = Math.floor(Math.random() * cards.length);
    card.style.order = randomPos;
  });
}

function checkWon(){
	openKaarten = openKaarten -2;
	
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