package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Packet {
	private static Map packetIdToClassMap = new HashMap();
	private static Map packetClassToIdMap = new HashMap();
	public final long creationTimeMillis = System.currentTimeMillis();
	public boolean isChunkDataPacket = false;
	private static HashMap field_21906_c;
	private static int field_21905_d;

	static void addIdClassMapping(int var0, Class var1) {
		if(packetIdToClassMap.containsKey(Integer.valueOf(var0))) {
			throw new IllegalArgumentException("Duplicate packet id:" + var0);
		} else if(packetClassToIdMap.containsKey(var1)) {
			throw new IllegalArgumentException("Duplicate packet class:" + var1);
		} else {
			packetIdToClassMap.put(Integer.valueOf(var0), var1);
			packetClassToIdMap.put(var1, Integer.valueOf(var0));
		}
	}

	public static Packet getNewPacket(int var0) {
		try {
			Class var1 = (Class)packetIdToClassMap.get(Integer.valueOf(var0));
			return var1 == null ? null : (Packet)var1.newInstance();
		} catch (Exception var2) {
			var2.printStackTrace();
			System.out.println("Skipping packet with id " + var0);
			return null;
		}
	}

	public final int getPacketId() {
		return ((Integer)packetClassToIdMap.get(this.getClass())).intValue();
	}

	public static Packet readPacket(DataInputStream var0) throws IOException {
		boolean var1 = false;
		Packet var2 = null;
		var0.mark(16384);

		int var5;
		try {
			var5 = var0.read();
			if(var5 == -1) {
				return null;
			}

			var2 = getNewPacket(var5);
			if(var2 == null) {
				throw new IOException("Bad packet id " + var5);
			}

			var2.readPacketData(var0);
		} catch (EOFException var4) {
			System.out.println("Reached end of stream");
			var0.reset();
			return null;
		}

		PacketCounter var3 = (PacketCounter)field_21906_c.get(Integer.valueOf(var5));
		if(var3 == null) {
			var3 = new PacketCounter((Empty1)null);
			field_21906_c.put(Integer.valueOf(var5), var3);
		}

		var3.func_22236_a(var2.getPacketSize());
		++field_21905_d;
		if(field_21905_d % 1000 == 0) {
		}

		return var2;
	}

	public static void writePacket(Packet var0, DataOutputStream var1) throws IOException {
		var1.write(var0.getPacketId());
		var0.writePacketData(var1);
	}

	public abstract void readPacketData(DataInputStream var1) throws IOException;

	public abstract void writePacketData(DataOutputStream var1) throws IOException;

	public abstract void processPacket(NetHandler var1);

	public abstract int getPacketSize();

	static {
		addIdClassMapping(0, Packet0KeepAlive.class);
		addIdClassMapping(1, Packet1Login.class);
		addIdClassMapping(2, Packet2Handshake.class);
		addIdClassMapping(3, Packet3Chat.class);
		addIdClassMapping(4, Packet4UpdateTime.class);
		addIdClassMapping(5, Packet5PlayerInventory.class);
		addIdClassMapping(6, Packet6SpawnPosition.class);
		addIdClassMapping(7, Packet7.class);
		addIdClassMapping(8, Packet8.class);
		addIdClassMapping(9, Packet9.class);
		addIdClassMapping(10, Packet10Flying.class);
		addIdClassMapping(11, Packet11PlayerPosition.class);
		addIdClassMapping(12, Packet12PlayerLook.class);
		addIdClassMapping(13, Packet13PlayerLookMove.class);
		addIdClassMapping(14, Packet14BlockDig.class);
		addIdClassMapping(15, Packet15Place.class);
		addIdClassMapping(16, Packet16BlockItemSwitch.class);
		addIdClassMapping(17, Packet17Sleep.class);
		addIdClassMapping(18, Packet18ArmAnimation.class);
		addIdClassMapping(19, Packet19.class);
		addIdClassMapping(20, Packet20NamedEntitySpawn.class);
		addIdClassMapping(21, Packet21PickupSpawn.class);
		addIdClassMapping(22, Packet22Collect.class);
		addIdClassMapping(23, Packet23VehicleSpawn.class);
		addIdClassMapping(24, Packet24MobSpawn.class);
		addIdClassMapping(25, Packet25.class);
		addIdClassMapping(27, Packet27.class);
		addIdClassMapping(28, Packet28.class);
		addIdClassMapping(29, Packet29DestroyEntity.class);
		addIdClassMapping(30, Packet30Entity.class);
		addIdClassMapping(31, Packet31RelEntityMove.class);
		addIdClassMapping(32, Packet32EntityLook.class);
		addIdClassMapping(33, Packet33RelEntityMoveLook.class);
		addIdClassMapping(34, Packet34EntityTeleport.class);
		addIdClassMapping(38, Packet38.class);
		addIdClassMapping(39, Packet39.class);
		addIdClassMapping(40, Packet40.class);
		addIdClassMapping(50, Packet50PreChunk.class);
		addIdClassMapping(51, Packet51MapChunk.class);
		addIdClassMapping(52, Packet52MultiBlockChange.class);
		addIdClassMapping(53, Packet53BlockChange.class);
		addIdClassMapping(54, Packet54.class);
		addIdClassMapping(60, Packet60.class);
		addIdClassMapping(100, Packet100.class);
		addIdClassMapping(101, Packet101.class);
		addIdClassMapping(102, Packet102.class);
		addIdClassMapping(103, Packet103.class);
		addIdClassMapping(104, Packet104.class);
		addIdClassMapping(105, Packet105.class);
		addIdClassMapping(106, Packet106.class);
		addIdClassMapping(130, Packet130.class);
		addIdClassMapping(255, Packet255KickDisconnect.class);
		field_21906_c = new HashMap();
		field_21905_d = 0;
	}
}
