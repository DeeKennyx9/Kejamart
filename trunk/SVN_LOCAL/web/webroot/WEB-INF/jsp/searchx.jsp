<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page isELIgnored ="false" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Find Quality Homes with Ease - kejamart</title>
    
<!-- JQuery, Jackson, Spring Validation --> 
        
<script src="<%=request.getContextPath() %>/javascript/jquery.js"></script>
<script type="text/javascript">
var contexPath = "<%=request.getContextPath() %>";
</script>

<script src="<%=request.getContextPath() %>/javascript/retrieveProps.js"></script>
<script src="<%=request.getContextPath() %>/javascript/search.js"></script>
<script src="<%=request.getContextPath() %>/javascript/refreshPages.js"></script>
<script src="<%=request.getContextPath() %>/javascript/kdialogue.js"></script>
<script src="<%=request.getContextPath() %>/javascript/userInterface.js"></script>
<script src="<%=request.getContextPath() %>/javascript/deleteLinks.js"></script> 

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-106182339-1', 'auto');
  ga('send', 'pageview');

</script>
    
<script type="text/javascript">
   
    $(document).ready(function() {
		      
		$().ready(function() {
		    $().load(function(event) {
 
			$.ajax({
				url: contexPath + "/search.html",
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
                
                $.getJSON('search2.html', {
                    country: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="1">County</option>';
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
                
                $.getJSON('search3.html', {
                    county: $(this).val(),
                    ajax : 'true'
                }, function(data) {
                    
                    var listInfo = '<option value="1">Location</option>';
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

    <!-- css -->
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css">
    <link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="css/owl.carousel.css" rel="stylesheet" media="screen" />
    <link href="css/owl.theme.css" rel="stylesheet" media="screen" />
    <link href="css/flexslider.css" rel="stylesheet" />
    <link href="css/animate.css" rel="stylesheet" />
    <link href="color/default.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <link rel="stylesheet" type="text/css" href="css/search.css">
    <link rel = "shortcut icon" href="img/logox.png"/>

</head>
	
<section id="intro" class="home-slide text-light">
    
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.10";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>    
	
    <!-- Navigation -->
    <div id="navigation">
        <nav class="navbar navbar-custom" role="navigation">
                              <div class="container">
                                    <div class="row">
                                          <div class="col-md-2">
                                                   <div class="site-logo">
                                                            <a href="search.html" class="navbar-brand">kejamart</a>
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
                                                                  <li><a href="search.html">Home</a></li>
                                                                  <li><a href="about.html">About</a></li>
                                                                  <li><a href="contact.html">Contact</a></li>
                                                                  <c:if test="${empty userSession}"> 
			                                          <li><a href="login.html" class="btn btn-bannery">Log In</a></li>
			                                          <li><a href="register.html" class="btn btn-bannerx">Sign Up</a></li>
								  </c:if>
								  <c:if test="${!empty userSession}">
								  <li>${logoutLink}</li>
								  <li>${profileLink}</li>
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
    </div> 
    <!-- /Navigation -->  

	</section>

         <div class="bgprop">   
         
         <p class="searchheader"> Find Quality Homes with <font class="ease">Ease.</font></p>
         
         <div class="searchprop">
         
	 <form id="searchForm" action="search.html" method="get" accept-charset=utf-8>
	 
	 <div class="select-style">
	 <select type="select" id="country" name="country" >
	 <option value="" >Country</option>
	 <c:forEach items="${countryList}" var="ct">
	 <option value="${ct.id}">${ct.country}</option>
	 </c:forEach>
     </select>
     </div>

     <div class="select-style">
	 <select type="select" id="county" name="county">
	 <option value="" >County</option>
     </select>
     </div>
         
     <div class="select-style">
	 <select type="select" id="location" name="location" >
	 <option value="" >Location</option>
     </select> 
     </div> 
         
     <div class="select-style">
	 <select type="select" id="propertyType" name="propertyType" onchange="showrange()">
	 <option value="" >Property Type</option>
	 <option value="To Let">To Let</option>
     <option value="For Sale">For Sale</option>
     </select>
     </div>
         
     <br><p>         
    
     <div class="select-style">
     <select type="select" id="category" name="category">
     <option value="" >Category</option>
     <option value="Residential Property" >Residential</option>
     <option value="Commercial Property" >Commercial</option>
     </select> 
     </div>
         
     <div class="select-style" id="bedroomholder">
	 <select type="select" id="bedrooms" name="bedrooms" >
	 <option value="0" >Bedrooms</option>
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
         
     <div class="select-style" id="rangeholderx">
	 <select type="select" id="prange" name="prange">
	 <option value="6" >Amount Range</option>
     <c:forEach items="${rangeList}" var="rn">
     <option value="${rn.id}">${rn.prange}</option>
     </c:forEach>
     </select> 
     </div> 
     
     <div class="select-style" id="rangeholder" style="display:none">
	 <select type="select" id="prange" name="prange" >
	 <option value="6" >Amount Range</option>
     <c:forEach items="${rangeListx}" var="rx">
     <option value="${rx.id}">${rx.prange}</option>
     </c:forEach>
     </select> 
     </div>         
     <button class="btn btn-search" type="button" id="searchbtn" onclick="getProps()"><i class="fa fa-search fa-fw"></i> Search</button>
     </form>	 	 
	 
     </div>
     
	<div class="panel panel-default">
	<div class="panel-body">
	
	<div class="resultsearch">${searchProcess}</div>
	<div class="resultsearch">${result}</div>

        <!-- Property list: Full -->            
        
        <c:if test="${!empty pstringList}">
        <c:forEach var="pr" items="${pstringList}">        
            
	<div class="propertydiv" id ="propertydivx"> 	 
	
	<form id="viewDetails" action="viewdetails.html" method="get" accept-charset=utf-8>
	<input type="hidden" name="id" id="id" value="${pr.id}">
	<input type="hidden" name="views" id="views" value="${pr.views}">
	
	<img src="${pr.path}" ${pr.orientation}><p>	
	<div class="results-headb"> ${pr.name} </div>
	<div class="results-head1"> ${pr.locationVal}, ${pr.countyVal} County.</div>
	<div class="results-head1"> <i class='fa fa-bed'></i> ${pr.bedrooms} &nbsp; <i class='fa fa-bath'></i> 
	${pr.bathroom} &nbsp; <i class="fa fa-car"></i> ${pr.parking} </div>	
	<div class="resultsamt"> Ksh. <fmt:formatNumber value="${pr.amount}" type="currency" currencySymbol="" pattern="###,###"/> </div>
	<div class="views"> ${pr.views} views </div>
        <button type="submit" class="view-holder">Details</button>
	</form>	
	
        </div>         
        </c:forEach>
        </c:if>       
	
	<!-- Featured list: Top 3 -->
	
	<div class="classheader"> Featured Properties </div>
	<div class="featwrapper" id="classifiedsx">
	<div class="moreresults" onclick="showallf()">View more &nbsp; <span class="glyphicon glyphicon-chevron-right"></span></div>
    	<c:if test="${!empty classifiedsx}">
        <c:forEach var="cl" items="${classifiedsx}">         
	<div class="classifieds"> 
	
	<form id="detailsView" action="viewdetails.html" method="get" accept-charset=utf-8>
	<input type="hidden" name="id" id="id" value="${cl.id}">
	<input type="hidden" name="views" id="views" value="${cl.views}">
	
	<img src="${cl.path}" ${cl.orientation}><p>
	<div class="results-headb"> ${cl.name} </div>
	<div class="results-head1"> ${cl.locationVal}, ${cl.countyVal} County.</div>
	<div class="results-head1"> <i class='fa fa-bed'></i> ${cl.bedrooms} &nbsp; <i class='fa fa-bath'></i> 
	${cl.bathroom} &nbsp; <i class="fa fa-car"></i> ${cl.parking} </div>	
	<div class="resultsamt"> Ksh. <fmt:formatNumber value="${cl.amount}" type="currency" currencySymbol="" pattern="###,###"/> </div>
	<div class="views"> ${cl.views} views</div> 
        <button type="submit" class="view-holder">Details</button>
	</form>	
	
        </div> 
	</c:forEach>
        </c:if>   
        </div>
        
        <!-- Featured list: Full -->
        
        <div class="featwrapper" id="classifieds" style="display:none">
    	<c:if test="${!empty classifieds}">
        <c:forEach var="cls" items="${classifieds}">         
	<div class="classifieds"> 
	
	<form id="detailsView" action="viewdetails.html" method="get" accept-charset=utf-8>
	<input type="hidden" name="id" id="id" value="${cls.id}">
	<input type="hidden" name="views" id="views" value="${cls.views}">
	
	<img src="${cls.path}" ${cls.orientation}><p>
	<div class="results-headb"> ${cls.name} </div>
	<div class="results-head1"> ${cls.locationVal}, ${cls.countyVal} County.</div>
	<div class="results-head1"> <i class='fa fa-bed'></i> ${cls.bedrooms} &nbsp; <i class='fa fa-bath'></i> 
	${cls.bathroom} &nbsp; <i class="fa fa-car"></i> ${cls.parking} </div>	
	<div class="resultsamt"> Ksh. <fmt:formatNumber value="${cls.amount}" type="currency" currencySymbol="" pattern="###,###"/> </div>
	<div class="views"> ${cls.views} views</div> 
        <button type="submit" class="view-holder">Details</button>
	</form>	
	
        </div> 
	</c:forEach>
        </c:if> 
        </div>

	</div>
	</div>
	
	</div>
	
	<footer>
		<div class="container">
		
		<div style="visibility: visible; animation-name: zoomIn;" class="col-sm-12 text-center wow zoomIn">
			<h3 class="footer h3">Follow us on</h3><br>
			<div class="footer_social">
				<ul>
					<li><a class="f_facebook" href=""><i class="fa fa-facebook"></i></a></li>
					<li><a class="f_twitter" href=""><i class="fa fa-twitter"></i></a></li>
					<li><a class="f_instagram" href=""><i class="fa fa-instagram"></i></a></li>
				</ul>
			</div>
                </div><!--- END COL -->
			<div class="row">
				<div class="col-md-6 col-md-offset-3">
					
					<div class="text-center">
						<a href="#intro" class="totop"><i class="fa fa-angle-up fa-3x"></i></a>
						<p>Copyright © 2017 kejamart. All rights reserved.</p>

					</div>
				</div>
			</div>	
		</div>
	</footer>

    <!-- Core JavaScript Files -->
    <script src="js/jquery.min.js"></script>	 
    <script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.flexslider-min.js"></script>
    <script src="js/jquery.easing.min.js"></script>	
	<script src="js/jquery.scrollTo.js"></script>
	<script src="js/jquery.appear.js"></script>
	<script src="js/stellar.js"></script>
	<script src="js/wow.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/nivo-lightbox.min.js"></script>
    <script src="js/custom.js"></script>
    <script src="contactform/contactform.js"></script>  
    
</body>

</html>
