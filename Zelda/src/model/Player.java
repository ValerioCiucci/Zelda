package model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class Player extends Observable {
	// Posizione logica nella mappa
	private int x;
	private int y;
	// Parametri di gioco del giocatore (specifiche JZelda)
	private int speed;
	private int health;
	private int maxHealth;
	private int rupees;
	private Direction direction;

	/**
	 * Costruttore del Player.
	 * 
	 * @param startX Coordinata X iniziale
	 * @param startY Coordinata Y iniziale
	 * @param speed  Velocità di movimento logica
	 */
	public Player(int startX, int startY, int speed) {
		this.x = startX;
		this.y = startY;
		this.speed = speed;
		this.maxHealth = 6;
		this.health = maxHealth;
		this.rupees = 0;
		this.direction = Direction.DOWN;
	}

	// --- METODI PER IL MOVIMENTO LOGICO ---
	public void move(Direction dir) {
		this.direction = dir;
		switch (dir) {
		case UP -> y -= speed;
		case DOWN -> y += speed;
		case LEFT -> x -= speed;
		case RIGHT -> x += speed;
		}
		setChanged();
		notifyObservers();
	}

	// --- METODI PER LA GESTIONE DELLO STATO ---
	public void takeDamage(int amount) {
		this.health -= amount;
		if (this.health < 0) {
			this.health = 0;
		}
		setChanged();
		notifyObservers();
	}

	public void addRupees(int amount) {
		this.rupees += amount;
		setChanged();
		notifyObservers();
	}

	public boolean isDead() {
		return this.health <= 0;
	}

	// --- GETTER E SETTER (Encapsulation) ---
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		setChanged();
		notifyObservers();
	}

	public int getSpeed() {
		return speed;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getRupees() {
		return rupees;
	}

	public Direction getDirection() {
		return direction;
	}

}
