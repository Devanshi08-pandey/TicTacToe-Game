package com.example.anactualjavafxapp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeFX extends Application {
    private char currentPlayer = 'X';
    private Button[][] board = new Button[3][3];
    private Label statusLabel;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #1e1e1e;"); // dark background

        // Status label at the top
        statusLabel = new Label("Player X's Turn");
        statusLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold;");
        statusLabel.setAlignment(Pos.CENTER);
        root.setTop(statusLabel);

        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        grid.setAlignment(Pos.CENTER);

        // Create board buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button btn = new Button(" ");
                btn.setPrefSize(120, 120);

                // Style for buttons
                btn.setStyle("-fx-font-size: 36px; -fx-background-color: #2c2c2c; "
                        + "-fx-text-fill: white; -fx-font-weight: bold; "
                        + "-fx-border-color: #888; -fx-border-width: 3; "
                        + "-fx-background-radius: 10; -fx-border-radius: 10;");

                int r = row, c = col;
                btn.setOnAction(e -> handleMove(r, c, btn));
                board[row][col] = btn;
                grid.add(btn, col, row);
            }
        }

        root.setCenter(grid);

        Scene scene = new Scene(root, 400, 450);
        primaryStage.setTitle("✨ Tic Tac Toe ✨");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleMove(int row, int col, Button btn) {
        if (!btn.getText().equals(" ")) {
            return; // already filled
        }

        btn.setText(String.valueOf(currentPlayer));
        // Different colors for X and O
        if (currentPlayer == 'X') {
            btn.setStyle(btn.getStyle() + "-fx-text-fill: #ff4c4c;"); // red X
        } else {
            btn.setStyle(btn.getStyle() + "-fx-text-fill: #4ca3ff;"); // blue O
        }

        if (haveWon(currentPlayer)) {
            statusLabel.setText("🎉 Player " + currentPlayer + " Wins!");
            showResult("🎉 Player " + currentPlayer + " has won!");
            resetBoard();
        } else if (isBoardFull()) {
            statusLabel.setText("It's a Draw!");
            showResult("It's a Draw!");
            resetBoard();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            statusLabel.setText("Player " + currentPlayer + "'s Turn");
        }
    }

    private boolean haveWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].getText().equals(String.valueOf(player)) &&
                    board[i][1].getText().equals(String.valueOf(player)) &&
                    board[i][2].getText().equals(String.valueOf(player))) return true;

            if (board[0][i].getText().equals(String.valueOf(player)) &&
                    board[1][i].getText().equals(String.valueOf(player)) &&
                    board[2][i].getText().equals(String.valueOf(player))) return true;
        }

        if (board[0][0].getText().equals(String.valueOf(player)) &&
                board[1][1].getText().equals(String.valueOf(player)) &&
                board[2][2].getText().equals(String.valueOf(player))) return true;

        if (board[0][2].getText().equals(String.valueOf(player)) &&
                board[1][1].getText().equals(String.valueOf(player)) &&
                board[2][0].getText().equals(String.valueOf(player))) return true;

        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showResult(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col].setText(" ");
                board[row][col].setStyle("-fx-font-size: 36px; -fx-background-color: #2c2c2c; "
                        + "-fx-text-fill: white; -fx-font-weight: bold; "
                        + "-fx-border-color: #888; -fx-border-width: 3; "
                        + "-fx-background-radius: 10; -fx-border-radius: 10;");
            }
        }
        currentPlayer = 'X';
        statusLabel.setText("Player X's Turn");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
