$(document).ready(function () {

    var fetchoptionsGet = {
        method: 'GET',
        headers : { 
            'Authorization': 'Bearer ' +  window.sessionStorage.getItem("myJWT")}
    };

    fetch("gamechane/cardset", fetchoptionsGet)
        .then(response => response.json())
        .then(function (myJson) {
            var table = document.getElementById("allCardsetsTable");

            for (const cardset of myJson) {

                var tr = document.createElement("tr");
                var tdTitle = document.createElement("td");
                var tdBackside = document.createElement("td");
                var tdDelete = document.createElement("td");
                var buttonDelete = document.createElement("button");

                tdTitle.innerHTML = cardset.name;
                if (cardset.allCards[0].backside.tekst !== null) {
                    // card backside has text
                    tdBackside.innerHTML = cardset.allCards[0].backside.tekst;
                } else if (cardset.allCards[0].backside.tekst === null && (cardset.allCards[0].backside.picture !== null || cardset.allCards[0].backside.picture !== "")) {
                    // card backside has image but no text
                    tdBackside.innerHTML = "<img src='" + cardset.allCards[0].backside.picture.url + "'>";
                } else {
                    // card backside has no text and no image
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
    var url = "/gamechane/cardset/delete/" + id;
    xhr.open("DELETE", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Authorization", "Bearer " + window.sessionStorage.getItem("myJWT"));
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            console.log(json);
        }
    };
    xhr.send();
    // window.location.reload();
}
