package games.stendhal.server.entity.slot;

import java.util.ArrayList;
import java.util.Iterator;

import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPObject;
import marauroa.common.game.RPSlot;

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
		
		String bank_name = bank.getSlotName();
		RPSlot bank_slot = player.getSlot(bank_name);
		
		Iterator<RPObject> it = bank_slot.iterator(); 
		
		ArrayList<String> allItemsInBank = new ArrayList<String>();
		
		while (it.hasNext()) {
			
			allItemsInBank.add(((Item)it.next()).getName());
			
		}
		return allItemsInBank;
		
	}

}

