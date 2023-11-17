import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class BST {
    public BSTNode root;
    public int count = 0;
    public BST() {
        root = null;
    }
    protected void visit(BSTNode p) {
        System.out.printf("%-25s %-10s %-10d %-5d \n", p.key, p.version, p.quantity, p.price);
    }

    public void sellStock(String softwareName, String version, int sold) {
        BSTNode p = root;
        NameComparator sort = new NameComparator();
        System.out.println("B4");
        while (p != null){
            if (sort.stringCompare(softwareName, p.key) == 0 && sort.stringCompare(version, p.version) == 0){
                if (p.quantity >= sold){
                    p.quantity = p.quantity - sold;
                    return; 
                } else {
                    System.err.println("Invalid Quantity");
                    return;
                }
            } else if (sort.stringCompare(softwareName, p.key) < 0)
                p = p.left;
            else p = p.right;
        }
        return;
    } // Figure 6.9

    public void addStock(String softwareName, String version, int addedQuantity, int price) {
        BSTNode p = root;
        NameComparator sort = new NameComparator();
        while (p != null){
            if (sort.stringCompare(softwareName, p.key) == 0 && sort.stringCompare(version, p.version) == 0){
                p.quantity = p.quantity + addedQuantity;
                return;
            } else if (sort.stringCompare(softwareName, p.key) < 0)
                p = p.left;
            else p = p.right;
        }
        addProduct(softwareName, version, addedQuantity, price);
        return;
    } // Figure 6.9

    public void addProduct(String softwareName, String version, int quantity, int price) {
        BSTNode p = root.right, prev = null;
        while (p != null) {
            prev = p;
            if (p.right != null)
                p = p.right;
            else
                break;
        }
        if (root == null) // tree is empty;
            root = new BSTNode(softwareName, version, quantity, price);
        else if (prev.right == null)
            prev.right = new BSTNode(softwareName, version, quantity, price);
    }

    public void breadthFirst() {
        BSTNode p = root;
        Queue<BSTNode> queue = new LinkedList<>();
        if (p != null) {
            queue.add(p);
            while (!queue.isEmpty()) {
                p = (BSTNode) queue.remove();
                visit(p);
                if (p.left != null)
                    queue.add(p.left);
                if (p.right != null)
                    queue.add(p.right);
            }
        }
    } 

    public void save() throws IOException {
        BSTNode p = root;
        Queue<BSTNode> queue = new LinkedList<>();
        
        FileWriter fw = new FileWriter("software.txt");
        if (p != null) {
            queue.add(p);
            while (!queue.isEmpty()) {
                p = (BSTNode) queue.remove();
                if(p.quantity > 0){
                    fw.write(p.key + '\n');
                    fw.write(p.version + '\n');
                    fw.write(String.valueOf(p.quantity) + '\n');
                    fw.write(String.valueOf(p.price));
                }
                if (p.left != null)
                    queue.add(p.left);
                if (p.right != null)
                    queue.add(p.right);
                if (!queue.isEmpty() && p.quantity > 0) {
                    fw.write('\n');
                }
            }
        }
        fw.close();
    } 


    public void insert(String softwareName, String version, int quantity, int price) {
        BSTNode p = root, prev = null;
        NameComparator sort = new NameComparator();
        while (p != null) { // find a place for inserting new node;
            prev = p;
            if (sort.stringCompare(p.key, softwareName) < 0)
                p = p.right;
            else p = p.left;
        }
        if (root == null) // tree is empty;
            root = new BSTNode(softwareName, version, quantity, price);
        else if (sort.stringCompare(prev.key, softwareName) < 0)
            prev.right = new BSTNode(softwareName, version, quantity, price);
        else prev.left = new BSTNode(softwareName, version, quantity, price);
    }
}