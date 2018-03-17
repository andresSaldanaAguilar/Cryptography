import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

class DesEncrypter {
  byte[] buf = new byte[1024];
  Cipher ecipher;
  Cipher dcipher;
  DesEncrypter(SecretKey key) throws Exception{
    byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
    ecipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
    dcipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

    ecipher.init(Cipher.ENCRYPT_MODE, key);
    dcipher.init(Cipher.DECRYPT_MODE, key);
  }


  public void encrypt(InputStream in, OutputStream out)  throws Exception{

    //leemos y ciframos la imagen
    out = new CipherOutputStream(out, ecipher);
    int numRead = 0;   
    while ((numRead = in.read(buf)) >= 0) {
      out.write(buf, 0, numRead);
    }
    out.close();
    
    //leemos y convertimos en un arreglo de bytes la imagen cifrada
    FileInputStream in2 = new FileInputStream("ciphertext.BMP");
    byte[] buffer = new byte[1024];
    int bytesRead;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    while ((bytesRead = in2.read(buffer)) != -1)
    {
        output.write(buffer, 0, bytesRead);
    }
    byte[] Encryptedmsg= output.toByteArray();
    output.close();
    
    //leemos la cabecera
    FileInputStream in3 = new FileInputStream("ASTRO2.BMP");
    byte[] header = new byte[54];
    int numReadb = 0;
    numReadb = in3.read(header);
      //ponemos la cabecera en el arreglo cifrado
      System.arraycopy(header, 0, Encryptedmsg, 0, 54);
        
    //escribimos la imagen cifrada pero con cabecera sin cifrar
    FileOutputStream out2 = new FileOutputStream("ciphertext.BMP");
    out2.write(Encryptedmsg, 0, Encryptedmsg.length);
    out2.close();
  }

  public void decrypt(InputStream in, OutputStream out)  throws Exception{
    
    //deciframos y escribimos 
    in = new CipherInputStream(in, dcipher);
    int numRead = 0;
    while ((numRead = in.read(buf)) >= 0) {
      out.write(buf, 0, numRead);
    }
    out.close();
    
    //leemos y convertimos en un arreglo de bytes la imagen cifrada
    FileInputStream in2 = new FileInputStream("cleartext2.BMP");
    byte[] buffer = new byte[1024];
    int bytesRead;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    while ((bytesRead = in2.read(buffer)) != -1)
    {
        output.write(buffer, 0, bytesRead);
    }
    byte[] DEncryptedmsg= output.toByteArray();    
    
    /*//leemos la cabecera
    FileInputStream in3 = new FileInputStream("ASTRO2.BMP");
    byte[] header = new byte[54];
    int numReadb = 0;
    numReadb = in3.read(header);
      System.out.println(header.length);
    //ponemos la cabecera en el arreglo cifrado
    for(int i = 0; i <54; i++){
        DEncryptedmsg[i] = header[i];
    }
    
    //escribimos la imagen cifrada pero con cabecera sin cifrar
    FileOutputStream out2 = new FileOutputStream("cleartext2.BMP");
    out2.write(DEncryptedmsg, 0, DEncryptedmsg.length);
    out2.close();*/

  }
  
  public static void main(String[] argv) throws Exception {
    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
    DesEncrypter encrypter = new DesEncrypter(key);
    encrypter.encrypt(new FileInputStream("ASTRO2.BMP"), new FileOutputStream("ciphertext.BMP"));
    encrypter.decrypt(new FileInputStream("ciphertext.BMP"), new FileOutputStream("cleartext2.BMP"));
  }
}