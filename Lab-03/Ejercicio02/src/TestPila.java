
public class TestPila {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pila pila = new Pila();
		
		pila.push("jesus");
		pila.push(16);
		pila.push(12);
		pila.push(8);
		pila.push(65);
		System.out.println("<<---Ejemplo de Pila--->>");
		pila.listar();
		System.out.println(pila.getSize());
		pila.pop();
		pila.listar();
		System.out.println(pila.getSize());
		pila.remove(25);
		pila.listar();
		System.out.println(pila.getSize());
		System.out.println(pila.search(12));
		pila.removeAll();
		System.out.println(pila.isEmpty());
		pila.listar();	
				

	}

}
