package frc.robot.CV;

public class Object {
    public String name;
    public int[] center;
    public int[] BBOXP1;
    public int[] BBOXP2;
    public String confidence;

    public Object(String name, int[] center, int[] BBOXP1, int[] BBOXP2, String confidence) {
        this.name = name;
        this.center = center;
        this.BBOXP1 = BBOXP1;
        this.BBOXP2 = BBOXP2;
        this.confidence = confidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getCenter() {
        return center;
    }

    public void setCenter(int[] center) {
        this.center = center;
    }

    public int[] getBBOXP1() {
        return BBOXP1;
    }

    public void setBBOXP1(int[] BBOXP1) {
        this.BBOXP1 = BBOXP1;
    }

    public int[] getBBOXP2() {
        return BBOXP2;
    }

    public void setBBOXP2(int[] BBOXP2) {
        this.BBOXP2 = BBOXP2;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String toString(){
        return "Name: " + name + " Center: " + center[0] + ", " + center[1] + " BBOXP1: " + BBOXP1[0] + ", " + BBOXP1[1] + " BBOXP2: " + BBOXP2[0] + ", " + BBOXP2[1] + " Confidence: " + confidence;}
}
