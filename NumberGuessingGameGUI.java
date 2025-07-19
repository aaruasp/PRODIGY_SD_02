package com.nt.tes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

@SuppressWarnings("serial")
public class NumberGuessingGameGUI extends JFrame implements ActionListener {

    private JLabel promptLabel;
    private JTextField guessField;
    private JButton guessButton, resetButton;
    private JLabel resultLabel;
    private int randomNumber;
    private int attempts;

    public NumberGuessingGameGUI() {
        setTitle("Number Guessing Game");
        setSize(400, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        promptLabel = new JLabel("Guess a number between 1 and 100:");
        add(promptLabel);

        guessField = new JTextField(10);
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.addActionListener(this);
        add(guessButton);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        add(resetButton);

        resultLabel = new JLabel(" ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(resultLabel);

        generateRandomNumber();
        setVisible(true);
    }

    private void generateRandomNumber() {
        Random rand = new Random();
        randomNumber = rand.nextInt(100) + 1;
        attempts = 0;
    }

    private void resetGame() {
        guessField.setText("");
        resultLabel.setText("Game reset! Try again.");
        generateRandomNumber();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String userInput = guessField.getText();

        try {
            int guess = Integer.parseInt(userInput);
            attempts++;

            if (guess == randomNumber) {
                resultLabel.setText("🎉 Correct! Attempts: " + attempts);
                guessButton.setEnabled(false);
            } else if (guess < randomNumber) {
                resultLabel.setText("Too low! Try again.");
            } else {
                resultLabel.setText("Too high! Try again.");
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("❌ Invalid input. Enter a number!");
        }
    }

    public static void main(String[] args) {
        new NumberGuessingGameGUI();
    }
}
