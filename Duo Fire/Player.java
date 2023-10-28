    import greenfoot.*; 
    import java.util.*;
    public class Player extends Actor
    {
        public static final int UP = 270;
        public static final int UP_LEFT = 225;
        public static final int UP_RIGHT = 315;
        public static final int DOWN = 90;
        public static final int LEFT = 180;
        public static final int RIGHT = 0; 
        public static final int DOWN_RIGHT = 45;
        public static final int DOWN_LEFT = 135;
        public static final List<String> WEAPON_TYPES = new ArrayList<>(
                List.of("gun","rocket launcher","machine gun")
        );
        
        public long timeOfPrevBullet = 0;
        public int health = 100;
        public Weapon weapon;
        
        public Player(String imgFile) {
            weapon = new Weapon("gun"); 
            GreenfootImage image = new GreenfootImage(imgFile);
            image.scale(30, 30);
            setImage(image);
        }
        public void act(){
            if(isTouching(Box.class)){
                onPickupBox();
            }
        }   
    public boolean isTouchingWall(){
        return isTouching(Wall.class);
    }
    public void movePlayer(int direction) 
    {
        setRotation(direction);
        move(1);
        if (isTouching(Wall.class)) {
            move(-1);
        }
    }
    
    public void shoot()
    {
        if (getCurrentTime() - timeOfPrevBullet > weapon.rateOfFire) {
            int direction = getRotation();
            Projectile projectile = getNewProjectile(direction);
            Game gameWorld = (Game)getWorld();
            gameWorld.projectileList.add(projectile);
            if(direction == RIGHT){
                gameWorld.addObject(projectile, (getX()+25), (getY()+12));
            } else if (direction == LEFT){
                gameWorld.addObject(projectile, (getX()-25), (getY()-12));
            } else if (direction == UP){
                gameWorld.addObject(projectile, (getX()+12), (getY()-25));
            } else if (direction == DOWN) {
                gameWorld.addObject(projectile, (getX()-12), (getY()+25));
            } else if (direction == UP_LEFT) {
                gameWorld.addObject(projectile, (getX()-12), (getY()-25));
            } else if (direction == UP_RIGHT){
                gameWorld.addObject(projectile, (getX()+26), (getY()-22));
            } else if (direction == DOWN_LEFT){
                gameWorld.addObject(projectile, (getX()-30), (getY()+12));
            } else if (direction == DOWN_RIGHT){
                gameWorld.addObject(projectile, (getX()+12), (getY()+25));
            } 
        
            timeOfPrevBullet = getCurrentTime();
            weapon.playSound();
            weapon.currentClipSize -= 1;
            if(weapon.currentClipSize == 0){
                weapon.currentClipSize = weapon.clipSize;
                timeOfPrevBullet = getCurrentTime() + weapon.reloadTime;
                
            }
            
        }
    }
    public Projectile getNewProjectile(int direction){
        if (weapon.name.equals("gun")){
            return new PistolBullet(direction);
        }
        else if (weapon.name.equals("machine gun")){
            return new MachineGunBullet(direction);
        }
        else { 
            return new Rocket(direction);
        }
    }
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
    public void onPickupBox() {
        Game world = (Game)getWorld();
        world.removeObject(world.box);
        world.box = null;
        world.frameOfBoxPickUp = world.currentFrame;
        int weaponIndex = Greenfoot.getRandomNumber(WEAPON_TYPES.size()-1) + 1;
        String newWeapon = WEAPON_TYPES.get(weaponIndex);
        weapon = new Weapon(newWeapon);
    }
}
