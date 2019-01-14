var app = new function () {
    var result;
    var countries = [];
   
    var mode = "";
    var updateidx;

    this.FetchAll = function () {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                result = xhr.responseText;
                alert(result);
                console.log(result);
                countries = JSON.parse(result);
            }
        }
        xhr.open('GET', '/country', true);
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

    };

    this.editCountry = function (countryid) {

        mode = "edit";
        document.getElementById('btn').innerHTML = "Update";
        for (let index = 0; index < countries.length; index++) {

            if (countries[index].countryid == countryid) {

                updateidx = index;
                document.getElementById("countryid").value = countries[index].countryid;
                document.getElementById("countryname").value = countries[index].countryname;
                document.getElementById('countryid').readOnly = true;
                console.log(mode);

            }

        }
    }





    this.deleteCountry = function (countryid) {


        console.log("delete called " + countryid);
        $.ajax({

            url: '/country/?countryid=' + countryid,
            type: 'DELETE',
            
        });
    }




    this.saveorupdate = function () {

        alert(countryid);
        // var countryid = document.getElementById("countryid").value;
        // var countryname = document.getElementById('countryname').value;


        if (mode == "") {

            alert("welcome to save");

            var countryid = $("#countryid").val();
            alert("countryid");
            var countryname = $('#countryname').val();
            alert("countryname");
           var detail = { "countryid": countryid, "countryname": countryname };
           
            var country = JSON.stringify(detail);
            $.ajax({
                
                url: '/country/',
                datatype: "json",
                data:country,
                
                type: 'POST',
                success: function (data) {

                    $('#countryid').val(data);
                    alert(data);
                },
                error: function () {
                    alert('Error');
                   }

            });
        
            this.FetchAll();
      
        }
        else {

            alert("welcome to Update");

            var countryid = $("#countryid").val();
            alert(countryid);

            var countryname = $('#countryname').val();
            alert(countryname);
            var detail = { "countryid": countryid, "countryname": countryname };
           
            var country = JSON.stringify(detail);
            $.ajax({

                url: '/country/',
                datatype: "json",
                data:country,
                type: 'PUT',
                
                
                success: function (data) {

                    $('countryid').val(data);
                    alert(data);
                },
                error: function () {
                    alert('Error');
                   }

                
            });
            this.FetchAll();
           

            // countries.splice(updateidx, 1, updatecountry);
            document.getElementById('btn').innerHTML = "Add";

        }
        document.getElementById("countryid").value = "";
        document.getElementById("countryname").value = "";
        this.getAllCountries();
    }
    this.FetchAll();

};