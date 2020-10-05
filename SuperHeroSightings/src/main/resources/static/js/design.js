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

$("#deleteThisHero").click(function(event){
var heroName=this.value;
var confirmed=confirm("Are you sure you with to delete "+heroName+"?");
if(confirmed){
  
}

    });


});