package projeto_03_grafite;
import java.util.Scanner;

class Pessoa{
    String nome;
    int idade;

    Pessoa(String nome,int idade){
        nome = this.nome;
        idade = this.idade;
    }

    public String toString(){
        return "Nome: " + nome + " ; " + "Idade: " + idade;
    }
}

public class Motoca {
    int potencia;
    int tempo;
    Pessoa pass = null;

    Motoca(int potencia, int tempo){
        potencia = this.potencia;
        tempo = this.tempo;
    }

    void subir(Pessoa pessoa){
        if (pass == null){
            pass = pessoa;
            System.out.println(pessoa.nome + "Vamos andar!");
        } else{
            System.out.println("Já tem gente na moto!");
        }
    }

    Pessoa descer(){
        if (pass==null){
            System.out.println("Não tem niguém na moto");
            return null;
        } else{
            Pessoa aux;
            aux = pass;
            pass = null;
            System.out.println("Acabou seu tempo, pode sair!");
            return aux;
        }
    }

    void compraTempo (int tempo){
        tempo = this.tempo;
        System.out.println("Adicionar: " + tempo + "por favor!");
    }

    void dirigir(){
        if (this.pass==null) {
            System.out.println("Não tem ninguém na moto");
        } else if(pass.idade > 10){
            System.out.println("Não pode!");
        } else if (this.tempo >= tempo){
            this.tempo -= tempo;
            System.out.println("Cuidado com o tempo!");
        } else{
            System.out.println("Não tem mais tempo!");
       }
    }
    
    public static void main(String[] args){
        Motoca motinha = new Motoca(1,0);
        Pessoa pass = new Pessoa("", 10);
        Scanner scanner = new Scanner(System.in);
        while(true){
            String line = scanner.nextLine();
            String[] ui = line.split(" ");
            if(ui[0].equals("end")){
                break;

            }else if(ui[0].equals("subir")){//init _nome
                motinha.subir(new Pessoa(ui[1], Integer.parseInt(ui[2])));

            }else if(ui[0].equals("Tempo")){
                motinha.compraTempo(Integer.parseInt(ui[1]));

            }else if(ui[0].equals("Descer")){
                motinha.descer();

            }else if(ui[0].equals("Andar")){
                motinha.dirigir();

            }else if(ui[0].equals("show")){
                System.out.println(motinha);

            }else{
                System.out.println("Comando invalido");
            }
        }
        scanner.close();

    }
}    
