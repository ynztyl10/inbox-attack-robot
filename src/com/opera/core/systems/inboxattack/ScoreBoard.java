package com.opera.core.systems.inboxattack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Keeps track of scores in the game.
 */
public class ScoreBoard {

  private ArrayList<Integer> scores = new ArrayList<Integer>();

  public void newScore(int score) {
    if (scores.size() == 0 || score > Collections.max(scores)) {
      newHighScore(score);
    }
    scores.add(score);
  }

  public int size() {
    return scores.size();
  }

  public String getHighScores() {
    Collections.sort(scores);
    Collections.reverse(scores);

    StringBuilder builder = new StringBuilder();

    builder.append("=================\n")
           .append("   HIGH SCORES:  \n")
           .append("=================\n");

    for (int i = 0; i < scores.size() && i < 10; i ++) {
      builder.append(i + 1).append(": ").append(scores.get(i)).append("\n");
    }

    return builder.toString();
  }

  public String getSummary() {
    int totalScore = 0;
    for (int currentScore : scores) {
      totalScore += currentScore;
    }

    StringBuilder builder = new StringBuilder();

    builder.append("=================\n")
           .append("     SUMMARY:    \n")
           .append("=================\n")
           .append("Games: ").append(scores.size()).append("\n")
           .append("Average score: ").append((totalScore != 0) ? totalScore / scores.size() : 0).append("\n");

    return builder.toString();
  }

  private void newHighScore(int score) {
    System.out.println("New high score: " + Integer.toString(score));
  }

}