package com.IndustrialWorld;

import com.IndustrialWorld.event.EventListener;
import com.IndustrialWorld.event.TickEvent;
import com.IndustrialWorld.i18n.I18n;
import com.IndustrialWorld.manager.MainManager;
import com.IndustrialWorld.manager.RegisterManager;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public final class IndustrialWorld extends JavaPlugin {
    public static YamlConfiguration blocksConfig;
    public static YamlConfiguration config;
    public static Locale locale;
    public static String localeString;

    public static IndustrialWorld instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getServer().getPluginManager().registerEvents(new EventListener(), this);
        if (getDataFolder().isDirectory() || getDataFolder().mkdirs()) ;
        File block_yml = new File(getDataFolder(), "blocks.yml");
        if (!(block_yml.isFile())) {
            try {
                block_yml.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        blocksConfig = YamlConfiguration.loadConfiguration(block_yml);
        MainManager.loadBlocksFromConfig(blocksConfig);

        if (getDataFolder().isDirectory() || getDataFolder().mkdirs()) ;
        File config_yml = new File(getDataFolder(), "config.yml");
        if (!(config_yml.isFile())) {
            saveDefaultConfig();
        }
        config = YamlConfiguration.loadConfiguration(config_yml);
        localeString = config.getString("lang");

        RegisterManager.registerIWCRecipes();
        RegisterManager.registerBlockIS();

        getServer().addRecipe(new ShapedRecipe(new NamespacedKey(this, "IW_CRAFTING_TABLE"), ConstItems.IW_CRAFTING_TABLE).shape("AAA", "ABA", "AAA").setIngredient('A', Material.IRON_INGOT).setIngredient('B', Material.CRAFTING_TABLE));

        getServer().getScheduler().runTaskTimer(this, () -> getServer().getPluginManager().callEvent(new TickEvent()), 0, 0);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MainManager.saveBlocksToConfig(config);
        try {
            config.save(new File(getDataFolder(), "blocks.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final String getDataFolderPath() {
        return getDataFolder().getAbsolutePath();
    }
}
