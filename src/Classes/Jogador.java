package Classes;

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


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
    private Date joinedOn;


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
        this.joinedOn = null;
    }

    public Jogador(String knownAs, String fullName, byte overall, double value, String bestPosition, String nacionality, byte age, String clubName, Date joinedOn){
       
        this.knownAs = knownAs;
        this.fullName = fullName;
        this.overall = overall;
        this.value = value;
        this.bestPosition = bestPosition;
        this.nacionality = nacionality;
        this.age = age;
        this.clubName = clubName;
        this.joinedOn = joinedOn;
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

    public Date getJoinedOn(){
        return joinedOn;
    }

    public void setJoinedOn(String stringDate) throws Exception{
        Date date = convertToDate(stringDate);
        this.joinedOn = date;
    }

    public Date convertToDate(String strData) throws Exception {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        date = sdf.parse(strData);
        return date;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String dateString = sdf.format(date);
        return dateString;
    }
    
    /*
     * Escreve o objeto jogador em um arquivo de bytes
    */
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
        dos.writeUTF(convertDateToString(joinedOn));

        return out.toByteArray();
    }

    /*
     * Lê o objeto jogador de um arquivo de bytes
     */
    public void fromByteArray(byte[] out) throws Exception{
        ByteArrayInputStream input = new ByteArrayInputStream(out);
        DataInputStream dis = new DataInputStream(input);

        setKnownAs(dis.readUTF());
        setFullName(dis.readUTF());
        setOverall(dis.readByte());
        setValue(dis.readDouble());
        setBestPosition(dis.readUTF());
        setNacionality(dis.readUTF());
        setAge(dis.readByte());
        setClubName(dis.readUTF());
        setJoinedOn(dis.readUTF());
    }

    public String toString(){
        return "KnowAs: "   + getKnownAs()
        +"\nFullName: "     + getFullName()
        +"\nOverall: "      + getOverall()
        +"\nValue: "        + getValue()
        +"\nBestPosition: " + getBestPosition()
        +"\nNacionality: "  + getNacionality()
        +"\nAge: "          + getAge()
        +"\nClubName: "     + getClubName()
        +"\nJoinedOn: "     + getJoinedOn();
        
    }
}