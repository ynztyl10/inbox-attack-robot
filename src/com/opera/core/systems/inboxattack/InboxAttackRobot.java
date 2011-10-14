package com.opera.core.systems.inboxattack;

import com.opera.core.systems.OperaDriver;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintStream;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * InboxAttackPilot plays the “Inbox Attack” game originally developed by Daniel Davis (Opera).
 *
 * Implementation heavily inspired by SelenicopterPilot by Dave Hunt (Mozilla).
 *
 * @author Andreas Tolf Tolfsen <andreastt@opera.com>
 */
public class InboxAttackRobot {

  public static OperaDriver driver;
  public static InboxAttack game;
  public static PrintStream out = System.out;
  public static PrintStream err = System.err;

  private static final Logger logger = Logger.getLogger(InboxAttackRobot.class.getName());
  private static ScoreBoard scores = new ScoreBoard();
  private static boolean running = false;
  private static String url;

  public static void main(String[] args) throws Exception {
    Options options = new Options();
    options.addOption("url", true, "target web address for the inbox attack game");
    options.addOption("debug", false, "enables verbose debug output");

    CommandLineParser parser = new GnuParser();
    CommandLine cli = parser.parse(options, args);

    if (cli.hasOption("url") && !cli.getOptionValue("url").isEmpty()) {
      url = cli.getOptionValue("url");
    }

    if (cli.hasOption("debug")) {
      Level logLevel = Level.ALL;
      Logger root = Logger.getLogger("");
      root.setLevel(logLevel);

      for (Handler h : root.getHandlers()) {
        h.setLevel(logLevel);
      }
    }

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("opera.port", -1);
    logger.config("OperaDriver capabilities: " + capabilities);

    driver = new OperaDriver(capabilities);
    driver.navigate().to(url);
    game = new InboxAttack(driver, url);

    running = true;
    while (running) {
      play();
    }

    gameOver();
    driver.quit();
  }

  private static void play() {
    // Wait until game is ready
    while (game.state().isWaiting()) {
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        // nothing
      }
    }

    out.println("=================");
    out.println("    LET'S GO!!   ");
    out.println("=================");

    // The game will start automatically
    while (game.state().isAlive() && scores.size() <= 5) { // 50
      game.updateCache();
      catchMail();
    }

    // Register score
    scores.newScore(game.inbox().getCaughtMailCount());

    // Reset game
    game.control().reset();
    new WebDriverWait(driver, 15).until(new GameReady());
  }

  private static void catchMail() {
    //if (game.inbox().getPresentMailCount() == 0) {
    if (game.inbox().size() == 0) {
      logger.fine("no present mail");
      return;
    }

    for (Mail mail : game.inbox().getPresentMail()) {
      logger.fine("processing mail #" + mail.getId());

      // Calculate player's distance to target channel
      int distanceFromTarget = game.player().distanceFromTarget(mail.getChannel());

      // Move player right or left the needed times
      logger.fine("  moving player");
      for (int i = 0; i < Math.abs(distanceFromTarget); i++) {
        if (distanceFromTarget > 0) {
          game.control().right();
        } else if (distanceFromTarget < 0) {
          game.control().left();
        }
      }

      // Wait for mail to be caught or missed
      boolean wait = true;
      //while (mail.isUnknown()) {
      while (wait) {
        logger.fine("  waiting...");
        logger.fine("  " + mail.getState());

        if (!game.inbox().getAllMail().get(mail.getId()).isUnknown()) {
          wait = false;
        }

        try {
          Thread.sleep(250);
        } catch (InterruptedException e) {
          // nothing
        }
      }
    }
  }

  private static void gameOver() {
    out.println("=================");
    out.println("    GAME OVER!   ");
    out.println("=================");
    out.print(scores.getHighScores());
    out.print(scores.getSummary());
    running = false;
  }

}