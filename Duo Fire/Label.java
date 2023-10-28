import greenfoot.*;  
public class Label extends Actor
{
    String text;
    int size = 30;
    Color textColor = Color.BLACK;
    Color backColor = null;
    
    public Label(String t) {
        text = t;
        updateImage();
    }
    
    public void setText(int number) {
        String textNumber = Integer.toString(number);
        setText(textNumber);
    }
    
    public void setText(String t){
        text = t;
        updateImage();
    }
    
    public void updateImage() {
        GreenfootImage img = new GreenfootImage(text, size, textColor, backColor);
        setImage(img);
    }



}
