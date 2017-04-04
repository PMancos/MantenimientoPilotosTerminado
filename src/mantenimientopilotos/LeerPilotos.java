/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mantenimientopilotos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author loren
 */
public class LeerPilotos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
  FileInputStream fis=null;
        ObjectInputStream ois=null;
        
       
      try {
          fis=new FileInputStream("pilotos.dat");
          ois=new ObjectInputStream(fis);
          
          Piloto p;
          
          while(true){
              p=(Piloto) ois.readObject();
              System.out.println(p.toString());
          }
      }catch (EOFException ex){
          System.out.println("---------------");
      } catch (FileNotFoundException ex) {
          Logger.getLogger(LeerPilotos.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
            Logger.getLogger(LeerPilotos.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ClassNotFoundException ex) {
            Logger.getLogger(LeerPilotos.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
            if(ois!=null) 
                ois.close();
            if(fis!=null) 
                fis.close();
            
            } catch (IOException ex) {
          Logger.getLogger(LeerPilotos.class.getName()).log(Level.SEVERE, null, ex);
      }
        }
    
    }
}
