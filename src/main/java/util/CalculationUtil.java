package util;

import entity.OrderEntity;
import entity.QueryBestEntity;
import entity.QuerySizeEntity;
import entity.UpdateEntity;

import java.util.Collections;
import java.util.List;

public final class CalculationUtil {

  private CalculationUtil(){
    throw new UnsupportedOperationException();
  }

  public static String executeQueryBestValue(String template, QueryBestEntity entity, List<UpdateEntity> data){
    long bestValue = 0;
    long size = 0;
    UpdateEntity filteredEntity = null;
    switch (entity.getMode()){
      case BEST_ASK:
        filteredEntity = getEntityByBestValue(entity, data, UpdateEntity.UpdateMode.ASK);
        break;
      case BEST_BID:
        filteredEntity = getEntityByBestValue(entity, data, UpdateEntity.UpdateMode.BID);
        break;
    }
    if(filteredEntity != null){
      bestValue = filteredEntity.getPrice();
      size = filteredEntity.getSize();
    }else{
      return null;
    }
    return String.format(template, new Object[]{bestValue, size});
  }

  private static UpdateEntity getEntityByBestValue(QueryBestEntity entity, List<UpdateEntity> data, UpdateEntity.UpdateMode mode){
    UpdateEntity result = null;
    long maxValue = 0;
    for(UpdateEntity item : data){
      if(item.getPrice() > maxValue && item.getMode().equals(mode)){
        result = item;
        maxValue = item.getPrice();
      }
    }
    return result;
  }

  public static String executeQuerySize(String template, QuerySizeEntity entity, List<UpdateEntity> data){
    long size = 0;
    boolean found = false;
    for(UpdateEntity item : data){
      if(item.getPrice() == entity.getPrice()){
        size = item.getSize();
        found = true;
      }
    }
    if(found){
      return String.format(template, new Object[]{size});
    }else{
      return null;
    }
  }

  public static String makeOrder(String template, OrderEntity entity, List<UpdateEntity> data){
    long amount = 0;
    Collections.sort(data, (item1, item2)->{
      return (int)(item1.getPrice() - item2.getPrice());
    });
    int removedElement = 0;
    switch (entity.getMode()){
      case SELL:
        for(int i = data.size()-1; i > 0; i--){
          if(data.get(i).getMode().equals(UpdateEntity.UpdateMode.BID)){
            removedElement = i;
            amount = 1;
            break;
          }
        }
        break;
      case BUY:
        for(int i = 0; i > data.size(); i++){
          if(data.get(i).getMode().equals(UpdateEntity.UpdateMode.ASK)){
            removedElement = i;
            amount = 1;
            break;
          }
        }
        break;
    }
    if(removedElement != 0){
      data.remove(removedElement);
    }
    return String.format(template, new Object[]{amount});
  }

}
