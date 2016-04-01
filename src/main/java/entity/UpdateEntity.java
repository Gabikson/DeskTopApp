package entity;

public class UpdateEntity{

  private CommandMode commandMode = CommandMode.UPDATE;
  private UpdateMode mode;
  private long price;
  private long size;

  public UpdateEntity(UpdateMode mode, long price, long size) {
    this.mode = mode;
    this.price = price;
    this.size = size;
  }

  public UpdateEntity() {
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public UpdateMode getMode() {
    return mode;
  }

  public void setMode(UpdateMode mode) {
    this.mode = mode;
  }

  public enum UpdateMode {
    BID("bid"), ASK("ask");

    private final String text;

    private UpdateMode(final String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return text;
    }

  }

}
