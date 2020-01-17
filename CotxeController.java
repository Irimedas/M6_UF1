import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CotxeController {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		//Declaració del fitxer
		File fitxer = new File("cotxesObject.txt");
		ArrayList<Cotxe> fCars = new ArrayList<Cotxe>();
		
		fCars = leturaDelFichero(fitxer, fCars);
		
		//Declaració del teclat
		Scanner teclado = new Scanner(System.in);
		String textoTeclado = "";
		int intTeclado = 0;
		
		boolean salida = false;
		
		while (!salida) {
			System.out.println("Introduce que quieres hacer[l|n|f|q]: ");
			textoTeclado = teclado.nextLine();
			
			//Lectura de coches
			if(textoTeclado.equalsIgnoreCase("list")||
				 	textoTeclado.equalsIgnoreCase("l")) {
				listarCochesActuales(fCars);
				
			//Creación de un nuevo coche
			}else if(textoTeclado.equalsIgnoreCase("new")||
				 	textoTeclado.equalsIgnoreCase("n")){
				registrarNuevoCoche(teclado, fCars);
			
			//Busqueda por campo	
			}else if(textoTeclado.equalsIgnoreCase("find")||
				 	textoTeclado.equalsIgnoreCase("f")){
				System.out.println("Sobre que quieres buscar[m matricula|a marca|o model|y any]:");
				
				textoTeclado = teclado.nextLine();
				
				if(textoTeclado.equalsIgnoreCase("matricula")||
					 	textoTeclado.equalsIgnoreCase("m")){
					
					System.out.println("Introduce la matricula:");
					textoTeclado = teclado.nextLine();
					filtrarPorTexto(fCars, 1, textoTeclado);
					
				}else if(textoTeclado.equalsIgnoreCase("marca")||
					 	textoTeclado.equalsIgnoreCase("a")) {
					System.out.println("Introduce la marca:");
					textoTeclado = teclado.nextLine();
					filtrarPorTexto(fCars, 2, textoTeclado);
					
				}else if(textoTeclado.equalsIgnoreCase("modelo")||
					 	textoTeclado.equalsIgnoreCase("o")) {
					System.out.println("Introduce el modelo:");
					textoTeclado = teclado.nextLine();
					filtrarPorTexto(fCars, 3, textoTeclado);
					
				}else if(textoTeclado.equalsIgnoreCase("año")||
					 	textoTeclado.equalsIgnoreCase("y")) {
					System.out.println("Introduce el año:");
					try {
						intTeclado = teclado.nextInt();
						teclado.nextLine();
						filtrarPorNumero(fCars, 1, intTeclado);
					
					} catch (Exception e) {
						System.out.println("Solo numeros.");
					}
				}
				
			
			//Salir del programa	
			}else if(textoTeclado.equalsIgnoreCase("quit") ||
					 	textoTeclado.equalsIgnoreCase("q")) {
				salida = true;
			}
			
			System.out.println();
		}
		
		System.out.println("-t-");
		teclado.close();
		
		escrituraDelFichero(fitxer, fCars);
	}

	private static void filtrarPorNumero(ArrayList<Cotxe> arrCars, int type, int value) {
		int num = 0;
		ArrayList<Cotxe> findCars = new ArrayList<Cotxe>();
		
		for (int i = 0; i < arrCars.size(); i++) {
			if(type == 1){
				num = arrCars.get(i).getAny();
			}
			
			if(value == num){
				findCars.add(arrCars.get(i));
			}
		}
		
		if(!arrCars.isEmpty()){
			listarCochesActuales(findCars);
		}else {
			System.out.println("no se han encontrado datos.");
		}
	}

	private static void filtrarPorTexto(ArrayList<Cotxe> arrCars, int type, String value) {
		String text = "";
		ArrayList<Cotxe> findCars = new ArrayList<Cotxe>();
		
		for (int i = 0; i < arrCars.size(); i++) {
			if(type == 1){
				text = arrCars.get(i).getMatricula();
			}else if(type == 2){
				text = arrCars.get(i).getMarca();
			}else if(type == 3){
				text = arrCars.get(i).getModel();
			}
			
			if(text.equalsIgnoreCase(value)){
				findCars.add(arrCars.get(i));
			}
		}
		
		if(!arrCars.isEmpty()){
			listarCochesActuales(findCars);
		}else {
			System.out.println("no se han encontrado datos.");
		}
		
	}

	//Mostrar los datos de una array de "Coches"
	private static void listarCochesActuales(ArrayList<Cotxe> arrCars) {
		for (int i = 0; i < arrCars.size(); i++) {
			System.out.println("[Matrícula: " + arrCars.get(i).getMatricula() + "]"
							 + "[Marca: "+ arrCars.get(i).getMarca() +"]"
					 		 + "[Model: "+arrCars.get(i).getModel()+"]"
			 		 		 + "[Any: "+arrCars.get(i).getAny()+"]");
		}
	}

	//Función que lee el fichero y muestra sus datos
	public static ArrayList<Cotxe> leturaDelFichero(File file, ArrayList<Cotxe> arrCars) throws IOException, ClassNotFoundException{

		//Camp variable tipus Cotxe
		Cotxe cotxe;
				
		//Crea el flux d'entrada
		FileInputStream fileIn = new FileInputStream(file);
		//Connectar el flux d'entrada de bytes al flux de dades
		ObjectInputStream dataInCotxe = new ObjectInputStream(fileIn);
		
		//Lectura completa del archivo de cotxes
		try {
			System.out.println("-------------------------------");
			while (true) {
				
				//Llegeix les dades del cotxe
				cotxe = (Cotxe)dataInCotxe.readObject();
				arrCars.add(cotxe);
			}	
		} catch (EOFException e) {}
		
		//Tanca el stream d'entrada
		dataInCotxe.close();
		
		return arrCars;
	}
	
	public static void escrituraDelFichero(File file, ArrayList<Cotxe> arrCars) throws IOException {
		
		Cotxe c;
		
		//Crea el flux de sortida
		FileOutputStream fileout = new FileOutputStream(file);
		
		//Connectar el flux de bytes al flux de dades
		ObjectOutputStream dataOutCotxe = new ObjectOutputStream(fileout);
		
		for (int i = 0; i < arrCars.size(); i++) {
			c = new Cotxe(arrCars.get(i).getMatricula(), arrCars.get(i).getMarca(), arrCars.get(i).getModel(), arrCars.get(i).getAny());
			dataOutCotxe.writeObject(c);
		}
		
		//Tanca el stream de sortida
		dataOutCotxe.close();
	}
	
	//Guardar nuevo Coche en la array
	public static void registrarNuevoCoche(Scanner teclado, ArrayList<Cotxe> arrCars){
		String mat, marc, mod;
		int any;
		Cotxe car;
		
		try {
			System.out.println("Introduce la matrícula:");
			mat = teclado.next();
			System.out.println("Introduce la marca:");
			marc = teclado.next();
			System.out.println("Introduce la modelo:");
			mod = teclado.next();
			System.out.println("Introduce el año:");
			any = teclado.nextInt();
			teclado.nextLine();
			
			car = new Cotxe(mat, marc, mod, any); 
			arrCars.add(car);
			
			System.out.println("Guardando...\n"+
								"["+mat+"]["+marc+"]["+mod+"]["+any+"]\n"+
								"Guardado.");
		} catch (Exception e) {
			System.out.println("Error detectado...\n Cancelando guardado.");
		}
	}
}
