/**
 * Making table of all persons.
 */
function getPersonByPhone(ev) {
    ev.preventDefault();
    var phoneAPI = "/course-assignment-2/api/person/phone/" + $(".container input").val();
    console.log(phoneAPI);
    fetch(phoneAPI)
            .then(res => res.json())
            .then(data => {
                console.log(data);
                var listData = [];
                listData.push(data);
                list2Table(listData,"#content");
//                
//                $("#content").html(
//                        "personID: " + data.personID
//                        + "<br>Email: " + data.email
//                        + "<br>Name: " + data.firstName + " " + data.lastName
//                        + "<br>Hobbies: " + data.hobbies);
//                data.hobbies.forEach($("#content").append(item.name));
//                data.hobbies.map($("#content").append(this));


            });
}

/**
 * adds Modal overlay into body of index.html
 */
//function insertModal() {
//    $.get("html/modalPhoneNumber.html", {}, function (results) {
//        $("#content").append(results);
//    });
//}

//PersonByPhoneNumber button eventlistener and other DOM manipulations

//$("#getpersonByPhone").addEventListener("click", insertModal);


