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



import static org.junit.Assert.assertFalse;


import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.server.maps.MockStendlRPWorld;
import games.stendhal.server.entity.player.Player;

import marauroa.common.Log4J;
import utilities.RPClass.ItemTestHelper;
import utilities.PlayerTestHelper;


public class PipeOfCharmingTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Log4J.init();
		MockStendlRPWorld.get();
		ItemTestHelper.generateRPClasses();

	}
	@Test
    /*
     * Test to see if entities attack whilst pipe is equipped
     */
    public void testNoEntityAttack(){
		assertTrue(false);
        
    }
	/*
	 * A test to see if the item can be equipped in left or right hand, and not head
	 */
	@Test
	public void testCorrectSlot() {
		final Player craig = PlayerTestHelper.createPlayer("craig");
		final PipeOfCharming pipe = new PipeOfCharming("pipe","PipeOfCharming","PipeOfCharming",null);
		assertTrue(craig.equip("lhand", pipe));
		assertTrue(craig.equip("rhand", pipe));
		assertFalse(craig.equip("head", pipe));
	}
	
    /*
     * A test to see if the PipeOfCharming has the correct description
     * This will also confirm that the xml has been implemented
     */
	@Test
    public void testDescription(){
		final PipeOfCharming pipe = new PipeOfCharming("Pipe of Charming","tool","pipeofcharming", null);
		assertThat(pipe.describe(), is("You see a pipe, it looks rather charming"));
		
		

    }
}
