import ui.MainFrame;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

import javax.swing.*;



public class Launcher {

  public static void main(String[] args) {
    try {
      setSystemProperties();
      // Call swing util for show application in new thread
      SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
          // show window
          MainFrame frame = new MainFrame();
          frame.showScreen();
        }
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void setSystemProperties() {
    try {
      System.setProperty("file.encoding", "UTF-8");
      Field charset = Charset.class.getDeclaredField("defaultCharset");
      charset.setAccessible(true);
      charset.set(null, null);
    } catch (Exception e) {
      throw new RuntimeException("Can't set default charset");
    }
  }


}
