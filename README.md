# NBTagAPI

NBTag is a short NBT Tag API for Bukkit.

Features:
- Edit NBT Tags live (Reflections)
- Very small
- Get NBT Tags of Entity's & ItemStack's


# Example

```Java
// Get Tags
NBTagCompound entityTag = NBTagUtil.getTag(myEntity);
NBTagCompound itemTag = NBTagUtil.getTag(itemTag);

// Use compounds
NBTagObject object = entityTag.getObject("CustomName");

String currentName = object.asString();
object.set(currentName+"1337");

// Use Lists
NBTagList list = entityTag.getList("PotionEffects");

// Remove first entry
list.remove(0);

// Add entries
entityTag.add("noAI", NBTagUtil.newInstance(1, Integer.class));
entityTag.add("uselessCompound", NBTagUtil.newCompound());

// Get NMS Lists/Maps
entityTag.directAccess(); // Direct access to the map of nms objects
list.directAccess(); // Direct access to the list of nms objects

// Get Handle
entityTag.getHandle();
list.getHandle();
object.getHandle();

// Other important methods
NBTagBase base = entityTag;

// Check the type
base.isObject();
base.isCompound();
base.isList();

```
