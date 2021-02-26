package projeto_08_busca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Contato {

  String nome;
  ArrayList<Telefone> fones;
 
    Contato(String nome) {
        this.nome=nome;
        fones = new ArrayList<>();
       
   }
     
    String getNome(){
    return this.nome;
   }

    public void setNome (String nome){
    this.nome = nome;
   }
   
    public void addFone(String number, String label){
       if(Telefone.validade(number)) {
           fones.add(new Telefone(label, number));
       } else {
           System.out.println("Erro : numero invalido");
       }

   }
   
    public void rmFone(int idx){
        fones.remove(fones.get(idx));
    }
           
           
    public String toString(){
        String out = "";
        for(int i=0; i<fones.size(); i++){
            Telefone tel = fones.get(i);
            out += "[" + i + ":" + tel + "]";
        }
         return getNome() + " " + out; 
        }
   }  

class Telefone {
    String label;
    String number;
   
   Telefone(String serial) {
      String array[] = serial.split(":");
      label = array[0];
      number = array[1];

   }
   
    String getLabel(){
    return this.label;
   }

    public void setLabel (String label){
    this.label = label;
   }
   
    static boolean validade(String number){
        String caracteres = "()-0123456789";
        for(int i=0;i<number.length();i++){
            if(caracteres.contains("" + number.charAt(i))){
                return true;
            }
        }
        return false;
    }
   
    public String toString(){
        return label + ":" + number;
    }
           
}

class ordenarPorNome implements Comparator <Contato>{
    public int compare(Contato a, Contato b) {
        return a.getNome().compareTo(b.getNome());
    }
    
}

public class Agenda {
    ArrayList<Contato> contatos;


    int acharContato (String nome){
       for(int i=0 ; i<contatos.size() ; i++){
           Contato c = contatos.get(i);
           if(c != null && c.getNome().equals(nome)){
               return i;
           }
    }   
       return -1;
    }

    void addContato (String nome, List<Telefone> fones) {
        Contato c = this.getContact(nome);
        if (c == null) {
            c = new Contato(nome);
            this.contatos.add(c);
        }
        for (Telefone fone : fones) {
            c.addFone(fone.getLabel(), fone.number);
    }
}
    

    boolean removeContato (String nome){
        int aux = acharContato(nome);
        if(aux == -1){
           System.out.println("Esse contato nao existe");
           return false;
        } else {
            this.contatos.remove(aux);
            return true;
        }
    }

    ArrayList<Contato> search (String pattern){
        ArrayList <Contato> conferem = new ArrayList<>();
        for(Contato contato : contatos){
            if(contato.toString().contains(pattern)){
                conferem.add(contato);
            }
        }
        return conferem;
    }

    ArrayList<Contato> getContacts(){
        return this.contatos;
    }

    Agenda() {
        contatos = new ArrayList<>();
    }
    
    Contato getContact (String nome){
        return null;
    }
    


    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner leitor = new Scanner(System.in);
        while(true){
            String line = leitor.nextLine();
            String ui[] = line.split(" ");
            if (ui[0].equals("Adicionar contato")){
                List <Telefone> fones = new ArrayList <>();
                for (int i = 2; i < ui.length; i++){
                    fones.add(new Telefone(ui[i]));
                }
                agenda.addContato(ui[1], fones);
                Collections.sort(agenda.getContacts(), new ordenarPorNome());
                System.out.println(agenda);
            } else if(ui[0].equals("Remover contato")){
                agenda.removeContato(ui[1]);
                System.out.println(agenda);
            } else if (ui[0].equals("Remover numero")){
                agenda.removeContato(ui[1]);
                System.out.println(agenda);
            } else if (ui[0].equals("agenda")){
                System.out.println(agenda);;
            } else if (ui[0].equals("buscar")){
                agenda.search(ui[1]);
                System.out.println(agenda);
            } else {
                System.out.println("fail: comando invalido");
                break;
            }
        }
        leitor.close();
    }
}

