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
public class Prova {
    ArrayList<Questao> questoes = new ArrayList<>();
    
    Prova(){
    }
    
    Prova(ArrayList<Questao> questoes){
        for(Questao questao : questoes){
            this.questoes.add(questao);
        }
    }
    
}
