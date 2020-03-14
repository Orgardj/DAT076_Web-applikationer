$(document).ready(function () {
    //console.log( "ready!" );

    $(".searchIcon").click(function () {
        /*
         $(".searchBar").css({
         "width": "50%",
         "margin-right": "10px"
         });
         
         */

        $(".searchBar").toggleClass("expandSearchBar");
    });


$("a").on('click', function(event) {
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
 */  });
    
});
