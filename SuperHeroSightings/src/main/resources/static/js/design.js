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

function deleteHero(name, id){


var confirmed=window.confirm('Are you sure you want to delete '+name+'?');
if(confirmed){
  window.location.href = "/deleteHero?id="+id;
};

};

function updateHero(id, value){

  var updateId= "updateHeroTr" + id;
  $('#'+updateId).removeAttr("hidden");
  $('.editForm').hide();
  $('#'+updateId).show();
  var superheroForm ="superPowerIdForEditHero"+id;
 $('#'+superheroForm).val(value);


 $('button[id="'+id+'"]').hide();
 

};

function hideForm(value){

  var updateId= "updateHeroTr" + value;
 
  $('#'+updateId).hide();
 
 $('button[id="'+value+'"]').show();


};
  
  

