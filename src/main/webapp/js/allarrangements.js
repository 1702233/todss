var arrangementID;

$(document).ready(function () {

    var fetchoptionsGet = {
        method: 'GET'
    };

    fetch("gamechane/arrangement/teacher/leraar1", fetchoptionsGet)
        .then(response => response.json())
        .then(function (myJson) {
            var table = document.getElementById("allArrangementsTable");

            for (var arrangement of myJson) {

                var tr = document.createElement("tr");
                var tdTitle = document.createElement("td");
                var tdGames = document.createElement("td");
                var tdDescription = document.createElement("td");
                var tdDelete = document.createElement("td");
                var buttonDelete = document.createElement("button");

                tdTitle.innerHTML = arrangement.name;

                var minigames = arrangement.allMinigames;
                var minigameList = "";
                for (var minigame in minigames) {
                    minigameList += "<li><strong>" + minigames[minigame].name + "</strong> " + minigames[minigame].omschrijving;
                }
                tdGames.innerHTML = minigameList;

                tdDescription.innerHTML = arrangement.description;
                buttonDelete.innerHTML = "Verwijderen";

                buttonDelete.setAttribute("id", "buttonDelete");
                buttonDelete.setAttribute("data-id", arrangement.id);

                buttonDelete.setAttribute("data-toggle", "modal");
                buttonDelete.setAttribute("data-target", "#myModal");
                buttonDelete.addEventListener("click", function () {
                    arrangementID = arrangement.id;
                });

                tdDelete.appendChild(buttonDelete);

                tr.appendChild(tdTitle);
                tr.appendChild(tdGames);
                tr.appendChild(tdDescription);
                tr.appendChild(tdDelete);

                table.appendChild(tr);
            }

            var jaButton = document.getElementById("jaButton");
            jaButton.addEventListener("click", function () {
                deleteArrangement(arrangementID)
            });
        })
});

function deleteArrangement(id) {
    var xhr = new XMLHttpRequest();
    var url = "/todss/gamechane/arrangement/delete/" + id;
    xhr.open("DELETE", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
        }
    };
    xhr.send();
    window.location.reload();
}
