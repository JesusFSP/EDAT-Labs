

public class Node<E extends Comparable<? super E>> {

    private E dato;
    private Node<E> izquierda;
    private Node<E> derecha;
    private int factorE;
    
    public Node(E dato) {
    	this.dato = dato;
    	izquierda = null;
    	derecha = null;
    	factorE = 0;
    }
    
    public Node<E> getIzquierda(){
		return izquierda;
	}
	
	public Node<E> getDerecha(){
		return derecha;
	}
	
	
	public E getDato(){
		return dato;
	}
	
	
	public void setDerecha(Node<E> derecha){
		this.derecha = derecha;
	}
	
	
	public void setIzquierda(Node<E> izquierda){
		this.izquierda = izquierda;
	}
	
	
	public void setDato(E dato){
		this.dato = dato;
	}
	
	
	public int getFactorE(){
		int altDer = 0;
		int altIzq = 0;
		if(this.getDerecha()!=null){
	    	altDer = this.getDerecha().getAltura();
	   	}
	   	if(this.getIzquierda()!=null){		    
	   		altIzq = this.getIzquierda().getAltura();
	   	}
		return (altDer - altIzq);
	}
	
	
	public void setFactorE(int fe){
		this.factorE = fe;
	}
	
	
	public int getAltura(){
		int hIzq = 0;
		int hDer = 0;
		
		if(this.getDato()==null){
		  return 0;
    	}


		if(this.getIzquierda()!=null){	
			hIzq = this.getIzquierda().getAltura();
		}else{
			hIzq = 0;
		}
    	
    	if(this.getDerecha()!=null){   
    		hDer = this.getDerecha().getAltura();
    	}else{
    		hDer = 0;
    	}
    	return Math.max(hIzq, hDer) + 1;
	}
    
}