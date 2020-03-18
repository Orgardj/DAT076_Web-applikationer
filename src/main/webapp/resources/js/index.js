$(document).ready(function () {
    //console.log( "ready!" );

    $(".searchIcon").click(function () {
        $(".searchBar").toggleClass("expandSearchBar");
    });

    $("a").on('click', function (event) {
        var hash = this.hash;
        alert($(hash).offset().top - 70);

        /*
         if (this.hash !== "") {
         
         event.preventDefault();
         // Store hash
         var hash = this.hash;
         $('html, body').animate({
         scrollTop: $(hash).offset().top - 30    
         }, 1100, function(){
         window.location.hash = hash;
         });
         } 
         * 
         */  
    });
    
});


function displayForm(input){
    //alert(input);
    
    if(input === "open"){
        $(".blackCover").css("display", "block");
    }
    else if(input === "close"){
        $(".blackCover").css("display", "none");
    }
}



function inputValue(input){
    //alert(input);

    if($("." + input + " input").val()){
        $("." + input + " label").addClass("keepOverHeadPlaceholder");
    }
    else if(!$("." + input + " input").val()){
        $("." + input + " label").removeClass("keepOverHeadPlaceholder");
    }
    
    //alert($("." + input + " input").val());
}


function switchForm(type){
    if(type === "log"){
        $(".loginForm").css("display", "none");
        $(".regForm").css("display", "block");
    }
    else if(type === "reg"){
        $(".regForm").css("display", "none");
        $(".loginForm").css("display", "block");
    }
}