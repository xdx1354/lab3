import java.util.Arrays;
import java.util.List;

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
    public static void addItem(String tag, String name, Float price, List<Product>itemsList){ //dodać filewritter, który dopisze na ostaniej pozycji dany produkt
        Product tempItem = new Product(tag,name,price);
        itemsList.add(tempItem);

    }

    public static String generateTag(String lastTag){ //generuje nowy tag wiekszy o 1 od poprzedniego
        int intTag = Integer.parseInt(lastTag)+1;
        String newTag = String.valueOf(intTag);
        while(newTag.length()<4) newTag = "0"+newTag;
        return newTag;
    }

}
