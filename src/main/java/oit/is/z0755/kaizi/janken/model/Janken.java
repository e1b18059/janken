package oit.is.z0755.kaizi.janken.model;

public class Janken {
  int i, you;

  public Janken(int i) {
    this.i = i;
    you = 0;
  }

  public String myhand() {
    String hand;
    if (i == 0) {
      hand = "Gu";
    } else if (i == 1) {
      hand = "Chi";
    } else {
      hand = "Pa";
    }
    return hand;
  }

  public String yourhand() {
    String hand;
    if (you == 0) {
      hand = "Gu";
    } else if (you == 1) {
      hand = "Chi";
    } else {
      hand = "Pa";
    }
    return hand;
  }

  public String judge() {
    String result;
    if (i == 0 && you == 1 || i == 1 && you == 2 || i == 2 && you == 0) {
      result = "You Win!";
    } else if (i == 0 && you == 2 || i == 1 && you == 0 || i == 2 && you == 1) {
      result = "You Lose!";
    } else {
      result = "Draw!";
    }
    return result;
  }

}
