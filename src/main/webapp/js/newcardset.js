(function ($) {

    var cardTemplate = "\n" +
        "            <div class=\"card-wrapper\">\n" +
        "\n" +
        "                <div class=\"row\">\n" +
        "\n" +
        "                    <div class=\"col-xs-12 col-sm-8 col-md-9\">\n" +
        "\n" +
        "                        <div class=\"input-group mb-3\">\n" +
        "                            <div class=\"input-group-prepend\">\n" +
        "                                <span class=\"input-group-text\" id=\"basic-addon1\">Tekst</span>\n" +
        "                            </div>\n" +
        "                            <input type=\"text\" class=\"form-control\" placeholder=\"Username\" aria-label=\"Username\"\n" +
        "                                   aria-describedby=\"basic-addon1\">\n" +
        "                        </div>\n" +
        "                        <div class=\"input-group mb-3\">\n" +
        "                            <div class=\"input-group-prepend\">\n" +
        "                                <span class=\"input-group-text\" id=\"inputGroupFileAddon01\">Afbeelding</span>\n" +
        "                            </div>\n" +
        "                            <div class=\"custom-file\">\n" +
        "                                <input type=\"file\" class=\"custom-file-input\" id=\"inputGroupFile1\"\n" +
        "                                       aria-describedby=\"inputGroupFileAddon01\">\n" +
        "                                <label class=\"custom-file-label\" for=\"inputGroupFile1\">Choose file</label>\n" +
        "                            </div>\n" +
        "                        </div>\n" +
        "\n" +
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
        console.log("voeg kaart toe");

        var newCard = $(".card-master").append(cardTemplate);

        // var newCard = $(this).parents(".card-wrapper").last().after(cardTemplate);

        newCard.find(".delete-card").on("click", function () {
            if ($(this).parents(".card-wrapper").parent().find(".card-wrapper").length > 1) {
                console.log("verwijder kaart");
                $(this).parents(".card-wrapper").remove();
            }
        });
    });
}

// function initPage(cardTemplate) {
//
//     $(".add-card-above").on("click", function() {
//         console.log("voeg kaart boven toe"); // log
//         var newCard = $(this).parents(".card-wrapper").before(cardTemplate); // find the parent of this (the button pressed) named card-wrapper and add a card before
//         initCard(newCard, cardTemplate);
//     });
//
//     $(".add-card-below").on("click", function() {
//         console.log("voeg kaart onder toe");
//         var newCard = $(this).parents(".card-wrapper").after(cardTemplate);
//         initCard(newCard, cardTemplate);
//     });
//
//     $(".delete-card").on("click", function() {
//         console.log($(this).parents(".card-wrapper").parent().find(".card-wrapper").length);
//         if ($(this).parents(".card-wrapper").parent().find(".card-wrapper").length > 1) {
//             console.log("verwijder kaart");
//             var newCard = $(this).parents(".card-wrapper").remove();
//             initCard(newCard, cardTemplate);
//         }
//     });
// }
//
// function initCard(newCard, cardTemplate) {
//     $(".add-card-above").on("click", function() {
//         console.log("voeg kaart boven toe"); // log
//         var newCard = $(this).parents(".card-wrapper").before(cardTemplate); // find the parent of this (the button pressed) named card-wrapper and add a card before
//     });
//
//     $(".add-card-below").on("click", function() {
//         console.log("voeg kaart onder toe");
//         $(this).parents(".card-wrapper").after(cardTemplate);
//     });
//
//     $(".delete-card").on("click", function() {
//         console.log($(this).parents(".card-wrapper").parent().find(".card-wrapper").length);
//         if ($(this).parents(".card-wrapper").parent().find(".card-wrapper").length > 1) {
//             console.log("verwijder kaart");
//             $(this).parents(".card-wrapper").remove();
//         }
//     });
// }