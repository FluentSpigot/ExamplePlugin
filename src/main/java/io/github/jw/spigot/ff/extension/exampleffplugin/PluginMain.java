package io.github.jw.spigot.ff.extension.exampleffplugin;

import io.github.jwdeveloper.ff.plugin.FluentPlugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        FluentPlugin.initialize(this)
                .withExtension(new PluginExtension())
                .withCommand(fluentCommandOptions ->
                {
                    fluentCommandOptions.setDefaultCommand(PluginCommand.class);
                })
                .create();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
