function deleteHero(name, id){


    var confirmed=window.confirm('Are you sure you want to delete '+name+'?');
    if(confirmed){
      window.location.href = "/deleteHero?id="+id;
    };
    
    };
    
    function updateHero(id, value){
    
      var updateId= "updateTr" + id;
      $('#'+updateId).removeAttr("hidden");
      $('.editForm').hide();
      $('#'+updateId).show();
      var superheroForm ="superPowerIdForEditHero"+id;
     $('#'+superheroForm).val(value);
    
    
     $('button[id="'+id+'"]').hide();
     
    
    };
    
    function hideForm(value){
    
      var updateId= "updateTr" + value;
     
      $('#'+updateId).hide();
     
     $('button[id="'+value+'"]').show();
    
    
    };
    
    
    function deleteOrganization(name, id){
    
    
      var confirmed=window.confirm('Are you sure you want to delete '+name+'?');
      if(confirmed){
        window.location.href = "/deleteOrganization?id="+id;
      };
      
      };
      
      function updateOrganization(id, value){
      
        var updateId= "updateTr" + id;
        $('#'+updateId).removeAttr("hidden");
        $('.editForm').hide();
        $('#'+updateId).show();
        var locationForm ="locationIdForEditorganization"+id;
       $('#'+locationForm).val(value);
      
      
       $('button[id="'+id+'"]').hide();
       
      
      };
    
      function deleteSighting(name, id){
    
    
        var confirmed=window.confirm('Are you sure you want to delete this sighting of the '+name+'?');
        if(confirmed){
          window.location.href = "/deleteOrganization?id="+id;
        };
        
        };
        
        function updateSighting(id, value){
        
          var updateId= "updateTr" + id;
          $('#'+updateId).removeAttr("hidden");
          $('.editForm').hide();
          $('#'+updateId).show();
         
         $('button[id="'+id+'"]').hide();
         
        
        };
      
     
      
      