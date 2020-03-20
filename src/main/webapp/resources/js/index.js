$(document).ready(function () {
    //console.log( "ready!" );

    $(".searchIcon").click(function () {
        $(".searchBar").toggleClass("expandSearchBar");
    });

    $("a").on('click', function (event) {
        var hash = this.hash;
        
        var scrollAmount = $(hash).offset().top - 70;

        if (this.hash !== "") {

            event.preventDefault();
            
            
            var hash = this.hash;
            $('html, body').animate({
                scrollTop: scrollAmount
            }, 700, function () {
                //window.location.hash = hash;
            });
        }

    });

});


function displayForm(input) {
    //alert(input);

    if (input === "open") {
        $(".blackCover").css("display", "block");
    } else if (input === "close") {
        $(".blackCover").css("display", "none");
    }
}



function inputValue(input) {
    //alert(input);

    if ($("." + input + " input").val()) {
        $("." + input + " label").addClass("keepOverHeadPlaceholder");
    } else if (!$("." + input + " input").val()) {
        $("." + input + " label").removeClass("keepOverHeadPlaceholder");
    }

    //alert($("." + input + " input").val());
}


function switchForm(type) {
    if (type === "log") {
        $(".loginForm").css("display", "none");
        $(".regForm").css("display", "block");
    } else if (type === "reg") {
        $(".regForm").css("display", "none");
        $(".loginForm").css("display", "block");
    }
}

function controllError(){
    if ($(".regForm span").length) {
        //alert($(".regForm span").length);

        var i;

        for (i = 0; i < $(".regForm span").length; i++) {
            //console.log($(".regForm span").parent().get( 0 ).tagName);
            $(".regForm span").parent().children("label").css({"top": "calc(50% - 1em)", "transition": "0s"});
        }
    }
}

controllError();

for (i = 0; i <= $(".regForm div input").length; i++) {
    //console.log($(".regForm div:nth-child(" + i + ") input").val());
    if ($(".regForm div:nth-child(" + i + ") input").val() !== "j_idt13:studentForm" && $(".regForm div:nth-child(" + i + ") input").val() !== "") {
        $(".regForm div:nth-child(" + i + ") label").addClass("keepOverHeadPlaceholder");
    }
}