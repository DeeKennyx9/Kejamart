
function dfname(){
    
    document.getElementById('dfname').innerHTML = '<div id ="responseholder" class="responseholder"><div id="error" class="error"></div><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">First Name</div><input type="text" class="inputclass" id="firstName"><div class="update" id="update" onclick="firstNamePost()">Update Changes</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';
    
    document.getElementById('dlname').innerHTML = '';
    document.getElementById('dpass').innerHTML = '';
    document.getElementById('dcat').innerHTML = '';
    document.getElementById('dmailing').innerHTML = '';    

}

function dlname(){
    
    document.getElementById('dlname').innerHTML =  '<div id ="responseholder" class="responseholder"><div id="error" class="error"></div><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Last Name</div><input type="text" class="inputclass" id="lastName"><div class="update" id="update" onclick="lastNamePost()">Update Changes</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';
      
    document.getElementById('dfname').innerHTML = '';
    document.getElementById('dpass').innerHTML = '';
    document.getElementById('dcat').innerHTML = '';
    document.getElementById('dmailing').innerHTML = '';    
    document.getElementById('dfname').innerHTML = ''; 
}

function dpass(){
    
    document.getElementById('dpass').innerHTML = '<div id ="responseholder" class="responseholder"><div id="error" class="error"></div><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Password</div><input type="text" class="inputclass" id="password"><div class="update" id="update" onclick="passwordPost()">Update Changes</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';
    
    document.getElementById('dfname').innerHTML = '';
    document.getElementById('dlname').innerHTML = '';
    document.getElementById('dcat').innerHTML = '';
    document.getElementById('dmailing').innerHTML = '';     
}

function dcat(){
    
    document.getElementById('dcat').innerHTML = '<div id ="responseholder" class="responseholder"><div id="error" class="error"></div><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Category</div><select type="select" id="category" class="selectclass"><option></option><option value="Personal" >Personal</option><option value="Merchant" >Merchant</option></select><div class="update" id="update" onclick="categoryPost()">Update Changes</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';
    
    document.getElementById('dfname').innerHTML = '';
    document.getElementById('dpass').innerHTML = '';
    document.getElementById('dlname').innerHTML = '';
    document.getElementById('dmailing').innerHTML = '';     
}

function dmailing(){
    
    document.getElementById('dmailing').innerHTML = '<div id ="responseholder" class="responseholder"><div id="error" class="error"></div><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Mailing</div><input type="checkbox" id="mailing" class="textboxclass"/><div class="update" id="update" onclick="mailPost()">Update Changes</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';
    
    document.getElementById('dfname').innerHTML = '';
    document.getElementById('dpass').innerHTML = '';
    document.getElementById('dcat').innerHTML = '';
    document.getElementById('dlname').innerHTML = '';     
}

function dial(){
    
    document.getElementById('dmailing').innerHTML = '<div id ="responseholder" class="responseholder"><div id="error" class="error"></div><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">Mailing</div><input type="checkbox" id="mailing" class="textboxclass"/><div class="update" id="update" onclick="mailPost()">Update Changes</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';
    
    document.getElementById('dfname').innerHTML = '';
    document.getElementById('dpass').innerHTML = '';
    document.getElementById('dcat').innerHTML = '';
    document.getElementById('dlname').innerHTML = '';     
}

function closeDialogue() {

    var x = document.getElementById('responseholder');
    x.style.display = 'none'

}

function formHolder(){

    document.getElementById('formholder').innerHTML = '';    
    document.getElementById('create').innerHTML = '';  
    document.getElementById('formholder').innerHTML = '<div id ="responseholder" class="responseholder"><div id="error" class="error"></div><div id="info" class="info"></div><div class="submitb"><div class="dialoguelabel">First Name</div><input type="text" class="inputclass" id="firstName"><div class="update" id="update" onclick="firstNamePost()">Update Changes</div><div class="cancel" id="cancel" onclick="closeDialogue()">Cancel</div></div>';
 
}















