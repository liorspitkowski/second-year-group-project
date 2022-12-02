/***************************************************************************
 *               (C) Copyright 2003-2013 - Faiumoni e.V                    *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.mapstuff.block;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import games.stendhal.common.Direction;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.entity.player.Player;
import games.stendhal.server.maps.MockStendlRPWorld;
import utilities.PlayerTestHelper;
import utilities.RPClass.BlockTestHelper;

/**
 * Tests for the pushable chest
 *
 * @author madmetzger
 */
public class PushableChestTest
{

	@BeforeClass
	public static void beforeClass() 
	{
		BlockTestHelper.generateRPClasses();
		PlayerTestHelper.generatePlayerRPClasses();
        MockStendlRPWorld.get();
	}


	@Test
	public void testPush() 
	{
		MovableChest c = new MovableChest(0,0);
		c.setPosition(0, 0);
		StendhalRPZone z = new StendhalRPZone("test", 10, 10);
		Player p = PlayerTestHelper.createPlayer("pusher");
		z.add(c);
		assertThat(Integer.valueOf(c.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(c.getY()), is(Integer.valueOf(0)));

		c.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(c.getX()), is(Integer.valueOf(1)));
		assertThat(Integer.valueOf(c.getY()), is(Integer.valueOf(0)));

		c.push(p, Direction.LEFT);
		assertThat(Integer.valueOf(c.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(c.getY()), is(Integer.valueOf(0)));

		c.push(p, Direction.DOWN);
		assertThat(Integer.valueOf(c.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(c.getY()), is(Integer.valueOf(1)));

		c.push(p, Direction.UP);
		assertThat(Integer.valueOf(c.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(c.getY()), is(Integer.valueOf(0)));
	}



	@Test
	public void testCoordinatesAfterPush() 
	{
		MovableChest c = new MovableChest(0,0);
		c.setPosition(0, 0);
		StendhalRPZone z = new StendhalRPZone("test", 10, 10);
		z.add(c);
		
		assertThat(Integer.valueOf(c.getXAfterPush(Direction.UP)), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(c.getYAfterPush(Direction.UP)), is(Integer.valueOf(-1)));

		assertThat(Integer.valueOf(c.getXAfterPush(Direction.DOWN)), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(c.getYAfterPush(Direction.DOWN)), is(Integer.valueOf(1)));

		assertThat(Integer.valueOf(c.getXAfterPush(Direction.LEFT)), is(Integer.valueOf(-1)));
		assertThat(Integer.valueOf(c.getYAfterPush(Direction.LEFT)), is(Integer.valueOf(0)));

		assertThat(Integer.valueOf(c.getXAfterPush(Direction.RIGHT)), is(Integer.valueOf(1)));
		assertThat(Integer.valueOf(c.getYAfterPush(Direction.RIGHT)), is(Integer.valueOf(0)));
	}

	@Test
	public void testCollisionOnPush() throws Exception {
		MovableChest b1 = new MovableChest(0,0);
		b1.setPosition(0, 0);
		StendhalRPZone z = new StendhalRPZone("test", 10, 10);
		Player p = PlayerTestHelper.createPlayer("pusher");
		z.add(b1, false);

		// one successful push
		b1.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(b1.getX()), is(Integer.valueOf(1)));

		// now we add an obstacle right of b1
		MovableChest b2 = new MovableChest(0,0);
		b2.setPosition(02, 0);
		z.add(b2, false);

		// push should not be executed now and stay at the former place
		b1.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(b1.getX()), is(Integer.valueOf(1)));
	}

	@Test
	public final void testNotPushableIfOpen() {
		Player p = PlayerTestHelper.createPlayer("pusher");
		MovableChest c = new MovableChest(0,0);
		StendhalRPZone z = new StendhalRPZone("test", 10, 10);
		c.setPosition(0, 0);
		z.add(c);
		assertFalse(c.isOpen());
		c.open();
		assertTrue(c.isOpen());

		c.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(c.getX()), is(Integer.valueOf(0)));
		assertThat(Integer.valueOf(c.getY()), is(Integer.valueOf(0)));

		
		c.close();
		assertFalse(c.isOpen());
		
		c.push(p, Direction.RIGHT);
		assertThat(Integer.valueOf(c.getX()), is(Integer.valueOf(1)));
		assertThat(Integer.valueOf(c.getY()), is(Integer.valueOf(0)));
	}
}
