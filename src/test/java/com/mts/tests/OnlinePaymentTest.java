package com.mts.tests.tests;

import com.mts.tests.BaseTest;
import com.mts.tests.pages.OnlinePaymentPage;
import com.mts.tests.pages.PaymentModalPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OnlinePaymentTest extends BaseTest {

    @Test
    @DisplayName("Полная проверка блока онлайн пополнения")
    void testOnlinePaymentBlock() {
        OnlinePaymentPage paymentPage = new OnlinePaymentPage(driver);

        // 1. Проверка названия блока
        assertEquals("Онлайн пополнение без комиссии", paymentPage.getBlockTitle());

        // 2. Проверка логотипов платежных систем
        assertTrue(paymentPage.getPaymentLogosCount() >= 3);

        // 3. Проверка работы ссылки "Подробнее о сервисе"
        String originalWindow = driver.getWindowHandle();
        paymentPage.clickDetailsLink();
        paymentPage.switchToNewWindow();
        assertNotEquals(originalWindow, driver.getWindowHandle());
        driver.close();
        driver.switchTo().window(originalWindow);

        // 4. Проверка надписей в полях для разных вариантов оплаты
        checkAllPaymentOptionsPlaceholders(paymentPage);

        // 5. Проверка формы услуг связи
        testServicesPaymentForm(paymentPage);
    }

    private void checkAllPaymentOptionsPlaceholders(OnlinePaymentPage paymentPage) {
        // Услуги связи
        paymentPage.selectServicesTab();
        String servicesPlaceholder = paymentPage.getPhoneInputPlaceholder();
        assertTrue(servicesPlaceholder.contains("номер") || servicesPlaceholder.contains("телефон"));

        // Домашний интернет
        paymentPage.selectInternetTab();
        List<String> internetPlaceholders = paymentPage.getAllPlaceholders();
        assertFalse(internetPlaceholders.isEmpty());

        // Рассрочка
        paymentPage.selectInstallmentTab();
        List<String> installmentPlaceholders = paymentPage.getAllPlaceholders();
        assertFalse(installmentPlaceholders.isEmpty());

        // Задолженность
        paymentPage.selectDebtTab();
        List<String> debtPlaceholders = paymentPage.getAllPlaceholders();
        assertFalse(debtPlaceholders.isEmpty());

        // Возвращаемся к услугам связи для следующего теста
        paymentPage.selectServicesTab();
    }

    private void testServicesPaymentForm(OnlinePaymentPage paymentPage) {
        // Заполняем форму
        paymentPage.selectServicesTab();
        paymentPage.enterPhoneNumber("297777777");
        paymentPage.clickContinue();

        // Переключаемся на модальное окно оплаты
        PaymentModalPage modalPage = new PaymentModalPage(driver);

        // Проверяем отображение суммы
        String amount = modalPage.getAmount();
        assertNotNull(amount);
        assertFalse(amount.isEmpty());

        // Проверяем номер телефона
        String phoneNumber = modalPage.getPhoneNumber();
        assertTrue(phoneNumber.contains("297777777"));

        // Проверяем сумму на кнопке
        String payButtonText = modalPage.getPayButtonText();
        assertTrue(payButtonText.contains(amount.replaceAll("[^\\d.]", "")));

        // Проверяем надписи в полях карты
        assertEquals("Номер карты", modalPage.getCardNumberPlaceholder());
        assertEquals("Срок действия", modalPage.getExpiryDatePlaceholder());
        assertEquals("CVV", modalPage.getCvvPlaceholder());

        // Проверяем иконки платежных систем
        assertTrue(modalPage.getCardIconsCount() >= 3);
        assertTrue(modalPage.isCardIconsDisplayed());
    }
}