$(document).ready(function () {

    var fetchoptionsGet = {
        method: 'GET'
    };

    fetch("gamechane/arrangement/teacher/leraar1", fetchoptionsGet)
        .then(response => response.json())
        .then(function (myJson) {
            for (var object in myJson) {
                console.log(myJson[object]);
                console.log(myJson[object].name);

                var minigames = myJson[object].allMinigames;

                var arrangementBlock = "";

                arrangementBlock += "<div class='arrangementContainer'>";
                arrangementBlock += "<h2>" + myJson[object].name + "</h2>";
                arrangementBlock += "<p>Aantal spellen in dit arrangement: <span class='numberOfGames'>" + myJson[object].allMinigames.length + "</span></p>";
                arrangementBlock += "<ul class='minigamelist'>";

                for (minigame in minigames) {
                    if (minigame < 4) {
                        arrangementBlock += "<li><strong>" + minigames[minigame].name + "</strong> " + minigames[minigame].omschrijving;
                    } else if (minigames.length === 5) {
                        arrangementBlock += "<li><strong>" + minigames[minigame].name + "</strong> " + minigames[minigame].omschrijving;
                        break;
                    } else {
                        arrangementBlock += "<li>En nog " + (minigames.length - 4) + " andere spellen...</li>";
                        break;
                    }
                }

                arrangementBlock += "</ul>";
                arrangementBlock += "<p class='description'>" + myJson[object].description + "</p>";
                arrangementBlock += "<a href='/arrangement.html?id=" + myJson[object].id + "' class='btn btn-outline-primary'>Meer informatie</a>";
                arrangementBlock += "</div>";
                arrangementBlock += "<hr>";

                $("#arrangementList").append(arrangementBlock)
            }
        })
});
