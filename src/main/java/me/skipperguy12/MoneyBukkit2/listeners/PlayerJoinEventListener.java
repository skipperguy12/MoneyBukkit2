package me.skipperguy12.MoneyBukkit2.listeners;

import me.skipperguy12.MoneyBukkit2.MoneyBukkitPlayer;
import me.skipperguy12.MoneyBukkit2.plugin.MoneyBukkitPlugin;
import me.skipperguy12.MoneyBukkit2.utils.MoneyUtils;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinEventListener implements Listener {

	MoneyBukkitPlugin plugin;

	public PlayerJoinEventListener(MoneyBukkitPlugin instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if (plugin.getConfig().getString(plugin.PATH_TO_PLAYERS + "." + event.getPlayer().getName()) == null) {
			plugin.getConfig().set(plugin.PATH_TO_PLAYERS + "." + event.getPlayer().getName() + ".balance", plugin.getConfig().getInt(plugin.PATH_TO_STARTING_BALANCE));
			plugin.saveConfig();
		}
	}
}
