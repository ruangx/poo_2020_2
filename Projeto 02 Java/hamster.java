public class hamster{
        int energia;
        int fome;
        int carencia;
        
    hamster (int energia, int fome, int carencia){
        this.energia = energia;
        this.fome = fome;
        this.carencia = carencia;
    }

    void roda(){
        if(energia>2){
            energia -=1;
            fome+= 4;
            System.out.println("Bora correr na roda!");
        } else{
            System.out.println("To cansado presido dormir!");
        }
    }

    void dormir(){
        if(energia<2){
            energia +=3;
            carencia +=2;
            System.out.println("Dormindo, não pertube!");
        } else{
            System.out.println("Vamos animar!");
        }
    }

    void comer(){
        if(fome>3){
            fome += 2;
            System.out.println("Estou saciado, obrigado!");
        } else{
            System.out.println("Estou com fome, me da comida logo!");
        }
    }

    void carinho(){
        if (carencia>2) {
            carencia -= 3;
            System.out.println("Quero carinho! Vem logo!");
        } else{
            System.out.println("Me deixa quieto!");
        }
    }

    void banho(){
        if(carencia>2 || energia<2){
            carencia -= 2;
            energia += 2;
            System.out.println("Preciso de banho!");
        } else{
            System.out.println("Estou ótimo, obrigado!");
        }
    }

    public String toString(){
        return "Hamster = energia: " + energia + " fome: " + fome + " carencia: " + carencia;
    }

    public static void main(String[] args){
         hamster coisinha = new hamster(10,5,8);
         System.out.println(coisinha);

         coisinha.banho();
         coisinha.carinho();
         coisinha.roda();
         coisinha.roda();
         coisinha.roda();
         coisinha.comer();
         coisinha.comer();
         coisinha.carinho();
         coisinha.banho();

         System.out.println(coisinha);
    
    }
}