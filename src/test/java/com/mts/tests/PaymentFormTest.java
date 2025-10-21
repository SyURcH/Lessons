package com.mts.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentFormTest extends BaseTest {

    @Test
    @DisplayName("4. Проверка работы формы пополнения счёта")
    void testPaymentForm() {
        WebElement serviceType = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//label[contains(., 'Услуги связи')] | " +
                                "//input[@type='radio'][following-sibling::label[contains(., 'Услуги связи')]] | " +
                                "//div[contains(@class, 'service-type')]//*[contains(text(), 'Услуги связи')]")
                ));
        serviceType.click();

        WebElement phoneInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@type='tel'] | " +
                                "//input[contains(@placeholder, 'номер')] | " +
                                "//input[contains(@name, 'phone')]")
                ));

        phoneInput.clear();
        phoneInput.sendKeys("297777777");

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(), 'Продолжить')] | " +
                                "//input[@type='submit'][contains(@value, 'Продолжить')]")
                ));
        continueButton.click();

        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("mts.by/")));

            WebElement nextPageElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(), 'оплат') or " +
                                    "contains(text(), 'платёж') or " +
                                    "contains(text(), 'сумма')]")
                    ));
            assertTrue(nextPageElement.isDisplayed(),
                    "Страница оплаты не отобразилась");

        } catch (TimeoutException e) {
            List<WebElement> errorMessages = driver.findElements(
                    By.xpath("//*[contains(@class, 'error') or contains(@class, 'invalid')]"));

            if (!errorMessages.isEmpty()) {
                fail("Обнаружены ошибки формы: " +
                        errorMessages.get(0).getText());
            } else {
                fail("Не произошел переход на страницу оплаты и нет сообщений об ошибке");
            }
        }

        System.out.println("✓ Форма пополнения счёта работает корректно");
    }
}