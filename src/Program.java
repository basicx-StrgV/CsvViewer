import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Program {
    public static void main(String[] args){
        new Program().run();
    }

    public void run()
    {
        DataTable dataTable = new DataTable();

        File file = validateFile(readUserInput());

        if(file != null){
            ArrayList<String> lines = readFileByLines(file);

            String[] headerValues = null;
            if(!lines.isEmpty()){
                headerValues = lines.get(0).split(",");
    
                dataTable.addDataRow(new DataRow(headerValues, true));
            }
            else{
                return;
            }
    
            for(int i = 1; i < lines.size(); i++){
                String[] values = lines.get(i).split(",");
    
                dataTable.addDataRow(new DataRow(values, false));
            }
    
            dataTable.render();
        }
        
        System.exit(0);
    }

    private String readUserInput(){
        String userInput = "";
        BufferedReader inputReader = null;
        try{
            inputReader = new BufferedReader(new InputStreamReader(System.in));
            
            Console.write(
                "\n" + Console.Colors.GREEN + "Enter file path/name: " + Console.Colors.RESET);
            userInput = inputReader.readLine();
            Console.write("\n");
        }
        catch(Exception e){
            Console.writeLine(e.getMessage());
        }
        
        return userInput;
    }

    private ArrayList<String> readFileByLines(File file){
        ArrayList<String> lines = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)){
            while(scanner.hasNextLine()){
                lines.add(scanner.nextLine());
            }
        }
        catch(Exception e) {
            Console.writeLine(e.getMessage());
        }

        return lines;
    }

    private File validateFile(String fileName){
        try{
            File file = new File(fileName);

            if(!file.exists()){
                Console.writeLine(
                    Console.Colors.RED + "Can't find the given file: \"" + fileName + "\"" + Console.Colors.RESET + "\n");
                return null;
            }

            if(!file.isFile()){
                Console.writeLine(
                    Console.Colors.RED + "\"" + fileName + "\" is not a file!" + Console.Colors.RESET + "\n");
                return null;
            }

            if(!file.canRead()){
                Console.writeLine(
                    Console.Colors.RED + "Can't read the given file: \"" + fileName + "\"" + Console.Colors.RESET + "\n");
                return null;
            }
            
            if(file.getPath().split("\\.")[
                file.getPath().split("\\.").length - 1]
                .equalsIgnoreCase("csv")){
                return file;
            }
            Console.writeLine(
                Console.Colors.RED + "Can't given file is not a \"csv\" file: \"" + fileName + "\"" + Console.Colors.RESET + "\n");
            return null;
        }
        catch(Exception e){
            Console.writeLine(
                Console.Colors.RED + "An error occurred while checking file: \"" + fileName + "\"" + Console.Colors.RESET + "\n");
            return null;
        }
    }
}