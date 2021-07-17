import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static int id = 0;
    private static int idOrder = 0;
    static Scanner sc = new Scanner(System.in);

    private final static List<Product> PRODUCTS = new ArrayList<>();

    static void addProduct() {
        Product prd = new Product();
        prd.setId(getNextId());
        System.out.println("Nhập Tên sản phẩm: ");
        sc.nextLine();
        prd.setName(sc.nextLine());
        System.out.println("Nhập Giá sản phẩm: ");
        prd.setPrice(sc.nextFloat());
        System.out.println("Nhập Số lượng sản phẩm: ");
        prd.setInventory(sc.nextInt());
        PRODUCTS.add(prd);
    }

    static void selectProduct(List<LineItem> lineItems, int orderId) {
        List<Integer> existsProductIds = new ArrayList<>();
        for (Product item : PRODUCTS) {
            existsProductIds.add(item.getId());
        }
        // Nếu nhập id sản phẩm không tồn tại
        System.out.println("Nhập id sản phẩm muốn mua:");
        int idProdSelected = sc.nextInt();
        // Kiểm tra xem idProd có tồn tại trong bảng Product hay không
        if (!existsProductIds.contains(idProdSelected)) {
            System.out.println("Sản phầm bạn chọn không tồn tại trong danh sách sản phẩm của chúng tôi");
            selectProduct(lineItems, orderId);
        } else {
            for (Product product : PRODUCTS) {
                if (idProdSelected == product.getId()) {
                    int quantity = inputQuantity(product);
                    LineItem lineItem = new LineItem();
                    lineItem.setId(product.getId());
                    lineItem.setName(product.getName());
                    lineItem.setQuality(quantity);
                    lineItem.setOrderId(orderId);
                    lineItem.setPrice(product.getPrice());
                    lineItems.add(lineItem);
                    break;
                }
            }
            System.out.println("Bạn muốn mua mặt hàng khác nữa hay không: 1. Có, 0. Không");
            int action = sc.nextInt();
            if (action == 1) {
                selectProduct(lineItems, orderId);
            }
        }
    }

    public static int inputQuantity(Product product) {
        System.out.println("Nhập số lượng sản phẩm: ");
        int quantity = sc.nextInt();
        if (quantity <= product.getInventory()) {
            product.subtractInventory(quantity);
            return quantity;
        } else {
            System.out.println("Sản phầm không đủ để cung cấp");
            return inputQuantity(product);
        }
    }

    public static void main(String[] args) {
        int n = 0; // số sản phẩm cần nhập
        System.out.println("\n=====================DINHLT TEST JAVA=======================\n");
        // Thêm sản phẩm
        System.out.println("Nhập số sản phẩm muốn thêm:");
        n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Sản phẩm thứ " + (i + 1) + ": ");
            addProduct();
        }

        //A. hiển thị danh sách sản phầm
        System.out.println("\n================A. DANH SÁCH SẢN PHẨM================\n");
        System.out.printf("%10s %20s %20s %20s \n", "id", "Tên sản phẩm", "Giá ", "Số lượng");
        for (Product prod1 : PRODUCTS) {
            prod1.inProduct();
        }

        // B. Tạo đơn hàng
        double totalAmount = 0;
        System.out.println("\n================B. ĐẶT HÀNG================\n");
        List<LineItem> lineItems = new ArrayList<>();
        Order order = new Order();
        order.setId(getNextIdOrder());
        selectProduct(lineItems, order.getId());
        order.setLineItems(lineItems);
        // Lưu sản phẩm đã chọn vào Order
        order.setLineItems(lineItems);
        if (!lineItems.isEmpty()) {
            System.out.println("\n================DANH SÁCH SẢN PHẨM SAU KHI ĐẶT HÀNG================\n");
            System.out.printf("%10s %20s %20s %20s \n", "id", "Tên sản phẩm", "Giá ", "Số lượng");
            for (Product productInventory : PRODUCTS) {
                productInventory.inProduct();
            }
            System.out.println("\n================SẢN PHẨM TRONG ĐƠN HÀNG================\n");
            System.out.printf("%10s %10s %20s %20s %20s \n", "ID đơn hàng", "id Sản phẩm", "Tên sản phẩm", "Giá ", "Số lượng");
            for (LineItem lineItemInOrrder : lineItems) {
                lineItemInOrrder.inLineItem();
                totalAmount += (lineItemInOrrder.getQuality() * lineItemInOrrder.getPrice());
            }
            System.out.println("Đơn hàng: " + order);
            Locale localeEN = new Locale("en", "EN");
            NumberFormat en = NumberFormat.getInstance(localeEN);
            System.out.println("GIÁ TRỊ ĐƠN HÀNG = " + en.format(totalAmount) + " VNĐ");

        }


        //C. In ra sản phẩm đã hết hàng
        List<Product> listOutOfStock = new ArrayList<>();
        // Thêm sản phẩm có số lượng = 0 vào list outOfStock
        for (Product outProd : PRODUCTS) {
            if (outProd.getInventory() == 0) {
                listOutOfStock.add(outProd);
            }
        }
        // In ra sản phẩm hết hàng
        if (listOutOfStock.isEmpty()) {
            System.out.println("\n================C. DANH SÁCH SẢN PHẨM ĐÃ HẾT HÀNG================\n");
        } else {
            System.out.println("\n================C. DANH SÁCH SẢN PHẨM ĐÃ HẾT HÀNG================\n");
            System.out.printf("%10s %20s %20s %20s \n", "id", "Tên sản phẩm", "Giá ", "Số lượng");
            for (Product outOfStock : listOutOfStock) {
                outOfStock.inProduct();
            }
        }

    }

    // Đánh id tự động
    public static int getNextId() {
        return ++id;
    }

    public static int getNextIdOrder() {
        return ++idOrder;
    }
}
