import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static String createItemsList(List<String> lines, List<Product> itemsList,String lastTag){ // generuje listę wszystkich produktów na podstawie pliku i zwraca ostatni tag

        for (int i=0; i< lines.size(); i++) {
            String line = lines.get(i);
            String[] splited = line.split("\\s*,\\s*");
            Product tempProduct = new Product(splited[0], splited[1], Float.parseFloat(splited[2]));
            itemsList.add(tempProduct);
            lastTag = splited[0];
        }
        return lastTag;
    }

    public static void addItem(String tag, String name, float price, List<Product>itemsList){ //dodać filewritter, który dopisze na ostaniej pozycji dany produkt
        Product tempItem = new Product(tag,name,price);
        itemsList.add(tempItem);

    }

    public static String generateNewTag(String lastTag){ //generuje nowy tag wiekszy o 1 od poprzedniego
        int intTag = Integer.parseInt(lastTag)+1;
        String newTag = String.valueOf(intTag);
        while(newTag.length()<4) newTag = "0"+newTag;
        return newTag;
    }

    public static void generateNewFile (List<Product>itemsList) throws IOException {
        File fileOpen = new File("D:\\PWR\\3 sem\\Jezyki_programowania\\lab3\\src\\main\\java\\inventory.txt");
        FileWriter writer = new FileWriter(fileOpen, false);
        int i = 0;
        for(Product p: itemsList) {
            writer.write(itemsList.get(i).toString2() + System.lineSeparator());
            i++;
        }
        writer.close();
    }

    public static void searchForItem(@NotNull List<Product> itemsList, String nameFromUser){
        int ile = 0;
        nameFromUser=nameFromUser.toLowerCase(Locale.ROOT);
        String customPattern = nameFromUser.replace("?","[a-zA-Z]");
        customPattern = customPattern.replace("*","[a-zA-Z]+");

        for( Product p: itemsList){
                Pattern pattern = Pattern.compile(customPattern);
                Matcher matcher = pattern.matcher(p.name);
                if(matcher.matches()){
                    ile++;
                    System.out.print(p.toString());
                }
        }
        if(ile==0){
            System.out.println("Nie ma takiego produktu");
        }
        else{
            if(ile == 1){
                System.out.println("W magazynie jest jeden taki produkt");
            }
            else{
                System.out.println("W magazynie: x" +ile+ " " + nameFromUser);
            }
        }
    }

    public static void removeItem(List<Product> itemsList, String tagToRemove){
        if(!itemsList.removeIf(p -> Objects.equals(p.tag, tagToRemove))){
            System.out.println("Nie ma takiego produktu!!");
        }
    }

    public static void addToCart(List<Product> itemsList, String tagToAdd, List<Product> clientCart ){
            boolean check=false;
        for(Product p:itemsList){
            if(Objects.equals(p.tag, tagToAdd)){
                clientCart.add(p);
                check=true;
            }
        }
        if(check){
            removeItem(itemsList,tagToAdd); //produkty usuwane są od razu z listy, nie ma możliwosci wyjecia ich z koszyka i "oddania na półkę"
        }
        else{
            System.out.println("Produkt o podanym tagu nie istnieje.\n\n");
        }
    }

    public static float summariseCart (List<Product> clientCart){
        float sum=0;
        for(Product p:clientCart){
            sum+=p.price;
        }
        return sum;
    }

    public static void finnaliseOrder(List<Product> clientCart, List<Product> itemsList){
        System.out.println("<> PODSUMOWANIE ZAMÓWNIENIA <>\n");
        System.out.println("ZAMÓWIONE PRODUKTY:");
        System.out.print(clientCart.toString());
        System.out.println("\nSuma: " + summariseCart(clientCart));
        System.out.println("Dziekujemy za skorzystanie z naszych usług");

    }
}