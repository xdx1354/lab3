import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class main {

    public static void main(String []Args) throws IOException {
        String filename = Args[0];
        File fileOpen = new File("D:\\PWR\\3 sem\\Jezyki_programowania\\lab3\\src\\main\\java\\inventory.txt");
        List<String> lines = new ArrayList<String>();                             //lista do której dodaję kolejne linie z pliku
        List<Product> itemsList = new ArrayList<>();                         //lista obiektów Product zbierająca
        List<Product> clientCart = new ArrayList<>();
        String lastTag="";

        try{
            Scanner sc = new Scanner(fileOpen);
            while(sc.hasNextLine()){
                lines.add(sc.nextLine());          //sczytywanie lini
            }
            sc.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        lastTag = Utils.createItemsList(lines,itemsList,lastTag);
        System.out.println(itemsList);
        String newTag = Utils.generateNewTag(lastTag);
        int n=0;
        System.out.println("WYBIERZ TRYB DZIALANIA: \n 1. <|> SPRZEDAWCA <|> \n 2. <|> KLIENT <|>");
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        if(x==1){
            while (n!=4) {
                Scanner switchScanner = new Scanner(System.in);
                System.out.println("Wybierz co chcesz robić: \n1. Wydrukuj dostepne produkty \n 2. Dodaj nowy produkt (podajac jego nazwe i cene) \n 3. Usun produkt (podajac jego tag) \n 4. Wyjscie z programu");
                n = switchScanner.nextInt();
                //switchScanner.close();
                switch (n) {
                    case 1: {
                        System.out.println("Lista dostępnych produktów");                   ///plik jest nadpisywany od raz po dodaniu nowego produktu
                        //Utils.generateNewFile(itemsList);
                        System.out.println(itemsList);
                        break;
                    }
                    case 2: {
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Podaj nazwe produktu: ");
                        String name = sc.nextLine();
                        System.out.println("Podaj cene: ");
                        float price =  sc.nextFloat();                                      ///trzeba podać z przecinkiem
                        //float price = 2.0F;
                        Utils.addItem(Utils.generateNewTag(lastTag), name, price, itemsList);
                        //Utils.generateNewFile(itemsList);
                        //sc.close();
                        break;
                    }
                    case 3: {
                        System.out.println("Podaj kod produktu do usuniecia");
                        System.out.println(itemsList);
                        Scanner sc = new Scanner(System.in);
                        String tagToRemove = sc.nextLine();
                        Utils.removeItem(itemsList,tagToRemove);
                        Utils.generateNewFile(itemsList);
                        break;
                    }
                }
            }
            Utils.generateNewFile(itemsList);
        }
        else{
            while (n!=6) {
                Scanner switchScanner = new Scanner(System.in);
                System.out.println("Wybierz co chcesz robić: \n1. Wydrukuj dostepne produkty \n 2. Wyszukaj produkt (po nazwie) \n 3. Dodaj produkt do koszyka \n 4. Podlicz wartosc koszyka \n 5. Finalizuj zamowienie \n 6. Wyjscie z programu");
                n = switchScanner.nextInt();
                //switchScanner.close();
                switch (n) {
                    case 1: {
                        System.out.println("Lista dostępnych produktów");                   ///plik jest nadpisywany od raz po dodaniu nowego produktu
                        //Utils.generateNewFile(itemsList);
                        System.out.println(itemsList);
                        break;
                    }
                    case 2: {
                        System.out.print("Podaj nazwe produktu do wyszukania: ");           // wyszukiwanie produktu po nazwie
                        Scanner sc = new Scanner(System.in);
                        String userName = sc.nextLine();
                        Utils.searchForItem(itemsList,userName);
                        //sc.close();
                        break;
                    }
                    case 3: {
                        System.out.print("Podaj Tag produktu, ktory ma zostac dodany do koszyka: ");
                        Scanner sc = new Scanner(System.in);
                        String addingItemToCartTag = sc.nextLine();
                        if(!Pattern.matches("[0-9]{4}",addingItemToCartTag)){
                            System.out.println("Podany tag jest niepoprawny\n\n");
                        }
                        else{
                            Utils.addToCart(itemsList,addingItemToCartTag,clientCart);
                        }
                    }
                    case 4: {
                        System.out.print("Suma: "+Utils.summariseCart(clientCart));
                        break;
                    }
                    case 5:{
                        Utils.finnaliseOrder(clientCart,itemsList);
                        break;
                    }
                }
            }
            Utils.generateNewFile(itemsList);
        }
    }
}