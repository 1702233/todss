$(document).ready(function () {
    var yeardisplays = document.getElementsByClassName("yeardisplay");

    for (index in yeardisplays) {
        yeardisplays[index].innerText = new Date().getFullYear();
    }
});
