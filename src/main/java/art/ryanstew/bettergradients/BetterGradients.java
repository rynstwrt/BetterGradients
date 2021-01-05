package art.ryanstew.bettergradients;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterGradients extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
        {
            // register PAPI hook.
            new GradientPlaceholder(this, new ColorUtil(this)).register();
        }
    }
}
