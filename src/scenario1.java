import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class scenario1 implements EventHandler<ActionEvent> {

    private Stage parentStage;
    public scenario1(Stage parent){
        this.parentStage = parent;
    }




    public void handle(ActionEvent event)  {

        try {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(parentStage);
            ArrayList<Trade> tradeList = ReadCSV.csvParse(file.getAbsolutePath());



        } catch (IOException e){
            System.out.println("File Not Chosen");

        }

    }

}




//    private JPanel parentWindow;
//
//    scenario1(JPanel parentWindow) {
//        this.parentWindow = parentWindow;
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
//        int result = fileChooser.showOpenDialog(parentWindow);
//        if (result == JFileChooser.APPROVE_OPTION) {
//            File selectedFile = fileChooser.getSelectedFile();
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//            System.out.println("Printed from scenario1.java");
//
//
//        }
//    }



