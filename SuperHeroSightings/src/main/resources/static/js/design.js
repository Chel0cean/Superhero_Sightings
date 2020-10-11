//design functions go here.

$(document).ready(function() {


  $('#addFormRevealButton').click( function(){
$('#addFormRevealButton').hide();
$('#addObjectField').removeAttr("hidden");
$('#addObjectField').show();
 $('#searchHeroesByOrganizationForm').hide();
   $('#searchHeroesBySuperpowerForm').hide();
 

});

$("#cancelAddObject").click(function(event){
$("#addFormRevealButton").show();
$("#addObjectField").hide();
});


$("#searchHeroesBySuperpower").click(function(event){
  $('#searchHeroesBySuperpowerForm').removeAttr("hidden");
  $('#searchHeroesBySuperpowerForm').show();
  $('#searchHeroesByOrganizationForm').hide();
 



});

$("#cancelSearchBySuperpower").click(function(event){
  $('#searchHeroesBySuperpowerForm').hide();
 

});



$("#searchHeroesByOrganization").click(function(event){
  $('#searchHeroesByOrganizationForm').removeAttr("hidden");
  $('#searchHeroesByOrganizationForm').show();
  $('#searchHeroesBySuperpowerForm').hide();




});


$("#cancelSearchByOrganization").click(function(event){
  $('#searchHeroesByOrganizationForm').hide();
 

});




});



$("#searchOrganizationsByHeroButton").click(function(event){
 
  $('#searchOrganizationsByHeroForm').removeAttr("hidden");
  $('#searchOrganizationsByHeroForm').show();





});


$("#cancelSearchByHero").click(function(event){
  $('#searchOrganizationsByHeroForm').hide();
 

});


$("#searchSightingsByHero").click(function(event){
  $('#searchSightingsByHeroForm').removeAttr("hidden");
  $('#searchSightingsByHeroForm').show();
  $('#searchSightingsByDateForm').hide();
  $('#searchSightingsByLocationForm').hide();
 



});

$("#cancelSearchByHero").click(function(event){
  $('#searchSightingsByHeroForm').hide();
 

});



$("#searchSightingsByLocation").click(function(event){
  $('#searchSightingsByLocationForm').removeAttr("hidden");
  $('#searchSightingsByLocationForm').show();
  $('#searchSightingsByHeroForm').hide();
  $('#searchSightingsByDateForm').hide();




});


$("#cancelSearchSightingsByLocation").click(function(event){
  $('#searchSightingsByLocationForm').hide();
 

});



$("#searchSightingsByDate").click(function(event){
  $('#searchSightingsByDateForm').removeAttr("hidden");
  $('#searchSightingsByDateForm').show();
  $('#searchSightingsByHeroForm').hide();
  $('#searchSightingsByLocationForm').hide();




});


$("#cancelSearchSightingsByDate").click(function(event){
  $('#searchSightingsByDateForm').hide();
 

});



