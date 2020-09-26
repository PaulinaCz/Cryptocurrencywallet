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

function buyTradeWithValidation() {

    if ((document.getElementById("buyForm").value *
        document.getElementById("coin-price").value) >
        document.getElementById("user-transaction-balance").value) {
        alert("You do not have enough funds to proceed with this transaction. "
            + "\nYour balance is: " + document.getElementById("user-transaction-balance").value);
    }else {

        buyTrade();
    }
}
function showCurrentDetails(){
    console.log(document.getElementById("buyForm").value + " <<< user input");
    console.log(document.getElementById("coin-price").value+ " <<< coin price");
    console.log(document.getElementById("user-transaction-balance").value + " <<< user balance");
}

function sellTradeWithValidation() {

    if ((document.getElementById("buyForm-sell").value >
        document.getElementById("coin-price-sell").value)) {
        alert("You do not have enough coins to proceed with this transaction. "
            + "\nYour balance is: " + document.getElementById("coin-price-sell").value);
    }
    sellTrade();
}

function showCurrentDetailsSell(){
    console.log(document.getElementById("buyForm-sell").value + " <<< user input");
    console.log(document.getElementById("testTag").value+ " <<< user number of coins");
}
// var numToFormat = document.getElementsByClassName("number-formatter");
// numToFormat.addEventListener('cli')



function getFormattedNumber(num) {
    if (num == "-") {
        return "";
    }
    var n = Number(num);
    var value = n.toLocaleString("en");
    return value;
}
function reverseNumberFormat(num) {
    return Number(num.replace(/,/g, ""));
}

function getSelectedCoinName() {
    var sel = document.getElementById("coin-price");
    return sel.options[sel.selectedIndex].text;
}
function getSelectedCoinNameSell() {
    var sel = document.getElementById("coin-price-sell");
    return sel.options[sel.selectedIndex].text;
}

function printOutput(num) {
    if (num == "") {
        document.getElementById("output-value").innerText = num;
    } else {
        document.getElementById("output-value").innerText = getFormattedNumber(num);
    }
}

var numOne = document.getElementById("buyForm");
var numTwo = document.getElementById("coin-price");
var sum = document.getElementById("output-value");

numOne.addEventListener("input", add);
numTwo.addEventListener("input", add);

function add(){
    var one = numOne.value;
    var two = numTwo.value;
    if (!isNaN((one*two))){
        sum.innerText ='$'+ one*two;
    } else {
        sum.innerText = "Enter number!"
    }
}



// var number = document.getElementById("buyForm");
// number.addEventListener("input", function(ev){
//     console.log(ev.type, ev.target);
//     let target = ev.currentTarget;
//     let tag = target.tagName;
//     let char = ev.char || ev.charCode || ev.which;
//     let s = String.fromCharCode(char);
//     printOutput(s);
// })

/*
var output = getOutput();
if (output != NaN){
    output = output+this.id;
    printOutput(output);*/
