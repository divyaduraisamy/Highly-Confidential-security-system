package nomad;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.imageio.stream.FileImageInputStream;

public class videoenc{

videoenc() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException
{

Cipher cipher=Cipher.getInstance("AES");
KeyGenerator keygen=KeyGenerator.getInstance("AES");
Key key=keygen.generateKey();
cipher.init(Cipher.ENCRYPT_MODE,key);
CipherInputStream cin=new CipherInputStream(new FileInputStream(new File("Bombay-Uyire-Uyire-Song.mp4")),cipher);

FileOutputStream fos=new FileOutputStream(new File("sap1.mp4"));
int i;
while((i=cin.read())!=-1){
fos.write(i);
}
//******************************decrypt*****************
/*Cipher cipher1=Cipher.getInstance("AES");

cipher1.init(Cipher.DECRYPT_MODE,key);
CipherInputStream cin1=new CipherInputStream(new FileInputStream(new File("sap1.mp4")),cipher1);

FileOutputStream fos1=new FileOutputStream(new File("divya.mp4"));
int j;
while((j=cin1.read())!=-1){
fos1.write(j);
}*/
}





public static void main(String[] args) {
try {
videoenc s=new videoenc();
} catch (InvalidKeyException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (NoSuchAlgorithmException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (NoSuchPaddingException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}




}

}

