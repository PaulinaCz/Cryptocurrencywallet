function getHostUrl() {
    const HOST_URL = "http://localhost:8080/user"
    return HOST_URL;
}

function buyTrade() {
    const HOST_URL = getHostUrl();
    // stopInterval();
    console.log('processing buy trade');
    var buyTrade = {};


    buyTrade.name = getSelectedCoinName();
    buyTrade.price = document.getElementById("coin-price").value;
    buyTrade.amount = document.getElementById("buyForm").value;
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
};
