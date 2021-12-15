# Android NFC Read In Kotlin 

## ScreenShot: 

<p float="left">
  <img src="https://user-images.githubusercontent.com/57435729/146185056-0d0e3815-51e1-421b-ba61-ecb58a52fca9.png" width="150" height="300">
  <img src="https://user-images.githubusercontent.com/57435729/146185067-c6dd5c09-0abc-410b-84f7-3899b396443f.png" width="150" height="300">
  <img src="https://user-images.githubusercontent.com/57435729/146185085-073ed01a-345d-4de3-a3e0-8b276555f967.png" width="150" height="300">
   <img src="https://user-images.githubusercontent.com/57435729/146185098-326eaae6-2620-4926-914f-509b4fcc84c2.png" width="150" height="300">

</p>




# About Android NFC Read Demo Application 
- Initial screen it will check whether the device supports NFC or not.
- If the device supports NFC hardware feature then It also checks  its enable in setting ?
- If  NFC feature does not enabled in setting it ask user to enable it via Enable button
- Once found that the  device  supports NFC feature and its enable in setting then It handle NFC tags in app startup (intent delivered through onCreate()) and when the app is already running (onNewIntent())
- Set a pending intent for enabling foreground dispatch to read and analyze all NFC tags tapped while the app is running.
