/**
 * jsonList2Table converts a json-list into a html table
 * and places it at the html tag position
 * arguments: a jsonlist and the tag identifier
 */
function list2Table(jsonList, htmlTag) {
    console.log("iiuj");
    //Create a HTML Table element.
    var table = document.createElement("TABLE");

    // insert table class for bootstrap goodies
    table.setAttribute("class", "table table-hover table-condensed");

    //insert empty row.
    var row = table.insertRow(-1);

    //insert header cell elements
    // it uses the first entry to get object properties
    Object.keys(jsonList[0]).forEach(function (item) {
//        console.log("item: " + item);
        var headerCell = document.createElement("TH");

        //create id for header. ID is the same as the content
        headerCell.setAttribute("id", item);
        var text = document.createTextNode(item);
        headerCell.appendChild(text);
        row.appendChild(headerCell);
    });

    //Add the data rows.
    jsonList.forEach(function (listItem, index) {
//        console.log("listItem.carID: " + listItem.carID);

        row = table.insertRow(-1);
        Object.keys(listItem).forEach(function (parts) {
//            console.log("listItem[parts]: " + listItem[parts]);
            var cell = row.insertCell(-1);
            var text = document.createTextNode(listItem[parts]);
            cell.appendChild(text);
        });
    });

    var divTable = document.querySelector(htmlTag);
    if (divTable.contains(document.querySelector("table"))) {
        document.querySelector("table").remove();
        divTable.appendChild(table);
    } else {
        divTable.appendChild(table);
    }
};

/**
 * 
 */
function linking(htmlTag) {
    var tableBody = document.querySelectorAll(htmlTag + " tbody tr");          // selecting all rows in table
    var firstRow = document.querySelector(htmlTag + " tbody :first-child");    // selecting first row in table

    tableBody.forEach(element => {                                            // adding links to new tablecolumn but not the first row  
        if (firstRow !== element) {

            //first column of row = id
            var rowID = element.querySelector(":first-Child").innerHTML;
            // console.log("rowID", rowID);

            //creating delete link
            var cell = element.insertCell(-1);
            var deleteLinkNode = document.createElement("a");
            deleteLinkNode.setAttribute("href", "#");
            deleteLinkNode.setAttribute("class", "btndelete");
            deleteLinkNode.setAttribute("id", rowID);
            var deleteText = document.createTextNode("delete");
            deleteLinkNode.appendChild(deleteText);
            cell.appendChild(deleteLinkNode);

            //creating edit link
            cell = element.insertCell(-1);
            var editLinkNode = document.createElement("a");
            editLinkNode.setAttribute("href", "#");
            editLinkNode.setAttribute("class", "btnedit");
            editLinkNode.setAttribute("id", rowID);
            var editText = document.createTextNode("edit");
            editLinkNode.appendChild(editText);
            cell.appendChild(editLinkNode);
        }
    });

    //adding eventlistener to table body
    document.querySelector(htmlTag).addEventListener("click", tableEvents);
};


/**
 * 
 * @param {*} e 
 */
function tableEvents(e) {
    console.log("e.target", e.target);
    var method = e.target.parentElement.querySelector(":first-child").innerHTML;
    var id = e.target.parentElement.querySelector(":link").id;

    console.log("method", method);
    console.log("id", id);

    if (method === "delete") {
        if (confirm('Are you sure you want to delete?')) {
            // deleteFunc(id);
            // getFunc();
            console.log("OK");
        } else {
            console.log("Cancel");
            return;
        }
    }
    if (method === "edit") {
        getFunc();
    }
};
