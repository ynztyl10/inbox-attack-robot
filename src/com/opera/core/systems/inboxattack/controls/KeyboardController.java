package com.opera.core.systems.inboxattack.controls;

import com.opera.core.systems.OperaKeyboard;
import com.opera.core.systems.OperaWebElement;

import org.openqa.selenium.Keys;

public class KeyboardController implements Controller  {

  private final OperaKeyboard keyboard;

  private enum KeyboardButton {
    LEFT ("btn_left"),
    RIGHT("btn_right"),
    RESET("btn_reset");

    private final String locator;

    private KeyboardButton(String locator) {
      this.locator = locator;
    }

    public String getLocator() {
      return this.locator;
    }
  }

  public KeyboardController(OperaKeyboard keyboard) {
    this.keyboard = keyboard;
  }

  public void activate(OperaWebElement button) {
    String id = button.getAttribute("id");

    if (id.equals(KeyboardButton.LEFT.getLocator())) {
      keyboard.pressKey(Keys.LEFT);
      keyboard.releaseKey(Keys.LEFT);
    } else if (id.equals(KeyboardButton.RIGHT.getLocator())) {
      keyboard.pressKey(Keys.RIGHT);
      keyboard.releaseKey(Keys.RIGHT);
    } else if (id.equals(KeyboardButton.RESET.getLocator())) {
      keyboard.pressKey(Keys.UP);
      keyboard.releaseKey(Keys.UP);
    }
  }

}