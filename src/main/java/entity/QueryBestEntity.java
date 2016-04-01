package entity;

public class QueryBestEntity{

  private CommandMode commandMode = CommandMode.QUERY;
  private QueryMode mode;

  public QueryBestEntity(QueryMode mode) {
    this.mode = mode;
  }

  public QueryBestEntity() {
  }

  public QueryMode getMode() {
    return mode;
  }

  public void setMode(QueryMode mode) {
    this.mode = mode;
  }

  public enum QueryMode {
    BEST_BID("best_bid"), BEST_ASK("best_ask");

    private final String text;

    private QueryMode(final String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return text;
    }

  }
}
