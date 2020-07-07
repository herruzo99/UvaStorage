/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ipaddrtest;

import java.io.*;
import java.net.*;

public class IPAddrTest {

    public static void main(final String[ ] args) throws UnknownHostException {
        InetAddress ip=InetAddress.getByName("localhost");
        System.out.println("IpAddress: " + ip.toString());
        String name = ip.getHostName(); // nombre de dominio
        System.out.println("Host Nombre: " + name);
        name = ip.getCanonicalHostName(); // nombre de dominio completamente cualificado
        System.out.println("Host Nombre completo: " + name);
    }
}