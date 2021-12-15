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


# More Information 
## *What is NFC ?*
- Short-range wireless communication technology: NFC ( *Near-field communication* ) instantly transmits data over <1 cm (real-life). Thus, it’s easier to say “tap” something.
- Transmission speeds are slow: the maximum is 424 kbit / sec. Sending an image over NFC would take minutes. So, NFC is only used for short transactions (authentication, room access, payments), and as a trigger for other actions (open web sites, pair with Bluetooth or Wi-Fi).

# **NFC Operation Modes**

- **Reader / Writer:** 
The phone reads data from an NFC tag or writes data back to it. The Core NFC API of Apple only supports reading open NFC tags containing NDEF messages. Android phones usually allow reading and writing, giving full access to the tag.


- **Peer to Peer:** 
Ttransmit data directly from one phone to another. Android uses the standardized SNEP protocol (Simple NDEF Exchange Protocol).Google created the branding “Android Beam” for this.
( Update for Android Beam ) :
Android Beam was deprecated starting with Android 10 in January 2019. Google replaced **Android Beam** with the introduction of **Nearby Share**, which is an **AirDrop** competitor by Google

- **Card Emulation:** 
The phone simulates an NFC tag / a smart card. To an external reader device, it could appear like an NFC enabled credit card. This traditionally required dedicated hardware (a “Secure Element”). Now, it’s also possible to simulate the card from apps.

