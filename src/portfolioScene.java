import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class portfolioScene implements EventHandler<ActionEvent> {


    private Stage masterWindow;

    public portfolioScene(Stage masterWindow) {
        this.masterWindow = masterWindow;

    }

    @Override
    public void handle(ActionEvent event) {

        FlowPane test = new FlowPane();


//        Add a go Back Button
        Scene portfolioScene = new Scene(test,300, 500);
        masterWindow.setScene(portfolioScene);

    }
}
