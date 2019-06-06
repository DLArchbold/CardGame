
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CardGame{
	
	//Iniitalize data members
	private int playerCount = 0;
	private int cardValue = 0;
	private int counter = 0;
	private int round = 1;
	
	//Create HashMap for later mapping of cards to players
	Map<Integer, ArrayList<Integer>> hand = new HashMap<Integer, ArrayList<Integer>>();
	
	//Create integer array lists to store cards later.
	ArrayList<Integer> set1 = new ArrayList<Integer>();
	ArrayList<Integer> set2 = new ArrayList<Integer>();
	ArrayList<Integer> set3 = new ArrayList<Integer>();
	ArrayList<Integer> set4 = new ArrayList<Integer>();

	//Scanner and BufferedReader defined for input later
	Scanner input = new Scanner(System.in);
	BufferedReader r = new BufferedReader(new InputStreamReader(System.in));

	public void initialSetUp()
	{
		//Four players defined, unused players handled later.
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
	
		//scene2
		//Buttons to select number of players created and set in vPlayerCount.
		
		
		VBox vPlayerCount = new VBox();
		RadioButton bt1 = new RadioButton("2 Players");
		RadioButton bt2 = new RadioButton("3 Players");
		RadioButton bt3 = new RadioButton("4 Players");
		Button bt4 = new Button("Go!");
		ToggleGroup tgroup = new ToggleGroup();
		bt1.setToggleGroup(tgroup);
		bt2.setToggleGroup(tgroup);
		bt3.setToggleGroup(tgroup);
		Label label = new Label("Please choose the number of players.");
		label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));

		
		
		vPlayerCount.setSpacing(10);
		vPlayerCount.setAlignment(Pos.CENTER);
		vPlayerCount.getChildren().addAll(label, bt1, bt2, bt3, bt4);

		
		
		
		//Background image and buttons are positioned and shown.
		StackPane pane = new StackPane();
		Rectangle rec = new Rectangle(480, 360);
		Image img = new Image("file:background.jpg");
		ImageView iv = new ImageView();
		iv.setImage(img);
		rec.setFill(Color.WHITESMOKE);
		rec.setStroke(Color.BLACK);
		pane.setPadding(new Insets(10)); 
		pane.setStyle("-fx-border-color: black");
		pane.getChildren().addAll(iv, rec, vPlayerCount);

		Scene scene = new Scene(pane, 1280, 720);
		Stage stage = GameDemo.getStage();
		stage.setScene(scene);
		
		//Events for buttons 1 to 3 are handled
		EventHandler<ActionEvent> event1 = (e -> {
			if(bt1.isSelected())
				playerCount = 2;
			else if(bt2.isSelected())
				playerCount = 3;
			else if(bt3.isSelected())
				playerCount = 4;
		});
		bt1.setOnAction(event1);
		bt2.setOnAction(event1);
		bt3.setOnAction(event1);
		bt1.setSelected(true);
		
		
		playerCount = 2;
		
		//Events for button 4 (deck numbering, shuffling, assigning and sorting) are handled
		bt4.setOnAction(e->{
			int[] deck = new int[52];
			
			
			//Cards are assigned numbers and shuffled through the Card class
			Card.Deck(deck);
			
			//Cards are assigned to decks
			for(int i = 0; i < 7; i++) {
				set1.add(deck[i]);
				set2.add(deck[i + 7]);
				set3.add(deck[i + 14]);
				set4.add(deck[i + 21]);
			}
			
			
			Collections.sort(set1);
			Collections.sort(set2);
			Collections.sort(set3);
			Collections.sort(set4);
			
			//Names(keys) must be unique if not will only have save 1 key mapping to a value
			//if else statement is to prevent unknown player when 2 player is selected
			SetUpPlayers(player1, player2, player3, player4);
		});

		//Game start
		//PlayGame(player1,player2,player3,player4, hand, playerCount);
	}

	public void SetUpPlayers(Player player1, Player player2, Player player3, Player player4) {	
		
		//Define all panes, scenes and stages
		StackPane pane = new StackPane();
		VBox vbox = new VBox();
		Stage stage = new Stage();
		stage = GameDemo.getStage();
		
		
		
	/*	Label asking user to enter name, buttons for confirm entry, textfield to receive input,background image
		and rectangle to store controls are set.
*/		Label label = new Label("Please enter your name(Player1).");
		label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		
		Button bt1 = new Button("Enter");
		Button bt2 = new Button("Enter");
		Button bt3 = new Button("Enter");
		Button bt4 = new Button("Enter");
		
		
		
		
		TextField tfield = new TextField();
		tfield.setMaxWidth(250);
		tfield.setEditable(true);
		tfield.setAlignment(Pos.CENTER_LEFT);
		
		
		Image img = new Image("file:background.jpg");
		ImageView iv = new ImageView();
		iv.setImage(img);
		
		Rectangle rec = new Rectangle(480, 360);
		rec.setFill(Color.WHITESMOKE);
		rec.setStroke(Color.BLACK);
		
		
		/*Labels, textfield and button added to Vbox
		Background image, rectangle for buttons, and VBox assigned to StackPane and stage is set with scene.*/
		vbox.getChildren().addAll(label, tfield, bt1);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		pane.setPadding(new Insets(10)); 
		pane.setStyle("-fx-border-color: black");
		pane.getChildren().addAll(iv, rec, vbox);
	
		
		Scene scene = new Scene(pane, 1280, 720);
		stage.setScene(scene);
	
	/*	Events for buttons are handled.
	 * Confirm input from textfield(Player names). Assign names and IDs to players.
		Continue until set for all players, then execute showAllInfo().
*/		
			bt1.setOnAction(e->{
				//Assign names
				player1.setName(tfield.getText());
				player1.setID(1);
				tfield.clear();
				vbox.getChildren().remove(bt1);
				vbox.getChildren().add(bt2);
				label.setText("Please enter your name(Player2).");
			});
			
			bt2.setOnAction(e->{
				//Assign names
				player2.setName(tfield.getText());
				player2.setID(2);
				tfield.clear();
				if(playerCount == 2)
					showAllinfo(player1, player2, player3, player4);
				else {
					vbox.getChildren().remove(bt2);
					vbox.getChildren().add(bt3);
					label.setText("Please enter your name(Player3).");
				}
			});
			
			bt3.setOnAction(e->{
				//Assign names
				player3.setName(tfield.getText());
				player3.setID(3);
				tfield.clear();
				if(playerCount == 3)
					showAllinfo(player1, player2, player3, player4);
				else {
					vbox.getChildren().remove(bt3);
					vbox.getChildren().add(bt4);
					label.setText("Please enter your name(Player4).");
				}
			});
			
			bt4.setOnAction(e->{
				//Assign names
				player4.setName(tfield.getText());
				player4.setID(4);
				tfield.clear();
				if(playerCount == 4)
					showAllinfo(player1, player2, player3, player4);
				else {
					vbox.getChildren().remove(bt4);
				}
			});
		
	}

	public void showAllinfo(Player player1, Player player2, Player player3, Player player4) {
		//Define panes and initialize stage
		 
		StackPane pane = new StackPane();
		VBox vbox = new VBox();
		Stage stage = new Stage();
		stage = GameDemo.getStage();
		
		
		
		
		
		//Create title and button and placeholder for player info and button
		Label title = new Label("Player List");
		title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
		
		Button bt1 = new Button("Proceed");
		
		Label label1 = new Label(" ");
		Label label2 = new Label(" ");
		Label label3 = new Label(" ");
		Label label4 = new Label(" ");
		label1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		label2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		label3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		label4.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		
		
		/*Create background image, rectangle and set title, placeholder for player info
		 *  and button into VBox.
		 * Set background image, rectangle and VBox into pane
		 */
		Image img = new Image("file:background.jpg");
		ImageView iv = new ImageView();
		iv.setImage(img);
		
		Rectangle rec = new Rectangle(480, 360);
		rec.setFill(Color.WHITESMOKE);
		rec.setStroke(Color.BLACK);
		
		vbox.getChildren().addAll(title, label1, label2, label3, label4, bt1);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		pane.setPadding(new Insets(10)); 
		pane.setStyle("-fx-border-color: black");
		pane.getChildren().addAll(iv, rec, vbox);
		
		/*Replace placeholder with actual player info and set pane 
		into scene and show the scene through a stage.*/
		if (playerCount >= 2) {
			label1.setText("Player: " + player1.getName() + " Score: " + player1.getScore());
			label2.setText("Player: " + player2.getName() + " Score: " + player2.getScore());
		}	
		if (playerCount >= 3) 
			label3.setText("Player: " + player3.getName() + " Score: " + player3.getScore());
		if (playerCount >= 4) 
			label4.setText("Player: " + player4.getName() + " Score: " + player4.getScore());

		Scene scene = new Scene(pane, 1280, 720);
		stage.setScene(scene);
		
		//Handle events for button, which maps each player's ID with a hand of cards.
		bt1.setOnAction(e -> {
			if (playerCount >= 4) {
				hand.put(player1.getID(), set1); 	
				hand.put(player2.getID(), set2);
				hand.put(player3.getID(), set3); 
				hand.put(player4.getID(), set4); 
			}	
			else if (playerCount >= 3) {
				hand.put(player1.getID(), set1); 	
				hand.put(player2.getID(), set2);
				hand.put(player3.getID(), set3); 
			}
			else if (playerCount >= 2) {
				hand.put(player1.getID(), set1); 	
				hand.put(player2.getID(), set2);
			}
			PlayGame(player1, player2, player3, player4);
		});
	}
	
	public void PlayGame(Player player1, Player player2, Player player3, Player player4) {
		
		
		/*Create panes and initialize stages.
		 * Create background image, titles, and buttons
		 */
		Stage stage = new Stage();
		stage = GameDemo.getStage();
		StackPane root = new StackPane();
		BorderPane centerpane = new BorderPane();
		VBox score = new VBox();
		VBox topbox = new VBox();
		HBox bottombox = new HBox();
		HBox viewCard = new HBox();
		StackPane finalbox = new StackPane();
		
		
		Image img = new Image("file:background.jpg");
		ImageView iv = new ImageView();
		iv.setImage(img);
		
		Text title = new Text(player1.getName() + "'s turn!");
		Text title2 = new Text("Select and confirm a card!");
		title.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
		title2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 36));
		
		
		Button rdy1 = new Button("Display Cards");
		Button rdy2 = new Button("Display Cards");
		Button rdy3 = new Button("Display Cards");
		Button rdy4 = new Button("Display Cards");
		Button confirm = new Button("Confirm");
		Button btok = new Button("OK");
		Button exit = new Button("Exit");
		Button stopgame = new Button("Stop Game");
		
		
		
		//Set properties of all sub-panes, and set all sub-panes into major centerpane and execute scoreBoard method.
		
	
		
		score.setAlignment(Pos.BOTTOM_LEFT);
		topbox.getChildren().addAll(title, title2);
		topbox.setAlignment(Pos.CENTER);
		topbox.setSpacing(10);
		viewCard.setAlignment(Pos.CENTER);
		viewCard.setSpacing(10);
		bottombox.setAlignment(Pos.CENTER);
		bottombox.setSpacing(10);
		bottombox.setPadding(new Insets(10));
		centerpane.setBottom(bottombox);
		centerpane.setCenter(viewCard);
		centerpane.setTop(topbox);
		
		bottombox.getChildren().addAll(rdy1, stopgame);
		
		scoreBoard(player1, player2, player3, player4, score);
		
		
		
		//Set button properties
		rdy1.setPrefSize(150, 50);
		rdy2.setPrefSize(150, 50);
		rdy3.setPrefSize(150, 50);
		rdy4.setPrefSize(150, 50);
		confirm.setPrefSize(150, 50);
		btok.setPrefSize(150, 50);
		exit.setPrefSize(150, 50);
		stopgame.setPrefSize(150, 50);
		rdy1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		rdy2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		rdy3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		rdy4.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		confirm.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		btok.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		exit.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		stopgame.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
		
		
		
		/*Add background image, score vbox, 
		 * centerpane(which contains sub-panes which store the title on top, and cards in the middle), 
		 * and the ready button(rdy1)
		to root StackPane*/
		
		
		
		root.getChildren().addAll(iv, score, centerpane );
		
		
		
		
		Scene scene = new Scene(root, 1280, 720);
		stage.setScene(scene);
		
		//Event handling for all player's buttons
		//From the page with the Display Cards button, if you pressed ready
		//it will change to "confirm" and display all cards for Players
		rdy1.setOnAction(e->{
			bottombox.getChildren().remove(rdy1);
			
			bottombox.getChildren().remove(stopgame);
			bottombox.getChildren().addAll(confirm);
			displayCards(player1, 0, viewCard);
		});
		
		rdy2.setOnAction(e->{
			bottombox.getChildren().remove(rdy2);
			bottombox.getChildren().remove(stopgame);
			bottombox.getChildren().addAll(confirm);
			displayCards(player2, 1, viewCard);
		});
		
		rdy3.setOnAction(e->{
			bottombox.getChildren().remove(rdy3);
			bottombox.getChildren().remove(stopgame);
			bottombox.getChildren().addAll(confirm);
			displayCards(player3, 2, viewCard);
		});
		
		rdy4.setOnAction(e->{
			bottombox.getChildren().remove(rdy4);
			bottombox.getChildren().remove(stopgame);
			bottombox.getChildren().addAll(confirm);
			displayCards(player4, 3, viewCard);
		});
		
		
	/*	//For each round after each player has chosen their cards, they will press confirm.
		//confirm event handler will determine how many players are there through playerCount, 
		 * and which player has finished their turn through counter. It will also remove
		 * the cards and change the confirm button to Display Card button, add the
		 * Stop Game button, and set the title
		 * notifying that it's the next player's turn.
		 *  Each of the other player's chosen cards will be set.
		 *  
		 *  When the round is finished, the button will be changed from confirm to ok.
		 *  When the round is finished, the winning player's name will be displayed instead
		 *  of the next player's name.
		 *  When a round is finished, the winner will be determined, its card will be displayed,
		 *  its score will increase by 1.
		 *  WHen round is finished, round also increases by 1.
		 *  
		 * 
		 *  
*/		confirm.setOnAction(e -> {
			viewCard.getChildren().clear();
			bottombox.getChildren().remove(confirm);
			counter++;
			
			if(playerCount == 4) {//check the number of players/loops
				if(counter == 1) {
					getCard(player1, 0);
					bottombox.getChildren().add(rdy2);
					bottombox.getChildren().add(stopgame);
					title.setText(player2.getName() + "'s turn!");
				}
				else if(counter == 2) {
					getCard(player2, 1);
					bottombox.getChildren().add(rdy3);
					bottombox.getChildren().add(stopgame);
					title.setText(player3.getName() + "'s turn!");
				}
				else if(counter == 3) {
					getCard(player3, 2);
					bottombox.getChildren().add(rdy4);
					bottombox.getChildren().add(stopgame);
					title.setText(player4.getName() + "'s turn!");
				}
				else if(counter == 4) {
					getCard(player4, 3);
					compareWinner(player1, player2, player3, player4, viewCard, topbox, score);
					bottombox.getChildren().add(btok);
					round++;
				}
			}
			else if (playerCount == 3) {
				if(counter == 1) {
					getCard(player1, 0);
					bottombox.getChildren().add(rdy2);
					bottombox.getChildren().add(stopgame);
					title.setText(player2.getName() + "'s turn!");
				}
				else if(counter == 2) {
					getCard(player2, 1);
					bottombox.getChildren().add(rdy3);
					bottombox.getChildren().add(stopgame);
					title.setText(player3.getName() + "'s turn!");
				}
				else if(counter == 3) {
					getCard(player3, 2);
					compareWinner(player1, player2, player3, player4, viewCard, topbox, score);
					bottombox.getChildren().add(btok);
					round++;
				}
			}
			else if (playerCount == 2) {
				if(counter == 1) {
					getCard(player1, 0);
					bottombox.getChildren().add(rdy2);
					bottombox.getChildren().add(stopgame);
					title.setText(player2.getName() + "'s turn!");
				}
				else if(counter == 2) {
					getCard(player2, 1);
					compareWinner(player1, player2, player3, player4, viewCard, topbox, score);
					bottombox.getChildren().add(btok);
					round++;
				}
			}
		});
		

		/*Event handler for ok button.
		 * If the number of completed rounds is less than 7,
		 * Once a round is finished and the OK button is pressed, a new round starts.
		 * The title, buttons are replaced , and the cards are removed.
		 * 
		 * If number of completed rounds is more than 7,
		 * the cards and button is cleared. The final
		 * scores and an exit button is displayed.
		 */
		btok.setOnAction(e -> {
			if(round <= 7) {
				bottombox.getChildren().clear();
				viewCard.getChildren().clear();
				topbox.getChildren().clear();
				topbox.getChildren().addAll(title, title2);
				counter = 0;
				bottombox.getChildren().add(rdy1);
				bottombox.getChildren().add(stopgame);
				title.setText(player1.getName() + "'s turn!"); // show name of next player
			}
			else {
				viewCard.getChildren().clear();
				bottombox.getChildren().clear();
				bottombox.getChildren().add(exit);
				displayWinner(player1, player2, player3, player4, topbox, score);
				
			
		
				
				
			}
		});
		
		exit.setOnAction(e -> {
			System.exit(1);
		});
		
		stopgame.setOnAction(e->
		{
			GameDemo backtostart = new GameDemo();
			
			backtostart.start(backtostart.getStage());
		});
	}
	
	
	public void getCard(Player player, int index) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		Object[] keys = hand.keySet().toArray();
		list = hand.get(keys[index]);
		
		//get list[cardValue] which represents the card you chose in displayCards
		int temp = list.get(cardValue);
		
		//Remove card from player's ma
		hand.remove(player.getID(), list);
		list.remove(cardValue);
		hand.put(player.getID(), list);
		player.setCard(temp);
	}

	public void displayCards(Player player, int index, HBox viewCard) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//Get integer-deck maps and store in keys array
		Object[] keys = hand.keySet().toArray();
		
		//For the player (0,1,2,3), use their index as the identifier for the relvant mapping from 
		//the array of mappings retrieved, and assign the mapping (which represents a deck) to list
		list = hand.get(keys[index]);
		
		
		
		
		
		/*Create an ImageView to store card images.
		 * 
		 */
		ImageView ivCard1 = new ImageView();
		ImageView ivCard2 = new ImageView();
		ImageView ivCard3 = new ImageView();
		ImageView ivCard4 = new ImageView();
		ImageView ivCard5 = new ImageView();
		ImageView ivCard6 = new ImageView();
		ImageView ivCard7 = new ImageView();
		
		
		/*Depending on current deck size left, get relevant card image from folder.
		 * Set ImageView and add them into viewCard HBox.  
		 */
		if(list.size() >= 1) {
			ivCard1.setImage(new Image("file:" + list.get(0) + ".png"));
			ivCard1.setFitHeight(210);
			ivCard1.setFitWidth(140);
			ivCard1.setPickOnBounds(true); // allows click on transparent areas
			cardValue = 0;
			viewCard.getChildren().addAll(ivCard1);
		}
		if(list.size() >= 2) {
			ivCard2.setImage(new Image("file:" + list.get(1) + ".png"));
			ivCard2.setFitHeight(180);
			ivCard2.setFitWidth(120);
			ivCard2.setPickOnBounds(true);
			viewCard.getChildren().addAll(ivCard2);
		}
		if(list.size() >= 3) {
			ivCard3.setImage(new Image("file:" + list.get(2) + ".png"));
			ivCard3.setFitHeight(180);
			ivCard3.setFitWidth(120);
			ivCard3.setPickOnBounds(true);
			viewCard.getChildren().addAll(ivCard3);
		}
		if(list.size() >= 4) {
			ivCard4.setImage(new Image("file:" + list.get(3) + ".png"));
			ivCard4.setFitHeight(180);
			ivCard4.setFitWidth(120);
			ivCard4.setPickOnBounds(true);
			viewCard.getChildren().addAll(ivCard4);
		}
		if(list.size() >= 5) {
			ivCard5.setImage(new Image("file:" + list.get(4) + ".png"));
			ivCard5.setFitHeight(180);
			ivCard5.setFitWidth(120);
			ivCard5.setPickOnBounds(true);
			viewCard.getChildren().addAll(ivCard5);
		}
		if(list.size() >= 6) {
			ivCard6.setImage(new Image("file:" + list.get(5) + ".png"));
			ivCard6.setFitHeight(180);
			ivCard6.setFitWidth(120);
			ivCard6.setPickOnBounds(true);
			viewCard.getChildren().addAll(ivCard6);
		}
		if(list.size() >= 7) {
			ivCard7.setImage(new Image("file:" + list.get(6) + ".png"));
			ivCard7.setFitHeight(180);
			ivCard7.setFitWidth(120);
			ivCard7.setPickOnBounds(true);
			viewCard.getChildren().addAll(ivCard7);
		}
		
		
		//Set the size of different cards after any card is clicked.
		ivCard1.setOnMouseClicked((MouseEvent e) -> {
			cardValue = 0;
			ivCard1.setFitHeight(210);
			ivCard1.setFitWidth(140);
			ivCard2.setFitHeight(180);
			ivCard2.setFitWidth(120);
			ivCard3.setFitHeight(180);
			ivCard3.setFitWidth(120);
			ivCard4.setFitHeight(180);
			ivCard4.setFitWidth(120);
			ivCard5.setFitHeight(180);
			ivCard5.setFitWidth(120);
			ivCard6.setFitHeight(180);
			ivCard6.setFitWidth(120);
			ivCard7.setFitHeight(180);
			ivCard7.setFitWidth(120);
        });
		ivCard2.setOnMouseClicked((MouseEvent e) -> {
			cardValue = 1;
			ivCard1.setFitHeight(180);
			ivCard1.setFitWidth(120);
			ivCard2.setFitHeight(210);
			ivCard2.setFitWidth(140);
			ivCard3.setFitHeight(180);
			ivCard3.setFitWidth(120);
			ivCard4.setFitHeight(180);
			ivCard4.setFitWidth(120);
			ivCard5.setFitHeight(180);
			ivCard5.setFitWidth(120);
			ivCard6.setFitHeight(180);
			ivCard6.setFitWidth(120);
			ivCard7.setFitHeight(180);
			ivCard7.setFitWidth(120);
        });
		ivCard3.setOnMouseClicked((MouseEvent e) -> {
			cardValue = 2;
			ivCard1.setFitHeight(180);
			ivCard1.setFitWidth(120);
			ivCard2.setFitHeight(180);
			ivCard2.setFitWidth(120);
			ivCard3.setFitHeight(210);
			ivCard3.setFitWidth(140);
			ivCard4.setFitHeight(180);
			ivCard4.setFitWidth(120);
			ivCard5.setFitHeight(180);
			ivCard5.setFitWidth(120);
			ivCard6.setFitHeight(180);
			ivCard6.setFitWidth(120);
			ivCard7.setFitHeight(180);
			ivCard7.setFitWidth(120);
        });
		ivCard4.setOnMouseClicked((MouseEvent e) -> {
			cardValue = 3;
			ivCard1.setFitHeight(180);
			ivCard1.setFitWidth(120);
			ivCard2.setFitHeight(180);
			ivCard2.setFitWidth(120);
			ivCard3.setFitHeight(180);
			ivCard3.setFitWidth(120);
			ivCard4.setFitHeight(210);
			ivCard4.setFitWidth(140);
			ivCard5.setFitHeight(180);
			ivCard5.setFitWidth(120);
			ivCard6.setFitHeight(180);
			ivCard6.setFitWidth(120);
			ivCard7.setFitHeight(180);
			ivCard7.setFitWidth(120);
        });
		ivCard5.setOnMouseClicked((MouseEvent e) -> {
			cardValue = 4;
			ivCard1.setFitHeight(180);
			ivCard1.setFitWidth(120);
			ivCard2.setFitHeight(180);
			ivCard2.setFitWidth(120);
			ivCard3.setFitHeight(180);
			ivCard3.setFitWidth(120);
			ivCard4.setFitHeight(180);
			ivCard4.setFitWidth(120);
			ivCard5.setFitHeight(210);
			ivCard5.setFitWidth(140);
			ivCard6.setFitHeight(180);
			ivCard6.setFitWidth(120);
			ivCard7.setFitHeight(180);
			ivCard7.setFitWidth(120);
        });
		ivCard6.setOnMouseClicked((MouseEvent e) -> {
			cardValue = 5;
			ivCard1.setFitHeight(180);
			ivCard1.setFitWidth(120);
			ivCard2.setFitHeight(180);
			ivCard2.setFitWidth(120);
			ivCard3.setFitHeight(180);
			ivCard3.setFitWidth(120);
			ivCard4.setFitHeight(180);
			ivCard4.setFitWidth(120);
			ivCard5.setFitHeight(180);
			ivCard5.setFitWidth(120);
			ivCard6.setFitHeight(210);
			ivCard6.setFitWidth(140);
			ivCard7.setFitHeight(180);
			ivCard7.setFitWidth(120);
        });
		ivCard7.setOnMouseClicked((MouseEvent e) -> {
			cardValue = 6;
			ivCard1.setFitHeight(180);
			ivCard1.setFitWidth(120);
			ivCard2.setFitHeight(180);
			ivCard2.setFitWidth(120);
			ivCard3.setFitHeight(180);
			ivCard3.setFitWidth(120);
			ivCard4.setFitHeight(180);
			ivCard4.setFitWidth(120);
			ivCard5.setFitHeight(180);
			ivCard5.setFitWidth(120);
			ivCard6.setFitHeight(180);
			ivCard6.setFitWidth(120);
			ivCard7.setFitHeight(210);
			ivCard7.setFitWidth(140);
        });
	}

	public void displayWinner(Player player1, Player player2, Player player3, Player player4, VBox topbox, VBox score) {
		int temp = 0; 
		
		//Sets player names and font properties
		Text name1 = new Text(player1.getName());
		Text name2 = new Text(player2.getName());
		Text name3 = new Text(player3.getName());
		Text name4 = new Text(player4.getName());
		Text winner = new Text("The winner(s) is: ");
		name1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
		name2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
		name3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
		name4.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
		winner.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
		
		topbox.getChildren().clear();
		topbox.getChildren().addAll(winner);
		
		
		//Determine which player is the winner
		temp = Math.max(player1.getScore(), temp);
		temp = Math.max(player2.getScore(), temp);
		temp = Math.max(player3.getScore(), temp);
		temp = Math.max(player4.getScore(), temp);
		
		//Display winning player's name
		if (temp == player1.getScore()) {
			topbox.getChildren().add(name1);
		}
		if (temp == player2.getScore()) {
			topbox.getChildren().add(name2);
		}
		if (temp == player3.getScore()) {
			topbox.getChildren().add(name3);
		}
		if (temp == player4.getScore()) {
			topbox.getChildren().add(name4);
		}
		scoreBoard(player1, player2, player3, player4, score);
	} 

	public void scoreBoard(Player player1, Player player2, Player player3, Player player4, VBox vbox) {
		
		//Create labels for each player
		Text label1 = new Text();
		Text label2 = new Text();
		Text label3 = new Text();
		Text label4 = new Text();
		label1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));
		label2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));
		label3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));
		label4.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 24));
		label1.setFill(Color.BLACK);
		label2.setFill(Color.BLACK);
		label3.setFill(Color.BLACK);
		label4.setFill(Color.BLACK);
		
		
		//Display all player's current score 
		if (playerCount >= 2) {
			label1.setText("Player: " + player1.getName() + " Score: " + player1.getScore());
			label2.setText("Player: " + player2.getName() + " Score: " + player2.getScore());
		}	
		if (playerCount >= 3) 
			label3.setText("Player: " + player3.getName() + " Score: " + player3.getScore());
		if (playerCount >= 4) 
			label4.setText("Player: " + player4.getName() + " Score: " + player4.getScore());
		vbox.getChildren().clear();
		vbox.getChildren().addAll(label1, label2, label3, label4);
		
		
	}

	public void compareWinner(Player player1, Player player2, Player player3, Player player4, HBox viewCard, VBox topbox, VBox score) {
		int temp = -1;
		
		//Get the card images that each player chose for the current round.
		ImageView ivCard1 = new ImageView(new Image("file:" + player1.getCard() + ".png"));
		ImageView ivCard2 = new ImageView(new Image("file:" + player2.getCard() + ".png"));
		ImageView ivCard3 = new ImageView(new Image("file:" + player3.getCard() + ".png"));
		ImageView ivCard4 = new ImageView(new Image("file:" + player4.getCard() + ".png"));
		ivCard1.setFitHeight(180);
		ivCard1.setFitWidth(120);
		ivCard2.setFitHeight(180);
		ivCard2.setFitWidth(120);
		ivCard3.setFitHeight(180);
		ivCard3.setFitWidth(120);
		ivCard4.setFitHeight(180);
		ivCard4.setFitWidth(120);
		
		
		
		//Set the player's names
		Text name1 = new Text(player1.getName());
		Text name2 = new Text(player2.getName());
		Text name3 = new Text(player3.getName());
		Text name4 = new Text(player4.getName());
		Text winner = new Text(" ");
		name1.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
		name2.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
		name3.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
		name4.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 18));
		winner.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 50));
	
		//Replace former display box on top which displayed "player's turn1 Select and confirm a card!"
		topbox.getChildren().clear();
		topbox.getChildren().addAll(winner);
		
	
		
		//Set chosen card images to vbox
		
		
		VBox vbox1 = new VBox();
		VBox vbox2 = new VBox();
		VBox vbox3 = new VBox();
		VBox vbox4 = new VBox();
		vbox1.setAlignment(Pos.CENTER);
		vbox2.setAlignment(Pos.CENTER);
		vbox3.setAlignment(Pos.CENTER);
		vbox4.setAlignment(Pos.CENTER);
		vbox1.getChildren().addAll(ivCard1, name1);
		vbox2.getChildren().addAll(ivCard2, name2);
		vbox3.getChildren().addAll(ivCard3, name3);
		vbox4.getChildren().addAll(ivCard4, name4);
		
		
		//Display final chosen cards according to number of players
		if(playerCount >= 2)
			viewCard.getChildren().addAll(vbox1, vbox2);
		if(playerCount >= 3)
			viewCard.getChildren().addAll(vbox3);
		if(playerCount >= 4)
			viewCard.getChildren().addAll(vbox4);
		
		//Returns the largest card
		temp = Math.max(player1.getCard(), temp);
		temp = Math.max(player2.getCard(), temp);
		temp = Math.max(player3.getCard(), temp);
		temp = Math.max(player4.getCard(), temp);
		
		//Determines which player got the largest card.
		//Set the winner's name to the player's name, display the winning card.
		//Increase the winning player's score by 1.
		if (temp == player1.getCard()) {
			winner.setText("The winner is " + player1.getName());
			ivCard1.setFitHeight(210);
			ivCard1.setFitWidth(140);
			player1.setScore(player1.getScore() + 1);
		}
		else if (temp == player2.getCard()) {
			winner.setText("The winner is " + player2.getName());
			ivCard2.setFitHeight(210);
			ivCard2.setFitWidth(140);
			player2.setScore(player2.getScore() + 1);	
		}
		else if (temp == player3.getCard()) {
			winner.setText("The winner is " + player3.getName());
			ivCard3.setFitHeight(210);
			ivCard3.setFitWidth(140);
			player3.setScore(player3.getScore() + 1);
		}
		else if (temp == player4.getCard()) {
			winner.setText("The winner is " + player4.getName());
			ivCard4.setFitHeight(210);
			ivCard4.setFitWidth(140);
			player4.setScore(player4.getScore() + 1);
		}
		scoreBoard(player1, player2, player3, player4, score);
	}
	
	
}