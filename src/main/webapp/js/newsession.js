var alertBoxGreen = document.getElementById("greenalert");
var alertBox = document.getElementById("redalert");

function initPage() {
    document.getElementById("dropdownArrangements").value = 0;
    getArrangements();
    var saveButton = document.getElementById("saveButton");
    saveButton.addEventListener("click", saveSession);

}

function saveSession() {
    var dropdownArrangements = document.getElementById("dropdownArrangements");

    if (dropdownArrangements.value != 0) {

        var formData = new FormData(document.getElementById("newSessionForm")); //pak de data uit de div en maak daar formdata van
        var encData = new URLSearchParams(formData.entries()); // zet de formdata om 

        var fetchoptionsPost = {
            method: 'POST',
            body: encData
        };

        fetch("gamechane/session/", fetchoptionsPost) //post de afgeronde taak naar de database
            .then(response => response.text())
            .then((body) => {

                alertBoxGreen.style.display = "block";
                alertBoxGreen.innerHTML = "Succes, de PIN van de nieuwe sessie is: " + body;

            });
    } else {
        alertBoxGreen.style.display = "none";
        alertBox.style.display = "block";
        alertBox.innerHTML = "U heeft geen arrangement geselecteerd";
    }

}

function getArrangements() {
    var fetchoptionsGet = { method: 'GET' }
    var ingelogdeDocent = sessionStorage.getItem('docent');

    fetch("gamechane/arrangement/teacher/" + ingelogdeDocent, fetchoptionsGet) // haal alle arrangementen op
        .then(response => response.json())
        .then(function (myJson) {


            if (myJson.length == 0) { //als de myJson geen objecten bevat, zet dan een waarschuwing in de dropdown
                console.log(myJson);
                var option = document.createElement("option");
                var dropdownArrangements = document.getElementById("dropdownArrangements");
                option.innerHTML = "Er zijn nog geen arrangementen gemaakt";
                option.value = "";
                dropdownArrangements.appendChild(option);

            } else {
                console.log(myJson);
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