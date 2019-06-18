package fr.frinn.modularmagic;

import fr.frinn.modularmagic.block.ModularMagicBlocks;
import fr.frinn.modularmagic.component.ComponentAspect;
import fr.frinn.modularmagic.component.ComponentGrid;
import fr.frinn.modularmagic.component.ComponentLifeEssence;
import fr.frinn.modularmagic.component.ComponentWill;
import fr.frinn.modularmagic.event.EventHandlerModularMagic;
import fr.frinn.modularmagic.item.ModularMagicItems;
import fr.frinn.modularmagic.proxy.CommonProxy;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModularMagic.MODID, name = ModularMagic.NAME, version = ModularMagic.VERSION, dependencies = "required-after:modularmachinery")
public class ModularMagic {
    public static final String MODID = "modularmagic";
    public static final String NAME = "Modular Magic";
    public static final String VERSION = "1.2.0";

    public static boolean bloodmagicLoaded = false;
    public static boolean thaumcraftLoaded = false;
    public static boolean thaumicJEILoaded = false;
    public static boolean extraUtils2Loaded = false;

    @SidedProxy(modId = MODID, clientSide = "fr.frinn.modularmagic.proxy.ClientProxy", serverSide = "fr.frinn.modularmagic.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.Instance(MODID)
    public static ModularMagic INSTANCE;

    public ModularMagic() {
        MinecraftForge.EVENT_BUS.register(ModularMagicBlocks.class);
        MinecraftForge.EVENT_BUS.register(ModularMagicItems.class);
        MinecraftForge.EVENT_BUS.register(EventHandlerModularMagic.class);
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        bloodmagicLoaded = Loader.isModLoaded("bloodmagic");
        if(bloodmagicLoaded) {
            ComponentType.Registry.register(new ComponentWill());
            ComponentType.Registry.register(new ComponentLifeEssence());
        }
        thaumcraftLoaded = Loader.isModLoaded("thaumcraft");
        thaumicJEILoaded = Loader.isModLoaded("thaumicjei");
        if(thaumcraftLoaded) {
            ComponentType.Registry.register(new ComponentAspect());
        }
        extraUtils2Loaded = Loader.isModLoaded("extrautils2");
        if(extraUtils2Loaded) {
            ComponentType.Registry.register(new ComponentGrid());
        }
        proxy.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }
}
