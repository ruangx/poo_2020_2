package projeto_02_java;
import java.util.Scanner;

public class Leitor {
    boolean livroAcabado;
    int energia;
    int animacao;
    int QndLivros;

    Leitor (int QndLivros, int energia, int animacao){
        this.QndLivros = QndLivros;
        this.energia = energia;
        this. animacao = animacao;
    }

    void ler(){
            if(energia>2){
                System.out.println("Vamos ler!");
                this.energia = (energia-2);
            } else{
                System.out.println("Juliana, presido descansar!");
            }
    }

    void guardarLivro(){
        if(livroAcabado = true){
            System.out.println("Vamos para o próximo livro");
            animacao += animacao;
            QndLivros += 1;
        }
    }

    void indicar(){
        if (animacao > 6 || livroAcabado == true){
            System.out.println("Tenho que indicar para alguém");
            energia+=energia;
        } else{
            System.out.println("Aff, que livro chato");
            animacao = animacao - 5;
            energia = energia - 2;

        }
    }

    void comer(){
        if(energia<4){
            System.out.println("Preciso comer!");
            energia = energia + 4;
        }
    }

    public String toString(){
        return "Estado do leitor = Livro Acabados: " + QndLivros + " Energia: " + energia + " Animação: " + animacao;
    }


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Qual o nivel de energia?");
        int energia = scanner.nextInt();

        System.out.println("Quantos livros lidos?");
        int QndLivros = scanner.nextInt();

        System.out.println("Qual o nivel de animacao?");
        int animacao = scanner.nextInt();

        scanner.close();

        Leitor leitor = new Leitor(QndLivros, energia, animacao);

        System.out.println(leitor);
        leitor.ler();
        leitor.guardarLivro();
        leitor.indicar();
        leitor.ler();
        leitor.guardarLivro();
        leitor.comer();
        leitor.comer();
        leitor.ler();
        leitor.ler();
        System.out.println(leitor);


    }
}
