package com.opera.core.systems.inboxattack;

public class GameState {

  private static final int WAITING = 1;
  private static final int PLAYING = 2;
  private static final int GAME_OVER = 3;

  private InboxAttack game;

  public GameState(InboxAttack game) {
    this.game   = game;
  }

  public boolean isWaiting() {
    return (getState() == WAITING);
  }

  public boolean isAlive() {
    return (getState() == PLAYING);
  }

  public boolean isDead() {
    return (getState() == GAME_OVER);
  }

  private int getState() {
    return new Integer(game.get("state").toString());
  }

}