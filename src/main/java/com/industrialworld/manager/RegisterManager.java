package com.industrialworld.manager;

import com.industrialworld.ConstItems;
import com.industrialworld.blocks.BasicMachineBlock;
import com.industrialworld.blocks.CopperOre;
import com.industrialworld.blocks.Grinder;
import com.industrialworld.blocks.IWCraftingTable;
import com.industrialworld.i18n.I18n;
import com.industrialworld.i18n.I18nConst;
import com.industrialworld.item.ItemType;
import com.industrialworld.item.material.IWMaterial;
import com.industrialworld.item.material.info.MaterialInfo;
import com.industrialworld.item.template.ItemIngot;
import com.industrialworld.manager.recipe.ShapedRecipeFactory;
import com.industrialworld.manager.recipe.ShapelessRecipe;
import com.industrialworld.manager.recipe.SmeltingRecipeImpl;
import com.industrialworld.utils.ItemStackUtil;
import com.industrialworld.utils.MaterialUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;

public class RegisterManager {
    public static void registerIWCRecipes() {
        // Example:
        // RecipeRegistry.register(new ShapedRecipeFactory().map('A', new ItemStack(whatever)).pattern("3x3", "Any", "uwa").build(ItemStack you want));

        /* FORGE_HAMMER */
        {
            ShapedRecipeFactory forgeHammerFactory = new ShapedRecipeFactory().map('A', ItemType.INGOT).map('B', new ItemStack(Material.STICK));
            RecipeRegistry.register(forgeHammerFactory.pattern("AAC", "ABB", "AAC").build(ConstItems.FORGE_HAMMER)); // FORGE_HAMMER [L]
            RecipeRegistry.register(forgeHammerFactory.pattern("CAA", "BBA", "CAA").build(ConstItems.FORGE_HAMMER)); // FORGE_HAMMER [R]
            RecipeRegistry.register(forgeHammerFactory.pattern("AAA", "ABA", "CBC").build(ConstItems.FORGE_HAMMER)); // FORGE_HAMMER [U]
            RecipeRegistry.register(forgeHammerFactory.pattern("CBC", "ABA", "AAA").build(ConstItems.FORGE_HAMMER)); // FORGE_HAMMER [D]

            RecipeRegistry.register(new ShapelessRecipe(Arrays.asList(ConstItems.FORGE_HAMMER, new ItemStack(Material.IRON_INGOT)), ConstItems.IRON_PLATE).addItemCost(ConstItems.FORGE_HAMMER, 3));
            RecipeRegistry.register(new ShapelessRecipe(Arrays.asList(ConstItems.FORGE_HAMMER, ConstItems.COPPER_INGOT), ConstItems.COPPER_PLATE).addItemCost(ConstItems.FORGE_HAMMER, 3));
            RecipeRegistry.register(new ShapelessRecipe(Arrays.asList(ConstItems.FORGE_HAMMER, ConstItems.RED_HOT_IRON_INGOT), ConstItems.RED_HOT_STEEL_INGOT).addItemCost(ConstItems.FORGE_HAMMER, 3));
        }
        /* CUTTER */
        {
            RecipeRegistry.register(new ShapedRecipeFactory().map('A', new ItemStack(Material.IRON_INGOT)).map('B', ConstItems.IRON_PLATE).pattern("BCB", "CBC", "ACA").build(ConstItems.CUTTER));
            RecipeRegistry.register(new ShapelessRecipe(Collections.singletonList(ConstItems.COPPER_PLATE), ConstItems.COPPER_WIRE).addItemCost(ConstItems.CUTTER, 4));
        }

        // Basic Machine Block
        RecipeRegistry.register(new ShapedRecipeFactory().map('0', new ItemStack(Material.IRON_INGOT)).pattern("000", "0w0", "000").build(ConstItems.BASIC_MACHINE_BLOCK)); // BASIC_MACHINE_BLOCK

        // Smelting Recipes
        RecipeRegistry.register(new SmeltingRecipeImpl(new ItemStack(Material.IRON_INGOT), ConstItems.RED_HOT_IRON_INGOT));
    }

    public static void registerBlockIS() {
        MainManager.register("BASIC_MACHINE_BLOCK", new BasicMachineBlock());
        MainManager.register("IW_CRAFTING_TABLE", new IWCraftingTable());
        MainManager.register("GRINDER", new Grinder());

        MainManager.register("COPPER_ORE", new CopperOre());

        //getServer().addRecipe(new ShapedRecipe(new NamespacedKey(this, "BASIC_MACHINE_BLOCK"), ConstItems.BASIC_MACHINE_BLOCK).shape("AAA", "ABA", "AAA").setIngredient('A', Material.IRON_INGOT).setIngredient('B', Material.AIR));
    }

    public static void registerItem() {
        // Forge Hammer
        ItemManager.register("FORGE_HAMMER", ItemStackUtil.create(MaterialUtil.IRON_SHOVEL).setId("FORGE_HAMMER").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.FORGE_HAMMER)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.FORGE_HAMMER_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.FORGE_HAMMER_LORE2),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.FORGE_HAMMER_LORE3))).getItemStack());

        // IW Crafting Table
        ItemManager.register("IW_CRAFTING_TABLE", ItemStackUtil.create(Material.CRAFTING_TABLE).setId("IW_CRAFTING_TABLE").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.IW_CRAFTING_TABLE)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.IW_CRAFTING_TABLE_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.IW_CRAFTING_TABLE_LORE2))).getItemStack());
        ItemManager.register("IRON_PLATE", ItemStackUtil.create(Material.PAPER).setId("IRON_PLATE").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.IRON_PLATE)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.IRON_PLATE_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Element.Fe))).getItemStack());
        ItemManager.register("CUTTER", ItemStackUtil.create(Material.SHEARS).setId("CUTTER").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.CUTTER)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.CUTTER),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.CUTTER_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.CUTTER_LORE2))).getItemStack());
        ItemManager.register("BASIC_MACHINE_BLOCK", ItemStackUtil.create(Material.IRON_BLOCK).setId("BASIC_MACHINE_BLOCK").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.BASIC_MACHINE_BLOCK)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.BASIC_MACHINE_BLOCK_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.BASIC_MACHINE_BLOCK_LORE2))).getItemStack());
        ItemManager.register("COPPER_INGOT", ItemStackUtil.create(Material.BRICK).setId("COPPER_INGOT").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_INGOT)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_INGOT_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.COPPER_INGOT_LORE2))).getItemStack());
        ItemManager.register("COPPER_PLATE", ItemStackUtil.create(Material.PAPER).setId("COPPER_PLATE").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_PLATE)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_PLATE_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.COPPER_PLATE_LORE2))).getItemStack());
        ItemManager.register("COPPER_WIRE", ItemStackUtil.create(Material.IRON_BARS).setId("COPPER_WIRE").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_WIRE)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_WIRE_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.COPPER_WIRE_LORE2))).getItemStack());
        ItemManager.register("COPPER_ORE", ItemStackUtil.create(Material.IRON_ORE).setId("COPPER_ORE").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_ORE)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.COPPER_ORE_LORE1),
                ChatColor.GRAY + I18n.getLocaleString(I18nConst.Item.COPPER_ORE_LORE2))).getItemStack());
        ItemManager.register("RED_HOT_IRON_INGOT", ItemStackUtil.create(Material.BRICK).setId("RED_HOT_IRON_INGOT").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.RED_HOT_IRON_INGOT)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.RED_HOT_IRON_INGOT_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.RED_HOT_IRON_INGOT_LORE2))).getItemStack());
        ItemManager.register("STEEL_INGOT", ItemStackUtil.create(Material.IRON_INGOT).setId("STEEL_INGOT").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.STEEL_INGOT)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.STEEL_INGOT_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.STEEL_INGOT_LORE2))).getItemStack());
        ItemManager.register("RED_HOT_STEEL_INGOT", ItemStackUtil.create(Material.BRICK).setId("RED_HOT_STEEL_INGOT").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.RED_HOT_STEEL_INGOT)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.RED_HOT_STEEL_INGOT_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.RED_HOT_STEEL_INGOT_LORE2))).getItemStack());
        ItemManager.register("QUICKLIME", ItemStackUtil.create(Material.BONE_MEAL).setId("QUICKLIME").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.QUICKLIME)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.QUICKLIME_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.QUICKLIME_LORE2))).getItemStack());
        ItemManager.register("TIN_INGOT", ItemStackUtil.create(Material.IRON_INGOT).setId("TIN_INGOT").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.TIN_INGOT)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.TIN_INGOT_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.TIN_INGOT_LORE2))).getItemStack());
        ItemManager.register("ZINC_INGOT", ItemStackUtil.create(Material.IRON_INGOT).setId("ZINC_INGOT").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.ZINC_INGOT)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.ZINC_INGOT_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.ZINC_INGOT_LORE2))).getItemStack());
        ItemManager.register("GRINDER", ItemStackUtil.create(Material.IRON_BLOCK).setId("GRINDER").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.GRINDER)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.GRINDER_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.GRINDER_LORE2))).getItemStack());
        ItemManager.register("BASIC_MOTOR", ItemStackUtil.create(Material.SKELETON_SKULL).setId("BASIC_MOTOR").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.BASIC_MOTOR)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.BASIC_MOTOR_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.BASIC_MOTOR_LORE2))).getItemStack());
        ItemManager.register("REDSTONE_BATTERY", ItemStackUtil.create(Material.SKELETON_SKULL).setId("REDSTONE_BATTERY").setAmount(1).setDisplayName(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.REDSTONE_BATTERY)).setLore(Arrays.asList(
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.REDSTONE_BATTERY_LORE1),
                ChatColor.WHITE + I18n.getLocaleString(I18nConst.Item.REDSTONE_BATTERY_LORE2))).getItemStack());

        ItemManager.register("INGOT_COPPER", ItemIngot.getItemStack(IWMaterial.COPPER));
    }

    public static void registerIWMaterial() {
        IWMaterialManager.register(IWMaterial.COPPER, MaterialInfo.newInstance(IWMaterial.COPPER).setLevel((short) 3));
    }

    public static void registerIWItemMaterial() {
        ItemManager.registerItemMaterial(ItemType.INGOT, IWMaterial.COPPER, Material.BRICK); // Copper Ingot -> Brick
    }
}
