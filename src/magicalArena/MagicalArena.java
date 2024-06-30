package magicalArena;
import java.util.Random;
import java.util.Scanner;

public class MagicalArena {

	static class Player {
        String Name; //Name of the player
        int Health;
        int Strength;
        int Attack;

        Player(String Name, int Health, int Strength, int Attack) {
            this.Name = Name;
            this.Health = Health;
            this.Strength = Strength;
            this.Attack = Attack;
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Scanner scanner = new Scanner(System.in);
        
        // To Initialize players i.e Player 1 & Player 2
        Player playerA = new Player("Player A", 50, 5, 10);
        Player playerB = new Player("Player B", 100, 10, 5);

        Random random = new Random();

        // On the basis of initial Health to determine who attacks first
        Player attacker = playerA.Health < playerB.Health ? playerA : playerB;
        Player defender = attacker == playerA ? playerB : playerA;

        System.out.println("Initial Statistics:");
        printPlayerStats(playerA);
        printPlayerStats(playerB);
        System.out.println();

        // Loop of the game Begins
        while (playerA.Health > 0 && playerB.Health > 0) {
            // Attacker rolls attack die
            int attackRoll = random.nextInt(6) + 1; // To roll a 6-sided die
            // Defender rolls defense die
            int defenseRoll = random.nextInt(6) + 1; //  To roll a 6-sided die

            // To Calculate damage
            int attackDamage = attacker.Attack * attackRoll;
            int defenseDamage = defender.Strength * defenseRoll;

            // To Calculate actual damage inflicted
            int damageTaken = Math.max(0, attackDamage - defenseDamage);
            defender.Health -= damageTaken;

            // Details of the rounds are printed here
            System.out.printf("%s attacks %s with an attack roll of %d\n", attacker.Name, defender.Name, attackRoll);
            System.out.printf("%s defends with a defense roll of %d\n", defender.Name, defenseRoll);
            System.out.printf("Damage inflicted: %d\n", damageTaken);
            System.out.printf("%s's health: %d, %s's health: %d\n\n", playerA.Name, playerA.Health, playerB.Name, playerB.Health);

            // For the next round roles are switched
            Player temp = attacker;
            attacker = defender;
            defender = temp;
        }

        // To Determine the winner
        if (playerA.Health <= 0) {
            System.out.println("Player B wins!");
        } else {
            System.out.println("Player A wins!");
        }

        scanner.close();
    }

    static void printPlayerStats(Player player) {
        System.out.printf("%s - Health: %d, Strength: %d, Attack: %d\n", player.Name, player.Health, player.Strength, player.Attack);
    }
}

