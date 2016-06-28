/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.utfpr.alvaras.leitor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.utfpr.alvaras.control.Utils;
import org.utfpr.alvaras.model.Alvara;
import org.utfpr.alvaras.model.Endereco;

/**
 *
 * @author henrique
 */
public class LerCSV {
    public static void main(String ... args){
        FileInputStream fis = null;
        try {
            File f = new File("Alvaras-Base_de_Dados.CSV");
            fis = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new FileReader(f));
            
            reader.readLine();
            ArrayList<Alvara> alvaras = ler(reader);
            
            int k = 0;
            for(Alvara a : alvaras){
                k++;
                
                System.out.println(a);
                
                if(k >= 10) break;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LerCSV.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LerCSV.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(LerCSV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ArrayList<Alvara> ler(BufferedReader reader) {
        try {
            ArrayList<Alvara> alvaras = new ArrayList<>();

            String s;

            while ((s = reader.readLine()) != null) {
                String[] splited = s.split(";");
                
                if(splited.length == 15){
                    Alvara a = new Alvara();
                    a.setNomeEmpresarial(splited[0]).setDataInicioDeAtividade(Utils.getCalendarFor(splited[1])).setNumeroDoAlvara(splited[2]);
                    a.setDataEmissao(Utils.getCalendarFor(splited[3])).setDataExpiracao(Utils.getCalendarFor(splited[4])).setAtividadePrincipal(splited[5]);
                    a.setAtividadeSecundaria(splited[6]).setAtividadeSecundaria2(splited[7]);
                    
                    Endereco end = new Endereco();
                    end.setLogradouro(splited[8]);
                    end.setNumero(splited[9]);
                    end.setUnidade(splited[10]);
                    end.setAndar(splited[11]);
                    end.setComplemento(splited[12]);
                    end.setBairro(splited[13]);
                    end.setCep(splited[14]);
                    
                    a.setEndereco(end);
                    
                    alvaras.add(a);
                }
            }
            
            return alvaras;
        } catch (IOException ex) {
            Logger.getLogger(LerCSV.class.getName()).log(Level.SEVERE, null, ex);

            return null;
        }
    }
}
