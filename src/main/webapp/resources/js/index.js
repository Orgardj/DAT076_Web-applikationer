$(document).ready(function () {
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
            });
        }
    });
});

function displayForm(input) {
    if (input === "open") {
        $(".blackCover").css("display", "block");
    } else if (input === "close") {
        $(".blackCover").css("display", "none");
    }
}

function inputValue(input) {
    if ($("." + input + " input").val()) {
        $("." + input + " label").addClass("keepOverHeadPlaceholder");
    } else if (!$("." + input + " input").val()) {
        $("." + input + " label").removeClass("keepOverHeadPlaceholder");
    }
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

function controllError(input) {
    var i;

    if ($(".regForm span").length) {
        for (i = 0; i < $(".regForm span").length; i++) {
            $(".regForm span").parent().children("label").css({"top": "calc(50% - 1em)", "transition": "0s"});
        }
    }

    if (i === 2 && input === "update") {
        location.reload();
    }
}

controllError();

for (i = 0; i <= $(".regForm div input").length; i++) {
    if ($(".regForm div:nth-child(" + i + ") input").val() !== "j_idt7:studentForm" && $(".regForm div:nth-child(" + i + ") input").val() !== "") {
        $(".regForm div:nth-child(" + i + ") label").addClass("keepOverHeadPlaceholder");
    }
}

if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}
