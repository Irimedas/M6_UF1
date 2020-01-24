import java.io.*;

public class ConsultarFitxerPersones {

	public static void main(String[] args) throws IOException{
		File fitxer = new File("personas");
		//Crea un flux (stream) d'arxiu d'accés aleatori només lectura
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");
		
		int apuntador = 0, id, count = 0;
		char aux;
		char nom[] = new char[30];
		char cognom[] = new char[30];
		char edat[] = new char[6];
		char telefon[] = new char[24];
		
		//Recorrer el fitxer de persones
		aleatoriFile.seek(apuntador);//<-- genera error
		
		for (;;) {
			
			//Llegeix ID
			id = aleatoriFile.readInt();
			
		count+= 4;
			
			//Llegeix nom
			for(int i = 0; i<30; i++) {
				aux = aleatoriFile.readChar();
				nom[i] = aux;
				count++;
			}
			String noms = new String(nom);
			
			//Llegeix cognom
			for(int i = 0; i<30; i++) {
				aux = aleatoriFile.readChar();
				cognom[i] = aux;
				count++;
			}
			String cognoms = new String(cognom);
			
			//Llegeix edat
			for(int i = 0; i<6; i++) {
				aux = aleatoriFile.readChar();
				edat[i] = aux;
				count++;
			}
			String edats = new String(edat);
			
			//Llegeix telefon
			for(int i = 0; i<24; i++) {
				aux = aleatoriFile.readChar();
				telefon[i] = aux;
				count++;
			}
			String telefons = new String(telefon);
			
			System.out.println("ID: "+id+
								"\nNom: "+noms+
								"\nCognom: "+cognoms+
								"\nEdat: "+edats+
								"\nTelefon: "+telefons+"\n"+count+"\n\n");
			
			//apuntador += 94;
			
			//Si coincideix on s'està apuntat amb el final del fitxer, sortim
			if(aleatoriFile.getFilePointer()==aleatoriFile.length()) break;
		}
		//aleatoriFile.close();//Tancar el fitxer
	}

}
