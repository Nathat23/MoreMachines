package uk.antiperson.moremachines.item;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import uk.antiperson.moremachines.MoreMachines;
import uk.antiperson.moremachines.config.machines.MachineConfig;
import uk.antiperson.moremachines.machines.MachineType;

import java.util.List;

public class RecipeHandler {

    private MoreMachines mm;

    public RecipeHandler(MoreMachines mm) {
        this.mm = mm;
    }

    /**
     * Registers all the recipes for all machines.
     */
    public void registerRecipes() {
        for (MachineType type : MachineType.values()) {
            MachineConfig machineConfig = mm.getConfigManager().getMachine(type);
            List<String> keys = machineConfig.getRecipeKey();
            List<String> shape = machineConfig.getRecipeShape();
            ItemStack itemStack = mm.getItemHandler().createItem(type);
            NamespacedKey namespacedKey = new NamespacedKey(mm, type.toString());
            ShapedRecipe recipe = new ShapedRecipe(namespacedKey, itemStack);
            recipe.shape(shape.get(0), shape.get(1), shape.get(2));
            for (String key : keys) {
                String[] split = key.split(":");
                char symbol = split[0].charAt(0);
                Material material = Material.matchMaterial(split[1]);
                recipe.setIngredient(symbol, material);
            }
            mm.getServer().addRecipe(recipe);
        }
    }
}
