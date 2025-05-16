import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class interviewQuestionReader {

    public static void main(String[]args){

        List<String> questions = loadQuestions("questions.csv");

        if(questions.isEmpty()){
            System.out.print("No questions found or failed to load file");
            return;
        }

        runInteractiveSession(questions);

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

    private static void runInteractiveSession(List<String> questions){

        Scanner scnr = new Scanner(System.in);

        System.out.print("\nPress enter to see the next question or type 'p' to quit");

        for(String question: questions){
            String input = scnr.nextLine();
            if(input.equalsIgnoreCase("q")){
                System.out.println("\nBettt rest uppp\n");
                break;
            }

            System.out.print("\nQuestion/Questions: " + question + " ");
        }
    }
}