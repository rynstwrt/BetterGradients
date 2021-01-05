# BetterGradients
A PlaceholderAPI expansion that creates placeholders for gradient text. Unlike other similar plugins, BetterGradients supports chat modifiers like &amp;l and &amp;n.

Works everywhere RGB color works! Converts a hex code like #FF0000 to &x&f&f&0&0&0&0. Note: RGB color does not work in scoreboards, but it does work on things like the server MOTD. 

# Placeholders
- `%BetterGradients_<hex1>_<hex2>_<message>%`
  - Example: `%BetterGradients_#FF6600_#FF00FF_This is a test message%`
  - Example Command: `/papi parse me %BetterGradients_#FF6600_#FF00FF_Test Message%`
  - Output: ![This is a test message with a gradient](https://i.imgur.com/l2gyPQM.png)

- `%BetterGradients_<chat modifiers>_<hex1>_<hex2>_<message>%`
  - Example: `%BetterGradients_&l&n&o_#FF6600_#FF00FF_This is a test message%`
  - Example Command: `/papi parse me %BetterGradients_&l&n&o_#FF6600_#FF00FF_This is a test message%`
  - Output: ![This is a test message with a gradient and bold, underline, and italic chat modifiers](https://i.imgur.com/gCnlnAp.png)
