package com.mts.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentFormTest extends BaseTest {

    @Test
    @DisplayName("4. Проверка работы формы пополнения счёта")
    void testPaymentForm() {
        try {
            WebElement serviceType = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//*[contains(text(), 'Услуги связи') or " +
                                    "contains(@class, 'service') or " +
                                    "//button[contains(text(), 'Услуги')]")
                    ));
            serviceType.click();
            WebElement phoneInput = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//input[@type='tel' or " +
                                    "contains(@placeholder, 'номер') or " +
                                    "contains(@name, 'phone') or " +
                                    "//input[contains(@class, 'phone')]]")
                    ));

            phoneInput.clear();
            phoneInput.sendKeys("297777777");
            WebElement continueButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[contains(text(), 'Продолжить') or " +
                                    "//input[@type='submit' and contains(@value, 'Продолжить')]")
                    ));
            continueButton.click();
            Thread.sleep(3000);

            String currentUrl = driver.getCurrentUrl();
            String pageSource = driver.getPageSource();

            boolean hasErrors = pageSource.contains("error") ||
                    pageSource.contains("ошибка") ||
                    driver.findElements(By.xpath("//*[contains(@class, 'error')]")).size() > 0;

            assertFalse(hasErrors, "Обнаружены ошибки на странице после отправки формы");

            System.out.println("✓ Форма пополнения счёта отработала, текущий URL: " + currentUrl);

        } catch (Exception e) {
            System.out.println("Ошибка при работе с формой: " + e.getMessage());
            fail("Не удалось проверить форму пополнения счёта: " + e.getMessage());
        }
    }
}