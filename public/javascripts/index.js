$(document).ready(function() {
    console.log("titi");

    var csrfToken = $("#csrfToken").val();
    var keywordInput = $("#keywordInput").val();
    var callAPI = $("#callAPI").val();

    var apiResult = $("#resultContent").val();

    var apiResultJSON = JSON.parse(apiResult);
    $("#resultContent").html(apiResultJSON);

});