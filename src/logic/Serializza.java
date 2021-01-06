package logic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import spedizione.Spedizione;

/**
 * Class for serializing an object of type Archive &lt; Shipping &gt;.
 * 
 * @author &#160; &#160; Castorini Francesco
 * @see Archivio
 */
public class Serializza {
	/**
	 * Constant representing the name of the serialized file.
	 */
	private static final String FILE_NAME = "Archivio.ser";
	
	/**
	 * 
	 */
	public Serializza() {
	}
	
	/**
	 * Serialize the archive.
	 * @param a archive to be serialized of type Archive
	 */
	public void serialize(Archivio<Spedizione> a) {

		try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(a); //Write byte stream to file system.
            out.close();
        } catch(IOException ex){
            ex.printStackTrace();
        }
	}
	
	/**
	 * Returns a serialized archive.
	 * @return Archive &lt; Shipping &gt;
	 * @see Archivio
	 */
	public Archivio<Spedizione> getSerializable() {
		Archivio<Spedizione> a2 = null;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME));
			a2 = (Archivio<Spedizione>) in.readObject();
			in.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		return a2;
	}
}
