import java.text.NumberFormat;
import java.util.Locale;

public class Product {
    //khai báo các thuộc tính sản phẩm
    private int id;
    private String name;
    private double price;
    private int inventory;

    Locale localeEN = new Locale("en", "EN");
    NumberFormat en = NumberFormat.getInstance(localeEN);

    //------------------begin getter and setter----------------------
    //trả về tên sản phẩm của đối tượng
    public String getName() {
        return name;
    }

    //gán giá trị cho thuộc tính tên sản phẩm của đối tượng
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //-----------------------end getter and setter--------------------

    // Phương thức hiển thị danh sách sản phẩm
    public void inProduct() {
        System.out.printf("%10d %20s %20s  %20d \n", id, name, en.format(price), inventory);
    }


    public void subtractInventory(int quantity){
        this.inventory -= quantity;
    }

}
