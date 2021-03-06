public class BinaryTree{
    private Node head; // root element (node)
    private int count; // number of elements in tree

    // adding
    public boolean add(int value, String attribute){
        Node added = new Node(value, attribute);
        boolean result = false;
        if (head == null){  // empty tree
            head = added;
            result = true;
        }
        else{               // tree is not empty, search place for node
            result = addTo(head, added);
            }
        count++;
        return result;
        }

    // recursive adding
    private boolean addTo(Node current, Node added){
        boolean result = false;
        switch(added.compareTo(current)){
            case 0:
                // element exists, not added
                break;
            case -1:        // value of added node < current
                if (current.getLeft() == null){
                    current.setLeft(added); // no node left, creating "added"
                    result = true;
                }
                else{
                    result = addTo(current.getLeft(), added); // there is the node left, repeat recursive adding
                }
                break;
            case 1:         // value of added node > current
                if (current.getRight() == null){
                    current.setRight(added); // no node right, creating "added"
                    result = true;
                }
                else{
                    result = addTo(current.getRight(), added); // there is the node right, repeat recursive adding
                }
                break;
        }
        return result;
    }

    // search element
    public Node search(int value){
        Node result;
        Node searched = new Node(value);

        if (head == null){  // empty tree
            return null;
        }
        else{               // tree is not empty, search node
            result = searchTo(head, searched);
        }
        return result;
    }

    // recursive searching
    private Node searchTo(Node current, Node searched){
        Node result = null;
        switch(searched.compareTo(current)){
            case 0:
                // element is found
                result = current;
                break;
            case -1:        // value of searched node < current
                if (current.getLeft() != null){
                    result = searchTo(current.getLeft(), searched); // there is the node left, repeat recursive searching
                }
                break;
            case 1:         // value of searched node > current
                if (current.getRight() != null){
                    result = searchTo(current.getRight(), searched); // there is the node right, repeat recursive searching
                }
                break;
        }
        return result;
    }

    // NOT recursive searching
    private Node[] searchWithParent(Node searched) {
        Node current = head;
        Node parent = null;

        while (current != null){
            switch(searched.compareTo(current)){
                case 0:
                    // element is found, current != null
                    return new Node[]{current, parent};
                case -1:        // value of searched node < current
                    parent = current;
                    current = current.getLeft();
                    break;
                case 1:         // value of searched node > current
                    parent = current;
                    current = current.getRight();
                    break;
            }
        }
        return new Node[]{current, parent};
    }

    public boolean remove(int value) {
        Node removed = new Node(value);
        Node[] sp = searchWithParent(removed);      // sp[0] - searched, sp[1] - parent

        removed = sp[0];
        Node parent = sp[1];

        if (removed == null) {                      // node is not found
            return false;
        }


        // Случай 1: Если нет детей справа, левый ребенок встает на место удаляемого.
        if(removed.getRight() == null){             // NO right branch
            if(parent == null){                     // removed node is root
                head = removed.getLeft();
            }
            else{                                   // No right branch, not root
                int result = removed.compareTo(parent);

                if (result == -1) {                 // removed < parent
                    parent.setLeft(removed.getLeft()); // левый ребенок removed становится левым ребенком parent.
                }
                else if (result == 1) {             // removed > parent (removed - right child)
                    parent.setRight(removed.getLeft());
                }
            }
            count--;
            return true;
        }


        // Случай 2:
        // 1) у removed есть правый ребенок removed.rChild, а у rChild нет детей слева, то
        // 2) левого ребенка removed.lChild перемещаем на место левого ребенка removed.rChild
        // 3) removed.rChild занимает место removed
        // 4) removed был слева от parent => все его наследники будут слева от parent


        if (removed.getRight().getLeft() == null) {              // 1)
            removed.getRight().setLeft(removed.getLeft());      // 2)

            int result = removed.compareTo(parent);
            if (result == -1) {                 // removed < parent
                parent.setLeft(removed.getRight()); // 4) removed.rChild становится левым ребенком parent.
            } else if (result == 1) {             // removed > parent
                parent.setRight(removed.getRight()); //removed.rChild становится правым ребенком parent
            }
            count--;
            return true;
        }

        // Случай 3: Если у removed.rChild есть дети слева
        // крайний левый ребенок removed.rChild (leftmost) заменяет removed

        Node leftmost = removed.getRight().getLeft();
        Node leftmostParent = removed.getRight();

        while (leftmost.getLeft() != null) {        // поиск leftmost по левой ветке removed.rChild
            leftmostParent = leftmost;
            leftmost = leftmost.getLeft();
        }

        // правая ветка крайнего левого узла leftmost становится левой веткой его родителя
        leftmostParent.setLeft(leftmost.getRight());

        // leftmost перемещаем на место removed
        leftmost.setLeft(removed.getLeft());
        leftmost.setRight(removed.getRight());

        if (parent == null) {
            head = leftmost;
        } else {
            int result = removed.compareTo(parent);
            if (result < 0) {
                parent.setLeft(leftmost); // removed < parent
                // крайний левый узел становится левым ребенком родителя.
            }
            else if (result > 0)
            {
                parent.setRight(leftmost);      // removed > parent
                // крайний левый узел становится правым ребенком родителя.
            }
        }
        count--;
        return true;
    }
}
