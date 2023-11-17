public class BSTNode {
    protected String key;
    protected String version;
    protected int quantity;
    protected int price;
    protected BSTNode left, right;
    public BSTNode() {
        left = right = null;
    }
    public BSTNode(String softwareName, String version, int quantity, int price) {
        this(softwareName,null,null);
        this.version = version;
        this.quantity = quantity;
        this.price = price;
    }
    public BSTNode(String softwareName, BSTNode lt, BSTNode rt) {
        key = softwareName; left = lt; right = rt;
    }
    public String getKey(){
        return key;
    }
    
}