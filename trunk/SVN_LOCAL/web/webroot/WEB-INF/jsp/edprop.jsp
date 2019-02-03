<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<html>
<head>
<title>Find Quality Homes with Ease - kejamart</title>

<script type="text/javascript" src="javascript/menuz.js"></script> 
    
<!-- JQuery, Jackson, Spring Validation -->  

<script src="<%=request.getContextPath() %>/javascript/editproperty.js"></script>    
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>

<script type="text/javascript">
   
    $(document).ready(function() {
		      
		$().ready(function() {
		    $().load(function(event) {
 
			$.ajax({
				url: contexPath + "/editproperty.html",
   			  	type: "GET",
			  	
			  	success: function() {
			  	
			  	}
			});			
		    });
		});
});   
</script>

<script>
$(document).ready(
        function() {

            $('#country').change(
            function() {
                
                $.getJSON('editproperty2.html', {
                    country: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="">--Select--</option>';
                    var len = data.length;
      
                    for ( var i = 0; i < len; i++) {
                    	listInfo += '<option value="' + data[i].id + '">' + data[i].county + '</option>';

                    }
                    listInfo += '</option>';
   
                    $('#county').html(listInfo);

                });  
            });
            
        });	        
</script>

<script>
$(document).ready(
        function() {

            $('#county').change(
            function() {
                
                $.getJSON('editproperty3.html', {
                    county: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="">--Select--</option>';
                    var len = data.length;
      
                    for ( var i = 0; i < len; i++) {
                    	listInfo += '<option value="' + data[i].id + '">' + data[i].location + '</option>';

                    }
                    listInfo += '</option>';
   
                    $('#location').html(listInfo);

                });  
            });
            
        });	        
</script>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/animate.css" rel="stylesheet" />
    <!-- Squad theme CSS -->
	<link href="css/login.css" rel="stylesheet">
	<link href="color/default.css" rel="stylesheet">
	<link href="css/app.css" rel="stylesheet">
	<link rel="stylesheet" href="css/kejanix.css">
	<link rel="stylesheet" href="css/style.css">
<link rel = "shortcut icon" href="img/logox.png"/>

</head>
<body>

        <nav class="navbarx navbar-customx" role="navigation">
                              <div class="container">
                                    <div class="row">
                                          <div class="col-md-2">
                                                   <div class="site-logo">
                                                            <a href="search.html" class="navbar-brand"></a>
                                                    </div>
                                          </div>
                                          

                                          <div class="col-md-10">
                         
                                                      <!-- Brand and toggle get grouped for better mobile display -->
                                          <div class="navbar-header">
                                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
                                                <i class="fa fa-bars"></i>
                                                </button>
                                          </div>
                                                      <!-- Collect the nav links, forms, and other content for toggling -->
                                                      <div class="collapse navbar-collapse" id="menu">
                                                            <ul class="nav navbar-nav navbar-right">
                                                                                                  
                                                                                                  <li>${sea}</li>                                                            
                                                                                                  <li>${prof}</li>
								                                  <li>${alert}</li>
								                                  <li>${notif}</li>
								                                  <li>${addp}</li>
								                                  <li>${dash}</li>
								                                  <li>${help2}</li>
								                                  <li>${musers}</li>
								                                  <li>${propmg}</li>
								                                  <li>${addpp}</li>
								                                  <li>${editpp}</li>
                                                                  <li>${advert}</li>
                                                                  <c:if test="${empty userSession}"> 
								  </c:if>
								  <c:if test="${!empty userSession}">
								  <li>${logoutLink}</li>
								  <li class="user">${userSession}</li>	
								  </c:if>            
                                                         </div>
                                                            </ul>
                                                      </div>
                                                      
                                                      <!-- /.Navbar-collapse -->
                             
                                          </div>
                                    </div>
                              </div>
                              <!-- /.container -->
                        </nav>

<div class="container">

<div class="formholder">

<div class="formtitle">Edit Property</div>

<form accept-charset=utf-8>

<c:forEach var="prop" items="${property}"> 

<input type="hidden" id="id" value=${prop.id}>

<div class="itemholder">
<div class="formlabel">Property Name</div><div class="formdisplay"><input type="text" class="textclassa" id="name" value="${prop.name}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Email</div><div class="formdisplay"><input type="text" class="textclassa" id="email" value="${prop.email}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Company</div><div class="formdisplay"><input type="text" class="textclassa" id="company" value="${prop.company}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Contacts</div><div class="formdisplay"><input type="text" class="textclassa" id="contacts" value="${prop.contacts}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Website</div><div class="formdisplay"><input type="text" class="textclassa" id="website" value="${prop.website}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Amount</div><div class="formdisplay"><input type="number" class="textclassa" id="amount" value= "${prop.amount}" onclick="clearResults()"></div>
</div>

<div class="itemholder2">
<div class="formlabel">Property Description</div>
<div class="formdisplay"><textarea  class="textclassi" id="description" onclick="clearResults()">${prop.description}</textarea></div>  
</div>

<div class="itemholder">
<div class="formlabel">Parking</div><div class="formdisplay"><input type="number" class="textclassa" id="parking" value= "${prop.parking}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Bathrooms</div><div class="formdisplay"><input type="number" class="textclassa" id="bathroom" value= "${prop.bathroom}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Latitude</div><div class="formdisplay"><input type="text" class="textclassa" id="latitude" value="${prop.latitude}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Longitude</div><div class="formdisplay"><input type="text" class="textclassa" id="longitude" value="${prop.longitude}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Video URL</div><div class="formdisplay"><input type="text" class="textclassa" id="videourl" value="${prop.videourl}" onclick="clearResults()"></div>
</div>

<div class="itemholder">
<div class="formlabel">Status</div><div class="formdisplay">
<select type="select" id="status" name="status" class="selectclassa">
<option value="${prop.status}">Select Status</option>
<option value = 0 >Default Listing</option>
<option value = 2 >Classified Listing</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Country</div><div class="formdisplay">
<select type="select" id="country" name="country" class="selectclassa">
<option value="${prop.country}">Country</option>
<c:forEach items="${countryList}" var="ct">
<option value="${ct.id}">${ct.country}</option>
</c:forEach>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">County</div><div class="formdisplay">
<select type="select" id="county" name="county" class="selectclassa" onchange="clearResults()">
<option value="${prop.county}">${prop.countyVal}</option>
<option value="">--Select--</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Location</div><div class="formdisplay">
<select type="select" id="location" name="location" class="selectclassa" onchange="clearResults()">
<option value="${prop.location}">${prop.locationVal}</option>
<option value="">--Select--</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Property Type</div><div class="formdisplay">
<select type="select" id="propertyType" name="propertyType" class="selectclassa">
<option value="${prop.propertyType}">${prop.propertyType}</option>
<option value="">--Select--</option>
<option value="To Let" >To Let</option>
<option value="For Sale" >For Sale</option>
</select>
</div>
</div>

<div class="itemholder">
<div class="formlabel">Category</div><div class="formdisplay">
<select type="select" id="category" name="category" class="selectclassa" onchange="clearResults()">
<option value="${prop.category}">${prop.category}</option>
<option value="">--Select--</option>
<option value="Residential Property" >Residential</option>
<option value="Commercial Property" >Commercial</option>
</select>
</div>
</div>

<div class="itemholder" id="bedroomholder">
<div class="formlabel">Bedrooms</div><div class="formdisplay">
<select type="select" id="bedrooms" class="selectclassa" name="bedrooms" onchange="clearResults()">
<option value="${prop.bedrooms}">${prop.bedrooms}</option>
<option value="">--Select--</option>
<option value="0">0</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>   
<option value="10">9+</option>  
</select>
</div>
</div>

<div class="itemholder" id="rangeholderx">
<div class="formlabel">Range</div><div class="formdisplay">
<select type="select" id="prange" name="prange"  class="selectclassa" onchange="clearResults()">
<option value="${prop.prange}">${prop.rangeValue}</option>
<c:forEach items="${rangeLists}" var="rn">
<option value="${rn.id}" id="xxx">${rn.prange}</option>
</c:forEach>
</select>
</div>
</div>

<div class="itemholder" id="scheduleholder">
<div class="formlabel">Payment Schedule</div><div class="formdisplay">
<select type="select" id="schedule" class="selectclassa" onchange="clearResults()">
<option value="${prop.schedule}">${prop.schedule}</option>
<option value="">--Select--</option>
<option value="Per Unit">Per Unit</option>
<option value="Per Month">Per Month</option>
<option value="Per SQ Feet">Per SQ Feet</option>
<option value="Per SQ Ft Per Month">Per SQ Ft Per Month</option>
<option value="Per SQ Cm Per Month">Per SQ Cm Per Month</option>
</select>
</div>
</div>

<div id="error" class="error"></div>
<div id="info" class="info"></div>

<div class="updatex" id="edit" onclick="editProp()">Edit Property</div><a href="/kejani/allproperty.html"><div class="updatex" id="cancel">Back to Properties</div></a>

</form>
</c:forEach>

</div>
</div>

	<footer>
		<div class="container">
		<div style="visibility: visible; animation-name: zoomIn;" class="col-sm-12 text-center wow zoomIn">
			<h3 class="footer h3">Follow us on</h3><br>
			<div class="footer_social">
				<ul>
					<li><a class="f_facebook" href="https://twitter.com/KejaniInfo"><i class="fa fa-facebook"></i></a></li>
					<li><a class="f_twitter" href="https://twitter.com/KejaniInfo"><i class="fa fa-twitter"></i></a></li>
					<li><a class="f_instagram" href="https://www.instagram.com/kejanilimited/"><i class="fa fa-instagram"></i></a></li>
				</ul>
			</div>
                </div><!--- END COL -->		
			<div class="row">
				<div class="col-md-12 col-lg-12">
					<div class="wow shake" data-wow-delay="0.4s">
					</div><br>
					<p>Copyright © 2017 kejamart. All rights reserved.</p>

				</div>
			</div>
		</div>
	</footer>

    <!-- Core JavaScript Files -->
    
    <script src="jsmain/jquery.min.js"></script>
    <script src="jsmain/bootstrap.min.js"></script>
    <script src="jsmain/jquery.easing.min.js"></script>
	<script src="jsmain/jquery.scrollTo.js"></script>
	<script src="jsmain/wow.min.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="jsmain/custom.js"></script>   


</body>

</html>