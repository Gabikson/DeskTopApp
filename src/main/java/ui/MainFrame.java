package ui;

import constants.Constants;
import ui.components.CustomizedButtonUI;
import util.FileUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public class MainFrame extends JFrame{

  private JButton uploadButton;

  private JTextArea textArea;

  private JFileChooser fileChooser;

  private int returnValueFromFileChooser;

  public void showScreen() {
    setVisible(true);
  }

  public MainFrame() {
    initUI();
    setTitle("Test task");
    setSize(new Dimension(450, 450));
    setResizable(false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  private void initUI() {
    Font font = new Font("Verdana", Font.BOLD, 20);
    textArea = new JTextArea(5, 20);
    textArea.setMargin(new Insets(5, 5, 5, 5));
    textArea.setForeground(Color.WHITE);
    textArea.setFont(font);
    textArea.setEditable(false);
    textArea.setBackground(Constants.TEXT_AREA_COLOR);
    textArea.setCaretColor(Color.WHITE);
    JScrollPane logScrollPane = new JScrollPane(textArea);

    //Create a file chooser
    fileChooser = new JFileChooser();
    fileChooser.addActionListener(event -> {
      if (returnValueFromFileChooser == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        StringBuilder result = FileUtil.executeFile(file);
        if (result != null) {
          textArea.setForeground(Color.WHITE);
          textArea.setText("Result: \n");
          textArea.append(result.toString());
        } else {
          textArea.setForeground(Color.RED);
          textArea.setText("Invalid file");
        }
      }
    });

    uploadButton = new JButton();
    uploadButton.setFont(new Font("Arial", Font.PLAIN, 40));
    uploadButton.setText("Upload file");
    uploadButton.addActionListener(event -> {
      returnValueFromFileChooser = fileChooser.showOpenDialog(MainFrame.this);
    });
    uploadButton.setUI(new CustomizedButtonUI(Constants.BUTTON_DEFAULT, Constants.BUTTON_CLICKED, Constants.BUTTON_CLICKED, font, Constants.BUTTON_TEXT_COLOR));

    add(uploadButton, BorderLayout.NORTH);
    add(logScrollPane, BorderLayout.CENTER);
  }

}
