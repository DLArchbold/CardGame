
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameDemo extends Application{
	private static Stage stage;
	Scene scene;/*, scene2;*/

	public void start(Stage primaryStage) {
		
		/*Assign primaryStage to stage
		 * Rectangle to lay below Start and Exit buttons are created
		 * panes to set title and buttons are created.*/
		stage = primaryStage;
		Rectangle rec = new Rectangle(300, 160);
		rec.setFill(Color.WHITESMOKE);
		rec.setStroke(Color.BLACK);
		rec.setTranslateY(230);

		BorderPane pane1 = new BorderPane();
		StackPane pane2 = new StackPane();
		pane1.setStyle("-fx-border-color: black");
		
		//Set main title wording and its properties
		Text title = new Text("Poker Game");
		title.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, FontPosture.REGULAR, 145));
		title.setFill(Color.LIGHTGREY);
		title.setStyle("-fx-stroke:black");

		//Create VBox to store Start Game and Exit Game buttons at start page
		VBox option =new VBox();
		option.setSpacing(20);
		option.setAlignment(Pos.CENTER);
		option.setTranslateY(150);

		//Set button wording and properties for Start Game and Exit Game buttons
		Button Start = new Button("Start Game");
		Button Exit = new Button("Exit Game");
		Start.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, FontPosture.REGULAR, 24));
		Exit.setFont(Font.font("Monotype Corsiva", FontWeight.BOLD, FontPosture.REGULAR, 24));
		Start.setPrefSize(200,50);
		Exit.setPrefSize(200,50);

		//Add Start Game and Exit Game buttons to VBox
		option.getChildren().addAll(Start,Exit);
		
		
		pane2.getChildren().add(title);
		pane1.setCenter(option);
		pane1.setTop(pane2);
		
		//set background image and poker card image
		Image img = new Image("file:background.jpg");
		ImageView iv = new ImageView("file:background.jpg");
		iv.setImage(img);
		Image img2 = new Image("file:Poker-512.png");
		ImageView iv2 = new ImageView("file:Poker-512.png");
		iv2.setFitHeight(400);
		iv2.setFitWidth(400);
		iv2.setTranslateY(-30);
		iv2.setImage(img2);
		StackPane root = new StackPane();
		
		/*Display red background image first, then poker image, then 
		 * rectangle to store buttons
		 *  then only title and buttons 
		in a stack pane.*/
		root.getChildren().addAll(iv, iv2, rec, pane1);

		
		scene = new Scene(root, 1280, 720);
		stage.setTitle("Poker Game"); 



		//Create instance of CardGame
		CardGame runGame = new CardGame();
		
		//Handle event when Start button is pressed
		Start.setOnAction(e -> {
			//primaryStage.setScene(scene2);
			runGame.initialSetUp();
		});

		//Handle event when Exit button is pressed
		Exit.setOnAction(e -> {
			System.exit(1);
		});

		
		//Associate scene with a stage, do not allow it to be resized, and show/launch the stage
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();	
	}

	public static void main(String[] args) {
		//launch JavaFX application.
		launch(args);
	}

	public static Stage getStage() {
		return stage;
	}

}

