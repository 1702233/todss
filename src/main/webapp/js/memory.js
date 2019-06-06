
(function init(){
	console.log("starting");
	
	fetch("gamechane/minigames/1")
    .then(response => response.json())
    .then(function(myJson) {
    	var cardrule=0;
    	console.log(myJson);
    	for(var i=0;i<myJson.cardset.allCards.length; i++){
    		for(var cr=0;cr<myJson.cardRules.length; cr++){
    			for(var ca=0;ca<myJson.cardRules[cr].cardAssignments.length; ca++){
    				if(myJson.cardRules[cr].cardAssignments[ca].card.id == myJson.cardset.allCards[i].id){
    					var cardrule = myJson.cardRules[cr].id;
    				}
    			}
    		}
    		
    		createCard(myJson.cardset.allCards[i], cardrule);
    	}
    	const cards = document.querySelectorAll('.memory-card');
    	shuffle(cards);
    	
    	cards.forEach(card => card.addEventListener('click', flipCard));
    })
	
})();

function createCard(card, cardruleID){
	var div = document.createElement("div");
	div.setAttribute("class","memory-card");
	
	div.setAttribute("data-framework", cardruleID);
	
	if(card.frontside.tekst == null){
		var frontside = document.createElement("img");
		frontside.setAttribute("class","front-face");
		frontside.setAttribute("src", card.frontside.picture.url);
	}
	else{
		var frontside = document.createElement("div");
		frontside.setAttribute("class","front-face");
		frontside.innerHTML = card.frontside.tekst;
	}
	
	if(card.backside.tekst == null){
		var backside = document.createElement("img");
		backside.setAttribute("class","back-face");
		backside.setAttribute("src", card.backside.picture.url);
	}
	else{
		var backside = document.createElement("div");
		backside.setAttribute("class","back-face");
		backside.innerHTML = card.backside.tekst;
	}
	
	div.appendChild(frontside);
	div.appendChild(backside);
	document.getElementById("memory-game").appendChild(div);
}

let hasFlippedCard = false;
let lockBoard = false;
let firstCard, secondCard;

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

    isMatch ? disableCards() : unflipCards();
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

<<<<<<< HEAD
(function shuffle() {
    cards.forEach(card => {
        let randomPos = Math.floor(Math.random() * 12);
        card.style.order = randomPos;
    });
})();
=======
function shuffle(cards) {
  cards.forEach(card => {
    let randomPos = Math.floor(Math.random() * cards.length);
    card.style.order = randomPos;
  });
}

>>>>>>> 105aaa8151753ac74612a0790a26a1dbc4e0734e

