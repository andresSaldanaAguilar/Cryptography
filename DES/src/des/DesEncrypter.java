package des;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFileChooser;

class DesEncrypter {
    byte[] buf = new byte[1024];
    Cipher ecipher;
    Cipher dcipher;
    String path;
  
  
    DesEncrypter(String path) throws Exception{
        this.path= path;  
    }

    /*encrypt*/
    void DES_ECB(String secret) throws Exception{
        /*ECB*/
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(56, sr); // 192 and 256 bits may not be available
        SecretKey key = kgen.generateKey();

        ecipher = Cipher.getInstance("DES/ECB/NoPadding");
        dcipher = Cipher.getInstance("DES/ECB/NoPadding");

        ecipher.init(Cipher.ENCRYPT_MODE, key);
        dcipher.init(Cipher.DECRYPT_MODE, key);

    }
    
    void DES_CBC(String secret) throws Exception{
        /*CBC*/
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(56, sr); // 192 and 256 bits may not be available
        SecretKey key = kgen.generateKey();
        
        byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        ecipher = Cipher.getInstance("DES/CBC/NoPadding");
        dcipher = Cipher.getInstance("DES/CBC/NoPadding");

        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        
    }
    
    void DES_CFB(String secret) throws Exception{
        /*CFB*/
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(56, sr); // 192 and 256 bits may not be available
        SecretKey key = kgen.generateKey();
        
        byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        ecipher = Cipher.getInstance("DES/CFB/NoPadding");
        dcipher = Cipher.getInstance("DES/CFB/NoPadding");

        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        
    }
    
    void DES_OFB(String secret) throws Exception{
        /*OFB*/
        KeyGenerator kgen = KeyGenerator.getInstance("DES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(56, sr); // 192 and 256 bits may not be available
        SecretKey key = kgen.generateKey();
        
        byte[] iv = new byte[] { (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07, 0x72, 0x6F, 0x5A };
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        ecipher = Cipher.getInstance("DES/OFB/NoPadding");
        dcipher = Cipher.getInstance("DES/OFB/NoPadding");

        ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
        dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
        
    }


    void AES_CBC(String secret) throws Exception{
        /*CFB*/
        byte[] iv = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF }; 
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();


        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        ecipher = Cipher.getInstance("AES/CBC/NoPadding");
        dcipher = Cipher.getInstance("AES/CBC/NoPadding");
        ecipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        dcipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
    }

    void AES_ECB(String secret) throws Exception{
        /*CFB*/
        //byte[] iv = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF }; 
        //IvParameterSpec ivSpec = new IvParameterSpec(iv);

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();


        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        ecipher = Cipher.getInstance("AES/ECB/NoPadding");
        dcipher = Cipher.getInstance("AES/ECB/NoPadding");
        ecipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        dcipher.init(Cipher.DECRYPT_MODE, skeySpec);
    }
    
    void AES_CFB(String secret) throws Exception{
        /*CFB*/
        byte[] iv = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF }; 
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();


        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        ecipher = Cipher.getInstance("AES/CFB/NoPadding");
        dcipher = Cipher.getInstance("AES/CFB/NoPadding");
        ecipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        dcipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
    }
    
    void AES_OFB(String secret) throws Exception{
        /*OFB*/
        byte[] iv = new byte[] { 0x0, 0x1, 0x2, 0x3, 0x4, 0x5, 0x6, 0x7, 0x8, 0x9, 0xA, 0xB, 0xC, 0xD, 0xE, 0xF }; 
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(secret.getBytes());
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] raw = skey.getEncoded();


        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        ecipher = Cipher.getInstance("AES/OFB/NoPadding");
        dcipher = Cipher.getInstance("AES/OFB/NoPadding");
        ecipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        dcipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
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
      String []arr = path.split("/");
      String filename = arr[arr.length-1];
      FileOutputStream out2 = new FileOutputStream("cipher"+filename);
      out2.write(header, 0, header.length);
      out2.close();

      //escribimos arreglo cifrado
      out = new CipherOutputStream(out, ecipher);
      out.write(msg, 0, msg.length);
      out.close();  

    }

    public void decrypt(InputStream in, OutputStream out, String path)  throws Exception{   

      //leemos archivo cifrado, creamos un arreglo de bytes sin cabecera
      String []arr = path.split("/");
      String filename = arr[arr.length-1];
      in = new FileInputStream(filename);
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
      FileOutputStream out2 = new FileOutputStream(filename.replace("cipher", "clear"));
      out2.write(header, 0, header.length);
      out2.close();

      //deciframos mensaje sin cabecera y escribimos 
      out = new CipherOutputStream(out, dcipher);
      out.write(msg, 0, msg.length);
      out.close(); 

    }
    
    public static void main(String args[]) throws Exception{
        String path = "/Users/andressaldana/Documents/Github/Cryptography/DES/ciphertext.BMP";
        String path1 = "/Users/andressaldana/Documents/Github/Cryptography/DES/ASTRO.BMP";
        /*
        DesEncrypter encrypter = new DesEncrypter(path);
        SecretKey k =encrypter.DES_ECB();
        encrypter.encrypt(new FileInputStream(path1), new FileOutputStream("ciphertext.BMP",true),path1);
        encrypter.DES_ECB(k);
        encrypter.decrypt(new FileInputStream("ciphertext.BMP"), new FileOutputStream("cleartext.BMP",true),path);  
        */
    }
}
