/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sha.md5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JFileChooser;

/**
 *
 * @author andressaldana
 */
public class SHAMD5 {

    /**
     * @param args the command line arguments
     */
    public static String SHA1(String path,char op,String type) throws NoSuchAlgorithmException{
        
        MessageDigest digest = MessageDigest.getInstance(type);
        String content = readFile(path);       

        if(op == '1'){
            //Creates the digest and parse it on hexadecimal
            byte[] encodedhash = digest.digest(content.getBytes(StandardCharsets.UTF_8));
            String hex = bytesToHex(encodedhash);
            content += "\t"+hex;  
            writeFile(content);
            
            return hex;
        }
        else{
            //Creates the digest and parse it on hexadecimal
            String [] arr=getHash(content);
            byte[] encodedhash = digest.digest(arr[0].getBytes(StandardCharsets.UTF_8));
            String hex = bytesToHex(encodedhash);
            System.out.println(arr[1] + " = " + hex);
            
            return hex;
        }
        
    }
    
    public static String readFile(String path){
        BufferedReader br = null;
        String content = "";
        
        try {
            br= new BufferedReader(new FileReader(path));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            content = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
	} finally {
            try{
                br.close();
            }catch (IOException ex) {
		ex.printStackTrace();
            }
        }
        return content;
    }
       
    public static void writeFile(String content){
        FileWriter fw = null;
        BufferedWriter bw = null;
        
        try{
            File file = new File("/Users/andressaldana/Documents/Github/Cryptography/SHA-MD5/h.txt");

            if (!file.exists()) {
               file.createNewFile();
            }

            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for(int i = 0; i < hash.length; i++){
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
        }
        return hexString.toString();
    }
    
    public static String[] getHash(String msg){
        String[] arr= msg.split("\t");
        return arr;
    }
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        File file = new File("/Users/andressaldana/Documents/Github/Cryptography/SHA-MD5");
        JFileChooser jf = new JFileChooser(file);
        jf.requestFocus();
        int r = jf.showOpenDialog(null);

        if (r==JFileChooser.APPROVE_OPTION) {
            File f = jf.getSelectedFile();
            String nombre = f.getName();
            String path = f.getAbsolutePath();
            SHA1(path,'2',"SHA-1");
        }
    }
    
}
