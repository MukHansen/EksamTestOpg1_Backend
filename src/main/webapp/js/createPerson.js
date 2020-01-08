//constant dom elements we're working on
const CREATEPERSONPAGE = document.querySelector("#createPersonPage");
const CREATEPERSONCONTENT = document.querySelector("#content");



function makeInputFields() {
    return "<form> New Person Information: <br> \n\
    First Name: <input type=\"text\" id=\"firstName\"> <br><br> \n\
    Last Name: <input type=\"text\" id=\"lastName\"> <br><br> \n\
    Email: <input type=\"text\" id=\"email\"> <br><br> \n\
    Street: <input type=\"text\" id=\"street\"> <br><br> \n\
    Zipcode: <input type=\"number\" id=\"zipcode\"> <br><br> \n\
    <input type=\"submit\" id=\"createPersonButton\" value=\"Create Person\">\n\
    </form><br><br>\n\
    <div id=\"confirmation\"></div>"
            ;
}

function makeCreatePersonPageContent(e) {
    e.preventDefault();
    CREATEPERSONCONTENT.innerHTML = makeInputFields();
    createPersonButton = document.querySelector("#createPersonButton");
    createPersonButton.addEventListener("click", createPerson);
}

function createPerson(e) {
    e.preventDefault();
    let url = "/course-assignment-2/api/person/create";
    var firstName = document.querySelector("#firstName").value;
    var lastName = document.querySelector("#lastName").value;
    var email = document.querySelector("#email").value;
    var street = document.querySelector("#street").value;
    var zipcode = document.querySelector("#zipcode").value;
    if (zipcode.length !== 4) {
        alert("Zipcode length must be 4 digits");
        return;
    }
    let options = {
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "firstName": firstName,
            "lastName": lastName,
            "email": email,
            "street": street,
            "zipcode": zipcode
        })
    };


    fetch(url, options)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                document.querySelector("#confirmation").innerHTML = "The id of the created person is: " + data.personID;
                document.querySelector("#firstName").value = "";
                document.querySelector("#lastName").value = "";
                document.querySelector("#email").value = "";
                document.querySelector("#street").value = "";
                document.querySelector("#zipcode").value = "";
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });
}

//Eventlisteners

CREATEPERSONPAGE.addEventListener("click", makeCreatePersonPageContent);


