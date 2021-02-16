package projeto_07_topic;
import java.util.ArrayList;
import java.util.Scanner;

class Pass{
    String nome;
    int idade;
    boolean idoso;
    
   Pass( String nome, int idade) {
        this.nome=nome;
        this.idade=idade;
}

String getNome(){
    return this.nome;
}

public void setNome (String nome){
    this.nome = nome;
}

int getIdade(){
    return idade;
}

public void setIdade(int idade){
    this.idade = idade;
}

public boolean idoso(){
    if (getIdade() < 60){
        return false;
    } else{
        return true;
    }
}
public String toString(){
    return nome + "/" + idade;
}
}

public class Topic {
    ArrayList<Pass>cadeiras = new ArrayList<>();
    int qtdPref;
    int topicTam;
    Pass tamanho;
      
    Topic(int qtdPref, int topicTam){
        this.qtdPref=qtdPref;
        this.topicTam=topicTam;
        cadeiras= new ArrayList<>();

        for(int i = 0; i < topicTam; i++){
        cadeiras.add(null);
        }
    }
     
    public String toSting(){
        return "Total de cadeiras:" + topicTam + "/" + "Cadeiras preferenciais:" + qtdPref;
    }
     
   public String toString(){
       String vet = "[";
       for(int i = 0; i < qtdPref; i++){
                   if(cadeiras.get(i) == null){
                       vet += "@";
                    } else {
                       vet += "@" + cadeiras.get(i) + " ";
                    }
            }
           
       for(int i = qtdPref; i < cadeiras.size(); i++){
                   if(cadeiras.get(i) == null){
                       vet += "="; 
                    } else {
                       vet += "=" + cadeiras.get(i) + " ";
                    }
                   
                }
       return vet += " ]";
    }
     
    Pass buscarPassageiro (String id) {
        for (int i = 0; i < cadeiras.size(); i++) {
            tamanho = cadeiras.get(i);

            if (tamanho != null && tamanho.getNome().equals(id)) {
                return tamanho;
            }
        } return null;
    }
    
    int buscarCadeira(int c1, int c2){
        for(int i = c1; i < c2; i++){
            if(cadeiras.get(i) == null){
                return i;
            }
        }
        return -1;
    }


    public void subir(Pass tamanho){
        int full = -1;

        if(buscarPassageiro(tamanho.getNome()) == null){
            if(tamanho.idoso()){
                full = this.buscarCadeira(0, cadeiras.size());
            } else {
                full = this.buscarCadeira(this.qtdPref, cadeiras.size());
                if(full == -1){
                    full = this.buscarCadeira(0, this.qtdPref);
                }
            }

            if (full == -1){
                System.out.println("Topic cheira, espera a próxima, por gentileza!");
            } else{
                cadeiras.set(full, tamanho);
            } 
        } else{
            System.out.println("Já está na topic!");
        }

    }    

    Pass descer(String nome){
        Pass x = buscarPassageiro(nome);
        if(x == null){
            System.out.println("Passageiro não se encontra na topic!");
        } else {
            cadeiras.remove(x);
            cadeiras.add(null);
        }
        return tamanho;
    } 
 
    public static void main(String[] args) {
       Topic luxo = new Topic (0,0);
       Scanner scanner = new Scanner(System.in);
       while (true) {
        String line = scanner.nextLine();
        String [] ui = line.split(" ");
        if (ui[0].equals("fim")) {
            break;
        } else if (ui[0].equals("Nova")) {
            luxo = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
            System.out.println(luxo);
        } else if (ui[0].equals("Entrar")) {
        luxo.subir(new Pass(ui[1], Integer.parseInt(ui[2])));
            System.out.println(luxo);
        } else if (ui[0].equals("Sair")) {
            luxo.descer(ui[1]);
            System.out.println(luxo);
        } else {
            System.out.println("comando inválido");
        }            
    } scanner.close();
}
}
    

