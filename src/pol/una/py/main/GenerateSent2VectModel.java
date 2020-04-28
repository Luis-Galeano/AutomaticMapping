/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pol.una.py.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;



/**
 *
 * @author Luis Galeano
 */
public class GenerateSent2VectModel {
    
    
    public static void main(String[] args) {
      
        GenerateSent2VectModel g = new GenerateSent2VectModel();
        g.generarModeloSent2Vect();
        g.generarEspacioVectorial();
        
        
    }
    
    public void generarEspacioVectorial(){
        try {
            
            Runtime rt = Runtime.getRuntime();
            String[] cmd = { "sh", "/opt/s2v/espacioVectorial.sh"};
            Process p = rt.exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null){}
            
            
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
     public  void generarModeloSent2Vect() {
        
        try {
            Runtime rt = Runtime.getRuntime();
            String[] cmd = { "sh", "/opt/s2v/modelos2v.sh"};
            Process pr = rt.exec(cmd);
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null){}
            
        } catch (Exception e) {
            System.err.println(e);
        }
        
        
    }
}
