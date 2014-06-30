package com.wj44.echem.configuration;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

/**
 * Created by Wesley on 30-6-2014.
 */
public class ConfigurationHandler
{
    public static void init(File configFile)
    {
        // Create the configuration object from the given configuration file
        Configuration configuration = new Configuration(configFile);

        try
        {
            // Load the configuration file
            configuration.load();

            // Read in properties from configurtion file
        }
        catch (Exception e)
        {
            // Log the Exception
        }
        finally
        {
            // Save the configuration file
            configuration.save();
        }
    }
}
