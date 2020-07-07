package wechat.client;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import wechat.communication.Client;


/**
 * Receptor de mensajes enviados al cliente desde el servidor
 * @author H
 */
public class ClientReceptor extends UnicastRemoteObject implements Client {

    private String name = null;
    private String token= null;
    private String tab  = "             ";
    private String you  = "(Tú)";
    private String[] colors ={"\u001B[40m", "\u001B[41m", "\u001B[42m", "\u001B[43m",
                              "\u001B[44m", "\u001B[45m", "\u001B[46m", "\u001B[47m"};
    private String resetcol = "\u001B[0m";
    private String separator= ":";

    private String colorYou(String name){
        if(!name.contains(this.name)) return getColor(name)+name+resetcol;
        else return name.replaceFirst(this.name, tab+getColor(name)+you+separator+resetcol);
    }

    private String getColor(String name){
        int index = 0;
        for(int j = 0; j < name.length()-1; j++) index += name.charAt(j);
        index = index % colors.length;
        return colors[index];
    }

    protected ClientReceptor(String n, String t, RMIClientSocketFactory rmicsf , RMIServerSocketFactory rmissf) throws RemoteException { // MISSING SOME ARGUMENTS
        super(0, rmicsf,rmissf); // PARENT CONSTRUCTOR METHOD
        this.name = n+separator;
        this.token= t;
    }

    @Override
    public void receiveLine(String tok, String linea) throws RemoteException{
        if(!this.token.equals(tok)) return; // IF IT IS NOT OUR SESSION TOKEN THEN QUIT
        String current_name = linea.substring(0, linea.indexOf(separator)+1);
        System.out.println(linea.replaceFirst(current_name, this.colorYou(current_name)));
    }
}