package io.github.jw.spigot.ff.extension.exampleffplugin.services;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerRepo {

    private final Plugin plugin;

    public PlayerRepo(Plugin plugin) {
        this.plugin = plugin;
    }

    public List<Player> fetchPlayersFromDb() {
        return new ArrayList<>(plugin.getServer().getOnlinePlayers());
    }
}
