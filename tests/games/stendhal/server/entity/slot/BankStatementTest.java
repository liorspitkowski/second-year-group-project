package games.stendhal.server.entity.slot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.StackableItem;
import games.stendhal.server.entity.player.Player;
import utilities.PlayerTestHelper;


public class BankStatementTest {
	
	/**
	 * Tests for getItemsFromBank(), adds item to banks chest and checks if it is present in bank statement
	 */
	@Test
	public final void testAddItemToBankStatementToSemosBank() {		
		// creates test world and player
		StendhalRPZone localzone = new StendhalRPZone("testzone", 20, 20);
		SingletonRepository.getRPWorld().addRPZone(localzone);
		final Player player = PlayerTestHelper.createPlayer("briana");
		
		/// gives them an item "cheese" and places it in their bank chest
		StackableItem item = (StackableItem) SingletonRepository.getEntityManager().getItem("cheese");
		player.equip(Banks.SEMOS.getSlotName(), item);
		localzone.add(player);
		assertFalse(player.isEquipped("cheese"));
		assertEquals(1, player.getTotalNumberOf("cheese"));
		
		// creates bank statement and checks if it contains cheese, should be true
		BankStatement bs = new BankStatement(player);
		assertTrue(bs.getItemsFromBank(Banks.SEMOS).contains("cheese"));
	}
	
	/**
	 * Tests for getItemsFromBank(), adds item to banks chest and checks if it is present in bank statement
	 */
	@Test
	public final void testAddItemToBankStatementToFadoBank() {		
		// creates test world and player
		StendhalRPZone localzone = new StendhalRPZone("testzone", 20, 20);
		SingletonRepository.getRPWorld().addRPZone(localzone);
		final Player player = PlayerTestHelper.createPlayer("briana");
		
		/// gives them an item "cheese" and places it in their bank chest
		StackableItem item = (StackableItem) SingletonRepository.getEntityManager().getItem("cheese");
		player.equip(Banks.FADO.getSlotName(), item);
		localzone.add(player);
		assertFalse(player.isEquipped("cheese"));
		assertEquals(1, player.getTotalNumberOf("cheese"));
		
		// creates bank statement and checks if it contains cheese, should be true
		BankStatement bs = new BankStatement(player);
		assertTrue(bs.getItemsFromBank(Banks.FADO).contains("cheese"));
	}
	
	
	/**
	 * Tests for getItemsFromBank(), adds item to chest and then removes item from bank chest and checks if it is present in bank statement
	 */
	@Test
	public final void testRemoveItemFromBankStatement() {
		// creates test world and player
		StendhalRPZone localzone = new StendhalRPZone("testzone", 20, 20);
		SingletonRepository.getRPWorld().addRPZone(localzone);
		final Player player = PlayerTestHelper.createPlayer("briana");
		StackableItem item = (StackableItem) SingletonRepository.getEntityManager().getItem("cheese");
		/// gives them an item "cheese" and places it in their bank chest
		player.equip(Banks.SEMOS.getSlotName(), item);
		localzone.add(player);
		assertFalse(player.isEquipped("cheese"));
		assertEquals(1, player.getTotalNumberOf("cheese"));
		
		// creates bank statement and checks if it contains cheese, should return true
		BankStatement bs = new BankStatement(player);
		assertTrue(bs.getItemsFromBank(Banks.SEMOS).contains("cheese"));

		// clears chest (removing cheese)
		player.getSlot("bank").clear();
		
		// checks if it contains cheese, should return false
		assertFalse(bs.getItemsFromBank(Banks.SEMOS).contains("cheese"));
	}
	
	
	/**
	 * Tests for getItemsFromBank(), adds the same item to two different banks and then removes one of them and checks if it is present in bank statement
	 */
	@Test
	public final void testAddTheSameItemInDifferentBanksAndRemoveOneFromBankStatement() {
		// creates test world and player
		StendhalRPZone localzone = new StendhalRPZone("testzone", 20, 20);
		SingletonRepository.getRPWorld().addRPZone(localzone);
		final Player player = PlayerTestHelper.createPlayer("briana");
		StackableItem item = (StackableItem) SingletonRepository.getEntityManager().getItem("cheese");
		
		/// gives them an item "cheese" and places it in their SEMOS chest
		player.equip(Banks.SEMOS.getSlotName(), item);
		localzone.add(player);
		assertFalse(player.isEquipped("cheese"));
		assertEquals(1, player.getTotalNumberOf("cheese"));
		
		/// gives them an item "cheese" and places it in their ADOS chest
		player.equip(Banks.ADOS.getSlotName(), item);
		localzone.add(player);
		assertFalse(player.isEquipped("cheese"));
		assertEquals(2, player.getTotalNumberOf("cheese"));
		
		
		// creates bank statement and checks if it contains cheese, should return true
		BankStatement bs = new BankStatement(player);
		assertTrue(bs.getItemsFromBank(Banks.SEMOS).contains("cheese"));

		// clears chest ados (removing cheese)
		player.getSlot("bank_ados").clear();
		
		
		// checks if it contains cheese, should return true
		assertTrue(bs.getItemsFromBank(Banks.SEMOS).contains("cheese"));
	}
	
	/**
	 * Tests for getItemsFromBank(), adds multiple items to banks chest and checks if it is present in bank statement
	 */
	@Test
	public final void testAddMultipleItemsToBankStatement() {		
		// creates test world and player
		StendhalRPZone localzone = new StendhalRPZone("testzone", 20, 20);
		SingletonRepository.getRPWorld().addRPZone(localzone);
		final Player player = PlayerTestHelper.createPlayer("lior");
		
		/// gives them an item "cheese" and places it in their bank chest
		StackableItem itemA = (StackableItem) SingletonRepository.getEntityManager().getItem("cheese");
		player.equip(Banks.SEMOS.getSlotName(), itemA);
		localzone.add(player);
		assertFalse(player.isEquipped("cheese"));
		assertEquals(1, player.getTotalNumberOf("cheese"));
		
		/// gives them an item "club" and places it in their bank chest
		StackableItem itemB = (StackableItem) SingletonRepository.getEntityManager().getItem("coal");
		player.equip(Banks.SEMOS.getSlotName(), itemB);
		localzone.add(player);
		assertFalse(player.isEquipped("coal"));
		assertEquals(1, player.getTotalNumberOf("coal"));
		
		// creates bank statement and checks if it contains cheese, should be true
		BankStatement bs = new BankStatement(player);
		assertTrue(bs.getItemsFromBank(Banks.SEMOS).contains("cheese"));
		assertTrue(bs.getItemsFromBank(Banks.SEMOS).contains("coal"));
	}

	
	
	
	
	
	
	
	

}
