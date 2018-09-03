/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ep_ie;

/**
 *
 * @author gabri
 */
public class Questao {
    double a;
    double b;
    int indice;
    
    public Questao (double disc, double dif){
        this.a = disc;
        this.b = dif;
    }

    Questao(double disc, double dif, int indice) {
        this.a = disc;
        this.b = dif;
        this.indice = indice;
    }

    @Override
    public String toString() {
        //String a = Double.toString(this.a);
        String ind = Integer.toString(this.indice);
        //String b = Double.toString(this.b);
        //a = a.concat(" ");
        return ind;
    }
    
    
}
