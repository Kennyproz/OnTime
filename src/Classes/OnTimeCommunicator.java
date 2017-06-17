package Classes;

import Controllers.OnTimeController;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForDomain;
import fontyspublisher.IRemotePublisherForListener;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ken on 11-6-2017.
 */
public class OnTimeCommunicator extends UnicastRemoteObject implements IRemotePropertyListener {
    //OnTime
    private final OnTimeController onTime;


    //Remote publisher
    private IRemotePublisherForDomain publisherForDomain;
    private IRemotePublisherForListener publisherForListener;
    private static String bindingName = "publisher";
    private boolean isConnected = false;

    //Thread pool
    private final int threads = 10;
    private ExecutorService threadpool = null;

    public OnTimeCommunicator(OnTimeController onTime) throws RemoteException {
        this.onTime = onTime;
        threadpool = Executors.newFixedThreadPool(threads);
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException {
        String property = propertyChangeEvent.getPropertyName();

        if (property.equals("Message")){
            Message message = (Message)propertyChangeEvent.getNewValue();
            onTime.requestMessage(property, message);
        }
        else {
            System.out.println("Something went wrong with propertyChange");
        }


    }

    public void connectToPublisher(String adress, int portNumber) {
        try {
            Registry registry = LocateRegistry.getRegistry(adress, portNumber);
            publisherForDomain = (IRemotePublisherForDomain) registry.lookup(bindingName);
            publisherForListener = (IRemotePublisherForListener) registry.lookup(bindingName);
            isConnected = true;
            System.out.println("Connection made with remote OnTime publisher.");
        } catch (RemoteException | NotBoundException ex) {
            isConnected = false;
            System.err.println("Ooops something went wrong");
            ex.printStackTrace();
        }
    }

    public void register(String property) {
        if (isConnected) {
            try {
                publisherForDomain.registerProperty(property);
            } catch (RemoteException re) {
                System.out.println("Failed to register");
            }
        }
    }

    public void subscribe(String property) {
        if (isConnected) {
            final IRemotePropertyListener remotePropertyListener = this;
            threadpool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        publisherForListener.subscribeRemoteListener(remotePropertyListener, property);
                    } catch (RemoteException re) {
                        re.printStackTrace();
                    }
                }
            });
        }
    }

    public void broadcast(String property, Message message) {
        if (isConnected) {
            threadpool.execute(() -> {
                try {
                    publisherForDomain.inform(property, null, message);
                } catch (RemoteException re) {
                    re.printStackTrace();
                    System.out.println("Something went wrong with broadcast");
                }
            });
        }
    }
    public void stop(){
        if (isConnected){
            try{
                publisherForListener.unsubscribeRemoteListener(this,null);
            }
            catch (RemoteException re){
                System.out.println("Something went wrong with Stopping");
            }
        }
        try{
            UnicastRemoteObject.unexportObject(this,true);
        }
        catch (RemoteException re){
            System.out.println("Tsja stopppen lukte niet echt");
        }
    }
}




