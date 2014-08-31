package net.darkhax.wawla.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constants {
    
    public static final String MODID = "wawla";
    public static final String MOD_NAME = "What Are We Looking At";
    public static final String VERSION = "1.0.6";
    public static final String SERVER = "net.darkhax.wawla.proxy.ProxyCommon";
    public static final String CLIENT = "net.darkhax.wawla.proxy.ProxyClient";
    
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);
    public static final DateFormat DATE = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
}
