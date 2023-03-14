package Classes;

import java.io.*;


class Jogador{
    private int id;
    private String knownAs;
    private String fullName;
    private byte overall;
    private double value;
    private String bestPosition;
    private String nacionality;
    private byte age;
    private String clubName;


    public Jogador(){
        this.id = -1;
        this.knownAs = "";
        this.fullName = "";
        this.overall = 0;
        this.value = 0;
        this.bestPosition = "";
        this.nacionality = "";
        this.age = 0;
        this.clubName = "";
    }

    public Jogador(String knownAs, String fullName, byte overall, double value, String bestPosition, String nacionality, byte age, String clubName){
       
        this.knownAs = knownAs;
        this.fullName = fullName;
        this.overall = overall;
        this.value = value;
        this.bestPosition = bestPosition;
        this.nacionality = nacionality;
        this.age = age;
        this.clubName = clubName;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getKnownAs(){
        return this.knownAs;
    }

    public void setKnownAs(String knownAs){
        this.knownAs = knownAs;
    }

    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public byte getOverall(){
        return this.overall;
    }

    public void setOverall(byte overall){
        this.overall = overall;
    }

    public double getValue(){
        return this.value;
    }

    public void setValue(double value){
        this.value = value;
    }

    public String getBestPosition(){
        return this.bestPosition;
    }

    public void setBestPosition(String bestPosition){
        this.bestPosition = bestPosition;
    }

    public String getNacionality(){
        return this.nacionality;
    }

    public void setNacionality(String nacionality){
        this.nacionality = nacionality;
    }

    public byte getAge(){
        return this.age;
    }

    public void setAge(byte age){
        this.age = age;
    }

    public String getClubName(){
        return this.clubName;
    }

    public void setClubName(String clubName){
        this.clubName = clubName;
    }

    
    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(out);

        dos.writeUTF(knownAs);
        dos.writeUTF(fullName);
        dos.writeByte(overall);
        dos.writeDouble(value);
        dos.writeUTF(bestPosition);
        dos.writeUTF(nacionality);
        dos.writeByte(age);
        dos.writeUTF(clubName);

        return out.toByteArray();
    }

    public void fromByteArray(byte[] out) throws IOException{
        ByteArrayInputStream input = new ByteArrayInputStream(out);
        DataInputStream dis = new DataInputStream(input);

        this.knownAs = dis.readUTF();
        this.fullName = dis.readUTF();
        this.overall = dis.readByte();
        this.value = dis.readDouble();
        this.bestPosition = dis.readUTF();
        this.nacionality = dis.readUTF();
        this.age = dis.readByte();
        this.clubName = dis.readUTF();
    }

    public String toString(){
        return "KnowAs: "   + this.knownAs
        +"\nFullName: "     + this.fullName
        +"\nOverall: "      + this.overall
        +"\nValue: "        + this.value
        +"\nBestPosition: " + this.bestPosition
        +"\nNacionality: "  + this.nacionality
        +"\nAge: "          + this.age
        +"\nClubName: "     +this.clubName;
    }
}