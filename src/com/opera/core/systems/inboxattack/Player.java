package com.opera.core.systems.inboxattack;

public class Player {

  private InboxAttack game;

  public Player(InboxAttack game) {
    this.game   = game;
  }

  public int getChannel() {
    return new Integer(game.cache.get("userPosition").toString());
  }

  public int distanceFromTarget(int targetChannel) {
    return targetChannel - getChannel();
  }

}