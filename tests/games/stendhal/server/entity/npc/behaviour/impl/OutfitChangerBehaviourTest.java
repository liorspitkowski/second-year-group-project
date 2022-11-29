/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2011 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.npc.behaviour.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static utilities.SpeakerNPCTestHelper.getReply;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.core.engine.SingletonRepository;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.behaviour.impl.OutfitChangerBehaviour.ExpireOutfit;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
//import games.stendhal.server.maps.MockStendhalRPRuleProcessor;
import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.maps.ados.city.MakeupArtistNPC;
import utilities.PlayerTestHelper;
import utilities.RPClass.ItemTestHelper;
import static games.stendhal.server.entity.npc.ConversationStates.IDLE;

/**
 * tests for OutfitChangerBehaviour
 */
public class OutfitChangerBehaviourTest {
	
	private static Player player = null;
	private SpeakerNPC npc = null;
	private Engine en = null;

	/**
	 * prepare tests
	 */
	@BeforeClass
	public static void setupBeforeClass() {

		MockStendlRPWorld.get();
		
		final StendhalRPZone zone = new StendhalRPZone("0_semos_mountain_n2");
		MockStendlRPWorld.get().addRPZone(new StendhalRPZone("0_semos_mountain_n2"));
		new MakeupArtistNPC().configureZone(zone, null);

		player = PlayerTestHelper.createPlayerWithOutFit("player");

		zone.add(player);
		player.setPosition(70, 100);
	}

	/**
	 * cleanup after tests
	 */
	@AfterClass
	public static void teardownAfterClass() {
		PlayerTestHelper.removePlayer(player);
		MockStendlRPWorld.reset();
	}

	/**
	 * Tests for onWornOff.
	 */
	@Test
	public void testOnWornOff() {
		Map<String, Integer> pricelist = new HashMap<String, Integer>();
		pricelist.put("trunks", Integer.valueOf(50));
		Player player = PlayerTestHelper.createPlayer("bob");
		ExpireOutfit cloth = new ExpireOutfit(player.getName());
		ExpireOutfit cloth2 = new ExpireOutfit(player.getName());
		assertTrue(cloth.equals(cloth2));
		assertTrue(cloth2.equals(cloth));

		ExpireOutfit cloth3 = new ExpireOutfit(PlayerTestHelper.createPlayer("bob").getName());

		assertTrue(cloth.equals(cloth3));
		assertTrue(cloth3.equals(cloth));

	}
	
	/*
	 * Test for buying outfit
	 */
	@Test
	public void buyOutfitTest()
	{
		npc = SingletonRepository.getNPCList().get("Fidorea");
		en = npc.getEngine();
		
		
		Item item = ItemTestHelper.createItem("money", 20);
		player.getSlot("bag").add(item);

		en.step(player, "hi");
		en.step(player, "buy");
		assertEquals("To buy a mask will cost 20. Do you want to buy one?", getReply(npc));
		
		en.step(player, "yes"); en.step(player, "keep");

		assertEquals("Thanks, and please don't forget to #return it when you don't need it anymore!", getReply(npc));
	}
	
	/*
	 * Test for changing outfit
	 */
	@Test
	public void changeOutfitTest()
	{
		npc = SingletonRepository.getNPCList().get("Fidorea");
		en = npc.getEngine();
		
		Item item = ItemTestHelper.createItem("money", 20);
		player.getSlot("bag").add(item);
		
		en.step(player, "hi");
		en.step(player, "buy");
		en.step(player, "yes");
		Integer maskBefore= player.getOutfit().getLayer("mask");
		en.step(player, "change");
		Integer maskAfter= player.getOutfit().getLayer("mask");
		
		assertNotEquals(maskBefore, maskAfter);
		
		en.step(player, "bye");
	}
	
	/*
	 * Outfit cannot be changed once kept Test
	 */
	@Test
	public void cannotChangeKeptOutfitTest()
	{
		npc = SingletonRepository.getNPCList().get("Fidorea");
		en = npc.getEngine();
		
		Item item = ItemTestHelper.createItem("money", 20);
		player.getSlot("bag").add(item);
		
		en.step(player, "hi");
		en.step(player, "buy");
		en.step(player, "yes");
		en.step(player, "keep");
		
		Integer maskBefore= player.getOutfit().getLayer("mask");
		en.step(player,  "change");
		Integer maskAfter= player.getOutfit().getLayer("mask");
		
		assertEquals(maskBefore, maskAfter);
		
		en.step(player, "bye");
	}
	/*
	 * Test for
	 * Outfit cannot be changed once player leaves seller 
	 * or 
	 * when the conversation times out
	 * even if player did not confirm to keep the choice of outfit after the purchase
	 */
	
	@Test
	public void cannotChangeAfterConversationEnds()
	{
		npc = SingletonRepository.getNPCList().get("Fidorea");
		en = npc.getEngine();
		
		Item item = ItemTestHelper.createItem("money", 20);
		player.getSlot("bag").add(item);
		
		en.step(player, "hi");
		en.step(player, "buy");
		en.step(player, "yes");
		
		en.setCurrentState(IDLE);
		en.step(player, "hi");
		assertEquals("Hi, there. Do you need #help with anything?", getReply(npc));
		
		Integer maskBefore= player.getOutfit().getLayer("mask");
		en.step(player,  "change");
		Integer maskAfter= player.getOutfit().getLayer("mask");
		
		assertEquals(maskBefore, maskAfter);
		
		en.step(player, "bye");
	}
	

}
