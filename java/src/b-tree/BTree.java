import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class BTree{

    private BNode root;
    private int order;
    private int size;

    public BTree(int order) {
        this.root = null;
        this.order = order;
    }

    public BTree() {
        this.root = null;
        this.order = 4;
    }

    /**
     * Insere um valor na árvore de forma recursiva.
     *
     * @param value valor a ser inserido
     */
    public void insert(int value) {
        if(isEmpty()){
            root = new BNode(this.order);
            root.addKey(value);
            size++;
        } else {
            if(root.isFull()){
                split(root);
            }
            insert(root, value);
        }
    }

    /**
     * Inserção recursiva, percorrendo os nós da árvore até encontrar uma folha.
     * A decisão para qual filho seguir é feita por busca binária.
     *
     * @param node nó atual da recursão
     * @param value valor a ser inserido
     */
    private void insert(BNode node, int value) {
        if(node.isLeaf()){
            node.addKey(value);
            size++;
        } else {
            int idx = linearSearch(node, value);
            BNode child = node.children.get(idx);
            if(child.isFull()) {
                split(child);
                if(value > node.keys.get(idx)) {
                    idx++;
                }
            }
            insert(node.children.get(idx), value);
        }
    }

    /**
     * Verifica se a árvore está vazia.
     *
     * @return true se a árvore não possuir raiz; false caso contrário
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Retorna a altura da árvore B.
     *
     * @return número de níveis da árvore
     */
    public int height(){
        if(isEmpty()) return 0;
        return height(root);
    }

    /**
     * Calcula a altura da subárvore a partir de um nó.
     *
     * @param node raiz da subárvore
     * @return altura da subárvore
     */
    private int height(BNode node){
        if(node.isLeaf()) return 1;
        return 1 + height(node.children.get(0));
    }

    /**
     * Realiza uma busca recursiva por um valor.
     *
     * @param value valor procurado
     * @return posição do nó e da chave, ou uma posição vazia caso não seja encontrado
     */
    public BNodePosition search(int value) {
        if(isEmpty()) {
            return new BNodePosition();
        }
        return search(root, value);
    }

    /**
     * Busca recursiva percorrendo a árvore até encontrar a chave ou uma folha.
     * A decisão para qual filho seguir é feita por busca binária.
     *
     * @param node nó atual da recursão
     * @param value valor procurado
     * @return posição do nó e da chave, ou uma posição vazia caso não seja encontrado
     */
    private BNodePosition search(BNode node, int value) {
        int idx = linearSearch(node, value);

        if(idx < node.size && value == node.keys.get(idx)) {
            return new BNodePosition(node, idx);
        }
        if(!node.isLeaf()) {
            return search(node.children.get(idx), value);
        }

        return new BNodePosition();
    }

    /**
     * Retorna a posição da maior chave da árvore de forma recursiva.
     *
     * @return posição da maior chave ou uma posição vazia se a árvore estiver vazia
     */
    public BNodePosition max() {
        if(isEmpty()) {
            return new BNodePosition();
        }

        return max(root);
    }

    /**
     * Auxilia a busca do maior valor de forma recursiva.
     *
     * @param node nó atual da recursão
     * @return posição da maior chave da subárvore
     */
    private BNodePosition max(BNode node) {
         if(node.isLeaf()) {
            return new BNodePosition(node, node.size-1);
        }

        return max(node.children.get(node.children.size()-1));
    }

    /**
     * Retorna a posição da menor chave da árvore de forma iterativa.
     *
     * @return posição da menor chave ou uma posição vazia se a árvore estiver vazia
     */
    public BNodePosition min() {
        if(isEmpty()) {
            return new BNodePosition();
        }

        BNode node = root;
        while(!node.isLeaf()) {
            node = node.children.get(0);
        }
        return new BNodePosition(node, 0);
    }

    /**
     * Remove um valor da árvore, caso ele exista.
     * Se a chave estiver em um nó interno, é substituída pelo predecessor
     * (maior valor da subárvore esquerda) antes da remoção efetiva na folha.
     *
     * @param value valor a ser removido
     */
    public void remove(int value){
        if(isEmpty()) return;

        BNodePosition pos = search(value); //posicao do valor a ser removido
        if(pos.node == null) return; //valor nao existe

        BNode node = pos.node;  //nó do valor a ser removido
        int index = pos.position; //index do valor a ser removido

        if(!node.isLeaf()){
            //substitui pelo predecessor (maior valor da subarvore esquerda)
            BNodePosition pred = max(node.children.get(index));
            node.keys.set(index, pred.getValue());
            node = pred.node;
            index = pred.position;
        }

        //remove a chave da folha
        node.keys.remove(index);
        node.size--;
        size--;

        fixUnderflow(node);
    }

    /**
     * Retorna o número mínimo de chaves permitido em um nó, de acordo com a ordem da árvore.
     *
     * @return quantidade mínima de chaves por nó
     */
    private int minKeys(){
        return (this.order - 1) / 2;
    }

    /**
     * Verifica se um nó ficou abaixo do mínimo de chaves após uma remoção,
     * corrigindo via redistribuição ou concatenação com um irmão.
     *
     * @param node nó a ser verificado
     */
    private void fixUnderflow(BNode node){
        if(node == root){
            if(node.size == 0){
                if(node.isLeaf()){
                    root = null; //arvore ficou vazia
                } else {
                    //filho unico vira a nova raiz
                    root = node.children.get(0);
                    root.parent = null;
                }
            }
            return;
        }

        if(node.size >= minKeys()) return; //sem underflow
        BNode parent = node.parent;
        int index = parent.children.indexOf(node);

        //pega o irmao esquerdo, se o node nao for o primeiro filho
        BNode leftSibling = null;
        if(index > 0){
            leftSibling = parent.children.get(index - 1);
        }

        //pega o irmao direito, se o node nao for o ultimo filho
        BNode rightSibling = null;
        if(index < parent.children.size() - 1){
            rightSibling = parent.children.get(index + 1);
        }

        if(leftSibling != null && leftSibling.size > minKeys()){
            redistributeLeft(node, leftSibling, parent, index);
        } else if(rightSibling != null && rightSibling.size > minKeys()){
            redistributeRight(node, rightSibling, parent, index);
        } else if(leftSibling != null){
            concatenate(leftSibling, node, parent, index - 1);
        } else {
            concatenate(node, rightSibling, parent, index);
        }
    }

    /**
     * Redistribui uma chave do irmão esquerdo para o nó deficiente, passando pelo pai.
     *
     * @param node nó com underflow
     * @param leftSibling irmão esquerdo com chaves sobrando
     * @param parent pai dos dois nós
     * @param index índice do node na lista de filhos do pai
     */
    private void redistributeLeft(BNode node, BNode leftSibling, BNode parent, int index){
        //chave do pai desce para o inicio do node
        node.keys.add(0, parent.keys.get(index - 1));
        node.size++;

        //maior chave do irmao esquerdo sobe para o pai
        parent.keys.set(index - 1, leftSibling.keys.remove(leftSibling.size - 1));
        leftSibling.size--;

        //move o ultimo filho do irmao, se houver
        if(!leftSibling.isLeaf()){
            BNode child = leftSibling.children.remove(leftSibling.children.size() - 1);
            child.parent = node;
            node.children.add(0, child);
        }
    }

    /**
     * Redistribui uma chave do irmão direito para o nó deficiente, passando pelo pai.
     *
     * @param node nó com underflow
     * @param rightSibling irmão direito com chaves sobrando
     * @param parent pai dos dois nós
     * @param index índice do node na lista de filhos do pai
     */
    private void redistributeRight(BNode node, BNode rightSibling, BNode parent, int index){
        //chave do pai desce para o final do node
        node.keys.add(parent.keys.get(index));
        node.size++;

        //menor chave do irmao direito sobe para o pai
        parent.keys.set(index, rightSibling.keys.remove(0));
        rightSibling.size--;

        //move o primeiro filho do irmao, se houver
        if(!rightSibling.isLeaf()){
            BNode child = rightSibling.children.remove(0);
            child.parent = node;
            node.children.add(child);
        }
    }

    /**
     * Concatena dois nós irmãos com a chave do pai que os separava, propagando
     * a checagem de underflow para o pai.
     *
     * @param left nó da esquerda (recebe as chaves e filhos do da direita)
     * @param right nó da direita (é descartado apos a concatenacao)
     * @param parent pai dos dois nós
     * @param parentKeyIndex índice da chave do pai que separa os dois nós
     */
    private void concatenate(BNode left, BNode right, BNode parent, int parentKeyIndex){
        //chave do pai desce para o meio da concatenacao
        left.keys.add(parent.keys.remove(parentKeyIndex));
        left.size++;
        parent.size--;

        //chaves e filhos do node direito migram para o esquerdo
        left.keys.addAll(right.keys);
        left.size += right.size;

        if(!right.isLeaf()){
            for(BNode child : right.children){
                child.parent = left;
            }
            left.children.addAll(right.children);
        }

        //remove o filho direito do pai e propaga underflow, se houver
        parent.children.remove(right);
        fixUnderflow(parent);
    }

    /**
     * Retorna os nós da árvore em ordem de profundidade, em pré ordem.
     *
     * @return lista com os nós visitados em profundidade
     */
    public ArrayList<BNode> depthFS(){
        ArrayList<BNode> nodes = new ArrayList<>();
        depthFS(root, nodes);
        return nodes;
    }

    /**
     * Percorre a árvore em profundidade adicionando os nós visitados em uma lista.
     *
     * @param node nó atual da travessia
     * @param nodes lista que armazena os nós visitados
     */
    private void depthFS(BNode node, ArrayList<BNode> nodes){
        if(node == null) return;

        nodes.add(node);

        for(BNode child : node.children){
            depthFS(child, nodes);
        }
    }

    /**
     * Retorna os nós da árvore em ordem de largura.
     *
     * @return lista com os nós visitados em largura
     */
    public ArrayList<BNode> breadthFS(){
        ArrayList<BNode> result = new ArrayList<>();

        if(isEmpty()) return result;

        Queue<BNode> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()){
            BNode current = queue.poll();
            result.add(current);

            if(!current.isLeaf()){
                for(BNode child : current.children){
                    queue.add(child);
                }
            }
        }
        return result;
    }

    /**
     * Retorna o número de elementos armazenados na árvore.
     *
     * @return quantidade de chaves presentes na árvore
     */
    public int size(){
        return this.size;
    }

    /**
     * Divide um nó cheio em dois nós filhos e promove a chave do meio para o pai.
     *
     * @param node nó cheio a ser dividido
     */
    private void split(BNode node){
        //Nó da esquerda
        BNode left = new BNode(this.order);
        //Primeira metade da keys no nó da esquerda
        for(int i = 0; i < (this.order - 1) / 2; i++){
            left.addKey(node.keys.get(i));
        }
        //Primeira metade dos filhos no nó da esquerda
        if(!node.isLeaf()){
            for(int i = 0; i < this.order/2; i++){
                left.children.add(node.children.get(i));
                node.children.get(i).parent = left;
            }
        }

        //Nó da direita
        BNode right = new BNode(this.order);
        //Segunda metade da keys no nó da direita
        for(int i = (this.order - 1) / 2 + 1; i < node.size; i++){
            right.addKey(node.keys.get(i));
        }
        //Segunda metade dos filhos no nó da direita
        if(!node.isLeaf()){
            for(int i = this.order/2; i < node.children.size(); i++){
                right.children.add(node.children.get(i));
                node.children.get(i).parent = right;
            }
        }

        //Se for raiz
        if(node.parent == null){
            node.parent = new BNode(this.order);
            root = node.parent;
        }

        BNode parent = node.parent;

        //Atribui novos filhos
        left.parent = parent;
        right.parent = parent;

        int index = parent.addKey(node.keys.get((this.order - 1) / 2));
    
        parent.children.remove(node);
        parent.children.add(index, left);
        parent.children.add(index + 1, right);
    }

    /**
     * Retorna a raiz da árvore B.
     *
     * @return nó raiz da árvore
     */
    public BNode getRoot(){
        return root;
    }

    /**
     * Realiza uma busca binária entre as chaves de um nó.
     *
     * @param node nó onde será realizada a busca
     * @param value valor procurado
     * @return índice da posição do filho no qual seguir a busca
     */
    private int binarySearch(BNode node, int value) {
        int init = 0, end = node.size-1, idx = node.size;
        while(init <= end) {
            int mid = (init + end) / 2;
            if(node.keys.get(mid) < value) {
                init = mid+1;
            } else {
                idx = mid;
                end = mid-1;
            }
        }
        return idx;
    }

    /**
     * Realiza uma busca linear entre as chaves de um nó.
     *
     * @param node nó onde será realizada a busca
     * @param value valor procurado
     * @return índice da posição do filho no qual seguir a busca
     */
    private int linearSearch(BNode node, int value) {
        int idx = 0;
        while(idx < node.size && value > node.keys.get(idx)) {
            idx++;
        }
        return idx;
    }

}

/**
 * Representa um nó de uma árvore B.
 */
class BNode{
    
    BNode parent;
    ArrayList<Integer> keys;
    ArrayList<BNode> children;
    int size;
    int order;

    /**
     * Cria um nó da árvore B com uma ordem específica.
     *
     * @param order ordem do nó
     */
    public BNode(int order){
        this.keys = new ArrayList<>();
        this.children = new ArrayList<>();
        this.size = 0;
        this.order = order;
    }

    /**
     * Adiciona uma chave ao nó, mantendo a ordem crescente.
     *
     * @param key chave a ser adicionada
     * @return índice em que a chave foi inserida
     */
    public int addKey(int key){
        int i = 0;
        while(i < size && key > keys.get(i)) {
            i++;
        }
        
        keys.add(i, key);
        size++;

        return i;
    }

    /**
     * Verifica se o nó é uma folha.
     *
     * @return true se o nó não possuir filhos; false caso contrário
     */
    public boolean isLeaf(){
        return children.isEmpty();
    }

    /**
     * Verifica se o nó está cheio.
     *
     * @return true se o nó atingir o limite de chaves permitidas
     */
    public boolean isFull(){
        return size == order-1;
    }
}

/**
 * Representa uma posição de uma chave dentro de um nó da árvore B.
 */
class BNodePosition{
    
    BNode node;
    int position;

    /**
     * Cria uma posição vazia, indicando que nenhum nó foi encontrado.
     */
    public BNodePosition(){
        this.node = null;
        this.position = -1;
    }

    /**
     * Cria uma posição apontando para um nó e uma chave específica.
     *
     * @param node nó onde a chave está localizada
     * @param position índice da chave dentro do nó
     */
    public BNodePosition(BNode node, int position){
        this.node = node;
        this.position = position;
    }

    /**
     * Retorna o valor armazenado na posição atual.
     *
     * @return valor da chave ou null se a posição for inválida
     */
    public Integer getValue() {
        if(node == null || position == -1) return null;
        return node.keys.get(position);
    }
}
