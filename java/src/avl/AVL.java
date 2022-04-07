import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class AVL {
    private Node root;
    private int size;

    public AVL() {
        this.size = -1;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Implementação iterativa da adição de um elemento em uma árvore AVL.
     * 
     * @param element o valor a ser adicionado na árvore.
     */
    public void add(int element) {

        this.size += 1;
        if (isEmpty()) this.root = new Node(element);
        else {

            Node aux = this.root;

            while (aux != null) {

                if (element < aux.value) {
                    if (aux.left == null) {
                        Node newNode = new Node(element);
                        aux.left = newNode;
                        newNode.parent = aux;

                        Node unbalanced = checkBalance(newNode);
                        if (unbalanced != null) callBestRotation(unbalanced);

                        return;
                    }

                    aux = aux.left;
                } else {
                    if (aux.right == null) {
                        Node newNode = new Node(element);
                        aux.right = newNode;
                        newNode.parent = aux;

                        Node unbalanced = checkBalance(newNode);
                        if (unbalanced != null) callBestRotation(unbalanced);


                        return;
                    }

                    aux = aux.right;
                }
            }
        }

    }

    /**
     * Checa do node passado até a raíz da árvore se existe um desbalanceamento.
     * 
     * @param node node de onde se começa a checagem
     * @return o node desbalanceado ou null caso a árvore esteja balanceada
     */
    public Node checkBalance(Node node) {
        Node aux = node;

        while (aux != null) {
            if (!aux.isBalanced()) {
                return aux;
            } else if (aux.parent != null) {
                aux = aux.parent;
            } else {
                break;
            }
        }

        return null;
    }

    /**
     * Implementação recursiva do método de adição.
     * @param element elemento a ser adicionado.
     */
    public void recursiveAdd(int element) {
        
        if (isEmpty())
            this.root = new Node(element);
        else {
            Node aux = this.root;
            recursiveAdd(aux, element);
        }
        this.size += 1;
        
    }

    /**
     * Método para auxiliar na implementação recursiva do método de adição.
     * @param node a raíz da árvore.
     * @param element elemento a ser adicionado.
     */
    public void recursiveAdd(Node node, int element) {

        if (element < node.value) {
            if (node.left == null) {
                Node newNode = new Node(element);
                node.left = newNode;
                newNode.parent = node;

                rebalance(node);

                return;
            }
            recursiveAdd(node.left, element);
            rebalance(node);
        } else {
            if (node.right == null) {
                Node newNode = new Node(element);
                node.right = newNode;
                newNode.parent = node;

                rebalance(node);

                return;
            }
            recursiveAdd(node.right, element);
            rebalance(node);
        }
    }

    private void rebalance(Node node) {
        int balance = node.balance();

        if (Math.abs(balance) > 1) {
            callBestRotation(node);
        }
    }


    /**
     * Escolhe e executa o melhor caso de rotação a se fazer analisando
     * o nó do qual a rotação deve partir.
     * 
     * @param unbalanced nó do qual a rotação deve partir
     */
    public void callBestRotation(Node unbalanced) {
        Node x = unbalanced;

        if (x.isLeftPending()) {
            Node y = x.left;

            if (y.left != null) rotateRight(x);
            else {
                rotateLeft(y); rotateRight(x);
            }

        } else {
            Node y = x.right;

            if (y.right != null) rotateLeft(x);
            else {
                rotateRight(y); rotateLeft(x);
            }
        }
    }

    /**
     * Rotaciona o nó à direita.
     * 
     * @param node nó a partir de onde a rotação ocorre
     */
    public void rotateRight(Node node) {
        Node newRoot = node.left;
        newRoot.parent = node.parent;

        node.left = newRoot.right;
        newRoot.right = node;

        node.parent = newRoot;

        if (newRoot.parent != null) {
            if (newRoot.parent.left == node)
                newRoot.parent.left = newRoot;
            else
                newRoot.parent.right = newRoot;
        } else
            this.root = newRoot;
    }

    /**
     * Rotaciona o nó à esquerda.
     * 
     * @param node nó a partir de onde a rotação ocorre
     */
    public void rotateLeft(Node node) {
        Node newRoot = node.right;
        newRoot.parent = node.parent;

        node.right = newRoot.left;
        newRoot.left = node;

        node.parent = newRoot;

        if (newRoot.parent != null) {
            if (newRoot.parent.right == node)
                newRoot.parent.right = newRoot;
            else
                newRoot.parent.left = newRoot;
        } else
            this.root = newRoot;
    }

    /**
     * Percorre a árvore em largura.
     * 
     * @return Uma lista com a os elementos percorridos em largura.
     */
    public ArrayList<Integer> bfs() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Deque<Node> queue = new LinkedList<Node>();

        if (!isEmpty()) {
            queue.addLast(this.root);
            while (!queue.isEmpty()) {
                Node current = queue.removeFirst();

                list.add(current.value);

                if (current.left != null)
                    queue.addLast(current.left);
                if (current.right != null)
                    queue.addLast(current.right);
            }
        }
        return list;
    }

    /**
     * @return o tamanho da árvore.
     */
    public int size() {
        return this.size;
    }

}

class Node {

    int value;
    int height;
    Node left;
    Node right;
    Node parent;

    Node(int v) {
        this.height = 0;
        this.value = v;
    }

    public boolean hasOnlyLeftChild() {
        return (this.left != null && this.right == null);
    }

    public boolean hasOnlyRightChild() {
        return (this.left == null && this.right != null);
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

    public int height() {
        if (this.left == null && this.right == null)
            return 0;
        else if (this.left == null) {
            return 1 + this.right.height();
        } else if (this.right == null) {
            return 1 + this.left.height();
        } else {
            return 1 + max(this.left.height(), this.right.height());
        }
    }

    private int max(int height1, int height2) {
        if (height1 >= height2)
            return height1;
        return height2;
    }

    public int balance() {
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();
        return left - right;
    }

    public boolean isLeftPending() {
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();
        return left - right >= 1;
    }

    public boolean isRightPending() {
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();
        return left - right <= -1;
    }

    public boolean isBalanced() {
        int left = this.left == null ? -1 : this.left.height();
        int right = this.right == null ? -1 : this.right.height();
        return left - right >= -1 && left - right <= 1;
    }

}
