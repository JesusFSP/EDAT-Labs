
public class Cola <T> {
	
	private Node cabeza;
	private Node ultimo;
	private int size;
	
	public Cola () {
		cabeza = null;
		ultimo= null;
		size = 0;
	}
	
	public boolean isEmpty() {
		return cabeza == null;
	}
	
	public int getSize() {
		return size;
	}
	
	public void push(T dato) {
		Node aux = new Node();
		aux.setDato(dato);
		
		if(isEmpty()) {
			cabeza = aux;
			ultimo = aux;
		}
		
		else {
			ultimo.setNextNode(aux);
			ultimo = aux;
		}
		size++;
	}
	
	public void pop() {
		if(!isEmpty()) {
			Node aux = cabeza;
			cabeza = cabeza.getNextNode();
			aux.setNextNode(null);
			if(cabeza == null) {
				ultimo = null;
			}
			size--;				
		}
	}
	
	public T peek() throws Exception{
		if(!isEmpty()) {
			return (T) ultimo.getDato();
		}
		else {
			throw new Exception("La pila se encuentra vacia");
		}
	}
	
	public boolean search(T dato) {
		
		Node aux = cabeza;
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
			
			while(dato != cabeza.getDato()) {
				Node temp = new Node();
				temp.setDato(cabeza.getDato());
				
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
		cabeza = null;
		ultimo = null;
		size = 0;
	}
	
	public void listar() {
		
		Node aux = cabeza;
		
		while ( aux != null) {
			System.out.print("|\t" + aux.getDato() + "\t|");
			
			aux = aux.getNextNode();
		}
	}
	
	

}