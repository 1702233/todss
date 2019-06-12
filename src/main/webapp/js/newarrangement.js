function initPage() {
    getMinigames();
    addMinigameToArrangement()
}

function getMinigames() {
    var fetchoptionsGet = { method: 'GET' }

    //HARDCODED WORD DE MINIGAMES VAN LERAAR1 OPGEHAALD, DIT MOET NOG AANGEPAST WORDEN NAAR INGELOGDE USERsdfgsddfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfgsdfg
    fetch("gamechane/minigames/teacher/leraar1", fetchoptionsGet) // haal alle minigames op
        .then(response => response.json())
        .then(function (myJson) {

            console.log(myJson);
            if (myJson.length == 0) { //als de myJson geen objecten bevat, zet dan een waarschuwing in de dropdown

                var option = document.createElement("option");
                var dropdownMinigames = document.getElementById("dropdownMinigames");
                option.innerHTML = "Er zijn nog geen minigames gemaakt";
                dropdownMinigames.appendChild(option);

            } else {

                for (const minigame of myJson) { //maak een dropdown option voor elk object wat er in de myJson staat
                    console.log(minigame.name);

                    var option = document.createElement("option");
                    var dropdownMinigames = document.getElementById("dropdownMinigames");
                    option.value = minigame.name + " - " + minigame.type;
                    option.innerHTML = minigame.name;
                    dropdownMinigames.appendChild(option);

                }
            }

        });
}

function addMinigameToArrangement() {
    var addButton = document.getElementById("addButton");
    var dropdownMinigames = document.getElementById("dropdownMinigames");
    var arrangementTable = document.getElementById("arrangementTable");

    addButton.addEventListener("click", function () {

        var tr = document.createElement("tr");
        var td = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var verwijderButton = document.createElement("button");

        verwijderButton.innerHTML = "verwijder";
        verwijderButton.addEventListener("click", function(){
            var row = verwijderButton.parentNode.parentNode
            row.parentNode.removeChild(row);
        });

        td.innerHTML = dropdownMinigames.innerHTML;
        td2.innerHTML = "";
        td3.appendChild(verwijderButton);
        tr.appendChild(td);
        tr.appendChild(td2);
        tr.appendChild(td3);

        
        arrangementTable.appendChild(tr);
    });
}

function saveArrangement() {

}

initPage();