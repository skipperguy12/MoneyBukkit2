package me.skipperguy12.MoneyBukkit2.utils;

import org.bukkit.Bukkit;

import me.skipperguy12.MoneyBukkit2.MoneyBukkitPlayer;
import me.skipperguy12.MoneyBukkit2.events.MoneyBukkitPlayerMoneyChangeEvent;
import me.skipperguy12.MoneyBukkit2.plugin.MoneyBukkitPlugin;

public class MoneyUtils {
	static MoneyBukkitPlugin plugin;

	public MoneyUtils(MoneyBukkitPlugin instance) {
		this.plugin = instance;
	}

	/**
	 * Sets the balance of a player
	 * 
	 * @param moneyBukkitPlayer
	 *            The player
	 * @param value
	 *            The value
	 */
	public static void setBalance(MoneyBukkitPlayer moneyBukkitPlayer, int value) {

		MoneyBukkitPlayerMoneyChangeEvent event = new MoneyBukkitPlayerMoneyChangeEvent(moneyBukkitPlayer, plugin.getConfig().getInt(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance"), plugin.getConfig().getInt(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance") - value);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (event.isCancelled())
			return;
		plugin.getConfig().set(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance", value);
		plugin.saveConfig();

	}

	/**
	 * Charges a player by subtracting his balance by the value
	 * 
	 * @param moneyBukkitPlayer
	 *            The player
	 * @param value
	 *            Amount to charge
	 */
	public static boolean chargePlayer(MoneyBukkitPlayer moneyBukkitPlayer, int value) {
		MoneyBukkitPlayerMoneyChangeEvent event = new MoneyBukkitPlayerMoneyChangeEvent(moneyBukkitPlayer, plugin.getConfig().getInt(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance"), plugin.getConfig().getInt(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance") - value);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (event.isCancelled()) {
			return false;
		}
		plugin.getConfig().set(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance", plugin.getConfig().getInt(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance") - value);
		plugin.saveConfig();
		return true;
	}

	/**
	 * Gets the name of the currency being used
	 * 
	 * @return
	 */
	public static String getCurrency() {
		return plugin.getConfig().getString(plugin.PATH_TO_CURRENCYNAME);
	}

	/**
	 * Gets the amount every new player starts with
	 * 
	 * @return The amount
	 */
	public static int getStartingBalance() {
		return plugin.getConfig().getInt(plugin.PATH_TO_STARTING_BALANCE);
	}

	/**
	 * Gets the amount a player currently has
	 * 
	 * @param moneyBukkitPlayer
	 *            The player
	 * @return The amount of currency the player has
	 */
	public static int getBalance(MoneyBukkitPlayer moneyBukkitPlayer) {
		return plugin.getConfig().getInt(plugin.PATH_TO_PLAYERS + "." + moneyBukkitPlayer.getName() + ".balance");
	}

}
