function deleteHero(name, id) {
    var confirmed = window.confirm('Are you sure you want to delete ' + name + '?');
    if (confirmed) {
        window.location.href = "/deleteHero?id=" + id;
    }
    ;
}
;
function getHero(id) {
    window.location.href = "/hero?id=" + id;
}
;
function updateHero(id, value) {
    
    var updateId = "updateTr" + id;
    $('#' + updateId).removeAttr("hidden");
    $('.editForm').hide();
    $('#' + updateId).show();
    var superheroForm = "superPowerIdForEditHero" + id;
    $('#' + superheroForm).val(value);
    $('button[id="' + id + '"]').hide();
}
;
function hideForm(value) {
    var updateId = "updateTr" + value;
    $('#' + updateId).hide();
    $('button[id="' + value + '"]').show();
}
;
function deleteOrganization(name, id) {
    var confirmed = window.confirm('Are you sure you want to delete ' + name + '?');
    if (confirmed) {
        window.location.href = "/deleteOrganization?id=" + id;
    }
    ;
}
;
function getOrganization(id) {
    window.location.href = "/organization?id=" + id;
}
;
function updateOrganization(id, value) {
    var updateId = "updateTr" + id;
    $('#' + updateId).removeAttr("hidden");
    $('.editForm').hide();
    $('#' + updateId).show();
    var locationForm = "locationIdForEditorganization" + id;
    $('#' + locationForm).val(value);
    $('button[id="' + id + '"]').hide();
}
;
function deleteSighting(name, id) {
    var confirmed = window.confirm('Are you sure you want to delete this sighting of the ' + name + '?');
    if (confirmed) {
        window.location.href = "/deleteOrganization?id=" + id;
    }
    ;
}
;
function getSighting(id) {
    window.location.href = "/sighting?id=" + id;
}
;
function updateSighting(id, value) {
    var updateId = "updateTr" + id;
    $('#' + updateId).removeAttr("hidden");
    $('.editForm').hide();
    $('#' + updateId).show();
    $('button[id="' + id + '"]').hide();
}
;
function deleteLocation(name, id) {
    var confirmed = window.confirm('Are you sure you want to delete ' + name + '?');
    if (confirmed) {
        window.location.href = "/deleteLocation?id=" + id;
    }
    ;
}
;
function getLocation(id) {
    window.location.href = "/location?id=" + id;
}
;
function updateLocation(id, value) {
    var updateId = "updateTr" + id;
    $('#' + updateId).removeAttr("hidden");
    $('.editForm').hide();
    $('#' + updateId).show();
    $('button[id="' + id + '"]').hide();
}
;
function deleteSuperpower(name, id) {
    var confirmed = window.confirm('Are you sure you want to delete ' + name + '?');
    if (confirmed) {
        window.location.href = "/deleteSuperpower?id=" + id;
    }
    ;
}
;
function getSuperpower(id) {
    window.location.href = "/superpower?id=" + id;
}
;
function updateSuperpower(id, value) {
    var updateId = "updateTr" + id;
    $('#' + updateId).removeAttr("hidden");
    $('.editForm').hide();
    $('#' + updateId).show();
    $('button[id="' + id + '"]').hide();
}
;

function searchBySuperpower(id) {
        window.location.href = "/searchHeroesBySuperpower?superPowerId=" + id;

}