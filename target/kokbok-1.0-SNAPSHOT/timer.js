/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    var timer = document.getElementById("btnId");

    function countdown () {
      var hide = document.getElementById("animation");
      hide.style.backgroundColor = "red";

      var yourDateToGo = new Date();
      yourDateToGo.setDate(yourDateToGo.getDate());
      yourDateToGo = new Date(yourDateToGo.getTime() + timer.value*1000 + 1000); //minutes + 1 second offset

      var timing = setInterval(
      function () {

          var currentDate = new Date().getTime();
          var timeLeft = yourDateToGo - currentDate; 

          var minutesLeft = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));  
          if (minutesLeft < 10) minutesLeft="0"+minutesLeft;
          var secondsLeft = Math.floor((timeLeft % (1000 * 60)) / 1000);
          if (secondsLeft < 10) secondsLeft="0"+secondsLeft;

          document.getElementById("countdown2").innerHTML = minutesLeft + "m " + secondsLeft + "s";  


          if (timeLeft <= 0) {
            clearInterval(timing);
            document.getElementById("countdown2").innerHTML = "It's over"; 
            hide.style.backgroundColor = "white";

          }
      }, 1000);

    }

timer.addEventListener('click', countdown);
});


            