package group7.levels;

import static group7.entities.inanimate.Potion.*;


/** 
* The class Level1 creates objects the player, all Enemies, traps, and collectable objects (such as 
* keys, eggs, and potions) for Level One and spawns them in their designated positions onto the map.
*
* @author  Mohammad Parsaei
* @author  Salman Ayaz
* @author  Karmen Yung
* @author  Chen Min
* @version 1.0
* @since 2023-03-13
*/
public class Level1 extends Level {

    public Level1(int dinoNumber) {
        super(dinoNumber,1);


       addEnemy(5, 14, 2, 3, 1);
       addEnemy(9, 4, 2, 3, 2);
       addEnemy(13, 9, 2, 3, 3);
       addEnemy(10, 15, 2, 3, 4);

        addTrap(3, 9);
        addTrap(11, 10);
        addTrap(16, 10);
        addTrap(8, 14);

        addKey(2, 14);
        addKey(17, 5);
        addKey(18, 16);

        addEgg(3, 15, 200);
        addEgg(16, 4, 100);
        addEgg(18, 13, 300);

        addPotion(10, 3, PURPLE_SPEED_POTION );    
        addPotion(7, 16, PURPLE_SPEED_POTION );    
        addPotion(9, 10, GREEN_HEALTH_POTION );    
    }

}
