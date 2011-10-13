package com.opera.core.systems.inboxattack;

import java.util.HashMap;

public class Mail {

  public static final int UNKNOWN = 1;
  public static final int CAUGHT = 2;
  public static final int MISSED = 3;

  private int id;
  private int channel = -1;
  private int state = UNKNOWN;
  private InboxAttack game;

  public Mail(HashMap<String, Long> newMail, InboxAttack game) {
    this.id = new Integer(newMail.get("id").toString());
    this.channel = new Integer(newMail.get("channel").toString());
    this.state = new Integer(newMail.get("state").toString());
    this.game = game;

    //System.out.println("Mail() id=" + id + ", channel=" + channel + ", state=" + state);
  }

  public int getId() {
    return this.id;
  }

  public int getChannel() {
    return this.channel;
  }

  public boolean isUnknown() {
    return (getState() == UNKNOWN);
  }

  public boolean isCaught() {
    return (getState() == CAUGHT);
  }

  public boolean isMissed() {
    return (getState() == MISSED);
  }

  public int getState() {

    /*
        List<Mail> inbox = new ArrayList<Mail>();
    ArrayList<HashMap> mails = (ArrayList) game.get("inbox");

    for (HashMap<String, Long> mail : mails) {
      inbox.add(new Mail(mail, game));
    }
    */

    return this.state;


    // TODO: WTF!
    //return new Integer(game.get("state").toString());
  }

}