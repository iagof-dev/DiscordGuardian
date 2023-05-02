package software.n3rdydev;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import software.n3rdydev.commands.DGadd;
import software.n3rdydev.commands.DGremove;

public final class DiscordGuardian extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("DiscordGuardian | Iniciado!");
        this.getServer().getPluginManager().registerEvents(this, this);
        getCommand("DGadd").setExecutor(new DGadd());
        getCommand("DGdel").setExecutor(new DGremove());

    }

    @Override
    public void onLoad() {
        System.out.println("DiscordGuardian | Carregando...!");
        Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Iniciando conexão com MySql...");
        MySql.CreateCon();
        Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Conectado!");

    }

    @Override
    public void onDisable() {
        System.out.println("DiscordGuardian | Desligado!");
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.setJoinMessage("");
        Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Registrando " + e.getPlayer().getName() + " com UUID: " + e.getPlayer().getUniqueId());
        String id = ""+e.getPlayer().getUniqueId();
        String name = ""+e.getPlayer().getName();
        Boolean verified = MySql.verifyPlayer(id, name);
        if (verified != true){
            e.getPlayer().kickPlayer("Você não está autorizado a entrar no servidor!\nPara liberar login\nEntre: discord.n3rdydesigner.xyz");
        }

    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        e.setQuitMessage("");
        Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Registrando " + e.getPlayer().getName() + " com UUID: " + e.getPlayer().getUniqueId());
        String id = ""+e.getPlayer().getUniqueId();
        String name = ""+e.getPlayer().getName();
        Boolean verified = MySql.verifyPlayer(id, name);
        if (verified != true){
            e.getPlayer().kickPlayer("Você não está autorizado a entrar no servidor!\nPara liberar login\nEntre: discord.n3rdydesigner.xyz");
        }

    }
}
