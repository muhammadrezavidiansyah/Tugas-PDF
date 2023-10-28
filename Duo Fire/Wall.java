import greenfoot.*;  
public class Wall extends Actor{
    public int height;
    public int width;
    public static final int UP = -700;
    public static final int DOWN = 50;
    public static final int LEFT = 300;
    public static final int RIGHT = 0;  
    
    public Wall(int width, int height){
        this.height = height;
        this.width = width;
        
        GreenfootImage wallImage = new GreenfootImage(width, height);
        wallImage.setColor(Color.GREEN);
        wallImage.fill();
        setImage(wallImage);
        
    }
}





