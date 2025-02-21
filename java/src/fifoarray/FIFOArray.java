import java.util.NoSuchElementException;

public class FIFOArray {

    private int head;
    private int tail;
    private String[] fila;
    private int size;

    public FIFOArray(int capacidade) {
        this.head = -1;
        this.tail = -1;
        this.size = 0;
        this.fila = new String[capacidade];
    }
    
    /**
     * Verifica se a fila está vazia ou não
     * 
     * @return true caso a fila esteja cheia, false caso não
     */
    public boolean isEmpty() {
        return this.head == -1 && this.tail == -1;
    }

    /**
     * Verifica se a fila está cheia ou não
     * 
     * @return true caso a fila esteja cheia, false caso não
     */
    public boolean isFull() {
        return this.size == this.fila.length;
    }
    
    /**
     * Adiciona um elemento na última posição da fila caso ela não esteja cheia e lança uma exceção caso esteja cheia
     * 
     * @param element o elemento a ser adicionado
     */
    public void addLast(String element) {
        if (isFull()) throw new RuntimeException("fila cheia");
        
        if (isEmpty()) {
            this.head = 0;
        }

        this.tail = (this.tail + 1) % this.fila.length;
        this.fila[this.tail] = element;
        this.size += 1;
    }
    
    /**
     * Informa o elemento que se encontra na primeira posição da fila (em head)
     * 
     * @return o valor do elemento presente em head
     */
    public String getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.fila[this.head];
    }
    
    /**
     * Informa o elemento que se encontra na última posição da fila (em tail)
     * 
     * @return o elemento presente em tail
     */
    public String getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.fila[this.tail];
    }
    
    /**
     * Remove o elemento presente em head, e, caso a fila fique vazia, reinincia o valor de head e tail para -1,
     * caso contrário, move a posição de head 1 posição para frente
     * 
     * @return o elemento que foi removido
     */
    public String removeFirst() {
        
        if (isEmpty()) throw new NoSuchElementException();
        
        String value = this.fila[this.head];

        if (this.head == this.tail) {
            this.head = -1;
            this.tail = -1;
        } else {
            this.head = (this.head + 1) % this.fila.length;
        }
        this.size -= 1;   
        return value; 
    }
    
    /**
     * Percorre o array de head até tail procurando por um elemento e, caso encontre, retorna seu índice na fila (não no array)
     * 
     * @param element elemento buscado
     * @return o índice na fila caso o elemento exista, -1 caso não
     */
    public int indexOf(String element) {
        for (int i = 0; i < this.size; i++) {
            int iArray = (this.head + i) % this.fila.length;

            if (this.fila[iArray].equals(element))
                return i;
        }
        return -1;
    }
    
    /**
     * Representação textual da fila no formato:
     * "a, b, c [...]"
     */
    public String toString() {
        String out = "";
        for (int i = 0; i < this.size; i++) {
            int iArray = (this.head + i) % this.fila.length;

            out += this.fila[iArray];
            if (i != this.size-1)
                out += ", ";
        }
        return out;
    }
    
    /**
     * Verifica se um elemento está contido na fila ou não
     * 
     * @param element o elemento cuja existência na fila estamos verificando
     * @return true caso o elemento esteja presente na fila, false caso não
     */
    public boolean contains(String element) {
        return indexOf(element) != -1;
    }
    
    /**
     * Informa o tamanho da fila
     * 
     * @return o tamanho da fila
     */
    public int size() {
        return this.size;
    }
    
}
