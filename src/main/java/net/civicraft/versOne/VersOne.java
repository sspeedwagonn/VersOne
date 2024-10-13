package net.civicraft.versOne;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;

public final class VersOne extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("VersOne has been enabled! :D");
    }

    @EventHandler
    public void onSwitch(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.isSneaking() && (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            ItemStack holding = p.getInventory().getItemInMainHand();
            if (holding.getType() != Material.AIR) {
                List<Material> tools = List.of(Material.STONE_SWORD, Material.STONE_HOE, Material.STONE_SHOVEL, Material.STONE_PICKAXE, Material.STONE_AXE);
                if (tools.contains(holding.getType())) {
                    int currentIndex = tools.indexOf(holding.getType());
                    int nextIndex = (currentIndex + 1) % tools.size();
                    Material nextTool = tools.get(nextIndex);

                    ItemMeta meta = holding.getItemMeta();
                    int damage = ((Damageable) meta).getDamage();

                    ItemStack newTool = new ItemStack(nextTool, 1);
                    ItemMeta newMeta = newTool.getItemMeta();
                    ((Damageable) newMeta).setDamage(damage);
                    newTool.setItemMeta(newMeta);
                    p.getInventory().setItemInMainHand(newTool);
                }
            }
        }
    }


    @Override
    public void onDisable() {
        getLogger().info("VersOne has been disabled :(");
    }
}
