package com.coliwogg.gemsandcrystals;

import com.coliwogg.gemsandcrystals.config.Config;
import com.coliwogg.gemsandcrystals.init.ItemInit;
import com.coliwogg.gemsandcrystals.world.gen.OreGeneration;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod("gemsandcrystals")
@Mod.EventBusSubscriber(modid = GemsandCrystals.MOD_ID, bus = Bus.MOD)
public class GemsandCrystals
{
    public static final String MOD_ID = "gemsandcrystals";
    public static GemsandCrystals instance;
    
    
    public GemsandCrystals() 
    {
    	final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    	modEventBus.addListener(this::setup);
    	modEventBus.addListener(this::doClientStuff);
        instance = this;
        
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.server_config);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.client_config);
        
        Config.loadConfig(Config.client_config, FMLPaths.CONFIGDIR.get().resolve("gemsandcrystals-client.toml").toString());
        Config.loadConfig(Config.server_config, FMLPaths.CONFIGDIR.get().resolve("gemsandcrystals-server.toml").toString());

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }

    private void doClientStuff(final FMLClientSetupEvent event) 
    {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) 
    {
        
    }
    
    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
    	OreGeneration.generateOre();
    }
    
    public static class GemsandCrystalsItemGroup extends ItemGroup {

    	public static final GemsandCrystalsItemGroup instance = new GemsandCrystalsItemGroup(ItemGroup.GROUPS.length, "gemsandcrystalstab");
    	
		public GemsandCrystalsItemGroup(int index, String label) {
			super(index, label);
		}

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemInit.RUBY);
		}  	
    }
}
