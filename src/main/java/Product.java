public class Product {
    String tag;
    String name;
    float price;

    Product(String tag, String name, float price){
        this.name = name;
        this.price = price;
        this.tag = tag;
    }
    @Override
    public String toString(){
        return "\n" + "Tag: " + tag + ", Name: " + name + ", price:" + price;
    }
    public String toString2(){
        return tag + ", " + name + ", " + price;
    }
}