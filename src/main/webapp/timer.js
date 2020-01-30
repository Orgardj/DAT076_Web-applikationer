/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//$( document ).ready(function() {});
window.onload = function() {
    var timer1min = document.getElementById("1mintimer");
    var animation = document.getElementById("animation");

    function countdown () {
        
      
      animation.style.animationPlayState="running";
      var hide = document.getElementById("animation");
      hide.style.backgroundColor = "red";

      var yourDateToGo = new Date();
      yourDateToGo.setDate(yourDateToGo.getDate());
      yourDateToGo = new Date(yourDateToGo.getTime() + timer1min.value*60000 + 1000); //minutes + 1 second offset

      var timing = setInterval(
      function () {

          var currentDate = new Date().getTime();
          var timeLeft = yourDateToGo - currentDate; 

          var minutesLeft = Math.floor((timeLeft % (1000 * 60 * 60)) / (1000 * 60));  
          if (minutesLeft < 10) minutesLeft="0"+minutesLeft;
          var secondsLeft = Math.floor((timeLeft % (1000 * 60)) / 1000);
          if (secondsLeft < 10) secondsLeft="0"+secondsLeft;

          document.getElementById("countdown").innerHTML = minutesLeft + "m " + secondsLeft + "s";  


          if (timeLeft <= 0) {
            clearInterval(timing);
            document.getElementById("countdown").innerHTML = "It's over"; 
            hide.style.backgroundColor = "white";

          }
      },0);

    }

timer1min.addEventListener('click', countdown);
}

            