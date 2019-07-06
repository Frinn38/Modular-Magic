package fr.frinn.modularmagic.event;

import fr.frinn.modularmagic.tile.TileStarlightInput;
import fr.frinn.modularmagic.tile.TileStarlightOutput;
import hellfirepvp.astralsorcery.common.event.StarlightNetworkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerModularMagic {

    @SubscribeEvent
    public static void onStarlightTransmissionRegister(StarlightNetworkEvent.TransmissionRegister event) {
        event.getRegistry().registerProvider(new TileStarlightInput.StarlightProviderReceiverProvider());
    }

    @SubscribeEvent
    public static void onStarlightSourceRegister(StarlightNetworkEvent.SourceProviderRegistry event) {
        event.getRegistry().registerProvider(new TileStarlightOutput.Provider());
    }
}
