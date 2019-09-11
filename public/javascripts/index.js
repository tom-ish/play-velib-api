$(document).ready(function() {
    console.log("titi");

    var csrfToken = $("#csrfToken").val();
    var keywordInput = $("#keywordInput").val();
    var callAPI = $("#callAPI").val();

    var apiResult = $("#resultContent").val();
    console.log(apiResult);
//    var apiResultJSON = JSON.parse(apiResult);
    if(apiResult !== undefined)
        $("#resultContent").html(apiResult);

});