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
package games.stendhal.server.maps.ados.forest;

import static org.junit.Assert.assertEquals;
import static utilities.SpeakerNPCTestHelper.getReply;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.npc.SpeakerNPC;
import games.stendhal.server.entity.npc.fsm.Engine;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.QuestHelper;
import utilities.ZonePlayerAndNPCTestImpl;
import utilities.RPClass.BlockTestHelper;
/**
 * Test getting hand carts.
 * @author Olivia Borgstrom
 */
public class FarmerNPCTest extends ZonePlayerAndNPCTestImpl{

	private Player player = null;
	private static final String ZONE_NAME = "0_ados_forest_w2";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		QuestHelper.setUpBeforeClass();
		BlockTestHelper.generateRPClasses();
		PlayerTestHelper.generatePlayerRPClasses();

		setupZone(ZONE_NAME);
	}

	@Before
	public void setUp() {
		final StendhalRPZone zone = new StendhalRPZone("0_ados_forest_w2",200, 200);
		MockStendlRPWorld.get().addRPZone(zone);
		new FarmerNPC().configureZone(zone, null);
		player = PlayerTestHelper.createPlayer("bob");
	}
	
	public FarmerNPCTest() {
		setNpcNames("Karl");
		addZoneConfigurator(new FarmerNPC(), ZONE_NAME);
	}
	
	/**
	 * Tests for getting cart when you are low level.
	 */
	@Test
	public void testGetCartLowLevel() {
		final SpeakerNPC npc = getNPC("Karl");
		final Engine en = npc.getEngine();
		player.setLevel(1);
		en.step(player, "hi");
		en.step(player, "cart");
		assertEquals("Sorry this item is for high level players only!", getReply(npc));
		en.step(player, "bye");
	}

	
	/**
	 * Tests for getting cart when you are high level.
	 */
	@Test
	public void testGetCartHighLevel() {
		final SpeakerNPC npc = getNPC("Karl");
		final Engine en = npc.getEngine();
		
		player.setLevel(300);
		en.step(player, "hi");
		en.step(player, "cart");
		assertEquals("Here is a cart!", getReply(npc));
		en.step(player, "bye");
	}

}

