package com.example.cryptocurrencywallet.service;

import com.example.cryptocurrencywallet.model.Coin;
import com.example.cryptocurrencywallet.model.TransactionHistory;
import com.example.cryptocurrencywallet.model.User;
import com.example.cryptocurrencywallet.model.Wallet;
import com.example.cryptocurrencywallet.repository.UserRepository;
import com.example.cryptocurrencywallet.model.BuySell;
import com.example.cryptocurrencywallet.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public User processTransaction(Transaction transaction, Wallet userWallet) throws CoinInUserWalletNotFound, InsufficientFundsException, InsufficientCoinException {
        LOGGER.info("  ------------- PROCESS TRANSACTION METHOD ------------ ");

        List<TransactionHistory> userTransactions = userWallet.getTransactionHistories();
        LOGGER.info("Number of transactions: " + userTransactions.size());

        List<Coin> userCoinsInWallet = userWallet.getMyCoins();
        LOGGER.info("Number of coin types in wallet: " + userCoinsInWallet.size());

        Coin newCoin = new Coin(transaction.getName(), transaction.getAmount(), transaction.getPrice());
        LOGGER.info("Coin created: \n" + newCoin.toString());

        LOGGER.info("User wallet before transaction: \n" + userWallet.toString());
        if (transaction.getBuySell() == BuySell.BUY) {
            LOGGER.info("------------- BUY TRANSACTION ----------");
            if (isEnoughFunds(transaction, userWallet)) {
                transaction.setExecuted(true);
                transaction.setAmountUSD(transaction.getTotalPriceForTransaction());

                userWallet.setBalanceUSD(userWallet.getBalanceUSD().subtract(transaction.getAmountUSD()));
                userTransactions.add(new TransactionHistory(transaction));
                if (isNewCoin(userCoinsInWallet, newCoin)) {
                    userCoinsInWallet.add(newCoin);
                } else {
                  updateUserCoinAmount(newCoin, userWallet);
                }
                LOGGER.info("User wallet after buy transaction: \n" + userWallet.toString());
            } else {
                throw new InsufficientFundsException("Not enough funds to process transaction");
            }
        } else {
            LOGGER.info("------------- SELL TRANSACTION ----------");
            if (isEnoughCoinsInWallet(newCoin, userWallet)) {
                transaction.setExecuted(true);
                transaction.setAmountUSD(transaction.getTotalPriceForTransaction());
                setNewWalletBalance(transaction, userWallet);
                setNewCoinAmount(newCoin, userWallet);
                userTransactions.add(new TransactionHistory(transaction));
            } else {
                throw new InsufficientCoinException("Not enough coins to process transaction");
            }
            LOGGER.info("User wallet after sell transaction: \n" + userWallet.toString());
        }
        return userRepository.save(userWallet.getUser());
    }

    private Wallet setNewCoinAmount(Coin newCoin, Wallet userWallet) throws CoinInUserWalletNotFound {
        Coin coinInUserWallet = findCoinInUserWallet(newCoin, userWallet);
        coinInUserWallet.setAmount(coinInUserWallet.getAmount().subtract(newCoin.getAmount()));
        return userWallet;
    }

    private Wallet setNewWalletBalance(Transaction transaction, Wallet userWallet) {
        userWallet.setBalanceUSD(userWallet.getBalanceUSD().add(transaction.getAmountUSD()));
        return userWallet;
    }

    private boolean isEnoughCoinsInWallet(Coin newCoin, Wallet userWallet) throws CoinInUserWalletNotFound {
        BigDecimal numberOfCoinsUserWantToSell = newCoin.getAmount();
        LOGGER.info("number of "+ newCoin.getCoinName() + " user want to sell: " + numberOfCoinsUserWantToSell.toString());
        Coin coinFromUserWallet = findCoinInUserWallet(newCoin, userWallet);
        BigDecimal numberOfCoinsInUserWallet = coinFromUserWallet.getAmount();
        LOGGER.info("number of "+ newCoin.getCoinName() + " in user wallet: " + numberOfCoinsInUserWallet.toString());
        LOGGER.info("result of numberOfCoinsUserWantToSell.compareTo(numberOfCoinsInUserWallet "+ numberOfCoinsUserWantToSell.compareTo(numberOfCoinsInUserWallet));
        return numberOfCoinsUserWantToSell.compareTo(numberOfCoinsInUserWallet) < 0;

    }

    private Coin findCoinInUserWallet(Coin newCoin, Wallet userWallet) throws CoinInUserWalletNotFound {

            String coinName = newCoin.getCoinName();
            return userWallet.getMyCoins()
                    .stream()
                    .filter(coin -> coin.getCoinName().equals(coinName))
                    .findFirst()
                    .orElseThrow(() -> new CoinInUserWalletNotFound("Type of coin " + coinName + " not found in user wallet."));

    }


    private BigDecimal updateUserCoinAmount(Coin newCoin, Wallet userWallet) throws CoinInUserWalletNotFound {
        Coin userCoin = findCoinInUserWallet(newCoin, userWallet);
        userCoin.setAmount(userCoin.getAmount().add(newCoin.getAmount()));
        return userCoin.getAmount();
    }

    private boolean isNewCoin(List<Coin> userCoinsInWallet, Coin newCoin) {
        return userCoinsInWallet.stream()
                .map(Coin::getCoinName)
                .noneMatch(coin -> coin.equalsIgnoreCase(newCoin.getCoinName()));

    }

    private boolean isEnoughFunds(Transaction transaction, Wallet loggedUserWallet) {
        return loggedUserWallet.getBalanceUSD().subtract(transaction.getTotalPriceForTransaction()).compareTo(BigDecimal.ZERO) >= 0;
    }
}
