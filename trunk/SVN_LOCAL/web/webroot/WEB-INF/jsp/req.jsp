<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@page isELIgnored ="false" %>

<!DOCTYPE html>
<html>
<head>
<title>Find Quality Homes with Ease - kejamart</title>

<script type="text/javascript" src="javascript/menuz.js"></script> 
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>
<script src="<%=request.getContextPath() %>/javascript/request.js"></script>
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterfacex.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script>

<script type="text/javascript">
   
    $(document).ready(function() {
		      
		$().ready(function() {
		    $().load(function(event) {
 
			$.ajax({
				url: contexPath + "/requests.html",
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
                
                $.getJSON('requests2.html', {
                    country: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="">County</option>';
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
                
                $.getJSON('requests3.html', {
                    county: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="">Location</option>';
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

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-106182339-1', 'auto');
  ga('send', 'pageview');

</script>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Fonts -->
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link href="css/animate.css" rel="stylesheet" />
    <!-- Squad theme CSS -->
    <link href="css/style.css" rel="stylesheet">
	<link href="css/login.css" rel="stylesheet">
	<link href="color/default.css" rel="stylesheet">
	<link href="css/app.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" href="css/kejanix.css">
  <link rel = "shortcut icon" href="img/logox.png"/>

</head>
<body id="page-top" data-spy="scroll" data-target=".navbar-custom">

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

<section id="contact" class="home-section nopadd-bot color-dark bg-white text-center">

<h6 class="h-bold">Manage Alerts</h6>

<div class="modal-dialog">
<div class="loginmodal-container">

<form id = "data" class="data">

<select type="select" id="country" name="country" class="form-control2">
<option value="" >Country</option>
<c:forEach items="${countryList}" var="ct">
<option value="${ct.id}">${ct.country}</option>
</c:forEach>
</select><p><p>

<select type="select" id="county" name="county" class="form-control2">
<option value="">County</option>
</select><p>

<select type="select" id="location" name="location" class="form-control2">
<option value="">Location</option>
</select><p>

<select type="select" id="propertyType" class="form-control2">
<option value="" >Property Type</option>
<option value="To Let" >Property To Let</option>
<option value="For Sale" >Property For Sale</option>
</select><p>

<select type="select" id="category" class="form-control2">
<option value="" >Category</option>
<option value="Residential Property" >Residential</option>
<option value="Commercial Property" >Commercial</option>
</select><p>

<select type="select" id="bedrooms" name="bedrooms" class="form-control2">
<option value="0" >Bedrooms</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
<option value="4">4</option>
<option value="5">5+</option>    
</select><p>

<select type="select" id="prange" class="form-control2">
<option value="6" >Range</option>
<c:forEach items="${rangeLists}" var="rn">
<option value="${rn.id}">${rn.prange}</option>
</c:forEach>
</select>
</select><p>

<input type="button" class="btn btn-primary btn-lg btn-block" id="create" onclick="saveRequest()" value="Create Request">

</form>

</div>
</div>

</section>

<c:if test="${!empty alerts}">

<div class="requestholder" id="listData">

<div class="faltitle" align="center">Alerts</div>

<table id="alertHolder">
<tr class="listheader">
<th><div class="listclass7"></div></th>
<th><div class="listclass2">County</div></th>
<th><div class="listclass2">Location</div></th>
<th><div class="listclass2">Category</div></th>
<th><div class="listclass2">Property Type</div></th>
<th><div class="listclass2">Range</div></th>
<th><div class="listclass5">Bedrooms</div></th>
<th><div class="listclass4">Delete</div></th>
</tr>

<c:forEach var="alert" items="${alerts}">
<tr class="listholder">

<div id ="dial" class="dialogue"></div>

<form id="requestForm" action="deleterequest.html" method="post" accept-charset=utf-8>
<th><div class="listclass3"><img src="led-icons/pin.png"></div></th>
<input type="hidden" name="id" id="id" value="${alert.id}">
<th><input type="text" class="listclass" id="county" name="county" value="${alert.countyVal}"></th>
<th><input type="text" class="listclass" id="locationVal" name="locationVal" value="${alert.locationVal}"></th>
<th><input type="text" class="listclass" id="category" name="category" value="${alert.category}"></th>
<th><input type="text" class="listclass" id="propertyType" name="propertyType" value="${alert.propertyType}"></th>
<th><input type="text" class="listclass" id="prange" name="prange" value="${alert.rangeValue}"></th>
<th><input type="text" class="listclass3" id="bedrooms" name="bedrooms" value="${alert.bedrooms}"></th>
<th><div class="listclass4"><img src="led-icons/cross.png" onclick="reqDial()"></div></th>
</form>

</tr>
</c:forEach>
</table>

</c:if>
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