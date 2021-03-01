package projeto_09_favoritos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

class Contato {

  String nome;
  boolean starred;
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
    
    boolean getStarred(){
        return starred;
    }
    
    public void setStarred(boolean valor){
        this.starred = valor;
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
    
    public void setBookMark(boolean valor){
         this.starred = valor;
    }
           
           
    public String toString(){
        String out = "";
        for(int i=0; i<fones.size(); i++){
            Telefone tel = fones.get(i);
            out += "[" + i + ":" + tel + "]";
        }
         return getNome() + " " + out; 
        }

    boolean containsKey(String nome) {
        throw new UnsupportedOperationException("Nao encontrado"); 
    }
   }  

class Telefone {
    String label;
    String number;
   
    Telefone(String label, String number) {
        this.label=label;
        this.number=number;
     }
    
    public Telefone (String combo) {
        String array[] = combo.split(":");
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

public class AgendaFav {
    Map < String, Contato > contatos;
    Map < String, Contato > favoritos;
    
    AgendaFav() {
        contatos = new TreeMap<>();
        favoritos = new TreeMap<>();
    }
    
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
        Contato contato = contatos.get(nome);
        if(!contato.containsKey(nome)){
            contatos.put(nome, new Contato(nome));
        }
        for(Telefone fone : fones){
            contato.addFone(fone.label, fone.number);
        }
    }
    

    boolean removeContato (String nome){
       Contato contato = getContact(nome);
       contatos.remove(nome);
       return true;
    }

    ArrayList<Contato> search (String pattern){
        ArrayList <Contato> conferem = new ArrayList<>();
        for(Contato contato : contatos.values()){
            if(contato.toString().contains(pattern)){
                conferem.add(contato);
            }
        }
        return conferem;
    }

    ArrayList<Contato> getContacts(){
        return new ArrayList<Contato>(contatos.values());
    }

    Contato getContact (String nome){
        Contato contato = contatos.get(nome);
        if(contato == null){
            System.out.println("Contato inexistente");
        }
        return contato;
    }
    
    public void bookMark(String id){
        Contato contato = getContact(id);
        if(!contato.getStarred()){
            contato.setStarred(true);
            favoritos.put(id, contato);
        }
    }
    
    void unBookMark(String id){
         Contato contato = getContact(id);
        if(contato.getStarred()){
            contato.setStarred(false);
            favoritos.remove(id);
        } 
    }
    
    ArrayList<Contato> getBookMarks(){
       return new ArrayList<Contato>(favoritos.values());
    }


    public static void main(String[] args) {
        AgendaFav agenda = new AgendaFav();
        Scanner leitor = new Scanner(System.in);

        while (true) {
            String line = leitor.nextLine();
            String [] ui = line.split(" ");
            if (ui[0].equals("end")) {
                break;
            } else if (ui[0].equals("Adicionar contato")) {
                List <Telefone> fones = new ArrayList <>();
                for (int i = 2; i < ui.length; i++) {
                    fones.add(new Telefone(ui[i]));
                } agenda.addContato((ui[1]), fones);
                System.out.print(agenda);
            } else if (ui[0].equals("Agenda")) {
                System.out.print(agenda);
            } else if (ui[0].equals("Remover contato")) {
                agenda.removeContato(ui[1]);
                System.out.println(agenda);
            } else if (ui[0].equals("Buscar contato")) {
                agenda.search(ui[1]);
                System.out.print(agenda);
            } else if (ui[0].equals("Favoritar")) {
                agenda.bookMark(ui[1]);
                System.out.println(agenda);
            } else if (ui[0].equals("Remover de favoritos")) {
                agenda.unBookMark(ui[1]);
                System.out.println(agenda);
            } else if (ui[0].equals("Favoritos")) {
                System.out.println(agenda.getBookMarks());
            }   
        } leitor.close();
    }
    }


