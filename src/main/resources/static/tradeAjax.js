function getHostUrl() {
    const HOST_URL = "http://localhost:8080/user"
    return HOST_URL;
}

function buyTrade() {
    const HOST_URL = getHostUrl();
    // stopInterval();
    console.log('processing buy trade');
    let buyTrade = {};


    buyTrade.name = getSelectedCoinName();
    buyTrade.price = document.getElementById("coin-price").value;
    buyTrade.amount = document.getElementById("buy-form-user-input").value;
    console.log(buyTrade.name + " -- JSON coinName");
    console.log(buyTrade.price + " -- JSON price");
    console.log(buyTrade.amount + " -- JSON amount");
    $.ajax({
        url: HOST_URL + "/transaction/buy",
        type: "POST",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        data: JSON.stringify(buyTrade)
        // data: JSON.stringify(buyTrade)
    })
        // .done(function (data) {
        // console.log('buy AJAX done' + data.toString());
        // var coin = jQuery.parseJSON(JSON.stringify(data));
        // console.log(coin);
        // $("#tradeResultModal").modal();
        // if (true) {
        //     document.getElementById("tradeResultTRX").innerHTML =
        //         "<p>Date time: " + transaction.tradeDateTime + "</p>"
        //         + "<p>Trade side: " + transaction.buySell + "</p>"
        //         + "<p>instrument: " + transaction.instrument + "</p>"
        //         + "<p>Amount: " + transaction.amount + "</p>"
        //         + "<p>Amount in PLN: " + transaction.amountPLN + "</p>"
        //         + "<p>Trade price: " + transaction.price + "</p>";
        // } else {
        //     document.getElementById("tradeResultTRX").innerHTML = "Transaction not executed. Reason: ";
        // }
        // })
        .fail(function () {
            console.log('ajax failed');
        });

    reloadPage();
};

function sellTrade() {
    const HOST_URL = getHostUrl();
    console.log('processing sell trade');
    let sellTrade = {};

    sellTrade.name = userCoinToSell.value;
    sellTrade.price = coinPrice;
    sellTrade.amount =formUserInputSell.value;
    console.log(sellTrade.name + " -- JSON SELL coinName");
    console.log(sellTrade.price + " -- JSON SELL price");
    console.log(sellTrade.amount + " -- JSON SELL amount");
    $.ajax({
        url: HOST_URL + "/transaction/sell",
        type: "POST",
        headers: {
            'Accept': 'application/json',
            "Content-Type": "application/json"
        },
        data: JSON.stringify(sellTrade)
    })/*.done(function (data) {
        console.log('sell AJAX done' + data.toString());
        var transaction = jQuery.parseJSON(JSON.stringify(data));
        console.log(transaction);
        $("#tradeResultModal").modal();
        if (transaction.executed === true) {
            document.getElementById("tradeResultTRX").innerHTML =
                "<p>Date time: " + transaction.tradeDateTime + "</p>"
                + "<p>Trade side: " + transaction.buySell + "</p>"
                + "<p>instrument: " + transaction.instrument + "</p>"
                + "<p>Amount: " + transaction.amount + "</p>"
                + "<p>Amount in PLN: " + transaction.amountPLN + "</p>"
                + "<p>Trade price: " + transaction.price + "</p>";
        } else {
            document.getElementById("tradeResultTRX").innerHTML = "Transaction not executed. Reason: " + transaction.executionFailReason;
        }
    })*/.fail(function () {
        console.log('ajax failed');
    });
    reloadPage();
};
function reloadPage() {
    location.reload();
}