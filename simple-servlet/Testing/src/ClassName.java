
public class ClassName{

    private String className = "";

    private boolean state = true;

    public ClassName(String className){
    	this.className = className;
    }
    
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "[DataObject: class name=" + className + ", state=" + state + "]";
    }
}
