
public class Pila <T> {
	
	private Node inicio;
	private int size;
	
	public Pila () {
		inicio = null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return inicio == null;
	}
	
	public int getSize() {
		return size;
	}
	
	public void push(T dato) {
		Node aux = new Node();
		aux.setDato(dato);
		
		if(isEmpty()) {
			inicio = aux;
		}
		
		else {
			aux.setNextNode(inicio);
			inicio = aux;
		}
		size++;
	}
	
	public void pop() {
		if(!isEmpty()) {
			inicio = inicio.getNextNode();
			size--;				
		}
	}
	
	public T peek() throws Exception{
		if(!isEmpty()) {
			return (T) inicio.getDato();
		}
		else {
			throw new Exception("La pila se encuentra vacia");
		}
	}
	
	public boolean search(T dato) {
		
		Node aux = inicio;
		boolean existe = false;
		
		while(existe != true && aux != null) {
			if(dato == aux.getDato()) {
				existe = true;
			}
			else {
				aux = aux.getNextNode();
			}
		}
		return existe;
	}
	
	public void remove(T dato) {
		
		if (search(dato)) {
			Node pilaAux = null;
			
			while(dato != inicio.getDato()) {
				Node temp = new Node();
				temp.setDato(inicio.getDato());
				
				if (pilaAux == null) {
					pilaAux = temp;
				}
				else {
					temp.setNextNode(pilaAux);
					pilaAux = temp;
				}
				pop();
			}
			pop();
			
			while (pilaAux != null) {
				push((T) pilaAux.getDato());
				pilaAux = pilaAux.getNextNode();
			}
			pilaAux = null;
		}
		
	}
	
	public void removeAll() {
		inicio = null;
		size = 0;
	}
	
	public void listar() {
		
		Node aux = inicio;
		
		while ( aux != null) {
			System.out.println("|\t" + aux.getDato() + "\t|");
			System.out.println("-----------------");
			aux = aux.getNextNode();
		}
	}
	
	

}
