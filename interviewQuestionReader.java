import java.awt.BorderLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class interviewQuestionReader {

    private static List<String> questions = new ArrayList<>();
    private static int currentIndex = 0;

    public static void main(String[]args){

        questions = loadQuestions("questions.csv");

        if(questions.isEmpty()){
            System.out.print("No questions found or failed to load file");
            return;
        }

        SwingUtilities.invokeLater(() -> createAndShowGui());

    }

    private static List<String> loadQuestions(String filename){

        List<String> questions = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader("questions.csv"))){
            String line;

            while((line = reader.readLine()) != null){
                if(!line.trim().isEmpty()){
                    questions.add(line.trim());
                }
            }
        } catch(IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }

        return questions;
    }

    private static void createAndShowGui(){

        JFrame frame = new JFrame("Interview Question Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        JTextArea questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font("Sanserif", Font.PLAIN, 14));

        JButton nextButton = new JButton("Next");
        JButton quitButton = new JButton("Quit");

        nextButton.addActionListener(e -> {
            if(currentIndex < questions.size()){
                questionArea.setText(questions.get(currentIndex));
                currentIndex++;
            } else {
                questionArea.setText("No more questions. Bettt lol");
                nextButton.setEnabled(false); // this disables the next button
            }
        });

        quitButton.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(nextButton);
        buttonPanel.add(quitButton);

        frame.getContentPane().add(new JScrollPane(questionArea), BorderLayout.CENTER);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);

        nextButton.doClick();







    }
}