package software.n3rdydev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import software.n3rdydev.MySql;

public class DGremove implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp() || sender.hasPermission("discordguardian.remove") || sender.hasPermission("discordguardian.*")){
            //tem op
            if(args[0] != null) {
                boolean userRemoved = MySql.removeUser(args[0]);
                if(userRemoved != false){
                    sender.sendMessage("§cUsuário removido da lista branca!");
                }
                else{
                    sender.sendMessage("§cVish, deu erro...");
                }
                return true;
            }
            else{
                sender.sendMessage("§cErro! você não especificou o Player");
                return true;

            }
        }
        return false;
    }
}
