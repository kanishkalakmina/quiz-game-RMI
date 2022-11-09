 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Server.RemoteInterface;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Lakmina
 */
public class RemoteConnection {
    
    private final Registry reg;
    private final RemoteInterface server;
    
    /*
    # Used to create a RMI server connection
    # Encapsulation
    */
    
    public RemoteConnection() throws RemoteException, NotBoundException{
     
           reg = LocateRegistry.getRegistry("localhost",1099);
           server = (RemoteInterface) reg.lookup("rmi://localhost/MyService");
       
    }
    
    public RemoteInterface getServer(){
      
        return  server;
    }
}
