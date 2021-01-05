package art.ryanstew.bettergradients;

import java.awt.Color;

public class ColorUtil
{
    BetterGradients plugin;

    public ColorUtil(BetterGradients plugin)
    {
        this.plugin = plugin;
    }

    public int[] hexToRGBArray(String hex)
    {
        return new int[] { Integer.parseInt(hex.substring(1, 3), 16),
                Integer.parseInt(hex.substring(3, 5), 16),
                Integer.parseInt(hex.substring(5, 7), 16) };
    }

    public String rgbToHex(int r, int g, int b)
    {
        r = Integer.parseInt(Integer.toString(r, 10));
        g = Integer.parseInt(Integer.toString(g, 10));
        b = Integer.parseInt(Integer.toString(b, 10));

        return "#" + Integer.toHexString(new Color(r, g, b).getRGB()).substring(2);
    }

    public String makeGradientText(String hex1, String hex2, String text)
    {
        int[] rgbArray1 = hexToRGBArray(hex2);
        int[] rgbArray2 = hexToRGBArray(hex1);
        String[] splitText = text.split("");

        String newText = "";
        for(int i = 0; i < splitText.length; ++i)
        {
            double alpha = i / Double.valueOf(splitText.length);
            int r = interpolateColor(rgbArray1[0], rgbArray2[0], alpha);
            int g = interpolateColor(rgbArray1[1], rgbArray2[1], alpha);
            int b = interpolateColor(rgbArray1[2], rgbArray2[2], alpha);

            String newColorCode = "&x";
            String hex = rgbToHex(r, g, b);
            String[] splitHex = hex.split("");
            for (int j = 0; j < splitHex.length; ++j)
            {
                if (splitHex[j].equalsIgnoreCase("#")) continue;
                newColorCode += "&" + splitHex[j];
            }

            newText += newColorCode + splitText[i];
        }

        return newText;
    }

    private int interpolateColor(int startValue, int endValue, double alpha)
    {
        return (int) Math.round(startValue * alpha + endValue * (1 - alpha));
    }
}