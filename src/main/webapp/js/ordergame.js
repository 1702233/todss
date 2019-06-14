fetch("gamechane/minigames/5")
.then(response => response.json())
.then(function(myJson) {
	
}


function allowDrop(ev)
{
  ev.preventDefault();
}

function drag(ev)
{
  ev.dataTransfer.setData("content",ev);
}

function drop(ev)
{
  ev.preventDefault();
        var data =ev.dataTransfer.getData("content");
  //if (ev.target.id == document.getElementById(image).getAttribute('data-div')){ 
  //   alert("ok");          
  ev.target.appendChild(document.getElementById(data));
}

function checkMatch(){
	
}