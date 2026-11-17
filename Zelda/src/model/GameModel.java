package model;

import java.util.Observable;

/**
 * Gestisce lo stato globale del gioco, coordina le entità (Player, Mappa,
 * Nemici) ed esegue la logica di evoluzione della partita (evolvi). Ha il 100%
 * di riuso ed è totalmente disaccoppiata dall'interfaccia grafica.
 */
@SuppressWarnings("deprecation")
public class GameModel extends Observable {
	private Player player;
	private GameState state;
	private int currentLevel;
	// Qui in seguito inseriremo le liste per la mappa, nemici e oggetti:
	// private Tile[][] map;
	// private List<Enemy> enemies;
	// private List<Item> items;

	/**
	 * Costruttore: inizializza le entità del modello e imposta lo stato iniziale.
	 */
	public GameModel() {
		// Inizializza il giocatore a coordinate iniziali (es. 100, 100) con velocità 4
		this.player = new Player(100, 100, 4);
		this.state = GameState.PLAYING;
		this.currentLevel = 1;
	}

	/**
	 * Metodo fondamentale richiesto dalla logica di gioco: avanzamento di un
	 * tick/frame. Invocato periodicamente dal Game Loop presente nel Controller.
	 */
	public void evolvi() {
		if (state != GameState.PLAYING) {
			return;
		}
		// 1. Aggiornamento logico delle entità (es. movimento dei nemici)
		// updateEnemies();
		// 2. Controllo delle collisioni logiche (giocatore-mappa, giocatore-nemici)
		// checkCollisions();
		// 3. Verifica delle condizioni di sconfitta o vittoria
		if (player.isDead()) {
			this.state = GameState.GAME_OVER;
		}
		// Notifichiamo alla View che il modello è evoluto e che la schermata va
		// ridisegnata
		setChanged();
		notifyObservers();
	}

	/**
	 * Invocato dal Controller quando l'utente impartisce un comando di movimento.
	 */
	public void movePlayer(Direction dir) {
		if (state == GameState.PLAYING) {
			player.move(dir);
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * Gestisce la logica pura delle collisioni (es. rettangoli di intersezione
	 * logici).
	 */
	private void checkCollisions() {
		// Implementazione delle collisioni tra bounding box matematici
	}

	/**
	 * Alterna lo stato di pausa.
	 */
	public void togglePause() {
		if (state == GameState.PLAYING) {
			state = GameState.PAUSED;
		} else if (state == GameState.PAUSED) {
			state = GameState.PLAYING;
		}
		setChanged();
		notifyObservers();
	}

	// --- GETTER E SETTER ---
	public Player getPlayer() {
		return player;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
		setChanged();
		notifyObservers();
	}

	public int getCurrentLevel() {
		return currentLevel;
	}
}
