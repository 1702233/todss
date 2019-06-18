(function init(){
	fetch("gamechane/minigames/8")
    .then(response => response.json())
    .then(function(myJson) {
    	console.log(myJson);
    	for(var cr=0;cr<myJson.cardRules.length; cr++){
    		console.log(myJson.cardRules[cr]);
    		createCard(myJson.cardRules[cr]);
    	}
    })
})();

function createCard(cardRule){
	
}

function allowDrop(ev)
{
  ev.preventDefault();
}

function drag(ev)
{
	console.log(ev.target);
	ev.dataTransfer.setData("content",ev.target.id);
}

function drop(ev)
{
	console.log(ev.target.className)
	ev.preventDefault();
	var data =ev.dataTransfer.getData("content");
	ev.target.appendChild(document.getElementById(data));
	
	console.log(document.getElementById(data).dataset.rank);
	console.log(document.getElementById(data).dataset.set);
	
	
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
}

function checkMatch(){
	
}