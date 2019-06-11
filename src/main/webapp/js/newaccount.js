function initPage(){
    createAccount();
}

function createAccount(){
    document.getElementById("post").addEventListener("click", function () {
        var formData = new FormData(document.getElementById("newAccountForm")); //pak de data uit de div en maak daar formdata van
        var encData = new URLSearchParams(formData.entries()); // zet de formdata om 

        var fetchoptionsPost = {
            method: 'POST',
            body: encData
        };

        fetch("gamechane/account", fetchoptionsPost) //post de afgeronde taak naar de database
            .then(function (response) {
                if (response.ok) {
                    window.location.href = 'docent.html'; // als het goed is gegaan keer terug naar de homepage
                } else {
                    alert("Er is iets mis gegaan");
                }
            });



    });
}
initPage();