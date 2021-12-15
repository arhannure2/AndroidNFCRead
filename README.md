# Android NFC Read In Kotlin 

## ScreenShot Notes : 
1. NFC supported ?
Device has to support NFC feature ( Hardware Feature )
2. NFC enabled ?
User must enable NFC feature in device setting.
3. If NFC isn ot enable in setting you may ask user to enable is via Setting screen.
4. Now user can tab NFC Tag on the mobile to read.
5. Once we get intent just disply in on screen and show it to use and process data according to your requirement.



# About Android NFC Read Demo Application 
- Initial screen it will check whether the device supports NFC or not.
- If the device supports NFC hardware feature then It also checks  its enable in setting ?
- If  NFC feature does not enabled in setting it ask user to enable it via Enable button
- Once found that the  device  supports NFC feature and its enable in setting then It handle NFC tags in app startup (intent delivered through onCreate()) and when the app is already running (onNewIntent())
- Set a pending intent for enabling foreground dispatch to read and analyze all NFC tags tapped while the app is running.
