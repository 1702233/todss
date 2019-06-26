var minigameIDsInTable = [];
var alertBox = document.getElementById("redalert");
var alertBoxGreen = document.getElementById("greenalert");

function initPage() {
    getMinigames();
    addMinigameToArrangement();
    saveArrangement();
}

function getMinigames() {

    clearDropdown();
    var fetchoptionsGet = { method: 'GET', headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")} }

    var ingelogdeDocent = sessionStorage.getItem('docent');

    fetch("gamechane/minigames/teacher/" + ingelogdeDocent, fetchoptionsGet) // haal alle minigames op
        .then(response => response.json())
        .then(function (myJson) {

            if (myJson.length == 0) { //als de myJson geen objecten bevat, zet dan een waarschuwing in de dropdown

                var option = document.createElement("option");
                var dropdownMinigames = document.getElementById("dropdownMinigames");
                option.innerHTML = "Er zijn nog geen minigames gemaakt";
                option.value = "";
                dropdownMinigames.appendChild(option);
            } else {

                for (const minigame of myJson) { //maak een dropdown option voor elk object wat er in de myJson staat
                    if (!minigameIDsInTable.includes(minigame.id)) {
                        var option = document.createElement("option");
                        var dropdownMinigames = document.getElementById("dropdownMinigames");

                        option.value = minigame.id;
                        option.innerHTML = minigame.name + " - " + minigame.type;
                        dropdownMinigames.appendChild(option);
                    }
                }
            }
        });
}

function addMinigameToArrangement() {
    var dropdownMinigames = document.getElementById("dropdownMinigames");
    var arrangementTable = document.getElementById("arrangementTable");

    addButton.addEventListener("click", function () {

        if (dropdownMinigames.value != "") {
            minigameIDsInTable.push(parseInt(dropdownMinigames.value));


            var value = dropdownMinigames[dropdownMinigames.selectedIndex].innerHTML;
            var minigameData = value.split(" - ")
            var tr = document.createElement("tr");
            var td = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var td4 = document.createElement("td");
            var verwijderButton = document.createElement("button");

            td.innerHTML = dropdownMinigames.value;
            td2.innerHTML = minigameData[0];
            td3.innerHTML = minigameData[1];
            td4.appendChild(verwijderButton);
            tr.appendChild(td);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tr.appendChild(td4);


            verwijderButton.innerHTML = "Verwijder";
            verwijderButton.addEventListener("click", function () {
                var row = verwijderButton.parentNode.parentNode
                row.parentNode.removeChild(row);

                minigameIDsInTable.splice(minigameIDsInTable.indexOf(parseInt(td.innerHTML)), 1);


                clearDropdown();
                getMinigames();
            });

            getMinigames();
            arrangementTable.appendChild(tr);
            dropdownMinigames.remove(dropdownMinigames.selectedIndex);
        }

    });
}

function saveArrangement() {



    var saveButton = document.getElementById("saveButton"); 
    saveButton.addEventListener("click", function () {

        var minigameList = [];  //lege lijst waar minigameID's in komen voor eht opslaan van een arrangement
        var table = document.getElementById("arrangementTable");
        var rows = table.getElementsByTagName('tr').length;

        for (var i = 1; i < rows; i++) {                                    //zet de minigameID's die in de tabel staan in de lijst zodat deze mee gestuurd kunnen worden met het aanmaken van een arrangement
            minigameList.push(table.rows[i].cells[0].innerHTML);
        }

        var name = document.getElementById("name").value;
        var omschrijving = document.getElementById("omschrijving").value;
        var ingelogdeDocent = sessionStorage.getItem('docent');
        var obj = { name: name, description: omschrijving, teacher: ingelogdeDocent, minigames: minigameList };  //maak een JSON object aan
        jsonString = JSON.stringify(obj);  //maak een string van het json object


        if (document.getElementById("name").value == "") { // validaties

            alertBoxGreen.style.display = "none";
            alertBox.style.display = "block";
            alertBox.innerHTML = "U heeft nog geen naam ingevoerd";
        }

        else if (document.getElementById("omschrijving").value == "") {
            alertBoxGreen.style.display = "none";
            alertBox.style.display = "block";
            alertBox.innerHTML = "U heeft nog geen omschrijving ingevoerd";
        }

        else if (minigameIDsInTable.length <= 0) {
            alertBoxGreen.style.display = "none";
            alertBox.style.display = "block";
            alertBox.innerHTML = "U heeft nog geen minigames toegevoegd aan het arrangement";
        }

        else {

            var fetchoptionsPost = {
                method: 'POST',
                body: jsonString,
                headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")}
            };

            fetch("gamechane/arrangement", fetchoptionsPost) //post het arrangement naar de database
                .then(function (response) {
                    if (response.ok) {
                        alertBox.style.display = "none";
                        alertBoxGreen.style.display = "block";
                        alertBoxGreen.innerHTML = "Arrangement is succesvol aangemaakt";
                        clearPage();
                    } else {
                        alertBox.style.display = "block";
                        alertBoxGreen.style.display = "none";
                        alertBox.innerHTML = "U heeft al een arrangement aangemaakt met deze naam";
                    }
                });


        }


    });





}

function clearDropdown() { //maak de dropm leeg
    var dropdownMinigames = document.getElementById("dropdownMinigames");
    var length = dropdownMinigames.options.length;
    for (i = 0; i < length; i++) {
        dropdownMinigames.options[i] = null;
    }
}

function clearPage() { // maak de pagina leeg

    document.getElementById("name").value = "";
    document.getElementById("omschrijving").value = "";

    var tableHeaderRowCount = 1;
    var table = document.getElementById("arrangementTable");
    var rowCount = table.rows.length;
    for (var i = tableHeaderRowCount; i < rowCount; i++) {
        table.deleteRow(tableHeaderRowCount);
    }

    minigameIDsInTable = [];

    getMinigames();
}

initPage();