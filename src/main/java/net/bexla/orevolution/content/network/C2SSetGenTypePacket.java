/*package net.bexla.orevolution.content.network;

import net.bexla.orevolution.content.data.utility.GenType;
import net.bexla.orevolution.content.types.WorldGenSettings;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class C2SSetGenTypePacket {
    private final GenType type;

    public C2SSetGenTypePacket(GenType type) {
        this.type = type;
    }

    public static void encode(C2SSetGenTypePacket pkt, FriendlyByteBuf buf) {
        buf.writeUtf(pkt.type.name());
    }

    public static C2SSetGenTypePacket decode(FriendlyByteBuf buf) {
        String s = buf.readUtf(256);
        try {
            return new C2SSetGenTypePacket(GenType.valueOf(s));
        } catch (Exception e) {
            return new C2SSetGenTypePacket(GenType.RANDOM);
        }
    }

    public static void handle(C2SSetGenTypePacket pkt, Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer sender = ctx.getSender();
            if (sender == null) {
                // In some edge cases there is no sender (dedicated server/world creation), ignore.
                return;
            }
            ServerLevel overworld = sender.server.getLevel(Level.OVERWORLD);
            if (overworld != null) {
                WorldGenSettings.get(overworld).setGenType(pkt.type);
            }
        });
        ctx.setPacketHandled(true);
    }
}
*/