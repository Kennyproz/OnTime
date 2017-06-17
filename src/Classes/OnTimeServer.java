package Classes;

import fontyspublisher.*;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Ken on 11-6-2017.
 */
public class OnTimeServer {

    public static int portNumber = 1099;
    private static String bindingName = "publisher";

    /**
     * @param args the command line arguments
     * @throws java.rmi.RemoteException
     */
    public static void main(String[] args) throws RemoteException {
        RemotePublisher remotePublisher = new RemotePublisher();

        Registry registry = LocateRegistry.createRegistry(portNumber);
        registry.rebind(bindingName, remotePublisher);

        System.out.println("Remote OnTime publisher registered.");
        System.out.println("Port number: " + portNumber);
        System.out.println("Binding name: " + bindingName);

    }
}
