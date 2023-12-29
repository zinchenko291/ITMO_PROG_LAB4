package Characters;

public enum Feels {
  ANGRY("злой как чёрт"),
  SCARY("дрогнул губой"),
  SAD("надулся"),
  RAGE("взревела от бешенства"),
  NORMAL("нормально"),
  DEAD_INSIDE("был просто в отчаянии");

  private final String feel;

  Feels(String feel) {
    this.feel = feel;
  }

  @Override
  public String toString() {
    return this.feel;
  }
}
