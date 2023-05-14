package software.n3rdydev;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class settings extends JavaPlugin {

    public static String db_ip = "n3rdydesigner.xyz";
    public static String db_user = "n3rdydev";
    public static int db_port = 3306;
    public static String db_pass = "N3rdygamerbr@123";
    public static String db_database = "DiscordGuardian";

    public static boolean LoadSettings(FileConfiguration cfg){
        db_ip = cfg.getString("db_ip");
        db_user = cfg.getString("db_user");
        db_port = cfg.getInt("db_port");
        db_pass = cfg.getString("db_pass");
        db_database = cfg.getString("db_database");


        //String info = "Informação Recebida:\n" +
        //      "IP: " + cfg.getString("db_ip") + "\n" +
        //      "User: " + cfg.getString("db_user") + "\n" +
        //      "Pass: " + cfg.getString("db_pass") + "\n" +
        //      "Database: " + cfg.getString("db_database") + "\n" +
        //      "Port: " + cfg.getInt("db_port") ;

        //Bukkit.getConsoleSender().sendMessage(info);
        return false;
    }


}
