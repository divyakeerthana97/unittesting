## get all countries

```
var app = new function () {
    var result;
    var countries = [];
    // var countries = [{ "countryname": "India", "countryid": 1 },
    // { "countryname": "France", "countryid": 2 },
    // { "countryname": "Germany", "countryid": 3 },
    // { "countryname": "England", "countryid": 4 },
    // { "countryname": "Spain", "countryid": 5 },
    // { "countryname": "USA", "countryid": 6 }];

    var mode = "";
    var updateidx;

    this.FetchAll = function () {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                result = xhr.responseText;
                console.log(result);
                countries = JSON.parse(result);
            }
        }
        xhr.open('GET', '/hello', true);
        xhr.send(null);
        // console.log(countries);
    }

    this.getAllCountries = function () {
        console.log(countries);
        this.el = document.getElementById("countries");
        var table_body = '<table border="1" id="example"><thead><tr><th>countryid</th><th>countryname</th></tr></thead><tbody>';
        for (var i in countries) {
            table_body += '<tr>';
            table_body += '<td>';
            table_body += countries[i].countryid;
            table_body += '</td>';
            table_body += '<td>';
            table_body += countries[i].countryname;
            table_body += '</td>';
            table_body += '<td><button onclick="app.editCountry(' + countries[i].countryid + ')">Edit</button></td>';
            table_body += '<td><button onclick="app.deleteCountry(' + countries[i].countryid + ')">Delete</button></td>';
            table_body += '</tr>';
        }
        this.el.innerHTML = table_body;

        // this.el = document.getElementById('countries');
        // //console.log("getAllCountries");
        // var data = '';
        // if (countries.length > 0) {

        //     for (i = 0; i < countries.length; i++) {

        //         data += '<tr>';
        //         data += '<tr>';
        //         data += '<td>' + countries[i].countryid + '</td>';
        //         data += '<td>' + countries[i].countryname + '</td>';
        //         data += '<td><button onclick="app.editCountry(' + countries[i].countryid + ')">Edit</button></td>';
        //         data += '<td><button onclick="app.deleteCountry(' + countries[i].countryid + ')">Delete</button></td>';
        //         data += '</tr>';

        //     }
        // }

        // this.el.innerHTML = data;
    };


    this.insertCountry = function () {
        //console.log("insertCountry");
    }

    this.getCountry = function (countryid) {
        //console.log("getCountry");
    }

    this.editCountry = function (countryid) {
        //console.log("editCountry " + countryid);
        mode = "edit";
        document.getElementById('btn').innerHTML = "Update";
        for (let index = 0; index < countries.length; index++) {
            // const element = array[index];
            //console.log(countries[index].countryid);
            if (countries[index].countryid == countryid) {
                //console.log(countries[index].countryid);
                updateidx = index;
                document.getElementById("countryid").value = countries[index].countryid;
                document.getElementById("countryname").value = countries[index].countryname;
                document.getElementById('countryid').readOnly = true;

            }

        }
    }

    this.deleteCountry = function (countryid) {

        //console.log("delete called " + countryid);
        //  var countryid = document.getElementById("countryid").value;
        // var countryname = document.getElementById('countryname').value;
        // countries.pop();
        //console.log("tobedeleted " + countryid);

        for (let index = 0; index < countries.length; index++) {
            // const element = array[index];
            //console.log(countries[index].countryid);
            if (countries[index].countryid == countryid) {
                //console.log(countries[index]);
                countries.splice(index, 1);
            }

        }

        // countries.splice(countries.indexOf(countryid), 1);
        //console.log(countries);
        this.getAllCountries();

    }

    this.saveorupdate = function (countryid) {
        //console.log("saveorupdate");
        var countryid = document.getElementById("countryid").value;
        var countryname = document.getElementById('countryname').value;
        //console.log(countryid + "-->" + countryname);
        if (mode == "") {


            var newcountry = { "countryid": countryid, "countryname": countryname };
            //console.log(newcountry);
            // .push({"01": nieto.label, "02": nieto.value});
            countries.push(newcountry);
            //console.log(JSON.stringify(countries));

        }
        else {
            //console.log("update called " + countryid);
            var updatecountry = { "countryid": countryid, "countryname": countryname };
            //console.log(updatecountry);
            // .push({"01": nieto.label, "02": nieto.value});
            // countries.push(updatecountry);
            //console.log("updateidx " + updateidx);
            countries.splice(updateidx, 1, updatecountry);
            document.getElementById('btn').innerHTML = "Add";

        }
        document.getElementById("countryid").value = "";
        document.getElementById("countryname").value = "";
        this.getAllCountries();
    }

};
```


```
var app = new function () {
    var countries = [{ "countryname": "India", "countryid": 1 },
    { "countryname": "France", "countryid": 2 },
    { "countryname": "Germany", "countryid": 3 },
    { "countryname": "England", "countryid": 4 },
    { "countryname": "Spain", "countryid": 5 },
    { "countryname": "USA", "countryid": 6 }];

    var mode = "";
    var updateidx;

    this.getAllCountries = function () {
        this.el = document.getElementById('countries');
        //console.log("getAllCountries");
        var data = '';
        if (countries.length > 0) {

            for (i = 0; i < countries.length; i++) {
                
                data += '<tr>';
                data += '<tr>';
                data += '<td>' + countries[i].countryid + '</td>';
                data += '<td>' + countries[i].countryname + '</td>';
                data += '<td><button onclick="app.editCountry(' + countries[i].countryid + ')">Edit</button></td>';
                data += '<td><button onclick="app.deleteCountry(' + countries[i].countryid + ')">Delete</button></td>';
                data += '</tr>';
               
            }
        }

        this.el.innerHTML = data;
    };


    this.insertCountry = function () {
        //console.log("insertCountry");
    }

    this.getCountry = function (countryid) {
        //console.log("getCountry");
    }

    this.editCountry = function (countryid) {
        //console.log("editCountry " + countryid);
        mode = "edit";
        document.getElementById('btn').innerHTML = "Update";
        for (let index = 0; index < countries.length; index++) {
            // const element = array[index];
            //console.log(countries[index].countryid);
            if (countries[index].countryid == countryid) {
                //console.log(countries[index].countryid);
                updateidx = index;
                document.getElementById("countryid").value = countries[index].countryid;
                document.getElementById("countryname").value = countries[index].countryname;
                document.getElementById('countryid').readOnly = true;
                
            }

        }
    }

    this.deleteCountry = function (countryid) {

        //console.log("delete called " + countryid);
        //  var countryid = document.getElementById("countryid").value;
        // var countryname = document.getElementById('countryname').value;
        // countries.pop();
        //console.log("tobedeleted " + countryid);

        for (let index = 0; index < countries.length; index++) {
            // const element = array[index];
            //console.log(countries[index].countryid);
            if (countries[index].countryid == countryid) {
                //console.log(countries[index]);
                countries.splice(index, 1);
            }

        }

        // countries.splice(countries.indexOf(countryid), 1);
        //console.log(countries);
        this.getAllCountries();

    }

    this.saveorupdate = function (countryid) {
        //console.log("saveorupdate");
        var countryid = document.getElementById("countryid").value;
        var countryname = document.getElementById('countryname').value;
        //console.log(countryid + "-->" + countryname);
        if (mode == "") {


            var newcountry = { "countryid": countryid, "countryname": countryname };
            //console.log(newcountry);
            // .push({"01": nieto.label, "02": nieto.value});
            countries.push(newcountry);
            //console.log(JSON.stringify(countries));

        }
        else {
            //console.log("update called " + countryid);
            var updatecountry = { "countryid": countryid, "countryname": countryname };
            //console.log(updatecountry);
            // .push({"01": nieto.label, "02": nieto.value});
            // countries.push(updatecountry);
            //console.log("updateidx " + updateidx);
            countries.splice(updateidx, 1, updatecountry);
            document.getElementById('btn').innerHTML = "Add";

        }
        document.getElementById("countryid").value = "";
        document.getElementById("countryname").value = "";
        this.getAllCountries();
    }

};
```










```
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="hello.js"></script>
</head>

<body>
        <form action="javascript:void(0);" method="POST" onsubmit="app.saveorupdate()">
        <label>Country id:</label>
        <input type="text" name="countryid" id="countryid">
        <br>
        <label>Country name: </label>
        <input type="text" name="countryname" id="countryname">
        <button type="submit">Add</button>
    </form>
    <hr>
    <p id="counter"></p>
    <table>
        <tr>
            <th>Name</th>
        </tr>
        <tbody id="countries">
        </tbody>
    </table>

    <button name="edit" onclick="editCountry(countryid)">Edit</button>
    <button name="delete" onclick="deleteCountry(countryid)">Delete</button>
</body>

</html>
```


```
var app = new function () {
    var countries = [{ "countryname": "India", "countryid": 1 },
    { "countryname": "France", "countryid": 2 },
    { "countryname": "Germany", "countryid": 3 },
    { "countryname": "England", "countryid": 4 },
    { "countryname": "Spain", "countryid": 5 },
    { "countryname": "USA", "countryid": 6 }];

    

    this.getAllCountries = function () {
        this.el = document.getElementById('countries');
        console.log("getAllCountries");
        var data = '';
        if (countries.length > 0) {
            for (i = 0; i < countries.length; i++) {
                data += '<tr>';
                data += '<td>' + countries[i].countryid + '</td>';
                data += '<td>' + countries[i].countryname + '</td>';
                data += '<td><button onclick="app.editCountry(' + countries[i].countryid + ')">Edit</button></td>';
                data += '<td><button onclick="app.deleteCountry(' + countries[i].countryid + ')">Delete</button></td>';
                data += '</tr>';
            }
        }

        this.el.innerHTML = data;
    };


    this.insertCountry = function () {
        console.log("insertCountry");
    }

    this.getCountry = function (countryid) {
        console.log("getCountry");
    }

    this.editCountry = function (countryid) {
        console.log("editCountry " + countryid);
    }

    this.deleteCountry = function (countryid) {
        console.log("deleteCountry " + countryid);
    }

    this.saveorupdate = function (countryid) {
        console.log("saveorupdate");
        var countryid = document.getElementById("countryid").value;
        var countryname = document.getElementById('countryname').value;
        console.log(countryid+"-->"+countryname);
        var newcountry = {"countryid": countryid, "countryname": countryname};
        console.log(newcountry);
        // .push({"01": nieto.label, "02": nieto.value});
        countries.push(newcountry);
        console.log(JSON.stringify(countries));
        document.getElementById("countryid").value="";
        document.getElementById("countryname").value="";
        this.getAllCountries();
    }
    
};
```

```
var app = new function () {
    var countries = [{ "countryname": "India", "countryid": 1 },
    { "countryname": "France", "countryid": 2 },
    { "countryname": "Germany", "countryid": 3 },
    { "countryname": "England", "countryid": 4 },
    { "countryname": "Spain", "countryid": 5 },
    { "countryname": "USA", "countryid": 6 }];

    var mode = "";
var updateidx;

    this.getAllCountries = function () {
        this.el = document.getElementById('countries');
        console.log("getAllCountries");
        var data = '';
        if (countries.length > 0) {
            for (i = 0; i < countries.length; i++) {
                data += '<tr>';
                data += '<td>' + countries[i].countryid + '</td>';
                data += '<td>' + countries[i].countryname + '</td>';
                data += '<td><button onclick="app.editCountry(' + countries[i].countryid + ')">Edit</button></td>';
                data += '<td><button onclick="app.deleteCountry(' + countries[i].countryid + ')">Delete</button></td>';
                data += '</tr>';
            }
        }

        this.el.innerHTML = data;
    };


    this.insertCountry = function () {
        console.log("insertCountry");
    }

    this.getCountry = function (countryid) {
        console.log("getCountry");
    }

    this.editCountry = function (countryid) {
        console.log("editCountry " + countryid);
        mode = "edit";
        for (let index = 0; index < countries.length; index++) {
            // const element = array[index];
            console.log(countries[index].countryid);
            if (countries[index].countryid == countryid) {
                console.log(countries[index].countryid);
                updateidx=index;
                document.getElementById("countryid").value = countries[index].countryid;
                document.getElementById("countryname").value = countries[index].countryname;
            }

        }
    }

    this.deleteCountry = function (countryid) {

        console.log("delete called " + countryid);
        //  var countryid = document.getElementById("countryid").value;
        // var countryname = document.getElementById('countryname').value;
        // countries.pop();
        console.log("tobedeleted " + countryid);

        for (let index = 0; index < countries.length; index++) {
            // const element = array[index];
            console.log(countries[index].countryid);
            if (countries[index].countryid == countryid) {
                console.log(countries[index]);
                countries.splice(index, 1);
            }

        }

        // countries.splice(countries.indexOf(countryid), 1);
        console.log(countries);
        this.getAllCountries();

    }

    this.saveorupdate = function (countryid) {
        console.log("saveorupdate");
            var countryid = document.getElementById("countryid").value;
            var countryname = document.getElementById('countryname').value;
            console.log(countryid + "-->" + countryname);
        if (mode == "") {

            
            var newcountry = { "countryid": countryid, "countryname": countryname };
            console.log(newcountry);
            // .push({"01": nieto.label, "02": nieto.value});
            countries.push(newcountry);
            console.log(JSON.stringify(countries));
            
        }
        else {
            console.log("update called "+countryid);
            var updatecountry = { "countryid": countryid, "countryname": countryname };
            console.log(updatecountry);
            // .push({"01": nieto.label, "02": nieto.value});
            // countries.push(updatecountry);
console.log("updateidx "+updateidx);
            countries.splice(updateidx,1,updatecountry);
        }
        document.getElementById("countryid").value = "";
            document.getElementById("countryname").value = "";
            this.getAllCountries();
    }

};
```