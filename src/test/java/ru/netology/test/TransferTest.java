package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;
import ru.netology.page.DashboardPage;


import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;

public class TransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        clearBrowserCookies();
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards1() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
        val DashboardPage = new DashboardPage();
        val balance = DashboardPage.getCardBalance(first);
        val transactionInformation = DashboardPage.transfersInfo();
        val cardNumber2 = DataHelper.getCardNumber2();
        val AmountSum = DataHelper.getTransferAmount();
        transactionInformation.transfersMoney(cardNumber2,AmountSum);


    }
}
