import java.util.NoSuchElementException;

public class Pilha {

    private int topo;
    private String[] pilha;

    public Pilha(int capacidade) {
        this.topo = -1;
        this.pilha = new String[capacidade];
    }
    
    public boolean isEmpty() {
        return this.topo == -1;
    }

    public boolean isFull() {
        return this.topo + 1 ==  this.pilha.length;
    }
    
    public void push(String element) {
        if (isFull()) throw new RuntimeException("Pilha cheia!");
        this.pilha[++this.topo] = element;
    }
    
    public String pop() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.pilha[this.topo--];
    }
    
    public String peek() {
        if (isEmpty()) throw new NoSuchElementException();
        return this.pilha[this.topo];
    }
    
    public int size() {
        return this.topo + 1;
    }

    public static void main(String[] args) {
        Pilha pilha = new Pilha(3);
        assert pilha.isEmpty();
        assert !pilha.isFull();
        assert pilha.size() == 0; 
        
        pilha.push("a");
        assert "a".equals(pilha.peek());
        assert pilha.size() == 1; 

        pilha.push("b");
        assert "b".equals(pilha.peek());
        assert 2 == pilha.size();

        assert pilha.pop().equals("b");
        assert 1 == pilha.size();
        assert "a".equals(pilha.peek());

        pilha.push("b");
        pilha.push("c");
    
        assert pilha.peek().equals("c");
        assert 3 == pilha.size();
        assert pilha.pop().equals("c");
        assert pilha.pop().equals("b");
        assert pilha.pop().equals("a");
        assert pilha.isEmpty();

    }
}
