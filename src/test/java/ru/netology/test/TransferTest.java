package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.DashboardPage;


import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;
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
        int startBalanceFirstCard = DashboardPage.getCardBalance("5559 0000 0000 0001");
        int startBalanceSecondCard = DashboardPage.getCardBalance("5559 0000 0000 0002");
        val transactionInformation = DashboardPage.transfersToFirstCard();
        transactionInformation.transfersMoney("100", "5559 0000 0000 0002");
        int updatedBalanceFirstCard = DashboardPage.getCardBalance("5559 0000 0000 0001");
        int updatedBalanceSecondCard = DashboardPage.getCardBalance("5559 0000 0000 0002");
        int amount = updatedBalanceFirstCard-startBalanceFirstCard;
        int expectedBalanceFirstCard = startBalanceFirstCard+amount;
        int expectedBalanceSecondCard = startBalanceSecondCard-amount;
        assertEquals(expectedBalanceFirstCard,updatedBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard,updatedBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val DashboardPage = new DashboardPage();
        int startBalanceFirstCard = DashboardPage.getCardBalance("5559 0000 0000 0001");
        int startBalanceSecondCard = DashboardPage.getCardBalance("5559 0000 0000 0002");
        val transactionInformation = DashboardPage.transfersToSecondCard();
        transactionInformation.transfersMoney("100", "5559 0000 0000 0001");
        int updatedBalanceFirstCard = DashboardPage.getCardBalance("5559 0000 0000 0001");
        int updatedBalanceSecondCard = DashboardPage.getCardBalance("5559 0000 0000 0002");
        int amount = updatedBalanceFirstCard-startBalanceFirstCard;
        int expectedBalanceFirstCard = startBalanceFirstCard+amount;
        int expectedBalanceSecondCard = startBalanceSecondCard-amount;
        assertEquals(expectedBalanceFirstCard,updatedBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard,updatedBalanceSecondCard);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCardMoreBalance() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val DashboardPage = new DashboardPage();
        int startBalanceFirstCard = DashboardPage.getCardBalance("5559 0000 0000 0001");
        int startBalanceSecondCard = DashboardPage.getCardBalance("5559 0000 0000 0002");
        val transactionInformation = DashboardPage.transfersToSecondCard();
        transactionInformation.transfersMoney("11000", "5559 0000 0000 0001");
        int updatedBalanceFirstCard = DashboardPage.getCardBalance("5559 0000 0000 0001");
        int updatedBalanceSecondCard = DashboardPage.getCardBalance("5559 0000 0000 0002");
        int amount = updatedBalanceFirstCard-startBalanceFirstCard;
        int expectedBalanceFirstCard = startBalanceFirstCard+amount;
        int expectedBalanceSecondCard = startBalanceSecondCard-amount;
        assertEquals(expectedBalanceFirstCard,updatedBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard,updatedBalanceSecondCard);
    }

}
