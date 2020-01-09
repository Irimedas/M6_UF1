import java.io.File;
import java.util.Date;

public class VeureInfo {

	public static void main(String [] args) {
		try {
			File f = new File(args[0]);
			 
			 //El archibo introducido no existe
			 if(!f.exists()) {
				 System.out.println("Esa ruta no existe.");
			 
			 } else {
				 
				//Si la ruta inserida es de un directorio ocuto
				if(f.isHidden() && f.isDirectory()){
					System.out.println("Esta ruta corresponde a un directorio oculto.");
					
				//Si la ruta inserida es de un fichero oculto
				}else if(f.isHidden() && f.isFile()){
					System.out.println("Esta ruta corresponde a un fichero oculto.");
				 
				//Si la ruta inserida es de un directorio
			 	}else if(f.isDirectory()){
			 		lecturaDeDirectorio(f);
				
				//Si la ruta inserida es la de un fichero
				}else if(f.isFile()){
					lecturaDeFichero(f);
				}
			 }
		}catch(Exception ex) {
			System.out.println("El parametro de ruta introducido no es correcto.");
		}
	 }
	
	
	//Lectura de archivos de un directorio
	public static void lecturaDeDirectorio(File f){
		obtenFechaCreacionFichero(f);
		System.out.println("Ficheros dentro del directorio: ");
		String[] arxius = f.list();
		
		for (int i = 0; i<arxius.length; i++){
			System.out.println(arxius[i]);
		}
	}
	
	//Lectura de datos de un fichero
	public static void lecturaDeFichero(File f){
		obtenFechaCreacionFichero(f);
		System.out.println("Nombre del fichero: "+f.getName());
		System.out.println("Ruta              : "+f.getPath());
		System.out.println("Ruta absoluta     : "+f.getAbsolutePath());
		System.out.println("Se puede escribir : "+f.canRead());
		System.out.println("Se puede leer     : "+f.canWrite());
		System.out.println("Tamaño            : "+f.length());
	}
	
	//Obtener la fecha de creación de un fichero o carpeta
	public static void obtenFechaCreacionFichero(File f){
		
		Date hoy = new Date();
		
		if((hoy.getTime()- f.lastModified()) < 259200000){
			System.out.println("I -> El archivo fue modificado en los ultimos 3 dias.");
		}
	}
}
