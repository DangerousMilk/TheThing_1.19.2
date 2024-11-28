package net.realfancymoo.thething;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.server.ServerStartedEvent;

import java.io.File;
import java.io.IOException;

public class HiveMind {
    private static final String FILENAME = "hivemind_data.dat";
    private static File hiveMindDataFile;
    private static CompoundTag hiveMindDataCompoundTag = new CompoundTag();

    private static int ticks = 0;

    private static int evolutionPoints = 0;

    public static void setDataFile(ServerStartedEvent event)
    {
        var server = event.getServer();
        hiveMindDataFile = new File(server.getWorldPath(LevelResource.ROOT).toFile(), FILENAME);
    }

    public static void initializeHiveMind(ServerStartedEvent event)
    {
        setDataFile(event);
        loadData();
        if(getValue("evolutionPoints") != null) {
            evolutionPoints = Integer.parseInt(getValue("evolutionPoints"));
        }
    }

    /*
    Gettings / Storing Values
    */

    public static void setValue(String key, String value) {
        hiveMindDataCompoundTag.putString(key, value);
        saveData(); // Save after modifying data
    }

    public static String getValue(String key) {
        return hiveMindDataCompoundTag.contains(key) ? hiveMindDataCompoundTag.getString(key) : null;
    }

    public static void tick(TickEvent.ServerTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END){
            ticks++;
            if(ticks % 40 == 0)
            {
                System.out.println("[HIVE MIND] current evolution points: " + evolutionPoints);
                ticks = 0;
            }
        }
    }

    public static void addEvolutionPoints(int points)
    {
        evolutionPoints += points;
        setValue("evolutionPoints", Integer.toString(evolutionPoints));
    }

    /*
    SAVING DATA
    */

    public static void loadData() {
        if (hiveMindDataFile.exists()) {
            try {
                hiveMindDataCompoundTag = NbtIo.readCompressed(hiveMindDataFile); // Load the data
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveData() {
        try {
            NbtIo.writeCompressed(hiveMindDataCompoundTag, hiveMindDataFile); // Save the data
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
