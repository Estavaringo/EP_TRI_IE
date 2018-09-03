/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep_ie;

import java.util.ArrayList;

/**
 *
 * @author gabri
 */
public class Aluno {

    double habilidade;
    
    Teste teste10 = new Teste();
    Teste teste20 = new Teste();
    Teste teste50 = new Teste();
    Teste teste100 = new Teste();
    int[] acertos = new int[100]; 
    
    Aluno(){
        
    }
    
    
    Aluno(double hab) {
        this.habilidade = hab;
    }
}
