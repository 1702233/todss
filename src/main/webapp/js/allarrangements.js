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

                console.log(myJson);
                console.log(arrangement);
                console.log(arrangement);

                tdTitle.innerHTML = arrangement.name;

                var minigames = arrangement.allMinigames;
                var minigameList = "";
                for (var minigame in minigames) {
                    if (minigame < 4) {
                        minigameList += "<li><strong>" + minigames[minigame].name + "</strong> " + minigames[minigame].omschrijving;
                    } else if (minigames.length === 5) {
                        minigameList += "<li><strong>" + minigames[minigame].name + "</strong> " + minigames[minigame].omschrijving;
                        break;
                    } else {
                        minigameList += "<li>En nog " + (minigames.length - 4) + " andere spellen...</li>";
                        break;
                    }
                }
                tdGames.innerHTML = minigameList;

                tdDescription.innerHTML = arrangement.description;
                buttonDelete.innerHTML = "Verwijderen";

                buttonDelete.setAttribute("id", "buttonDelete");
                buttonDelete.setAttribute("data-id", arrangement.id);
                buttonDelete.onclick = function() { deleteArrangement(arrangement.id) };

                tdDelete.appendChild(buttonDelete);

                tr.appendChild(tdTitle);
                tr.appendChild(tdGames);
                tr.appendChild(tdDescription);
                tr.appendChild(tdDelete);

                table.appendChild(tr);
            }
        })
});

function deleteArrangement(id) {
    var xhr = new XMLHttpRequest();
    var url = "http://localhost:8080/project5_war_exploded/gamechane/arrangement/delete/" +  id;
    xhr.open("DELETE", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            console.log(json.email + ", " + json.password);
        }
    };
    xhr.send();
    window.location.reload();
}
