var sessionDataJSON = [];
var sessieCode = "";

function initPage() {
    getSessions();

}


function getSessions() {
    var ingelogdeDocent = sessionStorage.getItem('docent'); //username van ingelogde docent in sessionstorage
    var fetchoptionsGet = { method: 'GET',headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")} };
    var table = document.getElementById("allSessionsTable");
    var fetchoptionsDel = { method: 'DELETE', headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")} };

    fetch("gamechane/session/" + ingelogdeDocent, fetchoptionsGet) // haal alle sessions op
        .then(response => response.json())
        .then(function (myJson) {

            for (const session of myJson) { //voor elke sessie uit de binnenkomende JSON

                var tr = document.createElement("tr"); //maak een tabelrij aan
                var td = document.createElement("td"); //maak 6 cellen in de rij aan
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var td5 = document.createElement("td");
                var td6 = document.createElement("td");

                var resultatenButton = document.createElement("button");  // maak een resultatenkknop
                resultatenButton.innerHTML = "Resultaten";
                resultatenButton.addEventListener("click", function () {
                    sessionStorage.setItem('sessionID', session.code);
                    window.location.href = "results.html";
                });




                var sluitenButton = document.createElement("button");  //maak een sessie sluiten knop
                sluitenButton.setAttribute("data-toggle", "modal"); 
                sluitenButton.setAttribute("data-target", "#myModal"); //als de knop geklikt wordt open dan de modal

                sluitenButton.innerHTML = "Sluiten";
                sluitenButton.addEventListener("click", function () {
                    sessieCode = session.code;
                });



                td.innerHTML = session.code; // zet de inhoud van de cellen in de tabelrij
                td2.innerHTML = session.opmerking;
                td3.innerHTML = session.arrangement.name
                td4.innerHTML = session.arrangement.description
                td5.appendChild(resultatenButton);
                td6.appendChild(sluitenButton);

                tr.appendChild(td); //voeg de cellen toe aan de tabelrij
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);
                tr.appendChild(td6);

                table.appendChild(tr);  // voeg de rij toe aan de tabel
            }

            var jaButton = document.getElementById("jaButton");
            jaButton.addEventListener("click", function () {

            	fetch("gamechane/session/" + sessieCode, fetchoptionsDel) //verwijder de sessie als er op ja geklikt wordt.

                    .then(function (response) {
                        if (response.ok) {
                            location.reload();


                        } else {
                            alert("Er is iets mis gegaan");
                        }
                    })
            });
        });
}


initPage();