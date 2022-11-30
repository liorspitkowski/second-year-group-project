package games.stendhal.server.entity.mapstuff.block;
import games.stendhal.server.entity.Entity;
import games.stendhal.server.entity.player.Player;
import games.stendhal.common.Direction;


class MovableChest extends Entity
{
	
	MovableChest(int x, int y)
	{

	}
	void push(Player p, Direction d)
	{

	}

	int getXAfterPush(Direction d)
	{
		return -1;
	}

	int getYAfterPush(Direction d)
	{
		return -1;
	}

	void open()
	{

	}

	void close()
	{
		
	}

	boolean isOpen()
	{
		return false;
	}
}