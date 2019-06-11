function initPage() {
    getMinigames();
}

function getMinigames(){
    var fetchoptionsGet = { method: 'GET' }

    fetch("gamechane/minigames", fetchoptionsGet) // haal alle minigames op
        .then(response => response.json())
        .then(function (myJson) {

            console.log(myJson);
            if (myJson.length == 0) { //als de myJson geen objecten bevat, zet dan een waarschuwing in de dropdown
            
                var option = document.createElement("option");
                var dropdownMinigames = document.querySelector("#minigameDropdown");
                option.innerHTML = "Voeg eerst een huishoudelijke taak toe";
                dropdownMinigames.appendChild(option);

            } else {

                for (const minigame of myJson) { //maak een dropdown option voor elk object wat er in de myJson staat
                    console.log(minigame.name);
                    var option = document.createElement("option");
                    var dropdownMinigames = document.querySelector("#minigameDropdown");
                    option.value = minigame.name;
                    option.innerHTML = minigame.name;
                    dropdownMinigames.appendChild(option);

                }
            }

        });
}

initPage();