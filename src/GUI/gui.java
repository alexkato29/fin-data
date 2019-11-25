package GUI;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import static jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType.ASCII;

public class gui {

    public static void main(String[] args) throws IOException{
        JFrame frame = new JFrame("Main Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,300);

        JPanel buttonPanel = new JPanel(new GridLayout(1,3,20, 20));

        JButton btn1 = new JButton("Scenario 1");
        JButton btn2 = new JButton ("Scenario 2");
        JButton btn3 = new JButton ("Scenario 3");

        buttonPanel.add(btn1);
        buttonPanel.add(btn2);
        buttonPanel.add(btn3);
        frame.getContentPane().add(buttonPanel);



        btn1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(buttonPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());

//JFileChooser chooser = new JFileChooser();
//chooser.setMultiSelectionEnabled(true);
//chooser.showOpenDialog(frame);
//File[] files = chooser.getSelectedFiles();



                }
            }
        });



        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("2");
            }
        });
        btn3.addActionListener(new scenario1(buttonPanel));
        frame.setVisible(true);
    }




}
