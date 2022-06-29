import java.util.*;
import java.util.List;

public class AVLTree<E extends Comparable<? super E>> implements AVLTreeInterface<E>{

    private Node<E> raiz;
    Comparator<E> comparador;
    
    public AVLTree() {
    	
    }
    
    public AVLTree(Comparator<E> comp) {
    	this.comparador = comp;
    }
    
    public boolean insert(E data) {
     
    	Node<E> nodo = new Node<E>(data);
    	boolean salir = false;
    	boolean der = false;
    	Node<E> raizTmp = this.getRaiz();

    	int altIzq, altDer;
    	if(raizTmp == null){
    		this.raiz = nodo;
    		return true;
    	}else
    	
    	
    	if(this.contains(nodo.getDato())){
    		return false;
    	}
    	
    	
    	else{    	
    		while(!salir){

    			    				
		    	if(this.compararDato(nodo.getDato(), raizTmp.getDato())>0){
		    		if(raizTmp.getDerecha()!=null){
		    			raizTmp = raizTmp.getDerecha();	
		    		}else{
		    			salir = true;
		    			der = true;
		    		}
		    			    		
		    	}
		    	
		    	else{
		    		if(raizTmp.getIzquierda()!=null){
		    			raizTmp = raizTmp.getIzquierda();
		    		}else{
		    			salir = true;
		    		}
		    	}
    		}
    		
    		
    		if(der){
    			raizTmp.setDerecha(nodo);
    		}
    		
    		
    		else{
    			raizTmp.setIzquierda(nodo);
    		}
	
    		
    		while(equilibrado(this.getRaiz())<0){
				raizTmp = padre(raizTmp);
    		
    			if(raizTmp.getDerecha()==null){
	    			altDer = 0;
	    		}else{
	    			altDer = raizTmp.getDerecha().getAltura();
	    		}
	    		
	    		if(raizTmp.getIzquierda()==null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = raizTmp.getIzquierda().getAltura();
	    		}
	    		
    			Node<E> cambiar = estructurar(raizTmp, altIzq, altDer);
    			Node<E> superior = padre(raizTmp);
	
    			
    			if(compararDato(superior.getDato(), raizTmp.getDato())!=0){
    				if(superior.getIzquierda()!=null && compararDato(superior.getIzquierda().getDato(), raizTmp.getDato())==0){
	    				superior.setIzquierda(cambiar);		
		    		}
		    		else if(superior.getDerecha()!=null && compararDato(superior.getDerecha().getDato(), raizTmp.getDato())==0){
	    				superior.setDerecha(cambiar);
	    			}
    			}else{
    				this.raiz = cambiar;
    			}
    		}
    		return true;
    	}
    	
    }
    
    public Node<E> estructurar(Node<E> nodo, int altIzq, int altDer){
		if(extraeFactorE(nodo)==2){
			if( extraeFactorE(nodo.getDerecha()) == 1  || extraeFactorE(nodo.getDerecha()) == 0){
				nodo = rotacionSimpleIzquierda(nodo);
			}
			
			else if(extraeFactorE(nodo.getDerecha()) == -1){
				nodo = rotacionCompuestaDerecha(nodo);
			}
		}
		else if(extraeFactorE(nodo) == -2){
			if(extraeFactorE(nodo.getIzquierda() )==-1 || extraeFactorE(nodo.getDerecha())==0){
				nodo = rotacionSimpleDerecha(nodo);
			}
			
			else if(extraeFactorE(nodo.getIzquierda())==1){
				nodo = rotacionCompuestaIzquierda(nodo);
			}
		}

		return nodo;	
    }
    
    public int extraeFactorE(Node<E> nodo){
    	if(nodo!=null){
    		return nodo.getFactorE();
    	}else{
    		return 0;
    	}
    }
    
    public Node<E> rotacionSimpleIzquierda(Node<E> nodo){
		Node<E> nodoTmp = nodo;
		
    	nodo = nodoTmp.getDerecha(); //clone??
		nodoTmp.setDerecha(nodo.getIzquierda());

		nodo.setIzquierda(nodoTmp);

		return nodo;
    }
    
    public Node<E> rotacionSimpleDerecha(Node<E> nodo){
    	Node<E> nodoTmp = nodo;
    	nodo = nodoTmp.getIzquierda();

		nodoTmp.setIzquierda(nodo.getDerecha());
		nodo.setDerecha(nodoTmp);

		return nodo;
    }
    
    public Node<E> rotacionCompuestaIzquierda(Node<E> nodo){
    	Node<E> nodoTmp = nodo;
    	nodoTmp = rotacionSimpleIzquierda(nodoTmp.getIzquierda());
    	nodo.setIzquierda(nodoTmp);
    	nodoTmp = rotacionSimpleDerecha(nodo);
    	
    	return nodoTmp;
    }
    
    public Node<E> rotacionCompuestaDerecha(Node<E> nodo){
    	Node<E> nodoTmp = nodo;
    	nodoTmp = rotacionSimpleDerecha(nodoTmp.getDerecha());
    	nodo.setDerecha(nodoTmp);
    	nodoTmp = rotacionSimpleIzquierda(nodo);
    	
    	return nodoTmp;
    }
    
    public int equilibrado(Node<E> n) {
    	int hIzq = 0;
    	int hDer = 0;
    	
    	if(n == null) {
    		return 0;
    	}
    	hIzq = equilibrado(n.getIzquierda());
    	
    	if (hIzq < 0) {
    		return hIzq;
    	}
    	hDer = equilibrado(n.getDerecha());
    	
    	if (hDer < 0) {
    		return hDer;
    	}
    	
    	if(Math.abs(hIzq - hDer) > 1) {
    		return -1;
    	}
    	
    	return Math.max(hIzq, hDer) + 1;
    }
    
    public Node<E> padre(Node<E> nodo){
		Node<E> raizTmp = this.getRaiz();
		Stack<Node<E>> pila = new Stack<Node<E>>();
    	pila.push(raizTmp);	
    	while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){
	    	if(this.compararDato(nodo.getDato(), raizTmp.getDato())>0){
	    		if(raizTmp.getDerecha()!=null){   	
	    			raizTmp = raizTmp.getDerecha();
	    		}
	    	}
	    	else if(this.compararDato(nodo.getDato(), raizTmp.getDato())<0){	
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}
	    	}
	    	if(this.compararDato(nodo.getDato(), raizTmp.getDato())==0){
	    		return pila.pop();
	    	}

	    	pila.push(raizTmp);	
    	}
    	return pila.pop();
	}
    
    public boolean isEmpty() {
    	return this.size() == 0;
    }
    
    public Iterator<E> iterator(){ 
    	List<E> lista= this.inOrder();
    	Iterator<E> iter = lista.iterator();
    	
    	return iter;
    }
    	
    public boolean remove(E data) {
    	Node<E> borrar = null,mirar = null,cambiar = null, nPadre = null;
    	Node<E> raizTmp = this.getRaiz();
    	E c_aux, d_aux;
    	boolean salir = false;
    	int altDer = 0;
    	int altIzq = 0;
    	int a = 0;
    	
    	if(this.isEmpty()){
    		return false;
    	}

    	if(this.compararDato((E)data, raizTmp.getDato()) == 0){
	    	salir = true;
	    	borrar = raizTmp;
	    }
    	
    	while(!salir && (raizTmp.getDerecha() != null || raizTmp.getIzquierda() != null)){

	    	if(this.compararDato((E)data, raizTmp.getDato()) > 0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return false;
	    		}
	    	}else if(this.compararDato((E)data, raizTmp.getDato()) < 0){
	    	
	    		if(raizTmp.getIzquierda() != null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	if(this.compararDato((E)data, raizTmp.getDato()) == 0){
	    		salir = true;
	    		borrar = raizTmp;
	    	}
    	}
    

    	if(salir){
    		mirar = borrar;

	    	if(borrar.getIzquierda() == null && borrar.getDerecha() == null){
	    		mirar = padre(borrar);
	    		nPadre = padre(borrar);
	    		
	    		if(this.size() == 1){
	    			this.raiz = null;
	    		}
	    		
	    		if(nPadre.getIzquierda() != null && compararDato(nPadre.getIzquierda().getDato(), borrar.getDato())==0){
	    			nPadre.setIzquierda(null);
	    		}else if(nPadre.getDerecha() != null && compararDato(nPadre.getDerecha().getDato(), borrar.getDato())==0){
	    			nPadre.setDerecha(null);
	    		}
	    		borrar.setDato(null);
	    	}
	    	
	    	else if(borrar.getAltura() <= 2){

	    		if(borrar.getIzquierda() != null){
	    			borrar.setDato(borrar.getIzquierda().getDato());
	    			borrar.setIzquierda(null);
	    		}
	    		
	    		else if(borrar.getDerecha() != null){
	    			borrar.setDato(borrar.getDerecha().getDato());
	    			borrar.setDerecha(null);
	    		}
	    	}
	    	
	    	else{

	    		if(borrar.getIzquierda() != null){
		    		cambiar = borrar.getIzquierda();
		    		
		    		while(cambiar.getDerecha()!=null){
		    			cambiar = cambiar.getDerecha();
		    		}
		    	}
		    		
		    	else if(borrar.getDerecha() != null){
		    		cambiar = cambiar.getDerecha();
		    	
		    		while(cambiar.getIzquierda() != null){
		    			cambiar = cambiar.getIzquierda();
		    		}
		    	}
	    	
		    	c_aux = cambiar.getDato();
		    	Node<E> papa = padre(cambiar);
		    	
		    	if(cambiar.getIzquierda() != null || cambiar.getDerecha() != null){
			    	if(cambiar.getIzquierda() != null){
			    		cambiar.setDato(cambiar.getIzquierda().getDato());
			    		cambiar.setIzquierda(null);
			    	}else if(cambiar.getDerecha() != null){
			    		cambiar.setDato(cambiar.getDerecha().getDato());
			    		cambiar.setDerecha(null);
			    	}
		    	}
		    	else{		    	
			    	if(papa.getIzquierda() != null && compararDato(papa.getIzquierda().getDato(), cambiar.getDato())==0){
			    		papa.setIzquierda(null);
			    	}else{
			    		papa.setDerecha(null);
			    	}
			    	cambiar.setDato(borrar.getDato());
			    	borrar.setDato(c_aux);
		    	}		    
	    	}
	    	
	    	while(equilibrado(this.getRaiz()) < 0){
    			if(mirar.getDerecha() == null){
	    			altDer = 0;
	    		}else{
	    			altDer = mirar.getDerecha().getAltura();
	    		}
	    		
	    		if(mirar.getIzquierda() == null){
	    			altIzq = 0;
	    		}else{
	    			altIzq = mirar.getIzquierda().getAltura();
	    		}
	    		
    			Node<E> cambiar2 = estructurar(mirar, altIzq, altDer);
    			Node<E> superior = padre(mirar);
    			
    			if(compararDato(superior.getDato(), mirar.getDato()) != 0){
    				if(superior.getIzquierda() != null && compararDato(superior.getIzquierda().getDato(), mirar.getDato())==0){
	    				superior.setIzquierda(cambiar2);		
		    		}
		    		else if(superior.getDerecha() != null && compararDato(superior.getDerecha().getDato(), mirar.getDato())==0){
	    				superior.setDerecha(cambiar2);
	    			}
    			}else{
    				this.raiz = cambiar2;
    			}
    			mirar = padre(mirar);
    		}
    		return true;	    	
    	}	
    	return false;
    }

    
    public E get(E data) {
        
        return null;
    }

   
    public boolean contains(E data) {
    	Node<E> raizTmp = this.getRaiz();
    	if(this.isEmpty()){
    		return false;
    	}
    	
    	
    	if(this.compararDato((E)data, raizTmp.getDato()) == 0){
	    	return true;
	    }
	    
    	while(raizTmp.getDerecha() != null || raizTmp.getIzquierda() != null){

	    	if(this.compararDato((E)data, raizTmp.getDato())>0){
	    		if(raizTmp.getDerecha() != null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return false;
	    		}
	    	}else if(this.compararDato((E)data, raizTmp.getDato())<0){	
	    		if(raizTmp.getIzquierda() != null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    	if(this.compararDato((E)data, raizTmp.getDato())==0){
	    		return true;
	    	}
    	}
    	return false;
    }
    
    

   
    public int size() {
        
        return this.preOrder().size();
    }

    
    public List<E> preOrder() {
    	List<E> lista = new ArrayList<E>();
        Node<E> nodo = this.getRaiz();
        Stack<Node<E>> pila = new Stack<Node<E>>();
        
        while((nodo!=null && nodo.getDato()!=null) || !pila.empty()){
     		if(nodo!=null){
     			lista.add(nodo.getDato());
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			nodo = nodo.getDerecha();
     		}
     	} 	
    	
    	return lista;
    }

    /**
     * Get the postorder traversal of the tree.
     *
     * @return a postorder traversal of the tree, or an empty list
     */
    
    public List<E> postOrder() {
    	List<E> lista = new ArrayList<E>();
        Node<E> nodo = this.getRaiz();
        Stack<Node<E>> pila1 = new Stack<Node<E>>();
        Stack<Boolean> pila2 = new Stack<Boolean>();
        

    	while((nodo != null && nodo.getDato() != null) || !pila1.empty()){
    		
    		if(nodo != null){
    			pila1.push(nodo);
    			pila2.push(true);
    			nodo = nodo.getIzquierda();
    		}else{
    			nodo = pila1.pop();
    			if(pila2.pop()){
    				pila1.push(nodo);
    				pila2.push(false);
    				nodo = nodo.getDerecha();
    			}else{
    				lista.add(nodo.getDato());
    				nodo = null;
    			}
    		}
    	}
    	
    	return lista;
    }

    /**
     * Get the inorder traversal of the tree.
     *
     * @return an inorder traversal of the tree, or an empty list
     */
    
    public List<E> inOrder() {
        List<E> lista = new ArrayList<E>();
        Node<E> nodo = this.getRaiz();
        Stack<Node<E>> pila = new Stack<Node<E>>();
        
        while((nodo != null &&nodo.getDato() != null)|| !pila.empty()){
     		if(nodo != null){
     			pila.push(nodo);
     			nodo = nodo.getIzquierda();
     		}else{
     			nodo = pila.pop();
     			lista.add(nodo.getDato());
     			nodo = nodo.getDerecha();
     		}
     	}
        return lista;
    }

    /**
     * Clear the tree.
     */
    @Override
    public void clear() {
        Iterator<E> iter = this.iterator();
        
        while(iter.hasNext()){
    		remove(iter.next());
    	}
    }

    
    public int height(E data) {
        Node<E> nodo = this.getNodo(data);
        if(!this.contains(data)) {
        	return -1;
        }
        return nodo.getAltura();
    }

    
    public Node<E> getRaiz() {
        return this.raiz;
    }
    
    public Node<E> getNodo(E dato){
     	Node<E> raizTmp = this.getRaiz();
     	
     	if(this.isEmpty()){
     		return null;
     	}
    	
   		while(raizTmp.getDerecha()!=null || raizTmp.getIzquierda()!=null){

	    	if(this.compararDato(dato, raizTmp.getDato())>0){
	    		if(raizTmp.getDerecha()!=null){   		
	    			raizTmp = raizTmp.getDerecha();
	    		}else{
	    			return null;
	    		}
	    	}else if(this.compararDato(dato, raizTmp.getDato())<0){	
	    		if(raizTmp.getIzquierda()!=null){   
		    		raizTmp = raizTmp.getIzquierda();
	    		}else{
	    			return null;
	    		}
	    	}
	    	
	    	if(this.compararDato(dato, raizTmp.getDato())==0){
	    		return raizTmp;
	    	}
    	}
    	
    	return raizTmp;
    }
    
    private Comparator<E> getComparator(){
    	return this.comparador;
    }
    
    public E extraeDato(Node<E> nodo){
    	return nodo.getDato();
    }
    
    private int compararDato(E t1, E t2){
    	if(this.comparador==null){
    		return ((Comparable)t1).compareTo(t2);
    	}else{
    		return this.comparador.compare(t1,t2);
    	}
    }
    
    public void imprimir() {
        imprimir(raiz);

    }
    
    private void imprimir(Node nodo) {
        if(nodo != null) {
        	imprimir(nodo.getDerecha());
        	System.out.print("[" + nodo.getDato() + "]");
           	imprimir(nodo.getIzquierda());
        }
    }
    
    public void imprimirPorAltura() {
    	imprimirPorAltura(raiz);
    }
    
    public void imprimirPorAltura(Node nodo){
    	 if(nodo != null) {
         	imprimirPorAltura(nodo.getDerecha());
         	System.out.println(replicate(" ", height((E) raiz) - height((E) nodo)) + "[" + nodo.getDato() + "]");
            	imprimirPorAltura(nodo.getIzquierda());
         }
    }
    
    private static String replicate(String a, int cont) {
    	String x = "";
    	
    	for(int i = 0; i < cont; i++) {
    		x = x + a;
    	}
    	
    	return x;
    	
    }
    
    public int calcularAltura() {
    	return calcularAltura(raiz);
    }
    
    private int calcularAltura(Node nodo) {
    	if(nodo == null) {
    		return -1;
    	}
    	
    	else {
    		return 1 + Math.max(calcularAltura(nodo.getIzquierda()), calcularAltura(nodo.getDerecha()));
    	}
    }
    
    public void imprimirPorNiveles() {
    	imprimirPorNiveles(raiz);
    }
    
    private void imprimirPorNiveles(Node nodo) {
    	int max = 0;
    	int nivel = calcularAltura();
    	
    	for(;nivel >= 0; nivel--) {
    		max += Math.pow(2, nivel);
    	}
    	
    	max++;
    	
    	Node cola[] = new Node[max];
    	cola[1] = nodo;
    	int x = 1;
    	
    	for(int i = 2; i < max; i += 2, x++) {
    		if(cola[x] == null) {
    			cola[i] = null;
    			cola[i + 1] = null;
    		}
    		else {
    			cola[i] = cola[x].getIzquierda();
    			cola[i + 1] = cola[x].getDerecha();
    		}
    	}
    	
    	nivel = 0;
    	int cont = 0;
    	int cantidad = 1;
    	int ultimaPosicion = 1;
    	
    	for(int i = 1; i < max; i++) {
    		if(i == Math.pow(2, nivel)) {
    			System.out.println("\n Nivel" + nivel + ": ");
    			nivel++;
    		}
    		if(cola[i] != null) {
    			System.out.println("[" + cola[i].getDato() + "]");
    			cont++;
    		}
    		if(ultimaPosicion == i && cantidad == Math.pow(2, --nivel)) {
    			if(cantidad == 1) {
    				System.out.print("Cantidad de nodos: " + cont + "(raiz)");
    			}
    			else {
    				System.out.print("Cantidad de nodos: " + cont);
    			}
    			cont = 0;
    			cantidad *= 2;
    			ultimaPosicion += Math.pow(2, ++nivel);
    		}
    	}
    }
	
}
