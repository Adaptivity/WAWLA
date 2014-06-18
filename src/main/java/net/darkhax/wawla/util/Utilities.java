package net.darkhax.wawla.util;

import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.StatCollector;

public class Utilities {

    /**
     * A simple method to make an ItemStack safe to work with in regards of nbt. If the stack does not
     * have a tag compound one will be added. If it already has one then nothing will happen. This is
     * useful when working with ItemStacks that may not have tag compounds yet.
     * 
     * @param stack: An instance of an ItemStack to be prepared for nbt work.
     * @return ItemStack: The same instance of ItemStack provided, however with a tag compound added if
     *         one did not alread exist.
     */
    public static ItemStack prepareStackCompound(ItemStack stack) {

        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());

        return stack;
    }

    /**
     * This method will take a string and break it down into multiple lines based on a provided line
     * length. The seperate strings are then added to the list provided. This method is useful for adding
     * a long description to an item tool tip and having it wrap. This method is similar to wrap in
     * Apache WordUtils however it uses a List making it easier to use when working with Minecraft.
     * 
     * @param string: The string being split into multiple lines. It's recommended to use
     *        StatCollector.translateToLocal() for this so multiple languages will be supported.
     * @param lnLength: The ideal size for each line of text.
     * @param wrapLongWords: If true the ideal size will be exact, potentially splitting words on the end
     *        of each line.
     * @param list: A list to add each line of text to. An good example of such list would be the list of
     *        tooltips on an item.
     * @return List: The same List instance provided however the string provided will be wrapped to the
     *         ideal line length and then added.
     */
    public static List wrapStringToList(String string, int lnLength, boolean wrapLongWords, List list) {

        String strings[] = WordUtils.wrap(string, lnLength, null, wrapLongWords).split("\\r\\n");

        for (int i = 0; i < strings.length; i++)
            list.add(strings[i]);

        return list;
    }

    /**
     * This method will create a list of enchantments on an ItemStack.
     * 
     * @param stack: An instance of the ItemStack that you are checking.
     * @param stored: This boolean is used to differentiate between stored enchantments and actual
     *        enchantments. Enchantment Books do not keep their enchantments under the same compound as
     *        other enchanted items. Set to true if the ItemStack being checked has stored enchantments
     *        rather than active ones.
     * @return Enchantment[]: A list of all the enchantments on an ItemStack.
     */
    public static Enchantment[] getEnchantmentsFromStack(ItemStack stack, boolean stored) {

        prepareStackCompound(stack);
        String tagName = (stored) ? "StoredEnchantments" : "ench";
        NBTTagCompound tag = stack.stackTagCompound;
        NBTTagList list = tag.getTagList(tagName, 10);
        Enchantment[] ench = new Enchantment[list.tagCount()];

        for (int i = 0; i < list.tagCount(); i++)
            ench[i] = Enchantment.enchantmentsList[list.getCompoundTagAt(i).getShort("id")];

        return ench;
    }
}
