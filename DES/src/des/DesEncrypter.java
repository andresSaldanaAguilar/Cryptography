import java.io.ByteArrayOutputStream;
import java.io.File;
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
import javax.swing.JFileChooser;

class DesEncrypter {
  byte[] buf = new byte[1024];
  Cipher ecipher;
  Cipher dcipher;
  DesEncrypter(SecretKey key) throws Exception{
    //estos parametros son para cifrados mas avanzados
    byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
    AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
    
    ecipher = Cipher.getInstance("DES/ECB/NoPadding");
    dcipher = Cipher.getInstance("DES/ECB/NoPadding");

    ecipher.init(Cipher.ENCRYPT_MODE, key);
    dcipher.init(Cipher.DECRYPT_MODE, key);
  }


  public void encrypt(InputStream in, OutputStream out,String path)  throws Exception{
 
    //leemos archivo, creamos un arreglo de bytes sin cabecera
    in = new FileInputStream(path);
    byte[] buffer = new byte[1024];
    int bytesRead;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    while ((bytesRead = in.read(buffer)) != -1)
    {
        output.write(buffer, 0, bytesRead);
    }
    byte[] Decryptedmsg= output.toByteArray();
    byte[] msg = new byte[Decryptedmsg.length-54];
    System.arraycopy(Decryptedmsg, 54, msg, 0, msg.length);
    output.close();
    
    //leeemos cabecera
    FileInputStream in1 = new FileInputStream(path);
    byte[] header = new byte[54];
    int numReadb = 0;
    numReadb = in1.read(header);
    
    //escribimos cabecera
    FileOutputStream out2 = new FileOutputStream("ciphertext.BMP");
    out2.write(header, 0, header.length);
    out2.close();
    
    //escribimos arreglo cifrado
    out = new CipherOutputStream(out, ecipher);
    out.write(msg, 0, msg.length);
    out.close();  

  }

  public void decrypt(InputStream in, OutputStream out, String path)  throws Exception{   
    
    //leemos archivo cifrado, creamos un arreglo de bytes sin cabecera
    in = new FileInputStream("ciphertext.BMP");
    byte[] buffer = new byte[1024];
    int bytesRead;
    ByteArrayOutputStream output = new ByteArrayOutputStream();
    while ((bytesRead = in.read(buffer)) != -1)
    {
        output.write(buffer, 0, bytesRead);
    }
    byte[] Encryptedmsg= output.toByteArray();
    byte[] msg = new byte[Encryptedmsg.length-54];
    System.arraycopy(Encryptedmsg, 54, msg, 0, msg.length);
    output.close(); 
    
    //leeemos cabecera
    FileInputStream in2 = new FileInputStream(path);
    byte[] header = new byte[54];
    int numReadb = 0;
    numReadb = in2.read(header);
    
    //escribimos cabecera
    FileOutputStream out2 = new FileOutputStream("cleartext.BMP");
    out2.write(header, 0, header.length);
    out2.close();
    
    //deciframos mensaje sin cabecera y escribimos 
    out = new CipherOutputStream(out, dcipher);
    out.write(msg, 0, msg.length);
    out.close(); 

  }
  
  public static void main(String[] argv) throws Exception {
    SecretKey key = KeyGenerator.getInstance("DES").generateKey();
    DesEncrypter encrypter = new DesEncrypter(key);
    
    JFileChooser jf = new JFileChooser();
    jf.requestFocus();  /*Lo manda a primer plano*/
    /*jf.setMultiSelectionEnabled(true) ... (1) */
    int r = jf.showOpenDialog(null);

    if (r==JFileChooser.APPROVE_OPTION) {
            /*File[] f = jf.getSelectedFiles()*/
            File f = jf.getSelectedFile();
            /*Realizar un for para que vaya leyendo cada uno de los datos del arreglo*/
            String nombre = f.getName();
            long tam = f.length(), enviados=0;
            String path = f.getAbsolutePath();
    
    encrypter.encrypt(new FileInputStream(path), new FileOutputStream("ciphertext.BMP",true),path);
    encrypter.decrypt(new FileInputStream("ciphertext.BMP"), new FileOutputStream("cleartext.BMP",true),path);
    }
  }
}