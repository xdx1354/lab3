public class Product {
    String tag;
    String name;
    Float price;

    Product(String tag, String name, Float price){
        this.name = name;
        this.price = price;
        this.tag = tag;
    }
    @Override
    public String toString(){
        return "\n" + "Tag: " + tag + ", Name: " + name + ", price:" + price;
    }
}




