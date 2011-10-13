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
    //System.out.println(button.getCoordinates().getLocationInViewPort());
    //mouse.click(button.getCoordinates());
    System.out.println(button.getAttribute("id") + "  " + button.getCoordinates().getLocationInViewPort());
    button.click();
  }

  /*
  public void activateLeft(OperaWebElement button) {
    mouse.click(button.getCoordinates());
  }

  public void activateRight(OperaWebElement button) {
    mouse.click(button.getCoordinates());
  }

  public void activateReset(OperaWebElement button) {
    mouse.click(button.getCoordinates());
  }
  */

}