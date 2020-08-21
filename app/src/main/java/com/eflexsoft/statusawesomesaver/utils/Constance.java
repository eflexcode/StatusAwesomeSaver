package com.eflexsoft.statusawesomesaver.utils;

import android.os.Environment;

import java.io.File;

public class Constance {

    public static final File WhatsAppStatusesDirectory = new File(Environment.getExternalStorageDirectory()+File.separator+"WhatsApp/Media/.Statuses");

    public static final File BusinessWhatsAppDirectory = new File(Environment.getExternalStorageDirectory()+File.separator+"WhatsApp Business/Media/.Statuses");

    public static final File MyDirectory = new File(Environment.getExternalStorageDirectory()+File.separator+"StatusAwesomeSaver");


}
