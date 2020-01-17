import java.io.*;

public class EscriureFitxerCotxeObject {

	public static void main(String[] args) throws IOException {
		//Camp variable tipus Cotche
		Cotxe cotxe;
		
		//Declaració del fitxer
		File fitxer = new File("cotxesObject.txt");
		
		//Crea el flux de sortida
		FileOutputStream fileout = new FileOutputStream(fitxer);
		
		//Connectar el flux de bytes al flux de dades
		ObjectOutputStream dataOutCotxe = new ObjectOutputStream(fileout);
		
		//Les dades per generar els objectes Cotxe
		String matricula[] = {
				"AS123D", "D43RES", "4824IY",
				"QW364T", "WTG291", "657YTR"};
		
		String marca[] = {
				"Seat", "Volvo", "Renault",
				"Renault", "Fiat", "Seat"};
		
		String model[] = {
				"Ibiza", "Segarra", "Clio",
				"Captur", "500", "Ibiza"};
		
		int any[] = {
				2005, 22713, 184403,
				69613, 44578, 117842};
		
		//Recorre els arrays
		for (int i=0; i<matricula.length; i++){
			//Crea el cotxe
			cotxe = new Cotxe(matricula[i], marca[i], model[i], any[i]);
			dataOutCotxe.writeObject(cotxe);//L'escriu al fixer
		}
		dataOutCotxe.close();//Tanca el stream de sortida
	}

}
