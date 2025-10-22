package com.mts.tests;

import com.mts.tests.BaseTest;
import com.mts.tests.pages.OnlinePaymentPage;
import com.mts.tests.pages.PaymentModalPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Тесты сайта MTS.by")
@Feature("Онлайн пополнение без комиссии")
@Story("Проверка функциональности блока онлайн пополнения")
public class OnlinePaymentTest extends BaseTest {

    @Test
    @DisplayName("Полная проверка блока онлайн пополнения")
    @Description("Этот тест проверяет весь функционал блока онлайн пополнения: заголовок, логотипы, ссылки, форму оплаты")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Тестировщик")
    void testOnlinePaymentBlock() {
        OnlinePaymentPage paymentPage = new OnlinePaymentPage(driver);

        Allure.step("Проверяем, что страница загрузилась", () -> {
            assertNotNull(paymentPage.getCurrentUrl());
            assertTrue(paymentPage.getCurrentUrl().contains("mts.by"));
        });

        // 1. Проверка названия блока
        checkBlockTitle(paymentPage);

        // 2. Проверка логотипов платежных систем
        checkPaymentLogos(paymentPage);

        // 3. Проверка работы ссылки "Подробнее о сервисе"
        checkDetailsLink(paymentPage);

        // 4. Проверка надписей в полях для разных вариантов оплаты
        checkAllPaymentOptionsPlaceholders(paymentPage);

        // 5. Проверка формы услуг связи
        testServicesPaymentForm(paymentPage);

        Allure.step("Все проверки блока онлайн пополнения завершены успешно");
    }

    @Step("Проверка заголовка блока")
    private void checkBlockTitle(OnlinePaymentPage paymentPage) {
        Allure.step("Проверяем, что заголовок отображается", () -> {
            assertTrue(paymentPage.isBlockTitleDisplayed(), "Заголовок блока не отображается");
        });

        String title = paymentPage.getBlockTitle();

        Allure.step("Проверяем текст заголовка", () -> {
            assertNotNull(title, "Заголовок не должен быть null");
            assertFalse(title.isEmpty(), "Заголовок не должен быть пустым");
            assertTrue(title.contains("Онлайн") || title.contains("пополнение") || title.contains("комиссия"),
                    "Заголовок должен содержать ключевые слова. Фактический заголовок: " + title);
        });

        Allure.addAttachment("Заголовок блока", title);
        takeScreenshot("block_title");
    }

    @Step("Проверка логотипов платежных систем")
    private void checkPaymentLogos(OnlinePaymentPage paymentPage) {
        int logosCount = paymentPage.getPaymentLogosCount();

        Allure.step("Проверяем количество логотипов", () -> {
            assertTrue(logosCount >= 1, "Должен быть найден хотя бы один логотип. Найдено: " + logosCount);
        });

        Allure.addAttachment("Количество логотипов", String.valueOf(logosCount));
        takeScreenshot("payment_logos");
    }

    @Step("Проверка работы ссылки 'Подробнее о сервисе'")
    private void checkDetailsLink(OnlinePaymentPage paymentPage) {
        Allure.step("Проверяем, что ссылка отображается", () -> {
            assertTrue(paymentPage.isDetailsLinkDisplayed(), "Ссылка 'Подробнее о сервисе' не отображается");
        });

        String originalUrl = paymentPage.getCurrentUrl();
        paymentPage.clickDetailsLink();
        paymentPage.switchToNewWindow();

        // Ждем загрузки новой страницы
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String newUrl = paymentPage.getCurrentUrl();

        Allure.step("Проверяем, что URL изменился", () -> {
            assertNotEquals(originalUrl, newUrl, "URL должен измениться после перехода по ссылке");
        });

        Allure.addAttachment("Новый URL", newUrl);
        takeScreenshot("details_link_page");

        // Закрываем новое окно и возвращаемся обратно
        driver.close();
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

    @Step("Проверка надписей в полях для всех вариантов оплаты")
    private void checkAllPaymentOptionsPlaceholders(OnlinePaymentPage paymentPage) {
        // Услуги связи
        checkServicesTabPlaceholders(paymentPage);

        // Домашний интернет
        checkInternetTabPlaceholders(paymentPage);

        // Рассрочка
        checkInstallmentTabPlaceholders(paymentPage);

        // Задолженность
        checkDebtTabPlaceholders(paymentPage);

        // Возвращаемся к услугам связи
        paymentPage.selectServicesTab();
    }

    @Step("Проверка плейсхолдера для услуг связи")
    private void checkServicesTabPlaceholders(OnlinePaymentPage paymentPage) {
        paymentPage.selectServicesTab();
        String servicesPlaceholder = paymentPage.getPhoneInputPlaceholder();

        Allure.step("Проверяем плейсхолдер услуг связи", () -> {
            assertNotNull(servicesPlaceholder, "Плейсхолдер не должен быть null");
        });

        Allure.addAttachment("Плейсхолдер услуг связи", servicesPlaceholder);
        takeScreenshot("services_tab");
    }

    @Step("Проверка плейсхолдеров для домашнего интернета")
    private void checkInternetTabPlaceholders(OnlinePaymentPage paymentPage) {
        paymentPage.selectInternetTab();
        List<String> internetPlaceholders = paymentPage.getAllPlaceholders();

        Allure.step("Проверяем плейсхолдеры интернета", () -> {
            assertNotNull(internetPlaceholders, "Список плейсхолдеров не должен быть null");
        });

        Allure.addAttachment("Плейсхолдеры интернета", String.join(", ", internetPlaceholders));
        takeScreenshot("internet_tab");
    }

    @Step("Проверка плейсхолдеров для рассрочки")
    private void checkInstallmentTabPlaceholders(OnlinePaymentPage paymentPage) {
        paymentPage.selectInstallmentTab();
        List<String> installmentPlaceholders = paymentPage.getAllPlaceholders();

        Allure.step("Проверяем плейсхолдеры рассрочки", () -> {
            assertNotNull(installmentPlaceholders, "Список плейсхолдеров не должен быть null");
        });

        Allure.addAttachment("Плейсхолдеры рассрочки", String.join(", ", installmentPlaceholders));
        takeScreenshot("installment_tab");
    }

    @Step("Проверка плейсхолдеров для задолженности")
    private void checkDebtTabPlaceholders(OnlinePaymentPage paymentPage) {
        paymentPage.selectDebtTab();
        List<String> debtPlaceholders = paymentPage.getAllPlaceholders();

        Allure.step("Проверяем плейсхолдеры задолженности", () -> {
            assertNotNull(debtPlaceholders, "Список плейсхолдеров не должен быть null");
        });

        Allure.addAttachment("Плейсхолдеры задолженности", String.join(", ", debtPlaceholders));
        takeScreenshot("debt_tab");
    }

    @Step("Проверка формы оплаты услуг связи")
    private void testServicesPaymentForm(OnlinePaymentPage paymentPage) {
        // Возвращаемся к услугам связи
        paymentPage.selectServicesTab();

        // Заполняем форму
        paymentPage.enterPhoneNumber("297777777");
        takeScreenshot("form_after_phone_input");

        paymentPage.clickContinue();

        // Ждем загрузки модального окна
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Переключаемся на модальное окно оплаты
        PaymentModalPage modalPage = new PaymentModalPage(driver);
        modalPage.waitForModalToLoad();

        // Проверяем отображение суммы
        checkPaymentAmount(modalPage);

        // Проверяем номер телефона
        checkPhoneNumber(modalPage);

        // Проверяем сумму на кнопке
        checkPayButtonAmount(modalPage);

        // Проверяем надписи в полях карты
        checkCardFieldsPlaceholders(modalPage);

        // Проверяем иконки платежных систем
        checkCardIcons(modalPage);

        takeScreenshot("payment_modal_final");
    }

    @Step("Проверка отображаемой суммы платежа")
    private void checkPaymentAmount(PaymentModalPage modalPage) {
        Allure.step("Проверяем, что сумма отображается", () -> {
            assertTrue(modalPage.isAmountDisplayed(), "Сумма платежа должна отображаться");
        });

        String amount = modalPage.getAmount();

        Allure.step("Проверяем значение суммы", () -> {
            assertNotNull(amount, "Сумма не должна быть null");
            assertFalse(amount.isEmpty(), "Сумма не должна быть пустой");
        });

        Allure.addAttachment("Сумма платежа", amount);
    }

    @Step("Проверка номера телефона в модальном окне")
    private void checkPhoneNumber(PaymentModalPage modalPage) {
        Allure.step("Проверяем, что номер телефона отображается", () -> {
            assertTrue(modalPage.isPhoneNumberDisplayed(), "Номер телефона должен отображаться");
        });

        String phoneNumber = modalPage.getPhoneNumber();

        Allure.step("Проверяем значение номера телефона", () -> {
            assertNotNull(phoneNumber, "Номер телефона не должен быть null");
            assertTrue(phoneNumber.contains("297777777"), "Номер телефона должен содержать 297777777. Фактический: " + phoneNumber);
        });

        Allure.addAttachment("Номер телефона", phoneNumber);
    }

    @Step("Проверка суммы на кнопке оплаты")
    private void checkPayButtonAmount(PaymentModalPage modalPage) {
        Allure.step("Проверяем, что кнопка оплаты отображается", () -> {
            assertTrue(modalPage.isPayButtonDisplayed(), "Кнопка оплаты должна отображаться");
        });

        String payButtonText = modalPage.getPayButtonText();

        Allure.step("Проверяем текст кнопки", () -> {
            assertNotNull(payButtonText, "Текст кнопки не должен быть null");
            assertFalse(payButtonText.isEmpty(), "Текст кнопки не должен быть пустым");
        });

        Allure.addAttachment("Текст кнопки оплаты", payButtonText);
    }

    @Step("Проверка плейсхолдеров полей карты")
    private void checkCardFieldsPlaceholders(PaymentModalPage modalPage) {
        String cardNumberPlaceholder = modalPage.getCardNumberPlaceholder();
        String expiryDatePlaceholder = modalPage.getExpiryDatePlaceholder();
        String cvvPlaceholder = modalPage.getCvvPlaceholder();

        Allure.step("Проверяем плейсхолдеры полей карты", () -> {
            assertNotNull(cardNumberPlaceholder, "Плейсхолдер номера карты не должен быть null");
            assertNotNull(expiryDatePlaceholder, "Плейсхолдер срока действия не должен быть null");
            assertNotNull(cvvPlaceholder, "Плейсхолдер CVV не должен быть null");
        });

        String placeholdersInfo = "Номер карты: " + cardNumberPlaceholder + "\n" +
                "Срок действия: " + expiryDatePlaceholder + "\n" +
                "CVV: " + cvvPlaceholder;
        Allure.addAttachment("Плейсхолдеры полей карты", placeholdersInfo);
    }

    @Step("Проверка иконок платежных систем")
    private void checkCardIcons(PaymentModalPage modalPage) {
        int iconsCount = modalPage.getCardIconsCount();

        Allure.step("Проверяем иконки платежных систем", () -> {
            assertTrue(iconsCount >= 1, "Должна быть найдена хотя бы одна иконка платежной системы. Найдено: " + iconsCount);
            assertTrue(modalPage.isCardIconsDisplayed(), "Иконки платежных систем должны отображаться");
        });

        Allure.addAttachment("Количество иконок платежных систем", String.valueOf(iconsCount));
    }
}