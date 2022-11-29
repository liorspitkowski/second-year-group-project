package games.stendhal.server.entity.slot;

import java.util.ArrayList;

import games.stendhal.server.entity.player.Player;


/**
 * manages items in different banks
 *
 */



public class BankStatement {
	
	private final Player player;
	
	/**
	 * Creates a new bank statement
	 *
	 * @param player
	 *            Player
	 */
	

	public BankStatement(final Player player) {
		this.player = player;	
	}
	
	/**
	 * Returns the items stored in bank instance for the specified slot name.
	 *
	 * @param Banks bank 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getItemsFromBank(Banks bank) {
		return new ArrayList<String>(); 
	}
	
	

}
