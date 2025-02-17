package net.mehvahdjukaar.snowyspirit.reg;

import com.google.common.collect.ImmutableMap;
import net.mehvahdjukaar.moonlight.api.block.ModStairBlock;
import net.mehvahdjukaar.moonlight.api.item.WoodBasedBlockItem;
import net.mehvahdjukaar.moonlight.api.misc.Registrator;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.moonlight.api.set.BlockSetAPI;
import net.mehvahdjukaar.moonlight.api.set.BlocksColorAPI;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.snowyspirit.SnowySpirit;
import net.mehvahdjukaar.snowyspirit.common.block.*;
import net.mehvahdjukaar.snowyspirit.common.entity.ContainerHolderEntity;
import net.mehvahdjukaar.snowyspirit.common.entity.SledEntity;
import net.mehvahdjukaar.snowyspirit.common.items.CandyCaneItem;
import net.mehvahdjukaar.snowyspirit.common.items.EggnogItem;
import net.mehvahdjukaar.snowyspirit.common.items.GlowLightsItem;
import net.mehvahdjukaar.snowyspirit.common.items.SledItem;
import net.minecraft.Util;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

@SuppressWarnings("ConstantConditions")
public class ModRegistry {

    public static void init() {
        BlockSetAPI.addDynamicItemRegistration(ModRegistry::registerSledItems, WoodType.class);
    }

    private static void registerSledItems(Registrator<Item> event, Collection<WoodType> woodTypes) {
        for (WoodType wood : woodTypes) {
            if (wood.canBurn()) {
                String name = wood.getVariantId(SLED_NAME);
                SledItem item = new SledItem(wood);
                event.register(SnowySpirit.res(name), item);
                SLED_ITEMS.put(wood, item);
                wood.addChild(SLED_NAME, item);
            }
        }
    }

    public static final String SLED_NAME = "sled";
    public static final Supplier<EntityType<SledEntity>> SLED = regEntity(SLED_NAME,
            () -> EntityType.Builder.<SledEntity>of(SledEntity::new, MobCategory.MISC)
                    .sized(1.375F, 0.5625F)
                    .clientTrackingRange(10));

    public static final Supplier<EntityType<ContainerHolderEntity>> CONTAINER_ENTITY = regEntity("container_entity",
            () -> EntityType.Builder.of(ContainerHolderEntity::new, MobCategory.MISC)
                    .sized(0.75f, 0.75f)
                    .clientTrackingRange(8));


    public static final Map<WoodType, SledItem> SLED_ITEMS = new LinkedHashMap<>();

    public static final Supplier<Block> CANDY_CANE_BLOCK = regWithItem("candy_cane_block", () ->
            new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.CALCITE)
                    .mapColor(MapColor.COLOR_PINK)
                    .requiresCorrectToolForDrops().strength(1.5F).sound(SoundType.CALCITE)));

    public static final String CANDY_CANE_NAME = "candy_cane";
    public static final Supplier<Item> CANDY_CANE = regItem(CANDY_CANE_NAME,
            () -> new CandyCaneItem(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(2).saturationMod(0.4f).build())));

    public static final Supplier<Item> GINGERBREAD_COOKIE = regItem("gingerbread_cookie",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder().nutrition(1).fast().saturationMod(0.4f).build())));

    public static final String EGGNOG_NAME = "eggnog";
    public static final Supplier<Item> EGGNOG = regItem(EGGNOG_NAME, EggnogItem::new);

    public static final String WINTER_DISC_NAME = "music_disc_winter";
    public static final Supplier<Item> WINTER_DISC = regItem(WINTER_DISC_NAME,
            () -> PlatHelper.newMusicDisc(14, ModSounds.WINTER_MUSIC, new Item.Properties()
                    .rarity(Rarity.RARE).stacksTo(1), 2 * 60 + 41));

    public static final Supplier<Block> GINGERBREAD_BLOCK = regWithItem("gingerbread", () ->
            new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.TERRACOTTA_ORANGE)
                    .sound(SoundType.WOOD).strength(1F)));

    //slab
    public static final Supplier<Block> GINGERBREAD_STAIRS = regWithItem("gingerbread_stairs", () -> new ModStairBlock(
            GINGERBREAD_BLOCK, BlockBehaviour.Properties.copy(GINGERBREAD_BLOCK.get())));

    //slab
    public static final Supplier<Block> GINGERBREAD_SLAB = regWithItem("gingerbread_slab", () -> new SlabBlock(
            BlockBehaviour.Properties.copy(GINGERBREAD_BLOCK.get())));

    public static final Supplier<Block> GINGERBREAD_FROSTED_BLOCK = regWithItem("gingerbread_frosted", () ->
            new Block(BlockBehaviour.Properties.copy(GINGERBREAD_BLOCK.get())));

    public static final Supplier<Block> GINGERBREAD_DOOR = regWithItem("gingerbread_door", () ->
            new DoorBlock(BlockBehaviour.Properties.copy(GINGERBREAD_BLOCK.get()), BlockSetType.ACACIA) {
            });

    public static final Supplier<Block> GINGERBREAD_TRAPDOOR = regWithItem("gingerbread_trapdoor", () ->
            new TrapDoorBlock(BlockBehaviour.Properties.copy(GINGERBREAD_BLOCK.get()), BlockSetType.ACACIA) {
            });


    public static final Supplier<Block> GINGER_WILD = regWithItem("wild_ginger", () -> new WildGingerBlock(
            BlockBehaviour.Properties.copy(Blocks.TALL_GRASS)));

    public static final String GINGER_NAME = "ginger";
    public static final Supplier<Block> GINGER_CROP = regBlock(GINGER_NAME, () ->
            new GingerBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
            ));
    public static final Supplier<Item> GINGER_FLOWER = regItem("ginger_flower",
            () -> new ItemNameBlockItem(GINGER_CROP.get(), new Item.Properties()));
    public static final Supplier<Item> GINGER = regItem("ginger",
            () -> new Item(new Item.Properties()));

    //pot
    public static final Supplier<Block> GINGER_POT = regBlock("potted_ginger", () -> PlatHelper.newFlowerPot(
            () -> (FlowerPotBlock) Blocks.FLOWER_POT, GINGER_CROP, BlockBehaviour.Properties.copy(Blocks.FLOWER_POT)));

    public static final Supplier<SimpleParticleType> GLOW_LIGHT_PARTICLE = RegHelper.registerParticle(
            SnowySpirit.res("glow_light"));


    public static final String GUMDROP_NAME = "gumdrop";
    public static final Map<DyeColor, Supplier<Block>> GUMDROPS_BUTTONS =
            BlocksColorAPI.SORTED_COLORS.stream().collect(ImmutableMap.toImmutableMap(Function.identity(),
                    c -> regWithItem(GUMDROP_NAME + "_" + c.getName(), () -> new GumdropButton(c))));

    public static final String GLOW_LIGHTS_NAME = "glow_lights";
    public static final Map<DyeColor, Supplier<Block>> GLOW_LIGHTS_BLOCKS = Util.make(() -> {
        var m = new HashMap<DyeColor, Supplier<Block>>();
        for (DyeColor c : DyeColor.values()) {
            m.put(c, regBlock(GLOW_LIGHTS_NAME + "_" + c.getName(), () -> new GlowLightsBlock(c)));
        }
        m.put(null, regBlock(GLOW_LIGHTS_NAME + "_prismatic", () -> new GlowLightsBlock(null)));
        return m;
    });

    public static final Map<DyeColor, Supplier<Item>> GLOW_LIGHTS_ITEMS = Util.make(() -> {
        var m = new HashMap<DyeColor, Supplier<Item>>();
        for (DyeColor c : BlocksColorAPI.SORTED_COLORS) {
            m.put(c, regItem("glow_lights_" + c.getName(), () -> new GlowLightsItem(GLOW_LIGHTS_BLOCKS.get(c).get())));
        }
        m.put(null, regItem("glow_lights_prismatic", () -> new GlowLightsItem(GLOW_LIGHTS_BLOCKS.get(null).get())));
        return m;
    });

    public static final Supplier<BlockEntityType<GlowLightsBlockTile>> GLOW_LIGHTS_BLOCK_TILE = regTile(
            "glow_lights", () -> PlatHelper.newBlockEntityType(GlowLightsBlockTile::new,
                    ModRegistry.GLOW_LIGHTS_BLOCKS.values().stream().map(Supplier::get).toArray(Block[]::new)));


    public static final String WREATH_NAME = "wreath";
    public static final Supplier<Block> WREATH = regWithItem(WREATH_NAME, () ->
            new WreathBlock(BlockBehaviour.Properties.of()
                    .ignitedByLava()
                    .noOcclusion()
                    .mapColor(MapColor.COLOR_GREEN)
                    .sound(SoundType.VINE).strength(0.1f).noCollission()));


    public static final String SNOW_GLOBE_NAME = "snow_globe";
    public static final Supplier<Block> SNOW_GLOBE = regWithItem(SNOW_GLOBE_NAME, () ->
            new SnowGlobeBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .sound(SoundType.GLASS)
                    .mapColor(MapColor.NONE)
                    .strength(0.5f)));


    public static <T extends Item> Supplier<T> regItem(String name, Supplier<T> sup) {
        return RegHelper.registerItem(SnowySpirit.res(name), sup);
    }

    public static <T extends BlockEntityType<E>, E extends BlockEntity> Supplier<T> regTile(String name, Supplier<T> sup) {
        return RegHelper.registerBlockEntityType(SnowySpirit.res(name), sup);
    }

    public static <T extends Block> Supplier<T> regBlock(String name, Supplier<T> sup) {
        return RegHelper.registerBlock(SnowySpirit.res(name), sup);
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> blockFactory) {
        return regWithItem(name, blockFactory, new Item.Properties(), 0);
    }

    public static <T extends Block> Supplier<T> regWithItem(String name, Supplier<T> blockFactory, Item.Properties properties, int burnTime) {
        Supplier<T> block = regBlock(name, blockFactory);
        regBlockItem(name, block, properties, burnTime);
        return block;
    }

    public static Supplier<BlockItem> regBlockItem(String name, Supplier<? extends Block> blockSup, Item.Properties properties, int burnTime) {
        return RegHelper.registerItem(SnowySpirit.res(name), () -> burnTime == 0 ? new BlockItem(blockSup.get(), properties) :
                new WoodBasedBlockItem(blockSup.get(), properties, burnTime));
    }

    public static <T extends Entity> Supplier<EntityType<T>> regEntity(String name, Supplier<EntityType.Builder<T>> builder) {
        return RegHelper.registerEntityType(SnowySpirit.res(name), () -> builder.get().build(name));
    }


}
