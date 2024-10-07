package org.elis.radice;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.businesslogic.BusinessLogic;
import org.elis.jdbc.UtenteDaoJDBC;
import org.elis.model.Gioco;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

public class Test {

	public static void main(String[] args) {
		
	
		File file = new File("./risorse-media/img_giochi/test.txt");
    	if(!file.exists()) {
    		try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	try(
    		DataOutputStream os = new DataOutputStream(new FileOutputStream(file))){
    		
    		os.writeChars("hello world");
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
