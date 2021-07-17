import java.text.NumberFormat;
import java.util.Locale;

public class LineItem {
    //khai báo các thuộc tính sản phẩm
    private int id;
    private int orderId;
    private String name;
    private double price;
    private int quality;
    Locale localeEN = new Locale("en", "EN");
    NumberFormat en = NumberFormat.getInstance(localeEN);

    //------------------begin getter and setter----------------------
    //Trả về tên sản phẩm của đối tượng
    public String getName() {
        return name;
    }

    //Gán giá trị cho thuộc tính tên sản phẩm của đối tượng
    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    //-----------------------end getter and setter--------------------

    // Phương thức hiển thị danh sách sản phẩm trong Order

    public void inLineItem() {
        System.out.printf("%10d %10d %20s %20s  %20d \n", orderId, id, name, en.format(price), quality);
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", name='" + name + '\'' +
                ", price=" + en.format(price) +
                ", quality=" + quality +
                '}';
    }
}
