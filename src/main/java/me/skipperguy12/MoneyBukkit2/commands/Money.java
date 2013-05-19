package me.skipperguy12.MoneyBukkit2.commands;

import me.skipperguy12.MoneyBukkit2.MoneyBukkitPlayer;
import me.skipperguy12.MoneyBukkit2.utils.MoneyUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissions;

public class Money {
	
	@Command(aliases = { "checkmoney", "money" }, desc = "Gets how much money you have", usage = "", min = 0, max = 0)
	public static void money(CommandContext args, CommandSender sender) throws CommandException {
		
		sender.sendMessage(ChatColor.RED + "Your current balance is: " + MoneyUtils.getBalance(new MoneyBukkitPlayer((Player) sender)));
	}
}
