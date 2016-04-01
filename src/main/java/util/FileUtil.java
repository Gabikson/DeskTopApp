package util;

import constants.Constants;
import entity.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;



public final class FileUtil {

  private FileUtil() {
    throw new UnsupportedOperationException();
  }

  public static StringBuilder executeFile(File file) {
    try {
      StringBuilder result = new StringBuilder();
      if (file.exists()) {
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<UpdateEntity> listOfItems = new ArrayList<>();
        String line = "";
        while ((line = br.readLine()) != null) {

          // use comma as separator
          String[] lineParams = line.split(Constants.SEPARATED_CHAR);

          if (lineParams != null && lineParams.length > 0) {
            String mode = lineParams[0].toUpperCase();
            CommandMode modeForThisLine = CommandMode.getCommandByShortName(mode.toLowerCase());
            switch (modeForThisLine) {
              case UPDATE:
                executeUpdate(lineParams, listOfItems);
                break;
              case QUERY:
                executeQuery(lineParams, listOfItems, result);
                break;
              case ORDER:
                executeOrder(lineParams, listOfItems, result);
                break;
            }
          }
        }
      }
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private static void executeUpdate(String[] lineParams, List<UpdateEntity> listOfItems) {
    if (lineParams.length <= Constants.COUNT_OF_PARAMS_FOR_UPDATE) {
      UpdateEntity entity = new UpdateEntity();
      entity.setSize(Long.parseLong(lineParams[2]));
      entity.setPrice(Long.parseLong(lineParams[1]));
      entity.setMode(UpdateEntity.UpdateMode.valueOf(lineParams[3].toUpperCase()));
      listOfItems.add(entity);
    }
  }

  private static void executeQuery(String[] lineParams, List<UpdateEntity> listOfItems, StringBuilder result) {
    int countOfParams = lineParams.length;
    String response = null;
    if (countOfParams == Constants.COUNT_OF_PARAMS_FOR_QUERY_BEST_VALUE) {
      QueryBestEntity entity = new QueryBestEntity(QueryBestEntity.QueryMode.valueOf(lineParams[1].toUpperCase()));
      String stringTemplate = "%s" + Constants.SEPARATED_CHAR + "%d" + Constants.NEW_LINE_CHAR;
      response = CalculationUtil.executeQueryBestValue(stringTemplate, entity, listOfItems);
    } else if (countOfParams == Constants.COUNT_OF_PARAMS_FOR_QUERY_SIZE) {
      if (lineParams[1].equals(Constants.QUERY_SIZE_COMMAND)) {
        QuerySizeEntity entity = new QuerySizeEntity();
        entity.setPrice(Long.parseLong(lineParams[2]));
        String stringTemplate = "%d" + Constants.NEW_LINE_CHAR;
        response = CalculationUtil.executeQuerySize(stringTemplate, entity, listOfItems);
      }
    }
    if(response != null){
      result.append(response);
    }else{
      result.append(Constants.NEW_LINE_CHAR);
    }
  }

  private static void executeOrder(String[] lineParams, List<UpdateEntity> listOfItems, StringBuilder result) {
    if (lineParams.length <= Constants.COUNT_OF_PARAMS_FOR_ORDER) {
      OrderEntity entity = new OrderEntity();
      entity.setMode(OrderEntity.OrderMode.valueOf(lineParams[1].toUpperCase()));
      entity.setSize(Long.parseLong(lineParams[2]));
      String stringTemplate = "%d" + Constants.NEW_LINE_CHAR;
      result.append(CalculationUtil.makeOrder(stringTemplate, entity, listOfItems));
    }
  }
}
