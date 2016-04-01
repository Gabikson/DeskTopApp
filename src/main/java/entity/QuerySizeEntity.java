package entity;

public class QuerySizeEntity{

  private CommandMode commandMode = CommandMode.QUERY;
  private long price;

  public QuerySizeEntity(long price) {
    this.price = price;
  }

  public QuerySizeEntity() {
  }

  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }

}
