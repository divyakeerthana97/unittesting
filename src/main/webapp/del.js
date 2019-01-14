var app = new function () {
    var result;
    var a = [];
    this.FetchAll = function () {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function () {
            if (xhr.readyState == XMLHttpRequest.DONE) {
                result = xhr.responseText;
                console.log(result);
                a = JSON.parse(result);
            }
        }
        xhr.open('GET', '/country', true);
        xhr.send(null);
    }
 
    this.printtable = function () {
        console.log(a);
        this.el = document.getElementById("countries");
        var table_body = '<table border="1" id="example"><thead><tr><th>countryid</th><th>countryname</th></tr></thead><tbody>';
        for (var i in a) {
            table_body += '<tr>';
            table_body += '<td>';
            table_body += a[i].countryid;
            table_body += '</td>';
            table_body += '<td>';
            table_body += a[i].countryname;
            table_body += '</td>';
            table_body += '</tr>';
        }
        this.el.innerHTML = table_body;
    }
 }