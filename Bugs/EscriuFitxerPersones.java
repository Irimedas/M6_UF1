import java.io.*;

public class EscriuFitxerPersones {
	public static void main(String[] args) throws IOException {
		File fitxer = new File("personas");
		//Crea un flux (stream) d'arxiu d'acc�s aleatori per llegir
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");
		
		//Dades: nom, cognom, edat, telefon
		String nom[] = {"Aqzw","Dews","Dcsw","Tredf"};		
		String cognom[] = {"Tyrt","Tyrt","Redf","Uhyg"};
		String edat[] = {"18","19","20","20"};
		String telefon[] = {"445566456","434454434","222333444","777888999"};
		
		//Construeix un buffer (mem�ria interm�dia) de strings
		StringBuffer buffer = null;
		
		for (int i = 0; i < nom.length; i++) {
			//1 enter ocupa 4 bytes
			aleatoriFile.writeInt(i+1);
			
			//15 car�cters a 2bytes/car�cter 30 bytes
			buffer = new StringBuffer (nom[i]);
			buffer.setLength(30);
			aleatoriFile.writeChars(buffer.toString());
			
			//15 car�cters a 2bytes/car�cter 30 bytes
			buffer = new StringBuffer (cognom[i]);
			buffer.setLength(30);
			aleatoriFile.writeChars(buffer.toString());
			
			//3 car�cters a 2bytes/car�cter 6 bytes
			buffer = new StringBuffer (edat[i]);
			buffer.setLength(6);
			aleatoriFile.writeChars(buffer.toString());
			
			//12 car�cters a 2bytes/car�cter 24 bytes
			buffer = new StringBuffer (telefon[i]);
			buffer.setLength(24);
			aleatoriFile.writeChars(buffer.toString());
			//Total 94
		}
		
		aleatoriFile.close();
	}
}
