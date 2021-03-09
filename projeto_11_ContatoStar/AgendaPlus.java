package projeto_11_ContatoStar;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;

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
            if(!caracteres.contains("" + number.charAt(i))){
                throw new RuntimeException("Contato inválido");
            }
        }
        return true;
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

class Contato {

  String nome;
  ArrayList<Telefone> fones;
 
    Contato(String nome, ArrayList<Telefone> fones ) {
        this.nome=nome;
        fones = new ArrayList<>();
   }

   Contato(String nome) {
    this.nome=nome;
}
     
    String getNome(){
    return this.nome;
   }

    public void setNome (String nome){
    this.nome = nome;
   }
    
   
    public void addFone(String number, String label){
       if(!Telefone.validade(number)) {
        throw new RuntimeException("Contato inválido");
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

    boolean containsKey(String nome) {
        throw new UnsupportedOperationException("Nao encontrado"); 
    }

    public Contato getContact(String id) {
        return null;
    }
}  

class ContatoPlus extends Contato{
    boolean starred;

    ContatoPlus(String nome, ArrayList<Telefone> fones) {
        super(nome, fones);
	}

    boolean getStarred(){
        return starred;
    }
    
    public void setStarred(boolean valor){
        this.starred = valor;
    }

    public void setBookMark(boolean valor){
        this.starred = valor;
   }

}

class AgendaFav {
    Map <String, Contato> contatos;
    
    AgendaFav(){
        contatos = new TreeMap<>();
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

    void addContato (String nome, ArrayList<Telefone> fones) {
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
            throw new RuntimeException("Contato inexistente!");
        }
        return contato;
    }

}

public class AgendaPlus extends AgendaFav{
    Map <String, ContatoPlus> favoritos;

    
    public void bookMark(String id){
        Contato contato = this.getContact(id);
        if (contato instanceof ContatoPlus) {
            ContatoPlus novo = (ContatoPlus) contato;
            if(!novo.getStarred()){
                novo.setStarred(true);
                favoritos.put(id, novo);
            }
        }
    }
    
    public void unBookMark(String id){
        Contato contato = this.getContact(id);
        if (contato instanceof ContatoPlus) {
            ContatoPlus novo = (ContatoPlus) contato;
            if(novo.getStarred()){
            novo.setStarred(false);
            favoritos.remove(id);
            }
        } 
    }
    
    ArrayList<Contato> getBookMarks(){
       return new ArrayList<Contato>(favoritos.values());
    }

    AgendaPlus(){
        favoritos = new TreeMap<>();
        contatos = new TreeMap<>();
    }

    public static void main(String[] args) {
        AgendaPlus agenda = new AgendaPlus();
        Scanner leitor = new Scanner(System.in);
        while(true) {
            try{
                String line = leitor.nextLine();
                String [] ui = line.split(" ");
                if (ui[0].equals("End")) {
                    break;
                } else if (ui[0].equals("Adicionar contato")) {
                    ArrayList<Telefone> fones = new ArrayList<>();
                    for (int i = 2; i < ui.length; i++) {
                        fones.add(new Telefone(ui[i]));
                    } agenda.addContato((ui[1]), fones);
                    System.out.print(agenda);
                } else if(ui[0].equals("PAgenda")) {
                    System.out.println(agenda);
                }else if(ui[0].equals("Remover contato")) {
                    agenda.removeContato(ui[1]);
                    System.out.println(agenda);
                } else if(ui[0].equals("Procurar Contato")) {
                    System.out.print(agenda.search(ui[1]));
                } else if(ui[0].equals("Favoritar")) {
                    agenda.bookMark(ui[1]);
                    System.out.println(agenda);
                } else if(ui[0].equals("Retirar dos favoritos")) {
                    agenda.unBookMark(ui[1]);
                    System.out.println(agenda);
                } else if(ui[0].equals("Todos os favoritos")) {
                    System.out.print(agenda.getBookMarks());
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw e;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    leitor.close(); 
    }
}
   