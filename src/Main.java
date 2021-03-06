public class Main {
    public static void main(String arguments[]){
        System.out.println("ASD: Lab 2");
        BinaryTree tree = new BinaryTree();
        addToTree(tree, 4, "four");
        addToTree(tree, 2, "two");
        addToTree(tree, 3, "three");
        addToTree(tree, 11, "eleven");
        addToTree(tree, 7, "seven");
        addToTree(tree, 5, "five");
        addToTree(tree, 9, "nine");
        addToTree(tree, 15, "fifteen");
        addToTree(tree, 13, "thirteen");
        addToTree(tree, 12, "twelve");
        addToTree(tree, 17, "seventeen");

        Node n = tree.search(11);
        if(n == null){
            System.out.println("Node is not found");
        }
        else{
            System.out.println("Node " + n.value + " is found. Attribute: " + n.attribute);
        }

     //   tree.remove(15);
        tree.remove(11);

    }

    public static void addToTree(BinaryTree tree, int value, String attr){
        if(tree.add(value, attr)){
            System.out.println("Value " + value + " added to tree");
        }
        else{
            System.out.println("Value" + value + "NOT added (already exists)");
        }
    }
}
