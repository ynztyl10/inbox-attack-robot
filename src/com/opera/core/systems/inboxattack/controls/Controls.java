package com.opera.core.systems.inboxattack.controls;

import com.opera.core.systems.OperaDriver;
import com.opera.core.systems.OperaKeyboard;
import com.opera.core.systems.OperaWebElement;

import org.openqa.selenium.By;

public class Controls {

  public final OperaWebElement left;
  public final OperaWebElement right;
  public final OperaWebElement reset;

  private Controller controller;

  public Controls(OperaDriver driver) {
    this(driver, new KeyboardController((OperaKeyboard) driver.getKeyboard()));
    //this(driver, new MouseController((OperaMouse) driver.getMouse()));
  }

  public Controls(OperaDriver driver, Controller controller) {
    this.controller = controller;

    left  = (OperaWebElement) driver.findElement(By.id("btn_left"));
    right = (OperaWebElement) driver.findElement(By.id("btn_right"));
    reset = (OperaWebElement) driver.findElement(By.id("btn_reset"));
  }

  public void left() {
    controller.activate(left);
  }

  public void right() {
    controller.activate(right);
  }

  public void reset() {
    controller.activate(reset);
  }
}