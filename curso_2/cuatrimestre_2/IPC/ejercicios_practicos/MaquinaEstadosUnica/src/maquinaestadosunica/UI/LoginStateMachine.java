/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquinaestadosunica.UI;

import javax.swing.JFrame;
import maquinaestadosunica.UI.*;
import maquinaestadosunica.dominio.Modelo;

/**
 *
 * @author marga
 */
public class LoginStateMachine {
    private JFrame currentState; 
    
    public LoginStateMachine() {
        
        start();    
    }
    
    void start() {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentState = new LoginWindow();
                currentState.setVisible(true);
            }
        });}
     
/*
   public void help() {
       currentState.setVisible(false); // si se desea ocultar
       currentState.dispose();   // si se desea destruir
       
       //transición
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentState = new HelpWindow();
                currentState.setVisible(true);
            }
        });
       
   }
 */
   public void recoverPassword() {
       currentState.setVisible(false); // si se desea ocultar
       currentState.dispose();   // si se desea destruir
       
       //transición
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentState = new PasswordRecoveryWindow();
                currentState.setVisible(true);
            }
        });
   }

    void close() {
       currentState.setVisible(false); // si se desea ocultar
       currentState.dispose();   // si se desea destruir
    }

    void back() {
       currentState.setVisible(false); // si se desea ocultar
       currentState.dispose();   // si se desea destruir
       // Para gestionar los backs podría convenir manejar un atributo que guarde el estado anterior
       // esta opción no hace eso, sería mejorable
       start();
    }
    
    
    
    
    
}
