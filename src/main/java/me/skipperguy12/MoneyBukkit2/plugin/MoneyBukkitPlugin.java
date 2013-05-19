package me.skipperguy12.MoneyBukkit2.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;

import me.skipperguy12.MoneyBukkit2.MoneyBukkitPlayer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.CommandException;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import com.sk89q.minecraft.util.commands.CommandUsageException;
import com.sk89q.minecraft.util.commands.CommandsManager;
import com.sk89q.minecraft.util.commands.MissingNestedCommandException;
import com.sk89q.minecraft.util.commands.WrappedCommandException;

import me.skipperguy12.MoneyBukkit2.commands.Money;
import me.skipperguy12.MoneyBukkit2.listeners.*;
import me.skipperguy12.MoneyBukkit2.utils.MoneyUtils;

public class MoneyBukkitPlugin extends JavaPlugin {

	public String PATH_TO_STARTING_BALANCE = "starting-balance";

	public String PATH_TO_CURRENCYNAME = "currency";

	public String PATH_TO_PLAYERS = "players";

	public static String PATH_TO_MESSAGES = "messages";

	public static String PATH_TO_NOT_ENOUGH_MONEY_MESSAGE = PATH_TO_MESSAGES + ".not-enough-money-message";

	public static String PATH_TO_SUCCESSFUL_PAYMENT_MESSAGE = PATH_TO_MESSAGES + ".successful-payment";

	public static String PATH_TO_SUCCESSFUL_TRANSACTION_MESSAGE = PATH_TO_MESSAGES + ".successful-transaction";

	public MoneyBukkitPlayerMoneyChangeEventListener MoneyBukkitPlayerMoneyChangeEventListener;
	public PlayerJoinEventListener PlayerJoinEventListener;
	public ExampleSignListener ExampleSignListener;

	public MoneyUtils MoneyUtils;

	private CommandsManager<CommandSender> commands;

	public void onEnable() {
		if (!getDataFolder().exists()) {
			createConfig(new File(getDataFolder(), "config.yml"));
		}
		registerListeners();
		setupCommands();
	}

	public void onDisable() {

	}

	public void registerListeners() {
		MoneyBukkitPlayerMoneyChangeEventListener = new MoneyBukkitPlayerMoneyChangeEventListener(this);
		PlayerJoinEventListener = new PlayerJoinEventListener(this);
		ExampleSignListener = new ExampleSignListener();

		MoneyUtils = new MoneyUtils(this);

		registerEvents(MoneyBukkitPlayerMoneyChangeEventListener);
		registerEvents(PlayerJoinEventListener);
		registerEvents(ExampleSignListener);
	}

	public void registerEvents(Listener listener) {
		Bukkit.getPluginManager().registerEvents(listener, this);
	}

	private void setupCommands() {
		this.commands = new CommandsManager() {
			@Override
			public boolean hasPermission(Object arg0, String arg1) {
				return ((CommandSender) arg0).hasPermission(arg1);
			}
		};
		CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);
		cmdRegister.register(Money.class);
	}

	public void createConfig(File f) {
		InputStream cfgStream = getResource("config.yml");
		if (!getDataFolder().exists())
			getDataFolder().mkdirs();
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ReadableByteChannel rbc = Channels.newChannel(cfgStream);
			fos.getChannel().transferFrom(rbc, 0L, 16777216L);
			fos.close();
		} catch (Exception e) {
			getLogger().info("There was an error in creating the config. Using bukkit methods to do so.");
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
			this.commands.execute(cmd.getName(), args, sender, new Object[] { sender });
		} catch (CommandPermissionsException e) {
			sender.sendMessage(ChatColor.RED + "You don't have permission.");
		} catch (MissingNestedCommandException e) {
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (CommandUsageException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
			sender.sendMessage(ChatColor.RED + e.getUsage());
		} catch (WrappedCommandException e) {
			if ((e.getCause() instanceof NumberFormatException)) {
				sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
			} else {
				sender.sendMessage(ChatColor.RED + "An error has occurred. See console.");
				e.printStackTrace();
			}
		} catch (CommandException e) {
			sender.sendMessage(ChatColor.RED + e.getMessage());
		}

		return true;
	}

}
