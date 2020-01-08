const ZIPCODEPAGE = document.querySelector("#content");
const ALLZIPCODES = document.querySelector("#zipcodePage");

function zipcodesToTable(jokes) {
    var tableContent = jokes.map(zip => "<tr><td>" + zip.city + "</td><td>" + zip.zipCode + "</td></tr>");

    tableContent.unshift("<table class=\"table\" border=\"1\"><tr><th>City</th><th>Zipcode</th></tr>");
    tableContent.push("</table>");
    return tableContent.join('');
};

ALLZIPCODES.onclick = function (e) {
    e.preventDefault();
    let url = "/course-assignment-2/api/person/zipcode/all";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                ZIPCODEPAGE.innerHTML = zipcodesToTable(data);
            });
};

