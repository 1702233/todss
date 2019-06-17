function initPage() {
    getArrangements();
    var saveButton = document.getElementById("saveButton");
    saveButton.addEventListener("click", saveSession);

}

function saveSession() {
    var ingelogdeDocent = sessionStorage.getItem('docent');
    console.log("penis");
    var formData = new FormData(document.getElementById("newSessionForm")); //pak de data uit de div en maak daar formdata van
    var encData = new URLSearchParams(formData.entries()); // zet de formdata om 

    var fetchoptionsPost = {
        method: 'POST',
        body: encData
    };

    fetch("gamechane/session/" + ingelogdeDocent, fetchoptionsPost) //post de afgeronde taak naar de database
        .then(function (response) {
            if (response.ok) {

                alert("succes")
            } else {
                alert("Er is iets mis gegaan");
            }
        });
}

function getArrangements() {
    var fetchoptionsGet = { method: 'GET' }
    var ingelogdeDocent = sessionStorage.getItem('docent');

    fetch("gamechane/arrangement/teacher/" + ingelogdeDocent, fetchoptionsGet) // haal alle arrangementen op
        .then(response => response.json())
        .then(function (myJson) {


            if (myJson.length == 0) { //als de myJson geen objecten bevat, zet dan een waarschuwing in de dropdown

                var option = document.createElement("option");
                var dropdownArrangements = document.getElementById("dropdownArrangements");
                option.innerHTML = "Er zijn nog geen arrangements gemaakt";
                option.value = "";
                dropdownArrangements.appendChild(option);

            } else {

                for (const arrangement of myJson) { //maak een dropdown option voor elk object wat er in de myJson staat


                    var option = document.createElement("option");
                    var dropdownArrangements = document.getElementById("dropdownArrangements");
                    option.value = arrangement.id;
                    option.innerHTML = arrangement.name + " - " + arrangement.description;
                    dropdownArrangements.appendChild(option);

                }
            }

        });
}

initPage();