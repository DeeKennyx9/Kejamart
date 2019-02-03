function retInfo(){
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
            document.getElementById("resultx").innerHTML ="<h4><font color=#2F4F4F></font></h4><p>";
            str += '<font color=#2F4F4F>LR Number: '+value.infoData.title+'\n </font><p>';
	    str += '<font color=#2F4F4F>Title Description: '+value.infoData.context+ '\n </font><p>';
	    str += '<font color=#2F4F4F>Owner: '+value.infoData.owner+ '\n </font><p>';
	    str += '<font color=#2F4F4F>Registrar: '+value.infoData.registrar+ '\n </font><p>';
	    str += '<hr size="1">';
	    str += '<font color=#2F4F4F>Parcel Number: '+value.infoData.parcelno+ '\n </font><p>';
	    str += '<font color=#2F4F4F>Area: '+value.infoData.area+ '\n </font><p>';
	    str += '<font color=#2F4F4F>Reg Section: '+value.infoData.regsection+ '\n </font><p>';
	    str += '<hr size="1">';
	    str += '<font color=#2F4F4F>Reg Mapsheet: '+value.infoData.regmapsheet+ '\n </font><p>';
	    str += '<font color=#2F4F4F>Edition: '+value.infoData.edition+ '\n </font><p>';
	    str += '<hr size="1">';
	    str += '<font color=#2F4F4F>ID / Passport Number: '+value.infoData.idpassport+ '\n </font><p>';
	    str += '<font color=#2F4F4F>Postal Address: '+value.infoData.address+ '\n </font><p>';    
	    str += '<p><img src="images/seal.png" width="180px" height="100px" id="buttonfin" onclick="_resultclass.start(), retInfo()"></p>';
            document.getElementById("formarea").innerHTML = str;
        } else {
            document.getElementById("resultx").innerHTML ="<h4><font color=#2F4F4F>search results</font></h4><p>";
            document.getElementById("formarea").innerHTML ="<font color=#2F4F4F>No record with that set of keywords found...</font>";
        }
    }
); 
}

