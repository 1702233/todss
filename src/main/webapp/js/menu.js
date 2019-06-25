$(document).ready(function () {
    var xhr = new XMLHttpRequest();
    var url = "gamechane/menu";
    xhr.open("GET", url);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.setRequestHeader("Authorization", "Bearer " + window.sessionStorage.getItem("myJWT"));
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var json = JSON.parse(xhr.responseText);
            createMenu(json.allPages);
        }
    };
    xhr.send();
});

function createMenu(allPages) {
    console.log(allPages);
    for (var index in allPages) {
        console.log(allPages[index]);
        var button = allPages[index];
        createButton(button.title, button.url, button.subPages);
    }
}

function createButton(title, url, subPages) {
    var hasSubpages = typeof subPages !== "undefined" && subPages.length > 0;

    var li = document.createElement("li");
    li.classList.add("nav-item");
    if (hasSubpages) {
        li.classList.add("dropdown");
    }
    if (/*is active*/ false) {
        li.classList.add("active");
    }

    var a = document.createElement("a");
    a.classList.add("nav-link");
    if (hasSubpages) {
        a.classList.add("dropdown-toggle");
    }
    a.href = url === null ? "#" : url;
    a.innerText = title;

    li.appendChild(a);

    if (hasSubpages) {
        console.log("Creating subPages for " + title);
        a.setAttribute("role", "button");
        a.setAttribute("data-toggle", "dropdown");
        a.setAttribute("aria-haspopup", "true");
        a.setAttribute("aria-expanded", "false");

        var dropdown = document.createElement("div");
        dropdown.classList.add("dropdown-menu");

        for (index in subPages) {
            var subpage = subPages[index];

            var dropdownLink = document.createElement("a");
            dropdownLink.classList.add("dropdown-item");
            dropdownLink.href = subpage.url;
            dropdownLink.innerText = subpage.title;
            dropdown.appendChild(dropdownLink);
        }

        li.appendChild(dropdown);
    }

    console.log("HOI");
    document.getElementById("menu").appendChild(li);
}
