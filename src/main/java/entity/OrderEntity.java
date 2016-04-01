package entity;

public class OrderEntity{

  private CommandMode commandMode = CommandMode.ORDER;
  private OrderMode mode;
  private long size;

  public OrderEntity(OrderMode mode, long size) {
    this.mode = mode;
    this.size = size;
  }

  public OrderEntity() {}

  public OrderMode getMode() {
    return mode;
  }

  public void setMode(OrderMode mode) {
    this.mode = mode;
  }

  public long getSize() {
    return size;
  }

  public void setSize(long size) {
    this.size = size;
  }

  public CommandMode getCommandMode() {
    return commandMode;
  }

  public void setCommandMode(CommandMode commandMode) {
    this.commandMode = commandMode;
  }

  public enum OrderMode {
    BUY("buy"), SELL("sell");

    private final String text;

    private OrderMode(final String text) {
      this.text = text;
    }

    @Override
    public String toString() {
      return text;
    }

  }
}
