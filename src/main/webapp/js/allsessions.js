var sessionDataJSON;

function initPage() {
    getSessions();
    createTable();

}


function getSessions() {
    var ingelogdeDocent = sessionStorage.getItem('docent');
    var fetchoptionsGet = { method: 'GET' };

    fetch("gamechane/session/" + ingelogdeDocent, fetchoptionsGet) // haal alle sessions op
        .then(response => response.json())
        .then(function (myJson) {


            if (myJson.length == 0) { 

                return false;

            } else {

                sessionDataJSON = myJson;

                

                return true;
            }

        });
}

function createTable(){
    var table = document.getElementById("arrangementTable");
    if(getSessions){

        for (const session of sessionDataJSON) { 

            var tr = document.createElement("tr");
            var td = document.createElement("td");
            var td2 = document.createElement("td");
            var td3 = document.createElement("td");
            var td4 = document.createElement("td");
            var td5 = document.createElement("td");
            var td6 = document.createElement("td");

            var resultatenButton = document.createElement("button");
            resultaten.innerHTML = "Resultaten";
            var sluitenButton = document.createElement("button");
            sluitenButton.innerHTML = "Sluiten";

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
    }

}

initPage();