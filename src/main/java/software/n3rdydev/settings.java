package software.n3rdydev;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class settings extends JavaPlugin {

    public static String db_ip = "";
    public static String db_user = "";
    public static int db_port = 3306;
    public static String db_pass = "";
    public static String db_database = "DiscordGuardian";

    //Link para enviar o nick, discord ou site
    public static String reg_website_discord = "";

    public static boolean LoadSettings(FileConfiguration cfg){
        db_ip = cfg.getString("db_ip");
        db_user = cfg.getString("db_user");
        db_port = cfg.getInt("db_port");
        db_pass = cfg.getString("db_pass");
        db_database = cfg.getString("db_database");
        reg_website_discord = cfg.getString("website");
        return false;
    }


}
