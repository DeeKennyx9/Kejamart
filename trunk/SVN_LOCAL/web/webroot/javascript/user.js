var userUrl = "/franchise/profile.html";
var glblIdAdd;
var glblIdPI;
var glblOccupation;

function getUserProfileAnimator(){
 var xf = '<img src="images/progress/spinner.gif">';
 return xf;
}

function add_sub(el){
    if (el.checked)
    el.form.elements['type'].value+=el.value + ", ";
    else{
        var re=el.value;
        el.form.elements['type'].value=el.form.elements['type'].value.replace(re,'');
    }
}

function updateDOB(){
    var dateOfBirth = document.getElementById('dateOfBirth').innerHTML;
    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'updateDOB'};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'dateOfBirth', value : ''+ dateOfBirth +''};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
              
            } else {
                alert(exception);
            }
        }
    );   
}

function updateBasicInfo(){
    var basic_info = document.getElementById('basic_info').value;
    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'updateBasicInfo'};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'basic_info', value : ''+ basic_info +''};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
              
            } else {
                alert(exception);
            }
        }
    );   
}


function updatefullnames(){
    var fullnames = document.getElementById('fullnamesBox').value;
    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'updatefullnames'};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'fullnames', value : ''+ fullnames +''};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
              
            } else {
                alert(exception);
            }
        }
    );   
}

function updateInterests(interests){
    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'updateinterests'};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'interests', value : ''+ interests +''};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
              
            } else {
                alert(exception);
            }
        }
    );   
}

function updateGender(){
    var gender = document.getElementById('gender').value;
    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'updategender'};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'gender', value : ''+ gender +''};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
              
            } else {
                alert(exception);
            }
        }
    );   
}

//Address and phone

function createNewAdd(){
    //cancelOppAdd();
    document.getElementById("saveBtnAdd").style.display="block";
    document.getElementById("AddEditAreaAdd").style.display="block";
    document.getElementById('addressarea').value = "";
}
function cancelOppAdd(){
    document.getElementById("AddEditAreaAdd").style.display="none";
    document.getElementById('addressarea').value = "";
    document.getElementById("saveBtnAdd").style.display="none";
    document.getElementById("editBtnAdd").style.display="none";
    document.getElementById('adderrors').innerHTML ="";
    glblIdAdd="";
}

function AddressJSON(AddressInfo,AddressId, action){
    var JSONdata = {addressData : []};
    for(var i = 0; i<4; i++){
        if(i==0){
            JSONdata.addressData[i]={ 'key' : 'address_Info', value : ''+ AddressInfo +''};
        }
        else if(i==1){
            JSONdata.addressData[i]={ 'key' : 'action', value : ''+action+''};
        }
        else if(i==2){
            JSONdata.addressData[i]={ 'key' : 'addressId', value : ''+ AddressId +''};
        }
    }
        
    JSONRequest.post(urlAdd, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
                    
            recordStr+='<table width="100%" border="0" cellspacing="0" cellpadding="0">';
            if (value) {
                for(var i=0;i<value.listSize[0];i++){
                    var addstatId = value.addressData[0].addressId[i];
                    
                    recordStr+='<tr>'
                    recordStr+='<td>'
                    recordStr+='<span id="addresseditBox'+addstatId+'" style="float:left; cursor:pointer;" onclick=getSpecAdd("' + addstatId + '");><img style="float:right; cursor:pointer;" src="images/global/edit_pen.gif" /></a>';
                    recordStr+='<span style="cursor:pointer">'+value.addressData[0].address_Info[i]+'</span>';
                    recordStr+='</td>';
                    recordStr+='</tr>';
                }
                recordStr+='</table>'
                document.getElementById('resultsAdd').innerHTML = recordStr;
                
            } else {
                alert(exception);
            }
        }
    );  
}
function updateAddress(){
    var AddressInfo = document.getElementById('addressarea').value;
    var AddressId = glblIdAdd;
    if(AddressInfo=="" || AddressId=="" || AddressInfo==null || AddressId==null ){
        document.getElementById('adderrors').innerHTML = "Illegal Operation. Trying to save invalid data";
        return;
    }
    AddressJSON(AddressInfo,AddressId, "update");
    cancelOppAdd();
}
function getSpecAdd(geterId){
    //cancelOppAdd();
    
    if(geterId == "" || geterId == null ){
        document.getElementById('adderrors').innerHTML = "Illegal Operation. You have to select a status to edit";
        return;
    }
    var JSONdata = {addressData : []};
        for(var i = 0; i<3; i++){
            if(i==0){
                JSONdata.addressData[i]={ 'key' : 'action', value : 'getSpec'};
            }
            else if(i==2){
                JSONdata.addressData[i]={ 'key' : 'addressId', value : ''+ geterId +''};
            }
        }
        
    JSONRequest.post(urlAdd, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
                    glblIdAdd = value.addressData.addressId;
                    document.getElementById('addressarea').value = value.addressData.address_Info;
                    document.getElementById("editBtnAdd").style.display="block";
                    document.getElementById("AddEditAreaAdd").style.display="block";
            } else {
                alert(exception);
            }
        }
    );  
}
function addAddress(){
    var AddressInfo = document.getElementById('addressarea').value ;
    var AddressId = 0;
    if(AddressInfo=="" || AddressInfo==null ){
        document.getElementById('adderrors').innerHTML = "Illegal Operation. Trying to save invalid data";
        return;
    }
   AddressJSON(AddressInfo,AddressId, "add");
   cancelOppAdd();
}
function initloadAdd(){
    document.getElementById("saveBtnAdd").style.display="none";
    document.getElementById("AddEditAreaAdd").style.display="none";
    document.getElementById("editBtnAdd").style.display="none";
    getAllAddress();
}
function getAllAddress(){
    var AddressInfo = "" ;
    var AddressId = 0;
    AddressJSON(AddressInfo,AddressId, "getAll");
}

function deletAddress(){
    var AddressInfo = document.getElementById('addressarea').value ;
    var AddressId = glblIdAdd;
    if(AddressInfo=="" || AddressId=="" || AddressInfo==null || AddressId==null ){
        document.getElementById('adderrors').innerHTML = "Illegal Operation. Trying to save invalid data";
        return;
    }
    AddressJSON(AddressInfo,AddressId, "delete");
    cancelOppAdd();
}

//Occupation
function updatePersonalInfo(){

    var occupation = "";
    occupation = glblOccupation;

        
    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'occupation', value : ''+ occupation +''};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'updateOccupation'};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
                    glblOccupation = value.personalData[0].occupation[0];
                    document.getElementById('occupationDiv').innerHTML = value.personalData[0].occupation[0];
                    document.getElementById("saveBtnpi").innerHTML="<button class='itemBtn' style='text-decoration:none' onClick='addPersonalInfo();'>Update</button>";
                    document.getElementById("saveBtnpi").style.display="block";
                    initView();
            } else {
                alert(exception);
            }
        }
    );   
}

function addPersonalInfo(){
    var pInfoId = 0;
    var occupation = "";

    occupation = glblOccupation;

    
    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'occupation', value : ''+ occupation +''};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'updateOccupation'};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
                    glblOccupation = value.personalData[0].occupation[0];
                    document.getElementById('occupationDiv').innerHTML = value.personalData[0].occupation[0];
                    document.getElementById("saveBtnpi").style.display="block";
                    document.getElementById("saveBtnpi").innerHTML="<button class='itemBtn' onClick='addPersonalInfo();'>Update</button>";
                    initView();
            } else {
                alert(exception);
            }
        }
    );   
}

function getGetPInfo(){
    var occupation = "";

    var JSONdata = {userProfile : []};
        for(var i = 0; i<2; i++){
            if(i==0){
                JSONdata.userProfile[i]={ 'key' : 'occupation', value : ''+ occupation +''};
            }
            else if(i==1){
                JSONdata.userProfile[i]={ 'key' : 'action', value : 'getSpec'};
            }
        }
        
    JSONRequest.post(userUrl, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            if (value) {
	            glblOccupation = value.personalData[0].occupation[0];
	            document.getElementById('occupationDiv').innerHTML = value.personalData[0].occupation[0];
	            //document.getElementById("saveBtnpi").style.display="block";
	            //document.getElementById("saveBtnpi").innerHTML="<a href='javascript:;' style='text-decoration:none' onClick='addPersonalInfo();'>Update</a>";
            } else {
                alert(exception);
            }
        }
    );  
}
function initView(){
    document.getElementById("contOcc").style.display="none";
    document.getElementById("occothers").style.display="none";
    document.getElementById("occlist").style.display="none";
    document.getElementById("saveBtnpi").style.display="none";
}
function initLoadpi(){
    document.getElementById('occupationDiv').innerHTML = getUserProfileAnimator();
    initView();
    var t=1;
    getGetPInfo();
}
function pickOccupation(){
    document.getElementById("contOcc").style.display="block";
    document.getElementById("occothers").style.display="none";
    document.getElementById("occlist").style.display="block";
    document.getElementById("saveBtnpi").style.display="block";
    document.getElementById("saveBtnpi").innerHTML="<button class='itemBtn' style='text-decoration:none' onClick='addPersonalInfo();'>Update</button>";
}
function addOccupation(){
    if(document.getElementById('occupation').value=="Other" && document.getElementById('otherstxt').value==""){
        document.getElementById('errors').innerHTML ="Please spacify the other occupation to proceed";
        return;
    }else if(document.getElementById('occupation').value=="Other"){
        glblOccupation=document.frmPInfo.otherstxt.value;
        document.getElementById('occupationDiv').innerHTML = document.frmPInfo.otherstxt.value;
        document.getElementById('errors').innerHTML = "";
    }
    document.getElementById("contOcc").style.display="none";
    document.getElementById("occothers").style.display="none";
    document.getElementById("occlist").style.display="none";
}
function picked(){
    
    if(document.getElementById('occupation').value=="Other"){
        document.getElementById('occupationDiv').innerHTML = ""; 
        document.getElementById("contOcc").style.display="block";
        document.getElementById("saveBtnpi").innerHTML="<button class='itemBtn' style='text-decoration:none' onClick='addOccupation(); addPersonalInfo();'>Update</button>";
        document.getElementById("occothers").style.display="block";
        document.getElementById("occlist").style.display="block";
    }else{
        document.getElementById("contOcc").style.display="block";
        document.getElementById("occothers").style.display="none";
        document.getElementById("occlist").style.display="block";
        document.getElementById("saveBtnpi").innerHTML="<a href='javascript:;' style='text-decoration:none' onClick='addPersonalInfo();'>Update</a>";
        glblOccupation=document.frmPInfo.occupation.value;
        document.getElementById('occupationDiv').innerHTML = document.getElementById('occupation').value;
    }
}

//Interests
function loadAllInterests(){
    document.getElementById('intsd').innerHTML = getUserProfileAnimator();
    document.getElementById('intsduser').innerHTML = getUserProfileAnimator();
    var JSONdata = {interestData : []};
        for(var i = 0; i<1; i++){
            if(i==0){
                JSONdata.interestData[i]={ 
                    'key' : 'action', value : 'loadAllInterests'
                };
            }
        }
        
    JSONRequest.post(urlInterests, JSONdata, 
        function (requestNumber, value, exception) {
            var recordStr="";
            var recordStrUser = "";
            if (value) {
                recordStr+="<b>Unselected</b><br/>";
                recordStr+='<select style="float:left; width:200px" id="Left" multiple="multiple" size="10">';
                if(value.interestsize == 0){
                        recordStr+= "<option></option>";
                }
                else{
                    for(var i=0; i<value.interestsize; i++){
                        recordStr+= "<option>"+value.interest.allinterests[i]+"</option>";
                    }
                    
                }
                recordStr+='</select>';
                document.getElementById('intsd').innerHTML = recordStr;
                
                
                recordStrUser+="<b>Selected</b><br/>";
                recordStrUser+='<select style="float:left; width:200px" id="Right" multiple="multiple" size="10">';
                for(var j=0; j<value.userinterestsSize; j++){
                    if(value.interest.userinterest[j]=="not yet set"){
                        recordStrUser+= "";
                    }
                    else if(value.interest.userinterest[j]== ""){
                        recordStrUser+= "";
                    }
                    else{
                        recordStrUser+= "<option>"+value.interest.userinterest[j]+"</option>";
                    }
                }
                recordStrUser+='</select>';
                document.getElementById('intsduser').innerHTML = recordStrUser;
                
            } else {
                document.getElementById('intsduser').innerHTML = "Error getting interests";
                document.getElementById('intsd').innerHTML = "Error getting interests";
            }
        }
    );
}

//Move interests from one select to another
function Move(inputControl){
  var left = document.getElementById("Left");
  var right = document.getElementById("Right");
  var from, to;
  var bAll = false;
  switch (inputControl.value){
      case '<<':
        bAll = true;
        // Fall through
      case '<':
        from = right; to = left;
        break;
      case '>>':
        bAll = true;
        // Fall through
      case '>':
        from = left; to = right;
        break;
      default:
        alert("Check your HTML!");
  }
  for (var i = from.length - 1; i >= 0; i--){
    var o = from.options[i];
    if (bAll || o.selected){
      from.remove(i);
      try{
        to.add(o, null);  // Standard method, fails in IE (6&7 at least)
      }
      catch (e){
        to.add(o); // IE only
      }
    }
  }
}

//save interests
function saveAllInterests(){
    var total="";
    for(var i=0; i < document.getElementById('Right').options.length; i++){
        total +=document.getElementById('Right').options[i].value + "<br/>";
    }

    interestsInfo.save(total);
    updateInterests(total); 
    
    return false;
}