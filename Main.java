public class Main {
    public static void main(String args[]){
       Encoder encode = new Encoder();
       try{
            if(args[1].equals("-encode")){
                System.out.println(encode.encode(args[0].toUpperCase()));
            }
            else if(args[1].equals("-decode")){
                System.out.println(encode.decode(args[0].toUpperCase()));
            }
            else{
                System.out.println("Please enter correct mode");
            }
       }catch(Exception e){
            System.out.print("Please enter mode: -encode or -decode");
       }
      
    }
}

class Encoder{
    private String table = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789()*+,-./";

    public Encoder(){}

    public String encode(String plaintext){
        int offsetIndex = getRandomIndex(0, 44);
        StringBuilder str = new StringBuilder();

        str.append(table.charAt(offsetIndex)); // first character of encoded message

        for(int i = 0; i < plaintext.length(); i++){
            if(plaintext.charAt(i) == ' ') {
                str.append(" ");
            }
            else{
                int getIndex = table.indexOf(plaintext.charAt(i)); // get the index of the char from plaintext in relation to table
                int newIndex = getNewIndex(getIndex, offsetIndex); 
                str.append(table.charAt(newIndex));
            }
        }
        return str.toString();
    }

    public String decode(String encodedText){
        StringBuilder str = new StringBuilder();
        int offsetIndex = table.indexOf(encodedText.charAt(0)); //get the index of first character

        for(int i = 1; i < encodedText.length(); i++){
            if(encodedText.charAt(i) == ' ') {
                str.append(" ");
            }
            else{
                int getIndex = table.indexOf(encodedText.charAt(i)); // get the index of the char from encodedtext in relation to table'
                int originalIndex = getOriginalIndex(getIndex, offsetIndex);
                str.append(table.charAt(originalIndex));
            }
        }
        return str.toString();
    }

    private int getRandomIndex(int min, int max){
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getNewIndex(int getIndex, int offsetIndex){
        int newIndex = getIndex - offsetIndex;
        if(newIndex < 0){                    // if index is negative, make a loop
            newIndex = 44 - Math.abs(newIndex);
        }
        return newIndex;
    }

    private int getOriginalIndex(int getIndex, int offsetIndex ){
        int originalIndex = getIndex + offsetIndex;
        if(originalIndex > 43){
            originalIndex = originalIndex - 44;
        }
        return originalIndex;
    }

}