package net.darkhax.wawla;

import java.util.Arrays;

import net.darkhax.wawla.handler.IMCHandler;
import net.darkhax.wawla.proxy.ProxyCommon;
import net.darkhax.wawla.util.Constants;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION, dependencies = "required-after:Waila")
public class Wawla {

    @SidedProxy(serverSide = Constants.SERVER, clientSide = Constants.CLIENT)
    public static ProxyCommon proxy;

    @Mod.Instance(Constants.MODID)
    public static Wawla instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        setModMeta(event.getModMetadata());
        proxy.registerSidedEvents();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.vanillamc.AddonVanillaEntities.registerAddon");
        FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.vanillamc.AddonVanillaTiles.registerAddon");
        FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.generic.AddonGenericEntities.registerAddon");
        FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.generic.AddonGenericTiles.registerAddon");

        if (Loader.isModLoaded("pixelmon")) {

            FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.pixelmon.AddonPixelmonEntities.registerAddon");
            FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.pixelmon.AddonPixelmonTiles.registerAddon");
        }

        if (Loader.isModLoaded("Thaumcraft"))
            FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.thaumcraft.AddonThaumcraftTiles.registerAddon");

        if (Loader.isModLoaded("TConstruct"))
            FMLInterModComms.sendMessage("Waila", "register", "net.darkhax.wawla.addons.tinkersconstruct.AddonTinkersTiles.registerAddon");

        proxy.registerSidedModules();
    }

    @EventHandler
    public void messageRecieved(FMLInterModComms.IMCEvent event) {

        for (IMCMessage message : event.getMessages())
            IMCHandler.readMeassage(message);
    }

    /**
     * Method to set information about the mod. This is an in game alternative to the mcmod.info file and
     * is primarily used by the list of mods in the main menu added by Forge Mod Loader.
     * 
     * @param meta: The ModMetadata for the mod. This can be obtained from the preInit event.
     */
    void setModMeta(ModMetadata meta) {

        meta.authorList = Arrays.asList("Darkhax");
        meta.credits = "Maintained by Darkhax. Critical additions made by lclc98 and Ghostrec35";
        meta.description = "This mod aims to provide more information about the minecraft world by bridging the gaps between Waila and other mods. This mod depends on prior work done by ChickenBones and ProfMobius";
        meta.autogenerated = false;
    }
}