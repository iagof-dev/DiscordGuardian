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
import java.sql.SQLException;


public final class DiscordGuardian extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        //Criando arquivo configuração
        FileConfiguration config = this.getConfig();
        //Se não possuir nenhum arquivo, criar pasta e arquivo de configuração
        config.options().copyHeader(true);
        config.options().copyDefaults(true);

        //Salvar Confioguração
        saveConfig();

        //Salvar informações do arquivo para a classe do MySql
        settings.LoadSettings(config);

        //Tabela já foi criada?
        //true = não faz nada
        //false = Cria a tabela e seta valor true no configured no arquivo config.yml
        boolean configurado = config.getBoolean("configured");
        if(configurado != true){
            //criar tabela
            boolean table_created = MySql.CreateTable();
            //caso tenha criado, atualizar config.yml
            if(table_created != false){
                config.set("configured", true);
                saveConfig();
            }
        }

        //Criação de conexão com MySql
        Connection mysqlcon = MySql.CreateCon();
        boolean mysql_closed = false;
        try {
            mysql_closed = mysqlcon.isClosed();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (mysql_closed != true)
        {
            Bukkit.getConsoleSender().sendMessage("§f[§dDiscordGuardian§f] §7| §aConectado!");
        }

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
    public void onDisable()
    {
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
