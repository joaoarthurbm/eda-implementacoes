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
    
    public boolean isEmpty() {
        return this.head == -1 && this.tail == -1;
    }

    public boolean isFull() {
        return (this.tail + 1) % this.fila.length == this.head;
    }
    
    public void addLast(String element) {
        // toda adição deve aumentar o número de elementos, exceto
        // se já estiver cheio.
        if (!isFull())
            this.size += 1;
    
        // na primeira adição, ambos vão para o índice 0.
        if (isEmpty())
            this.head = 0;
        
        // se já tiver cheio, precisamos andar com head para liberar o espaço;
        // e não acrescentamos em size porque não houve aumento de elementos.
        if (isFull())
            this.head += 1 % this.tail;
        
        // incrementa tail e adiciona o novo elemento
        this.tail = (this.tail + 1) % this.fila.length;
        this.fila[tail] = element;
    }
    
    public String getFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.fila[this.head];
    }
    
    public String getLast() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.fila[this.tail];
    }
    
    
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
    
    public int indexOf(String element) {
        for (int i = head; i <= tail; i += 1 % this.fila.length) {
            if (this.fila[i].equals(element))
                return i;
        }
        return -1;
    }
    
    public String toString() {
        String out = "";
        for (int i = head; i <= tail; i += 1 % this.fila.length) {
            out += this.fila[i] + " ";
        }
        return out.trim();
    }
    
    public boolean contains(String element) {
        return indexOf(element) != -1;
    }
    
    public int size() {
        return this.size;
    }

    public static void main(String[] args) {
        FIFOArray fila = new FIFOArray(3);
        assert fila.isEmpty();
        assert !fila.isFull();
        
        fila.addLast("a");
        assert "a".equals(fila.getFirst());

        fila.addLast("b");
        assert "a".equals(fila.getFirst());
        assert 2 == fila.size();

        assert fila.removeFirst().equals("a");
        assert 1 == fila.size();

        assert fila.removeFirst().equals("b");
        assert fila.isEmpty();
        assert !fila.isFull();
    
        fila.addLast("a");
        fila.addLast("b");
        fila.addLast("c");
        
        assert fila.getFirst().equals("a");
        assert 3 == fila.size();
        fila.addLast("d");
        assert 3 == fila.size();
        assert fila.getFirst().equals("b");
        assert fila.getLast().equals("d");
        

    }
}
