package io.github.jw.spigot.ff.extension.exampleffplugin.services;

import org.bukkit.entity.Player;

import java.util.List;

public class PlayerManager
{
    private final PlayerRepo playerRepository;

    public PlayerManager(PlayerRepo playerRepository) {
        this.playerRepository = playerRepository;
    }


    public List<Player> getPlayer()
    {
        return playerRepository.fetchPlayersFromDb();
    }
}
