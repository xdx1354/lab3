import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {

    public static void main(String []Args){
        String filename = Args[0];
        File fileOpen = new File("D:\\PWR\\3 sem\\Jezyki_programowania\\lab3\\src\\main\\java\\inventory.txt");
        List<String> lines = new ArrayList<String>();
        List<Product> itemsList = new ArrayList<>();
        String lastTag="";

        try{
            Scanner sc = new Scanner(fileOpen);
            while(sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
            sc.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        lastTag = Utils.createItemsList(lines,itemsList,lastTag);
        System.out.println(itemsList);


    }
}
