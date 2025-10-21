package com.mts.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentLogosTest extends BaseTest {

    @Test
    @DisplayName("2. Проверка наличия логотипов платёжных систем")
    void testPaymentSystemLogos() {
        List<WebElement> paymentLogos = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.xpath("//div[contains(@class, 'pay-systems')]//img | " +
                                "//div[contains(@class, 'payment')]//img | " +
                                "//div[contains(@class, 'logo')]//img")
                ));

        assertTrue(paymentLogos.size() >= 3,
                "Найдено недостаточно логотипов платежных систем. Найдено: " + paymentLogos.size());

        for (WebElement logo : paymentLogos) {
            String src = logo.getAttribute("src");
            assertNotNull(src, "Логотип не имеет атрибута src");
            assertFalse(src.isEmpty(), "Атрибут src логотипа пустой");
        }

        System.out.println("✓ Найдено логотипов платежных систем: " + paymentLogos.size());
    }
}