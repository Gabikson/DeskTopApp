package constants;

import java.awt.*;

public interface Constants {

  String SEPARATED_CHAR = ",";
  String NEW_LINE_CHAR = "\n";
  String QUERY_SIZE_COMMAND = "size";

  int COUNT_OF_PARAMS_FOR_UPDATE = 4;
  int COUNT_OF_PARAMS_FOR_QUERY_BEST_VALUE = 2;
  int COUNT_OF_PARAMS_FOR_ORDER = 3;
  int COUNT_OF_PARAMS_FOR_QUERY_SIZE = COUNT_OF_PARAMS_FOR_ORDER;

  Color TEXT_AREA_COLOR = new Color(50, 50, 50);

  Color BUTTON_DEFAULT = new Color(200, 200, 200);
  Color BUTTON_CLICKED = new Color(150, 150, 150);
  Color BUTTON_TEXT_COLOR = new Color(20, 20, 20);
}
