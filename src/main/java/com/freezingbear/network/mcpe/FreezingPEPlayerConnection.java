package com.freezingbear.network.mcpe;

import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;

import java.util.Set;

/**
 * Created by FreezingBear Team.
 */
public class FreezingPEPlayerConnection extends PlayerConnection {

    @Override
    public CraftPlayer getPlayer() {
        return super.getPlayer();
    }

    @Override
    public void c() {
        super.c();
    }

    @Override
    public NetworkManager a() {
        return super.a();
    }

    @Override
    public void disconnect(String s) {
        super.disconnect(s);
    }

    //NONONO NO VEHICLE!
    @Override
    public void a(PacketPlayInSteerVehicle packetplayinsteervehicle) {
        super.a(packetplayinsteervehicle);
    }

    @Override
    public void a(PacketPlayInFlying packetplayinflying) {
        super.a(packetplayinflying);
    }

    @Override
    public void a(double d0, double d1, double d2, float f, float f1) {
        super.a(d0, d1, d2, f, f1);
    }

    @Override
    public void a(double d0, double d1, double d2, float f, float f1, Set<PacketPlayOutPosition.EnumPlayerTeleportFlags> set) {
        super.a(d0, d1, d2, f, f1, set);
    }

    @Override
    public void teleport(Location dest) {
        super.teleport(dest);
    }

    @Override
    public void teleport(Location dest, Set set) {
        super.teleport(dest, set);
    }

    @Override
    public void a(PacketPlayInBlockDig packetplayinblockdig) {
        super.a(packetplayinblockdig);
    }

    @Override
    public void a(PacketPlayInBlockPlace packetplayinblockplace) {
        super.a(packetplayinblockplace);
    }

    @Override
    public void a(PacketPlayInSpectate packetplayinspectate) {
        super.a(packetplayinspectate);
    }

    @Override
    public void a(PacketPlayInResourcePackStatus packetplayinresourcepackstatus) {
        super.a(packetplayinresourcepackstatus);
    }

    @Override
    public void a(IChatBaseComponent ichatbasecomponent) {
        super.a(ichatbasecomponent);
    }

    @Override
    public void sendPacket(Packet packet) {
        super.sendPacket(packet);
    }

    @Override
    public void a(PacketPlayInHeldItemSlot packetplayinhelditemslot) {
        super.a(packetplayinhelditemslot);
    }

    @Override
    public void a(PacketPlayInChat packetplayinchat) {
        super.a(packetplayinchat);
    }

    @Override
    public void chat(String s, boolean async) {
        super.chat(s, async);
    }

    @Override
    public void a(PacketPlayInArmAnimation packetplayinarmanimation) {
        super.a(packetplayinarmanimation);
    }

    @Override
    public void a(PacketPlayInEntityAction packetplayinentityaction) {
        super.a(packetplayinentityaction);
    }

    @Override
    public void a(PacketPlayInUseEntity packetplayinuseentity) {
        super.a(packetplayinuseentity);
    }

    @Override
    public void a(PacketPlayInClientCommand packetplayinclientcommand) {
        super.a(packetplayinclientcommand);
    }

    @Override
    public void a(PacketPlayInCloseWindow packetplayinclosewindow) {
        super.a(packetplayinclosewindow);
    }

    @Override
    public void a(PacketPlayInWindowClick packetplayinwindowclick) {
        super.a(packetplayinwindowclick);
    }

    @Override
    public void a(PacketPlayInEnchantItem packetplayinenchantitem) {
        super.a(packetplayinenchantitem);
    }

    @Override
    public void a(PacketPlayInSetCreativeSlot packetplayinsetcreativeslot) {
        super.a(packetplayinsetcreativeslot);
    }

    @Override
    public void a(PacketPlayInTransaction packetplayintransaction) {
        super.a(packetplayintransaction);
    }

    @Override
    public void a(PacketPlayInUpdateSign packetplayinupdatesign) {
        super.a(packetplayinupdatesign);
    }

    @Override
    public void a(PacketPlayInKeepAlive packetplayinkeepalive) {
        super.a(packetplayinkeepalive);
    }

    @Override
    public void a(PacketPlayInAbilities packetplayinabilities) {
        super.a(packetplayinabilities);
    }

    @Override
    public void a(PacketPlayInTabComplete packetplayintabcomplete) {
        super.a(packetplayintabcomplete);
    }

    @Override
    public void a(PacketPlayInSettings packetplayinsettings) {
        super.a(packetplayinsettings);
    }

    @Override
    public void a(PacketPlayInCustomPayload packetplayincustompayload) {
        super.a(packetplayincustompayload);
    }

    public FreezingPEPlayerConnection(MinecraftServer minecraftserver, NetworkManager networkmanager, EntityPlayer entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }

}
