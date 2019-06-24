$(document).ready(function () {

    var fetchoptionsGet = {
        method: 'GET'
    };

    fetch("gamechane/cardset", fetchoptionsGet)
        .then(response => response.json())
        .then(function (myJson) {
            var table = document.getElementById("allCardsetsTable");

            for (var cardset of myJson) {

                var tr = document.createElement("tr");
                var tdTitle = document.createElement("td");
                var tdBackside = document.createElement("td");
                var tdDelete = document.createElement("td");
                var buttonDelete = document.createElement("button");

                console.log(myJson);
                console.log(cardset);
                console.log(cardset);

                tdTitle.innerHTML = cardset.name;
                if (cardset.allCards[0].backside.tekst !== null) {
                    // card backside has text
                    console.log("backside = text");
                    tdBackside.innerHTML = cardset.allCards[0].backside.tekst;
                } else if (cardset.allCards[0].backside.tekst === null && (cardset.allCards[0].backside.picture !== null || cardset.allCards[0].backside.picture !== "")) {
                    // card backside has image but no text
                    console.log("backside = image");
                    tdBackside.innerHTML = "<img src='" + cardset.allCards[0].backside.picture.url + "'>";
                } else {
                    // card backside has no text and no image
                    console.log("backside = null");
                    tdBackside.innerHTML = "Geen achterkant gevonden";
                }
                buttonDelete.innerHTML = "Verwijderen";

                buttonDelete.setAttribute("id", "buttonDelete");
                buttonDelete.setAttribute("data-id", cardset.id);
                buttonDelete.onclick = function () {
                    deleteArrangement(cardset.id)
                };

                tdDelete.appendChild(buttonDelete);

                tr.appendChild(tdTitle);
                tr.appendChild(tdBackside);
                tr.appendChild(tdDelete);

                table.appendChild(tr);
            }
        })
});

function deleteArrangement(id) {
    var xhr = new XMLHttpRequest();
    var url = "/todss/gamechane/cardset/delete/" + id;
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
