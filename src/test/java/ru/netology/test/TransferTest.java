package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.DashboardPage;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        clearBrowserCookies();
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val DashboardPage = new DashboardPage();
        val amountValue = "100";
        val numberFistCard = DataHelper.getCardNumber().getCard01();
        val numberSecondCard = DataHelper.getCardNumber().getCard02();
        val startBalanceFirstCard = DashboardPage.getCardBalance(numberFistCard);
        val startBalanceSecondCard = DashboardPage.getCardBalance(numberSecondCard);
        val transactionInformation = DashboardPage.transfersToFirstCard();
        transactionInformation.transfersMoney(amountValue, numberSecondCard);
        val updatedBalanceFirstCard = DashboardPage.getCardBalance(numberFistCard);
        val updatedBalanceSecondCard = DashboardPage.getCardBalance(numberSecondCard);
        val amount = updatedBalanceFirstCard - startBalanceFirstCard;
        val expectedBalanceFirstCard = startBalanceFirstCard + amount;
        val expectedBalanceSecondCard = startBalanceSecondCard - amount;
        assertEquals(expectedBalanceFirstCard, updatedBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, updatedBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val DashboardPage = new DashboardPage();
        val amountValue = "100";
        val numberFistCard = DataHelper.getCardNumber().getCard01();
        val numberSecondCard = DataHelper.getCardNumber().getCard02();
        val startBalanceFirstCard = DashboardPage.getCardBalance(numberFistCard);
        val startBalanceSecondCard = DashboardPage.getCardBalance(numberSecondCard);
        val transactionInformation = DashboardPage.transfersToSecondCard();
        transactionInformation.transfersMoney(amountValue, numberFistCard);
        val updatedBalanceFirstCard = DashboardPage.getCardBalance(numberFistCard);
        val updatedBalanceSecondCard = DashboardPage.getCardBalance(numberSecondCard);
        val amount = updatedBalanceFirstCard - startBalanceFirstCard;
        val expectedBalanceFirstCard = startBalanceFirstCard + amount;
        val expectedBalanceSecondCard = startBalanceSecondCard - amount;
        assertEquals(expectedBalanceFirstCard, updatedBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, updatedBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCardMoreBalance() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val DashboardPage = new DashboardPage();
        val amountValue = "11000";
        val numberFistCard = DataHelper.getCardNumber().getCard01();
        val numberSecondCard = DataHelper.getCardNumber().getCard02();
        val startBalanceFirstCard = DashboardPage.getCardBalance(numberFistCard);
        val startBalanceSecondCard = DashboardPage.getCardBalance(numberSecondCard);
        val transactionInformation = DashboardPage.transfersToSecondCard();
        transactionInformation.transfersMoney(amountValue, numberFistCard);
        val updatedBalanceFirstCard = DashboardPage.getCardBalance(numberFistCard);
        val updatedBalanceSecondCard = DashboardPage.getCardBalance(numberSecondCard);
        val amount = updatedBalanceFirstCard - startBalanceFirstCard;
        val expectedBalanceFirstCard = startBalanceFirstCard;
        val expectedBalanceSecondCard = startBalanceSecondCard;
        assertEquals(expectedBalanceFirstCard, updatedBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, updatedBalanceSecondCard);
        DashboardPage.notificationTransfer();
    }

}
