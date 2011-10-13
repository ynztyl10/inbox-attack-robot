package com.opera.core.systems.inboxattack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class GameReady implements ExpectedCondition<WebElement> {

  //      wait.until(new ExpectedCondition<WebElement>() {
  public WebElement apply(WebDriver driver) {
    return driver.findElement(By.tagName("svg"));
  }

}