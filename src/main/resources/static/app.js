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

function getSelectedCoinName() {
    let coinName = document.getElementById("coin-price");
    return coinName.options[coinName.selectedIndex].innerText;
}

function getSelectedCoinNameSell() {
    let sel = document.getElementById("coin-price-sell");
    return sel.options[sel.selectedIndex].text;
}


// BUY MODAL
const numOne = document.getElementById("buy-form-user-input");
const numTwo = document.getElementById("coin-price");
const sum = document.getElementById("output-value");
const actualBalance = document.getElementById("user-transaction-balance").value;
const balance = document.getElementById("balance-after-transaction-value");

sum.innerText = 'Current transaction value $0';
balance.innerText = "Your balance after transaction $" + getFormattedNumber(actualBalance);

numOne.addEventListener("input", displayDetailsFormBuy);
numTwo.addEventListener("input", displayDetailsFormBuy);

function displayDetailsFormBuy() {
    let one = numOne.value;
    let two = numTwo.value;
    let myBalance = document.getElementById("user-transaction-balance").value;

    if (!isNaN((one * two))) {
        sum.innerText = 'Current transaction value $' + getFormattedNumber(one * two);
        balance.innerText = 'Your balance after transaction $' + getFormattedNumber(myBalance - (one * two));
    } else {
        sum.innerText = "Enter number!"
    }
    let coinName = document.getElementById("coin-price");
}

function buyTradeWithValidation() {

    if ((numOne.value * numTwo.value) > actualBalance.value) {
        alert("You do not have enough funds to proceed with this transaction. ");
    } else {
        buyTrade();
    }
}

// SELL MODAL
const formUserInputSell = document.getElementById("sell-form-user-input");
const userCoinToSell = document.getElementById("user-coin-to-sell");
const showActualCoinPrice = document.getElementById("coin-price-api");
const showUserCoinAmount = document.getElementById("balance-before-transaction");
const sumTransaction = document.getElementById("output-value-sell");
const showActualUserBalance = document.getElementById("show-actual-balance");
const showUserBalanceAfterTransaction = document.getElementById("balance-after-transaction-sell-form");
let coinPrice = getCoinPrice();
let userCoinAmount = getUserCoinAmount();


sumTransaction.innerText = 'Current transaction value: $0';
showActualCoinPrice.innerText = "Current coin price: $0";
showUserCoinAmount.innerText = "Number of coin in user wallet ";
showActualUserBalance.innerText = "Actual balance : $" + actualBalance;
showUserBalanceAfterTransaction.innerText = "Balance after transaction: $" + actualBalance;

userCoinToSell.addEventListener("input", displayDetailsFormSell);
formUserInputSell.addEventListener("input", displayDetailsFormSell);

function getUserCoinAmount() {
    for (let i = 0; i < userCoinList.length; i++) {
        if (userCoinToSell.value === userCoinList[i].coinName) {
            return parseFloat(userCoinList[i].amount);
        }
    }
}

function getCoinPrice() {
    for (let i = 0; i < apiCoinsList.length; i++) {
        if (userCoinToSell.value === apiCoinsList[i].name) {
            return parseFloat(apiCoinsList[i].price);
        }
    }
}

function displayDetailsFormSell() {
    let userInputValue = formUserInputSell.value;
    coinPrice = getCoinPrice();
    userCoinAmount = getUserCoinAmount();

    showActualCoinPrice.innerText = "Current coin price: $" + getFormattedNumber(coinPrice);

    showUserCoinAmount.innerText = "Coins in user wallet: " + userCoinAmount;

    if (!isNaN((coinPrice * userInputValue))) {
        sumTransaction.innerText = 'Current transaction value $' + getFormattedNumber(coinPrice * userInputValue);
        showUserBalanceAfterTransaction.innerText = 'Your balance after transaction $' + getFormattedNumber(parseFloat(actualBalance) + (coinPrice * userInputValue));
    } else {
        sumTransaction.innerText = "Enter number!"
    }
}

function sellTradeWithValidation() {
    if ((formUserInputSell.value > userCoinAmount)) {
        alert("You do not have enough coins to proceed with this transaction. ");
    } else {
        sellTrade();
    }
}

// TRANSACTION CONFIRMATION
function getConfirmationFormBuy() {
    let confirmTrade = confirm("Do you want to proceed transaction ?");
    if (confirmTrade === true) {
        buyTradeWithValidation();
        return true;
    } else {
        alert("Transaction cancelled!");
        return false;
    }
}

function getConfirmationFormSell() {
    let confirmTrade = confirm("Do you want to proceed transaction ?");
    if (confirmTrade === true) {
        sellTradeWithValidation();
        return true;
    } else {
        alert("Transaction cancelled!");
        return false;
    }
}

// Utilities
function printOutput(num) {
    if (num === "") {
        document.getElementById("output-value").innerText = num;
    } else {
        document.getElementById("output-value").innerText = getFormattedNumber(num);
    }
}


function showCurrentDetails() {
    // console.log(document.getElementById("buy-form-user-input").value + " <<< user input");
    // console.log(document.getElementById("coin-price").value + " <<< coin price");
    // console.log(document.getElementById("user-transaction-balance").value + " <<< user balance");
}

function showCurrentDetailsSell() {
    // console.log(formUserInputSell.value + " <<< formUserInputSell");
    // console.log(userCoinToSell.value + " <<< userCoinToSell");
}

function getFormattedNumber(num) {
    if (num === "-") {
        return "";
    }
    let n = Number(num);
    let value = n.toLocaleString("en");
    return value;
}

function reverseNumberFormat(num) {
    return Number(num.replace(/,/g, ""));
}

// BUTTONS FUNCTIONALITY
function goToAccountDetails(){
    location.replace("http://localhost:8080/accountDetails");
}

function goToHomePageUser(){
    location.replace("http://localhost:8080/user");
}