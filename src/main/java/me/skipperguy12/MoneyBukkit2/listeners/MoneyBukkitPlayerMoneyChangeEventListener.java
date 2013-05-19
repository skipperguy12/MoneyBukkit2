package me.skipperguy12.MoneyBukkit2.listeners;

import me.skipperguy12.MoneyBukkit2.enums.MoneyChangeType;
import me.skipperguy12.MoneyBukkit2.events.MoneyBukkitPlayerMoneyChangeEvent;
import me.skipperguy12.MoneyBukkit2.plugin.MoneyBukkitPlugin;
import me.skipperguy12.MoneyBukkit2.utils.MoneyUtils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MoneyBukkitPlayerMoneyChangeEventListener implements Listener {
	MoneyBukkitPlugin plugin;

	public MoneyBukkitPlayerMoneyChangeEventListener(MoneyBukkitPlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onMoneyBukkitPlayerMoneyChangeEvent(MoneyBukkitPlayerMoneyChangeEvent event) {
		if (event.getChangeType() == MoneyChangeType.DECREMENT && event.getStartingValue() <= 0) {
			event.setCancelled(true);
			MoneyUtils.setBalance(event.getMoneyBukkitPlayer(), 0);
			event.getPlayer().sendMessage(plugin.getConfig().getString(MoneyBukkitPlugin.PATH_TO_NOT_ENOUGH_MONEY_MESSAGE).replaceAll("&", "§"));
			return;
		}
		event.getPlayer().sendMessage(event.getChangeType() == MoneyChangeType.DECREMENT ? plugin.getConfig().getString(MoneyBukkitPlugin.PATH_TO_SUCCESSFUL_PAYMENT_MESSAGE).replaceAll("&", "§") : plugin.getConfig().getString(MoneyBukkitPlugin.PATH_TO_SUCCESSFUL_TRANSACTION_MESSAGE).replaceAll("&", "§"));
	}
}
