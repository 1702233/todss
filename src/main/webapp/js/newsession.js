var alertBoxGreen = document.getElementById("greenalert");

function initPage() {
    getArrangements();
    var saveButton = document.getElementById("saveButton");
    saveButton.addEventListener("click", saveSession);

}

function saveSession() {
    var formData = new FormData(document.getElementById("newSessionForm")); //pak de data uit de div en maak daar formdata van
    var encData = new URLSearchParams(formData.entries()); // zet de formdata om 

    var fetchoptionsPost = {
        method: 'POST',
        body: encData,
        headers : { 
            'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")}
    };

    fetch("gamechane/session/", fetchoptionsPost) //post de afgeronde taak naar de database
        .then(response => response.text())
        .then((body) => {

            alertBoxGreen.style.display = "block";
            alertBoxGreen.innerHTML = "Succes, de PIN van de nieuwe sessie is: " + body;
            
        });
}

function getArrangements() {
    var fetchoptionsGet = { method: 'GET', headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")} }
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
                    console.log(arrangement.id);
                    option.value = arrangement.id;
                    option.innerHTML = arrangement.name + " - " + arrangement.description;
                    dropdownArrangements.appendChild(option);

                }
            }

        });
}

initPage();