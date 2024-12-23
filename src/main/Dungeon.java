package main;

import java.util.Random;

public class Dungeon {
    private String[] rooms;
    private int currentRoom;

    public Dungeon(int size) {
        rooms = new String[size];
        generateDungeon();
        currentRoom = 0; // Start at the first room
    }

    private void generateDungeon() {
        Random rand = new Random();
        for (int i = 0; i < rooms.length; i++) {
            int encounter = rand.nextInt(3);
            switch (encounter) {
                case 0:
                    rooms[i] = "Empty Room";
                    break;
                case 1:
                    rooms[i] = "Monster Encounter";
                    break;
                case 2:
                    rooms[i] = "Item Room";
                    break;
            }
        }
    }

    public String getCurrentRoom() {
        return rooms[currentRoom];
    }

    public void moveNorth() {
        if (currentRoom < rooms.length - 1) {
            currentRoom++;
        }
    }

    public void moveSouth() {
        if (currentRoom > 0) {
            currentRoom--;
        }
    }

    public int getCurrentRoomIndex() {
        return currentRoom;
    }
    
}
