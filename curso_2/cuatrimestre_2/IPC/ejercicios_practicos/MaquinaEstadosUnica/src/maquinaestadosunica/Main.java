/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaestadosunica;

import maquinaestadosunica.UI.LoginStateMachine;

/**
 *
 * @author marga
 */
public class Main {
    private static LoginStateMachine loginSM;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        loginSM=new LoginStateMachine();
    }
    
    public static LoginStateMachine getStateMachineLogin() {
        return loginSM;
    }
    
    /**
     * FALTARIA UN CLOSE PARA CERRAR LA MAQUINA DE ESTADOS, creo
     */
}
