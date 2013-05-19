package me.skipperguy12.MoneyBukkit2.listeners;

import me.skipperguy12.MoneyBukkit2.MoneyBukkitPlayer;
import me.skipperguy12.MoneyBukkit2.utils.MoneyUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ExampleSignListener implements Listener {

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (event.getLine(0).equalsIgnoreCase("[MoneyBukkit]")) {
			if (!event.getPlayer().isOp() || !event.getPlayer().hasPermission("moneybukkit.signs.create"))
				return;
			event.setLine(0, "[" + ChatColor.GREEN + "MoneyBukkit" + ChatColor.RESET + "]");
			event.getPlayer().sendMessage(ChatColor.GREEN + "MoneyBukkit sign shop has been created successfully!");
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock().getState() instanceof Sign) {
				Block b = event.getClickedBlock();
				BlockState bs = b.getState();
				try {
					Sign s = (Sign) bs;
					if (!s.getLine(0).equals("[" + ChatColor.GREEN + "MoneyBukkit" + ChatColor.BLACK + "]"))
						return;
					String itemName = ChatColor.stripColor(s.getLine(1));
					String priceString = ChatColor.stripColor(s.getLine(2).replaceAll("$", "").trim());
					int price = Integer.valueOf(priceString.replace("$", "").replaceAll("\\s", ""));
					String quanString = ChatColor.stripColor(s.getLine(3).replaceAll("Amount:", "").trim());
					int quantity = Integer.valueOf(quanString.replaceAll("\\s", ""));
					Player p = event.getPlayer();

					try {
						Material m = Material.matchMaterial(itemName.toUpperCase().trim());
						if (MoneyUtils.chargePlayer(new MoneyBukkitPlayer(p), price)) {
							p.getInventory().addItem(new ItemStack(m, quantity));
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				} catch (Exception e1) {
					Bukkit.getLogger().severe("[MoneyBukkit] Error casting block to a sign!");
					e1.printStackTrace();
				}
			}
		}
	}
}