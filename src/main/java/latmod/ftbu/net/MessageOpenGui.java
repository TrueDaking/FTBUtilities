package latmod.ftbu.net;
import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.relauncher.*;
import ftb.lib.client.FTBLibClient;
import latmod.ftbu.mod.FTBU;
import latmod.ftbu.util.LMGuiHandler;
import net.minecraft.nbt.NBTTagCompound;

public class MessageOpenGui extends MessageFTBU
{
	public MessageOpenGui() { super(DATA_LONG); }
	
	public MessageOpenGui(String mod, int id, NBTTagCompound tag, int wid)
	{
		this();
		io.writeString(mod);
		io.writeInt(id);
		writeTag(tag);
		io.writeUByte(wid);
	}
	
	@SideOnly(Side.CLIENT)
	public IMessage onMessage(MessageOpenGui m, MessageContext ctx)
	{
		String modID = io.readString();
		int guiID = io.readInt();
		NBTTagCompound data = readTag();
		int windowID = io.readUByte();
		
		LMGuiHandler h = LMGuiHandler.Registry.getLMGuiHandler(modID);
		if(h != null && FTBU.proxy.openClientGui(FTBLibClient.mc.thePlayer, modID, guiID, data))
			FTBLibClient.mc.thePlayer.openContainer.windowId = windowID;
		return null;
	}
}