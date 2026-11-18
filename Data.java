package Product;

public class Data {
    private final String CustomerName;
    private final String ProductName;
    private final  double VatAmount;
private final int CommissionAmount;

public Data(String customerName,String productName,double vatAmount,int commissionAmount) {
    this.CustomerName = customerName;
    this.ProductName = productName;
    this.VatAmount = vatAmount;
    this.CommissionAmount = commissionAmount;
}

    public String getCustomerName() {
        return CustomerName;
    }

    public String getProductName() {
        return ProductName;
    }

    public double getVatAmount() {
        return VatAmount;
    }

    public int getCommissionAmount() {
        return CommissionAmount;
    }
}


