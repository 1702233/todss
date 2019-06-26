//De standaard variabelen die we in de rest van het bestand gan gebruiken
let hasFlippedCard = false;
let lockBoard = false;
let firstCard, secondCard;
let openKaarten;
var timeStarted;
var timeDone;

//haalt gegevens op uit de local storage
var minigame = JSON.parse(sessionStorage.getItem('minigame'));
var student = JSON.parse(sessionStorage.getItem('studentID'));

//kijkt of er op de knop geklikt word
document.querySelector("#nextMinigame").addEventListener("click", nextMinigame);

//de functie die wordt  aangeroepen als de pagina wordt geladen
(function init(){	
	//een fetch call naar de backend om de gegevens van de minigame op te halen
	fetch("gamechane/minigames/" + minigame)
    .then(response => response.json())
    .then(function(myJson) {
    	//loopt alle gegevens regels door om de kaarten te tonen
		for(var cr=0;cr<myJson.cardRules.length; cr++){
			for(var ca=0;ca<myJson.cardRules[cr].cardAssignments.length; ca++){
	    		createCard(myJson.cardRules[cr].cardAssignments[ca],myJson.cardRules[cr].group);
			}
		}
		
		//maakt een lijst van alle kaarten, kijkt hoeveel kaarten er zijn en legt deze op willekeurige plekken
    	const cards = document.querySelectorAll('.memory-card');
    	openKaarten = cards.length;
    	shuffle(cards);
    	
    	timeStarted = new Date;

    	
    	document.getElementById('minigameName').innerHTML = myJson.name;
    	cards.forEach(card => card.addEventListener('click', flipCard));
    })	
})();

//functie om de kaarten te tonen
function createCard(cardAssignment, cardruleID){
	//attributen om de standaard dingen voor een kaart te maken
	var div = document.createElement("div");
	div.setAttribute("class","memory-card");
	div.setAttribute("data-framework", cardruleID);
	
	//maakt de voorkant aan met tekst of het plaatje dat bij de kaart hoort
	if(cardAssignment.card.frontside.tekst == null){
		var frontside = document.createElement("img");
		frontside.setAttribute("class","front-face");
		frontside.setAttribute("src", cardAssignment.card.frontside.picture.url);
	}
	else{
		var frontside = document.createElement("div");
		frontside.setAttribute("class","front-face");
		
		var wrapper = document.createElement("div");
		wrapper.setAttribute("class", "card-wrapper");
		wrapper.innerHTML = cardAssignment.card.frontside.tekst;
		
		frontside.appendChild(wrapper);
	}
	
	//maakt de achterkant aan met tekst of het plaatje dat bij de kaart hoort
	if(cardAssignment.card.backside.tekst == null){
		var backside = document.createElement("img");
		backside.setAttribute("class","back-face");
		backside.setAttribute("src", cardAssignment.card.backside.picture.url);
	}
	else{
		var backside = document.createElement("div");
		backside.setAttribute("class","back-face");
		
		var wrapper = document.createElement("div");
		wrapper.setAttribute("class", "card-wrapper");
		wrapper.innerHTML = cardAssignment.card.backside.tekst;
		
		backside.appendChild(wrapper);
	}
	
	//voegt de kaart toe aan het speelveld
	div.appendChild(frontside);
	div.appendChild(backside);
	document.getElementById("memory-game").appendChild(div);
}

//functie om een kaart te draaien
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

//kijkt of de 2 gekozen kaarten bij dezelfde set horen
function checkForMatch() {
    let isMatch = firstCard.dataset.framework === secondCard.dataset.framework;

    if(isMatch){
    	disableCards();
    	checkWon()
    }else{unflipCards()}
}

//disabled de kaarten om niet omgedraaid te worden omdat ze al gekozen zijn
function disableCards() {
    firstCard.removeEventListener('click', flipCard);
    secondCard.removeEventListener('click', flipCard);

    resetBoard();
}

//als de kaarten zijn gekozen maar niet matchen legt deze functie ze weer op zijn kop
function unflipCards() {
    lockBoard = true;

    setTimeout(() => {
        firstCard.classList.remove('flip');
        secondCard.classList.remove('flip');

        resetBoard();
    }, 1500);
}

//zet de variabelen weer naar de standaard waarde zodat er opnieuw een set gekozen kan worden
function resetBoard() {
    [hasFlippedCard, lockBoard] = [false, false];
    [firstCard, secondCard] = [null, null];
}

//husselt de kaarten
function shuffle(cards) {
  cards.forEach(card => {
    let randomPos = Math.floor(Math.random() * cards.length);
    card.style.order = randomPos;
  });
}

//kijkt of er nog kaarten niet aan hun setpartner zijn gekoppeld 
function checkWon(){
	openKaarten = openKaarten -2;
	
	//als er geen open kaarten meer zijn melding tonen en de eind tijd opslaan
	if(openKaarten == 0){
		document.getElementById('winDIV').style.display='block';
		document.getElementById('memory-game').style.display='none';
		timeDone = new Date;
	}
}

//functie die wordt uitgevoerd als er op een knop is geklikt
function nextMinigame(){
	//de gegevens die verstuurd moeten gaan worden klaarzetten
	var obj = { timeStarted: timeStarted, timeDone: timeDone, minigame: minigame, student: student };
    jsonString = JSON.stringify(obj);
    
    var fetchoptionsPost = {
            method: 'POST',
            body: jsonString
        };
    
    //fetch om de behaalde resultaten op te slaan
	fetch("gamechane/result", fetchoptionsPost)
	.then(function(response){
			window.location.href="startminigame.html";
		})
	.catch(error => console.log(error));
	
}