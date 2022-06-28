
public class Node <T> {
	
	private T dato;
	private Node nextNode;
	
	public void Node() {
		this.dato = null;
		this.nextNode = null;
	}
	
	public T getDato() {
		return dato;
	}
	
	public void setDato(T dato) {
		this.dato = dato;
	}
	
	public Node getNextNode() {
		return nextNode;
	}
	
	public void setNextNode(Node nextNode) {
		this.nextNode = nextNode;
	}

}
