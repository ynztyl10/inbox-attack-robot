package com.opera.core.systems.inboxattack.controls;

import com.opera.core.systems.OperaMouse;
import com.opera.core.systems.OperaWebElement;

import org.openqa.selenium.Mouse;

public class MouseController implements Controller {

  private final Mouse mouse;

  public MouseController(OperaMouse mouse) {
    this.mouse = mouse;
  }

  public void activate(OperaWebElement button) {
    button.click();
  }

}