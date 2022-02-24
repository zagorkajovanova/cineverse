let numSeats = 0;

$(document).ready(function(){
    console.log("test");
    $(".seat").click(function(){
        numSeats++;
    });

    $(".btn").click(function(){
        console.log(numSeats);
    });
});