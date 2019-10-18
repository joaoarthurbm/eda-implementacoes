package bst;
public class BST {

	private Node root;
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	/**
	 * Implementação iterativa da adição de um elemento em uma árvore binária de pequisa.
	 * @param element o valor a ser adicionado na árvore.
	 */
	public void add(int element) {
		
		if (isEmpty())
			this.root = new Node(element);
		else {
			
			Node aux = this.root;
			
			while (aux != null) {
				
				if (element < aux.value) {
					if (aux.left == null) { 
						Node newNode = new Node(element);
						aux.left = newNode;
						newNode.parent = aux;
						return;
					}
					
					aux = aux.left;
				} else {
					if (aux.right == null) { 
						Node newNode = new Node(element);
						aux.right = newNode;
						newNode.parent = aux;
						return;
					}
					
					aux = aux.right;
				}
			}
		}
		
	}
	
	
	/**
	 * Retorna o nó que contém o valor máximo da árvore. Implementação recursiva.
	 * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver vazia.
	 */
	public Node min() {
		if (isEmpty()) return null;
		return min(this.root);
	}
	
	/**
	 * Retorna o nó que contém o valor máximo da árvore cuja raiz é passada como parâmetro. Implementação recursiva.
	 * @param a raiz da árvore.
	 * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver vazia.
	 */
	private Node min(Node node) {
		if (node.left == null) return node;
		else return min(node.left);
	}

	/**
	 * Retorna o nó que contém o valor máximo da árvore. Implementação iterativa.
	 * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver vazia.
	 */
	public Node max() {
		if (isEmpty()) return null;
		
		Node node = this.root;
		while(node.right != null)
			node = node.right;
		
		return node;
	}
	
	/**
	 * Retorna o nó que contém o valor máximo da árvore cuja raiz é passada como parâmetro. Implementação recursiva.
	 * @param raiz da árvore.
	 * @return o nó contendo o valor máximo da árvore ou null se a árvore estiver vazia.
	 */
	
	private Node max(Node node) {
		if (node.right == null) return node;
		else return max(node.right);
	}
	
	/**
	 * Retorna o nó cujo valor é predecessor do valor passado como parâmetro. 
	 * @param valor O nó para o qual deseja-se identificar o predecessor.
	 * @return O nó contendo o predecessor do valor passado como parâmetro. O método retorna null caso não haja 
	 * predecessor.
	 */
	public Node predecessor(Node node) {
		if (node == null) return null;
		
		if (node.left != null)
			return max(node.left);
		else {
			Node aux = node.parent;
			
			while (aux != null && aux.value > node.value)
				aux = aux.parent;
			
			return aux;
		}
	}
	
	/**
	 * Retorna o nó cujo valor é sucessor do valor passado como parâmetro. 
	 * @param valor O valor para o qual deseja-se identificar o sucessor.
	 * @return O nó contendo o sucessor do valor passado como parâmetro. O método retorna null
	 * caso não haja sucessor.
	 */
	public Node sucessor(Node node) {
		if (node == null) return null;
		
		if (node.right != null)
			return min(node.right);
		else {
			Node aux = node.parent;
			
			while (aux != null && aux.value < node.value)
				aux = aux.parent;
			
			return aux;
		}
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
		
	}

	/**
	 * Método para auxiliar na implementação recursiva do método de adição.
	 * @param node a raíz da árvore.
	 * @param element elemento a ser adicionado.
	 */
	private void recursiveAdd(Node node, int element) {
		
		if (element < node.value) {
			if (node.left == null) {
				node.left = new Node(element);
				return;
			}
			recursiveAdd(node.left, element);
		} else {
			if (node.right == null) {
				node.right = new Node(element);
				return;
			}
			recursiveAdd(node.right, element);
		}
		
	}
	
	/**
	 * Busca o nó cujo valor é igual ao passado como parâmetro. Essa é a implementação 
	 * iterativa clássica da busca binária em uma árvore binária de pesquisa.
	 * @param element O elemento a ser procurado.
	 * @return O nó contendo o elemento procurado. O método retorna null caso
	 * o elemento não esteja presente na árvore.
	 */
	public Node search(int element) {
		
		Node aux = this.root;
		
		while (aux != null) {	
			if (aux.value == element) return aux;
			if (element < aux.value) aux = aux.left;
			if (element > aux.value) aux = aux.right;
		}
		
		return null;

	}
	
	/**
	 * Busca o nó cujo valor é igual ao passado como parâmetro. Essa é a implementação 
	 * recursiva clássica da busca binária em uma árvore binária de pesquisa.
	 * @param element O elemento a ser procurado.
	 * @return O nó contendo o elemento procurado. O método retorna null caso
	 * o elemento não esteja presente na árvore.
	 */
	public Node recursiveSearch(int element) {
		return recursiveSearch(this.root, element);
	}
	
	/**
	 * Busca o nó cujo valor é igual ao passado como parâmetro na sub-árvore cuja raiz é node. Trata-se de um método auxiliar
	 * para a busca recursiva.
	 * @param node a raiz da árvore.
	 * @param element O elemento a ser procurado.
	 * @return O nó contendo o elemento procurado. O método retorna null caso
	 * o elemento não esteja presente na árvore.
	 */
	private Node recursiveSearch(Node node, int element) {
		if (node == null) return null;
		if (element == node.value) return node;
		if (element < node.value) return recursiveSearch(node.left, element);
		else return recursiveSearch(node.right, element);
	}

	/**
	 * Encaminhamento pré-ordem.
	 */
	public void preOrder() {
		preOrder(this.root);
	}

	/**
	 * Encaminhamento pré-ordem na árvore cuja raiz é node.
	 */
	private void preOrder(Node node) {
		if (node != null) {
			System.out.print(node.value + " ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}

	/**
	 * Encaminhamento em-ordem na árvore cuja raiz é node.
	 */
	public void inOrder() {
		inOrder(this.root);
	}

	/**
	 * Encaminhamento em-ordem na árvore cuja raiz é node.
	 */
	private void inOrder(Node node) {
		if (node != null) {
			inOrder(node.left);
			System.out.print(node.value + " ");
			inOrder(node.right);
		}
		
	}

	/**
	 * Encaminhamento pos-ordem na árvore.
	 */
	public void posOrder() {
		posOrder(this.root);
	}

	/**
	 * Encaminhamento pos-ordem na árvore cuja raiz é node.
	 */
	private void posOrder(Node node) {
		if (node != null) {
			posOrder(node.left);
			posOrder(node.right);
			System.out.print(node.value + " ");
		}
		
	}
	
}


class Node {
	
	int value;
	Node left;
	Node right;
	Node parent;
	
	Node(int v) {
		this.value = v;
	}
	
}
