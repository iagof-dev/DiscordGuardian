# 🛡️ DiscordGuardian
Java &amp; C# | Plugin de Minecraft baseado nos servidores de FiveM que necessita entrar no discord e enviar o ID para conseguir conectar no servidor, mas ao invés de ser o id, enviar o nick

# Methods
Plugin - [Repositorio](https://github.com/iagof-dev/DiscordGuardian)

Bot - [Repositorio](https://github.com/iagof-dev/DiscordGuardianBot)

Website - [Repositorio](https://github.com/iagof-dev/DiscordGuardian_Site)


# MySql Table

>create table if not exists allowedUsers ( id int auto_increment primary key, uuid varchar(36) null unique, username varchar(32) not null unique, auth bool not null default false);
