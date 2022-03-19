import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner leitor = new Scanner(System.in);

        //Criando os bonecos
        Enemy enemy = new Enemy("", 100, 20);

        You player = new You("", 100, 5, 2, 2, 2);
        
        System.out.println("-HAHAHA VAMOS JOGAR UM JOGO?!-\n"+
        "-O nome desse jogo é 'JOGO'.\n"+
        "-Você está enfrentando um inimigo que está escondido em uma barricada, assim como você.\n"+
        "-Ambos possuindo CEM de energia (Vidinha <3)."+
        "-A cada rodada, terá duas escolhas principais:\n"+
        "-Atirar, tirando DEZ pontos de energia do inimigo. Se o inimigo estiver escondido, você recupera DOIS pontos de energia."+
        "-Esconder te dá DOIS pontos de energia e impede o inimigo de lhe atingir e lhe tirar DEZ pontos de energia.\n"+
        "-As ações do inimigo serão aleatórias a cada rodada."+
        "-Você terá também um total de DUAS granadas e DUAS poções de energia."+
        "-Cada poção de energia lhe devolverá CINCO pontos de energia."+
        "-Já a granada, tirará VINTE de energia do inimigo, porém causará CINCO de dano em você.\n");
        
        //Dando nome ao protagonista com o que o jogador digitou
        System.out.println("Primeiramente, qual o nome do seu personagem?");
        String nome = leitor.nextLine();
        player.youName = nome;

        //Dando nome ao inimigo com o que o jogador digitou
        System.out.println("E o nome do seu inimigo?");
        String nome2 = leitor.nextLine();
        enemy.enemyName = nome2;

        System.out.println("...\n-O jogo começou!-\n" + player.youName + " vs " + enemy.enemyName+"\n...");
        
        //Início real do jogo, só vai parar quando um dos dois personagens perder toda a vida
        while ((player.youEnergy > 0) || (enemy.enemyEnergy > 0)) 
        {

            if (player.youEnergy > 100) 
            {
                player.youEnergy = 100;
            }
            
            System.out.println("-Digite '1' para atirar, '2' para se esconder, '3' para usar poção ou '4' para jogar granada-");
            int youEscolha = leitor.nextInt();

        //Definir o movimento aleatório no inimigo. Vai gerar um número aleatório entre 0 e 1
        
            Random gerador = new Random();
            int enemyPlay = gerador.nextInt(2);
        // Esse "2" entre os parênteses, serve para definir que o random apenas escolherá números entre 0 e 1

        //Analisa escolha do jogador 1 atira 2 esconde
            if (youEscolha == 1) 
            {
            //Se os dois atirarem
                if(enemyPlay == 1)
                {
                    player.youEnergy = player.youEnergy - 10;
                    enemy.enemyEnergy = enemy.enemyEnergy -10;
                    System.out.print("Os dois atiraram, cada um perdeu DEZ pontos de energia.-\n");
                    System.out.println("Sua energia: "+ player.youEnergy);
                    System.out.println("Energia do inimigo: "+ enemy.enemyEnergy);
                }
                else
                {
                //Se o jogador atira e o inimigo se esconde
                    System.out.print("-Você atirou, porém, o "+enemy.enemyName+" se escondeu.-\n");
                    player.youEnergy = player.youHealHide + player.youEnergy;
                    if (player.youEnergy > 100) 
                    {
                        player.youEnergy = 100;
                    }
                    System.out.println("-Sua energia: "+ player.youEnergy);
                }
            }

        //Se o jogador se esconder
            if(youEscolha == 2)
            {
            
                System.out.print("-Você se escondeu e recuperou DOIS pontos de energia.-");
                player.youEnergy = player.youEnergy + player.youHealHide;
            
        //Impede que o jogador chegue a mais de 100 de energia
                if (player.youEnergy > 100) 
                {
                    player.youEnergy = 100;
                }

                System.out.println("\n-Sua energia: "+ player.youEnergy);
                System.out.println("-Energia do inimigo: "+ enemy.enemyEnergy);
            }

        //Digitou 3 para usar poção de recuperar vida
                if(youEscolha == 3)
                {
                    if(player.youQntPotion > 0)
                    {
        //A Poção recupera 5 de energia, se ele tiver mais de 95 de energia ele não poderá usa-la
                        if(player.youEnergy > 95)
                        {
                            System.out.println("-Você só poderá usar a poção se estiver com 95 ou menos de vida.-");
                        }
                    else if(player.youHealPotion > 0)
                    {
                        player.youEnergy = player.youHealPotion + player.youEnergy;
                        System.out.println("-Você usou uma poção e recuperou 5 de energia!-");
                        player.youQntPotion = player.youQntPotion - 1;
                        System.out.println("-Sua energia: "+ player.youEnergy);
                        System.out.println("-Quantidade de poções: "+ player.youQntPotion);
                    }
                    }
                    else
                    {
                        System.out.println("-Você não possui mais poções!");
                    }
                }

        //Digitou 4 para jogar a granada
                    if (youEscolha == 4) 
                    {
                        if(player.youQntGranada > 0)
                    {
                        player.youEnergy = player.youEnergy -5;
                        enemy.enemyEnergy = enemy.enemyEnergy -20;
                        player.youQntGranada = player.youQntGranada -1;
                        System.out.println("-Você gastou uma granada, perdeu 5 pontos de energia e gastou 20 do seu inimigo!");
                        System.out.println("-Quantidade de granadas: "+ player.youQntGranada);
                    }
                    else
                    {
                        System.out.println("-Você não possui mais Granadas!");
                    }
                    }
        }   

        if(enemy.enemyEnergy <= 0)
        {
            System.out.println("-Parabéns, você derrotou o"+ enemy.enemyName+"!!-");
        }
        if((player.youEnergy <= 0) & (enemy.enemyEnergy <= 0)) 
        {
            System.out.println("-EMPATEEEEEE!!-");    
        }
        else
        {
            System.out.println("-Que pena, você foi derrotado!!-");
        }

        leitor.close();
    }
}