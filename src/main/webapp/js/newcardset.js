(function ($) {

    var cardTemplate = "\n" +
        "            <div class=\"card-wrapper\">\n" +
        "\n" +
        "                <div class=\"row\">\n" +
        "\n" +
        "                    <div class=\"col-xs-12 col-sm-8 col-md-9 front-side\">\n" +
        "\n" +
        "                        <div class=\"input-group mb-3\">\n" +
        "                            <div class=\"input-group-prepend\">\n" +
        "                                <span class=\"input-group-text\" id=\"basic-addon1\">Tekst</span>\n" +
        "                            </div>\n" +
        "                            <input type=\"text\" class=\"form-control cardside-input front-side-text\" placeholder=\"Tekst\" aria-label=\"Tekst\"\n" +
        "                                   aria-describedby=\"basic-addon1\">\n" +
        "                        </div>\n" +
        "                        <div class=\"input-group mb-3\">\n" +
        "                            <div class=\"input-group-prepend\">\n" +
        "                                <span class=\"input-group-text\">Afbeelding-URL</span>\n" +
        "                            </div>\n" +
        "                            <input type=\"text\" class=\"form-control cardside-input front-side-image\" placeholder=\"Afbeelding-URL\" aria-label=\"Image\">\n" +
        "                        </div>" +
        "                    </div>\n" +
        "\n" +
        "                    <div class=\"btn-group-vertical col-xs-12 col-sm-4 col-md-3\" role=\"group\" style=\"border-left: 1px solid rgba(0, 0, 0, 0.1)\" aria-label=\"Basic example\">\n" +
        "                            <button type=\"button\" class=\"btn btn-outline-danger delete-card\">Verwijderen</button>" +
        "                    </div>\n" +
        "                    \n" +
        "                </div>\n" +
        "\n" +
        "                <hr>\n" +
        "\n" +
        "            </div>";

    initPage(cardTemplate);

})(jQuery);

function initPage(cardTemplate) {
    $(".add-card").on("click", function () {
        addCard(cardTemplate)
    });

    $(".cardside-input").on("change", function () {
        disableInput($(this))
    });

    console.log("test");
    $("#saveCardset").on("click", function () {
        console.log("Save cardset");

        var obj = {};

        obj.title = document.getElementById("cardset-name").value;

        obj.teacherName = sessionStorage.getItem("docent");

        obj.backside = {
            "text": document.getElementById("backside-text").value,
            "image": document.getElementById("backside-image").value
        };
        obj.frontside = [];

        var frontsides = document.getElementsByClassName("front-side");
        console.log(frontsides.length);

        var frontsidetext = "";
        var frontsideimage = "";

        for (var i = 0; i < frontsides.length; i++) {
            frontsidetext = frontsides[i].getElementsByClassName("front-side-text")[0].value;
            frontsideimage = frontsides[i].getElementsByClassName("front-side-image")[0].value;

            obj.frontside.push({
                "text": frontsidetext,
                "image": frontsideimage
            });
        }

        console.log(obj);

        var xhr = new XMLHttpRequest();
        var url = "gamechane/cardset";
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.setRequestHeader("Authorization", "Bearer " + window.sessionStorage.getItem("myJWT"));
        xhr.onreadystatechange = function () {
            console.log(xhr.readyState);
            console.log(xhr.status === 200);
            if (xhr.readyState === 4 && xhr.status === 200) {
                console.log("testje");
                var response = JSON.parse(xhr.responseText);
                console.log(response);
                if (response) {
                    greenAlert("De nieuwe kaartset is succesvol opgeslagen!");
                } else {
                    redAlert("De nieuwe kaartenset kon niet worden opgelsagen... ");
                }
                return;
            }
        };
        var data = JSON.stringify(obj);
        xhr.send(data);
    });
}

function greenAlert(message, time) {
    console.log("greenalert");
    customAlert("greenalert", message, time);
}

function redAlert(message, time) {
    customAlert("redalert", message, time);
}

function customAlert(alert, message, time = 5) {
    console.log("customalert");
    var greenAlert = document.getElementById(alert);
    greenAlert.classList.add("active");
    greenAlert.innerText = message;

    if (time !== "infinity") {
        setTimeout(function() {
            greenAlert.classList.remove("active")
        }, time * 1000);
    }
}

function addCard(cardTemplate) {
    console.log("voeg kaart toe");

    var newCard = $(".card-master").append(cardTemplate);

    // var newCard = $(this).parents(".card-wrapper").last().after(cardTemplate);

    newCard.find(".delete-card").on("click", function () {
        if ($(this).parents(".card-wrapper").parent().find(".card-wrapper").length > 1) {
            console.log("verwijder kaart");
            $(this).parents(".card-wrapper").remove();
        }
    });

    $(".cardside-input").on("change", function () {
        disableInput($(this))
    });
}

function disableInput(input) {
    console.log(input.parent().parent().find(".cardside-input"));

    var currentElement = input[0];

    input.parent().parent().find(".cardside-input").each(function (key, element) {
        if (element !== currentElement) {
            console.log(input.val());
            if (input.val() === "") {
                element.disabled = false;
            } else {
                element.value = "";
                element.disabled = true;
            }
        }
    });
}