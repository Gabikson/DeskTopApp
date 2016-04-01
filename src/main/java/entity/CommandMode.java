package entity;

public enum CommandMode {
  UPDATE("update"), QUERY("query"), ORDER("order");

  private final String text;

  private CommandMode(final String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }

  public static CommandMode getCommandByShortName(String command){
    switch (command){
      case "u":
        return UPDATE;
      case "o":
        return ORDER;
      case "q":
        return QUERY;
      default:
        return UPDATE;
    }
  }

}
