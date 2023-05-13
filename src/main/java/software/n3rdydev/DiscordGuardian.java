package software.n3rdydev;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import software.n3rdydev.commands.DGadd;
import software.n3rdydev.commands.DGremove;
import software.n3rdydev.settings;
import software.n3rdydev.MySql;

import java.sql.Connection;


public final class DiscordGuardian extends JavaPlugin implements Listener {



    @Override
    public void onEnable() {
        //Carregar configurações
        FileConfiguration config = this.getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        //Salvar informações do arquivo para a classe do MySql
        String[] cfg_settings = new String[5];
        cfg_settings[0] = config.getString("db_ip");
        cfg_settings[1] = config.getString("db_user");
        cfg_settings[2] = config.getString("db_pass");
        int cfg_settings_port = config.getInt("db_port");
        cfg_settings[4] = config.getString("db_database");

        settings.LoadSettings(cfg_settings[0], cfg_settings[1], cfg_settings[2], cfg_settings[4], cfg_settings_port);

        //Conexão com Mysql
        //Connection mysqlcon = MySql.CreateCon();
        Bukkit.getConsoleSender().sendMessage("§f[§dDiscordGuardian§f] §7| §aConectado!");

        System.out.println("§f[§dDiscordGuardian§f] §7| §aIniciado!");
        this.getServer().getPluginManager().registerEvents(this, this);
        getCommand("DGadd").setExecutor(new DGadd());
        getCommand("DGdel").setExecutor(new DGremove());


    }

    @Override
    public void onLoad() {
        System.out.println("§f[§dDiscordGuardian§f] §7| §6Carregando...");
        Bukkit.getConsoleSender().sendMessage("§f[§dDiscordGuardian§f] §7| §6Iniciando conexão com MySql...");
    }

    @Override
    public void onDisable() {
        System.out.println("§f[§dDiscordGuardian§f] §7| §cDesligado!");
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Bukkit.getConsoleSender().sendMessage("§f[§dDiscordGuardian§f] §7| §6Registrando " + e.getPlayer().getName() + " com UUID: " + e.getPlayer().getUniqueId());
        String id = ""+e.getPlayer().getUniqueId();
        String name = ""+e.getPlayer().getName();
        Boolean verified = MySql.verifyPlayer(id, name);
        if (verified != true){
            e.getPlayer().kickPlayer("[DiscordGuardian] Você não está autorizado a entrar no servidor!\nPara liberar login\nEntre: discord.n3rdydesigner.xyz");
        }

    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }
}
