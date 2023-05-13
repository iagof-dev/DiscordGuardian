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



    public static boolean CreateSettings(){
        return false;
    }

    public static boolean LoadSettings(String cfg_ip, String cfg_user, String cfg_pass, String cfg_database, int cfg_port){

        db_ip = cfg_ip;
        db_user = cfg_user;
        db_port = cfg_port;
        db_pass = cfg_pass;
        db_database = cfg_database;

        String info = "Informação Recebida:\n" +
                "IP: " + cfg_ip + "\n" +
                "User: " + cfg_user + "\n" +
                "Pass: " + cfg_pass + "\n" +
                "Database: " + cfg_database + "\n" +
                "Port: " + cfg_port ;

        Bukkit.getConsoleSender().sendMessage(info);


        return false;
    }


}
