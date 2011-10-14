package com.opera.core.systems.inboxattack;

import com.opera.core.systems.inboxattack.controls.Controls;
import com.opera.core.systems.OperaDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import java.util.logging.Logger;

public class InboxAttack {

  public OperaDriver driver;
  public Map cache = null;

  private final Logger logger = Logger.getLogger(this.getClass().getName());

  private String url;
  private Wait<WebDriver> wait;
  private GameState state;
  private Player player;
  private Inbox inbox;
  private Controls controls;

  public InboxAttack(OperaDriver driver, String url) {
    this.driver = driver;
    this.url = url;
    this.state = new GameState(this);
    this.player = new Player(this);
    this.inbox = new Inbox(this);
    this.wait = new WebDriverWait(this.driver, 15);

    loadGame();
  }

  private void loadGame() {
    driver.navigate().to(url);
    wait.until(new GameReady());
  }

  public GameState state() {
    return state;
  }

  public Player player() {
    return player;
  }

  public Inbox inbox() {
    return inbox;
  }

  public Controls control() {
    if (controls == null) {
      controls = new Controls(driver);
    }
    return controls;
  }

  public void updateCache() {
    inbox().update();
    get();
  }

  private Map get() {
    new WebDriverWait(this.driver, 15).until(new GameReady());
    cache = (Map) driver.executeScript("return debugData()");
    return cache;
  }

  public Object get(String key) {
    return get().get(key);
  }

}