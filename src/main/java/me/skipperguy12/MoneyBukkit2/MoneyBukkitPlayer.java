package me.skipperguy12.MoneyBukkit2;

import me.skipperguy12.MoneyBukkit2.utils.MoneyUtils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;

public class MoneyBukkitPlayer {
	private Player player;

	public MoneyBukkitPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Get the player from the SpleefPlayer
	 * 
	 * @return The player
	 */
	public Player getBukkit() {
		return this.player;
	}

	/**
	 * Get the name of the player
	 * 
	 * @return Returns the players name
	 */
	public String getName() {
		return player.getName();
	}

	/**
	 * Gets a players balance by using MoneyUtils
	 * 
	 * @return The players balance
	 */
	public int getBalance() {
		return MoneyUtils.getBalance(this);
	}

}
