/* $Id$ */
/***************************************************************************
 *                   (C) Copyright 2003-2010 - Stendhal                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.item;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;




import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.creature.Creature;

import marauroa.common.Log4J;
import utilities.RPClass.ItemTestHelper;
import utilities.RPClass.CreatureTestHelper;
import utilities.PlayerTestHelper;


public class PipeOfCharmingTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();
		CreatureTestHelper.generateRPClasses();

	}
	
	
    /*
     * To test that creatures continue to target the player once charmed     * 
     */
	@Test
    public void testCreatureTarget(){
		StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		final Player jim = PlayerTestHelper.createPlayer("jim");
		final PipeOfCharming pipe = new PipeOfCharming("pipe","tool","pipe", null);
		final Creature enemy = new Creature();
		zone.add(jim);
		zone.add(enemy);
		enemy.setTarget(jim);
		assertEquals(enemy.getAttackTarget(), jim);
		jim.equip("lhand", pipe);
		assertEquals(enemy.getAttackTarget(), jim);	        
    }
	
    /*
     * To test that creatures can see PipeOfCharming Equipped 
     */
	@Test
    public void testGetPipe(){
		StendhalRPZone zone = new StendhalRPZone("testzone", 10, 10);
		final Player finn = PlayerTestHelper.createPlayer("finn");
		final PipeOfCharming pipe = new PipeOfCharming("pipe","tool","pipe", null);
		final Creature enemy = new Creature();
		zone.add(finn);
		zone.add(enemy);
		enemy.setTarget(finn);
		finn.equip("lhand", pipe);
		assertTrue(enemy.getAttackTarget().isEquipped("pipe"));	    
		enemy.attack();
    }
	

	/*
	 * To test when used
	 */
	@Test
	public void testUse() {
		final PipeOfCharming pipe = new PipeOfCharming("pipe","tool","pipe", null);
		final Player adam = PlayerTestHelper.createPlayer("adam");
		
		assertTrue(pipe.onUsed(adam));
		
		
		
	}
	
    /*
     * A test to see if the PipeOfCharming has the correct description
     * This will also confirm that the xml has been implemented
     */
	@Test
    public void testDescription(){
		final PipeOfCharming pipe = new PipeOfCharming("pipe","tool","pipe", null);
		assertThat(pipe.describe(), is("You see a pipe, it looks rather charming"));
		
		

    }
}
