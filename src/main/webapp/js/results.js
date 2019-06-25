function initPage() {
    document.getElementById("titel").innerHTML += sessionStorage.getItem('sessionID');
    getResults();
}


function getResults() {
    var fetchoptionsGet = { method: 'GET', headers : { 'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")} };
    var table = document.getElementById("allResultsTable");
    var sessionCode = sessionStorage.getItem('sessionID');

    fetch("gamechane/result/" + sessionCode, fetchoptionsGet) // haal alle sessions op
        .then(response => response.json())
        .then(function (myJson) {

            for (const result of myJson) {
                var tr = document.createElement("tr");
                var td = document.createElement("td");
                var td2 = document.createElement("td");
               

                td.innerHTML = result.name

                tr.appendChild(td);
                tr.appendChild(td2);

                var minigamelist = "";
               
                for (const subresult of result.result) {
                    
                    td2.innerHTML += subresult.minigamename + "\n";
                    var starttime = new Date(subresult.starttime);
                    var endtime = new Date(subresult.endtime);
                    var delta = Math.abs(endtime - starttime) / 1000;
                    var days = Math.floor(delta / 86400);
                    delta -= days * 86400;
                    var hours = Math.floor(delta / 3600) % 24;
                    delta -= hours * 3600;
                    var minutes = Math.floor(delta / 60) % 60;
                    delta -= minutes * 60;
                    var seconds = delta % 60;  // in theory the modulus is not required

                    if(seconds < 10){
                        seconds = "0" + seconds;
                    }

                    if(minutes < 10){
                        minutes = "0" + minutes;
                    }

                    if(hours < 10){
                        hours = "0" + hours;
                    }
                    var timeDifferenceString = hours + ":" + minutes + ":" + seconds;

                    minigamelist += "<li><strong>" + subresult.minigamename + "</strong> / " + timeDifferenceString;
                    td2.innerHTML = minigamelist;
                    console.log(minutes + ":" + seconds);

                }

                table.appendChild(tr);
            }
        });
}

function timeDifference(timestamp1, timestamp2) {
    var difference = timestamp1 - timestamp2;
    var daysDifference = Math.floor(difference / 1000 / 60 / 60 / 24);

    return daysDifference;
}

initPage();
