import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class simpleRPG {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    // Classes to represent the Player, Monster, and Item
    static class Player {
        String name;
        int health = 100;
        int attack = 20;
        ArrayList<String> inventory = new ArrayList<>();
        int score = 0;

        void displayStats() {
            System.out.println("---- Player Stats ----");
            System.out.println("Name: " + name);
            System.out.println("Health: " + health);
            System.out.println("Attack: " + attack);
            System.out.println("Score: " + score);
            System.out.println("Inventory: " + inventory);
            System.out.println("----------------------");
        }
    }

    static class Monster {
        String name;
        int health;
        int attack;

        Monster(String name, int health, int attack) {
            this.name = name;
            this.health = health;
            this.attack = attack;
        }
    }

    static class Item {
        String name;
        int healthRestore;

        Item(String name, int healthRestore) {
            this.name = name;
            this.healthRestore = healthRestore;
        }
    }

    // Methods to handle different game mechanics
    public static Player createCharacter() {
        System.out.println("Enter your character's name:");
        String name = scanner.nextLine();
        Player player = new Player();
        player.name = name;
        System.out.println("Welcome, " + name + "! Your adventure begins!");
        return player;
    }

    public static Monster spawnMonster() {
        String[] monsterNames = {"Goblin", "Orc", "Troll", "Skeleton"};
        String name = monsterNames[random.nextInt(monsterNames.length)];
        int health = 30 + random.nextInt(20);
        int attack = 5 + random.nextInt(10);
        return new Monster(name, health, attack);
    }

    public static Item findItem() {
        String[] itemNames = {"Potion", "Elixir", "Herb"};
        String name = itemNames[random.nextInt(itemNames.length)];
        int healthRestore = 10 + random.nextInt(20);
        return new Item(name, healthRestore);
    }

    public static void battle(Player player, Monster monster) {
        System.out.println("A wild " + monster.name + " appears!");
        while (monster.health > 0 && player.health > 0) {
            System.out.println("\nChoose an action: ");
            System.out.println("1. Attack");
            System.out.println("2. Use Item");
            System.out.println("3. Flee");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                int damage = random.nextInt(player.attack);
                System.out.println("You attack the " + monster.name + " for " + damage + " damage!");
                monster.health -= damage;

                if (monster.health > 0) {
                    int monsterDamage = random.nextInt(monster.attack);
                    System.out.println("The " + monster.name + " attacks you for " + monsterDamage + " damage!");
                    player.health -= monsterDamage;
                }
            } else if (choice == 2) {
                if (player.inventory.isEmpty()) {
                    System.out.println("Your inventory is empty!");
                } else {
                    System.out.println("Choose an item to use: ");
                    for (int i = 0; i < player.inventory.size(); i++) {
                        System.out.println((i + 1) + ". " + player.inventory.get(i));
                    }
                    int itemChoice = scanner.nextInt() - 1;
                    scanner.nextLine(); // Consume newline
                    System.out.println("You used " + player.inventory.get(itemChoice));
                    player.health += 20; // Health restore for simplicity
                    player.inventory.remove(itemChoice);
                }
            } else if (choice == 3) {
                System.out.println("You fled from the battle!");
                return;
            }
        }

        if (player.health <= 0) {
            System.out.println("You were defeated by the " + monster.name + "!");
            System.out.println("Game Over!");
            System.exit(0);
        } else {
            System.out.println("You defeated the " + monster.name + "!");
            player.score += 10;
        }
    }

    public static void explore(Player player) {
        System.out.println("\nYou explore the dungeon...");
        int event = random.nextInt(3);

        if (event == 0) {
            Monster monster = spawnMonster();
            battle(player, monster);
        } else if (event == 1) {
            Item item = findItem();
            System.out.println("You found a " + item.name + "!");
            player.inventory.add(item.name);
        } else {
            System.out.println("The room is empty...");
        }
    }

    public static void main(String[] args) {
        Player player = createCharacter();

        while (true) {
            player.displayStats();
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Explore");
            System.out.println("2. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                explore(player);
            } else {
                System.out.println("Exiting the game...");
                System.out.println("Final Score: " + player.score);
                System.exit(0);
            }
        }
    }
}
