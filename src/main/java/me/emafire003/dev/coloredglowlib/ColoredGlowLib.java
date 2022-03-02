package me.emafire003.dev.coloredglowlib;

import me.emafire003.dev.coloredglowlib.util.Color;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.world.GameRules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ColoredGlowLib implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static String MOD_ID = "coloredglowlib";
	public static Color color = new Color(255, 255, 255);
	private static HashMap<EntityType, Color> per_entitytype_color_map = new HashMap<>();
	private static boolean per_entitytype = true;
	private static List<EntityType> entitytype_rainbow_list = new ArrayList<>();
	private static boolean overrideTeamColors = false;
	private static boolean jebthing = false;

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		//Why do i even have to write this :/
		System.out.println("[ColoredGlowLib] Hey, if you have downloaded this mod from a site different from CurseForge," +
				" Modrinth or Github, please remove this mod and download it again from an official source, " +
				"such as the ones cited before. Mods on other sites are NOT checked or secure since i did NOT " +
				"upload them there (those site also violate the license of the mod, and thus they are NOT LEGAL)");
		setColorToEntityType(EntityType.BAT, new Color(33, 87, 190));
	}
	public static final GameRules.Key<GameRules.BooleanRule> OVERRIDE_TEAM_COLORS =
			GameRuleRegistry.register("overrideTeamColors", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(false));

	/**Set this to true to override the default minecraft team colors
	 * even of the entity is in a team
	 *
	 * @param b The value to assing to overrideTeamColors*/
	public static void setOverrideTeamColors(boolean b){
		overrideTeamColors = b;
	}

	/**Get the value of the overrideTeamColors variable.
	 * (if true overrides the default minecraft team colors
	 * even of the entity is in a team)*/
	public static boolean getOverrideTeamColors(){
		return overrideTeamColors;
	}

	/**Set this to true to make every entity change color like
	 * a jeb sheep aka change color each tick*/
	public static void setRainbowChangingColor(boolean b){
		jebthing = true;
	}

	/**Set this to true to make every entity change color like
	 * a jeb sheep aka change color each tick*/
	public static boolean getRainbowChangingColor(){
		return jebthing;
	}

	/**
	 * Sets the EntityType-specifc option for the custom colored
	 * glow effect.
	 * If enabled it will check for the EntityType of an entity
	 * and set (if configured) a custom glow color for the entity.
	 * */
	public static void setPerEntityTypeColor(boolean b){
		per_entitytype = b;
	}

	/**
	 * Returns true if the option for EntityType-specific glow
	 * color is enabled, returns false if it's disabled.
	 * If enabled it will check for the EntityType of an entity
	 * and set (if configured) a custom glow color for the entity.
	 * */
	public static boolean getPerEntityTypeColor(){
		return per_entitytype;
	}

	/**
	 * Sets a new custom Color value for the glowing effect
	 * of a specific EntityType, such as EntityType.CHICKEN
	 *
	 * @param type The EntityType to set the color for
	 * @param color The color to set for the EntityType
	 * */
	public static void setColorToEntityType(EntityType type, Color color){
		per_entitytype_color_map.put(type, color);
	}

	/**
	 * Removes the EntityType from having a custom color
	 * for the glowing effect.
	 *
	 * @param type The EntityType that will no longer have a custom color
	 * */
	public static void removeColorFromEntityType(EntityType type){
		if(per_entitytype_color_map.containsKey(type)){
			per_entitytype_color_map.remove(type);
		}
	}

	/**
	 * Returns the current Color used for the glowing effect
	 * of a specific EntityType. If there is no custom color for
	 * an EntityType (such as EntityType.CHICKEN) it returns the default one.
	 * (default is white, (255,255,255))
	 *
	 * @param type The EntityType to check the color for
	 * */
	public static Color getEntityTypeColor(EntityType type){
		if(!per_entitytype_color_map.containsKey(type)){
			return Color.getWhiteColor();
		}
		return per_entitytype_color_map.get(type);
	}

	/**
	 * Sets a new rainbow Color that changes every tick for the glowing effect
	 * of a specific EntityType, such as EntityType.CHICKEN
	 *
	 * @param type The EntityType to set the rainbow for
	 * @param enabled Weather or not to enable or disable the rainbow color
	 * */
	public static void setRainbowColorToEntityType(EntityType type, boolean enabled){
		if(enabled){
			entitytype_rainbow_list.add(type);
		}else if(entitytype_rainbow_list.contains(type)){
			entitytype_rainbow_list.remove(type);
		}

	}

	/**
	 * Returns the current Color used for the glowing effect
	 * of a specific EntityType. If there is no custom color for
	 * an EntityType (such as EntityType.CHICKEN) it returns the default one.
	 * (default is white, (255,255,255))
	 *
	 * @param type The EntityType to check the color for
	 * */
	public static boolean getEntityTypeRainbowColor(EntityType type){
		if(entitytype_rainbow_list.contains(type)){
			return true;
		}else{
			return false;
		}
	}


	/**
	 * Returns the current Color used for the glowing effect
	 * (default is white, (255,255,255))
	 * */
	public static Color getColor(){
		return color;
	}

	/**
	 * Set the color used for the glowing effect
	 *
	 * @param colour A Color object that will determine the glowing color
	 * */
	public static void setColor(Color colour){
		color = colour;
	}

	/**
	 * Set the color used for the glowing effect
	 *
	 * @param red Red color value (0-255)
	 * @param green Green value (0-255)
	 * @param blue Blue value (0-255)
	 * */
	public static void setColor(int red, int green, int blue){
		color = new Color(red, green, blue);
	}

	/**
	 * Set the color used for the glowing effect
	 *
	 * @param value The colorvalue (RRRRRRGGGGGGBBBBBB) You can get this via Color.translateToColorValue()
	 */
	public static void setColorValue(int value){
		color = Color.translateFromColorValue(value);
	}

}
