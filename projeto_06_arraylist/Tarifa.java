package projeto_06_arraylist;
import java.util.Scanner;
import java.util.ArrayList;

class Operacao{
    private int id;
    String descricao;
    Float valor;
    Float saldo;

    Operacao (int id, String descricao, Float valor, Float saldo){
        id = this.id;
        descricao = this.descricao;
        valor = this.valor;
        saldo = this.saldo;
    }

    public String toString(){
        return id +": " + descricao + ": " + valor + ": " + saldo;
    }

}

public class Tarifa {
    int idNext; 
    private Float saldo;
    private int numero;
    ArrayList<Operacao> extrato;

    Tarifa(int numero){
        this.idNext = 0;
        this.saldo = 0.0f;
        this.numero = numero;
        extrato = new ArrayList<>();
    }

    void addOperacao(String descricao, Float valor){
        Operacao operc = new Operacao (idNext, descricao, valor, getSaldo());
        extrato.add(operc);
        idNext += 1;
    }

    ArrayList<Operacao> getExtrato(){
       for(int i = 0; i<extrato.size(); i++){
           System.out.println(extrato.get(i));
       }
       return extrato;
    }
    
     ArrayList<Operacao> getExtratoParcial(int qntd){
       for(int i = 0; i<extrato.size()- qntd; i++){
           System.out.println(extrato.get(i));
       }
       return extrato;
    }
    
    public ArrayList setExtrato() {
        this.extrato = extrato;
        return extrato;
    }
    
    public float setsaldo(float valor){
        this.saldo = valor;
        return saldo;
    }
    
    public float getSaldo(){
      return saldo;  
    }
    
    public void setNumero(int numero){
        if(numero==this.numero){
          System.out.println("Conta existente, tente novamente");  
        }else{
            this.numero=numero;
        }
    }
    
     public int getNumero(){
      return numero;  
    }
     
    public boolean depositar(Float valor){
        if (valor > 0){
            this.saldo += valor;
            addOperacao("Deposito", valor);
            System.out.println("Seu saldo é de: " + saldo);
            return true; 
        } else{
            System.out.println("Deposito invalido");
            return false;
        }
    }

    public boolean sacar(Float valor) {
        if (valor > 0 && valor < saldo){
            this.saldo -= valor;
            addOperacao("Sacar", -valor);
            System.out.println("Seu saldo � de: " + saldo);
            return true;
        } else{
            System.out.println("Saque invalido");
            return false;
        }
    }

    public boolean tarifas(Float valor){
        if(valor > 0 && valor < saldo){
            this.saldo -= valor;
            addOperacao("tarifas", -valor);
            System.out.println("Seu saldo � de: " + saldo);
            return true;
        } else{
            System.out.println("Valor Invalido!");
            return false;
        }
    }

    public boolean extornar(int indice) {
        float p=0;
        if(indice>0){
            if(indice <= extrato.size() && extrato.get(indice).descricao.equals("Tarifas")){
                p = extrato.get(indice).valor;
                addOperacao("Extorno", p*-1);
                return true;
            }else{
                System.out.println("Extorno Invalido!");
                return false;
            }
        }
        return false;
        
    }
     public String toString(){
        return "Conta: " + numero + "/" + "Saldo:" + saldo;
    }
    
     public static void main(String[] args) {
       
        Scanner scanner  = new Scanner(System.in); 
        Tarifa poupanca = new Tarifa(0);
        System.out.println("Qual operacao deseja realizar ?");
      while(true){
          String line = scanner.nextLine();
          String[] m=line.split(" ");
          if(m[0].equals("end")){ 
              break;
          } else if (m[0].equals("Conta")){
              poupanca.setNumero(Integer.parseInt(m[1]));
              poupanca = new Tarifa(Integer.parseInt(m[1]));
              System.out.println(poupanca);
          } else if (m[0].equals("Deposito")){
              poupanca.depositar(Float.parseFloat(m[1]));
              System.out.println(poupanca);
          } else if (m[0].equals("Saque")){
              poupanca.sacar(Float.parseFloat(m[1]));
              System.out.println(poupanca);
          } else if (m[0].equals("Tarifas")){
              poupanca.tarifas(Float.parseFloat(m[1]));
              System.out.println(poupanca);
          } else if (m[0].equals("Extrato")){
              poupanca.getExtrato();
              System.out.println(poupanca);
          } else if (m[0].equals("Extrato parcial")){
              poupanca.getExtratoParcial(Integer.parseInt(m[1]));
              System.out.println(poupanca);
          }else if (m[0].equals("Extornar")){
              poupanca.extornar(Integer.parseInt(m[1]));
              poupanca.getExtrato();
              System.out.println(poupanca);
          }  else {
             System.out.println("Comando invalido");
         }
         
        }
            scanner.close();
    }
} 