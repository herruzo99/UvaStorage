import java.io.*;
import java.util.Random;

public class generator {
    public static void main(String[] args) { // args: nombre fich / num tests / num nums por test / tam nums
        if(args.length < 3){
            throw new IllegalArgumentException("número insuficiente de argumentos \n args: nombre fich / num tests / num nums por test / [tam nums]");
        }

        Random rand = new Random();
        FileWriter output;
        try {
            output = new FileWriter(args[0]);
            BufferedWriter file = new BufferedWriter(output);

            file.write(args[1]);
            file.newLine();
            for(int i = 0; i < Integer.parseInt(args[1]); i++){

                file.write(Integer.parseInt(args[2]) + " ");

                for(int j = 0; j < Integer.parseInt(args[2]); j++){
                    if(args.length ==4) {
                        file.write(rand.nextInt(Integer.parseInt(args[3])) + " ");
                    }else {
                        file.write(Integer.parseInt(args[2]) - j + " ");
                    }

                }
                file.newLine();




            }
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
