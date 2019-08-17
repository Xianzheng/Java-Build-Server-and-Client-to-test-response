//package RIM;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class RmiClient implements RmiServerIntf{
    public static void main(String args[]) throws Exception {
        try{
            RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
            PrimeNumber a= new PrimeNumber();
            System.out.println("the Biggest Number is ="+a.getPrimeNumber(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
        }catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("args[0] need to indicate the minimum number");
            System.out.println("args[1] need to indicate the maxmum number");
            System.out.println("Both of them can not be null");
            System.exit(1);
        }

    }

    @Override
    public String getMessage() throws RemoteException {
        return "client";
    }

    @Override
    public String sentMessage() throws RemoteException {
        return "client";
    }
}
