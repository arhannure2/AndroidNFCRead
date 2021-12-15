package com.example.anroidnfcread



import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.nfc.*
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.Settings

import android.text.Html
import android.text.Spanned
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.anroidnfcread.HelperDialog.positiveButton
import kotlinx.android.synthetic.main.activity_main.*
import android.nfc.NfcAdapter.ReaderCallback

import android.os.Parcelable





/*
* 1. NFC supported ?
* Device has to support NFC feature ( Hardware Feature )
* 2. NFC enabled ?
* User must enable NFC feature in device setting.
* 3. If NFC isn ot enable in setting you may ask user to enable is via Setting screen.
* 4. Now user can tab NFC Tag on the mobile to read.
* 5. Once we get intent just disply in on screen and show it to use and process data according to your requirement
*
*
*
* */

class MainActivity : AppCompatActivity() {
    // NFC adapter for checking NFC state in the device
    private var nfcAdapter : NfcAdapter? = null

    // Pending intent for NFC intent foreground dispatch.
    // Used to read all NDEF tags while the app is running in the foreground.
    private var nfcPendingIntent: PendingIntent? = null
    // Optional: filter NDEF tags this app receives through the pending intent.
    //private var nfcIntentFilters: Array<IntentFilter>? = null

    private val KEY_LOG_TEXT = "logText"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        // Restore saved text if available
        if (savedInstanceState != null) {
            tv_messages.text = savedInstanceState.getCharSequence(KEY_LOG_TEXT)
        }




//        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
//        logMessage("NFC supported", (nfcAdapter != null).toString())
//        logMessage("NFC enabled", (nfcAdapter?.isEnabled).toString())

        /*
        *
        * */

        // Check if NFC is supported and enabled
//        val manager = getSystemService(Context.NFC_SERVICE) as NfcManager
//        nfcAdapter = manager.getDefaultAdapter()
         nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            // NFC is not available for device
            logMessage("NFC supported: ","NFC is not available on device")

        }else{
            // NFC is available
            logMessage("NFC supported", (nfcAdapter != null).toString())

        }


        if (!nfcAdapter!!.isEnabled) {
            // NFC is available for device but not enabled
           // logMessage("NFC supported: ","NFC is available for device but not enabled")
            logMessage("NFC enabled:", (nfcAdapter?.isEnabled).toString())
            buttonEnable.visibility = View.VISIBLE



        } else {
            // NFC is enabled
           // logMessage("NFC is enabled: ","NFC is available for device and also  enabled")
            logMessage("NFC enabled:", (nfcAdapter?.isEnabled).toString())
            buttonEnable.visibility = View.GONE
        }

        buttonEnable.setOnClickListener {
            askToEnableNFC()
        }



        // Read all tags when app is running and in the foreground
        // Create a generic PendingIntent that will be deliver to this activity. The NFC stack
        // will fill in the intent with the details of the discovered tag before delivering to
        // this activity.
        nfcPendingIntent = PendingIntent.getActivity(this, 0,
            Intent(this, javaClass).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0)

        // Optional: Setup an intent filter from code for a specific NDEF intent
        // Use this code if you are only interested in a specific intent and don't want to
        // interfere with other NFC tags.
        // In this example, the code is commented out so that we get all NDEF messages,
        // in order to analyze different NDEF-formatted NFC tag contents.
        //val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        //ndef.addCategory(Intent.CATEGORY_DEFAULT)
        //ndef.addDataScheme("https")
        //ndef.addDataAuthority("*.diyarme.com", null)
        //ndef.addDataPath("/", PatternMatcher.PATTERN_PREFIX)
        // More information: https://stackoverflow.com/questions/30642465/nfc-tag-is-not-discovered-for-action-ndef-discovered-action-even-if-it-contains
        //nfcIntentFilters = arrayOf(ndef)

        if (intent != null) {
            // Check if the app was started via an NDEF intent
            logMessage("Found intent in onCreate", intent.action.toString())
          //  processIntent(intent)
           resolveIntent(intent)
        }



        // Make sure the text view is scrolled down so that the latest messages are visible
        scrollDown()
    }


    override fun onResume() {
        super.onResume()
        // Get all NDEF discovered intents
        // Makes sure the app gets all discovered NDEF messages as long as it's in the foreground.
        nfcAdapter?.enableForegroundDispatch(this, nfcPendingIntent, null, null);
        // Alternative: only get specific HTTP NDEF intent
        //nfcAdapter?.enableForegroundDispatch(this, nfcPendingIntent, nfcIntentFilters, null);
    }

    override fun onPause() {
        super.onPause()
        // Disable foreground dispatch, as this activity is no longer in the foreground
        nfcAdapter?.disableForegroundDispatch(this);
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        logMessage("Found intent in onNewIntent", intent?.action.toString())
        // If we got an intent while the app is running, also check if it's a new NDEF message
        // that was discovered
       // if (intent != null) processIntent(intent)
         if (intent != null) resolveIntent(intent)

    }

    /**
     * Check if the Intent has the action "ACTION_NDEF_DISCOVERED". If yes, handle it
     * accordingly and parse the NDEF messages.
     * @param checkIntent the intent to parse and handle if it's the right type
     */
//    private fun processIntent(checkIntent: Intent) {
//        // Check if intent has the action of a discovered NFC tag
//        // with NDEF formatted contents
//        if (checkIntent.action == NfcAdapter.ACTION_NDEF_DISCOVERED) {
//            logMessage("New NDEF intent", checkIntent.toString())
//
//            // Retrieve the raw NDEF message from the tag
//            val rawMessages = checkIntent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
//            logMessage("Raw messages", rawMessages!!.size.toString())
//
//            // Complete variant: parse NDEF messages
//            if (rawMessages != null) {
//                val messages = arrayOfNulls<NdefMessage?>(rawMessages.size)// Array<NdefMessage>(rawMessages.size, {})
//                for (i in rawMessages.indices) {
//                    messages[i] = rawMessages[i] as NdefMessage;
//                }
//                // Process the messages array.
//                processNdefMessages(messages)
//            }
//
//            // Simple variant: assume we have 1x URI record
//            //if (rawMessages != null && rawMessages.isNotEmpty()) {
//            //    val ndefMsg = rawMessages[0] as NdefMessage
//            //    if (ndefMsg.records != null && ndefMsg.records.isNotEmpty()) {
//            //        val ndefRecord = ndefMsg.records[0]
//            //        if (ndefRecord.toUri() != null) {
//            //            logMessage("URI detected", ndefRecord.toUri().toString())
//            //        } else {
//            //            // Other NFC Tags
//            //            logMessage("Payload", ndefRecord.payload.contentToString())
//            //        }
//            //    }
//            //}
//
//        }else if (checkIntent.action == NfcAdapter.ACTION_TAG_DISCOVERED)
//        {
//            logMessage("New TAG_DISCOVERED : ", checkIntent.toString())
//
//            val ndefMessage = getNdefMessages(checkIntent)
//
//
//            HelperDialog.alertDialog(this, icon = R.drawable.checked) {
//                setTitle("TAG DISCOVERED!")
//                setMessage(ndefMessage)
//                positiveButton(positiveButtonText = getString(R.string.okay)) {
//                    //do positive actions
//                    it.dismiss()
//
//                }
//            }.show()
//
//        }else if (checkIntent.action == NfcAdapter.ACTION_TECH_DISCOVERED)
//        {
//
//        }
//
//
//    }

    /**
     * Parse the NDEF message contents and print these to the on-screen log.
     */
//    private fun processNdefMessages(ndefMessages: Array<NdefMessage?>) {
//        // Go through all NDEF messages found on the NFC tag
//        for (curMsg in ndefMessages) {
//            if (curMsg != null) {
//                // Print generic information about the NDEF message
//                logMessage("Message", curMsg.toString())
//                // The NDEF message usually contains 1+ records - print the number of recoreds
//                logMessage("Records", curMsg.records.size.toString())
//
//                // Loop through all the records contained in the message
//                for (curRecord in curMsg.records) {
//                    if (curRecord.toUri() != null) {
//                        // URI NDEF Tag
//                        logMessage("- URI", curRecord.toUri().toString())
//                    } else {
//                        // Other NDEF Tags - simply print the payload
//                        logMessage("- Contents", curRecord.payload.contentToString())
//                    }
//                }
//            }
//        }
//    }



    private fun resolveIntent(intent: Intent) {
        val action = intent.action
        if (NfcAdapter.ACTION_TAG_DISCOVERED == action || NfcAdapter.ACTION_TECH_DISCOVERED == action || NfcAdapter.ACTION_NDEF_DISCOVERED == action) {
            val tag: Tag = (intent.getParcelableExtra<Parcelable>(NfcAdapter.EXTRA_TAG) as Tag?)!!
            val payload: String = HelperTagData.detectTagData(tag)
            logMessage(getString(R.string.nfc_title), payload.toString())


            HelperDialog.alertDialog(this, icon = R.drawable.checked) {
                setTitle(getString(R.string.nfc_title))
                setMessage(payload.toString())
                positiveButton(positiveButtonText = getString(R.string.okay)) {
                    //do positive actions
                    it.dismiss()

                }
            }.show()


        }
    }



    // Utility functions

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putCharSequence(KEY_LOG_TEXT, tv_messages.text)
        super.onSaveInstanceState(outState)


    }

    /**
     * Log a message to the debug text view.
     * @param header title text of the message, printed in bold
     * @param text optional parameter containing details about the message. Printed in plain text.
     */
    private fun logMessage(header: String, text: String?) {
        tv_messages.append(if (text.isNullOrBlank()) fromHtml("<b>$header</b><br>") else fromHtml("<b>$header</b>: $text<br>"))
        scrollDown()
    }

    /**
     * Convert HTML formatted strings to spanned (styled) text, for inserting to the TextView.
     * Externalized into an own function as the fromHtml(html) method was deprecated with
     * Android N. This method chooses the right variant depending on the OS.
     * @param html HTML-formatted string to convert to a Spanned text.
     */
    private fun fromHtml(html: String): Spanned {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            @Suppress("DEPRECATION")
            Html.fromHtml(html)
        }
    }

    /**
     * Scroll the ScrollView to the bottom, so that the latest appended messages are visible.
     */
    private fun scrollDown() {
        sv_messages.post({ sv_messages.smoothScrollTo(0, sv_messages.bottom) })
    }



    private fun askToEnableNFC()
    {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
        {
            val  intent = Intent(Settings.ACTION_NFC_SETTINGS)
            startActivity(intent);
        }
        else{
            val intent = Intent(Settings.ACTION_WIRELESS_SETTINGS)
            startActivity(intent);
        }
    }


    fun getNdefMessages(intent: Intent): String? {
        var msgs: Array<NdefMessage?>? = null
        val action = intent.action
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == action || NfcAdapter.ACTION_TAG_DISCOVERED == action) {


            val rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)

            if (rawMsgs != null) {
                msgs = arrayOfNulls(rawMsgs.size)
                for (i in rawMsgs.indices) {
                    msgs[i] = rawMsgs[i] as NdefMessage
                }
            } else {
                val empty = byteArrayOf()
                val record = NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty)
                val msg = NdefMessage(arrayOf(record))
                msgs = arrayOf(msg)
            }
        }
        return msgs?.toString() ?: "No Tag discovered!"
    }
}
