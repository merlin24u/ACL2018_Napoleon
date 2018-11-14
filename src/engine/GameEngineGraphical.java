package engine;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 * 
 *         moteur de game generique. On lui passe un game et un afficheur et il
 *         permet d'executer un game.
 */
public class GameEngineGraphical {

	/**
	 * le game a executer
	 */
	private Game game;

	/**
	 * l'afficheur a utiliser pour le rendu
	 */
	private GamePainter gamePainter;

	/**
	 * le controlleur a utiliser pour recuperer les commandes
	 */
	private GameController gameController;

	/**
	 * l'interface graphique
	 */
	private GraphicalInterface gui;

	private static int timeIter = 1000 / 15;

	/**
	 * construit un moteur
	 * 
	 * @param game
	 *            game a lancer
	 * @param gamePainter
	 *            afficheur a utiliser
	 * @param gameController
	 *            controlleur a utiliser
	 * 
	 */
	public GameEngineGraphical(Game game, GamePainter gamePainter, GameController gameController) {
		// creation du game
		this.game = game;
		this.gamePainter = gamePainter;
		this.gameController = gameController;
	}

	/**
	 * permet de lancer le game
	 */
	public void run() throws InterruptedException {

		// creation de l'interface graphique
		this.gui = new GraphicalInterface(this.gamePainter, this.gameController);
		long tDeb, tFin;

		System.out.println("Ecrire commande (Z,Q,S,D)");

		// boucle de game
		while (!this.game.isFinished()) {
			// demande controle utilisateur
			Cmd c = this.gameController.getCommand();
			// fait evoluer le game
			this.game.evolve(c);

			// affiche le game
			tDeb = System.currentTimeMillis();
			this.gui.paint();
			tFin = System.currentTimeMillis();

			// met en attente
			Thread.sleep(timeIter - (tDeb - tFin));
		}
	}

}
