import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Javafx.stage = Window
// Scene = stuff inside the window

public class javafxgui extends Application {

    Stage masterWindow;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        masterWindow = primaryStage;
        primaryStage.setTitle("Portfolio Management");


        Button optn1 = new Button("Scenario 1");
        Button optn2 = new Button("Scenario 2");
        Button optn3 = new Button("Scenario 3");
        Button optn4 = new Button("Scenario 4");
        Button viewPortfolio = new Button("View Portfolios");


        optn1.setOnAction(new scenario1(primaryStage));
        optn2.setOnAction(new scenario1(primaryStage));
        optn3.setOnAction(new scenario1(primaryStage));
        viewPortfolio.setOnAction(new jsonData(primaryStage));

//        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
//
//        grid.add(optn1, 0,0);
//        grid.add(optn2, 1,0);
//        grid.add(optn3, 2,0);
//        grid.add(optn4, 3, 0);
        VBox scenarioButtons = new VBox(20);
        scenarioButtons.getChildren().addAll(optn1,optn2,optn3,optn4);


        scenarioButtons.setAlignment(Pos.CENTER);

        BorderPane master = new BorderPane();
        master.setBottom(viewPortfolio);
        master.setCenter(scenarioButtons);





        Scene scene = new Scene(master, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.show();



    }

}
