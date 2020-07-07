package wechat.server;

import java.security.SecureRandom;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

import wechat.communication.Client;
import wechat.communication.Messenger;


/**
 * Implementación de la interfaz Messenger utilizando conexiones SSL (MessengerWeChat)
 * @author H
 */
public class MessengerWeChat extends UnicastRemoteObject implements Messenger{

    private HashMap<String, String> sessions = new HashMap<String, String>();

    public MessengerWeChat(
    		RMIClientSocketFactory rmicsf,  /* factoría de socket de cliente */
            RMIServerSocketFactory rmissf) /* factoría de socket de servidor */
                    throws java.rmi.RemoteException {
    	 super(0, rmicsf, rmissf);
    }

    private String generateRandomToken(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes.toString();
    }
    
    @Override
    public String login(String nombre, String password) throws RemoteException{
    	String usuario= nombre + "55555";
    	if(password.contentEquals(usuario)) {
    		String token = generateRandomToken();
    		sessions.put(nombre, token);
    		return token;
    	}else {
    		return null;
    	}
    }
    @Override
    public void write(String token, String groupName, String newLine) throws RemoteException, FileNotFoundException {
        if(!this.checkToken(token)) return; // IF TOKEN IS NOT KNOWN THEN RETURN   
        PrintWriter out = new PrintWriter(new FileOutputStream(groupName, true));
        out.println(newLine);
        out.close();
    }

    @Override
    public void openGroup(String token, String groupName, Client iClient) throws RemoteException, FileNotFoundException {
        if(!this.checkToken(token)) return; // IF TOKEN IS NOT KNOWN THEN RETURN
        Runnable watcher = () -> {
            try {

                this.checkOrCreateGroup(groupName);

                BufferedReader br = new BufferedReader(new FileReader(groupName));
                int numLineas= this.blockRead(token, br, iClient);

                while(true){
                    numLineas = this.continuousReading(token, groupName, iClient, numLineas);
                    Thread.sleep(2000);
                }
            } catch (IOException e) {
                System.out.println("exception controlled: IOEx");
                e.printStackTrace();
            } catch (InterruptedException e) {
                System.out.println("exception controlled: IEx");
                e.printStackTrace();
            }catch(Exception e){
                System.out.println("exception controlled: Ex");
                e.printStackTrace();
            }
        };
        new Thread(watcher, "Sirviente tailf").start();
    }
    private boolean checkToken(String token){
        boolean b = sessions.containsValue(token);
        if(!b) System.err.println("invalid token "+token);
        return b;
    }
    private void checkOrCreateGroup(String groupName) throws IOException {
        File f = new File(groupName);
        if(!f.exists()){
            Path newFilePath = Paths.get(groupName);
            Files.createFile(newFilePath);
        }
    }   
    private int blockRead(String token, BufferedReader br, Client iClient) throws IOException {
        String linea = null;
        int numReadLineas= 0;
        while((linea = br.readLine()) != null) {
            iClient.receiveLine(token, linea);
            numReadLineas++;
        }
        return numReadLineas;
    }
    private int continuousReading(String token, String groupName, Client iClient, int numLines) throws IOException {
        BufferedReader brx = new BufferedReader(new FileReader(groupName));
        int reachedLines =0;
        while(reachedLines++ < numLines) brx.readLine();
        return numLines + this.blockRead(token, brx, iClient);
    }
 }