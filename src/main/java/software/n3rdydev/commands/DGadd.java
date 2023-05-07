package software.n3rdydev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import software.n3rdydev.MySql;

public class DGadd implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender.isOp() || sender.hasPermission("discordguardian.add") || sender.hasPermission("discordguardian.*")){
            //tem op
            if(args[0] != null){
                boolean userCreated = MySql.CreateUser(args[0]);
                if(userCreated != false){
                    sender.sendMessage("§aUsuário adicionado á lista branca!");
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
        else{
            sender.sendMessage("§cErro! você não possui permissão...");
            return true;
        }
    }
}
