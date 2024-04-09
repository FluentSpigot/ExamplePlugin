package io.github.jw.spigot.ff.extension.exampleffplugin;


import io.github.jwdeveloper.ff.extension.commands.api.annotations.Argument;
import io.github.jwdeveloper.ff.extension.commands.api.annotations.Command;
import io.github.jwdeveloper.ff.plugin.implementation.FluentApi;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.C;

@Command(name = "example-command")
public class PluginCommand {

    @Command
    public void onCommand(Player player) {
        FluentApi.messages().chat()
                .text("this is default method call")
                .send(player);
    }


    @Command(name = "player-name")
    public void onCommandName(Player player) {
        var messages = FluentApi.messages();
        messages.chat()
                .color(ChatColor.GRAY)
                .text("My name is", ChatColor.GREEN, player.getName())
                .send(player);
    }

    @Command(name = "arguments")
    @Argument(name = "name")
    @Argument(name = "lastName")
    public void onCommandName(Player player, String name, String lastName) {
        var messages = FluentApi.messages();
        messages.chat()
                .color(ChatColor.GRAY)
                .text("My name is", ChatColor.GREEN, name, ChatColor.RED, lastName)
                .send(player);
    }
}
