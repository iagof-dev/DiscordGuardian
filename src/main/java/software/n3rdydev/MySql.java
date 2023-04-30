package software.n3rdydev;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MySql {

    static String db_ip = "";
    static String db_port = "3306";
    static String db_user = "";
    static String db_pass = "";
    static String db_database = "";
    static String db_table = "allowedUsers";
    static String db_type = "jdbc:mysql://";
    public static String db = db_type+db_ip+":"+db_port+"/"+db_database+"?jdbcCompliantTruncation=false";

    public static Connection CreateCon(){
        try{
            return DriverManager.getConnection(db, db_user, db_pass);

        }
        catch(Exception e){
            Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Erro!\n" + e);
            return null;
        }
    }

    public static boolean verifyPlayer(String uuid, String name){
        Connection con = CreateCon();

        //Verificar se usuário possui permissão de entrar
        String com_verify = "select * from " + db_table + " where username='" + name + "' or uuid='" + uuid + "';";
        try{
            PreparedStatement st = con.prepareStatement(com_verify);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                //Usuário existe
                if(rs.getBoolean("auth") != false) {
                    con.close();
                    return true;
                }
            }
            else{
                //Caso usuário não exista, criar log para dar update no bot
                String com_insert_if_not_exists = "insert into allowedUsers values (default, '" + uuid + "', '" + name + "', false);";
                try{
                    PreparedStatement st2 = con.prepareStatement(com_insert_if_not_exists);
                    st2.executeUpdate();
                    con.close();
                    return false;

                }
                catch (Exception e){
                    Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Erro!" + e);
                    con.close();
                    return false;
                }
            }

        }
        catch (Exception ex){
            Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Erro!" + ex);
            return false;

        }


        return false;
    }


    public static void CreateTable(){
        String com = "create table if not exists " + db_table +" ( id int auto_increment primary key, uuid varchar(36) null unique, username varchar(32) not null unique, auth bool not null default false );";
        Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Iniciando criação da tabela!");
        try{
            Connection con = CreateCon();
            PreparedStatement st = con.prepareStatement(com);
            Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Tabela Criada!");
        }
        catch (Exception e){
            Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Erro!" + e);
        }
    }

    public static boolean verifyUserExists(String username){
        Connection con = CreateCon();
        String com = "select * from " + db_table + " where username='" + username + "';";
        try{
            PreparedStatement st = con.prepareStatement(com);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                con.close();
                return true;
            }
        }
        catch (Exception e){
            return false;
        }
        return false;
    }

    public static boolean CreateUser(String username){
        boolean userRegistered = verifyUserExists(username);
        Connection con = CreateCon();
        if(userRegistered != false){
            //se exister ele só vai dar um update
            String com = "update " + db_table +" set auth=true where username='" + username + "';";
            try{
                PreparedStatement st = con.prepareStatement(com);
                st.executeUpdate();
                con.close();
                return true;
            }
            catch (Exception exx){
                Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Erro!" + exx);
                return false;
            }
        }
        else{
            //se usuário não existe, criar
            String com = "insert into " + db_table +" values(default, null, '" + username + "', true);";
            try{
                PreparedStatement st = con.prepareStatement(com);
                st.executeUpdate();
                con.close();
                return true;

            }
            catch (Exception exx){
                Bukkit.getConsoleSender().sendMessage("DiscordGuardian | Erro!" + exx);
                return false;
            }
        }
    }
    public static boolean removeUser(String username){
        Connection con = CreateCon();
        String com = "delete from " + db_table + " where username='" + username + "';";
        try{
            PreparedStatement st = con.prepareStatement(com);
            st.executeUpdate();
            con.close();
            return true;
        }
        catch (Exception e){
            return false;
        }
    }


}
