package com.github.sakiio.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Project: Hub
 * Date: 15/07/2021 @ 0:38
 * Class: WorldListener
 */
public class WorldListener implements Listener {
    @EventHandler
    public void onWorldWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }
}
