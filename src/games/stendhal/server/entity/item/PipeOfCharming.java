/*
 ***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
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

//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
import java.util.Map;

//import games.stendhal.server.core.engine.SingletonRepository;
//import games.stendhal.server.core.rule.EntityManager;

//import org.apache.log4j.Logger;

//import games.stendhal.common.Rand;
//import games.stendhal.server.core.engine.SingletonRepository;
//import games.stendhal.server.core.engine.StendhalRPZone;
//import games.stendhal.server.core.rp.StendhalRPAction;
//import games.stendhal.server.core.rule.EntityManager;
import games.stendhal.server.entity.RPEntity;
//import games.stendhal.server.entity.creature.AttackableCreature;
//import games.stendhal.server.entity.creature.Creature;
//import marauroa.common.game.RPObject;
//import games.stendhal.server.entity.creature.Creature;

/**
 * Represents a creature summon staff.
 */
public class PipeOfCharming extends Item {



	/**
	 * Creates a new necromancer staff.
	 *
	 * @param name
	 * @param clazz
	 * @param subclass
	 * @param attributes
	 */
	public PipeOfCharming(final String name, final String clazz, final String subclass,
			final Map<String, String> attributes) {
		super(name, clazz, subclass, attributes);
	}

	/**
	 * Copy constructor.
	 *
	 * @param item
	 *            item to copy
	 */
	public PipeOfCharming(final PipeOfCharming item) {
		super(item);
	}
	public String describe() {
		String text = "You see a pipe, it looks rather charming";
		return text;
	}


	@Override
	public boolean onUsed(final RPEntity user) {
		user.sendPrivateText("**MUSIC*PLACEHOLDER**");
		return true;

	}
}