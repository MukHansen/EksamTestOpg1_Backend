const GETPERSONPAGE = document.querySelector("#getPersonPageById");
const GETPERSONIDCONTENT = document.querySelector("#content");


function makeInputFieldsPersonId() {
    return "<form> get person: <br> \n\
    id: <input type=\"text\" id=\"personId\"> <br><br> \n\
    <input type=\"submit\" id=\"getPersonButton\" value=\"get person\"></form>\n\
    <div id=\"idConfirmation\"> </div>    "
            ;
}
function makeGetPersonPageContent() {
    GETPERSONIDCONTENT.innerHTML = makeInputFieldsPersonId();
    getPersonButton = document.querySelector("#getPersonButton");
    getPersonButton.addEventListener("click", getPerson);
}
function getPerson(e) {
    e.preventDefault();
    var id = document.querySelector("#personId").value;
    let url = "/course-assignment-2/api/person/" + id;
    fetch(url)
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                document.querySelector("#idConfirmation").innerHTML =
                        "<p>Here is your requested person: ID: " + id + " : " + data.firstName + " " + data.lastName + "</p>";
            });
}
;
GETPERSONPAGE.addEventListener("click", makeGetPersonPageContent);

