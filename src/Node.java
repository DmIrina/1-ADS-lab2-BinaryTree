public class Node implements Comparable<Node>{

    public int value;
    public String attribute;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, String attribute) {
        this.value = value;
        this.attribute = attribute;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public int compareTo(Node node) {
        if (value < node.value){
            return -1;
        }
        if (value > node.value){
            return 1;
        }
        return 0;
    }
}

