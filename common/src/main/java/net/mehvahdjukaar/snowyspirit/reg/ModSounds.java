package net.mehvahdjukaar.snowyspirit.reg;

import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.mehvahdjukaar.snowyspirit.SnowySpirit;
import net.mehvahdjukaar.snowyspirit.client.SledSoundInstance;
import net.mehvahdjukaar.snowyspirit.common.entity.SledEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class ModSounds {

    public static void init(){

    }

    public static final Supplier<SoundEvent> WINTER_MUSIC = RegHelper.registerSound(SnowySpirit.res("music.winter"));
    public static final Supplier<SoundEvent> SLED_SOUND = RegHelper.registerSound(SnowySpirit.res("entity.sled"));
    public static final Supplier<SoundEvent> SLED_SOUND_SNOW = RegHelper.registerSound(SnowySpirit.res("entity.sled_snow"));


}
