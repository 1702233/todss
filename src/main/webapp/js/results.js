function initPage(){
    document.getElementById("titel").innerHTML += sessionStorage.getItem('sessionID');
    getResults();
}


function getResults() {
    var fetchoptionsGet = { method: 'GET' };
    var table = document.getElementById("allResultsTable");
    var sessionCode = sessionStorage.getItem('sessionID');

    fetch("gamechane/result/" + sessionCode, fetchoptionsGet) // haal alle sessions op
        .then(response => response.json())
        .then(function (myJson) {

            for (const result of myJson) {

                console.log(myJson);

            }


        });
}

initPage();
