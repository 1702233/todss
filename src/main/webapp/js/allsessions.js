var sessionDataJSON = [];

function initPage() {
    getSessions();

}


function getSessions() {
    var ingelogdeDocent = sessionStorage.getItem('docent');
    var fetchoptionsGet = { method: 'GET' };
    var table = document.getElementById("allSessionsTable");
    var fetchoptionsDel = { method: 'DELETE' };

    fetch("gamechane/session/" + ingelogdeDocent, fetchoptionsGet) // haal alle sessions op
        .then(response => response.json())
        .then(function (myJson) {

            for (const session of myJson) {

                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var td5 = document.createElement("td");
                var td6 = document.createElement("td");

                var resultatenButton = document.createElement("button");
                resultatenButton.innerHTML = "Resultaten";
                var sluitenButton = document.createElement("button");
                sluitenButton.innerHTML = "Sluiten";

                sluitenButton.addEventListener("click", function () {



                    fetch("gamechane/session/" + td.innerHTML, fetchoptionsDel)
                        .then(function (response) {
                            if (response.ok) {
                                alert("Sessie is gesloten");
                                location.reload();
                                //window.location.href = 'docent.html'; // als het goed is gegaan keer terug naar de homepage
                            } else {
                                alert("Er is iets mis gegaan");
                            }
                        })
                });

                td.innerHTML = session.code;
                td2.innerHTML = session.opmerking;
                td3.innerHTML = session.arrangement.name
                td4.innerHTML = session.arrangement.description
                td5.appendChild(resultatenButton);
                td6.appendChild(sluitenButton);

                tr.appendChild(td);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);
                tr.appendChild(td6);

                table.appendChild(tr);

                console.log(session.code + session.opmerking + session.arrangement.name + session.arrangement.description);



            }


        });
}


initPage();