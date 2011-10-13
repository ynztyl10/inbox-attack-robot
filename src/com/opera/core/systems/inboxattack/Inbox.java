package com.opera.core.systems.inboxattack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Inbox {

  private InboxAttack game;
  private List<Mail> inbox = new ArrayList<Mail>();

  public Inbox(InboxAttack game) {
    this.game = game;
  }

  /*
  public Mail getNextMail() {
    update();
    return inbox.get(inbox.size() - 1);
  }
  */

  public List<Mail> getPresentMail() {
    update();
    List<Mail> presentMail = new ArrayList<Mail>();

    for (Mail mail : inbox) {
      System.out.println("parsing " + mail.getId() + " (channel " + mail.getChannel() + ", state: " + mail.getState() + ")");
      if (mail.isUnknown()) {
        presentMail.add(mail);
      }
    }

    return presentMail;
  }

  public List<Mail> getAllMail() {
    update();
    return inbox;
  }

  public Integer getTotalMailCount() {
    return new Integer(game.get("countTotal").toString());
  }

  public Integer getDroppedMailCount() {
    return new Integer(game.get("countMails").toString());
  }

  public Integer getCaughtMailCount() {
    return new Integer(game.get("countCaught").toString());
  }

  public Integer getPresentMailCount() {
    return getTotalMailCount() - getDroppedMailCount();
  }

  public int size() {
    return inbox.size();
  }

  public void update() {
    inbox = null;
    inbox = parseMails();
  }

  private List<Mail> parseMails() {
    List<Mail> parsedMail = new ArrayList<Mail>();
    ArrayList<HashMap> inbox = (ArrayList) game.get("inbox");

    for (HashMap<String, Long> mail : inbox) {
      parsedMail.add(new Mail(mail, game));
    }


    /*
    List<Mail> inbox = new ArrayList<Mail>();
    ArrayList<HashMap> mails = (ArrayList) game.get("inbox");

    for (HashMap<String, Long> mail : mails) {
      inbox.add(new Mail(mail, game));
    }
    */

    return parsedMail;
  }

}