package io.github.jw.spigot.ff.extension.exampleffplugin;

import io.github.jw.spigot.ff.extension.exampleffplugin.services.PlayerManager;
import io.github.jw.spigot.ff.extension.exampleffplugin.services.PlayerRepo;
import io.github.jwdeveloper.ff.core.spigot.commands.api.enums.ArgumentType;
import io.github.jwdeveloper.ff.plugin.api.FluentApiSpigotBuilder;
import io.github.jwdeveloper.ff.plugin.api.extention.FluentApiExtension;
import io.github.jwdeveloper.ff.plugin.implementation.FluentApi;
import io.github.jwdeveloper.ff.plugin.implementation.FluentApiSpigot;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.event.player.PlayerJoinEvent;

public class PluginExtension implements FluentApiExtension {
    @Override
    public void onConfiguration(FluentApiSpigotBuilder builder) {


        /* Dependency injection container
         *  more information - https://github.com/jwdeveloper/DepenDance
         */
        var container = builder.container();
        container.registerSingleton(PlayerRepo.class);
        container.registerTransient(PlayerManager.class);
    }

    /**
     * Basically same thing as Plugin.onEnable() but this method providers
     * fluentAPI object
     */
    @Override
    public void onFluentApiEnable(FluentApiSpigot fluentAPI) throws Exception {

        //Getting object instance from container
        PlayerManager playerManager = fluentAPI.container().findInjection(PlayerManager.class);

        //events
        fluentAPI.events().onEvent(PlayerJoinEvent.class, event ->
        {
            FluentApi.messages()
                    .title()
                    .withTitle("Welcome on the server")
                    .withSubTitle(event.getPlayer().getDisplayName())
                    .buildAndSend(event.getPlayer());
        });

        //tasks
        fluentAPI.tasks().taskTimer(5, (iteration, task) ->
        {
            Bukkit.getOnlinePlayers().forEach(player ->
            {
                player.spawnParticle(Particle.HEART, player.getLocation(), 3);
            });
        }).start();

        //dynamically added commands
        fluentAPI.createCommand("test-cmd")
                .propertiesConfig(config ->
                {
                    config.setLabel("label");
                    config.addPermissions("player.good");
                })
                .eventsConfig(config ->
                {
                    config.onPlayerExecute(event ->
                    {
                        event.getPlayer().sendMessage("hello world");
                    });
                })
                .argumentsConfig(argumentConfig ->
                {
                    argumentConfig.addArgument("name", ArgumentType.TEXT);
                })
                .buildAndRegister();
    }


    /**
     * Basically same thing as Plugin.onDiable() but this method providers
     * fluentAPI object
     */
    @Override
    public void onFluentApiDisabled(FluentApiSpigot fluentAPI) throws Exception {

    }
}
