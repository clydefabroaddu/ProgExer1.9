import java.util.Comparator;

public class NameComparator implements Comparator<BSTNode> {
    public int compare(BSTNode a, BSTNode b){
        return a.getKey().trim().compareToIgnoreCase(b.getKey().trim());
    }
    public int stringCompare(String keyA, String keyB){
        return keyA.trim().compareToIgnoreCase(keyB.trim());
    }
}
