package club.someoneice.cheesingcheese;

import club.someoneice.togocup.tags.ItemStackTag;
import club.someoneice.togocup.tags.TagsManager;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

@Mod(modid = Cheese.MODID)
public class Cheese {
    public static final String MODID = "cheesing_death";

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        registryEvent(this);
    }

    public static final ItemStackTag MILK_FOOD_TAG = TagsManager.manager().registerItemStackTag("milk_food", new ItemStack(Items.milk_bucket));

    @SubscribeEvent
    public void onPlayerHurt(LivingHurtEvent event) {
        if (!(event.entity instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) event.entity;
        if (!player.isUsingItem()) return;
        ItemStack item = player.getItemInUse();
        if (MILK_FOOD_TAG.hasItemStack(item)) event.setCanceled(true);
    }

    private void registryEvent(Object eventObj) {
        MinecraftForge.EVENT_BUS.register(eventObj);
        FMLCommonHandler.instance().bus().register(eventObj);
    }
}
