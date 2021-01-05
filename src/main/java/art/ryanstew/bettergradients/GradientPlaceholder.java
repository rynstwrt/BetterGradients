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
        return "bettergradients";
    }

    @Override
    public String getAuthor()
    {
        return "CarbonAPI / Ryan Stewart";
    }

    @Override
    public String getVersion()
    {
        return "0.0.1";
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

//        // %bettergradients_bold_<hex1>_<hex2>_<message>%
//        if (identifier.matches("bold_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_", ""), "&l");
//        }
//
//        // %bettergradients_italic_<hex1>_<hex2>_<message>%
//        if (identifier.matches("italic_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("italic_", ""), "&o");
//        }
//
//        // %bettergradients_underlined_<hex1>_<hex2>_<message>%
//        if (identifier.matches("underlined_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("underlined_", ""), "&n");
//        }
//
//        // %bettergradients_strikethrough_<hex1>_<hex2>_<message>%
//        if (identifier.matches("strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("strikethrough_", ""), "&m");
//        }
//
//        // %bettergradients_bold_italic_<hex1>_<hex2>_<message>%
//        if (identifier.matches("bold_italic_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_italic_", ""), "&l&o");
//        }
//
//        // %bettergradients_bold_italic_<hex1>_<hex2>_<message>%
//        if (identifier.matches("bold_underlined_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_underlined_", ""), "&l&n");
//        }
//
//        // %bettergradients_bold_strikethrough_<hex1>_<hex2>_<message>%
//        if (identifier.matches("bold_strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_strikethrough_", ""), "&l&m");
//        }
//
//        // %bettergradients_italic_underlined_<hex1>_<hex2>_<message>%
//        if (identifier.matches("italic_underlined_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("italic_underlined_", ""), "&o&n");
//        }
//
//        // %bettergradients_italic_strikethrough_<hex1>_<hex2>_<message>%
//        if (identifier.matches("italic_strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("italic_strikethrough_", ""), "&o&m");
//        }
//
//        // underlined strikethrough
//        if (identifier.matches("underlined_strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("underlined_strikethrough_", ""), "&n&m");
//        }
//
//        // bold italic underlined
//        if (identifier.matches("bold_italic_underlined_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_italic_underlined_", ""), "&l&o&n");
//        }
//
//        // bold italic strikethrough
//        if (identifier.matches("bold_italic_strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_italic_strikethrough_", ""), "&l&o&m");
//        }
//
//        // bold underlined strikethrough
//        if (identifier.matches("bold_underlined_strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_underlined_strikethrough_", ""), "&l&n&m");
//        }
//
//        // italic underlined strikethrough
//        if (identifier.matches("italic_underlined_strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("italic_underlined_strikethrough_", ""), "&o&n&m");
//        }
//
//        // bold italic underlined strikethrough
//        if (identifier.matches("bold_italic_underlined_strikethrough_" + hexCodeRegex + "_" + hexCodeRegex + "_.+"))
//        {
//            return getModifiedGradient(identifier.replaceAll("bold_italic_underlined_strikethrough_", ""), "&l&o&n&m");
//        }

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
