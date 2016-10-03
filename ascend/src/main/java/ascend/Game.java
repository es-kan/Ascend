package ascend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Game {

	static Random rng = new Random();

	public final int height = 32, width = 64, roomAmount = 10, maxRoomHeight = 8, maxRoomWidth = 8, minRoomHeight = 4, minRoomWidth = 4;

	// field, with more specified lists to iterate over when necessary.
	public Tile[][] field = new Tile[height][width];
	ArrayList<Room> rooms = new ArrayList<Room>(roomAmount);
	ArrayList<Tile> corridorTiles = new ArrayList<Tile>();
	public ArrayList<Tile> allTiles = new ArrayList<Tile>(height * width);

	// all actors go in here!
	public ArrayList<Actor> actors = new ArrayList<Actor>();
	// and all items here. No items yet though.
	ArrayList<Item> items = new ArrayList<Item>();
	
	//is the game over?
	boolean gameOver = false;

	public Hero hero;

	// MAIN!!!!!
	public static void main(String[] args) {
		Game game = new Game();
		printField(game);
	}


	public Game() {
		createField();
		createMultipleRooms();
		//removeRoomTilesFromCorridors();
		setTileAttributes();
		initHero();
		initEnemies(5);
	}

	// this method creates a field of tiles with height height and width width
	public void createField() {
		for (int y = 0; y < height; y++) {
			Tile[] row = new Tile[width];
			for (int x = 0; x < width; x++) {
				row[x] = new Tile(x, y);
				allTiles.add(row[x]);
			}
			field[y] = row;
		}
	}

	// this method prints the Tile char 'attribute' for each Tile in the field
	// (to the console).
	static public void printField(Game game) {
		for (int y = 0; y < game.height; y++) {
			for (int x = 0; x < game.width; x++) {
				System.out.print(game.field[y][x]);
			}
			System.out.println();
		}
	}

	// this method creates one Room object within the boundaries of the field.
	public Room createRoom() {
		boolean isBuilding = true;
		int topLeftX, topLeftY, roomWidth, roomHeight; // x and y are the
														// coordinates of the
														// top left corner of
														// the Room to be
														// created
		createRoomLoop: do {
			topLeftX = rng.nextInt(width - 2) + 1;
			topLeftY = rng.nextInt(height - 2) + 1;
			// ensure generated room size is between established mins and maxes
			roomWidth = rng.nextInt(maxRoomWidth - minRoomWidth) + minRoomWidth;
			roomHeight = rng.nextInt(maxRoomHeight - minRoomHeight) + minRoomWidth;
			// test if new room would be out of bounds in field, or manifest at
			// one of the outer edges
			if ((topLeftX + roomWidth > width - 1 || topLeftY + roomHeight > height - 1)) {
				continue createRoomLoop;
			}
			isBuilding = false;
		} while (isBuilding);

		Room room = new Room(topLeftX, topLeftX + roomWidth, topLeftY, topLeftY + roomHeight);

		// add tiles from field to room tile arrays
		for (int a = topLeftY; a < (topLeftY + roomHeight); a++) { // a == row a
																	// in field
			for (int b = topLeftX; b < (topLeftX + roomWidth); b++) { // b ==
																		// column
																		// b in
																		// row
				room.tiles.add(field[a][b]);

			}
		}
		return room;
	}

	public void createMultipleRooms() {
		int i = 0;
		while (i < roomAmount) {
			boolean intersectionFound = false;
			Room newRoom = createRoom();
			if (rooms.size() == 0) {
				rooms.add(newRoom);
				i++;
			} else {
				for (Room room : rooms) {
					if (newRoom.intersects(room)) {
						intersectionFound = true;
					}
				}
				if (!intersectionFound) {
					rooms.add(newRoom);
					setCorridors(rooms.get(i - 1), newRoom);
					i++;
				}
			}
		}
		removeRoomTilesFromCorridors();
	}

	public void setCorridors(Room room1, Room room2) {
		Tile center1 = room1.getRandomTile(), center2 = room2.getRandomTile();
		setHCorridor(center1.x, center2.x, center1.y);
		setVCorridor(center1.y, center2.y, center2.x);
	}

	public void setVCorridor(int y1, int y2, int x) {
		int max = Math.max(y1, y2);
		int min = Math.min(y1, y2);
		for (int i = min; i <= max; i++) {
			corridorTiles.add(field[i][x]);
		}
	}

	public void setHCorridor(int x1, int x2, int y) {
		int max = Math.max(x1, x2);
		int min = Math.min(x1, x2);
		for (int i = min; i <= max; i++) {
			corridorTiles.add(field[y][i]);
		}
	}

	public void setTileAttributes() {
		for (Tile corridorTile : corridorTiles) {
			corridorTile.setAttribute(Tile.TileType.FLOOR_TILE);
		}
		for (Room room : rooms) {
			for (Tile roomTile : room.tiles) {
				roomTile.setAttribute(Tile.TileType.FLOOR_TILE);
			}
		}
	}

	public void removeRoomTilesFromCorridors() {
		ArrayList<Tile> roomTiles = new ArrayList<Tile>();
		for (Room room : rooms) {
			for (Tile tile : room.tiles) {
				roomTiles.add(tile);
			}
		}
		for (Tile tile : roomTiles) {
			if (corridorTiles.contains(tile)) {
				corridorTiles.remove(tile);
			}
		}
	}

	public void initHero() {
		Hero hero = new Hero(this);
		placeUnit(hero);
		actors.add(hero);
		this.hero = hero;
	}

	// only makes goblins for now. The goblins, they do nothing.
	public void initEnemies(int amountOfEnemies) {
		Enemy[] newEnemies = new Enemy[amountOfEnemies];
		for (int i = 0; i < amountOfEnemies; i++) {
			Goblin goblin = new Goblin(this);
			placeUnit(goblin);
			newEnemies[i] = goblin;
		}
		actors.addAll(Arrays.asList(newEnemies));
	}

	public void placeUnit(Unit u) {
		Tile targetTile = rooms.get(rng.nextInt(roomAmount - 1)).getRandomTile();
		if (!targetTile.checkOccupied()) {
			u.currentPosition = targetTile;
			targetTile.pushUnit(u);
		} else {
			placeUnit(u);
		}
	}
	
	public Tile getNeighbour(Tile tile, String direction) {
		//TODO implements ACT!
		switch (direction) {
		case "NORTH":
			return field[tile.y - 1][tile.x];
		case "EAST":
			return field[tile.y][tile.x + 1];
		case "SOUTH":
			return field[tile.y + 1][tile.x];
		case "WEST":
			return field[tile.y][tile.x - 1];
		}
		// illegal direction!
		throw new IllegalArgumentException("Wrong direction!");
	}
	
	public Tile[] getAllNeighbours(Tile tile){
		Tile[] neighbours = {field[tile.y - 1][tile.x], field[tile.y][tile.x + 1], field[tile.y + 1][tile.x], field[tile.y][tile.x - 1]};
		return neighbours;
	}
	
	public Tile[] getSurroundingSquare(Tile tile){
		ArrayList<Tile> surround = new ArrayList<Tile>(8);
		for(int y = tile.y - 1; y <=tile.y + 1; y++){
			for(int x = tile.x - 1; x <=tile.x + 1; x++){
				surround.add(field[y][x]);
			}
		}
		return surround.toArray(new Tile[surround.size()]);
	}
	
	public Actor[] getNearbyEnemies(Tile tile){
		ArrayList<Actor> targets = new ArrayList<Actor>();
		for(Tile neighbourTile : getSurroundingSquare(tile)){
			if(neighbourTile.unit instanceof Actor){
				targets.add((Actor) neighbourTile.unit);
			}
		}
		return targets.toArray(new Actor[targets.size()]);
	}
}