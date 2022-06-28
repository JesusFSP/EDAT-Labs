
public class TestCola {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Cola cola = new Cola();
		cola.push("JEsus");
		cola.push(45);
		cola.push(50);
		cola.push(60);
		cola.push(45);
		System.out.println("<<---Ejemplo de Cola--->>");
		cola.listar();
		System.out.println(cola.getSize());
		cola.pop();
		cola.listar();
		System.out.println(cola.getSize());

	}

}
