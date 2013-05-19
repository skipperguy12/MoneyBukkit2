package me.skipperguy12.MoneyBukkit2.events;

import me.skipperguy12.MoneyBukkit2.MoneyBukkitPlayer;
import me.skipperguy12.MoneyBukkit2.enums.MoneyChangeType;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class MoneyBukkitPlayerMoneyChangeEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();
	private boolean cancelled;

	private int amountChanged;
	private MoneyChangeType changeType;
	private int startingValue;
	private int endingValue;
	private MoneyBukkitPlayer player;

	public MoneyBukkitPlayerMoneyChangeEvent(MoneyBukkitPlayer player, int startingValue, int endingValue) {
		this.player = player;
		this.startingValue = startingValue;
		this.endingValue = endingValue;

		this.amountChanged = Math.abs(startingValue - endingValue);
		this.changeType = endingValue > startingValue ? MoneyChangeType.INCREMENT : MoneyChangeType.DECREMENT;

	}

	public MoneyBukkitPlayer getMoneyBukkitPlayer() {
		return player;
	}

	public Player getPlayer() {
		return player.getBukkit();
	}

	public int getAmountChanged() {
		return amountChanged;
	}

	public int getStartingValue() {
		return startingValue;
	}

	public int getEndingValue() {
		return endingValue;
	}

	public MoneyChangeType getChangeType() {
		return changeType;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean bln) {
		this.cancelled = bln;
	}

}
