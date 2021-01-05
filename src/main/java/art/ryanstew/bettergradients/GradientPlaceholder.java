package art.ryanstew.bettergradients;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GradientPlaceholder extends PlaceholderExpansion
{
    private BetterGradients plugin;
    private ColorUtil util;
    private String hexCodeRegex = "#*[A-Fa-f0-9]{6}";

    public GradientPlaceholder(BetterGradients plugin, ColorUtil util)
    {
        this.plugin = plugin;
        this.util = util;
    }

    public String getIdentifier()
    {
        return "BetterGradients";
    }

    @Override
    public String getAuthor()
    {
        return "CarbonAPI";
    }

    @Override
    public String getVersion()
    {
        return "1.0.0";
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier)
    {
        // %bettergradients_<hex1>_<hex2>_<message>%
        if (identifier.matches(hexCodeRegex + "_" + hexCodeRegex + "_.+"))
        {
            return getGradient(identifier);
        }

        // %bettergradients_<suffix>_<hex1>_<hex2>_<message>%
        if (identifier.matches(".*_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
        {
            Pattern p = Pattern.compile("(.+)_" + hexCodeRegex + "_" + hexCodeRegex + "_");
            Matcher m = p.matcher(identifier);

            String mods = "";
            while (m.find()) mods = m.group(1);

            return getModifiedGradient(identifier.replaceAll(mods + "_", ""), mods);
        }
        return null;
    }

    private String getGradient(String identifier)
    {
        Pattern hexPattern = Pattern.compile(hexCodeRegex);
        Matcher hexMatcher = hexPattern.matcher(identifier);
        Pattern messagePattern = Pattern.compile("_" + hexCodeRegex + "_(.+)");
        Matcher messageMatcher = messagePattern.matcher(identifier);

        String message = "";
        while (messageMatcher.find()) message = messageMatcher.group(1);

        ArrayList<String> hexCodes = new ArrayList<>();
        while (hexMatcher.find()) hexCodes.add(hexMatcher.group());

        return util.makeGradientText(hexCodes.get(0), hexCodes.get(1), message);
    }

    private String getModifiedGradient(String identifier, String modifier)
    {
        String returnValue = getGradient(identifier);
        Pattern p = Pattern.compile("&x(?:&[A-Fa-f0-9]){6}.{1}");
        Matcher m = p.matcher(returnValue);

        String message = "";
        while (m.find())
        {
            String groupText = m.group();
            message += groupText.substring(0, groupText.length() - 1) + modifier + groupText.substring(groupText.length() - 1);
        }

        return message;
    }
}
