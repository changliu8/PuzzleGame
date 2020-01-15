import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.Random;

public class Puzzle extends Application {
    Button[][] pieces;
    String viewname;
    String imagename;
    Timeline updateTimer;

    int[][][] ImageView = new int[4][4][2];
    int[][][] initView = new int[4][4][2];
    Random rnd = new Random();
    int r = (int)(Math.random()*4);
    int c = (int)(Math.random()*4);
    int duration = 0;



    public void start(Stage primaryStage) {

        Pane right = new Pane();
        right.relocate(758,5);
        right.setPrefSize(748,190);
        Pane puzzle = new Pane();
        puzzle.setStyle("-fx-padding: 50 50;");
        Scene window = new Scene(puzzle, 1000, 748);
        primaryStage.setTitle("My Window");
        primaryStage.setScene(window);
        primaryStage.show();

        ListView<String> puzzlelist = new ListView<String>();
        String[] puzzles = {"Pets","Scenery","Lego","Numbers"};
        puzzlelist.setItems(FXCollections.observableArrayList(puzzles));
        puzzlelist.relocate(758,197);
        puzzlelist.setPrefSize(187,187);
        String selectedString = puzzlelist.getSelectionModel().getSelectedItem();



        pieces = new Button[4][4];
        for (int col = 0; col<4;col ++){
            for (int row = 0;row<4;row++){
                imagename = "BLANK.png";
                ImageView[col][row][0] = row;
                ImageView[col][row][1] = col;
                initView[col][row][0] = row;
                initView[col][row][1] = col;
                Image lego = new Image(getClass().getResourceAsStream(imagename));
                pieces[row][col] = new Button();
                pieces[row][col].setGraphic(new ImageView(lego));
                pieces[row][col].relocate(row*187, col*187);
                pieces[row][col].setPrefSize(187,187);
                pieces[row][col].setPadding(new Insets(1,1,1,1));
                puzzle.getChildren().addAll(pieces[row][col]);
            }
        }

        ImageView[c][r][0] = -1;
        ImageView[c][r][1] = -1;
        initView[c][r][0] = -1;
        initView[c][r][1] = -1;

        viewname = "BLANK.png";
        Image view = new Image(getClass().getResourceAsStream(viewname));
        Label b = new Label();
        b.setGraphic(new ImageView(view));
        b.relocate(758,5);
        b.setPrefSize(187,187);
        b.setPadding(new Insets(0,0,0,0));

        Button start = new Button();
        start.setText("Start");
        start.setAlignment(Pos.CENTER);
        start.setPrefSize(187,20);
        start.relocate(758,390);
        start.setStyle("-fx-base: GREEN;");



        Button stop = new Button();
        stop.setText("Stop");
        stop.setAlignment(Pos.CENTER);
        stop.setPrefSize(187,20);
        stop.relocate(758,390);
        stop.setStyle("-fx-base: RED;");
        stop.setVisible(false);



        //time
        Label timer = new Label("Time:");
        timer.relocate(758, 415);
        timer.setPrefSize(80, 30);
        TextField time = new TextField();
        time.relocate(820, 420);
        time.setPrefSize(120, 20);

        updateTimer = new Timeline(new KeyFrame(Duration.millis(1000),
                new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        // FILL IN YOUR CODE HERE THAT WILL GET CALLED ONCE PER SEC.

                        duration ++;
                        int second = duration%60;
                        int minute = duration/60;
                        time.setText((minute)+":"+second);

                    }
                }));
        updateTimer.setCycleCount(Timeline.INDEFINITE);

        time.setAlignment(Pos.BASELINE_LEFT);








        //start
        puzzlelist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                String selectedString = puzzlelist.getSelectionModel().getSelectedItem();
                if (selectedString != null) {
                    Image view = new Image(getClass().getResourceAsStream(selectedString + "_Thumbnail.png"));
                    b.setGraphic(new ImageView(view));
                }
            }
        });
        start.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {


                String selectedString = puzzlelist.getSelectionModel().getSelectedItem();
                if(selectedString !=null){
                    updateTimer.play();
                    duration = 0;
                    for (int col = 0; col < 4; col++) {
                        for (int row = 0; row < 4; row++) {
                            imagename = selectedString + "_" + col + row + ".png";
                            viewname = selectedString + "_Thumbnail.png";
                            Image lego = new Image(getClass().getResourceAsStream(imagename));
                            Image view = new Image(getClass().getResourceAsStream(viewname));
                            b.setGraphic(new ImageView(view));
                            pieces[row][col] = new Button();
                            pieces[row][col].setGraphic(new ImageView(lego));
                            pieces[row][col].relocate(row * 187, col * 187);
                            pieces[r][c].setGraphic(new ImageView("BLANK.png"));
                            pieces[row][col].setPrefSize(187, 187);
                            pieces[row][col].setPadding(new Insets(1, 1, 1, 1));
                            puzzle.getChildren().addAll(pieces[row][col]);
                        }
                    }
                    init(selectedString);

                    b.setDisable(true);
                    start.setVisible(false);
                    stop.setVisible(true);
                    for (int col = 0; col < 4; col++) {
                        for (int row = 0; row < 4; row++) {
//                            imagename = selectedString + "_" + col + row + ".png";
//                            viewname = selectedString + "_Thumbnail.png";
//                            Image lego = new Image(getClass().getResourceAsStream(imagename));
//                            Image view = new Image(getClass().getResourceAsStream(viewname));
//                            b.setGraphic(new ImageView(view));
//                            pieces[row][col] = new Button();
//                            pieces[row][col].setGraphic(new ImageView(lego));
//                            pieces[row][col].relocate(row * 187, col * 187);
//                            pieces[r][c].setGraphic(new ImageView("BLANK.png"));
//                            pieces[row][col].setPrefSize(187, 187);
//                            pieces[row][col].setPadding(new Insets(1, 1, 1, 1));
//                            puzzle.getChildren().addAll(pieces[row][col]);
                            //buttons clicked
                            pieces[row][col].setOnMouseClicked(new EventHandler<MouseEvent>() {

                                public void handle(MouseEvent event) {

                                    for (int col = 0; col < 4; col++) {
                                        for (int row = 0; row < 4; row++) {
                                            if (event.getSource() == pieces[row][col]) {
                                                swap(row,col,selectedString);

                                            }
                                        }
                                    }

                                    if(checkResult(initView,ImageView)){
                                        updateTimer.stop();
                                        time.setText((0)+":"+0);
                                        System.out.println("done");
                                        stop.setVisible(false);
                                        start.setVisible(true);
                                        b.setDisable(false);
                                        for (int col = 0; col<4;col ++){
                                            for (int row = 0;row<4;row++){
                                                imagename = "BLANK.png";
                                                ImageView[col][row][0] = row;
                                                ImageView[col][row][1] = col;
                                                Image lego = new Image(getClass().getResourceAsStream(imagename));
                                                b.setGraphic(new ImageView("BLANK.png"));
                                                pieces[row][col] = new Button();
                                                pieces[row][col].setGraphic(new ImageView(lego));
                                                pieces[row][col].relocate(row*187, col*187);
                                                pieces[row][col].setPrefSize(187,187);
                                                pieces[row][col].setPadding(new Insets(1,1,1,1));
                                                puzzle.getChildren().addAll(pieces[row][col]);
                                            }
                                        }
                                        ImageView[c][r][0] = -1;
                                        ImageView[c][r][1] = -1;
                                        initView[c][r][0] = -1;
                                        initView[c][r][1] = -1;
                                    }


                                }

                            });


                        }
                    }

                }
            }
        });
        //stop
        stop.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                updateTimer.stop();
                time.setText((0)+":"+0);
                stop.setVisible(false);
                start.setVisible(true);
                b.setDisable(false);
                for (int col = 0; col<4;col ++){
                    for (int row = 0;row<4;row++){
                        imagename = "BLANK.png";
                        ImageView[col][row][0] = row;
                        ImageView[col][row][1] = col;
                        Image lego = new Image(getClass().getResourceAsStream(imagename));
                        b.setGraphic(new ImageView("BLANK.png"));
                        pieces[row][col] = new Button();
                        pieces[row][col].setGraphic(new ImageView(lego));
                        pieces[row][col].relocate(row*187, col*187);
                        pieces[row][col].setPrefSize(187,187);
                        pieces[row][col].setPadding(new Insets(1,1,1,1));
                        puzzle.getChildren().addAll(pieces[row][col]);
                    }
                }
                ImageView[c][r][0] = -1;
                ImageView[c][r][1] = -1;
                initView[c][r][0] = -1;
                initView[c][r][1] = -1;
            }
        });



        right.getChildren().addAll(b);
        puzzle.getChildren().addAll(puzzlelist,b,start,stop,timer,time);





    }

    public void init(String selectedString){
        for (int t = 0; t < 300; t++) {
            //left
            if (r==0 && (c !=0 | c !=3)) {
                switch((int)(Math.random()*3+1))
                {
                    case 1 :
                        swap(r, c - 1, selectedString);
                        break; // break is optional
                    case 2 :
                        swap(r, c+1, selectedString);
                        break; // break is optional
                    case 3 :
                        swap(r+1, c, selectedString);
                        break; // break is optional
                    default :
                        swap(r+1, c, selectedString);
                }
            }
            else if (r==0 && (c ==0 | c ==3)) {
                swap(r+=1, c, selectedString);
            }
//right
            else if (r==3 && (c !=0 | c !=3)) {
                switch((int)(Math.random()*3+1))
                {
                    case 1 :
                        swap(r, c - 1, selectedString);
                        break; // break is optional
                    case 2 :
                        swap(r, c+1, selectedString);
                        break; // break is optional
                    case 3 :
                        swap(r-1, c, selectedString);
                        break; // break is optional
                    default :
                        swap(r, c - 1, selectedString);
                }
            }
            else if (r==3 && (c ==0 | c ==3)) {
                swap(r-1, c, selectedString);
            }

//top
            else if (c==0 && (r !=0 | r !=3)) {
                switch((int)(Math.random()*3+1))
                {
                    case 1 :
                        swap(r-1, c, selectedString);
                        break; // break is optional
                    case 2 :
                        swap(r+1, c, selectedString);
                        break; // break is optional
                    case 3 :
                        swap(r, c+1, selectedString);
                        break; // break is optional
                    default :
                        swap(r, c+1, selectedString);
                }
            }
            else if (c==0 && (r ==0 | r ==3)) {
                swap(r, c+1, selectedString);
            }
//bot
            else if (c==3 && (r !=0 | r !=3)) {
                switch((int)(Math.random()*3+1))
                {
                    case 1 :
                        swap(r-1, c, selectedString);
                        break; // break is optional
                    case 2 :
                        swap(r+1, c, selectedString);
                        break; // break is optional
                    case 3 :
                        swap(r, c-1, selectedString);
                        break; // break is optional
                    default :
                        swap(r, c-1, selectedString);
                }
            } else if (c==3 && (r ==0 | r ==3)) {
                swap(r, c-1, selectedString);
            }else if (r>0 && c >0 && c<3 && r<3) {
                switch((int)(Math.random()*4+1))
                {
                    case 1 :
                        swap(r, c - 1, selectedString);
                        break; // break is optional
                    case 2 :
                        swap(r, c + 1, selectedString);
                        break; // break is optional
                    case 3 :
                        swap(r+1, c, selectedString);
                        break; // break is optional
                    case 4 :
                        swap(r-1, c, selectedString);
                        break; // break is optional
                    default :
                        swap(r, c - 1, selectedString);
                }
            }
        }
    }

    public void swap(int row, int col, String selectedString){


        if ((row == 3 && (col == 1 || col == 2))) {
            if (pieces[r][c] == pieces[row-1][col] || pieces[r][c] == pieces[row][col+1] || pieces[r][c] == pieces[row][col-1]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }



        if ((col == 3 && (row == 1 || row == 2))) {
            if (pieces[r][c] == pieces[row][col-1] || pieces[r][c] == pieces[row+1][col] || pieces[r][c] == pieces[row-1][col]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }


        if (row == 3 && (col == 3)) {
            if (pieces[r][c] == pieces[row-1][col] || pieces[r][c] == pieces[row][col-1]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }

        if (row == 3 && (col == 0)) {
            if (pieces[r][c] == pieces[row-1][col] || pieces[r][c] == pieces[row][col+1]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }

        if (row == 0 && (col == 3)) {
            if (pieces[r][c] == pieces[row+1][col] || pieces[r][c] == pieces[row][col-1]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }


        if (row == 0 && (col == 0)) {
            if (pieces[r][c] == pieces[row+1][col] || pieces[r][c] == pieces[row][col+1]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }

        if ((col == 0 && (row == 1 || row == 2))) {
            if (pieces[r][c] == pieces[row][col+1] || pieces[r][c] == pieces[row+1][col] || pieces[r][c] == pieces[row-1][col]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }

        if ((row == 0 && (col == 1 || col == 2))) {
            if (pieces[r][c] == pieces[row+1][col] || pieces[r][c] == pieces[row][col+1] || pieces[r][c] == pieces[row][col-1]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }

        if (row > 0 && row <3 && col>0 && col<3) {
            if (pieces[r][c] == pieces[row+1][col] || pieces[r][c] == pieces[row][col+1] || pieces[r][c] == pieces[row][col-1]|| pieces[r][c] == pieces[row-1][col]){




                int[] temp= ImageView[col][row];
                ImageView[col][row] = ImageView[c][r];
                ImageView[c][r] = temp;




                String changed1 = "";
                String changed2 = "";
                if (ImageView[col][row][0] == -1){
                    changed1 = "BLANK.png";
                } else{
                    changed1 = selectedString + "_" + ImageView[col][row][1] + ImageView[col][row][0] + ".png";
                }
                if (ImageView[c][r][0] == -1){
                    changed2 = "BLANK.png";
                } else {
                    changed2 = selectedString + "_" + ImageView[c][r][1] + ImageView[c][r][0] + ".png";
                }


                pieces[row][col].setGraphic(new ImageView(changed1));
                pieces[r][c].setGraphic(new ImageView(changed2));

                r = row;
                c = col;
            }
        }
    }





    public static boolean checkResult(int[][][] initView, int[][][] imageView){
        for (int a = 0; a<4; a++){
            for (int b = 0; b<4; b++){
                for (int j = 0; j<2; j++)
                {
                    if(initView[a][b][j] != imageView[a][b][j]){
                        return false;
                    }
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }

}

