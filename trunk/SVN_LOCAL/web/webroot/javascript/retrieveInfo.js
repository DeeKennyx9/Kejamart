function retrieveInfo(){
        document.getElementById('resultx').innerHTML =""; 

	var searchText = document.getElementById("search").value;
	url = "adminmenu.html";
	var jsonData = {
		"title" : searchText
	};
	JSONRequest.post(url, jsonData, 
    	function (requestNumber, value, exception) {
        if (value) {
            var str = '';
            document.getElementById("resultx").innerHTML ="<font color=#2F4F4F>Found: 1 record(s) matching your query</></font><p>";
            str += '<hr size="1">';
            str += '<font color=#2F4F4F>'+value.infoData.title+'\n </font>';
            str += '<font color=#2F4F4F>'+value.infoData.owner+ '\n </font>';
            str += '<font color=#2F4F4F>'+value.infoData.context+ '\n </font>';
            str += '<p><img src="images/genreportg.png" width="180px" height="35px" id="buttonfin" onclick="_resultclass.start(), retInfo()"></p>';
            document.getElementById("formarea").innerHTML = str;
        } else {
            document.getElementById("resultx").innerHTML ="<h4><font color=#2F4F4F>search results</font></h4><p>";
            document.getElementById("formarea").innerHTML ="<font color=#2F4F4F>No record with that set of keywords found...</font>";
        }
    }
); 
}
