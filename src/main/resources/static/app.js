$(document).ready(function () {

    $(".tabs").click(function () {

        $(".tabs").removeClass("active");
        $(".tabs h6").removeClass("font-weight-bold");
        $(".tabs h6").addClass("text-muted");
        $(this).children("h6").removeClass("text-muted");
        $(this).children("h6").addClass("font-weight-bold");
        $(this).addClass("active");

        current_fs = $(".active");

        next_fs = $(this).attr('id');
        next_fs = "#" + next_fs + "1";

        $("fieldset").removeClass("show");
        $(next_fs).addClass("show");

        current_fs.animate({}, {
            step: function () {
                current_fs.css({
                    'display': 'none',
                    'position': 'relative'
                });
                next_fs.css({
                    'display': 'block'
                });
            }
        });
    });
});

document.getElementById("homeButton").onclick = function () {
    location.href = "/logout";
};

function buyTradeWithValidation() {
    if (document.getElementById("buyForm").value *
        document.getElementById("coin-price").value >
        document.getElementById("user-transaction-balance").value) ;
    alert("You do not have enough money to proceed with this transaction. "
        + " Your balance is: " + document.getElementById("user-transaction-balance").value);
}

// var numToFormat = document.getElementsByClassName("number-formatter");
// numToFormat.addEventListener('cli')

function numberFormatter(num) {
    this.value = getFormattedNumber(num);
}

function getFormattedNumber(num) {
    if (num == "-") {
        return "";
    }
    var n = Number(num);
    var value = n.toLocaleString("en");
    return value;
}